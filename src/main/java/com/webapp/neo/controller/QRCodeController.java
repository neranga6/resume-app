package com.webapp.neo.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;



import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Controller
public class QRCodeController {

    @GetMapping("/qrcode")
    public void generateQrCode(HttpServletResponse response) throws IOException, com.google.zxing.WriterException {

        String link = "/login";

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitMatrix bitMatrix = new QRCodeWriter().encode(link, BarcodeFormat.QR_CODE, 200, 200,
                java.util.Collections.singletonMap(EncodeHintType.MARGIN, 0));
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        byte[] qrCodeBytes = outputStream.toByteArray();
        response.getOutputStream().write(qrCodeBytes);
        response.getOutputStream().flush();
    }



}