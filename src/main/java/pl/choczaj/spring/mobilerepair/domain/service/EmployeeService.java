package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.*;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.PartRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.UserRoleRepository;
import pl.choczaj.spring.mobilerepair.web.dto.EmployeeAvailabilityDto;
import pl.choczaj.spring.mobilerepair.web.dto.EmployeeDto;

import java.util.ArrayList;
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

    public EmployeeDto findById(Long id) {
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

    public List<Task> findAllTasksByEmployeeId(Long id) {
        return taskRepository.findAllByEmployeeId(id);
    }


    public List<Task> findAllTasksByEmployeeEmail(String email) {
        return taskRepository.findAllByEmployeeEmailAndStatusNotIn(email, TaskStatus.REPAIRED, TaskStatus.CANCELED, TaskStatus.DELIVERED);
    }


    public List<EmployeeAvailabilityDto> findAvailableEmployees() {
        List<EmployeeAvailabilityDto> employees = new ArrayList<>();
        List<Task> activeTasks = taskRepository.findAllByStatusNotIn(TaskStatus.REPAIRED, TaskStatus.CANCELED, TaskStatus.DELIVERED);
        for (Task activeTask : activeTasks) {
            if (!containsEmployeeById(employees, activeTask.getEmployee().getId())) {
                EmployeeAvailabilityDto newEmplDto = new EmployeeAvailabilityDto();
                newEmplDto.setId(activeTask.getEmployee().getId());
                newEmplDto.setEmail(activeTask.getEmployee().getEmail());
                newEmplDto.setWorkHourCost(activeTask.getEmployee().getWorkHourCost());
                newEmplDto.setHours(8.0);
                employees.add(newEmplDto);
            }
            reduceTime(employees, activeTask.getEmployee().getId(), activeTask.getPart().getWorkHours());
        }
        return employees;
    }

    private boolean containsEmployeeById(List<EmployeeAvailabilityDto> list, Long id) {
        for (EmployeeAvailabilityDto employee : list) {
            if (employee.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private void reduceTime(List<EmployeeAvailabilityDto> list, Long id, Double reduceTime) {
        for (EmployeeAvailabilityDto employee : list) {
            if (employee.getId().equals(id)) {
                employee.setHours(employee.getHours() - reduceTime);
            }
        }
    }

}
