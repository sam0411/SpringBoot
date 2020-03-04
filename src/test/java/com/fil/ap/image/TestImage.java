package com.fil.ap.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.LookupOp;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestImage {
	
	public static BufferedImage doBinaryImage(BufferedImage bi) {

		byte[] threshold = new byte[256];
		for (int i = 0; i < 256; i++) {
			threshold[i] = (i < 128) ? (byte) 0 : (byte) 255;
		}
		BufferedImageOp thresholdOp = new LookupOp(new ByteLookupTable(0, threshold), null);
		return thresholdOp.filter(null, bi);
	}

	@Test
	public void toBlackWhite() {
		
		try {
            BufferedImage original = ImageIO.read(new File("C:\\original.jpg"));
            
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            
            ColorConvertOp op = new ColorConvertOp(cs, null);
            
            BufferedImage target = op.filter(original, null);
            
            ImageIO.write(target, "jpg", new File("C:\\original_target.jpg"));
            
            //--------brighten
            BufferedImage target1 = TestImage.doBinaryImage(target);
            
            ImageIO.write(target1, "jpg", new File("C:\\original_target1.jpg"));
        }  catch (Exception e) {
            e.printStackTrace();
        }
	}
}
