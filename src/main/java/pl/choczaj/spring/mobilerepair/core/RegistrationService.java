package pl.choczaj.spring.mobilerepair.core;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;

@Service
@Transactional
public class RegistrationService {

    private EmployeeRepository employeeRepository;

    public RegistrationService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


}
