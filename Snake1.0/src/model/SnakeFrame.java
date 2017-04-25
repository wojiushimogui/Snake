package model;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * 完成的功能：界面的编写
 * */

	public class SnakeFrame extends Frame{
		//方格的宽度和长度
		public static final int BLOCK_WIDTH = 15 ;
		public static final int BLOCK_HEIGHT = 15 ;
		//界面的方格的行数和列数
		public static final int ROW = 40;
		public static final int COL = 40;
		public static void main(String[] args) {
			new SnakeFrame().launch();
		}
		
		public void launch(){
			
			this.setTitle("Snake");
			this.setSize(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
			this.setLocation(300, 400);
			this.addWindowListener(new WindowAdapter() {
	
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
				
			});
			this.setResizable(false);
			this.setVisible(true);
		}
	
	}
