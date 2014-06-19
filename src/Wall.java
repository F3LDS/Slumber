import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Wall extends Entity
{

	protected Animation anim;

	public Wall(float x, float y, float w, float h, Image entityImage)
	{
		super(x, y, entityImage);

	}

	public Wall(float x, float y, float w, float h)
	{
		super(x, y, w, h);

//		try
//		{
//			Image[] ims = new Image[3];
//			for (int i = 0; i < ims.length; i++)
//			{
//				ims[i] = new Image("res/Wall" + (i + 1) + ".png");
//			}
//			anim = new Animation(ims, 100);
//		} catch (SlickException e)
//		{
//			e.printStackTrace();
//		}
	}

	public void render(Graphics g)
	{

		//g.drawAnimation(anim, this.pos.getX(), this.pos.getY());

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
