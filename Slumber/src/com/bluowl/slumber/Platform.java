package com.bluowl.slumber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Platform {
	private BodyDef groundDef;
	private Body groundBody;
	final float WORLD_TO_BOX = 0.01f; //infamous conversion numbers
	final float BOX_TO_WORLD = 100f; 
	
	public void create(World world, float width, float height, float x, float y, Boolean fullwidth) {

	    PolygonShape groundShape = new PolygonShape();
		
	    groundDef = new BodyDef();
	    
	    //if the fullwidth bool is set true, we assume its the ground and stretch it to fill the screen
	    if(fullwidth){
	    	groundDef.position.set(new Vector2((Gdx.graphics.getWidth() / 2) * WORLD_TO_BOX, height * WORLD_TO_BOX));
	    	groundShape.setAsBox((Gdx.graphics.getWidth() / 2) * WORLD_TO_BOX, height * WORLD_TO_BOX);
	    }
	    //otherwise take the parameters and use them
	    else {
	    	groundDef.position.set(new Vector2(x * WORLD_TO_BOX, y * WORLD_TO_BOX));
	    	groundShape.setAsBox(width * WORLD_TO_BOX, height * WORLD_TO_BOX);
	    }
	    
	    //create new physics object based on above parameters
	    groundBody = world.createBody(groundDef);
	    groundBody.createFixture(groundShape, 0f);
	    groundShape.dispose();
	}
}
