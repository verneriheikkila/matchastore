package fi.swd4tn020.teekauppa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProducerRepository extends CrudRepository<Producer, Long> {
	List<Producer> findByName(String name);
}
