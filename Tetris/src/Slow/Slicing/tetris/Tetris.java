package Slow.Slicing.tetris;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends JPanel{
	private  int score;
	private int   lines;
	private Cell[][] wall;
	private Teromino teromino;//正在下落的方块
	private Teromino nextOne;//下一个方块
	private int speed;
	private int level;
	private int index;
	private static BufferedImage background;
	private static BufferedImage gameOver;
	private static BufferedImage pause;
	public static BufferedImage T;
	public static BufferedImage S;
	public static BufferedImage I;
	public static BufferedImage L;
	public static BufferedImage J;
	public static BufferedImage O;
	public static BufferedImage Z;

	private Timer timer;
	public static final int FONT_COLOR = 0x667799;
	public static final int FONT_SIZE = 30;
	public static final int Rows = 20;
	public static final int Cols = 10;

	private int state;
	public static final int RUNNING = 0;
	public static final int PAUSE = 1;
	public static final int GAME_OVER = 2;

	static {
		try {
			background = ImageIO.read(Tetris.class.getResource("tetris.png"));
			gameOver = ImageIO.read(Tetris.class.getResource("game-over.png"));
			pause = ImageIO.read(Tetris.class.getResource("pause.png"));
			T = ImageIO.read(Tetris.class.getResource("T.png"));
			I = ImageIO.read(Tetris.class.getResource("I.png"));
			S = ImageIO.read(Tetris.class.getResource("S.png"));
			Z = ImageIO.read(Tetris.class.getResource("Z.png"));
			J = ImageIO.read(Tetris.class.getResource("J.png"));
			L = ImageIO.read(Tetris.class.getResource("L.png"));
			O = ImageIO.read(Tetris.class.getResource("O.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		g.translate(15,15);
		paintWall(g);
		paintTetromino(g);
		paintNextOne(g);
		paintScore(g);
		paintSate(g);
	}

	private void paintSate(Graphics g) {
		switch (state){
			case PAUSE:
				g.drawImage(pause,-15,-15,null);
				break;
			case GAME_OVER:
				g.drawImage(gameOver,-15,-15,null);
				break;
		}
	}

	public void action(){
		wall = new Cell[Rows][Cols];
		//wall[2][2] = new Cell(2,2,T);
		teromino = Teromino.randomOne();
		nextOne = Teromino.randomOne();
		state = RUNNING;
		KeyAdapter l = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				switch (state){
					case GAME_OVER:
						prcessGameoverKey(key);
						break;
					case PAUSE:
						processPauseKey(key);
						break;
					case RUNNING:
						processRunningKey(key);
				}
				repaint();
			}
		};
		this.requestFocus();
		this.addKeyListener(l);
		timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				speed = 40 - (lines / 100);
				speed = speed <1 ? 1:speed;
				level = 41 - speed;
				if(state == RUNNING && index % speed == 0){
					softDropAction();
				}
				index++;
				repaint();
			}
		},10,10);

	}

	private void processRunningKey(int key) {
		switch (key){
			case KeyEvent.VK_Q:
				System.exit(0);
				break;
			case KeyEvent.VK_RIGHT:
				Tetris.this.moveRightAction();
				break;
			case KeyEvent.VK_LEFT:
				Tetris.this.moveLeftAction();
				break;
			case KeyEvent.VK_DOWN:
				softDropAction();
				break;
			case KeyEvent.VK_SPACE:
				hardDropAction();
				break;
			case KeyEvent.VK_UP:
				rotateRightAction();
				break;
			case KeyEvent.VK_P:
				state = PAUSE;
				break;
		}
	}

	private void processPauseKey(int key) {
		switch (key){
			case KeyEvent.VK_Q:
				System.exit(0);
				break;
			case KeyEvent.VK_C:
				index = 0;
				state = RUNNING;
				break;
		}
	}

	private void prcessGameoverKey(int key) {
		switch (key){
			case KeyEvent.VK_Q:
				System.exit(0);
				break;
			case KeyEvent.VK_S:
				this.lines = 0;
				this.score = 0;
				this.wall = new Cell[Rows][Cols];
				this.teromino = Teromino.randomOne();
				this.nextOne = Teromino.randomOne();
				this.state = RUNNING;
				this.index = 0;
				break;
		}
	}

	private void rotateRightAction() {
		teromino.rotateRight();
		if(outOfBounds() || coincide()){
			teromino.rotateLeft();
		}
	}

	private void hardDropAction() {
		while (canDrop()){
			teromino.softDrop();
		}
		landIntoWall();
		destoryLines();
		if(isGameOver()){
			state = GAME_OVER;
		}else{
			teromino = nextOne;
			nextOne = Teromino.randomOne();
		}
	}

	private boolean outOfBounds(){//是否出界
		Cell[] cells = teromino.cells;
		for(int i = 0;i<cells.length;i++){
			Cell cell = cells[i];
			int col = cell.getCol();
			if(col<0 || col>= Cols){
				return true;
			}
		}
		return false;
	}
	private boolean coincide(){
		Cell[] cells = teromino.cells;
		for(int i = 0;i<cells.length;i++){
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			if(row >= 0 && row < Rows && col >= 0 && col <= Cols && wall[row][col] != null){
				return true;
			}
		}
		return false;
	}
	private void moveLeftAction() {
		teromino.moveLeft();;
		if(outOfBounds() || coincide()){
			teromino.moveRight();
		}
	}

	private void moveRightAction() {
		teromino.moveRight();
		if(outOfBounds() || coincide()){
			teromino.moveLeft();
		}
	}

	public void softDropAction(){
		 if (canDrop()){
			teromino.softDrop();
		}else{
			landIntoWall();
			destoryLines();
			if(isGameOver()){
				state = GAME_OVER;
			}else{
				teromino = nextOne;
				nextOne = Teromino.randomOne();
			}

		}
	}

	private boolean isGameOver() {
		Cell[] cells = nextOne.cells;
		for(Cell cell :cells){
			int row = cell.getRow();
			int col = cell.getCol();
			if(wall[row][col] != null){
				return true;
			}
		}
		return false;
	}

	private static int[] scoreTable = {0,1,10,50,100};
	private void destoryLines() {
		int lines = 0;
		for(int row = 0;row < wall.length;row++){
			if(fullCells(row)){
				deleteRow(row);
				lines++;
			}
		}
		this.score += scoreTable[lines];
		this.lines += lines;
	}

	private void deleteRow(int row) {
		for(int i = row;i>=1;i--){
			System.arraycopy(wall[i-1],0,wall[i],0,Cols);
		}
		Arrays.fill(wall[0],null);
	}

	private boolean fullCells(int row) {
		Cell[] line = wall[row];
		for(Cell cell : line){
			if(cell == null){
				return false;
			}
		}
		return true;
	}

	private void landIntoWall() {
		Cell[] cells = teromino.cells;
		for(int i =0 ;i < cells.length;i++){
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			wall[row][col] = cell;
		}
	}

	private boolean canDrop() {
		Cell[] cells = teromino.cells;
		for(int i =0;i<cells.length;i++){
			Cell cell = cells[i];
			int row = cell.getRow();
			if(row == Rows - 1){
				return false;
			}
		}
		for(Cell cell:cells) {
			int row = cell.getRow() + 1;
			int col = cell.getCol();
			if (row >= 0 && row < Rows && col >= 0 && col <= Cols && wall[row][col] != null) {
				return false;
			}
		}
		return true;
	}

	public void paintTetromino(Graphics g){
		if(teromino == null){
			return;
		}
		Cell[] cells = teromino.cells;
		for(int i = 0;i<cells.length;i++){
			Cell cell = cells[i];
			int x = cell.getCol() * CELL_SIZE;
			int y = cell.getRow() * CELL_SIZE;
			g.drawImage(cell.getImage(),x-1,y-1,null);
		}
	}

	public void paintNextOne(Graphics g){
		if(nextOne == null){
			return;
		}
		Cell[] cells = nextOne.cells;
		for(int i = 0;i<cells.length;i++){
			Cell cell = cells[i];
			int x = (cell.getCol() + 10) * CELL_SIZE;
			int y = (cell.getRow() + 1) * CELL_SIZE;
			g.drawImage(cell.getImage(),x -1,y-1,null);
		}
	}

	public void paintWall(Graphics g){
		for(int row = 0;row < wall.length;row++){
			Cell[]line = wall[row];
			for(int col = 0;col<line.length;col++){
				Cell cell = line[col];
				int x = col * CELL_SIZE;
				int y = row * CELL_SIZE;
				if(cell == null){
					g.drawRect(x,y,CELL_SIZE,CELL_SIZE);
				}else{
					g.drawImage(cell.getImage(),x-1,y-1,null);
				}
			}
		}
	}
	public static final int CELL_SIZE = 26;
	private void paintScore(Graphics g){
		int x = 290;
		int y = 160;
		g.setColor(new Color(FONT_COLOR));
		Font font = g.getFont();
		font = new Font(font.getName(),font.getStyle(),FONT_SIZE);
		g.setFont(font);
		String str = "SCORE: " + score;
		g.drawString(str,x,y);
		y += 56;
		str = "LINES: " + lines;
		g.drawString(str,x,y);
		y += 56;
		g.drawString("LEVEL: " + level,x,y);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Tetris tetris = new Tetris();
		tetris.setBackground(new Color(0x1008FF));
		frame.add(tetris);
		frame.setSize(530,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		tetris.action();
	}
}
