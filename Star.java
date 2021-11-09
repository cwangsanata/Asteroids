import processing.core.PApplet;

public class Star extends PApplet {
	private float x, y;
	private int radius;
	private PApplet applet;

	public Star(PApplet applet_) {
		applet = applet_;
		x = (float)Math.random() * applet.width;
		y = (float)Math.random() * applet.height;
		radius = (int)(Math.random() * 6);
	}

	public void display() {
		applet.fill(x+x, y+y, y-x);
		applet.noStroke();
		applet.ellipse(x, y, radius, radius);
	}
}