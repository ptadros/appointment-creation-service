package outfittery.model.dto;

import java.util.Date;
/***
 * This is a custom TimeSlot object to represent slots from database
 * @author peter
 *
 */
public class TimeSlot {
	private Date date;
	private String from;
	
	public TimeSlot(Date date, String from) {
		this.date = date;
		this.from = from;
	}

	public Date getDate() {
		return date;
	}

	public String getFrom() {
		return from;
	}
}
