/*******************************************************************
//  Program: Boar.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: stores bore properties
*******************************************************************/
package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Boar extends Entity {

	// variables
	GamePanel gp;
	Player player;

	// recieve gamepanel and player class methods & variables
	public Boar(GamePanel gp, Player player) {
		this.gp = gp;
		this.player = player;
		defualtValues();
		loadImages();
	}
	
	/*====================================================================
	|  defaultValues()                                                   |
	|--------------------------------------------------------------------|
	|  returns void                                                      |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  Sets the beginning position of our boar  at the start each loop   |
	====================================================================*/
	public void defualtValues() {
		
		x = gp.screenX;
		y = gp.screenY;
		speed = 3;

	}

	/*====================================================================
	|  uploadImages     ()                                               |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  uploads the boar images so that we can use it                     |
	====================================================================*/
	public void loadImages() {
		try {
			run1 = ImageIO.read(getClass().getResourceAsStream("/boar/run_1.png"));
			run2 = ImageIO.read(getClass().getResourceAsStream("/boar/run_2.png"));
			run3 = ImageIO.read(getClass().getResourceAsStream("/boar/run_3.png"));
			run4 = ImageIO.read(getClass().getResourceAsStream("/boar/run_4.png"));
			run5 = ImageIO.read(getClass().getResourceAsStream("/boar/run_5.png"));
			run6 = ImageIO.read(getClass().getResourceAsStream("/boar/run_6.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*====================================================================
	|  update()                                                          |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  updates the boar values                                           |
	====================================================================*/
	public void update() {
		// boar constantly moves up to the left
		y -= speed;
		x -= speed;

		// resets its position when it leaves the screen(x)
		if (x < -200) {
			x = gp.screenX;
		}
		
		// resets its position when it leaves the screen(y)
		if (y < 0) {
			y = gp.screenY;
		}

		// we swap images ever 3 loops; so that it shows animation
		imageSwap++;
		if (imageSwap > 3) {
			if (imageNum == 1) {
				imageNum = 2;
			} else if (imageNum == 2) {
				imageNum = 3;

			} else if (imageNum == 3) {
				imageNum = 4;
			} else if (imageNum == 4) {
				imageNum = 5;
			} else if (imageNum == 5) {
				imageNum = 6;
			} else if (imageNum == 6) {
				imageNum = 1;
			}

			imageSwap = 0; 
		}

	}

	/*====================================================================
	|  draw()                                                            |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  takes in 2DGraphics g2                                              |
	|--------------------------------------------------------------------|
	| draws the boar using 2D graphics                                    |
	================================================== ==================*/
	public void draw(Graphics2D g2) {

		
		BufferedImage imageToPrint = null;
		
		// tells us what image to print
		if (imageNum == 1) {
			imageToPrint = run1;

		} else if (imageNum == 2) {
			imageToPrint = run2;
		} else if (imageNum == 3) {
			imageToPrint = run3;
		} else if (imageNum == 4) { // ex. we print the fourth animation
			imageToPrint = run4;
		} else if (imageNum == 5) {
			imageToPrint = run5;

		} else if (imageNum == 6) {
			imageToPrint = run6;
		}

		// draw boar
		g2.drawImage(imageToPrint, (int) x, (int) y, gp.finalSizeInGame * 2, gp.finalSizeInGame, null);

	}

}
