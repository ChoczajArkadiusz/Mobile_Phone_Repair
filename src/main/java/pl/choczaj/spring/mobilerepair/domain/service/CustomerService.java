package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.*;
import pl.choczaj.spring.mobilerepair.domain.repository.*;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    private DeviceRepository deviceRepository;
    private TaskRepository taskRepository;

    public CustomerService(CustomerRepository customerRoleRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, DeviceRepository deviceRepository,
                           TaskRepository taskRepository) {
        this.customerRepository = customerRoleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.deviceRepository = deviceRepository;
        this.taskRepository = taskRepository;
    }

    public boolean anonymize(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            Random rand = new Random();
            String randName = String.format("anonim%09d", rand.nextInt(1000000000));
            customer.setFirstName(randName);
            customer.setLastName(randName);
            customer.setEmail(String.format("%s@mobile.pl", randName));
            customer.setPhone("000000000");
            customer.setAddress("-");
            customer.setEnabled(false);
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    public void delete(Customer customer) {
        List<Device> devices = deviceRepository.findAllByOwnerId(customer.getId());
        for (Device device : devices) {
            List<Task> tasks = taskRepository.findAllByDeviceId(device.getId());
            for (Task task : tasks) {
                taskRepository.delete(task);
            }
            deviceRepository.delete(device);
        }
        customerRepository.delete(customer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public void initWithDemoCusomers() {
        Customer[] demoCustomers = new Customer[6];
        for (int i = 0; i < demoCustomers.length; i++) {
            demoCustomers[i] = new Customer();
        }
        demoCustomers[0].setFirstName("Michał");
        demoCustomers[0].setLastName("Polak");
        demoCustomers[0].setEmail("michal.p@wp.pl");
        demoCustomers[0].setPhone("+48 678000001");
        demoCustomers[0].setAddress("Wrocław, ul. Testowa 2/5");

        demoCustomers[1].setFirstName("Janusz");
        demoCustomers[1].setLastName("Kowalewski");
        demoCustomers[1].setEmail("janusz.k@wp.pl");
        demoCustomers[1].setPhone("+48 678000002");
        demoCustomers[1].setAddress("Wrocław, ul. Testowa 23");

        demoCustomers[2].setFirstName("Kasia");
        demoCustomers[2].setLastName("Nowak");
        demoCustomers[2].setEmail("kasia.n@wp.pl");
        demoCustomers[2].setPhone("+48 678000003");
        demoCustomers[2].setAddress("Wrocław, ul. Kręta 22/3");

        demoCustomers[3].setFirstName("Kaja");
        demoCustomers[3].setLastName("Krzywa");
        demoCustomers[3].setEmail("kaja.k@wp.pl");
        demoCustomers[3].setPhone("+48 678000004");
        demoCustomers[3].setAddress("Wrocław, ul. Reymonta 10/8");

        demoCustomers[4].setFirstName("Dariusz");
        demoCustomers[4].setLastName("Mirkowski");
        demoCustomers[4].setEmail("dariusz.m@wp.pl");
        demoCustomers[4].setPhone("+48 678000005");
        demoCustomers[4].setAddress("Wrocław, ul. Bzowa 19");

        demoCustomers[5].setFirstName("Agnieszka");
        demoCustomers[5].setLastName("Michalik");
        demoCustomers[5].setEmail("agnieszka.m@wp.pl");
        demoCustomers[5].setPhone("+48 678000006");
        demoCustomers[5].setAddress("Wrocław, ul. Zmyślona 16");

        for (Customer customer : demoCustomers) {
            customer.setPassword(passwordEncoder.encode("demo"));
            customer.setEnabled(true);
            customerRepository.save(customer);
            UserRole customerRole = new UserRole();
            customerRole.setRole(UserRoleEnum.ROLE_CUSTOMER);
            customerRole.setUser(customer);
            userRoleRepository.save(customerRole);
        }
    }



}
