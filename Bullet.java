import processing.core.PApplet;

public class Bullet extends Floater {
	public Bullet(PApplet applet_, Spaceship theShip) {
		super(applet_);
		corners = 0;
		xCorners = new int[corners];
		yCorners = new int[corners];  
		myCenterX = theShip.getCenterX();
		myCenterY = theShip.getCenterY();
		myPointDirection = theShip.getPointDirection();
		myColor = 255;
		myXspeed = 0;
		myYspeed = 0;
		accelerate(6);
	}

	@Override
	public void move() {
		myCenterX += myXspeed;    
    myCenterY += myYspeed;
	}

	@Override
	public void show() {
		applet.fill(myColor);
		applet.stroke(myColor);
		applet.ellipse((float)myCenterX, (float)myCenterY, 4, 4);
	}

	public boolean outOfBounds() {
			if (myCenterX > applet.width) return true;    
      else if (myCenterX < 0) return true;
      else if(myCenterY > applet.height) return true;
      else if (myCenterY < 0) return true;
			return false;
	}
}