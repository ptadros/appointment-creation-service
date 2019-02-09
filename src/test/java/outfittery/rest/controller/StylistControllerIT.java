package outfittery.rest.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

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

import outfittery.model.entity.Stylist;
import outfittery.model.repository.StylistRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StylistControllerIT {
	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;
	
	@Autowired
	private StylistRepository stylistRepository;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		stylistRepository.deleteAll();
	}

	@Test
	public void testAddStylistSuccess() throws Exception {
		Stylist newStylist = new Stylist("john", "john@test.com");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		HttpEntity<Stylist> request = new HttpEntity<>(newStylist, headers);

		ResponseEntity<String> response = template.postForEntity(base.toString() + "/stylists", request, String.class);
		// Verify request succeed
		assertThat(201, equalTo(response.getStatusCodeValue()));
	}
	
	@Test
	public void testAddStylistFailureWithValidationError() throws Exception {
		Stylist newStylist = new Stylist("john", "john@test.com");
		stylistRepository.save(newStylist);
		
		Stylist duplicateStylist = new Stylist("john", "john@test.com");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		HttpEntity<Stylist> request = new HttpEntity<>(duplicateStylist, headers);

		ResponseEntity<String> response = template.postForEntity(base.toString() + "/customers", request, String.class);
		// Verify request failed
		assertThat(422, equalTo(response.getStatusCodeValue()));
	}
}
