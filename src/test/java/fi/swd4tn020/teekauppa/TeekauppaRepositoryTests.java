package fi.swd4tn020.teekauppa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.swd4tn020.teekauppa.domain.Producer;
import fi.swd4tn020.teekauppa.domain.ProducerRepository;
import fi.swd4tn020.teekauppa.domain.Matcha;
import fi.swd4tn020.teekauppa.domain.MatchaRepository;
import fi.swd4tn020.teekauppa.domain.User;
import fi.swd4tn020.teekauppa.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeekauppaRepositoryTests {

	@Autowired
	private MatchaRepository mrepository;

	@Autowired
	private ProducerRepository prepository;

	@Autowired
	private UserRepository urepository;

	// Matcha Repository

	@Test
	public void findMatchaById() {
		Matcha matcha = mrepository.findById(5L).get();

		assertThat(matcha.getId()).isEqualTo(5L);
	}

	public void findByNameShouldReturnMatcha() {
		List<Matcha> matchas = mrepository.findByName("Megumi No Mukashi");

		assertThat(matchas).hasSize(1);
		assertThat(matchas.get(0).getDescription()).isEqualTo(
				"Japanese Tencha leaf green tea, ground in traditional stone mills. Sweet and fruity with a strong umami taste.");
	}

	@Test
	public void createNewMatcha() {
		Matcha matcha = mrepository.save(new Matcha("test", "test", "test", 0.00, 0.00, null));

		assertThat(matcha.getId()).isNotNull();
	}

	@Test
	public void deleteMatcha() {
		mrepository.deleteById(5L);
		
		assertThat(mrepository.findById(5L).isEmpty());
	}

	// Producer Repository
	@Test
	public void findByNameShouldReturnProducer() {
		List<Producer> producers = prepository.findByName("Morimoto");

		assertThat(producers).hasSize(1);
		assertThat(producers.get(0).getAbout()).isEqualTo("blablabla...");
	}

	@Test
	public void findProducer() {
		Producer producer = prepository.findById(1L).get();
		
		assertThat(producer.getProducerid()).isEqualTo(1L);
	}

	@Test
	public void createNewProducer() {
		Producer producer = prepository.save(new Producer("Kikkoman", "Japoni", "Hyvä soija"));
		
		assertThat(producer.getProducerid()).isNotNull();
	}

	@Test
	public void deleteProducer() {
		prepository.deleteById(1L);
		
		assertThat(prepository.findById(1L).isEmpty());
	}

	// User Repository

	@Test
	public void findByRoleShouldReturnUser() {
		List<User> users = urepository.findByRole("ADMIN");

		assertThat(users).hasSize(1);
		assertThat(users.get(0).getUsername()).isEqualTo("admin");
	}
	
    @Test
    public void findUser() {
    	User user = urepository.findById(1L).get();
    	
    	assertThat(user.getId()).isEqualTo(1L);
    }

	@Test
	public void createNewUser() {
		User user = urepository.save(new User("test", "test", "TEST"));
		
		assertThat(user.getId()).isNotNull();
	}

	@Test
	public void deleteUser() {
		urepository.deleteById(1L);

		assertThat(urepository.findById(1L)).isEmpty();
	}
}
