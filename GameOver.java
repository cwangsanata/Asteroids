import processing.core.PApplet;

public class GameOver extends PApplet {
	private PApplet applet;
	private int bgColor;
	private int fontSize;

	public GameOver(PApplet applet_) {
		applet = applet_;
		bgColor = 255;
		fontSize = 50;
	}

	public void display() {
		applet.fill(bgColor, 0, 0);
		applet.textSize(fontSize);
		applet.text("GAME OVER", applet.width / 5, applet.height / 2);
		applet.textSize(fontSize / 3);
		applet.text("Press R to reset \nPress Q to quit", (float)(applet.width / 5) + 95, (float)(applet.height / 1.5));
		applet.textSize(15);
	}
}