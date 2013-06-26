package com.bluowl.slumber;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.ApplicationListener;    
import com.badlogic.gdx.physics.box2d.Fixture;  



public class Slumber implements Screen{

		private OrthographicCamera camera;
		//Texture texture = Gdx.files.internal("assets/data/spritesheet.png");
		Monster monster;
		SpriteBatch spriteBatch;
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
			spriteBatch = new SpriteBatch();
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();

			camera = new OrthographicCamera(w, h);
			//monster = new Monster(camera);
			//monster.create();//creates all the sprites

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
		    if(Gdx.input.isKeyPressed(Keys.D)){
		    	playerBody.applyForceToCenter(10,0);
		    }
		    if(Gdx.input.isKeyPressed(Keys.A)){
		    	playerBody.applyForceToCenter(-10,0);
		    }
		    if(Gdx.input.isKeyPressed(Keys.W)){
		    	playerBody.applyForceToCenter(0,15);
		    	//playerBody.setUserData();
		    }
		    // physics updates

		    Matrix4 cameraCopy = camera.combined.cpy();
		    debugRenderer.render(world, cameraCopy.scl(BOX_TO_WORLD));

		    world.step(1/60f, 6, 2);
			
		    
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
		    debugRenderer = new Box2DDebugRenderer();
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
		    playerShape.setAsBox(50 * WORLD_TO_BOX, 50 * WORLD_TO_BOX);
		    

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
