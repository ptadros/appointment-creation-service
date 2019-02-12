package outfittery.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="appointment")
public class AppointmentProperties {
	private String firstTimeSlot;
	private Integer freeSlotsPerDay;
	private Integer slotDurationInMins;
	
	public String getFirstTimeSlot() {
		return firstTimeSlot;
	}
	public void setFirstTimeSlot(String firstTimeSlot) {
		this.firstTimeSlot = firstTimeSlot;
	}
	public Integer getFreeSlotsPerDay() {
		return freeSlotsPerDay;
	}
	public void setFreeSlotsPerDay(Integer freeSlotsPerDay) {
		this.freeSlotsPerDay = freeSlotsPerDay;
	}
	public Integer getSlotDurationInMins() {
		return slotDurationInMins;
	}
	public void setSlotDurationInMins(Integer slotDurationInMins) {
		this.slotDurationInMins = slotDurationInMins;
	}
}
