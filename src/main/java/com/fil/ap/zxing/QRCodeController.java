package com.fil.ap.zxing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@RestController
@Profile({ "uat" })
public class QRCodeController {

	@RequestMapping(value = "/urlqrcode", method = RequestMethod.GET)
	public ResponseEntity<byte[]> urlQRCode(
			@RequestParam(required = true)String id
		)  throws Exception {  
		
		String url = "https://www.fidelity.com.hk/investor/";
		
		int width = 256;
		int height = 256;
		String format = "png";
		
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		
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
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.CREATED);
	}
}
