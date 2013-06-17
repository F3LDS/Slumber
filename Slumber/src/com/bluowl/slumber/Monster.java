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
	private TextureAtlas spriteSheet2;
	private TextureAtlas spriteSheet3;
	private TextureAtlas spriteSheet4;
	private TextureAtlas spriteSheet5;
	private Array<Sprite> skeleton;
	private Array<Sprite> left;
	private Array<Sprite> right;
	private Array<Sprite> up;
	private Array<Sprite> down;
	private int	currentFrame = 0;
	private final float	frameLength = 0.17f;	//in seconds, how long a frame last
	private float animationElapsed = 0.0f;
	float charx = 100;
	float chary = 100;
	int direction;
	
	
	
public void create() {
	
	spriteBatch = new SpriteBatch();
	spriteSheet = new TextureAtlas("assets/data/spritesheet.txt");
	spriteSheet2 = new TextureAtlas("assets/data/spritesheet2.txt");
	spriteSheet3 = new TextureAtlas("assets/data/spritesheet3.txt");
	spriteSheet4 = new TextureAtlas("assets/data/spritesheet4.txt");
	spriteSheet5 = new TextureAtlas("assets/data/spritesheet5.txt");
	skeleton = spriteSheet.createSprites("Stand");
	left = spriteSheet2.createSprites("Stand");
	right = spriteSheet3.createSprites("Stand");
	up = spriteSheet4.createSprites("Stand");
	down = spriteSheet5.createSprites("Stand");
	
	for(int i=0; i<skeleton.size; i++){
		skeleton.get(i).setSize(100.0f, 100.0f);
		skeleton.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
	for(int i=0; i<left.size; i++){
		left.get(i).setSize(100.0f, 100.0f);
		left.get(i).setPosition(charx, chary);
	}
	for(int i=0; i<right.size; i++){
		right.get(i).setSize(100.0f, 100.0f);
		right.get(i).setPosition(charx, chary);
	}
	for(int i=0; i<up.size; i++){
		up.get(i).setSize(100.0f, 100.0f);
		up.get(i).setPosition(charx, chary);
	}
	for(int i=0; i<down.size; i++){
		down.get(i).setSize(100.0f, 100.0f);
		down.get(i).setPosition(charx, chary);
	}
	
}
	
public Monster(OrthographicCamera c) {
	
	camera = c;
	
}
public int update() {
	direction = 0;
	
	   if(Gdx.input.isKeyPressed(Keys.A)) {
		      charx -= Gdx.graphics.getDeltaTime() * 150;
		      direction =1;}
		   if(Gdx.input.isKeyPressed(Keys.D)) {
		      charx += Gdx.graphics.getDeltaTime() * 150;
		      direction = 2;}
		   if(Gdx.input.isKeyPressed(Keys.W)) {
		      chary += Gdx.graphics.getDeltaTime() * 150;
		      direction = 3;}
		   if(Gdx.input.isKeyPressed(Keys.S)) {
		      chary -= Gdx.graphics.getDeltaTime() * 150;
		      direction = 4;}
	return direction;
}

public void draw() {

	float dt = Gdx.graphics.getDeltaTime();
	animationElapsed += dt;
	
	
	while(animationElapsed > frameLength){
		animationElapsed -= frameLength;
		currentFrame = (currentFrame == skeleton.size - 1) ? 0 : ++currentFrame;
	}

		for(int i=0; i<skeleton.size; i++){
		skeleton.get(i).setSize(100.0f, 100.0f);
		skeleton.get(i).setPosition(charx, chary);
	}
		
	GL20 gl = Gdx.graphics.getGL20();
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();
	skeleton.get(currentFrame).draw(spriteBatch);
	spriteBatch.end();
	

	

}
public void draw2() {

	float dt = Gdx.graphics.getDeltaTime();
	animationElapsed += dt;
	
	
	while(animationElapsed > frameLength){
		animationElapsed -= frameLength;
		currentFrame = (currentFrame == left.size - 1) ? 0 : ++currentFrame;
	}

		for(int i=0; i<left.size; i++){
		left.get(i).setSize(100.0f, 100.0f);
		left.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
	GL20 gl = Gdx.graphics.getGL20();
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();
	left.get(currentFrame).draw(spriteBatch);
	spriteBatch.end();
	

	

}

public void draw3() {

	float dt = Gdx.graphics.getDeltaTime();
	animationElapsed += dt;
	
	
	while(animationElapsed > frameLength){
		animationElapsed -= frameLength;
		currentFrame = (currentFrame == up.size - 1) ? 0 : ++currentFrame;
	}

		for(int i=0; i<up.size; i++){
		up.get(i).setSize(100.0f, 100.0f);
		up.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
	GL20 gl = Gdx.graphics.getGL20();
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();
	up.get(currentFrame).draw(spriteBatch);
	spriteBatch.end();
	

	

}

public void draw4() {

	float dt = Gdx.graphics.getDeltaTime();
	animationElapsed += dt;
	
	
	while(animationElapsed > frameLength){
		animationElapsed -= frameLength;
		currentFrame = (currentFrame == down.size - 1) ? 0 : ++currentFrame;
	}

		for(int i=0; i<down.size; i++){
		down.get(i).setSize(100.0f, 100.0f);
		down.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
	GL20 gl = Gdx.graphics.getGL20();
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();
	down.get(currentFrame).draw(spriteBatch);
	spriteBatch.end();
	

	

}

public void draw5() {

	float dt = Gdx.graphics.getDeltaTime();
	animationElapsed += dt;
	
	
	while(animationElapsed > frameLength){
		animationElapsed -= frameLength;
		currentFrame = (currentFrame == right.size - 1) ? 0 : ++currentFrame;
	}

		for(int i=0; i<right.size; i++){
		right.get(i).setSize(100.0f, 100.0f);
		right.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
	GL20 gl = Gdx.graphics.getGL20();
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();
	right.get(currentFrame).draw(spriteBatch);
	spriteBatch.end();
	

	

}

}
