package com.bluowl.slumber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;



public class Slumber implements Screen{

		private OrthographicCamera camera;
		//Texture texture = Gdx.files.internal("assets/data/spritesheet.png");
		Monster monster;
		
		int pos_status = 0;//because we want him to be standing initially
		private World world;
		private Box2DDebugRenderer debugRenderer;
		private static final float WORLD_TO_BOX = 0.01f;
		private static final float BOX_TO_WORLD = 100f; 
		private BodyDef groundDef;
		private Body groundBody;
		private BodyDef playerDef;
		private Body playerBody;
		
		

		GameClass game;
		
		public void create() {	
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();

			camera = new OrthographicCamera(w, h);
			
			monster.create();

		}

		@Override
		public void dispose() {
			
		}
		

		public Slumber(GameClass game) {
			this.game = game;
		}


		public void render(float delta) {	
			

			
			
			GL20 gl = Gdx.graphics.getGL20();
			gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		    camera.update();
		    Matrix4 cameraCopy = camera.combined.cpy();
		    //debugRenderer.render(world, cameraCopy.scl(BOX_TO_WORLD));

		    world.step(1/60f, 6, 2);
			int x = (int) playerBody.getPosition().x;
			int y = (int) playerBody.getPosition().y;
		    if(Gdx.input.isKeyPressed(Keys.D)){
		    	if(playerBody.getLinearVelocity().x < 2){
		    	playerBody.applyForceToCenter(10,0);
		    	}
		    }
		    if(Gdx.input.isKeyPressed(Keys.A)){
		    	if(playerBody.getLinearVelocity().x > -2){
		    	playerBody.applyForceToCenter(-10,0);
		    	}
		    	pos_status = 1;
		    }
		    if(Gdx.input.isKeyPressed(Keys.W)){
		    	if(playerBody.getLinearVelocity().y ==0){
		    	playerBody.applyForceToCenter(0,200);
		    	}
		    	//playerBody.setUserData();
		    }
		    if((!Gdx.input.isKeyPressed(Keys.A)) && (!Gdx.input.isKeyPressed(Keys.D) && playerBody.getLinearVelocity().y ==0)){
		    	playerBody.setLinearVelocity(0, 0);
		    }
		    // physics updates
		    
		    
			if(pos_status==0){
			monster.draw((playerBody.getPosition().x * BOX_TO_WORLD) - 50, (playerBody.getPosition().y * BOX_TO_WORLD) - 50);
			System.out.println("Static");
			}
			if(pos_status==1){
			monster.draw2();
			System.out.println("left");
			}
			if(pos_status==2){
			monster.draw3();
			System.out.println("up");
			}
			if(pos_status==3){
			monster.draw4();
			System.out.println("right");
			}
			if(pos_status==4){
			monster.draw5();
			System.out.println("down");
			}

			
		    
			//draws the direction variable and sets it to this pos_status 
		   //pos_status = monster.update();
		   if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			   game.setScreen(game.mainmenu);
		   }

			  
		//after looping through again it'll change if a key is pressed
		   //you'll be able to tell by the system print out in the console

		}
	    
		@Override
	    public void show() {
			Gdx.input.setCursorCatched(true);
			camera = new OrthographicCamera();
			camera.setToOrtho(false);
		    world = new World(new Vector2(0, -10), true);
		    //debugRenderer = new Box2DDebugRenderer();
		    groundDef = new BodyDef();
		    groundDef.position.set(new Vector2((Gdx.graphics.getWidth() / 2) * WORLD_TO_BOX, 16f * WORLD_TO_BOX));
		    groundBody = world.createBody(groundDef);
		    PolygonShape groundShape = new PolygonShape();
		    groundShape.setAsBox((Gdx.graphics.getWidth() / 2) * WORLD_TO_BOX, 16f * WORLD_TO_BOX);
		    groundBody.createFixture(groundShape, 0f);
		    groundShape.dispose();

		    // the player box

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
           
		    Sounds.music.play();
	             // called when this screen is set as the screen with game.setScreen();
	    }
		@Override
	    public void hide() {
			Gdx.input.setCursorCatched(false);
			Sounds.music.pause();

	             // called when current screen changes from this to a different screen
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
