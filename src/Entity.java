import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity
{

	// Added visibilty modifiers

	// Removed velocity variable from Entity

	// Rectangles work much better for collisions
	protected Rectangle pos;
	protected Image entityImage;
	protected boolean canFall = true;

	// Cut parameters of Player constructor to what was neccessary
	public Entity(float x, float y, Image entityImage)
	{
		pos = new Rectangle(x, y, entityImage.getWidth(),
				entityImage.getHeight());

		this.entityImage = entityImage;
	}

	public Entity(float x, float y)
	{
		pos = new Rectangle(x, y, 50, 50);

		this.entityImage = null;
	}

	public Entity(float x, float y, float w, float h)
	{
		pos = new Rectangle(x, y, w, h);
		this.entityImage = null;
	}

	// So In order for us to have multiple collisions we need to move
	// our entities one pixel at a time. To do this we move
	// our entities one pixel at a time multiple times per tick
	//TODO: This may cause an issue with enough entitys on screen
	//		the game appears to be "turn-based"
	public void move(float xSpeed, float ySpeed)
	{
		for (int i = 0; i < Math.abs(xSpeed); i++)
		{

			if (xSpeed > 0)
			{
				if (this.canMove(1, 0))
					this.pos.setX(this.pos.getX() + 1);
				;
			} else if (xSpeed < 0)
			{
				if (this.canMove(-1, 0))
					this.pos.setX(this.pos.getX() - 1);
				;
			} else
				break;
		}

		for (int i = 0; i < Math.abs(ySpeed); i++)
		{
			if (ySpeed > 0)
			{
				if (this.canMove(0, 1))
					this.pos.setY(this.pos.getY() + 1);
				;
			} else if (ySpeed < 0)
			{
				if (this.canMove(0, -1))
					this.pos.setY(this.pos.getY() - 1);
				;
			} else
				break;
		}
	}

	// For collision I attempted to write a sweep and prune
	// method (http://en.wikipedia.org/wiki/Sweep_and_prune),
	// But it doesn't seem like it will be a huge improvement over
	// basic collision detection...I'll try both and see which
	// one is faster

	// Basically what we do is we see if the item is going to
	// collide on the x axis, if it isnt we move on, if it is
	// we then check the y axis, if both return true, collision
	// hypothetically this will be more efficent because
	// we aren't comparing both x and y axis on every object,
	// lowering the over amount of checks made per second
	// and making everything more efficent

	// this cycles through every other object in the memory,
	// sees if this entity is colliding with anything, and
	// returns accordingly
	public boolean canMove(float x, float y)
	{
		Entity o;

		for (int i = 0; i < Main.objs.size(); i++)
		{
			o = Main.objs.get(i);

			// Make sure we're not checking that the objects
			// is colliding with itself
			if (!o.equals(this))
			{
				if (checkCollisionX(x, o))
				{

					if (checkCollisionY(y, o))
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	// this checks to see if either the left or right edge of an entities
	// bounding box is intersecting with any other bounding box
	// this allows to check HALF of the collision, and not bothering with the
	// other half if there is no collision
	// allowing for more efficent collision detection
	public boolean checkCollisionX(float xSpeed, Entity e)
	{
		float x1 = pos.getX() + xSpeed;
		float w1 = pos.getWidth() + xSpeed;

		float x2 = e.getPos().getX();
		float w2 = e.getPos().getWidth();

		if ((x1 >= x2 && x1 <= x2 + w2)
				|| (x1 + w1 >= x2 && x1 + w1 <= x2 + w2))
		{
			return true;
		}

		return false;

	}

	// this checks to see if either the top or bottom edge of an entities
	// bounding box is intersecting with any other bounding box
	public boolean checkCollisionY(float ySpeed, Entity e)
	{
		float y1 = pos.getY() + ySpeed;
		float h1 = pos.getHeight() + ySpeed;

		float y2 = e.getPos().getY();
		float h2 = e.getPos().getHeight();

		if ((y1 >= y2 && y1 <= y2 + h2)
				|| (y1 + h1 >= y2 && y1 + h1 <= y2 + h2))
		{
			return true;
		}

		return false;
	}

	public abstract void update(float delta);

	public abstract void render(Graphics g);

	public Rectangle getPos()
	{
		return pos;
	}

	public void setPos(Rectangle pos)
	{
		this.pos = pos;
	}

}
