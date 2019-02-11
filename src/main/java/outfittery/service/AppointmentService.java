package outfittery.service;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import outfittery.Application;
import outfittery.AppointmentProperties;
import outfittery.model.dto.TimeSlot;
import outfittery.model.entity.Appointment;
import outfittery.model.entity.Stylist;
import outfittery.model.repository.AppointmentRepository;

@Service
@ConfigurationProperties
public class AppointmentService {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private AppointmentProperties appointmentProperties;

	@Autowired
	private AppointmentRepository appointmentRepoistory;

	public AppointmentService() {
	}

	public void generateFreeSlots(Stylist stylist, Integer daysInFuture) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		IntStream.range(0, daysInFuture).forEach(day -> {
			log.info((day + 1) + "");

			c.add(Calendar.DATE, 1);
			log.info("Current date " + c.getTime().toString());

			IntStream.range(0, appointmentProperties.getFreeSlotsPerDay()).forEach(slotNo -> {
				LocalTime startTimeSlot = LocalTime.parse(appointmentProperties.getFirstTimeSlot())
						.plusMinutes(slotNo * appointmentProperties.getSlotDurationInMins());
				LocalTime endTimeSlot = startTimeSlot.plusMinutes(appointmentProperties.getSlotDurationInMins());
				Appointment app = new Appointment(stylist, null, c.getTime(), startTimeSlot.toString(),
						endTimeSlot.toString());
				appointmentRepoistory.save(app);

			});
		});
	}

	public List<TimeSlot> getAvailableSlots() {
		return appointmentRepoistory.getAvailableSlots(new Date());
	}

}
