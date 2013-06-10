package com.bluowl.slumber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Monster {

	SpriteBatch batch;
	Texture texture;
	Sprite sprite;
	
	float charx = 100;
	float chary = 100;
	
	//batch = new SpriteBatch();
	
public Monster(Texture t) {
	
	texture = t;
	
}
public void update() {
	
	   if(Gdx.input.isKeyPressed(Keys.A)) 
		      charx -= Gdx.graphics.getDeltaTime() * 150;
		   if(Gdx.input.isKeyPressed(Keys.D)) 
		      charx += Gdx.graphics.getDeltaTime() * 150;
		   if(Gdx.input.isKeyPressed(Keys.W)) 
		      chary += Gdx.graphics.getDeltaTime() * 150;
		   if(Gdx.input.isKeyPressed(Keys.S)) 
		      chary -= Gdx.graphics.getDeltaTime() * 150;
	
}

public void draw(SpriteBatch spriteBatch) {

	//batch.setProjectionMatrix(camera.combined);

	
	spriteBatch.draw(texture,charx,chary);
	
}

}
