package com.sburkett.flappygame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel
{
	final static int HEIGHT = 600; //Game window height
	final static int WIDTH = 600; //Game window width
	
	private Character mario;
	private Pipe pipe1;
	private Pipe pipe2;
	private Rectangle characterBounds;
	private Rectangle pipe1TopPipeBounds;
	private Rectangle pipe1BottomPipeBounds;
	private Rectangle pipe2TopPipeBounds;
	private Rectangle pipe2BottomPipeBounds;
	private Image bg;
	private int score;
	private boolean gameStarted;
	private int bg1X = 0; //X coordinate of the first bg image
	private int bg2X = 600; //X coordinate of the second bg image (we use two to give a scrolling effect
	
	public Game()
	{
		mario = new Character();
		
		//Initialize the pipes
		pipe1 = new Pipe(); //pipe object represents a top pipe and a bottom pipe with a gap in-between
		pipe2 = new Pipe();
		pipe2.setXCoords(WIDTH+300, WIDTH+300); //Draw the second set of pipes 300 pixels to the right of the first set
		
		//Initialize the triangles which will be used for collision detection
		characterBounds = new Rectangle();
		pipe1TopPipeBounds = new Rectangle();
		pipe1BottomPipeBounds = new Rectangle();
		pipe2TopPipeBounds = new Rectangle();
		pipe2BottomPipeBounds = new Rectangle();
		
		try 
		{
			bg = ImageIO.read(new File("Images/bg.png"));
		}
		catch (IOException e)
		{
			System.out.println("Cannot find file.");
			e.printStackTrace();
		}
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				mario.jump();
				gameStarted = true;
			}
		});	
	}
	
	public void playGame()
	{		
		//Update the hit boxes
		characterBounds.setBounds(mario.getX(), mario.getY(), 40, 40);
		pipe1TopPipeBounds.setBounds(pipe1.getTopXCoord(), pipe1.getTopYCoord(), 50, 420);
		pipe1BottomPipeBounds.setBounds(pipe1.getBottomXCoord(), pipe1.getBottomYCoord(), 50, 420);
		pipe2TopPipeBounds.setBounds(pipe2.getTopXCoord(), pipe2.getTopYCoord(), 50, 420);
		pipe2BottomPipeBounds.setBounds(pipe2.getBottomXCoord(), pipe2.getBottomYCoord(), 50, 420);
		
		//If your character hits a pipe you die
		if (characterBounds.intersects(pipe1TopPipeBounds) || characterBounds.intersects((pipe1BottomPipeBounds))
		    || characterBounds.intersects(pipe2TopPipeBounds) || characterBounds.intersects(pipe2BottomPipeBounds))
		{
			youDied();
		}
		
		if (mario.getY() < 0 - 40 || mario.getY() > HEIGHT) //If character goes off-screen
		{
			youDied();
		}
		
		//Increment the score when the player goes through a set of pipes
		if (pipe1.getTopXCoord() + 50 == 300)
		{
			score += 1;
		}
		else if (pipe2.getTopXCoord() + 50 == 300)
		{
			score += 1;
		}
	}
	
	public void youDied() //Reset the game
	{
		System.out.println("Oops, you died!");
		score = 0;
		mario.setY(WIDTH / 2);
		pipe1.setXCoords(WIDTH, WIDTH);
		pipe2.setXCoords(WIDTH+300, WIDTH+300);
		mario.setSpeed(1);
		gameStarted = false;
	}
	
	public void run()
	{
		if (gameStarted)
		{
			playGame();
			moveBG();
			pipe1.movePipes();
			pipe2.movePipes();
			mario.moveCharacter();
			repaint();
		}
	}
	
	public void moveBG() //Method to scroll the background
	{
		bg1X -= 5; //Move bg1 5 to the left
		bg2X -= 5; //Move bg2 5 to the left
		
		if (bg1X + 600 <= 0) //If the first bg image goes off screen, move it back to the right
			bg1X = 600;
		if (bg2X + 600 <= 0) //Same thing, but with the second bg image
			bg2X = 600;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(bg, bg1X, 0, null);//Draw background image 1
		g.drawImage(bg, bg2X, 0, null);//Draw background image 2
		
		//Draw the character and pipes
		pipe1.paintComponent(g);	//Draw the set of first pipes
		pipe2.paintComponent(g);	//Draw second set of pipes
		mario.paintComponent(g);		//Draw character
		
		//Show current score in the window
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20)); 
		g.drawString("Score: " + score, 50, 80);
		
		//Display this message if game isn't started yet
		if (!gameStarted) //If game hasn't been started yet show this text
		{
			g.drawString("Click anywhere to begin", 230, 250);
		}
	}
}
