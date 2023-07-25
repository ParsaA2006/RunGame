/*******************************************************************
//  Parent Class: Entity 
//  By: Jordan Lin
//  Date: 01/03/2023
//  Description: All entities inherit this class
*******************************************************************/
package entity;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Entity {
	
	// entities basic x, y, speed
	public int x;
	public int y;
	public int speed;
	
	// additional variables to some entities
	public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
	public BufferedImage spear;
	public BufferedImage run1, run2, run3, run4, run5, run6;
	String direction;
	int imageSwap = 1;
	int imageNum = 1;
	int spawnRate;
	boolean continueRunning = false;
	

}




