/*******************************************************************
//  Program: MainGame
//  By: Jordan Lin
//  Date: 01/23/2022
//  Description: Incharge of creating the window and starting the game
*******************************************************************/

package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGame {

	public static void main(String[] args) {

		// MUSIC SELECTION
		int musicSelector = (int)(Math.random() * 2) + 2;

		// WINDOW SETTINGS
		GamePanel gamepanel = new GamePanel();
		JFrame frame = new JFrame("Run 1: by Jordan");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamepanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// start the game
		gamepanel.playMusic(musicSelector);
		gamepanel.gameStart();


	}

	
}