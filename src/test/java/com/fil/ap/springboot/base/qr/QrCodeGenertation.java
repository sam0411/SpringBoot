package com.fil.ap.springboot.base.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QrCodeGenertation {

    private static final Logger LOGGER = LoggerFactory.getLogger(QrCodeGenertation.class);

    private static void generateQr4Url() {

        String url = "https://www.fidelity.com.hk/investor/";

        int width = 256;
        int height = 256;
        String format = "png";

        Map<EncodeHintType, Object> hints = new HashMap<>();

        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    url,
                    BarcodeFormat.QR_CODE,
                    width,
                    height,
                    hints
            );

            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            ImageIO.write(image, format, out);

            ImageIO.write(image, "png", new File("C:\\Users\\sam04\\qr_target.jpg"));

        } catch (IOException | WriterException e) {

            LOGGER.error("Failed to generate QR Code", e);
        }
    }

    public static void main(String[] args) {

        generateQr4Url();
    }
}
