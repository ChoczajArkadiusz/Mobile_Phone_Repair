package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.Employee;
import pl.choczaj.spring.mobilerepair.domain.model.User;
import pl.choczaj.spring.mobilerepair.domain.model.UserRole;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.UserRepository;
import pl.choczaj.spring.mobilerepair.web.dto.UserFormDto;

@Service
public class UserService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


}
