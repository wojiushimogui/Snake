package model;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
 * 完成功能：单独用一个线程来控制重画，
 * 并添加Snake类，并添加键盘的方向键来控制蛇的运动
 * */
public class SnakeFrame  extends Frame{

	
	//游戏界面的大小
	public static final int GAME_WIDTH =400;
	public static final int GAME_HEIGHT =400;
	//游戏中方格的大小
	private static final int squareSize = 10;
	
	Snake s = new Snake(40,40,Direction.D,this);
	
	public static void main(String[] args) {
		
		SnakeFrame s = new SnakeFrame();
		s.launchFrame();

	}

	private void launchFrame() {
		
		this.setLocation(300, 400);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setBackground(Color.WHITE);
		this.setTitle("Snake");
		//添加关闭的处理事件
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		//禁止改变窗的大小
		this.setResizable(false);
		this.setVisible(true);
		
		new Thread(new MyPaintThread()).start();
		
		this.addKeyListener(new MyKeyListener());
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		
		/*
		 * 画线
		 * */
		int col = GAME_WIDTH/squareSize;
		int row = GAME_HEIGHT/squareSize;
		for(int i=0;i<col;i++){
			g.drawLine(i*squareSize, 0, i*squareSize, GAME_HEIGHT);
		}
		for(int i=0;i<row;i++){
			g.drawLine(0, i*squareSize, GAME_WIDTH, i*squareSize);
		}
		g.setColor(c);
		
		s.draw(g);
	}
	
	/*
	 * 重画线程类
	 * */
	private class MyPaintThread implements Runnable{

		@Override
		public void run() {
			//每隔50ms重画一次
			while(true){
				repaint();//会自动调用paint方法
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	/*
	 * 键盘监听类
	 * */
	private class MyKeyListener extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
		
	}
	
}
