package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.Customer;
import pl.choczaj.spring.mobilerepair.domain.model.Device;
import pl.choczaj.spring.mobilerepair.domain.model.Task;
import pl.choczaj.spring.mobilerepair.domain.repository.CustomerRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.DeviceRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;
import pl.choczaj.spring.mobilerepair.web.dto.DeviceDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceService {

    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;
    private TaskRepository taskRepository;

    public DeviceService(DeviceRepository deviceRepository, CustomerRepository customerRepository, TaskRepository taskRepository) {
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
        this.taskRepository = taskRepository;
    }

    public DeviceDto prepareNewDto() {
        List<Customer> customers = customerRepository.findAll();
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setCustomersEmails(customers.stream().map(c -> c.getEmail()).collect(Collectors.toList()));
        return deviceDto;
    }

    public DeviceDto findById(Long id) {
        Device device = deviceRepository.findById(id).orElse(null);
        List<Customer> customers = customerRepository.findAll();
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setCustomersEmails(customers.stream().map(c -> c.getEmail()).collect(Collectors.toList()));
        deviceDto.setId(device.getId());
        deviceDto.setManufacturer(device.getManufacturer());
        deviceDto.setModel(device.getModel());
        deviceDto.setDescription(device.getDescription());
        if (device.getOwner() != null) {
            deviceDto.setOwner(device.getOwner().getEmail());
        }
        return deviceDto;
    }

    public Device findDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public void save(DeviceDto deviceDto) {
        Device device = new Device();

        device.setId(deviceDto.getId());
        device.setManufacturer(deviceDto.getManufacturer());
        device.setModel(deviceDto.getModel());
        device.setDescription(deviceDto.getDescription());
        device.setOwner(customerRepository.findByEmail(deviceDto.getOwner()).orElse(null));
        deviceRepository.save(device);
    }

    public void delete(DeviceDto deviceDto) {
        Device device = new Device();
        if (deviceDto != null) {
            List<Task> tasks = taskRepository.findAllByDeviceId(deviceDto.getId());
            for (Task task : tasks) {
                taskRepository.delete(task);
            }
            device.setId(deviceDto.getId());
            deviceRepository.delete(device);
        }
    }

    public List<Task> findAllTasksByDeviceId(Long id) {
        return taskRepository.findAllByDeviceId(id);
    }

    public void deleteAll() {
        deviceRepository.deleteAll();
    }

    public void initWithDemoDevices() {
        Device[] demoDevices = new Device[8];
        for (int i = 0; i < demoDevices.length; i++) {
            demoDevices[i] = new Device();
        }
        demoDevices[0].setManufacturer("Samsung");
        demoDevices[0].setModel("S6");
        demoDevices[0].setDescription("czarny");
        demoDevices[0].setOwner(customerRepository.findByEmail("michal.p@wp.pl").orElse(null));

        demoDevices[1].setManufacturer("Xiaomi");
        demoDevices[1].setModel("Mi6");
        demoDevices[1].setDescription("czarny");
        demoDevices[1].setOwner(customerRepository.findByEmail("janusz.k@wp.pl").orElse(null));

        demoDevices[2].setManufacturer("Xiaomi");
        demoDevices[2].setModel("Redmi Note 5");
        demoDevices[2].setDescription("czarny");
        demoDevices[2].setOwner(customerRepository.findByEmail("kasia.n@wp.pl").orElse(null));

        demoDevices[3].setManufacturer("Samsung");
        demoDevices[3].setModel("S6");
        demoDevices[3].setDescription("niebieski");
        demoDevices[3].setOwner(customerRepository.findByEmail("kaja.k@wp.pl").orElse(null));

        demoDevices[4].setManufacturer("Xiaomi");
        demoDevices[4].setModel("Redmi Note 5");
        demoDevices[4].setDescription("niebieski");
        demoDevices[4].setOwner(customerRepository.findByEmail("dariusz.m@wp.pl").orElse(null));

        demoDevices[5].setManufacturer("Xiaomi");
        demoDevices[5].setModel("Mi6");
        demoDevices[5].setDescription("czarny, czarne etui");
        demoDevices[5].setOwner(customerRepository.findByEmail("dariusz.m@wp.pl").orElse(null));

        demoDevices[6].setManufacturer("Samsung");
        demoDevices[6].setModel("S7");
        demoDevices[6].setDescription("czarny");
        demoDevices[6].setOwner(customerRepository.findByEmail("michal.p@wp.pl").orElse(null));

        demoDevices[7].setManufacturer("Samsung");
        demoDevices[7].setModel("S7");
        demoDevices[7].setDescription("biały, czarne etui");
        demoDevices[7].setOwner(customerRepository.findByEmail("agnieszka.m@wp.pl").orElse(null));

        for (Device device : demoDevices) {
            deviceRepository.save(device);
        }
    }


}
