package com.bluowl.slumber;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameClass implements ApplicationListener {
	private OrthographicCamera camera;
	Texture texture;
	Monster monster;
	SpriteBatch spriteBatch;

	
	@Override
	public void create() {	
		spriteBatch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		monster = new Monster(camera);
		monster.create();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		
	    monster.draw();
			  
	    monster.update();
		  
			   
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
