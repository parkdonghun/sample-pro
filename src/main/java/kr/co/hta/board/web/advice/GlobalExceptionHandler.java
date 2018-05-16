package kr.co.hta.board.web.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.co.hta.board.exception.SimpleBoardException;

// 주로 예외처리용이다
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SimpleBoardException.class)
	public String handleException(SimpleBoardException ex) {
		ex.printStackTrace();
		return "error/500.jsp";
		
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		ex.printStackTrace();
		return "error/500.jsp";
	}
}																																		
