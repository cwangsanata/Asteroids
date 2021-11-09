import processing.core.PApplet;

public class Spaceship extends Floater
{   
	private int life;
	private int score;
	private boolean isDestroyed;
	private static int highScore;
	private final String grunts[];
	
	public Spaceship(PApplet applet_) {
		super(applet_);
		corners = 4;
		xCorners = new int[]{-8, 16, -8, -2};
		yCorners = new int[]{-8, 0, 8, 0};
		myColor = 255;
		myXspeed = 0;
		myYspeed = 0;
		myCenterX = applet_.width / 2; 
		myCenterY = applet_.height / 2; 
		myPointDirection = 270;
		life = 3;
		score = 0;
		isDestroyed = false;
		highScore = 0;
		grunts = new String[]{"Ow", "Oof", "Ouch", "darn", "urgh"};
	}

	@Override
	public void accelerate(double d) {
		super.accelerate(d);
		int speedLimit = 3;
		if (myXspeed > speedLimit) myXspeed = speedLimit;
		if (myYspeed > speedLimit) myYspeed = speedLimit;
		if (myXspeed < -speedLimit) myXspeed = -speedLimit;
		if (myYspeed < -speedLimit) myYspeed = -speedLimit;
	}

	public static void setHighScore(int tempScore) {
		if (tempScore > highScore) highScore = tempScore;
	} 

	// Settin' and Gettin'

	public void setCenterX(double x) {myCenterX = x;}

	public void setCenterY(double y) {myCenterY = y;}

	public void setXspeed(double speed) {myXspeed = speed;}

	public void setYspeed(double speed) {myYspeed = speed;}

	public double getXspeed() {return myXspeed;}

	public double getYspeed() {return myYspeed;}

	public void xAccelerate(double acc) {myXspeed += acc;}

	public void yAccelerate(double acc) {myYspeed += acc;}

	public double getPointDirection() {return myPointDirection;}

	public int getLife() {return life;}

	public void addLife(int newLife) {life += newLife;}

	public int getScore() {return score;}

	public void addScore(int newScore) {score += newScore;}

	public boolean getIsDestroyed() {return isDestroyed;}

	public int getHighScore() {return highScore;}

	public void setIsDestroyed(boolean isDestroyed_) { isDestroyed = isDestroyed_;}
	
	public void damage() {
		life--;
		int i = (int)(Math.random() * grunts.length);
		applet.text(grunts[i], (float)myCenterX - 20, (float)myCenterY - 20);
		if (life == 0 && !isDestroyed) {
			isDestroyed = true;
			reset();
			AsteroidsGame.flipExtraChance();
		}
	}
	
	public void reset() {
		myXspeed = 0;
		myYspeed = 0;
		myCenterX = applet.width / 2; 
		myCenterY = applet.height / 2; 
		myPointDirection = 270;
		score = 0;
		life = 3;
	}

	public void show() {
		super.show();
		applet.fill(255, 0, 0);
		applet.stroke(255);
		if (!isDestroyed) {
			for (int lives = 1; lives < life + 1; lives++) {
				applet.rect(applet.width - lives * (applet.width / 12) - 5, 5, applet.width / 12, 10, 7);
			}
			applet.fill(255);

			applet.text("Score: " + String.valueOf(score), 10, 15);
			// Need to save high score into text file
			applet.text("High-score: " + String.valueOf(highScore), 10, 30);
		}
	}

	public void deathAnimation() {
		turn(Math.random() * 10 + 5);
		myXspeed = Math.random() * 10 - 5;
	  myYspeed = Math.random() * 10 - 5;
		applet.fill(255);
		applet.text("MAYDAY, MAYDAY", (float)myCenterX - 5, (float)myCenterY - 5);
	}
}
