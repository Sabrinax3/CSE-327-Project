package GUIShaap;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Game extends JPanel implements KeyListener, ActionListener{
	//length of snake
	private int[] xlength=new int[750];
	private int[] ylength=new int[750];
	
	//detecting snake's movement
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	//image of snake
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	//default length of snake
	private int length=3;
	
	//speed of snake
	private Timer t;
	private int delay=120;
	
	//snake body
	private ImageIcon body;
	
	//snakes food
	private int [] foodx= {25,50,75,100,125,150,175,200,
					225,250,275,300,325,350,375,400,425,450,475,
					500,525,550,575,600,625,650,675,
					700,725,750,775,800,825,850};
	
			
	private int [] foody= {75,100,125,150,175,200,
			225,250,275,300,325,350,375,400,425,450,475,
			500,525,550,575,600,625	};
	 
	private ImageIcon food;
	//food randomiser
	private Random random=new Random();
	
	private int xpos=random.nextInt(34);//random return value between 0 and 33. this is to pick index of food position.
	private int ypos=random.nextInt(23);
	
	//scores
	private int score=0;
	
	//running status of game
	private boolean isRunning=false;
	private boolean gameOver=false;
	
	//number of moves made
	private int moves=0;
	
	private ImageIcon title;
	
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t=new Timer(delay, this);
		t.start();
		
	}
	
	//built in method to draw things on the panel
	//main animation method. this draws everything.
	public void paint(Graphics g) {
		
		//default position of snake
		if(moves==0) {
			xlength[2]=50;
			xlength[1]=75;
			xlength[0]=100;
			
			ylength[2]=100;
			ylength[1]=100;
			ylength[0]=100;
			
		}
		//draw title image border
		g.setColor(Color.YELLOW);
		g.drawRect(24,  10,  851,  55);
		
		//draw title image
		title=new ImageIcon("snaketitle.jpg");
		title.paintIcon(this, g, 25, 11);
		
		//draw border for game
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw background for game
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw Length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length: "+length,50, 40 );
		

		//draw Scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Scores: "+score,780, 30 );
		
				
		
		//initial position of snake
		//if condition added or else program keeps redrawing rightmouth every animation.
		if(moves==0) {
		rightmouth=new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, xlength[0], ylength[0]);//draw
		}
		//direction of snake
		for(int i=0; i<length; i++) {
			if(i==0 && right) {
				rightmouth=new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, xlength[i], ylength[i]);
			}
			
			if(i==0 && left) {
				leftmouth=new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, xlength[i], ylength[i]);
			}
			
			if(i==0 && up) {
				upmouth=new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, xlength[i], ylength[i]);
			}
			
			if(i==0 && down) {
				downmouth=new ImageIcon("downmouth.png");
			    downmouth.paintIcon(this, g, xlength[i], ylength[i]);
			}
			
			if(i!=0) {
					body=new ImageIcon("snakeimage.png");
					body.paintIcon(this, g, xlength[i], ylength[i]);
			}
		}
		
		
		food= new ImageIcon("enemy.png");
		
					
		//condition to respawn food when eaten
		if((foodx[xpos]==xlength[0])&& (foody[ypos]==ylength[0])) {
			java.awt.Toolkit.getDefaultToolkit().beep();
			length++;
			score+=10;
			xpos=random.nextInt(34);
			ypos=random.nextInt(23);
			
		}
		
		food.paintIcon(this, g, foodx[xpos], foody[ypos]);
		
		
		for(int a=1;a<length;a++) {
			if(xlength[a]==xlength[0]&&ylength[a]==ylength[0]) {
				right=false;
				left=false;
				up=false;
				down=false;
				isRunning=false;
				gameOver=true;
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial",Font.PLAIN,45));
				g.drawString("XD GAME OVER XD", 220, 300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.PLAIN,35));
				g.drawString("Score:"+score, 350, 350);
						
			}
		}
		
			if(!gameOver&&!isRunning&&moves==0) {	
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.PLAIN,50));
				g.drawString("Welcome To GUI Shaap!", 160, 300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.PLAIN,25));
				g.drawString("Move to Start game", 330, 350);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		t.start();
		if(right) {
			for(int r=length;r>=0;r--) {
				ylength[r+1]=ylength[r];
			}
			for(int r=length;r>=0;r--) {
				if(r==0) {
				xlength[r]=xlength[r]+25;
				//Snake head moves right by 25 pixels
				}
				else {
					xlength[r]=xlength[r-1];
					//snake body parts move to position of previous part
					//not using xlength[r+1]=xlength[r]; because that way positioning of r=1 skipped.//r==1 not assigned new pos
				}
				if(xlength[r]>850) {
					xlength[r]=25;
				}
			}
			
			repaint();
		}
		if(left) {

			for(int r=length;r>=0;r--) {
				ylength[r+1]=ylength[r];
			}
			for(int r=length;r>=0;r--) {
				if(r==0) {
				xlength[r]=xlength[r]-25;
				//Snake head moves right by 25 pixels
				}
				else {
					xlength[r]=xlength[r-1];
					//snake body parts move to position of previous part
					//not using xlength[r+1]=xlength[r]; because that way positioning of r=1 skipped.//r==1 not assigned new pos
				}
				if(xlength[r]<25) {
					xlength[r]=850;
				}
			}
			
			repaint();
			
		}
		if(up) {

			for(int r=length;r>=0;r--) {
				xlength[r+1]=xlength[r];
			}
			for(int r=length;r>=0;r--) {
				if(r==0) {
				ylength[r]=ylength[r]-25;
				//Snake head moves up by 25 pixels
				}
				else {
					ylength[r]=ylength[r-1];
					//snake body parts move to position of previous part
					//not using xlength[r+1]=xlength[r]; because that way positioning of r=1 skipped.//r==1 not assigned new pos
				}
				if(ylength[r]<75) {
					ylength[r]=625;
				}
			}
			
			repaint();
	
		}
		if(down) {
			for(int r=length;r>=0;r--) {
				xlength[r+1]=xlength[r];
			}
			for(int r=length;r>=0;r--) {
				if(r==0) {
				ylength[r]=ylength[r]+25;
				//Snake head moves down by 25 pixels
				}
				else {
					ylength[r]=ylength[r-1];
					//snake body parts move to position of previous part
					//not using xlength[r+1]=xlength[r]; because that way positioning of r=1 skipped.//r==1 not assigned new pos
				}
				if(ylength[r]>625) {
					ylength[r]=75;
				}
			}
			
			repaint();
	
		}

		}

	

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			isRunning=true;
			
			if(score>0) {
				score-=1;
			}
			right=true;
			if(!left) {
				right=true;
			}
			else {
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			isRunning=true;
			
			if(score>0) {
				score-=1;
			}
			left=true;
			if(!right) {
				left=true;
			}
			else {
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			isRunning=true;
			
			if(score>0) {
				score-=1;
			}
			up=true;
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
			right=false;
			left=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			isRunning=true;
			
			if(score>0) {
				score-=1;
			}
			down=true;
			if(!up) {
				down=true;
			}
			else {
				down=false;
				up=true;
			} 
			left=false;
			right=false;
		}
		

	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
