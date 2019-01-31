package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Customer;
import pl.choczaj.spring.mobilerepair.domain.model.UserRoleEnum;
import pl.choczaj.spring.mobilerepair.domain.repository.CustomerRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.DeviceRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    private DeviceRepository deviceRepository;

    public CustomerController(CustomerRepository customerRepository, DeviceRepository deviceRepository) {
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
    }

    @ModelAttribute("appRoles")
    public List<UserRoleEnum> prepareRoles() {
        return Arrays.asList(UserRoleEnum.values());
    }

    @GetMapping
    public String prepareList(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customers/all";
    }

    @GetMapping("/add")
    public String prepareAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/add-edit-form";
    }

    @PostMapping({"/add", "/edit"})
    public String save(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/add-edit-form";
        }
        customerRepository.save(customer);
        return "redirect:/customers/";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/edit")
    public String prepareEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id));
        return "customers/add-edit-form";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/confirm-delete")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return "redirect:/customers/";
        }
        model.addAttribute("toRemove", customer);
        return "customers/confirm-delete";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/delete")
    public String delete(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.getDevices().stream().forEach(d -> deviceRepository.delete(d));
            customerRepository.delete(customer);
        }
        return "redirect:/customers";
    }


}
