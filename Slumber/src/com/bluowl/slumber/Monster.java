package com.bluowl.slumber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class Monster {

	static SpriteBatch spriteBatch;
	Texture texture;
	OrthographicCamera camera;
	Sprite sprite;
	private static TextureAtlas spriteSheet;
	private static  TextureAtlas spriteSheet2;
	private static TextureAtlas spriteSheet3;
	private static TextureAtlas spriteSheet4;
	private static  TextureAtlas spriteSheet5;
	private static Array<Sprite> skeleton;
	private static Array<Sprite> left;
	private static  Array<Sprite> right;
	private static  Array<Sprite> up;
	private static Array<Sprite> down;
	private static int	currentFrame = 0;
	private static final float	frameLength = 0.17f;	//in seconds, how long a frame last
	private static float animationElapsed = 0.0f;
	static float charx = 100;
	static float chary = 100;
	int direction;
    GameClass game;
	

	
public static void  create() {
	
	spriteBatch = new SpriteBatch();
	//imports spriteSheets
	spriteSheet = new TextureAtlas("assets/data/spritesheet.txt");//standing anim
	spriteSheet2 = new TextureAtlas("assets/data/spritesheet2.txt");//left anim
	spriteSheet3 = new TextureAtlas("assets/data/spritesheet3.txt");//right anim
	spriteSheet4 = new TextureAtlas("assets/data/spritesheet4.txt");//up anim
	spriteSheet5 = new TextureAtlas("assets/data/spritesheet5.txt");//down anim
	
	//Creates Arrays from spriteSheetes
	skeleton = spriteSheet.createSprites("Stand");
	left = spriteSheet2.createSprites("Stand");
	right = spriteSheet3.createSprites("Stand");
	up = spriteSheet4.createSprites("Stand");
	down = spriteSheet5.createSprites("Stand");
	//Gives all static sizes and positions
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
public void update() {
	//direction indicates which key is being pressed
	
	//receives input and creates keypress status for animations
	 //  if(Gdx.input.isKeyPressed(Keys.A)) {
	//	      direction = 1;
	//	      }
	//	   if(Gdx.input.isKeyPressed(Keys.D)) {
	//	      direction = 2;
	//	      }
	//	   if(Gdx.input.isKeyPressed(Keys.W)) {
	//	      direction = 3;
	//	      }
	//	   if(Gdx.input.isKeyPressed(Keys.S)) {
	//	      direction = 4;
	//	      }
}
//different draw functions for different animations
public static void draw(float x, float y) {
//gets the time
	charx = x;
	chary = y;
	float dt = Gdx.graphics.getDeltaTime();
	animationElapsed += dt;
	
//decides which frame to choose	
	while(animationElapsed > frameLength){
		animationElapsed -= frameLength;
		currentFrame = (currentFrame == skeleton.size - 1) ? 0 : ++currentFrame;
	}
//updates  size and position from original
		for(int i=0; i<skeleton.size; i++){
		skeleton.get(i).setSize(100.0f, 100.0f);
		skeleton.get(i).setPosition(x, y);
	}
		//some open opengl stuff
		
	
	//camera
	//spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();//starts
	skeleton.get(currentFrame).draw(spriteBatch);//draws it
	spriteBatch.end();//closes/ends
	

	

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
	
	
	spriteBatch.setProjectionMatrix(camera.combined);
	spriteBatch.begin();
	right.get(currentFrame).draw(spriteBatch);
	spriteBatch.end();
	

	

}

}
