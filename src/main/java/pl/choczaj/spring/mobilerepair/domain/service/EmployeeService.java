package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.*;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.UserRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.UserRoleRepository;
import pl.choczaj.spring.mobilerepair.web.dto.EmployeeAvailabilityDto;
import pl.choczaj.spring.mobilerepair.web.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private TaskRepository taskRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder,
                           UserRepository userRepository, UserRoleRepository userRoleRepository,
                           TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
            employeeDto.setRoles(userRoleRepository.findAllByUserId(id).stream().map(r -> r.getRole()).collect(Collectors.toList()));
            employeeDto.setEnabled(employee.isEnabled());
        }
        return employeeDto;
    }

    public boolean save(EmployeeDto employeeDto, boolean newUser) {
        Employee employee = new Employee();
        if (employeeDto != null) {
            employee.setId(employeeDto.getId());
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());
            if (newUser) {
                employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
                employee.setEnabled(true);
            } else {
                Employee employeeInDb = employeeRepository.findById(employeeDto.getId()).orElse(null);
                employee.setPassword(employeeInDb.getPassword());
                employee.setEnabled(employeeDto.getEnabled());
            }
            employee.setPhone(employeeDto.getPhone());
            employee.setAddress(employeeDto.getAddress());
            employee.setWorkHourCost(employeeDto.getWorkHourCost());
            employeeRepository.save(employee);

            employee.setRoles(userRoleRepository.findAllByUserId(employee.getId()));
            for (UserRoleEnum roleName : employeeDto.getRoles()) {
                if (!employee.hasRole(roleName)) {
                    UserRole userRole = new UserRole();
                    userRole.setUser(employee);
                    userRole.setRole(roleName);
                    userRoleRepository.save(userRole);
                }
            }
            for (UserRole role : employee.getRoles()) {
                if (!employeeDto.getRoles().contains(role.getRole())) {
                    userRoleRepository.delete(role);
                }
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
        List<Employee> allEmployees = employeeRepository.findAll();
        List<EmployeeAvailabilityDto> availableEmployees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            EmployeeAvailabilityDto newEmplDto = new EmployeeAvailabilityDto();
            newEmplDto.setId(employee.getId());
            newEmplDto.setEmail(employee.getEmail());
            newEmplDto.setWorkHourCost(employee.getWorkHourCost());
            newEmplDto.setHours(8.0);
            availableEmployees.add(newEmplDto);
        }
        List<Task> activeTasks = taskRepository.findAllByStatusNotIn(TaskStatus.REPAIRED, TaskStatus.CANCELED, TaskStatus.DELIVERED);
        for (Task activeTask : activeTasks) {
            reduceTime(availableEmployees, activeTask.getEmployee().getId(), activeTask.getPart().getWorkHours());
        }
        return availableEmployees;
    }

    private void reduceTime(List<EmployeeAvailabilityDto> list, Long id, Double reduceTime) {
        for (EmployeeAvailabilityDto employee : list) {
            if (employee.getId().equals(id)) {
                employee.setHours(employee.getHours() - reduceTime);
            }
        }
    }

    public void initWithDemoEmployees() {
        Employee[] demoEmployees = new Employee[3];
        for (int i = 0; i < demoEmployees.length; i++) {
            demoEmployees[i] = new Employee();
        }
        demoEmployees[0].setFirstName("Jan");
        demoEmployees[0].setLastName("Nowak");
        demoEmployees[0].setEmail("jan.nowak@mobile.pl");
        demoEmployees[0].setWorkHourCost(75.0);
        demoEmployees[0].setPhone("+48 654654654");
        demoEmployees[0].setAddress("Wrocław, ul. Prosta 1");

        demoEmployees[1].setFirstName("Tomasz");
        demoEmployees[1].setLastName("Kowal");
        demoEmployees[1].setEmail("tomasz.kowal@mobile.pl");
        demoEmployees[1].setWorkHourCost(80.0);
        demoEmployees[1].setPhone("+48 698698698");
        demoEmployees[1].setAddress("Wrocław, ul. Krzywa 12/4");

        demoEmployees[2].setFirstName("Anna");
        demoEmployees[2].setLastName("Kowalska");
        demoEmployees[2].setEmail("anna.kowalska@mobile.pl");
        demoEmployees[2].setWorkHourCost(89.0);
        demoEmployees[2].setPhone("+48 612126612");
        demoEmployees[2].setAddress("Wrocław, ul. Kręta 2/14");

        for (Employee employee : demoEmployees) {
            employee.setPassword(passwordEncoder.encode("demo"));
            employee.setEnabled(true);
            userRepository.save(employee);
            UserRole employeeRole = new UserRole();
            employeeRole.setRole(UserRoleEnum.ROLE_EMPLOYEE);
            employeeRole.setUser(employee);
            userRoleRepository.save(employeeRole);
        }
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void initWithDemoManagers() {
        Employee demoManager = new Employee();
        demoManager.setFirstName("Marcin");
        demoManager.setLastName("Janik");
        demoManager.setEmail("marcin.janik@mobile.pl");
        demoManager.setWorkHourCost(89.0);
        demoManager.setPhone("+48 632632632");
        demoManager.setAddress("Wrocław, ul. Kwiatowa 43/1");
        demoManager.setPassword(passwordEncoder.encode("demo"));
        demoManager.setEnabled(true);
        userRepository.save(demoManager);

        UserRole managerRole = new UserRole();
        managerRole.setRole(UserRoleEnum.ROLE_MANAGER);
        managerRole.setUser(demoManager);
        userRoleRepository.save(managerRole);

        UserRole employeeRole = new UserRole();
        employeeRole.setRole(UserRoleEnum.ROLE_EMPLOYEE);
        employeeRole.setUser(demoManager);
        userRoleRepository.save(employeeRole);
    }

    public boolean anonymize(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            Random rand = new Random();
            String randName = String.format("anonim%09d", rand.nextInt(1000000000));
            employee.setFirstName(randName);
            employee.setLastName(randName);
            employee.setEmail(String.format("%s@mobile.pl", randName));
            employee.setPhone("000000000");
            employee.setAddress("-");
            employee.setEnabled(false);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

}
