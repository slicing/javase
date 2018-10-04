package Brid;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ground {
	BufferedImage image;
	int x,y;
	int width;
	int height;
	public Ground() throws IOException {
		image = ImageIO.read(getClass().getResource("ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = 500;
	}

	public void step(){
		x--;
		if(x == -109){
			x = 0;
		}
	}
}
