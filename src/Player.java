import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
	protected boolean aniJumping = false;
	protected float scale = 1;
	protected Animation aniStand;
	protected Animation aniWalkRight;
	protected Animation aniWalkLeft;
	protected Animation aniJump;
	protected Animation aniFall;

	public Player(float x, float y)
	{
		super(x, y);

		Image[] aniArray = new Image[2];

		// This try block builds all the animations
		// TODO: one thing I don't like about this is that I have to have two
		// animation objects
		// for the player walking left and right, the Animation class doesnt
		// seem to have a class
		// that allows me to simply flip the array of images, I shall have to
		// look into this
		// and possibly write an extension of Animation to fit my needs
		try
		{
			// Instantiate the image to find the scale constant
			Image ima = new Image("res/StickDude1.png");

			// Find the scale constant so the sprite will fit inside the hitbox
			scale = this.pos.getHeight() / ima.getHeight();

			// Create array of frames to be used in the image object
			aniArray = new Image[2];

			// Assigns the images needed to an array (also accounts for scale)
			for (int i = 0; i < aniArray.length; i++)
			{
				aniArray[i] = (new Image("res/StickDude" + (i + 1) + ".png"))
						.getScaledCopy(scale);
			}

			// Instantiates the animation object with the populated image array
			// That will be drawn
			aniStand = new Animation(aniArray, 100);

			// Reinstantiates the image array for the next animation
			aniArray = new Image[5];

			// rinse and repeat for every animation needed
			for (int i = 0; i < 5; i++)
			{
				aniArray[i] = new Image("res/StickDudeWalk" + (i + 1) + ".png")
						.getScaledCopy(scale);
			}
			aniWalkRight = new Animation(aniArray, 100);

			for (int i = 0; i < aniArray.length; i++)
			{
				aniArray[i] = new Image("res/StickDudeWalk" + (i + 1) + ".png")
						.getScaledCopy(scale).getFlippedCopy(true, false);
			}
			aniWalkLeft = new Animation(aniArray, 100);

			for (int i = 0; i < aniArray.length; i++)
			{
				aniArray[i] = new Image("res/StickDudeJump" + (i + 1) + ".png")
						.getScaledCopy(scale);
			}
			aniJump = new Animation(aniArray, 100);
			aniJump.setLooping(false);

			aniArray = new Image[3];
			for (int i = 0; i < aniArray.length; i++)
			{
				aniArray[i] = new Image("res/StickDudeFall" + (i + 1) + ".png")
						.getScaledCopy(scale);
			}
			aniFall = new Animation(aniArray, 100);

		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	public void update(float delta)
	{
		// If were falling, accelerate
		if (falling)
		{
			ySpeed += (float) (9.8 * (delta / 1000));
		} else
		{
			if (this.canMove(0, 3))
				falling = true;
		}

		// Move the player a distance equal to it's speed every "tick"
		this.move(xSpeed, ySpeed);
	}

	public void render(Graphics g)
	{
		// Draw the proper animation according to the players present action
		if (aniJumping)
		{
			g.drawAnimation(aniJump, pos.getX(), pos.getY());
			// If the jumping animation is finished, reset the animation and
			// stop the jumping animation, he wont be jumping multiple times in
			// the air
			if (aniJump.isStopped())
			{
				aniJumping = false;
				aniJump.restart();
			}
			return;
		}
		if (falling)
		{
			g.drawAnimation(aniFall, pos.getX(), pos.getY());
			return;
		}
		// If hes not walking
		if (xSpeed == 0)
		{
			g.drawAnimation(aniStand, pos.getX(), pos.getY());
			return;
		}
		// If hes walking right
		if (xSpeed > 0)
		{
			g.drawAnimation(aniWalkRight, pos.getX(), pos.getY());
		}
		// If hes walking left
		else
			g.drawAnimation(aniWalkLeft, pos.getX(), pos.getY());

	}

	public void move(float xSpeed, float ySpeed)
	{
		// For the move method we move our entity
		// One pixel at a time multiple times per movement
		// this allows for accurate collisions along with varying speeds
		for (int i = 0; i < Math.abs(xSpeed); i++)
		{

			if (xSpeed > 0)
			{
				if (this.canMove(1, 0))
				{
					this.pos.setX(this.pos.getX() + 1);
					Main.setXt(Main.getXt() - 1);
				}

			} else if (xSpeed < 0)
			{
				if (this.canMove(-1, 0))
				{
					this.pos.setX(this.pos.getX() - 1);
					Main.setXt(Main.getXt() + 1);
				}

			} else
				break;
		}

		for (int i = 0; i < Math.abs(ySpeed); i++)
		{
			if (ySpeed > 0)
			{
				if (this.canMove(0, 1))
					this.pos.setY(this.pos.getY() + 1);

			} else if (ySpeed < 0)
			{
				if (this.canMove(0, -1))
					this.pos.setY(this.pos.getY() - 1);

			} else
				break;
		}
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
		aniJumping = true;
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
