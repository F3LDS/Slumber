package com.bluowl.slumber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class light {
	
	Body circleBody;
	
	RayHandler handler;
	
	public void create(World world, Matrix4 camera, float w, float h) {
		
		
		
		BodyDef circleDef = new BodyDef();
		circleDef.type = BodyType.DynamicBody;
		circleDef.position.set(5f, 5f);
		
		circleBody = world.createBody(circleDef);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(.2f); 
		
		FixtureDef circleFixture = new FixtureDef();
		circleFixture.shape = circleShape;
		circleFixture.density = 0.4f;
		circleFixture.friction = 0.2f;
		circleFixture.restitution = 0.8f;
		
		circleBody.createFixture(circleFixture);
		
		handler = new RayHandler(world);
		handler.setCombinedMatrix(camera);
		
		new PointLight(handler, 5000, Color.CYAN, 1000, 100, 100);
		
	}
	public void render() {
		
		handler.updateAndRender();
		
	}
}
