import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;


public class Entity {

	float posX;
	float posY;
	float entityVelocity;
	Vector2f position;
	Image entityImage; 

    public Entity(float x, float y, float velocity, String direction)
    {
        position = new Vector2f(x, y);
        entityVelocity = velocity;
    }

    public void update(float delta, boolean moving)
    {

    }
}
