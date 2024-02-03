package com.webapp.neo.services;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
@Service
public class PDFService {
    public void pdfViewer(Path path, HttpServletResponse response) throws IOException {
        ClassPathResource resource = new ClassPathResource(path.toString());
        InputStream inputStream = resource.getInputStream();
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        // Set the response headers for inline display
        response.setHeader("Content-Disposition", "inline; filename=file.pdf");
        response.setHeader("Cache-Control", "no-cache");

        // Copy the PDF file's input stream to the response's output stream
        IOUtils.copy(inputStream, response.getOutputStream());

        inputStream.close();
    }
}
