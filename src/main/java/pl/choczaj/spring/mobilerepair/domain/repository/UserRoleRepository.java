package pl.choczaj.spring.mobilerepair.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.choczaj.spring.mobilerepair.domain.model.UserRole;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findById(Long id);


}
