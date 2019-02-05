package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Customer;
import pl.choczaj.spring.mobilerepair.domain.model.TaskStatus;
import pl.choczaj.spring.mobilerepair.domain.model.User;
import pl.choczaj.spring.mobilerepair.domain.model.UserRoleEnum;
import pl.choczaj.spring.mobilerepair.domain.repository.CustomerRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.DeviceRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;
import pl.choczaj.spring.mobilerepair.domain.service.EmployeeService;
import pl.choczaj.spring.mobilerepair.domain.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private UserService userService;
    private EmployeeService employeeService;
    private CustomerRepository customerRepository;
    private DeviceRepository deviceRepository;
    private TaskRepository taskRepository;

    public HomeController(UserService userService, EmployeeService employeeService, CustomerRepository customerRepository, DeviceRepository deviceRepository, TaskRepository taskRepository) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String test(Principal principal, Model model) {
        if (principal != null) {
            String loggedUserEmail = principal.getName();
            model.addAttribute("loggedUser", loggedUserEmail);
            User loggedUser = userService.findByEmail(loggedUserEmail);

            if (loggedUser.hasRole(UserRoleEnum.ROLE_MANAGER)) {
                model.addAttribute("tasks", taskRepository.findAllByStatusNotIn(
                        TaskStatus.REPAIRED, TaskStatus.CANCELED, TaskStatus.DELIVERED));
                return "manager/home";
            } else if (loggedUser.hasRole(UserRoleEnum.ROLE_EMPLOYEE)) {
                model.addAttribute("tasks", employeeService.findAllTasksByEmployeeEmail(principal.getName()));
                return "employees/home";
            } else if (loggedUser.hasRole(UserRoleEnum.ROLE_CUSTOMER)) {
                Customer customer = customerRepository.findByEmail(principal.getName()).orElse(null);
                model.addAttribute("customer", customer);
                model.addAttribute("devices", deviceRepository.findAllByOwnerId(customer.getId()));
                return "customer/home";
            } else {
                return "home";
            }
        } else {
            model.addAttribute("loggedUser", "");
            return "redirect:/login";
        }

    }


}
