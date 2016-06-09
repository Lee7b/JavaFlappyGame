package com.sburkett.flappygame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Pipe
{
	private final int GAP = 200;
	
	private int topXPos = Game.WIDTH;
	private int topYPos;

	private int bottomXPos = Game.WIDTH;
	private int bottomYPos;
	
	private int range = (400 - 1) + 1;
	private Random rand = new Random();
	private Image img;
	
	public Pipe()
	{
		int y = 0;
		
		topYPos = y - 200;
		bottomYPos = topYPos + (400 + GAP); //Image is drawn with respect to the top left corner. So we have to account for the size of the image and the desired gap
											//between the pipes
		
		try 
		{
			img = ImageIO.read(new File("Images/pipe.jpg"));
		}
		catch (IOException e)
		{
			System.out.println("Cannot find file.");
			e.printStackTrace();
		}
	}
	
	public void setXCoords(int topX, int bottomX)
	{
		topXPos = topX;
		bottomXPos = bottomX;
	}
	
	public int getTopXCoord()
	{
		return topXPos;
	}
	
	public int getBottomXCoord()
	
	{
		return bottomXPos;
	}
	
	public int getTopYCoord()
	{
		return topYPos;
	}
	
	public int getBottomYCoord()
	{
		return bottomYPos;
	}
	
	public void movePipes()
	{
		topXPos -= 7;
		bottomXPos -= 7;
		
		if (topXPos + 40 <= 0)
		{
			topXPos = 600;
			topYPos = rand.nextInt(range);
			topYPos = -topYPos;
		}

		if (bottomXPos + 40 <= 0)
		{
			bottomXPos = 600;
			bottomYPos = topYPos + (400 + 200);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, topXPos, topYPos, null);
		g.drawImage(img, bottomXPos, bottomYPos, null);
	}
}
