package outfittery.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import outfittery.model.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void testCreateNewCustomer() {
		Customer newCustomer = new Customer("Peter", "peter@test.com");
		Customer savedCustomer = customerRepository.save(newCustomer);

		assertThat(savedCustomer.getName()).isEqualTo(newCustomer.getName());
		assertThat(savedCustomer.getEmail()).isEqualTo(newCustomer.getEmail());
		assertThat(savedCustomer.getId()).isNotNull();
	}
}
