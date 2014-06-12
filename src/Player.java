import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends Entity
{

	// Speed moved when user tells player to move
	protected float xSpeedI = 5, ySpeedI = 5;

	// Speed in x or y that player is presently moving
	protected float xSpeed, ySpeed;

	protected boolean falling = true;
	private float time = 0;

	public Player(float x, float y, Image im)
	{
		super(x, y, im);
	}


	public void update(float delta)
	{
		// s(t) = 1/2at^2+v_0t + s_0
		// v(t) = s'(t) = at + v_0
		// a(t) = v'(t) = s''(t) = a = 9.8

		// ySpeed = 9.8t + ySpeedI

		// time is recorded to apply an acceleration to the player
		if (falling)
		{
			time += delta;
			ySpeed = (float) (9.8 * (time / 1000));
		} else
		{
			// if we're not falling time does not need to be recorded
			time = 0;
		}

		this.move(xSpeed, ySpeed);

	}

	public void render(Graphics g)
	{
		g.drawImage(entityImage, pos.getX(), pos.getY());
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


}
