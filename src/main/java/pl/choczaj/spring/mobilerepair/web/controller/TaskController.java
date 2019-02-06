package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Device;
import pl.choczaj.spring.mobilerepair.domain.model.Task;
import pl.choczaj.spring.mobilerepair.domain.model.TaskStatus;
import pl.choczaj.spring.mobilerepair.domain.service.EmployeeService;
import pl.choczaj.spring.mobilerepair.domain.service.TaskService;
import pl.choczaj.spring.mobilerepair.email.EmailSender;
import pl.choczaj.spring.mobilerepair.web.dto.TaskDto;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;
    private EmployeeService employeeService;
    private final EmailSender emailSender;

    public TaskController(TaskService taskService, EmployeeService employeeService, EmailSender emailSender) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.emailSender = emailSender;
    }

    @GetMapping
    public String prepareList(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/set-status")
    public String setStatus(@RequestParam TaskStatus status, @PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            task.setStatus(status);
        }
        if (status.equals(TaskStatus.REPAIRED)) {
            emailSender.sendEmail("programmingTestReceive@gmail.com",
                    "Mobile Repair - Urządzenie gotowe do odbioru", prepareReadyForPickUpEmail(task));
        }
        taskService.save(task);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String prepareForm(Model model, @Valid Device device) {
        model.addAttribute("taskDto", taskService.prepareNewDto(device));
        model.addAttribute("employees", employeeService.findAvailableEmployees());
        return "tasks/form";
    }

    @PostMapping({"/new"})
    public String save(@ModelAttribute("taskDto") @Valid TaskDto taskDto, BindingResult result) {
        if (result.hasErrors()) {
            return "tasks/form";
        }
        taskService.save(taskDto);
        return "redirect:/devices/" + taskDto.getDevice().getId() + "/details";
    }

    private String prepareReadyForPickUpEmail(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dzień dobry <b>").append(task.getDevice().getOwner().getFirstName()).append(" ")
                .append(task.getDevice().getOwner().getLastName()).append("</b>,<br><br>")
                .append("Twoje urządzenie: <b>").append(task.getDevice().getManufacturer()).append(" ")
                .append(task.getDevice().getModel()).append(" jest gotowe do odbioru</b><br>")
                .append("Pozdrawiamy :)");
        return sb.toString();
    }


}
