/*******************************************************************
//  Program: player.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: contains the properties of player
*******************************************************************/
package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.GamePanel;
import main.KeyInput;
import java.awt.Rectangle;

public class Player extends Entity {
	
	// variables
	GamePanel gp;
	KeyInput keyH;
	public boolean boundry = true; // ensures that character does not leave screen
	
	public Player(GamePanel gp, KeyInput keyH) {
		this.gp = gp;
		this.keyH = keyH;
		defaultValues();
		loadImages();
		direction = "up";

		
	}


	/*====================================================================
	|  defaultValues()                                                   |
	|--------------------------------------------------------------------|
	|  returns void                                                      |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  Sets the beginning position of our Player  at the start each loop |
	====================================================================*/
	public void defaultValues() {
	      x = (gp.screenX / 2) - 30;
		  y = 600;	
		  speed = 6;
	}
	
	/*====================================================================
	|  loadImages ()                                                     |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  uploads the Player     image so that we can use it                |
	====================================================================*/
	public void loadImages()
	{   
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/Player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/Player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/down2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/Player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/Player/right2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/Player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/Player/left2.png"));
			
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
	|  updates the Player values                                        |
	====================================================================*/
	public void update()
	{
		
		// character does not move if they try to leave screen
		if (boundry)
		{
			if (x > gp.screenX - 50)
			{
				x = gp.screenX - 50;
			}
			if (x <= 0)
			{
				x = 0;
			}
			if (y > gp.screenY - 50)
			{
				y = gp.screenY - 50;
			}
			if (y <= 0)
			{
				y = 0;
			}
		}
	
		
		// if user presses any of these keys..
		if (keyH.up == true || keyH.down == true || keyH.right == true || keyH.left == true)
		{
			if (keyH.up == true) // we move up if they press W
			{
				direction = "up";
				y -= speed;
				
			} else if (keyH.down == true) // we move down if they press S
			{
				direction = "down";
				y += speed;
				
			} else if (keyH.right == true) // we move right if they press D
			{
				direction = "right";
				x += speed;
				
			} else if (keyH.left) // we move left if they press A
			{
				direction = "left";
				x -= speed;
			}
			

			
			// swap image sprites every 5 loops
			imageSwap++;
			if (imageSwap > 5) {
				if (imageNum == 1)
				{
					imageNum = 2;
				}
				else if (imageNum == 2)
				{
					imageNum = 1;
				}
				
				imageSwap = 0;
			}
		}
		
	}
	
	/*====================================================================
	|  draw()                                                            |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  takes in 2Dgraphics g2                                            |
	|--------------------------------------------------------------------|
	| draws the player using 2D graphics                                 |
	================================================== ==================*/
	public void draw(Graphics2D g2)
	{
		BufferedImage imageToPrint = null;
		
		// prints sprites different motions(each direction has 2 pictures)
		switch(direction)	{
			case "up":
				if (imageNum == 1)
				{
					imageToPrint = up1;
					
				} else 
				{
					imageToPrint = up2;
				}
			
				break;
			case "down":
				if (imageNum == 1)
				{
					imageToPrint = down1;
					
				}
				if (imageNum == 2)
				{
					imageToPrint = down2;
				}
				break;
			case "right":
				if (imageNum == 1)
				{
					imageToPrint = right1;
					
				} else 
				{
					imageToPrint = right2;
				}
				break;
			case "left":
				if (imageNum == 1)
				{
					imageToPrint = left1;
					
				} else 
				{
					imageToPrint = left2;
				}
				break;
		}
		
	
		// draws the image
		g2.drawImage(imageToPrint, (int) x, (int)y, gp.finalSizeInGame, gp.finalSizeInGame, null);
		
		
	}
	
}
