package outfittery.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import outfittery.model.entity.Stylist;
import outfittery.model.repository.StylistRepository;
import outfittery.rest.dto.SuccessDto;
import outfittery.rest.http.exception.validationErrorException;
import outfittery.service.AppointmentService;
/***
 * This class contains all end-points of the Stylist resource
 * @author peter
 *
 */
@RestController
public class StylistController {

	@Autowired
	private StylistRepository stylistRepository;

	@Autowired
	private AppointmentService appointmentService;
	
	/***
	 * 
	 * @return list of all stylists
	 */
	@GetMapping("/stylists")
	public List<Stylist> index() {
		return (List<Stylist>) stylistRepository.findAll();
	}

	/***
	 * Adds a new stylist to the system
	 * @param newStylist
	 * @return the created stylist with response 201
	 */
	@PostMapping("/stylists")
	@ResponseStatus(HttpStatus.CREATED)
	public Stylist createNewStylist(@RequestBody Stylist newStylist) {
		if (stylistRepository.existsByEmail(newStylist.getEmail()))
			throw new validationErrorException("Email is already registered");
		return stylistRepository.save(newStylist);
	}
	
	@PostMapping("/stylists/{id}/init-free-slots")
	public SuccessDto initStylistCalendar(@PathVariable Long id, @RequestParam Integer days) {
		// TODO handle exception for invalid Stylist id
		Stylist stylist = stylistRepository.findById(id).get();
		appointmentService.generateFreeSlots(stylist, days);
		return new SuccessDto("Initiated free slots for Stylist " + stylist.getId() + "Successfully");
	}
}
