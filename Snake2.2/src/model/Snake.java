package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {
	private static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;
	
	private Node head = null;
	private Node tail = null;	
	
	private SnakeFrame sf;
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
		move();
		for(Node node = head;node!=null;node = node.next){
			node.draw(g);
		}	
	}
	/*
	 * 此函数的功能，先在头部添加一个节点，然后删除尾部的节点，这样就完成了移动
	 * */
	public void move() {
		addNodeInHead();
		//检查是否死忙
		checkDead();
		deleteNodeInTail();
	}

	private void checkDead() {
		//头结点的边界检查
		if(head.row<2||head.row>SnakeFrame.ROW||head.col<0||head.col>SnakeFrame.COL){
			this.sf.gameOver();
		}
		
		//头结点与其它结点相撞也是死忙
		for(Node node =head.next;node!=null;node = node.next){
			if(head.row==node.row&&head.col == node.col){
				this.sf.gameOver();
			}
		}
	}

	private void deleteNodeInTail() {
		Node node = tail.pre;
		tail = null;
		node.next = null;
		tail = node;
	}

	private void addNodeInHead() {
		Node node = null;
		switch(head.dir){
		case L:
			node = new Node(head.row,head.col-1,head.dir);
			break;
		case U:
			node = new Node(head.row-1,head.col,head.dir);
			break;
		case R:
			node = new Node(head.row,head.col+1,head.dir);
			break;
		case D:
			node = new Node(head.row+1,head.col,head.dir);
			break;
		}
		
		node.next = head;
		head.pre = node;
		head = node;

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT :
			if(head.dir!=Direction.R){
				head.dir = Direction.L;
			}
			break;
		case KeyEvent.VK_UP :
			if(head.dir!=Direction.D){
				head.dir = Direction.U;
			}
			break;
		case KeyEvent.VK_RIGHT :
			if(head.dir!=Direction.L){
				head.dir = Direction.R;
			}
			break;
		case KeyEvent.VK_DOWN :
			if(head.dir!=Direction.U){
				head.dir = Direction.D;
			}
			break;
		}
	}
	
	public Rectangle getRect(){
		return new Rectangle(head.col*BLOCK_WIDTH, head.row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
	public boolean eatEgg(Egg egg){
		
		if(this.getRect().intersects(egg.getRect())){
			addNodeInHead();
			egg.reAppear();
			return true;
		}
		else{
			return false;
		}
	}
	
	public class Node {
		

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
