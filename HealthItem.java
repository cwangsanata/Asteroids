import processing.core.PApplet;

public class HealthItem extends Floater {
	int healthValue = 1;
	public HealthItem(PApplet applet_) {
		super(applet_);
		PApplet applet;
    corners = 12; 
    xCorners = new int[]{0, 0, 4, 4, 8, 8, 4, 4, 0, 0, -4, -4};   
    yCorners = new int[]{0, 4, 4, 0, 0, -4, -4, -8, -8, -4, -4, 0}; 
    myColor = 255;   
    myCenterX = Math.random() * applet_.width; 
		myCenterY = Math.random() * applet_.height;  
    myPointDirection = Math.random() * 360;

		accelerate(0.25); 
	}

	public void show() {
		   applet.fill(myColor);   
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

	public int getHealthVal() {return healthValue;}
}