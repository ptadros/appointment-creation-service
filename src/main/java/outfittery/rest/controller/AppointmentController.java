package outfittery.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import outfittery.model.dto.TimeSlot;
import outfittery.model.entity.Customer;
import outfittery.model.repository.AppointmentRepository;
import outfittery.model.repository.CustomerRepository;
import outfittery.rest.dto.AppointmentBooking;
import outfittery.rest.dto.SuccessDto;
import outfittery.service.AppointmentService;

/***
 * This class contains all end-points of the Appointment resource
 * 
 * @author peter
 *
 */
@RestController
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AppointmentService appointmentService;

	/***
	 * Gets list of available appointments
	 * 
	 * @return list of all available slots
	 */
	@GetMapping("/appointments/available")
	public List<TimeSlot> getAvailableSlots() {
		// TODO: add a new method getAvailableSlots to AppointmentService to separate
		// the the controller from the model layer
		// TODO: limit availability to max X future days 
		return appointmentRepository.getAvailableSlots(new Date());
	}

	/***
	 * End-point to book an appointment for a customer in a specific date and time
	 * slot
	 * 
	 * @param appointmentBooking
	 * @return SuccessDto
	 */
	@PostMapping("/appointments/book")
	@ResponseStatus(HttpStatus.CREATED)
	public SuccessDto bookAppointment(@RequestBody AppointmentBooking appointmentBooking) {
		// TODO handle exception of invalid customer id
		Customer customer = customerRepository.findById(appointmentBooking.getCustomerId()).get();
		appointmentService.bookAppointment(customer, appointmentBooking.getDate(), appointmentBooking.getFromSlot());
		return new SuccessDto("Appointment is booked successfully");
	}
}
