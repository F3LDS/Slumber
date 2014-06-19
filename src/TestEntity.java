import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TestEntity extends Player

{

	public TestEntity(float x, float y, Image im)
	{
		super(x, y, im);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta)
	{
		if (falling)
		{
			time += delta;
			ySpeed=5;
		} else
		{
			// if we're not falling time does not need to be recorded
			time = 0;
			ySpeed = 0;
		}

		for (int i = 0; i < Math.abs(xSpeed); i++)
		{

			if (xSpeed > 0)
			{
				if (this.canMove(1, 0))
					this.move(1, 0);
			} else if (xSpeed < 0)
			{
				if (this.canMove(-1, 0))
					this.move(-1, 0);
			} else
				break;
		}

		for (int i = 0; i < Math.abs(ySpeed); i++)
		{
			if (ySpeed > 0)
			{
				if (this.canMove(0, 1))
					this.move(0, 1);
			} else if (ySpeed < 0)
			{
				if (this.canMove(0, -1))
					this.move(0, -1);
			} else
				break;
		}

	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

	}

}
