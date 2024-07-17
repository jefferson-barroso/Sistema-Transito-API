package com.example.demo.api.exceptionhandler;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice // notação de captura global
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		
		ProblemDetail problemDetail =  ProblemDetail.forStatus(status);
		problemDetail.setTitle("Um ou mais campos estão invalidos");
		//customizando campos
		Map<String, String> fields = ex.getBindingResult().getAllErrors() //retorna lista com campos q estão com problemas
				.stream()
				.collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
										objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
		
		problemDetail.setProperty("fields", fields);
		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}
	
	 //Exception antes utilizada\\\\\\
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<String> handleNegocio(NegocioException e){
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle(e.getMessage());
		return problemDetail;
		
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ProblemDetail handleEntidadeNaoEncontrada(NegocioException e){
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetail.setTitle(e.getMessage());
		return problemDetail;
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ProblemDetail handleDataIntegrity(DataIntegrityViolationException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT); 
		problemDetail.setTitle("Recurso em uso");
		
		return problemDetail; 
	}
		
}
