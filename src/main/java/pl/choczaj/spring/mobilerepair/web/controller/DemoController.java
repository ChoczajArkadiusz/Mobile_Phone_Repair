package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.choczaj.spring.mobilerepair.domain.service.*;

@Controller
@RequestMapping("/demo")
public class DemoController {

    private EmployeeService employeeService;
    private CustomerService customerService;
    private PartService partService;
    private DeviceService deviceService;
    private TaskService taskService;

    public DemoController(EmployeeService employeeService, CustomerService customerService, PartService partService,
                          DeviceService deviceService, TaskService taskService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.partService = partService;
        this.deviceService = deviceService;
        this.taskService = taskService;
    }

    @GetMapping("/confirm-restore")
    public String confirmResore() {
        return "demo-restore";
    }

    @GetMapping("/restore")
    public String initDb() {
        taskService.deleteAll();
        deviceService.deleteAll();
        customerService.deleteAll();
        employeeService.deleteAll();
        partService.deleteAll();
        employeeService.initWithDemoEmployees();
        employeeService.initWithDemoManagers();
        customerService.initWithDemoCusomers();
        partService.initWithDemoParts();
        deviceService.initWithDemoDevices();
        taskService.initWithDemoTasks();
        return "redirect:/login";
    }


}
