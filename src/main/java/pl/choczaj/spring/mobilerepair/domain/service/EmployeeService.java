package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.Employee;
import pl.choczaj.spring.mobilerepair.domain.model.Task;
import pl.choczaj.spring.mobilerepair.domain.model.UserRole;
import pl.choczaj.spring.mobilerepair.domain.model.UserRoleEnum;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.UserRoleRepository;
import pl.choczaj.spring.mobilerepair.web.dto.EmployeeDto;

import java.util.List;


@Service
@Transactional
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private TaskRepository taskRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.taskRepository = taskRepository;
    }

    public EmployeeDto getById(Long id) {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            employeeDto.setId(employee.getId());
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setPassword(employee.getPassword());
            employeeDto.setPhone(employee.getPhone());
            employeeDto.setAddress(employee.getAddress());
            employeeDto.setWorkHourCost(employee.getWorkHourCost());
        }
        return employeeDto;
    }

    public boolean save(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        if (employeeDto != null) {
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());
            employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
            employee.setEnabled(true);
            employee.setEnabled(true);
            employee.setPhone(employeeDto.getPhone());
            employee.setAddress(employeeDto.getAddress());
            employee.setWorkHourCost(employeeDto.getWorkHourCost());
            employeeRepository.save(employee);

            for (UserRoleEnum roleName : employeeDto.getRoles()) {
                UserRole userRole = new UserRole();
                userRole.setUser(employee);
                userRole.setRole(roleName);
                userRoleRepository.save(userRole);
            }
        }
        return true;
    }

    public List<Task> findAllTasksByEmployeeId(Long id){
        return taskRepository.findAllTasksByEmployeeId(id);
    }


}
