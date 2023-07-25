/*******************************************************************
//  Program: GamePanel.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: in charge of displaying our updated information
*******************************************************************/
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import entity.SpearRight;
import entity.SpearLeft;
import entity.Boar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.event.FocusListener;

import background.background;

public class GamePanel extends JPanel implements Runnable {

	// GAME STATE
	final int startingScreen = 1;
	final int retryScreen = 2;
	final int inGame = 3;
	int gamestate = startingScreen;

	// DEFUALT SIZES
	final int defaultSize = 16; // 16 x 16 pixels
	final int scale = 3;
	public final int finalSizeInGame = 16 * 3; // scale it so that its larger on screen
	public final int screenX = 10 * finalSizeInGame; // 480 pixels
	public final int screenY = 14 * finalSizeInGame; // 672 pixels

	// GAME
	Thread gameThread;
	KeyInput keyH = new KeyInput();
	MainGame mg = new MainGame();
	Player player = new Player(this, keyH);
	SpearRight hand = new SpearRight(this);
	SpearLeft secondHand = new SpearLeft(this);
	Boar boar = new Boar(this, player);
	Music music = new Music();
	background background = new background(this);
	Screens screen = new Screens(this, keyH, mg);
	ScoreChecker sc = new ScoreChecker(screen);
	File file = new File("player.txt");

	// FPS
	int FPS = 60;

	// Collision
	boolean collision;

	// Constructor to build our panel
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenX, screenY));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true); // we can render quicker
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}

	/*====================================================================
	|  gamestart()                                                       |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	| starts our game along with our thread                              |
	================================================== ==================*/
	public void gameStart() {
		startGameThread(); // starts our gameThread
		
		// checks if player score file exits, if not create new one
		try {
			if (file.exists()) {

			} else {
				sc.createFile();
			}
		} catch (Exception e) {

		}

	}

	/*====================================================================
	| createHitBox()                          						     |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	| draws the boar using 2D graphics                                    |
	=====================================================================*/
	public void createHitBox()

	{
		//create new hitboxes for each entity
		Rectangle p = new Rectangle(player.x, player.y, this.finalSizeInGame, this.finalSizeInGame);
		Rectangle h = new Rectangle(hand.x + 100, hand.y + 140, finalSizeInGame * 6, finalSizeInGame);
		Rectangle h2 = new Rectangle(secondHand.x - 10, secondHand.y + 150, this.finalSizeInGame * 5 + 50,this.finalSizeInGame * 4 - 160);
		Rectangle b = new Rectangle(boar.x, boar.y, this.finalSizeInGame * 2, this.finalSizeInGame);

		// we check if player collides with any
		if (p.intersects(h) || p.intersects(h2) || p.intersects(b)) {
			music.stop();
			collision = true;
			player.boundry = false; // we remove the boundry so our character can leave the screen
			
			
			if (collision == true) {

				gamestate = retryScreen;
				playSoundFX(1);
				player.y -= 5000;
				player.speed = 0;
				hand.speed = 0;
				secondHand.speed = 0;
				boar.speed = 0;

			}
		}

	}

	/*====================================================================
	|  startGameThread()                            					   |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	| creates a game thread                                              |
	================================================== ==================*/
	public void startGameThread() {
		gameThread = new Thread(this); // create thread
		gameThread.start();// starts the thread

	}

	/*====================================================================
	| playMusic()                                                        |
	|--------------------------------------------------------------------|
	| void                                                               |
	|--------------------------------------------------------------------|
	| no parameters                                                      |
	|--------------------------------------------------------------------|
	|  plays the background music                                        |
	================================================== ==================*/
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop(); // loop

	}
	
	/*====================================================================
	| playSoundFX()                                                      |
	|--------------------------------------------------------------------|
	| void                                                               |
	|--------------------------------------------------------------------|
	| no parameters                                                      |
	|--------------------------------------------------------------------|
	|  plays a sound fx                                                  |
	================================================== ==================*/
	public void playSoundFX(int i) {
		music.setFile(i);
		music.play(); // dont loop 
	}

	/*====================================================================
	| stopMusic()                                                        |
	|--------------------------------------------------------------------|
	| returns void                                                       |
	|--------------------------------------------------------------------|
	| no parameters                                                      |
	|--------------------------------------------------------------------|
	| stops the background music                                        |
	================================================== ==================*/
	public void stopMusic() {
		music.stop(); // stops music
	}
	
	/*====================================================================
	| reset()                                                            |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	| parameters - none                                                  |
	|--------------------------------------------------------------------|
	| resets all objects position if user retries                        |
	================================================== ==================*/
	public void reset() {
		playMusic(4);
		boar.defualtValues();
		hand.defaultValues();
		secondHand.defaultValues();
		player.defaultValues();
		screen.timer = 0;
		
	}
	
	// MAIN GAME THREAD
	@Override
	public void run() {

		while (gameThread != null) {
			long drawTime = 1000000000 / FPS; // we want to draw a new frame every 0.0166 seconds
			double whenToDrawNext = System.nanoTime() + drawTime; // 
			double timeLeft;
			// UPDATE: we first update the information 
			update();

			// PAINT: we then paint the new information
			repaint();

			timeLeft = whenToDrawNext - System.nanoTime();

			try {
				timeLeft = whenToDrawNext - System.nanoTime();
				timeLeft = timeLeft / 1000000; // calculates how much time is left
				if (timeLeft < 0) {
					timeLeft = 0;
				}
				Thread.sleep((long) timeLeft); // we sleep for that amount of time until next loop

				whenToDrawNext += drawTime;
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		}

	}
	/*====================================================================
	| update()                                                           |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	| parameters - none                                                  |
	|--------------------------------------------------------------------|
	|our main update method to update everything           				 |
	================================================== ==================*/
	public void update() {

		// displays differently spending on gamestate
		if (this.gamestate == startingScreen) {
			// no updates
		} else if (this.gamestate == this.retryScreen) 
		{
			// no updates
		} else {
			
			// reset our position when we restart
			if (screen.retry)
			{
				reset();
				screen.retry = false;
			}
			
			// constantly updates these objects
			createHitBox();
			background.update();
			player.update();
			hand.update();
			secondHand.update();
			boar.update();
		}

	}

	/*====================================================================
	| paintComponent                                                     |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	| parameters - Graphics g(we cast to 2d) to draw                   	 |
	|--------------------------------------------------------------------|
	| main drawing method, draws everything                              |
	================================================== ==================*/
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;// casts g to 2d (so we can use 2d graphics)
		
		// displays depending on gamestate
		if (this.gamestate == startingScreen) {
	
			screen.exitProgramCheck = true;
			screen.drawMenuScreen(g2);

		} else if (this.gamestate == retryScreen) 
		{
	
			screen.exitProgramCheck = false;
			screen.drawGameOverScreen(g2);
			
		} else if (this.gamestate == inGame) {
			background.draw(g2);
			boar.draw(g2);
			hand.draw(g2);
			secondHand.draw(g2);
			player.draw(g2);
// 			g2.drawRect(player.x, player.y, this.finalSizeInGame, this.finalSizeInGame); // THESE ARE HITBOXES. uncomment if you want to see.
//			g2.drawRect(hand.x + 100, hand.y + 140, finalSizeInGame * 6, finalSizeInGame);
//			g2.drawRect(secondHand.x - 10, secondHand.y + 150, this.finalSizeInGame * 5 + 50,this.finalSizeInGame * 4 - 160);
//			g2.drawRect(boar.x, boar.y, this.finalSizeInGame * 2, this.finalSizeInGame);
			screen.DrawTimer(g2);
			g2.dispose();

		}

	}

}