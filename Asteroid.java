import processing.core.PApplet;
import java.util.Random;

public class Asteroid extends Floater {
	private double rotSpeed;
	private int pointValue;
	private Random rand;

	public Asteroid(PApplet applet_) {
		super(applet_);

		rand = new Random();

		// Spawns Asteroids only on the 5% margins
		double tempX = 0;
		if (rand.nextBoolean()) tempX = Math.random() * (applet_.width * 0.05);
		else tempX = Math.random() * (applet_.width * 0.05) + (applet_.width * 0.95);

		double tempY = 0;
		if (rand.nextBoolean()) tempY = Math.random() * (applet_.width * 0.05);
		else tempY = Math.random() * (applet_.width * 0.05) + (applet_.width * 0.95);
		
		rotSpeed = (Math.random() * 6) - 3;
		corners = 6;
		xCorners = new int[]{-11, 7, 13, 6, -11, -10};
		yCorners = new int[]{-8, -8, 0, 10, 8, 0};
		myXspeed = Math.random() * 4 - 2;
		myYspeed = Math.random() * 4 - 2;
		myCenterX = tempX;
		myCenterY = tempY;
		myColor = 255;
		myPointDirection = 0;
		pointValue = 50;
	}

	@Override
	public void move() {
		super.move();
		turn(rotSpeed);
	}

	@Override
	public void show() {
		    applet.fill(0,0);   
        applet.stroke(myColor);    
        //translate the (x,y) center of the ship to the correct position
    	applet.translate((float)myCenterX, (float)myCenterY);

		//convert degrees to radians for rotate()     
		float dRadians = (float)(myPointDirection * (Math.PI / 180));
	
		//rotate so that the polygon will be drawn in the correct direction
		applet.rotate(dRadians);
	
		//draw the polygon 
 		applet.beginShape();
		for (int i = 0; i < corners; i++)
		{
		  applet.vertex(xCorners[i], yCorners[i]);
		}
		applet.endShape(applet.CLOSE);

		//"unrotate" and "untranslate" in reverse order
		applet.rotate(-1 * dRadians);
		applet.translate(-1 * (float)myCenterX, -1 * (float)myCenterY);
	}

	public int getPointValue() {
		return pointValue;
	}
}