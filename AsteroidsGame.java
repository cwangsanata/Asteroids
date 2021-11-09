/*
Issues: 

Spelled "collision" wrong for every method lol

Lotsa bloat
*/
import processing.core.PApplet;
import java.util.ArrayList;

public class AsteroidsGame extends PApplet
{
		private PApplet applet;
    private Spaceship ship;
		private Star[] stars;
		private ArrayList<Asteroid> asteroids;
		private ArrayList<Bullet> bullets;
		private ArrayList<HealthItem> healths;
		private GameOver go;
		private static boolean extraChance;

    public void settings()
    {
			size(500, 500);
    }

    public void setup() 
    {
			stars = new Star[100];
			asteroids = new ArrayList<>();
			ship = new Spaceship(this);
			bullets = new ArrayList<>();
			healths = new ArrayList<>();
			go = new GameOver(this);
			// Give one last chance to the player if they are at one health by spawning health item
			extraChance = true;

			for (int i = 0; i < stars.length; i++) {
				stars[i] = new Star(this);
			}

			for (int i = 0; i < 3; i++) {
				asteroids.add(new Asteroid(this));
			}
    }

    public void draw() 
    {
			background(0);
			// Makes the star background
			for (Star star : stars) {
				star.display();
			}

			// Spawns random asteroid every 5 seconds 
			if (frameCount % 180 == 0 && asteroids.size() < 10) asteroids.add(new Asteroid(this));
			if (ship.getLife() == 1 && extraChance && healths.size() < 1) {
				healths.add(new HealthItem(this));
				flipExtraChance();
			}

			// Checks if the ship and asteroid have made contact and damages if so.
			for (int i = 0; i < asteroids.size(); i++) {
				asteroids.get(i).move();
				asteroids.get(i).show();
				if (checkColission(asteroids.get(i), ship, 20)) {
					asteroids.remove(i);
					ship.damage();
				}
			}

			// Basic ship movement and animation
      ship.show();
			ship.move();

			// Removes bullets that leave the screen, animates, and displays
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).move();
				bullets.get(i).show();
				if (bullets.size() > 1 && bullets.get(i).outOfBounds()) 
          bullets.remove(i);
			}

			// Checks if the bullet and asteroid made contact
			for (int i = 0; i < asteroids.size(); i++) {
				for (int j = 0; j < bullets.size(); j++) {
					if (checkColission(asteroids.get(i), bullets.get(j), 20)) {
						ship.addScore(asteroids.get(i).getPointValue());
						bullets.remove(j);
						asteroids.remove(i);
						break;
					}
				}
			}

			// Healing item colission with ship, movement, and display 
			for (int i = 0; i < healths.size(); i++) {
				healths.get(i).move();
				healths.get(i).show();
				if (checkColission(ship, healths.get(i), 20)) {
					ship.addLife(healths.get(i).getHealthVal());
					healths.remove(i);
				}
			}

			// Gameover 
			if (ship.getIsDestroyed()) {
				go.display();
				wipe();
				ship.deathAnimation();
			}

			// If no input is detected, the ship slows down
			// until stopped; autodeacceleration
			if (!keyPressed){
				if (ship.getXspeed() < 0) ship.xAccelerate(0.02);
				if (ship.getYspeed() > 0) ship.yAccelerate(-0.02);
				if (ship.getXspeed() > 0) ship.xAccelerate(-0.02);
				if (ship.getYspeed() < 0) ship.yAccelerate(0.02);
			}

			// Directional movement and turning
			if (keyPressed && key == CODED) {
				if (keyCode == UP) ship.accelerate(0.05);
				if (keyCode == DOWN) ship.accelerate(-0.05);
				if (keyCode == LEFT) ship.turn(-5);
				if (keyCode == RIGHT) ship.turn(5);
			}

			Spaceship.setHighScore(ship.getScore());
    }

		// Checks "contact" between objects of the Floater class and its subclasses at a given 
		// distance and returns a boolean value if contact is made.
		public boolean checkColission(Floater a, Floater b, double distance) {
			return applet.dist(
			(float)a.getCenterX(), 
			(float)a.getCenterY(), 
			(float)b.getCenterX(), 
			(float)b.getCenterY()) < distance;
		}

		// Weird implementation; but works
		public static void flipExtraChance() {extraChance = !extraChance;}

		public void keyPressed() {
			// Hyperspace (random jump and set movement to 0)
			if (key == 'h') {
				ship.setCenterX(Math.random() * width);
				ship.setCenterY(Math.random() * height);
				ship.setXspeed(0);
				ship.setYspeed(0);
			}

			// Shoot bullets
			if (key == ' ') bullets.add(new Bullet(this, ship));

			// Play/Pause
			if (key == 'p') {
    		if (looping) noLoop();
    		else loop();
			}

						// resets
			if (key == 'r') {
				wipe();
				setup();
			}

			// ends program
			if (key == 'q') {
				System.exit(0);
			}

			/*
			Cheats/Test buttons
			*/

			// Clear all floaters except ship 
			if (key == 'w') {
				wipe();
			}

			// Self Damage
			if (key == 'd') {
				ship.damage();
			}
		}

		public void wipe() {
			asteroids.clear();
			healths.clear();
			bullets.clear();
			stars = new Star[0];
		}
}