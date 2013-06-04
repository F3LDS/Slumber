package com.bluowl.slumber;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameClass implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float charx;
		float chary;
		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("dude.png"));;
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 128, 128);
		
		sprite = new Sprite(region);
		sprite.setSize(128f, 128f);
		sprite.setPosition(100, 100);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		
		
		//   if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
		//	      charx -= Gdx.graphics.getDeltaTime();
		//	   if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
		//	      charx += Gdx.graphics.getDeltaTime();
		//	   if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) 
		//	      chary += Gdx.graphics.getDeltaTime();
		//	   if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) 
		//	      chary -= Gdx.graphics.getDeltaTime();
			   
			   
			   
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
