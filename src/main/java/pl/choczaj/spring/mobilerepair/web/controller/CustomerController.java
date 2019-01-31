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
import pl.choczaj.spring.mobilerepair.email.EmailSender;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    private DeviceRepository deviceRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private final EmailSender emailSender;

    public CustomerController(CustomerRepository customerRepository, DeviceRepository deviceRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, EmailSender emailSender) {
        this.customerRepository = customerRepository;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Witaj <b>").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("</b>,<br><br>");
        sb.append("Utworzyliśmy dla Ciebie konto w serwisie: ").append("MOBILE_REPAIR").append("<br>");
        sb.append("Status swoich zleceń możesz sprawdzić tutaj: ").append("<a href='http://localhost:5000/'>Link</a>").append("<br>");
        sb.append("Twoje hasło: ").append(pass).append("<br><br>");
        sb.append("Pozdrawiamy :)");
        emailSender.sendEmail("programmingTestReceive@gmail.com",
                "Mobile Repair - Utworzono użytkownika " + customer.getEmail(), sb.toString());
        return "redirect:/customers/?added";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/edit-form";
        }
        customer.setPassword(customer.getPassword());
        customer.setEnabled(true);
        customerRepository.save(customer);
        return "redirect:/customers/?edited";
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
            customer.getDevices().stream().forEach(d -> deviceRepository.delete(d));
            customerRepository.delete(customer);
        }
        return "redirect:/customers";
    }


}
