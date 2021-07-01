package in.siva.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import in.siva.exception.DBException;
import in.siva.exception.ValidException;
import in.siva.model.BankError;

@ControllerAdvice
public class UserExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(ValidException.class)
	public ResponseEntity<BankError> mapValidException(ValidException e) {

		BankError errorMessage = new BankError(e.getMessage(), HttpStatus.BAD_REQUEST);
		e.printStackTrace();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DBException.class)
	public ResponseEntity<BankError> mapDBException(DBException e) {
		BankError errorMessage = new BankError(e.getMessage(), HttpStatus.BAD_REQUEST);
		e.printStackTrace();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
