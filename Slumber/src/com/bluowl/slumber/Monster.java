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
	down = spriteSheet3.createSprites("Stand");
	right = spriteSheet4.createSprites("Stand");
	up = spriteSheet5.createSprites("Stand");
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

}
//different draw functions for different animations
public static void draw(float x, float y, int z) {
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
		for(int i=0; i<left.size; i++){
		left.get(i).setSize(100.0f, 100.0f);
		left.get(i).setPosition(x, y); // optional: center the sprite to screen
	}
		for(int i=0; i<up.size; i++){
		up.get(i).setSize(100.0f, 100.0f);
		up.get(i).setPosition(x, y); // optional: center the sprite to screen
	}
		for(int i=0; i<down.size; i++){
		down.get(i).setSize(100.0f, 100.0f);
		down.get(i).setPosition(x, y); // optional: center the sprite to screen
	}
		for(int i=0; i<right.size; i++){
		right.get(i).setSize(100.0f, 100.0f);
		right.get(i).setPosition(x, y); // optional: center the sprite to screen
	}
		
	
	//camera
	//spriteBatch.setProjectionMatrix(camera.combined);
	
	spriteBatch.begin();//starts
	if(z==0){
		skeleton.get(currentFrame).draw(spriteBatch);}//draws it
	if(z==2){
		up.get(currentFrame).draw(spriteBatch);}
	if(z==4){
		down.get(currentFrame).draw(spriteBatch);}
	if(z==3){
		right.get(currentFrame).draw(spriteBatch);}
	if(z==1){
		left.get(currentFrame).draw(spriteBatch);}
	spriteBatch.end();//closes/ends
	
	


}

}
