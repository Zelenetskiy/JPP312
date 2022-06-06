package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;
import java.security.Principal;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @GetMapping(value = "/")
//    public String printHello(ModelMap model) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Hello!");
//
//        model.addAttribute("messages", messages);
//        return "index";
//    }

    @GetMapping()
    public String pageUser(Principal principal, ModelMap model){
        model.addAttribute("user", userDetailsService.findByUsername(principal.getName()));
        return "user";
    }

}
