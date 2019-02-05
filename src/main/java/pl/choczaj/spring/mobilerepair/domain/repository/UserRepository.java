package pl.choczaj.spring.mobilerepair.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.choczaj.spring.mobilerepair.domain.model.Employee;
import pl.choczaj.spring.mobilerepair.domain.model.User;
import pl.choczaj.spring.mobilerepair.domain.model.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

}
