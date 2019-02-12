package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Customer;
import pl.choczaj.spring.mobilerepair.domain.model.UserRole;
import pl.choczaj.spring.mobilerepair.domain.model.UserRoleEnum;
import pl.choczaj.spring.mobilerepair.domain.repository.CustomerRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.DeviceRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.UserRoleRepository;
import pl.choczaj.spring.mobilerepair.domain.service.CustomerService;
import pl.choczaj.spring.mobilerepair.email.EmailSender;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomersController {

    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private DeviceRepository deviceRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private final EmailSender emailSender;

    public CustomersController(CustomerRepository customerRepository, CustomerService customerService, DeviceRepository deviceRepository,
                               PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, EmailSender emailSender) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.deviceRepository = deviceRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.emailSender = emailSender;
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
        return "customers/add-form";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/add-form";
        }
        String pass = customer.getPassword();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setEnabled(true);
        UserRole userRole = new UserRole();
        userRole.setUser(customer);
        userRole.setRole(UserRoleEnum.ROLE_CUSTOMER);
        customerRepository.save(customer);
        userRoleRepository.save(userRole);
        emailSender.sendEmail("programmingTestReceive@gmail.com",
                "Mobile Repair - Utworzono użytkownika " + customer.getEmail(), prepareWelcomeEmail(customer, pass));
        return "redirect:/customers/?added";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/edit-form";
        }
        Customer customerInDb = customerRepository.findById(customer.getId()).orElse(null);
        if (customerInDb != null) {
            customer.setPassword(customerInDb.getPassword());
            customer.setEnabled(true);
            customerRepository.save(customer);
            return "redirect:/customers/?edited";
        }
        return "customers/edit-form";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/edit")
    public String prepareEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id));
        return "customers/edit-form";
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
            customerService.delete(customer);
        }
        return "redirect:/customers";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/confirm-anonymize")
    public String confirmAnonymize(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return "redirect:/customers/";
        }
        model.addAttribute("toAnonymize", customer);
        return "customers/confirm-anonymize";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/anonymize")
    public String anonymize(@PathVariable Long id) {
        boolean success = customerService.anonymize(id);
        if (success) {
            return "redirect:/customers/?edited";
        }
        return "redirect:/customers/?failed";
    }

    private String prepareWelcomeEmail(@ModelAttribute("customer") @Valid Customer customer, String pass) {
        StringBuilder sb = new StringBuilder();
        sb.append("Witaj <b>").append(customer.getFirstName()).append(" ")
                .append(customer.getLastName()).append("</b>,<br><br>")
                .append("Utworzyliśmy dla Ciebie konto w serwisie: ").append("MOBILE_REPAIR").append("<br>")
                .append("Status swoich zleceń możesz sprawdzić tutaj: ")
                .append("<a href='http://localhost:5000/'>Link</a>").append("<br>")
                .append("Twoje hasło: ").append(pass).append("<br><br>")
                .append("Pozdrawiamy :)");
        return sb.toString();
    }

}
