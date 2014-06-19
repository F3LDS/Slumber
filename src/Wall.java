import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Wall extends Entity
{

	public Wall(float x, float y, float w, float h, Image entityImage)
	{
		super(x, y, entityImage);
	}

	public Wall(float x, float y, float w, float h)
	{
		super(x, y, w, h);
	}

	public void render(Graphics g)
	{
		if (this.entityImage == null)
		{
			g.drawRect(pos.getX(), pos.getY(), pos.getWidth(), pos.getHeight());
		} else
		{
			g.drawImage(entityImage, pos.getX(), pos.getY());
		}
	}

	public void update(float delta)
	{

	}

}
