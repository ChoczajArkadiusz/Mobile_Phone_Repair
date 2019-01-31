package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.service.EmployeeService;
import pl.choczaj.spring.mobilerepair.web.dto.EmployeeDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees/form")
public class EmployeeFormController {

    private EmployeeService employeeService;

    public EmployeeFormController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String prepareForm(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        return "employees/form-dto";
    }

    @PostMapping
    public String save(@ModelAttribute("employeeDto") @Valid EmployeeDto employeeDto, BindingResult result) {
        if (result.hasErrors()) {
            return "employees/all";
        }
        employeeService.save(employeeDto);
        return "redirect:/";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/edit")
    public String prepareEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employeeDto", employeeService.findById(id));
        return "employees/form-dto";
    }




}
