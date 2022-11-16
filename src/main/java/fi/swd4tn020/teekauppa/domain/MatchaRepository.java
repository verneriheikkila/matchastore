package fi.swd4tn020.teekauppa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MatchaRepository extends CrudRepository<Matcha, Long> {
	List<Matcha> findByName(String name);
}
