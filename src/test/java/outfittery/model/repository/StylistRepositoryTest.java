package outfittery.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import outfittery.model.entity.Stylist;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class StylistRepositoryTest {
	@Autowired
	private StylistRepository stylistRepository;

	@Test
	public void testCreateNewCustomer() {
		Stylist newStylist = new Stylist("stylist 1", "stylist1@test.com");
		Stylist savedStylist = stylistRepository.save(newStylist);

		assertThat(savedStylist.getName()).isEqualTo(newStylist.getName());
		assertThat(savedStylist.getEmail()).isEqualTo(newStylist.getEmail());
		assertThat(savedStylist.getId()).isNotNull();
	}
}
