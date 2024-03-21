package com.spark.lms.controller;

import com.spark.lms.model.User;
import com.spark.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.spark.lms.common.Constants;


@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Perform any additional validation if needed

        // Set default role for new users (adjust according to your application)
        user.setRole(Constants.ROLE_USER);

        // Save the user to the database
        userService.addNew(user);

        // Redirect to the login page after successful registration
        return "redirect:/login?registrationSuccess=true";
    }
}
