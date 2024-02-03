package com.webapp.neo.controller;


import com.webapp.neo.model.PasswordForm;

import com.webapp.neo.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;


@Controller

public class LoginController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    DataService dataService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/submit-form")
    public String submitLoginForm(Model model, @RequestParam("password") String password) {
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setPassword(password);
        model.addAttribute("PasswordForm",passwordForm);
        dataService.savePassword(passwordForm);
        return "/pdf";
    }





}