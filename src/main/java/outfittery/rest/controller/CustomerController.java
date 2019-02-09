package outfittery.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import outfittery.model.entity.Customer;
import outfittery.model.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
    public List<Customer> index() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@PostMapping("/customers")
	@ResponseStatus(HttpStatus.CREATED)
    public Customer createNewCustomer(@RequestBody Customer newCustomer) {
		return customerRepository.save(newCustomer);
	}
}
