package ru.kata.spring.boot_security.demo.controller;

import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String printHello(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");

        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model){
        model.addAttribute("users",  userService.getAllUsers());
        return "users_list";
    }

    @GetMapping(value = "/user_add")
    public String addUserForm(User user){
        return "user_add";
    }
    @PostMapping(value = "/user_add")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "user_delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping(value = "/user_update/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("users", userService.getUserById(id));
        return "user_update";
    }
    @PostMapping(value = "/user_update")
    public String editUser(User user){
        userService.addUser(user);
        return "redirect:/users";
    }



    @GetMapping("/admin")
    public String pageAdmin() {
        return "admin";
    }


}
