package outfittery.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import outfittery.model.entity.Stylist;
import outfittery.model.repository.StylistRepository;
import outfittery.rest.http.exception.validationErrorException;
/***
 * This class contains all endpoints of the Stylist resource
 * @author peter
 *
 */
@RestController
public class StylistController {

	@Autowired
	private StylistRepository stylistRepository;

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
	public Stylist createNewStylist(@RequestBody @Valid Stylist newStylist) {
		if (stylistRepository.existsByEmail(newStylist.getEmail()))
			throw new validationErrorException("Email is already registered");
		return stylistRepository.save(newStylist);
	}
}
