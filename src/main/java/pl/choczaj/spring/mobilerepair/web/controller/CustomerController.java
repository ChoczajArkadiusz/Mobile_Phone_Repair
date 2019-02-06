package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Customer;
import pl.choczaj.spring.mobilerepair.domain.model.Device;
import pl.choczaj.spring.mobilerepair.domain.repository.CustomerRepository;
import pl.choczaj.spring.mobilerepair.domain.service.DeviceService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerRepository customerRepository;
    private DeviceService deviceService;

    public CustomerController(CustomerRepository customerRepository, DeviceService deviceService) {
        this.customerRepository = customerRepository;
        this.deviceService = deviceService;
    }

    @GetMapping("/edit")
    public String prepareEditForm(Model model, Principal principal) {
        model.addAttribute("customer", customerRepository.findByEmail(principal.getName()));
        return "customer/edit-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "customer/edit-form";
        }
        if (customer.getEmail().equals(principal.getName())) {
            Customer customerInDb = customerRepository.findByEmail(principal.getName()).orElse(null);
            if (customerInDb != null) {
                customerInDb.setFirstName(customer.getFirstName());
                customerInDb.setLastName(customer.getLastName());
                customerInDb.setAddress(customer.getAddress());
                customerInDb.setPhone(customer.getPhone());
                customerRepository.save(customerInDb);
                return "redirect:/?edited";
            }
        }
        return "redirect:/?failed";
    }

    @GetMapping("/device-details/{id:[1-9]*[0-9]+}")
    public String prepareDetailedView(@PathVariable Long id, Model model, Principal principal) {
        Customer customer = customerRepository.findByEmail(principal.getName()).orElse(null);
        Device device = deviceService.findDeviceById(id);
        if (device.getOwner().getEmail().equals(customer.getEmail())) {
            model.addAttribute("device", device);
            model.addAttribute("tasks", deviceService.findAllTasksByDeviceId(id));
            return "customer/device-details";
        } else {
            return "redirect:/?wrongDevice";
        }
    }


}
