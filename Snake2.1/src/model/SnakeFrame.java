package model;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * 完成的功能：添加暂停功能，当按空格键时就暂停，
 * 按键B时就重新开始
 * */

public class SnakeFrame extends Frame{
	//方格的宽度和长度
	public static final int BLOCK_WIDTH = 15 ;
	public static final int BLOCK_HEIGHT = 15 ;
	//界面的方格的行数和列数
	public static final int ROW = 40;
	public static final int COL = 40;
	
	//得分
	private int score = 0;
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	//画图的线程对象
	private MyPaintThread paintThread = new MyPaintThread();

	private Image offScreenImage = null;
	
	private Snake snake = new Snake(this);
	
	private Egg egg = new Egg();
	
	public static void main(String[] args) {
		new SnakeFrame().launch();
	}
	
	public void launch(){
		
		this.setTitle("Snake");
		this.setSize(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
		this.setLocation(30, 40);
		this.setBackground(Color.WHITE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setResizable(false);
		this.setVisible(true);
		
		//为界面添加监听事件
		this.addKeyListener(new KeyMonitor());
		
		new Thread(paintThread).start();
	}
	
	
	private boolean b_gameOver = false;
	
	public void gameOver(){
		b_gameOver = true;
	}
	
	/*
	 * 重写update方法
	 * */
	@Override
	public void update(Graphics g) {
		if(offScreenImage==null){
			offScreenImage = this.createImage(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
		}
		Graphics offg = offScreenImage.getGraphics();
		//先将内容画在虚拟画布上
		paint(offg);
		//然后将虚拟画布上的内容一起画在画布上
		g.drawImage(offScreenImage, 0, 0, null);
		
		if(b_gameOver){
			g.drawString("游戏结束！！！", ROW/2*BLOCK_HEIGHT, COL/2*BLOCK_WIDTH);
		}
		
		snake.draw(g);
		boolean b_Success=snake.eatEgg(egg);
		//吃一个加5分
		if(b_Success){
			score+=5;
		}
		egg.draw(g);
		displaySomeInfor(g);
		
		
	}
	/*
	 * 函数功能：在界面上显示一些提示信息
	 * */
	public void displaySomeInfor(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawString("使用说明:空格键---暂停，按键B---暂停后开始", 5*BLOCK_HEIGHT, 3*BLOCK_WIDTH);
		g.drawString("得分:"+score, 5*BLOCK_HEIGHT, 5*BLOCK_WIDTH);		
		g.setColor(c);
		
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		/*
		 * 将界面画成由ROW*COL的方格构成,两个for循环即可解决
		 * */
		for(int i = 0;i<ROW;i++){
			g.drawLine(0, i*BLOCK_HEIGHT, COL*BLOCK_WIDTH,i*BLOCK_HEIGHT );
		}
		for(int i=0;i<COL;i++){
			g.drawLine(i*BLOCK_WIDTH, 0 , i*BLOCK_WIDTH ,ROW*BLOCK_HEIGHT);
		}
		
		g.setColor(c);
	}
	
	
	/*
	 * 重画线程类
	 * */
	private class MyPaintThread implements Runnable{
		private boolean  running = true;
		private boolean  pause = false;
		@Override
		public void run() {
			while(running){
				//如果pause 为true ，则暂停
				if(pause){
					continue;
				}
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		/*
		 * 函数功能：暂停
		 * */
		public void pause(){
			pause = true;
		}
		/*
		 * 从暂停中重新开始
		 * */
		public void reStart(){
			pause = false;
		}
		
		
	}
	
	private class KeyMonitor extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_SPACE){
				paintThread.pause();
			}
			else if(key == KeyEvent.VK_B){//开始
				paintThread.reStart();
			}
			snake.keyPressed(e);
		}
		
	}
	
}
