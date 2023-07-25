/*******************************************************************
//  Program: background.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: creates a background
*******************************************************************/
package background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class background {
	
	// background settings
	GamePanel gp;
	BufferedImage background;
	int x = 0;
	int y = 0;
	int x1 = 0;
	int y1 = -672;
	final int speed = 7;

	
	public background (GamePanel gp)
	{
		this.gp = gp;
		uploadBackground(); // we upload our background when constructor called
	}
	
	/*====================================================================
	|  uploadBackground ()                                               |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	|  no parameters                                                     |
	|--------------------------------------------------------------------|
	|  uploads the background image so that we can use it                |
	====================================================================*/
	public void uploadBackground()
	{
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/background/background.png"));
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
	|  updates the background values                                     |
	====================================================================*/
	public void update()
	{
		// increase 2 images speeds
		y += speed;
		y1 += speed;
		
		// 1 image always behind the other, if image hits max y value, reset its value
		if (y >= gp.screenY)
		{
			y = -672;
		}
		
		if (y1 >= gp.screenY)
		{
			y1 = -672;
		}
	}
	
	/*====================================================================
	|  draw()                                                            |
	|--------------------------------------------------------------------|
	|  void                                                              |
	|--------------------------------------------------------------------|
	| takes in 2Dgraphics g2                                               |
	|--------------------------------------------------------------------|
	| draws the backgrounds using 2D graphics                             |
	====================================================================*/
	public void draw(Graphics g2)
	{
		g2.drawImage(background, 0, y,  gp.screenX, gp.screenY + 50, null);
		g2.drawImage(background, 0, y1,  gp.screenX, gp.screenY + 50, null);


	}
	
	
	
}
