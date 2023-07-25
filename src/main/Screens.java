/*******************************************************************
//  Program: Screens.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: in charge of our menu and gameover screen.
*******************************************************************/
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class Screens {
	// variables
	KeyInput keyH;
	GamePanel gp;
	MainGame mg;
	Font arcadeclassic;
	int playerX = 165;
	int playerY = 250;
	double timer = 0;
	BufferedImage timerImage;
	BufferedImage down1;
	BufferedImage down2;
	BufferedImage skull;
	DecimalFormat timerFormat = new DecimalFormat("#0.00"); // instantiating DecimalFormat for timer, so that it
														// displays only 2 decimal
	ScoreChecker sc = new ScoreChecker(this);
	boolean exitProgramCheck = true;
	boolean retry; // see if user retried

	public Screens(GamePanel gp, KeyInput keyH, MainGame mg) {
		this.gp = gp;
		this.keyH = keyH;
		loadImages(); // load images and set the font in constructor so automatically
		setFont();

	}

	/*====================================================================
	| LoadImages()       			                                     |
	|--------------------------------------------------------------------|
	| return - void                                                      |
	|--------------------------------------------------------------------|
	|Parameters - none        v                                      	 |
	|--------------------------------------------------------------------|
	|loads character sprites, timer and skull image                      |
	================================================== ==================*/
	public void loadImages() {
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/down2.png")); // not currently used
			timerImage = ImageIO.read(getClass().getResourceAsStream("/background/timer.png"));
			skull = ImageIO.read(getClass().getResourceAsStream("/objects/skull.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*====================================================================
	| setFont()       			                                         |
	|--------------------------------------------------------------------|
	| return - void                                                      |
	|--------------------------------------------------------------------|
	|parameters - none                                             		 |
	|--------------------------------------------------------------------|
	|sets our font                                                       |
	================================================== ==================*/
	public void setFont() {
		try {
			InputStream is = getClass().getResourceAsStream("/font/ARCADECLASSIC.TTF");
			arcadeclassic = Font.createFont(Font.TRUETYPE_FONT, is); // set our font
		} catch (FontFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*====================================================================
	| drawMenuScreen();       			  	                  	         |
	|--------------------------------------------------------------------|
	| returns - void                                                   	 |
	|--------------------------------------------------------------------|
	| parameter - graphics g to draw                          		   	 |
	|--------------------------------------------------------------------|
	|draws the menu screen										   		 |
	================================================== ==================*/
	public void drawMenuScreen(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; // casts g to g2(so we can use graphics 2d)

		// BACKGROUND
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenX, gp.screenY);

		// PRINTS OUT SCORE
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
		try {
			if (sc.readCurrentScore() != null) {
				g2.drawString("HIGHSCORE: " + sc.readCurrentScore(), 70, 625);

			} else {
				g2.drawString("HIGHSCORE: 0", 70, 625);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// PRINTS OUT TITLE
		g2.setFont(arcadeclassic);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 150));
		g2.setColor(Color.GRAY);
		g2.drawString("RUN 1", 70, 210);
		g2.setColor(Color.WHITE);
		g2.drawString("RUN 1", 65, 200);

		// PRINTS OUT MENU OPTIONS
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75));
		g2.drawString("PLAY", 120, 470);
		g2.drawString("QUIT", 120, 550);

		// DRAW BOXES ON MENU OPTIONS
		if (keyH.menuUp == true) {

			g2.drawString(">", 350, 470);

			if (keyH.enter == true) {
				gp.gamestate = gp.inGame; // we enter game and change states
				gp.stopMusic();
				gp.playMusic(4);
			}
		} else if (keyH.menuDown == true) {
			g2.drawString(">", 350, 550);
			if (keyH.enter) {
				if (this.exitProgramCheck == false) {

				} else if (this.exitProgramCheck == true) {
					System.exit(0); // close program if they quit
				}

			}
		}

		g2.drawImage(down1, this.playerX, this.playerY, gp.finalSizeInGame * 3, gp.finalSizeInGame * 3, null);// draw
																												// Image

	}

	/*====================================================================
	| drawGameOverSCreen()        			                  		     |
	|--------------------------------------------------------------------|
	|returns - void                                                      |
	|--------------------------------------------------------------------|
	| parameters - graphics g; to draw stuff                 	         |
	|--------------------------------------------------------------------|
	|draws the game over screen                                          |
	================================================== ==================*/
	public void drawGameOverScreen(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// BACKGROUND
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, gp.screenX, gp.screenY);

		// DRAWS SCORES
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));
		if (sc.NewScoreCheck() == true) {
			sc.ReWriteScore();
			g2.drawString("NEW HIGH SCORE: " + timerFormat.format(this.timer), 65, 190);
		} else if (sc.NewScoreCheck() == false) {
			g2.drawString("SCORE: " + timerFormat.format(this.timer), 140, 190);
		}

		// GAMEOVER TEXT
		g2.setFont(arcadeclassic);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
		g2.setColor(Color.GRAY);
		g2.drawString("GAMEOVER", 60, 135);
		g2.setColor(Color.WHITE);
		g2.drawString("GAMEOVER", 55, 130);

		// RETRY TEXT
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
		g2.setColor(Color.GRAY);
		g2.drawString("RETRY", 123, 475);
		g2.setColor(Color.WHITE);
		g2.drawString("RETRY", 120, 470);

		// QUIT TEXT
		g2.setColor(Color.GRAY);
		g2.drawString("QUIT", 123, 555);
		g2.setColor(Color.WHITE);
		g2.drawString("QUIT", 120, 550);

		// USER OPTIONS
		if (keyH.menuUp == true) {

			g2.drawString(">", 350, 470);
			if (keyH.enter == true) {
				this.retry = true; 
				gp.gamestate = gp.inGame; // if user wants to retry, we change game state
			}

		} else if (keyH.menuDown == true) {
			g2.drawString(">", 350, 550);
			if (keyH.enter) {
				gp.gamestate = gp.startingScreen; // if user wants to quit, close program
			}
		}

		g2.drawImage(skull, this.playerX, this.playerY, gp.finalSizeInGame * 3, gp.finalSizeInGame * 3, null); // draw image

	}
	
	/*====================================================================
	|DrawTimer()      			                                         |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	|parameters - graphics g to draw                             		 |
	|--------------------------------------------------------------------|
	|draws the timer                                                     |
	================================================== ==================*/
	public void DrawTimer(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		this.timer += (double) 1 / 60; // we add 1/60 every loop, so 60 times a second
		g2.drawImage(timerImage, 125, 50, gp.finalSizeInGame * 5, gp.finalSizeInGame * 2, null);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35));
		
		// REFORMAT TIME
		if (this.timer > 10) {
			g2.drawString("" + timerFormat.format(this.timer), 205, 110);
		} else {
			g2.drawString("" + timerFormat.format(this.timer), 213, 110);
		}

	}

}
