package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.*;
import pl.choczaj.spring.mobilerepair.domain.repository.*;
import pl.choczaj.spring.mobilerepair.web.dto.TaskDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {

    private TaskRepository taskRepository;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private PartRepository partRepository;

    public TaskService(TaskRepository taskRepository, DeviceRepository deviceRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, PartRepository partRepository) {
        this.taskRepository = taskRepository;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.partRepository = partRepository;
    }

    public TaskDto prepareNewDto(Device device) {
        List<Employee> employees = employeeRepository.findAll();
        List<Part> parts = partRepository.findPartsByManufacturerAndModel(device.getManufacturer(), device.getModel());
        TaskDto taskDto = new TaskDto();
        taskDto.setEmployeesEmails(employees.stream().map(c -> c.getEmail()).collect(Collectors.toList()));
        taskDto.setParts(parts);
        taskDto.setDevice(device);
        return taskDto;
    }

    public void save(Task task){
        taskRepository.save(task);
    }

    public void save(TaskDto taskDto) {
        Task task = new Task();

        taskDto.setDevice(deviceRepository.findById(taskDto.getDevice().getId()).orElse(null));
        taskDto.setPart(partRepository.findById(taskDto.getPart().getId()).orElse(null));

        task.setId(taskDto.getId());
        task.setDevice(taskDto.getDevice());
        task.setPart(taskDto.getPart());
        task.setEmployee(employeeRepository.findByEmail(taskDto.getEmployeeEmail()).orElse(null));
        task.setStatus(TaskStatus.REGISTERED);
        task.setProblemDescription(taskDto.getProblemDescription());
        taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElse(null);
    }



}
