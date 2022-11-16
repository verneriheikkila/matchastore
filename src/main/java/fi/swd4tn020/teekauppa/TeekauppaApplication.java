package fi.swd4tn020.teekauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.swd4tn020.teekauppa.domain.Matcha;
import fi.swd4tn020.teekauppa.domain.MatchaRepository;
import fi.swd4tn020.teekauppa.domain.Producer;
import fi.swd4tn020.teekauppa.domain.ProducerRepository;
import fi.swd4tn020.teekauppa.domain.User;
import fi.swd4tn020.teekauppa.domain.UserRepository;

@SpringBootApplication
public class TeekauppaApplication {
	private static final Logger log = LoggerFactory.getLogger(TeekauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TeekauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MatchaRepository mrepository, ProducerRepository prepository,
			UserRepository urepository) {
		return (args) -> {

			Producer producer1 = prepository.save(new Producer("Morimoto", "Miyazaki, Japan", "blablabla..."));
			Producer producer2 = prepository
					.save(new Producer("Sugimoto Family", "Uji, Kyoto, Japan", "bölasdlölö..."));
			Producer producer3 = prepository
					.save(new Producer("Marukuy Koyamen", "Uji, Kyoto, Japan", "hölölö pölölö..."));
			Producer producer4 = prepository
					.save(new Producer("Miumori", "Kirishima, Kagoshima, Japan", "asd kood..."));

			log.info("fetch all producers");
			for (Producer producer : prepository.findAll()) {
				log.info(producer.toString());
			}

			mrepository.save(new Matcha("Megumi No Mukashi", "Ceremonial, Usucha",
					"Japanese Tencha leaf green tea, ground in traditional stone mills. Sweet and fruity with a strong umami taste.",
					27.00, 30.00, producer2));

			mrepository.save(new Matcha("Byakuren", "Food Grade", "Excellent cooking matcha suitable for baking.",
					22.00, 100.00, producer3));

			mrepository.save(new Matcha("Miumori", "Ceremonial, Usucha",
					"A tea ceremony quality fine organic matcha from southern Japan. Fresh floral and sweet taste with a hint of umami.",
					27.45, 20.00, producer4));

			mrepository.save(new Matcha("Gyokojou", "Premium",
					"It has a subtly sweet, deep and aromatic flavour with a distinct umami note. Gyokujou offers a refreshing, relaxing and holistic tea experience.",
					19.90, 20.00, producer1));

			log.info("fetch all matcha");
			for (Matcha matcha : mrepository.findAll()) {
				log.info(matcha.toString());
			}

			urepository.save(new User("user", "$2a$10$vc5N3hMOyGuqhdWbg1CUKOqpoFQQOmtXLAROiXtjrDn1VLeslilYC", "USER"));
			urepository
					.save(new User("admin", "$2a$10$9Z.FmUMcc6bkgW18.sMzZuXEXALzy8tt5vsmamof0JOsjUd7EKZNG", "ADMIN"));

			log.info("fetch all users");
			for (User user : urepository.findAll()) {
				log.info(user.toString());
			}
		};
	}
}
