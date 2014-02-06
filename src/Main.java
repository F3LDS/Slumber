import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Howdy!", 10, 30);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Slumber"));
			appgc.setMaximumLogicUpdateInterval(60);			
			appgc.setDisplayMode(640, 480, false); // Screen Size
			appgc.setTargetFrameRate(60);
			appgc.setAlwaysRender(true);
			appgc.setVSync(true);
			appgc.setShowFPS(false); // Show FPS in top left corner
			appgc.start(); // Initialize game container
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}