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
import outfittery.configuration.AppointmentProperties;
import outfittery.model.dto.TimeSlot;
import outfittery.model.entity.Appointment;
import outfittery.model.entity.Customer;
import outfittery.model.entity.Stylist;
import outfittery.model.repository.AppointmentRepository;
import outfittery.rest.http.exception.validationErrorException;

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

	public void bookAppointment(Customer customer, Date date, String fromSlot) {
		// TODO: verify that the customer doesn't have an appointment in the same date
		// and time slot before. Otherwise, return an error
		
		// TODO: modify findAvailableSlotByDateAndTime to return single record instead
		// of list and add a transaction block
		// between read and update operations to make sure no two concurrent
		// transactions are updating the same record
		List<Appointment> availableAppointments = appointmentRepoistory.findAvailableSlotByDateAndTime(date, fromSlot);
		if (availableAppointments.isEmpty())
			throw new validationErrorException("This booking slot is no longer available");

		Appointment app = availableAppointments.get(0);
		app.setBookedAt(new Date());
		app.setBookedBy(customer);
		appointmentRepoistory.save(app);
	}

}
