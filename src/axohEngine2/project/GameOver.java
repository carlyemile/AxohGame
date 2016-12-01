/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 1.0
 * Date: July 5, 2015
 * 
 * Title: Title Menu
 * Description: Create a title menu with a graphic and options to load/ssave/delete a file
 * 
 * TODO: Create option of deleting a file from the menu
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package
package axohEngine2.project;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;

import axohEngine2.entities.AnimatedSprite;
import axohEngine2.entities.ImageEntity;

public class GameOver {

	/********************
	 * Variables
	 ********************/


	//_mainImage - The initial background image of the title screen
	//_secondary - The image which appear after choosing load game
	//titleArrow - The AnimatedSprite which indicates which option the user is currently hovering over
	//_option - A choice the player might make like load or newGame or delete
	private ImageEntity _elephant;


	//Fonts to be used to display text, variouse ones for various uses
	private Font _simple;
	private Font _bold;
	private Font _bigBold;

	//SCREENWIDTH, SCREENHEIGHT - width and height of the game JFrame window in pixels
	private int SCREENWIDTH;
	private int SCREENHEIGHT;

	/*******************************************************************
	 * Constructor
	 * 
	 * @param mainImage - ImageEntity background
	 * @param secondary - ImageEntity load game background
	 * @param titleArrow - AnimatedSprite for currently selected option
	 * @param screenWidth - width of the window in pixels
	 * @param screenHeight - height of the window in pixels
	 * @param simple - The font to use for normal text
	 * @param bold - The font to use for bold text
	 * @param bigBold - The font to use for big loud remarks, very bold
	 * @return 
	 *******************************************************************/
	public GameOver() {
		
		_bold = new Font("Arial", Font.BOLD, 72);
		_bigBold = new Font("Arial", Font.BOLD, 100);
	}

	/****************************************************************************
	 * Render the title screen and change what is being shown based on options the user chooses
	 * 
	 * @param frame - JFrame window where the images will be rendered
	 * @param g2d - Graphics2D object needed to render images
	 * @param titleX - x position of the arrow
	 * @param titleY - y position of the arrow
	 * @param titleX2 - x position of the arrow once a choice is made
	 * @param titleY2 - y position of the arrow once a choice has been made
	 ****************************************************************************/
	public void render(JFrame frame, Graphics2D g2d) {
		_elephant = new ImageEntity(frame);
		_elephant.load("/menus/gameoverelephant.png");
		g2d.drawImage(_elephant.getImage(), 900, 350, 500, 500, frame);
		g2d.setColor(Color.WHITE);
		g2d.setFont(_bigBold);
		g2d.drawString("GAME OVER", 800, 400);
		g2d.setFont(_bold);
		g2d.drawString("Try Again (ENTER)", 410, 750);
		g2d.drawString("Quit (BACKSPACE)", 1160, 750);
		
		
		
	}
		
	
	void drawString(Graphics2D g2d, String text, int x, int y) {
		for (String line : text.split("\n"))
			g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
	}
}