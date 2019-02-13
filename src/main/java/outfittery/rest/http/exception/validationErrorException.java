package outfittery.rest.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class represents validationError response error for invalid request
 * parameters
 * 
 * @author peter
 *
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class validationErrorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public validationErrorException(String exception) {
		super(exception);
	}
}
