package pl.choczaj.spring.mobilerepair.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.choczaj.spring.mobilerepair.domain.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    Optional<Customer> findByEmail(String email);
}
