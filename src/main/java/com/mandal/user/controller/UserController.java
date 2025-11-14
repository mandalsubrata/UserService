package com.mandal.user.controller;


import com.mandal.user.entity.User;
import com.mandal.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String getUser(Model model, @RequestParam(required = false) Long id) {
        User user = userService.getUser(id);
        System.out.println("user-->>"+user);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        System.out.println("user-->>"+ Arrays.toString(users.toArray()));
        model.addAttribute("user", users);
        return "user";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);

        System.out.println("Registered User : " + user);
        User userq= userService.saveUser(user);
        return "user";  // show user details on user.html
    }
}
