package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String test(Principal principal, Model model) {
        if (principal != null)
            model.addAttribute("loggedUser", principal.getName());
        else
            model.addAttribute("loggedUser", "");

        return "home";
    }
}
