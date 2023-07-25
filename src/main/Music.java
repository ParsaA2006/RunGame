/*******************************************************************
//  Program: Music.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: in charge of creating the music
*******************************************************************/
package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Music {

	//variables
	Clip clip;
	URL pathway[] = new URL[10];
	
	public Music() {
		pathway[0] = getClass().getResource("/backgroundmusic/bg1.wav");
		pathway[1] = getClass().getResource("/backgroundmusic/gameover.wav");
		pathway[2] = getClass().getResource("/backgroundmusic/menumusic.wav");
		pathway[3] = getClass().getResource("/backgroundmusic/menumusic2.wav");
		pathway[4] = getClass().getResource("/backgroundmusic/bg2.wav");
		pathway[5] = getClass().getResource("/soundfx/buttonsfx.wav");
	}
	
	/*====================================================================
	| setFile()                                                          |
	|--------------------------------------------------------------------|
	| returns -  void                                              	     |
	|--------------------------------------------------------------------|
	| int track - index to which song to play                            |
	|--------------------------------------------------------------------|
	|sets the track to play                                              |
	================================================== ==================*/
	public void setFile(int track)
	{
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(pathway[track]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
		
		
		}
		
	}
	
	/*====================================================================
	| play()                                                             |
	|--------------------------------------------------------------------|
	| parameters - void                                           		 |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	|plays a track                                                       |
	================================================== ==================*/
	public void play()
	{
		clip.start();
	}
	
	/*====================================================================
	| loop()                                                             |
	|--------------------------------------------------------------------|
	| parameters - void                                       	         |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	|loops a track                                                       |
	================================================== ==================*/
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/*====================================================================
	| stop()                                                             |
	|--------------------------------------------------------------------|
	|parameters -  void                                           		 |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	|stops a track                                                       |
	================================================== ==================*/
	public void stop()
	{
		clip.stop();
	}
}
