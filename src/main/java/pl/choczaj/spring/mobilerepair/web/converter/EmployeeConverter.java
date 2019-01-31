package pl.choczaj.spring.mobilerepair.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.choczaj.spring.mobilerepair.domain.model.Employee;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;

public class EmployeeConverter implements Converter<String, Employee> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void EmployeeConverter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee convert(String s) {
        return employeeRepository.findById(Long.parseLong(s)).orElse(null);
    }
}
