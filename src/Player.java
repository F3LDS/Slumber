import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends Entity
{

	// Speed moved when user tells player to move
	protected float xSpeedI = 5, ySpeedI = 5;

	// Speed in x or y that player is presently moving
	protected float xSpeed, ySpeed;

	protected boolean falling = true;
	protected float time = 0;
	protected float jumpStr = 30;
	protected float mass = 5;

	public Player(float x, float y, Image im)
	{
		super(x, y, im);
	}

	public void update(float delta)
	{
		if (falling)
		{
			ySpeed += (float) (9.8 * (delta / 1000));
		}

		this.move(xSpeed, ySpeed);
	}

	public void render(Graphics g)
	{
		g.drawImage(entityImage, pos.getX(), pos.getY());
	}

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
					this.xSpeed = 0;
					if (checkCollisionY(y, o))
					{
						if (this.ySpeed > 0)
						{
							falling = false;
						}
						this.ySpeed = 0;
						return false;
					}
				}
			}
		}
		return true;

	}

	// Method tells sentity to jump
	// Sets upward veloctiy according to strength of sentity's jump
	public void jump()
	{
		if (!falling)
		{
			falling = true;
			ySpeed = (jumpStr / mass) * -1;
		}

	}

	// Accessors and mutators for instance variables
	public float getXSpeedI()
	{
		return xSpeedI;
	}

	public void setXSpeedI(float xSpeedI)
	{
		this.xSpeedI = xSpeedI;
	}

	public float getYSpeedI()
	{
		return ySpeedI;
	}

	public void setYSpeedI(float ySpeedI)
	{
		this.ySpeedI = ySpeedI;
	}

	public float getXSpeed()
	{
		return xSpeed;
	}

	public void setXSpeed(float xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public float getYSpeed()
	{
		return ySpeed;
	}

	public void setYSpeed(float ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	public boolean isFalling()
	{
		return falling;
	}

	public void setFalling(boolean falling)
	{
		this.falling = falling;
	}

	public boolean isFaling()
	{
		return falling;
	}

}
