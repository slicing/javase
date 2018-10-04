package Brid;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Column {
	BufferedImage image;
	int x,y;//柱子的中心点
	int width,height;
	int gap;//柱子中间缝隙
	int distance;//两个柱子之间的距离
	Random random = new Random();
	public Column(int n) throws IOException {
		image = ImageIO.read(getClass().getResource("column.png"));
		width = image.getWidth();
		height = image.getHeight();
		gap = 144;
		distance = 245;
		x = 550 + (n -1) * distance;
		y = random.nextInt(218) + 132;
	}
	public void step(){
		x--;
		if(x == -width/2){
			x = random.nextInt(218) + 132;
		}
	}
}
