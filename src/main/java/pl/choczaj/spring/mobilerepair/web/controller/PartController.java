package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.choczaj.spring.mobilerepair.domain.model.Part;
import pl.choczaj.spring.mobilerepair.domain.repository.PartRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartController {

    PartRepository partRepository;

    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @GetMapping
    public String prepareList(Model model) {
        List<Part> parts = partRepository.findAll();
        model.addAttribute("parts", parts);
        return "parts/all";
    }

    @GetMapping("/add")
    public String prepareAddForm(Model model) {
        model.addAttribute("part", new Part());
        return "parts/add-edit-form";
    }

    @PostMapping({"/add", "/edit"})
    public String save(@ModelAttribute("part") @Valid Part part, BindingResult result) {
        if (result.hasErrors()) {
            return "parts/add-edit-form";
        }
        partRepository.save(part);
        return "redirect:/parts/";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/edit")
    public String prepareEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("part", partRepository.findById(id));
        return "parts/add-edit-form";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/confirm-delete")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Part part = partRepository.findById(id).orElse(null);
        if (part == null) {
            return "redirect:/parts/";
        }
        model.addAttribute("toRemove", part);
        return "parts/confirm-delete";
    }

    @GetMapping("/{id:[1-9]*[0-9]+}/delete")
    public String delete(@PathVariable Long id) {
        Part part = partRepository.findById(id).orElse(null);
        if (part != null) {
            partRepository.delete(part);
        }
        return "redirect:/parts";
    }


}
