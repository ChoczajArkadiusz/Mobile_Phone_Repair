package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.service.EmployeeService;
import pl.choczaj.spring.mobilerepair.web.dto.EmployeeDto;
import pl.choczaj.spring.mobilerepair.web.validation.ValidGroupEmployeeAdd;

import javax.validation.groups.Default;

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
    public String save(@ModelAttribute("employeeDto") @Validated({ValidGroupEmployeeAdd.class, Default.class}) EmployeeDto employeeDto, BindingResult result) {
        if (result.hasErrors()) {
            return "employees/form-dto";
        }
        if (!employeeDto.getPassword().equals(employeeDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Hasła muszą być zgodne");
            return "employees/form-dto";
        }
        employeeService.save(employeeDto, true);
        return "redirect:/employees?added";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("employeeDto") @Validated({Default.class}) EmployeeDto employeeDto, BindingResult result) {
        if (result.hasErrors()) {
            return "employees/edit-form-dto";
        }
        employeeService.save(employeeDto, false);
        return "redirect:/employees?edited";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/edit")
    public String prepareEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employeeDto", employeeService.findById(id));
        return "employees/edit-form-dto";
    }


}
