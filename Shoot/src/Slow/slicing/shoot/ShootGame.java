package Slow.slicing.shoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ShootGame extends JPanel {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 654;
	private java.util.Timer timer;//定时器。。swing也有Timer
	private int intervel = 1000 / 100;//时间间隔
	private int score = 0;
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	private FlyingObject[] flyings = {};
	private Bullet[] bullets = {};
	private  Hero hero = new Hero();
	private int state;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	public ShootGame(){
		//flyings = new FlyingObject[2];
		//flyings[0] = new Airplane();
		//flyings[1] = new Bee();

		//bullets = new Bullet[1];
		//bullets[0] = new Bullet(200,350);
	}

	static {
		try {
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		paintHero(g);//画英雄机
		paintBullets(g);//画子弹
		paintFlyingObjects(g);//画飞行物
		paintScore(g);
		paintState(g);
	}

	private void paintFlyingObjects(Graphics g) {
		for(int i =0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			g.drawImage(f.getImage(),f.getX(),f.getY(),null);
		}
	}

	private void paintBullets(Graphics g){
		for(int i = 0;i<bullets.length;i++){
			Bullet b = bullets[i];
			g.drawImage(b.getImage(),b.getX(),b.getY(),null);
		}
	}

	private void paintHero(Graphics g) {
		g.drawImage(hero.getImage(),hero.getX(),hero.getY(),null);
	}
	/*
	* 随机生成飞行物
	* */
	public static FlyingObject nextOne(){
		Random random = new Random();
		int type = random.nextInt(20);
		if(type == 0){
			return new Bee();
		}
		else{
			return new Airplane();
		}
	}
	int flyEnteredIndex = 0;
	public void enterAction(){
		flyEnteredIndex ++;
		if(flyEnteredIndex % 40 == 0){
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length +1);//扩容
			flyings[flyings.length - 1] = obj;//放到最后一位
		}
	}

	public void stepAction(){
		/* 飞行物走了一步*/
		for(int i = 0;i <flyings.length;i++){
			FlyingObject f = flyings[i];
			f.step();
		}
		for(int i =0;i<bullets.length;i++){
			Bullet b = bullets[i];
			b.step();
		}
		hero.step();
	}

	public void action(){
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {//鼠标移动
				if(state == RUNNING){
					int x = e.getX();
					int y =e.getY();
					hero.moveTo(x,y);
				}

			}
			@Override
			public void mouseEntered(MouseEvent e){//鼠标进入
				if(state == PAUSE){
					state = RUNNING;
				}
			}
			@Override
			public void mouseExited(MouseEvent e){//鼠标退出
				if(state!=GAME_OVER){
					state = PAUSE;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e){//鼠标点击
				switch (state){
					case START:
						state = RUNNING;
						break;
					case GAME_OVER:
						flyings = new FlyingObject[0];
						bullets = new Bullet[0];
						hero = new Hero();
						score = 0;
						state = START;
						break;
				}
			}
		};
		this.addMouseListener(l);//处理鼠标点击操作
		this.addMouseMotionListener(l);//处理鼠标滑动操作
		timer = new Timer();//主流程控制
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				if(state == RUNNING){
					enterAction();//飞行物入场
					stepAction();//走一步
					shootAction();//射击
					bangAction();
					outofBoundsAction();//删除越界飞行物及子弹
					checkGameOverAction();//检查游戏结束
				}
				repaint();
			}
		},intervel,intervel);
	}

	int shootIndex = 0;
	public void shootAction(){
		shootIndex ++;
		if(shootIndex % 30 == 0){
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets,bullets.length + bs.length);//扩容
			System.arraycopy(bs,0,bullets,bullets.length - bs.length,bs.length);//追加数组
		}
	}
	//子弹与飞行物碰撞检测
	public void bangAction(){
		for(int i = 0;i <bullets.length;i++){
			Bullet b = bullets[i];
			bang(b);
		}
	}
//子弹与飞行物之间的碰撞检查
	private void bang(Bullet b) {
		int index = -1;//击中的飞行物索引
		for(int  i =0;i<flyings.length;i++){
			FlyingObject obj = flyings[i];
			if(obj .shootBy(b)){//判断是否击中
				index = i;//记录被击中的飞行物索引
				break;
			}
		}
		if(index != -1){//有击中的飞行物
			FlyingObject one = flyings[index];//记录被击中的飞行物
			FlyingObject temp = flyings[index];//被击中的飞行物与最后一个飞行物交换
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;
			flyings = Arrays.copyOf(flyings,flyings.length -1);//删除最后一个飞行物

			if(one instanceof  Enemy){   //设置检查类型 是敌人
				Enemy e = (Enemy) one;
				score += e.getScore();//加分
			}
			if(one instanceof Award){//奖励
				Award a = (Award) one;
				int type = a.getType();//奖励类型
				switch (type){
					case Award.DOUBLE_FIRE://双倍火力
						hero.addDoubleFire();
						break;
					case Award.LIFE://加命
						hero.addLife();
						break;
				}
			}
		}
	}

	//画分数和命数
	public void paintScore(Graphics g){
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF,Font.BOLD,14);
		g.setColor(new Color(0x3A3B3B));
		g.setFont(font);
		g.drawString("SCORE:" + score,x,y);
		y += 20;
		g.drawString("LIFE:" + hero.getLife(),x,y);
	}
	//删除越界飞行物
	public void outofBoundsAction(){
		int index = 0;
		//存储活着的飞行物
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i = 0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outofBounds()){
				flyingLives[index++] = f;
			}
		}
		flyings = Arrays.copyOf(flyingLives,index);

		index = 0;
		Bullet[] bulletLives = new Bullet[bullets.length];
		for(int i = 0;i<bullets.length;i++){
			Bullet b = bullets[i];
			if(!b.outofBounds()){
				bulletLives[index ++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives,index);
	}
	public void checkGameOverAction(){
		if(isGameOver()){
			state = GAME_OVER;
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i<flyings.length;i++){
			int index = -1;
			FlyingObject obj = flyings[i];
			if(hero.hit(obj)){
				hero.subtractLife();
				hero.setDoubleFire(0);
				index = i;
			}
			if(index != -1){
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
		}
		return hero.getLife() <= 0;
	}
	public void paintState(Graphics g){
		switch (state){
			case START:
				g.drawImage(start,0,0,null);
				break;
			case PAUSE:
				g.drawImage(pause,0,0,null);
				break;
			case GAME_OVER:
				g.drawImage(gameover,0,0,null);
				break;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Fly");
		ShootGame game = new ShootGame();
		frame.add(game);
		frame.setSize(WIDTH,HEIGHT);
		frame.setAlwaysOnTop(true);//总在最上
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);//尽快调用
		game.action();
	}
}
