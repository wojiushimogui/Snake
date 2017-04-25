package model;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	
	private Node head = null;
	private Node tail = null;	

	private SnakeFrame sf;
	//初始化是蛇的位置
	private Node node = new Node(3,4,Direction.D);
	
	private int size = 0;
	public Snake(SnakeFrame sf) {
		head = node;
		tail = node;
		size ++;
		this.sf = sf ;		
	}

	public void draw(Graphics g){
		if(head==null){
			return ;
		}
		for(Node node = head;node!=null;node = node.next){
			node.draw(g);
		}	
	}
	
	public class Node {
		
		private static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
		private static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;
		/*
		 * 每个节点的位置
		 * */
		private int row;
		private int col;
		//方向
		private Direction dir ;
		
		private Node pre;
		private Node next;
		
		public Node(int row, int col, Direction dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
			g.setColor(c);		
		}
	}
}
