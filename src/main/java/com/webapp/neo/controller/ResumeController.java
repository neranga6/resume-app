package com.webapp.neo.controller;

import com.webapp.neo.model.ResumeForm;
import com.webapp.neo.services.ResumeEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ResumeController {

    @Autowired
    ResumeEmailService resumeEmailService;


    private static final String WORD_FOLDER = "words";

    private static final String PDF_FOLDER = "words/";

    @RequestMapping(value = "/resume-page")
    public ModelAndView showResumeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resume");
        return modelAndView;
    }

    @PostMapping("/resume-form")
    public RedirectView sendResumeForm(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email, Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resume");
        model.addAttribute("resumeFrom", new ResumeForm());


        String  path = WORD_FOLDER + "/" +"Neo_Urapola1.pdf";

        String to = email;
        String subject1 = "Resume-Email";
        String content =  "\n" + "Neranga Urapola's" +"\n" + "Resume Sent to" +"\n" + "Name: " + firstName +"\n"+ lastName + "\n";
        resumeEmailService.sendEmailWithAttachment(to, subject1, content, path);
        return new RedirectView("/success");
    }

    @GetMapping("/download/resume-form")
    public ResponseEntity<Resource> downloadResume() throws IOException {
        Path path = Paths.get(PDF_FOLDER + "Neo_Urapola1.pdf");
        ClassPathResource resource = new ClassPathResource(path.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Neo_Urapola1.pdf\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

}
