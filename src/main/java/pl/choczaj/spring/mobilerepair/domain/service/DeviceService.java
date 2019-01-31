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
            device.setId(deviceDto.getId());
            deviceRepository.delete(device);
        }
    }

    public List<Task> findAllTasksByDeviceId(Long id){
        return taskRepository.findAllByDeviceId(id);
    }

}
