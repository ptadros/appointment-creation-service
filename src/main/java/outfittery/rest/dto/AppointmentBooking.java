package outfittery.rest.dto;

import java.util.Date;

public class AppointmentBooking {
	private Date date;
	private String fromSlot;
	private Long customerId;
	
	public AppointmentBooking() {}

	public Date getDate() {
		return date;
	}

	public String getFromSlot() {
		return fromSlot;
	}

	public Long getCustomerId() {
		return customerId;
	};
}
