package pl.choczaj.spring.mobilerepair.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.choczaj.spring.mobilerepair.domain.model.Employee;
import pl.choczaj.spring.mobilerepair.domain.model.TaskStatus;
import pl.choczaj.spring.mobilerepair.domain.model.UserRole;
import pl.choczaj.spring.mobilerepair.domain.model.UserRoleEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);

    Optional<Employee> findByEmail(String email);

//    List<Employee> findAllHavingRoleIn(UserRoleEnum... userRoles);

}
