/*******************************************************************
//  Program: background.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: contains the spear properties
*******************************************************************/
package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyInput;

public class SpearLeft extends Entity {
	
	// variables
	GamePanel gp;
	KeyInput keyH;
	
	
	public SpearLeft (GamePanel gp)
	{
		this.gp = gp;
		defaultValues();// set default values and load images in constructor
		loadImages();
	}
	
	/*====================================================================
	|  defaultValues()                                                   |
	|--------------------------------------------------------------------|
	|  returns void                                                      |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  Sets the beginning position of our Spear  at the start each loop  |
	====================================================================*/
	public void defaultValues() {
	      x = -35;
		  y = -500;
		  speed = 7;
	}
	
	/*====================================================================
	|  loadImages ()                                                     |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  uploads the spear image so that we can use it                     |
	====================================================================*/	
	public void loadImages()
	{
		try {
			spear = ImageIO.read(getClass().getResourceAsStream("/objects/hand_left.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			
		 	// we reset the spears position back to top once it reaches bottom
			if (y < gp.screenY + 30 )
			{
				y += speed;
				
			} else if (y > gp.screenY + 30)
			{
				y = -200;
			}
			
			if (x > 1) {
				
			}
			
		
	
	}
	
	/*====================================================================
	|  draw()                                                            |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  takes in 2Dgraphcis g2                  					   		|
	|--------------------------------------------------------------------|
	| draws the spear using 2D graphics                                  |
	================================================== ==================*/
	public void draw(Graphics2D g2) {
		
		BufferedImage imageToPrint = spear;
		
		// draws the spear
		g2.drawImage(imageToPrint, (int) x, (int)y - 100, gp.finalSizeInGame*6, gp.finalSizeInGame*6, null);
	}
	
	
}
