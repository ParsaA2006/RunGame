/*******************************************************************
//  Program: ScoreChecker.java
//  By: Jordan Lin
//  Date: 01/23/2023
//  Description: in charge of checking score
*******************************************************************/
package main;

import java.io.*;
import java.text.DecimalFormat;

public class ScoreChecker {

	Screens screen;
	DecimalFormat timerFormat = new DecimalFormat("#0.00");

	public ScoreChecker(Screens ss) {

		this.screen = ss;
	}

	/*====================================================================
	| NewScoreCheck()            			                             |
	|--------------------------------------------------------------------|
	| return - void                                                      |
	|--------------------------------------------------------------------|
	| boolean newScoreCheck - true if new score, false if no 			 |
	|--------------------------------------------------------------------|
	|tells us whether there is a new highscore or not                    |
	================================================== ==================*/
	public boolean NewScoreCheck() {

		// variables
		String fileName = "player.txt";
		String currentHighScore;
		boolean newScoreCheck = false;

		try {
			BufferedReader read = new BufferedReader(new FileReader(fileName));
			currentHighScore = read.readLine(); // we read what thier current score is 
			
			// checks if they have a high score
			if (currentHighScore == null || screen.timer > Double.parseDouble(currentHighScore) - 0.02) {
				newScoreCheck = true;
			} else 
			{
				newScoreCheck = false;
			}

			read.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return newScoreCheck;
	}

	
	/*===================================================================|
	| ReWriteScore()       			                                     |
	|--------------------------------------------------------------------|
	|returns - void                                               	     |
	|--------------------------------------------------------------------|
	|parameters - none                                         		     |
	|--------------------------------------------------------------------|
	|rewrites the new high score if NewScoreCheck() returns true         |
	================================================== ==================*/
	public void ReWriteScore() {
		String fileName = "player.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
			writer.write(String.valueOf(timerFormat.format(screen.timer))); // rewrites their new high score
			writer.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/*====================================================================
	|createFile()        			                                     |
	|--------------------------------------------------------------------|
	|returns -  void                                                     |
	|--------------------------------------------------------------------|
	|parameters - none                                            		 |
	|--------------------------------------------------------------------|
	|creates a new player file if user deletes it                        |
	================================================== ==================*/
	public void createFile() {
		String fileName = "player.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) // automatically creates new file 
		{
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*====================================================================
	| readCurrentScore()       			                                 |
	|--------------------------------------------------------------------|
	| returns - void                                                     |
	|--------------------------------------------------------------------|
	|parameter - none                                              		 |
	|--------------------------------------------------------------------|
	|reads our current highest score                                     |
	================================================== ==================*/
	public String readCurrentScore() throws IOException {
		String fileName = "player.txt";
		String currentScore = null;
		try {
			BufferedReader read = new BufferedReader(new FileReader(fileName));
			currentScore = read.readLine(); // reads our current score
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentScore;
	}
}