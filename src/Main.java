import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Main extends BasicGame
{

	// TODO: The problem right now is that with acceleration, the speed of our
	// falling player will be large.
	// and because it's large that means it moves in greater strides. Because of
	// this checking for collisions becomes difficult
	// as you will see, because of the large speed the player detects a
	// collision a noticable distance from the actual object
	// its colliding with. If have any ideas on how to remedy this situation
	// please let me know, I've hit a dead end for tonight
	
	//2 Months later: Yea i got nothing -

	private Player p;
	private Image playerImage;
	private static AppGameContainer appgc;
	private Wall w;
	private boolean dev = true;
	private int counter = 0;
	
	// A complete list of all entities in the game area, used for collisons, and
	// easy cycling
	public static ArrayList<Entity> objs = new ArrayList<Entity>();

	public Main(String gamename)
	{
		super(gamename);
	}

	//Code run on creation of game
	public void init(GameContainer gc) throws SlickException
	{
		createPlayers();
		createEnv();

		appgc.setShowFPS(true);
	}

	// Method run on start that generates the player and any NPCs
	public void createPlayers() throws SlickException
	{
		playerImage = new Image("res/testSprite.png");
		p = new Player(350, 0, playerImage);
		objs.add(p);
	}

	// Method run on start that generates the environment
	public void createEnv() throws SlickException
	{
		w = new Wall(0, 350, 800, 50);
		objs.add(w);
	}

	// Method run many times a second that updates the logic components of the
	// game
	public void update(GameContainer gc, int delta) throws SlickException
	{
		pollInput(gc.getInput());

		for (Entity elem : objs)
			elem.update(delta);

		System.out.println("foo: " + counter);
		counter++;
	}

	// Method run many times a second that draws the game to the screen
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		for (Entity elem : objs)
			elem.render(g);
		if (dev)
			drawDev(g);
	}

	//Draws developer info to screen
	public void drawDev(Graphics g)
	{
		for (Entity elem : objs)
		{
			g.setColor(Color.red);
			//Draws hit boxes for items in game
			g.drawRect(elem.getPos().getX(), elem.getPos().getY(), elem
					.getPos().getWidth(), elem.getPos().getHeight());
		}
		g.setColor(Color.white);
	}

	// Method called in update() that collects user input
	public void pollInput(Input i)
	{
		// Controls movement of player through WASD
		if (i.isKeyDown(Input.KEY_A))
		{
			p.setXSpeed(-1 * p.getXSpeedI());
		} else if (i.isKeyDown(Input.KEY_D))
		{
			p.setXSpeed(p.getXSpeedI());
		} else
			p.setXSpeed(0);

		if (i.isKeyPressed(Input.KEY_I)) // Prints First two objects position
											// info to console, take out after
											// finish acceleration/collision
		{

			System.out.println(objs.get(0).getPos().getY()
					+ objs.get(0).getPos().getHeight());
			System.out.println(objs.get(1).getPos().getY());

		}

		// Closes game with hit of escape
		if (i.isKeyPressed(Input.KEY_ESCAPE))
			appgc.exit();
		// Toggles developer features
		if (i.isKeyPressed(Input.KEY_F3))
			dev = !dev;
	}

	public static void main(String[] args)
	{
		try
		{
			appgc = new AppGameContainer(new Main("Slumber"));
			appgc.setMaximumLogicUpdateInterval(60); // Did I even put this
														// here? Felder? Did
														// you?
			appgc.setDisplayMode(640, 480, false); // Screen Size
			appgc.setTargetFrameRate(60); // Set ideal frame rate
			appgc.setAlwaysRender(true); // I don't know, its been a few months
			appgc.setVSync(true); // Set Vertical Sync
			appgc.setShowFPS(false); // Show FPS in top left corner
			appgc.start(); // Initialize game container
		} catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}