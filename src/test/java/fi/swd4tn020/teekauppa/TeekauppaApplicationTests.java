package fi.swd4tn020.teekauppa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import fi.swd4tn020.teekauppa.webcontroller.MatchaController;
import fi.swd4tn020.teekauppa.webcontroller.UserController;
import fi.swd4tn020.teekauppa.webcontroller.ProducerController;
import fi.swd4tn020.teekauppa.webcontroller.ShoppingCartController;


@SpringBootTest
class TeekauppaApplicationTests {
	@Autowired 
	private MatchaController matchaController;
	
	@Autowired
	private ProducerController producerController;
	
	@Autowired
	private ShoppingCartController ShoppingCartController;
	
	@Autowired 
	private UserController userController;

	@Test
	void contextLoads() {
		assertThat(matchaController).isNotNull();
		assertThat(producerController).isNotNull();
		assertThat(ShoppingCartController).isNotNull();
		assertThat(userController).isNotNull();
	}
}
