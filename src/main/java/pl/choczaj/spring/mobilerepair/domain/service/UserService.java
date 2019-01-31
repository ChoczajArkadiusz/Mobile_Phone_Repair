package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.Employee;
import pl.choczaj.spring.mobilerepair.domain.model.UserRole;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;
import pl.choczaj.spring.mobilerepair.web.dto.UserFormDto;

@Service
public class UserService {

    EmployeeRepository employeeRepository;
    PasswordEncoder passwordEncoder;

    public UserService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean register(UserFormDto form) {
        Employee employee = new Employee();
        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getFirstName());
        employee.setEmail(form.getEmail());
        employee.setPassword(passwordEncoder.encode(form.getPassword()));
        employeeRepository.save(employee);

        UserRole userRole = new UserRole();
        userRole.setUser(employee);
        return true;


    }
}
