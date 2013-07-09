package com.bluowl.slumber;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;


public class LightSource {

	RayHandler handler;
	final float WORLD_TO_BOX = 0.01f; //infamous conversion numbers
	final float BOX_TO_WORLD = 100f;
	
	public void create(World world, Matrix4 camera) {

		handler = new RayHandler(world);
		handler.setCombinedMatrix(camera); //Set which camera the ray handler will operate under
		handler.setAmbientLight(0.1f); //Number between 0-1 describing brightness in the shadows
		
		//create a new light (RayHandler, # of rays, color, spread, x, y)
		new PointLight(handler, 5000, Color.WHITE, 10, 700 * WORLD_TO_BOX, 300 * WORLD_TO_BOX);
		
	}
	public void render() {
		
		//Combines updating the rays and rendering into one tidy call
		handler.updateAndRender();
		
	}
}
