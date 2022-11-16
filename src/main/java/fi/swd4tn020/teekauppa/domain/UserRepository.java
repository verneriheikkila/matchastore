package fi.swd4tn020.teekauppa.domain;
import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

	List<User> findByRole(String string);
}