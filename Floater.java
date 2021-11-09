import processing.core.PApplet;

public class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   
    protected PApplet applet;
    protected int corners;  //the number of corners, a triangular floater has 3   
    protected int[] xCorners;   
    protected int[] yCorners;   
    protected int myColor;   
    protected double myCenterX, myCenterY; //holds center coordinates   
    protected double myXspeed, myYspeed; //holds the speed of travel in the x and y directions  
    protected double myPointDirection; //holds current direction the ship is pointing in degrees   
    
    public Floater(PApplet applet_)
    {
        applet = applet_;
    }
    
    //Accelerates the floater in the direction it is pointing (myPointDirection)   
    public void accelerate (double dAmount)   
    {          
        //convert the current direction the floater is pointing to radians    
        double dRadians = myPointDirection * (Math.PI / 180);     
        //change coordinates of direction of travel    
        myXspeed += ((dAmount) * Math.cos(dRadians));    
        myYspeed += ((dAmount) * Math.sin(dRadians));       
    }   

    public void turn (double degreesOfRotation)   
    {     
        //rotates the floater by a given number of degrees    
        myPointDirection += degreesOfRotation;   
    }   

    public void move ()   //move the floater in the current direction of travel
    {      
        //change the x and y coordinates by myDirectionX and myDirectionY       
        myCenterX += myXspeed;    
        myCenterY += myYspeed;     

        //wrap around screen    
        if(myCenterX > applet.width)
        {     
            myCenterX = 0;    
        }    
        else if (myCenterX < 0)
        {     
            myCenterX = applet.width;    
        }    
        if(myCenterY > applet.height)
        {    
            myCenterY = 0;    
        }   
        else if (myCenterY < 0)
        {     
            myCenterY = applet.height;    
        }   
    }   

    public void show ()  //Draws the floater at the current position  
    {             
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

		/*
		THE STUFF I ADDED GOES BEYOND HERE
		*/
		public double getCenterX() {
			return myCenterX;
		}

		public double getCenterY() {
			return myCenterY;
		}

		public void info() {
		applet.text("X Speed: " + String.valueOf(myXspeed), 10, 15);
		applet.text("Y Speed: " +  String.valueOf(myYspeed), 10, 30);
		applet.text("X Position: " + String.valueOf(myCenterX), 10, 45);
		applet.text("Y Position: " + String.valueOf(myCenterY), 10, 60);
	}
} 
