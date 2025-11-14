package com.mandal.user.controller;


import com.mandal.user.entity.User;
import com.mandal.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String getUser(Model model, @RequestParam(required = false) Long id) {
        User user = userService.getUser(id);
        System.out.println("user-->>" + user);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }


    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println("Deleting user with ID: " + id);
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        User newUser = userService.saveUser(user);
        System.out.println("New registered User : " + newUser);

        return "user";  // show user details on user.html
    }
}
