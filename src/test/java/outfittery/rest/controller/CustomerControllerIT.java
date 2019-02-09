package outfittery.rest.controller;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import outfittery.model.entity.Customer;
import outfittery.model.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIT {
	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		customerRepository.deleteAll();
	}

	@Test
	public void testAddCustomerSuccess() throws Exception {
		Customer newCustomer = new Customer("john", "john@test.com");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		HttpEntity<Customer> request = new HttpEntity<>(newCustomer, headers);

		ResponseEntity<String> response = template.postForEntity(base.toString() + "/customers", request, String.class);
		// Verify request succeed
		assertThat(201, equalTo(response.getStatusCodeValue()));
	}
	
	@Test
	public void testAddCustomerFailureWithValidationError() throws Exception {
		Customer newCustomer = new Customer("john", "john@test.com");
		customerRepository.save(newCustomer);
		
		Customer duplicateCustomer = new Customer("john", "john@test.com");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		HttpEntity<Customer> request = new HttpEntity<>(duplicateCustomer, headers);

		ResponseEntity<String> response = template.postForEntity(base.toString() + "/customers", request, String.class);
		// Verify request failed
		assertThat(422, equalTo(response.getStatusCodeValue()));
	}
}
