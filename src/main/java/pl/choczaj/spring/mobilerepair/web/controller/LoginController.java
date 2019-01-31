package pl.choczaj.spring.mobilerepair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String prepareList() {
        return "login-form";
    }


}
