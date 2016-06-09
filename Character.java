package com.sburkett.flappygame;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character
{
	private Image img;
	private int charXPos;
	private int charYPos;
	private boolean isFalling;
	private double speed;
	
	public Character()
	{
		charXPos = 300;
		charYPos = 300;
		isFalling = true;
		
		try 
		{
			img = ImageIO.read(new File("Images/mario.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void setY(int y)
	{
		charYPos = y;
	}
	
	public void setSpeed(int s)
	{
		speed = s;
	}

	public int getX()
	{
		return charXPos;
	}
	
	public int getY()
	{
		return charYPos;
	}
	
	public void jump()
	{
		isFalling = false;
		speed = -15;
	}
	
	public void moveCharacter()
	{
		if (isFalling == true)
		{
			speed += 1.5;
		}
		charYPos += speed;
		isFalling = true;		
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, charXPos, charYPos, null);
	}
}
