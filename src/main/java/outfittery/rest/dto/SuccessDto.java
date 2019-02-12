package outfittery.rest.dto;

public class SuccessDto {
	private String message;
	
	public SuccessDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
