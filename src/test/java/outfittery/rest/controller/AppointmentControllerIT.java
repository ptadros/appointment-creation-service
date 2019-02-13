package outfittery.rest.controller;

import java.net.URL;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import outfittery.model.repository.AppointmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentControllerIT {
	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		appointmentRepository.deleteAll();
	}

	@Test
	@Ignore("To be tested later")
	public void testReturnEmptyAppointmentsIfNotAvailable() throws Exception {
	}
	
	@Test
	@Ignore("To be tested later")
	public void testReturnAvailableAppointments() throws Exception {
	}
	
	@Test
	@Ignore("To be tested later")
	public void testBookAppointmentIfAvailable() throws Exception {
	}
	
	@Test
	@Ignore("To be tested later")
	public void testBookAppointmentFailedIfAlreadyBooked() throws Exception {
	}
}
