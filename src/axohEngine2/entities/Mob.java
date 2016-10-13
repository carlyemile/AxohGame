/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 1.0
 * Date: June 29, 2015
 * 
 * Title: Mob
 * Description: A class which adds additional abilities to a possibly animated image. used for monsters or 
 * normal characters. This class has a lot of room for improvement.
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package
package axohEngine2.entities;

import java.awt.Color;
//Imports
import java.awt.Graphics2D;

import java.util.LinkedList;
import java.util.Random;

import axohEngine2.Judgement;
import axohEngine2.project.TYPE;
import axohEngine2.util.Vector2D;

public class Mob extends AnimatedSprite{
	
	/*************
	 * Variables
	 *************/
	//random - Use to obtain a randomly generated number
	//attacks - A list of attacks that can be used by a Mob
	//hostile - Can the mob attack the player?
	//health - HP
	/*ai - An enum which sets the ai of an npc, that ai needs to be written in a method and then added as
	   a TYPE in the TYPE.java class before it can be used. Right now only random ai is possible, and PLAYER.*/
	//xx and yy - Variables used as a starting position from a spawn point
	//speed - How fast the mob can move(Default 2 pixels per update)
	//attacking - A possible state the mob could be in, for many kinds of checks
	//takenOut - Boolean to see if the mob has it's weapon out
	//currentAttack - The currently selected attack to use from the list of Mob attacks
	public Vector2D velocity;
	private Random random = new Random();
	private LinkedList<Attack> attacks;
	private int health;
	private TYPE ai;
	
	private boolean attacking;
	private boolean takenOut = false;
	private Attack currentAttack;

	//moveDir - Direction the mob was moving
	//direction - The direction the Mob is facing
	//randomDir - The random choice of a direction used in random movements
	private DIRECTION moveDir;
	private DIRECTION direction;
	//Graphics and Window objects the mob needs for display
	protected Graphics2D g2d;
	protected Judgement game;
	
	private boolean _isAlive;
	
	private double speed = 0;
	
	public double getXVel() {
		return velocity.getX();
	}
	
	public double getYVel() {
		return velocity.getY();
	}
	
	/************************************************************************
	 * Constructor
	 *  
	 * @param frame - JFrame window for display
	 * @param g2d - Graphics2D object used for displaying an image on a JFrame
	 * @param sheet - The spriteSheet the animation or image is taken from
	 * @param spriteNumber - The position on the spriteSheet the animation is or image starts
	 * @param ai - A TYPE enum depicting the ai's kind (radom, set path, player, etc)
	 * @param name - The character name in a String
	 * @param hostility - Boolean, is the mob going to attack the player?
	 *************************************************************************/
	public Mob(Judgement game, Graphics2D g2d, SpriteSheet sheet, int spriteNumber, TYPE ai, String name) {
		super(game, g2d, sheet, spriteNumber, name);
		velocity = new Vector2D();
		attacks = new LinkedList<Attack>();
		this.game = game;
		this.g2d = g2d;
		this.ai = ai;
		
		setName(name);
		health = 0;
		setAlive(true);
		setSpriteType(ai);

	}
	
	//Getters for name and ai type
	public String getName() { return _name; }
	public TYPE getType() { return ai; }
	
	//Setters for current health, ai, name and speed
	public void setHealth(int health) { this.health = health; }
	public void setAi(TYPE ai) { this.ai = ai; }
	public void setName(String name) { super._name = name; }
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return speed;
	}
	public boolean isAlive() {
		return _isAlive;
	}
	public void setAlive(boolean value) {
		_isAlive = value;
	}
	
	/**************************************************
	 * Set all of the movement related variables to whatever nothing is
	 **************************************************/
	public void resetMovement() {
		moveDir = DIRECTION.NONE;
	}
	
	/***************************************************************
	 * Stop a mob from moving. This stop depends on the type of ai, this needs to
	 * be updated every time a new ai is added. This does not work for player mobs.
	 ****************************************************************/
	public void stop() {
		if(ai == TYPE.RANDOMPATH){
			stopAnim();
		}
	}
	
	/***************************************************************
	 * Method thats called by the system to call various methods for many
	 * different ai types. Update this for future ai types.
	 ****************************************************************/
	public void updateMob() {
		if(ai == TYPE.RANDOMPATH) {
	//		randomPath();
		}
		if(ai == TYPE.SEARCH) {
			search();
		}
		if(ai == TYPE.CHASE) {
			chase();
		}
		if(health < 0) {
			setAlive(false);
		}
	}
	
	/***************************************************************
	 * AI logic used for the randomly moving ai type
	 ****************************************************************/
	/* private void randomPath() {
		int xa = 0;
		int ya = 0;
		int r = random.nextInt(7);
		
		if(wait <= 0) {
			waitOn = false;
			randomDir = DIRECTION.NONE;
			moveDir = DIRECTION.NONE;
		}
		
		if(r == 0 && !waitOn) { //right
			randomDir = DIRECTION.RIGHT;
			waitOn = true;
			wait = random.nextInt(200);
		}
		if(r == 1 && !waitOn) { //left
			randomDir = DIRECTION.LEFT;
			waitOn = true;
			wait = random.nextInt(200);
		}
		if(r == 2 && !waitOn) { //up
			randomDir = DIRECTION.UP;
			waitOn = true;
			wait = random.nextInt(200);
		}
		if(r == 3 && !waitOn) { //down
			randomDir = DIRECTION.DOWN;
			waitOn = true;
			wait = random.nextInt(200);
		}
		if(r >= 4 && !waitOn) { //Not moving
			waitOn = true;
			wait = random.nextInt(200);
			stopAnim();
		}
		
		if(randomDir == DIRECTION.RIGHT) xa = speed; startAnim();
		if(randomDir == DIRECTION.LEFT) xa = -speed; startAnim();
		if(randomDir == DIRECTION.UP) ya = speed; startAnim();
		if(randomDir == DIRECTION.DOWN) ya = -speed; startAnim();
		
		move(xa, ya);
		if(waitOn) wait--;
	}
	*/
	/***************************************************************
	 * AI logic used for the search for something ai type
	 ****************************************************************/
	private void search() {
	}
	
	/***************************************************************
	 * AI logic used for the chase something ai type
	 ****************************************************************/
	private void chase() {
		
	}
	
	/***************************************************************
	 * Method used to change a mobs position by the xa and ya parameters.
	 * This also updates a mobs aniamtion based on what direction is is moving
	 * in. Four animations are needed for a full moving sprite.
	 * 
	 * @param xa - Int movement in pixels on the x axis
	 * @param ya - Int movement in pixels on the y axis
	 ****************************************************************/
	public void move(double xa, double ya) {
		velocity.setLocation(xa + velocity.getX(), ya + velocity.getY());
		position.setX(position.getX() + velocity.getX());
		position.setY(position.getY() + velocity.getY());
		if(xa < 0) { //left
			//xx += xa; 
			if(moveDir != DIRECTION.LEFT) setAnimTo(leftAnim);
			startAnim();
			direction = DIRECTION.LEFT;
			moveDir = DIRECTION.LEFT;
		} else if(xa > 0) { //right
			//xx += xa; 
			
			if(moveDir != DIRECTION.RIGHT) setAnimTo(rightAnim);
			startAnim();
			direction = DIRECTION.RIGHT;
			moveDir = DIRECTION.RIGHT;
		}
			
		if(ya < 0) {  //up
			//yy += ya;

			if(moveDir != DIRECTION.UP) setAnimTo(upAnim);
			startAnim();
			direction = DIRECTION.UP;
			moveDir = DIRECTION.UP;
		} else if(ya > 0) { //down
			//yy += ya; 
			
			if(moveDir != DIRECTION.DOWN) setAnimTo(downAnim);
			startAnim();
			direction = DIRECTION.DOWN;
			moveDir = DIRECTION.DOWN;
		}
		if(xa == 0 && ya == 0) stopAnim();
		velocity.setLocation(0, 0);
	}
	
	/********************************************************************************
	 * Update various player related information. Methods like these can be used for other AI's as well.
	 * Update: weapon in/out, direction, keys pressed, movements etc.
	 * 
	 * @param left - keyCode
	 * @param right - keyCode
	 * @param up - keyCode
	 * @param down - keyCode
	 ********************************************************************************/
	public void updatePlayer(boolean left, boolean right, boolean up, boolean down) {
		if (left) {
			if (!right && !up && !down) {
				direction = DIRECTION.LEFT;
				moveDir = DIRECTION.LEFT;
				toggleLeg(true);
				toggleLeft(false);
				toggleRight(false);
				toggleHead(false);
				if(!takenOut) setAnimTo(leftAnim);
				if(takenOut) setAnimTo(currentAttack.getMoveAnim(direction));
				startAnim();
			}
		}
		if (right) {
			if (!left && !up && !down) {
				direction = DIRECTION.RIGHT;
				moveDir = DIRECTION.RIGHT;
				toggleLeg(true);
				toggleLeft(false);
				toggleRight(false);
				toggleHead(false);
				if(!takenOut) setAnimTo(rightAnim);
				if(takenOut) setAnimTo(currentAttack.getMoveAnim(direction));
				startAnim();
			}
		}
		if (up) {
			if (!left && !right && !down) {
				direction = DIRECTION.UP;
				moveDir = DIRECTION.UP;
				toggleLeg(false);
				toggleLeft(true);
				toggleRight(true);
				toggleHead(true);
				if(!takenOut) setAnimTo(upAnim);
				if(takenOut) setAnimTo(currentAttack.getMoveAnim(direction));
				startAnim();
			}
		}
		if (down) {
			if (!left && !right && !up) {
				direction = DIRECTION.DOWN;
				moveDir = DIRECTION.DOWN;
				toggleLeg(false);
				toggleLeft(true);
				toggleRight(true);
				toggleHead(true);
				if(!takenOut) setAnimTo(downAnim);
				if(takenOut) setAnimTo(currentAttack.getMoveAnim(direction));
				startAnim();
			}
		}
		if(!playOnce) attacking = false;
		if(!left && !right && !up && !down) stopAnim();
		else if (up && down) stopAnim();
		else if (right && left) stopAnim();
	}
	
	/*********************************************************************************
	 * Check to see if a mob is currently attacking or change the state of whether
	 *  it is attacking or not. Depnding on the check, certain animations can be 
	 *  played out.
	 *********************************************************************************/
	public void inOutItem() {
		takenOut = !takenOut;
		setFullAnim(currentAttack.getInOutAnim(direction), currentAttack.getInOutTotal(), currentAttack.getInOutDelay());
		if(takenOut) playOnce(currentAttack.getMoveAnim(direction), currentAttack.getMoveTotal(), currentAttack.getMoveDelay(), currentAttack.getInOutAnim(direction) + currentAttack.getInOutTotal());
		else {
			if(direction == DIRECTION.LEFT) playOnce(leftAnim, walkFrames, walkDelay, currentAttack.getInOutAnim(direction) + currentAttack.getInOutTotal());
			if(direction == DIRECTION.RIGHT) playOnce(rightAnim, walkFrames, walkDelay, currentAttack.getInOutAnim(direction) + currentAttack.getInOutTotal());
			if(direction == DIRECTION.UP) playOnce(upAnim, walkFrames, walkDelay, currentAttack.getInOutAnim(direction) + currentAttack.getInOutTotal());
			if(direction == DIRECTION.DOWN) playOnce(downAnim, walkFrames, walkDelay, currentAttack.getInOutAnim(direction) + currentAttack.getInOutTotal());
		}
	}
	
	//Getters
	public boolean isTakenOut() { return takenOut; }
	public boolean attacking() { return attacking; }	
	
	//Set the animations for attacks of whatever attack is selected and play them on screen
	public void attack() {
		attacking = true;
		setFullAnim(currentAttack.getAttackAnim(direction), currentAttack.getAttackTotal(), currentAttack.getAttackDelay());
		playOnce(currentAttack.getMoveAnim(direction), currentAttack.getMoveTotal(), currentAttack.getMoveDelay(), currentAttack.getAttackAnim(direction) + currentAttack.getAttackTotal());
	}

	/******************************************
	 * Get the list of attacks the mob can use
	 * @return - LinkedList of attacks
	 *****************************************/
	public LinkedList<Attack> attacks() { return attacks; }
	
	/**********************************************************
	 * Add an attack to the list of possibles a mob can use
	 * 
	 * @param name - String differentiating one attack from another
	 * @param magicDam - Number to be used when calculating attack Magic damage
	 * @param strengthDam - Number to be used when calculating attack Strength damage
	 *********************************************************/
	public void addAttack(String name, int magicDam, int strengthDam){ attacks.add(new Attack(name, magicDam, strengthDam)); }
	
	/*******************************************************************
	 * @param name - String name of the attack to retrieve from the Mob
	 * @return - An Attack that is wanted
	 *******************************************************************/
	public Attack getAttack(String name) {
		for(int i = 0; i < attacks.size(); i++){
			if(attacks.get(i).getName().equals(name)) return attacks.get(i);
		}
		return null;
	}
	
	/***************************************************
	 * @param name - String name of the attack for the Mob to use now
	 ***************************************************/
	public void setCurrentAttack(String name) { currentAttack = getAttack(name); }
	
	/***************************************************
	 * @return - Attack datatype which refers to the currently being used attack
	 ***************************************************/
	public Attack getCurrentAttack() { return currentAttack; }
	
	/****************************************************
	 * Lower this mobs health by a damage as well by a modifier from 0 to 
	 * a random int of a maximum value of the damage parameter % 5.
	 * @param damage - An int used to lower this mobs health
	 *****************************************************/
	public void takeDamage(int damage){
		health -= damage - random.nextInt(damage%5); 
	}
	
	/***************************************************
	 * @return - Getter for the current health the mob is at
	 ***************************************************/
	public int health() { return health; }

	/**********************************************
	 * Render the Mob in the game room at anx and y location
	 * 
	 * @param x - Int x position
	 * @param y - Int y position
	 ***********************************************/
	public void renderMob() {
		int x = (int) position.getX() - (int) game.camera.getX();
		int y = (int) position.getY() - (int) game.camera.getY();
		g2d.drawImage(getImage(), x, y, getSpriteSize(), getSpriteSize(), game);
		g2d.setColor(new Color(1f, 0, 0, 0.5f));
		g2d.fillRect(x, y, getSpriteSize(), getSpriteSize());
		g2d.setColor(Color.red);
		g2d.drawRect(x, y, getSpriteSize(), getSpriteSize());
		g2d.setColor(Color.green);
		g2d.fillOval(x - 4, y - 4, 8, 8);
		//g2d.drawImage(getImage(), (int) position.getX() - (int) game.camera.getX(), (int) position.getY() - (int) game.camera.getY(), getSpriteSize(), getSpriteSize(), game);
		//g2d.drawImage(getImage(), (int) position.getX() - (int) game.camera.getX(), (int) position.getY() - (int) game.camera.getY(), getSpriteSize(), getSpriteSize(), game);
		//position.setX(x);
		//position.setY(y);
	}
}