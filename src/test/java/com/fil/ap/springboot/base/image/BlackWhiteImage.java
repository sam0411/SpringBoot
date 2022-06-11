package com.fil.ap.base.image;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.awt.image.LookupOp;

import javax.imageio.ImageIO;

public class BlackWhiteImage {
	
	private static BufferedImage doBinaryImage(BufferedImage bi) {

		byte[] threshold = new byte[256];
		for (int i = 0; i < 256; i++) {
			threshold[i] = (i < 128) ? (byte) 0 : (byte) 255;
		}
		BufferedImageOp thresholdOp = new LookupOp(new ByteLookupTable(0, threshold), null);
		return thresholdOp.filter(null, bi);
	}

	private static void toBlackWhite() {
		
		try {
            BufferedImage original = ImageIO.read(new File("C:\\Users\\sam04\\original.jpg"));
            
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            
            ColorConvertOp op = new ColorConvertOp(cs, null);
            
            BufferedImage target = op.filter(original, null);
            
            ImageIO.write(target, "jpg", new File("C:\\Users\\sam04\\original_target.jpg"));
            
            //--------brighten
            BufferedImage target1 = doBinaryImage(target);
            
            ImageIO.write(target1, "jpg", new File("C:\\Users\\sam04\\original_target1.jpg"));
        }  catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		
		toBlackWhite();
	}
}
