package com.bluowl.slumber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Monster {

	static SpriteBatch spriteBatch;
	Texture texture;
	OrthographicCamera camera;
	Sprite sprite;
	private BodyDef playerDef;
	private Body playerBody;
	private World world;
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
	final float WORLD_TO_BOX = 0.01f;
	final float BOX_TO_WORLD = 100f;
	int pos_status;
    GameClass game;
	

	
public void create() {

	pos_status = 0;//because we want him to be standing initially
	
/*****************************************************************************************
							DEFINE PHYSICS OBJECTS
*****************************************************************************************/  
	
    playerDef = new BodyDef();
    playerDef.type = BodyType.DynamicBody;
    playerDef.position.set(new Vector2(100 * WORLD_TO_BOX, 400 * WORLD_TO_BOX));
    
    playerBody = world.createBody(playerDef);
   	playerBody.setFixedRotation(true);
   	
   	PolygonShape playerShape = new PolygonShape();
    playerShape.setAsBox(50 * WORLD_TO_BOX ,50 * WORLD_TO_BOX);
   
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = playerShape;
    fixtureDef.density = 0.5f; 
    fixtureDef.friction = 0.6f;
    fixtureDef.restitution = 0.0f;
    
    Fixture fixture = playerBody.createFixture(fixtureDef);

    playerShape.dispose();

/*****************************************************************************************
						SPRITE BATCHES FOR ANIMATION SWITCHING
*****************************************************************************************/   
    
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
	
public Monster(OrthographicCamera c, World w) {
	//pass our camera and world to the monster
	camera = c;
	world = w;
	
}
public void update() {

}

public void draw() {
	pos_status = 0; //resets to standing animation when no button is pressed
	
	//Set our sprite to be on top of our physics body
	charx = playerBody.getPosition().x * BOX_TO_WORLD - 50;
	chary = playerBody.getPosition().y * BOX_TO_WORLD - 50;
	
	//set a standard of time passing
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
		skeleton.get(i).setPosition(charx, chary);
	}
		for(int i=0; i<left.size; i++){
		left.get(i).setSize(100.0f, 100.0f);
		left.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
		for(int i=0; i<up.size; i++){
		up.get(i).setSize(100.0f, 100.0f);
		up.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
		for(int i=0; i<down.size; i++){
		down.get(i).setSize(100.0f, 100.0f);
		down.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
		for(int i=0; i<right.size; i++){
		right.get(i).setSize(100.0f, 100.0f);
		right.get(i).setPosition(charx, chary); // optional: center the sprite to screen
	}
		
		
/*****************************************************************************************
									USER INPUT
*****************************************************************************************/  
		
	    if(Gdx.input.isKeyPressed(Keys.D)){
	    	if(playerBody.getLinearVelocity().x < 2){
	    	playerBody.applyForceToCenter(new Vector2(10,0), true);
	    	}
	    	pos_status = 3;
	    }
	    if(Gdx.input.isKeyPressed(Keys.A)){
	    	if(playerBody.getLinearVelocity().x > -2){
	    	playerBody.applyForceToCenter(new Vector2(-10,0), true);
	    	}
	    	pos_status = 1;
	    }
	    if(Gdx.input.isKeyPressed(Keys.W)){
	    	if(playerBody.getLinearVelocity().y ==0){
	    	playerBody.applyForceToCenter(new Vector2(0,200), true);
	    	}
	    	pos_status = 2;
	    }
	    if(Gdx.input.isKeyPressed(Keys.S)){
	    	pos_status = 4;
	    }
	    
	    if((!Gdx.input.isKeyPressed(Keys.A)) && (!Gdx.input.isKeyPressed(Keys.D) && playerBody.getLinearVelocity().y ==0)){
	    	playerBody.setLinearVelocity(0, 0);
	    	
	    }

	spriteBatch.begin();//starts
	if(pos_status==0){
		skeleton.get(currentFrame).draw(spriteBatch);}//draws it
	if(pos_status==2){
		up.get(currentFrame).draw(spriteBatch);}
	if(pos_status==4){
		down.get(currentFrame).draw(spriteBatch);}
	if(pos_status==3){
		right.get(currentFrame).draw(spriteBatch);}
	if(pos_status==1){
		left.get(currentFrame).draw(spriteBatch);}
	spriteBatch.end();//closes/ends
	
	}

}
