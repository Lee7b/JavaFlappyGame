package com.sburkett.flappygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main
{
	private Game game = new Game();

	public Main()
	{
		JFrame frame = new JFrame("Bird Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(600,640);
		frame.setResizable(false);
		frame.add(game);
		
		Timer timer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				game.run();
			}
		});
		
		timer.start();
	}
	
	public static void main(String [] args)
	{
		new Main();
	}
	
}
