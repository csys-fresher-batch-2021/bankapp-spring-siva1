package in.siva.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class BankError {

	public final String errorMessage;
	public final HttpStatus errorCode;
	public final List<FieldError> errorMessages = new ArrayList<>();

	public void addFieldError(String field, String defaultMessage) {
		FieldError error = new FieldError(field, defaultMessage, defaultMessage);
		errorMessages.add(error);
	}
}
