package com.webapp.neo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;


@Service
public class ResumeEmailService {

    private static final Logger logger = LoggerFactory.getLogger(ResumeEmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmailWithAttachment(String toEmail, String subject, String body, String pdfFile) throws Exception {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            try {
                // Set the recipient, subject, and body of the email
                helper.setTo(toEmail);
                helper.setSubject(subject);
                helper.setText(body, true); // Use true for HTML content

                // Attach the PDF file
                ClassPathResource resource = new ClassPathResource(pdfFile);
                if (resource.exists()) {
                    InputStream inputStream = resource.getInputStream();
                    byte[] pdfBytes = new byte[inputStream.available()];
                    inputStream.read(pdfBytes);

                    ByteArrayResource byteArrayResource = new ByteArrayResource(pdfBytes) {
                        @Override
                        public String getFilename() {

                           return "Neo_Urapola1.pdf";
                        }
                    };

                    helper.addAttachment(byteArrayResource.getFilename(), byteArrayResource);

                } else {
                    throw new Exception("PDF file not found: " + pdfFile);
                }
                javaMailSender.send(mimeMessage);
            } catch (IOException e) {
                throw new Exception("Error attaching PDF file: " + e.getMessage(), e);
            }
        }

}