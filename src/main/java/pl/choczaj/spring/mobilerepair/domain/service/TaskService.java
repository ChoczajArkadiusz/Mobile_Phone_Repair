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
    private EmployeeRepository employeeRepository;
    private PartRepository partRepository;
    private CustomerRepository customerRepository;

    public TaskService(TaskRepository taskRepository, DeviceRepository deviceRepository,
                       EmployeeRepository employeeRepository, PartRepository partRepository,
                       CustomerRepository customerRepository) {
        this.taskRepository = taskRepository;
        this.deviceRepository = deviceRepository;
        this.employeeRepository = employeeRepository;
        this.partRepository = partRepository;
        this.customerRepository = customerRepository;
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

    public void save(Task task) {
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

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }

    public void initWithDemoTasks() {
        Task[] demoTasks = new Task[10];
        for (int i = 0; i < demoTasks.length; i++) {
            demoTasks[i] = new Task();
        }

        demoTasks[0].setDevice(deviceRepository.findByModelAndDescription("S6", "czarny").get());
        demoTasks[0].setPart(partRepository.findBySerialNumber("456465465").get());
        demoTasks[0].setEmployee(employeeRepository.findByEmail("jan.nowak@mobile.pl").get());
        demoTasks[0].setProblemDescription("bateria napęczniała");

        demoTasks[1].setDevice(deviceRepository.findByModelAndDescription("Mi6", "czarny").get());
        demoTasks[1].setPart(partRepository.findBySerialNumber("123465987").get());
        demoTasks[1].setEmployee(employeeRepository.findByEmail("tomasz.kowal@mobile.pl").get());
        demoTasks[1].setProblemDescription("ekran świeci tylko na zielono");

        demoTasks[2].setDevice(deviceRepository.findByModelAndDescription("Redmi Note 5", "czarny").get());
        demoTasks[2].setPart(partRepository.findBySerialNumber("129465789").get());
        demoTasks[2].setEmployee(employeeRepository.findByEmail("anna.kowalska@mobile.pl").get());
        demoTasks[2].setProblemDescription("piszczy podczas rozmów");

        demoTasks[3].setDevice(deviceRepository.findByModelAndDescription("S6", "niebieski").get());
        demoTasks[3].setPart(partRepository.findBySerialNumber("456465789").get());
        demoTasks[3].setEmployee(employeeRepository.findByEmail("jan.nowak@mobile.pl").get());
        demoTasks[3].setProblemDescription("brak dźwięku");

        demoTasks[4].setDevice(deviceRepository.findByModelAndDescription("Redmi Note 5", "niebieski").get());
        demoTasks[4].setPart(partRepository.findBySerialNumber("129465987").get());
        demoTasks[4].setEmployee(employeeRepository.findByEmail("tomasz.kowal@mobile.pl").get());
        demoTasks[4].setProblemDescription("stłuczony ekran");

        demoTasks[5].setDevice(deviceRepository.findByModelAndDescription("Mi6", "czarny, czarne etui").get());
        demoTasks[5].setPart(partRepository.findBySerialNumber("123465987").get());
        demoTasks[5].setEmployee(employeeRepository.findByEmail("anna.kowalska@mobile.pl").get());
        demoTasks[5].setProblemDescription("stłuczony ekran");

        demoTasks[6].setDevice(deviceRepository.findByModelAndDescription("S7", "czarny").get());
        demoTasks[6].setPart(partRepository.findBySerialNumber("459465437").get());
        demoTasks[6].setEmployee(employeeRepository.findByEmail("anna.kowalska@mobile.pl").get());
        demoTasks[6].setProblemDescription("nie dizała digitizer");

        demoTasks[7].setDevice(deviceRepository.findByModelAndDescription("S7", "biały, czarne etui").get());
        demoTasks[7].setPart(partRepository.findBySerialNumber("459498784").get());
        demoTasks[7].setEmployee(employeeRepository.findByEmail("jan.nowak@mobile.pl").get());
        demoTasks[7].setProblemDescription("brak dźwięku");

        demoTasks[8].setDevice(deviceRepository.findByModelAndDescription("Redmi Note 5", "niebieski").get());
        demoTasks[8].setPart(partRepository.findBySerialNumber("129465789").get());
        demoTasks[8].setEmployee(employeeRepository.findByEmail("tomasz.kowal@mobile.pl").get());
        demoTasks[8].setProblemDescription("brak dźwięku");

        demoTasks[9].setDevice(deviceRepository.findByModelAndDescription("Mi6", "czarny, czarne etui").get());
        demoTasks[9].setPart(partRepository.findBySerialNumber("123465465").get());
        demoTasks[9].setEmployee(employeeRepository.findByEmail("jan.nowak@mobile.pl").get());
        demoTasks[9].setProblemDescription("przegrzewa się");

        for (Task taks : demoTasks) {
            taks.setStatus(TaskStatus.REGISTERED);
            taskRepository.save(taks);
        }
    }


}
