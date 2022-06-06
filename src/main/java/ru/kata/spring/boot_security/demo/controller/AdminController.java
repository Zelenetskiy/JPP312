package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping(value = "/users_list")
    public String getAllUsers(Model model){
        model.addAttribute("users_list",  userService.getAllUsers());
        return "users_list";
    }

    @GetMapping(value = "/user_add")
    public String addUserForm(Model model, User user){
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "user_add";
    }
    @PostMapping(value = "/user_add")
    public String addUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin/users_list";
    }

    @GetMapping(value = "user_delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.removeUser(id);
        return "redirect:/admin/users_list";
    }

    @GetMapping(value = "/user_update/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("users", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "user_update";
    }
    @PostMapping(value = "/user_update")
    public String editUser(User user){
        userService.addUser(user);
        return "redirect:/admin/users_list";
    }


    @GetMapping()
    public String pageAdmin() {
        return "admin";
    }

}
