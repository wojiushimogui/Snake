package model;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
 * 完成功能：在界面中画单元格
 * */
public class SnakeFrame  extends Frame{

	private static final int GAME_WIDTH =400;
	private static final int GAME_HEIGHT =400;
	
	private static final int squareSize = 10;
	
	
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
	}
	
}
