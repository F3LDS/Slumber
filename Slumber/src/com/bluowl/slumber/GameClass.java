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
	float charx = 100f;
	float chary = 100f;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("dude.png"));;
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 128, 128);
		
		sprite = new Sprite(region);
		sprite.setSize(128f, 128f);
		sprite.setPosition(charx, chary);
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
		
		
		
		   if(Gdx.input.isKeyPressed(Keys.A)) 
			      charx -= Gdx.graphics.getDeltaTime() * 150;
			   if(Gdx.input.isKeyPressed(Keys.D)) 
			      charx += Gdx.graphics.getDeltaTime() * 150;
			   if(Gdx.input.isKeyPressed(Keys.W)) 
			      chary += Gdx.graphics.getDeltaTime() * 150;
			   if(Gdx.input.isKeyPressed(Keys.S)) 
			      chary -= Gdx.graphics.getDeltaTime() * 150;
			   
		sprite.setPosition(charx, chary);  
			   
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
