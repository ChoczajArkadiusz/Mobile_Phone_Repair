package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Employee;
import pl.choczaj.spring.mobilerepair.domain.model.UserRoleEnum;
import pl.choczaj.spring.mobilerepair.domain.repository.EmployeeRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;
import pl.choczaj.spring.mobilerepair.domain.service.EmployeeService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;

    private EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    @ModelAttribute("appRoles")
    public List<UserRoleEnum> prepareRoles() {
        return Arrays.asList(UserRoleEnum.values());
    }

    @GetMapping
    public String prepareList(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees/all";
    }

    @GetMapping("/add")
    public String prepareAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add-edit-form";
    }

    @PostMapping({"/add", "/edit"})
    public String save(@ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employees/add-edit-form";
        }
        employeeRepository.save(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/details")
    public String prepareDetailedView(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeRepository.findById(id).orElse(null));
//        model.addAttribute("tasks", employeeService.findAllTasksByEmployeeId(id));
        return "employees/details";
    }


    @GetMapping("/{id:[1-9]*[0-9]+}/edit")
    public String prepareEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeRepository.findById(id));
        return "employees/add-edit-form";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/confirm-delete")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return "redirect:/employees/";
        }
        model.addAttribute("toRemove", employee);
        return "employees/confirm-delete";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/delete")
    public String delete(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
        return "redirect:/employees";
    }


}
