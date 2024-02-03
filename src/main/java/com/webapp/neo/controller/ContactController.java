package com.webapp.neo.controller;

import com.webapp.neo.model.ContactForm;
import com.webapp.neo.model.ResumeForm;
import com.webapp.neo.services.EmailService;
import com.webapp.neo.services.ResumeEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ContactController {
    private final EmailService emailService;




    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;

    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "/contact";
    }

    @PostMapping("/contact")
    public RedirectView submitContactForm(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("message1") String message,
            @RequestParam("subject") String subject) {

        String to = "neranga65@gmail.com";
        String subject1 = "subject:" + subject;
        String content = "Name: " + firstName + lastName + "\n"
                + "Email: " + email + "\n"
                + "Message: " + message;
        emailService.sendEmail(to, subject1, content);
        return new RedirectView("/success");
    }

    @GetMapping("/success")
    public ModelAndView successView(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }


}