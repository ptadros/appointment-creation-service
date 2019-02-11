package outfittery.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import outfittery.model.dto.TimeSlot;
import outfittery.model.repository.AppointmentRepository;
import outfittery.service.AppointmentService;
/***
 * This class contains all end-points of the Appointment resource
 * @author peter
 *
 */
@RestController
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private AppointmentService appointmentService;
	
	/***
	 * 
	 * @return list of all available slots
	 */
	@GetMapping("/appointments/available")
	public List<TimeSlot> index() {
		return appointmentRepository.getAvailableSlots(new Date());
	}
}
