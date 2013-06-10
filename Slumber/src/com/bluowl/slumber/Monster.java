package com.bluowl.slumber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class Monster {

	SpriteBatch spriteBatch;
	Texture texture;
	OrthographicCamera camera;
	Sprite sprite;
	private TextureAtlas spriteSheet;
	private Array<Sprite> skeleton;
	private int	currentFrame = 0;
	private final float	frameLength = 0.17f;	//in seconds, how long a frame last
	private float animationElapsed = 0.0f;
	float charx = 100;
	float chary = 100;
	
	
	
public void create() {
	
	spriteBatch = new SpriteBatch();
	spriteSheet = new TextureAtlas("assets/data/spritesheet.txt");
	skeleton = spriteSheet.createSprites("Stand");
	
	for(int i=0; i<skeleton.size; i++){
		skeleton.get(i).setSize(33.0f, 33.0f);
		skeleton.get(i).setPosition(-1.5f, -1.5f); // optional: center the sprite to screen
	}
	
	//batch = new SpriteBatch();
}
	
public Monster(OrthographicCamera c) {
	
	camera = c;
	
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

public void draw() {

	//batch.setProjectionMatrix(camera.combined);
	float dt = Gdx.graphics.getDeltaTime();
	animationElapsed += dt;
	
	
	while(animationElapsed > frameLength){
		animationElapsed -= frameLength;
		currentFrame = (currentFrame == skeleton.size - 1) ? 0 : ++currentFrame;
	}

	
	//spriteBatch.draw(texture,charx,chary);
	GL20 gl = Gdx.graphics.getGL20();
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();
	skeleton.get(currentFrame).draw(spriteBatch);
	spriteBatch.end();
	
}

}
