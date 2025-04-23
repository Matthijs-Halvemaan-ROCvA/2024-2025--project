package nl.rocva.project.repository;

import nl.rocva.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<User, Long> {
}
