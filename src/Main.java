import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{
	private Player p;
	private static int width = 800;
	private static int height = 640;
	private static AppGameContainer appgc;
	private Wall w;
	private Wall w1;
	private boolean dev = true;

	// TODO: Test variables
	private boolean stressTest = false;
	private ArrayList<TestEntity> ents;
	private int stress = 10;
	private static int xt = 0, yt = 0;

	// A complete list of all entities in the game area, used for collisions, and
	// easy cycling
	public static ArrayList<Entity> objs = new ArrayList<Entity>();

	public Main(String gamename)
	{
		super(gamename);
	}

	// Code run on creation of game
	public void init(GameContainer gc) throws SlickException
	{
		createPlayers();
		createEnv();

		appgc.setShowFPS(true);
	}

	// Method run on start that generates the player and any NPCs
	public void createPlayers() throws SlickException
	{
		p = new Player(350, 0);
		objs.add(p);

		// Code for stress testing:
		// {
		if (stressTest)
		{
			ents = new ArrayList<>();
			for (int i = 0; i < stress; i++)
			{
				ents.add(new TestEntity(i * 55, 0));
				objs.add(ents.get(ents.size() - 1));
			}
		}
		// }
	}

	// Method run on start that generates the environment
	public void createEnv() throws SlickException
	{
		w = new Wall(0, 350, 800, 50);
		w1 = new Wall(100, 300, 400, 50);
		objs.add(w);
		objs.add(w1);
	}

	// Method run many times a second that updates the logic components of the
	// game
	public void update(GameContainer gc, int delta) throws SlickException
	{
		pollInput(gc.getInput());

		for (Entity elem : objs)
			elem.update(delta);
	}

	// Method run many times a second that draws the game to the screen
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.translate(xt, yt);

		for (Entity elem : objs)
			elem.render(g);

		for (int i = 1; i < objs.size(); i++)
		{
			objs.get(i).render(g);
		}
		if (dev)
			drawDev(g);

	}

	// Draws developer info to screen
	public void drawDev(Graphics g)
	{
		g.setColor(Color.red);
		for (Entity elem : objs)
		{
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
		if (i.isKeyPressed(Input.KEY_SPACE))
		{
			p.jump();
		}

		// TODO: test for scrolling
		if (i.isKeyDown(Input.KEY_RIGHT))
		{
			xt -= 5;
		} else if (i.isKeyDown(Input.KEY_LEFT))
			xt += 5;

		// Closes game with hit of escape
		if (i.isKeyPressed(Input.KEY_ESCAPE))
			appgc.exit();
		// Toggles developer features
		if (i.isKeyPressed(Input.KEY_F3))
			dev = !dev;
	}

	public static int getXt()
	{
		return xt;
	}

	public static void setXt(int x)
	{
		xt = x;
	}

	public int getYt()
	{
		return yt;
	}

	public static void setYt(int y)
	{
		yt = y;
	}

	public static void main(String[] args)
	{
		try
		{
			appgc = new AppGameContainer(new Main("Slumber"));
			appgc.setMaximumLogicUpdateInterval(60); // Did I even put this
														// here? Felder? Did
														// you?
			appgc.setDisplayMode(width, height, false); // Screen Size
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