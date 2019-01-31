package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Device;
import pl.choczaj.spring.mobilerepair.domain.model.Task;
import pl.choczaj.spring.mobilerepair.domain.service.TaskService;
import pl.choczaj.spring.mobilerepair.web.dto.TaskDto;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public String prepareList(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }


    @PostMapping("/add")
    public String prepareForm(Model model, @Valid Device device) {
        model.addAttribute("taskDto", taskService.prepareNewDto(device));
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


}
