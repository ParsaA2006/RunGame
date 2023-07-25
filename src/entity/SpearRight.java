/*******************************************************************
//  Program: Hands.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: stores all of our right spear properties
*******************************************************************/
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.lang.*;

import main.GamePanel;
import main.KeyInput;

public class SpearRight extends Entity {
	
	GamePanel gp;
	KeyInput keyH;
	
	
	public SpearRight (GamePanel gp)
	{
		this.gp = gp;
		defaultValues(); // we load defualt values and load thier image
		loadImages();	// through the constructor
	}
	
	/*====================================================================
	|  defaultValues()                                                   |
	|--------------------------------------------------------------------|
	|  returns void                                                      |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  Sets the beginning position of our spear at the start each loop  |
	====================================================================*/
	public void defaultValues() {
	      x = gp.screenX - 275;
		  y = -1000;
		  speed = 8;
	}
	
	/*====================================================================
	|  loadImages   ()                                                   |
	|--------------------------------------------------------------------|
	|  returns void                                                      |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  loads the images of the spear(on right)                           |
	====================================================================*/
	public void loadImages()
	{
		try {
			spear = ImageIO.read(getClass().getResourceAsStream("/objects/hand_right.png"));
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
	|  updates the spears values                                         |
	====================================================================*/
	public void update() {
			
			
			// moves the hand downwards
			if (y < gp.screenY + 30 )
			{
				y += speed;
				
			} else if (y > gp.screenY + 30)
			{
				y = -200;
			}
			

		
			
			
	
	}
	
	/*====================================================================
	|  draw()                                                            |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	| takes in graphics g2                                               |
	|--------------------------------------------------------------------|
	| draws the spear using 2D graphics                                    |
	================================================== ==================*/
	public void draw(Graphics2D g2) {
		
		BufferedImage imageToPrint = spear;
		
		g2.drawImage(imageToPrint, x +100, y + 140, gp.finalSizeInGame*6, gp.finalSizeInGame, null);

	}
	
	
}
