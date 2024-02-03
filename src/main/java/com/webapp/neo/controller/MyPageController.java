package com.webapp.neo.controller;
//
//import com.webapp.neo.service.LoggerInterceptorService;
import com.webapp.neo.model.IPDetails;
import com.webapp.neo.model.ResumeForm;
import com.webapp.neo.repositories.IPRepository;
import com.webapp.neo.services.IPDetailsService;
import com.webapp.neo.services.ResumeEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MyPageController {

    @Autowired
    IPDetailsService IPDetailsService;

    @Autowired
    IPRepository iprepository;


    @RequestMapping("/")
    public ModelAndView home(HttpServletRequest request, Model model) {
        String ip =  IPDetailsService.ipGrab(request);
        ModelAndView modelAndView = new ModelAndView();
        IPDetails myIP = new IPDetails();
        myIP.setIP(ip);
        //iprepository.save(myIP);
        model.addAttribute("IPData",myIP);
        modelAndView.setViewName("index");

        return modelAndView;
    }


    @GetMapping("/service")
    public ModelAndView uploadResume()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }


}
