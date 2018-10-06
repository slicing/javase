package Slow.slicing.shoot;
//敌飞机
public class Airplane extends FlyingObject implements Enemy {
	private int speed = 2;//移动速度
	public Airplane(){
		this.image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		x = (int)(Math.random()*(ShootGame.WIDTH - width));
		//x = 100;
		//y = 100;
	}
	@Override
	public int getScore() {
		return 5;
	}
	@Override
	public void step(){
		y += speed;
	}
	@Override
	public boolean outofBounds(){
		return y>ShootGame.HEIGHT;
	}


}
