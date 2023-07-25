/*******************************************************************
//  Program: KeyInput.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: we use this class to detect player input/output
*******************************************************************/
package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
	// variables
	GamePanel gp;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean flash;
	public boolean menuUp = true;
	public boolean menuDown;
	public boolean enter;
	
	int keyINFO; // recieves which key user has inputed
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// in charge of key presses
	@Override
	public void keyPressed(KeyEvent e) {
		keyINFO = e.getExtendedKeyCode();
		
		// PLAYER MOVEMENT
		// if user enters W
		if (keyINFO == KeyEvent.VK_W)
		{
			up = true;
			
			
		}
		
		// if user enters S
	    if (keyINFO == KeyEvent.VK_S)
		{
			down = true;
		}
		
		// if user presses D
		if (keyINFO == KeyEvent.VK_D)
		{
			right = true;
			
		}
		
		// if user pressed A
		if (keyINFO == KeyEvent.VK_A)
		{
			left = true;
		}
		
		if (keyINFO == KeyEvent.VK_F)
		{
			flash = true;
		}
		
		// MENU SETTINGS
		if (keyINFO == KeyEvent.VK_UP)
		{
			gp = new GamePanel();
			gp.playSoundFX(5);
			menuUp = true;
			menuDown = false;
			
		} else if (keyINFO == KeyEvent.VK_DOWN)
		{
			gp = new GamePanel();
			gp.playSoundFX(5);
			menuDown = true;
			menuUp = false;
			
		} else if (keyINFO == KeyEvent.VK_ENTER)
		{
			enter = true; 
		}

		
	}

	// in charge of key releases
	@Override
	public void keyReleased(KeyEvent e) {
		
		keyINFO = e.getExtendedKeyCode(); // get what user pressed
		
		//PLAYER MOVEMENT
		// if user enters W
		if (keyINFO == KeyEvent.VK_W)
		{
			
			up = false;
		}
		
		// if user enters S
		if (keyINFO == KeyEvent.VK_S)
		{
			
			down = false;
		}
		
		// if user presses D
		if (keyINFO == KeyEvent.VK_D)
		{
			right = false;
		}
		
		// if user pressed A
		if (keyINFO == KeyEvent.VK_A)
		{
			left = false;
		}
		
		if (keyINFO == KeyEvent.VK_F)
		{
			flash = false;
			
		}
		
		// MENU SETTINGS
		if (keyINFO == KeyEvent.VK_UP)
		{
			menuUp = true;
			
		} else if (keyINFO == KeyEvent.VK_DOWN)
		{
			menuDown = true;
		} else if (keyINFO == KeyEvent.VK_ENTER)
		{
			enter = false;
		}
		

		

		
		
	}

}
