package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Device;
import pl.choczaj.spring.mobilerepair.domain.service.DeviceService;
import pl.choczaj.spring.mobilerepair.web.dto.DeviceDto;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public String prepareList(Model model) {
        List<Device> devices = deviceService.findAll();
        model.addAttribute("devices", devices);
        return "devices/all";
    }

    @GetMapping("/add")
    public String prepareForm(Model model) {
        model.addAttribute("deviceDto", deviceService.prepareNewDto());
        return "devices/form";
    }

    @PostMapping({"/add", "/edit"})
    public String save(@ModelAttribute("deviceDto") @Valid DeviceDto deviceDto, BindingResult result) {
        if (result.hasErrors()) {
            return "devices/form";
        }
        deviceService.save(deviceDto);
        return "redirect:/devices?modified";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/details")
    public String prepareDetailedView(@PathVariable Long id, Model model) {
        model.addAttribute("device", deviceService.findDeviceById(id));
        model.addAttribute("tasks", deviceService.findAllTasksByDeviceId(id));
        return "devices/details";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/edit")
    public String prepareEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("deviceDto", deviceService.findById(id));
        return "devices/form";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/confirm-delete")
    public String confirmDelete(@PathVariable Long id, Model model) {
        DeviceDto deviceDto = deviceService.findById(id);
        if (deviceDto == null) {
            return "redirect:/devices/";
        }
        model.addAttribute("toRemove", deviceDto);
        return "devices/confirm-delete";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/delete")
    public String delete(@PathVariable Long id) {
        DeviceDto deviceDto = deviceService.findById(id);
        if (deviceDto != null) {
            deviceService.delete(deviceDto);
        }
        return "redirect:/devices";
    }


}
