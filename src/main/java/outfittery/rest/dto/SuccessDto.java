package outfittery.rest.dto;

/***
 * This is a class for general success response
 * @author peter
 *
 */
public class SuccessDto {
	private String message;
	
	public SuccessDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
