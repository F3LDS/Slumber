package com.bluowl.slumber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;



public class Slumber implements Screen{
		GameClass game;
		private Platform ground;
		private OrthographicCamera camera;
		private Matrix4 cameraCopy;
		private World world;
		private Box2DDebugRenderer debugRenderer;
		private static final float WORLD_TO_BOX = 0.01f;
		private static final float BOX_TO_WORLD = 100f; 
		private Monster dude;
		private LightSource light;

	
		public Slumber(GameClass game) {
			//pass on our game class to this screen
			this.game = game;
		}
		
		
/*****************************************************************************************
					INITIALIZATION - called on screen switch in
*****************************************************************************************/
		@Override
	    public void show() {
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();
			
			camera = new OrthographicCamera(w, h); //The main camera
			Gdx.input.setCursorCatched(true); //Catches the cursor while gameplay is active
			camera.setToOrtho(false);
			cameraCopy = camera.combined.cpy(); //create a copy of the camera as a Matrix4 for use with box2d
			
		    world = new World(new Vector2(0, -10), true);
		    debugRenderer = new Box2DDebugRenderer(); //Allows us to see the physics objects outlined
		    
		    //use our Platform class to create the ground
		    ground = new Platform();
		    ground.create(world, 300, 36, 100, 100,true); //make a platform with (World, width, height, x, y, (is the ground));
		    
		    //create a new instance of our player
		    dude = new Monster(camera, world);
		    dude.create();
		    
		    //create a new instance of our lighting class
		    light = new LightSource();
		    light.create(world, cameraCopy.scl(BOX_TO_WORLD)); //we send out Matrix4 camera scaled properly for box2d
		    
		    Sounds.music.play(); //PREPARE TO SHIT YOUR PANTS
	    }


		public void render(float delta) {	
			GL20 gl = Gdx.graphics.getGL20(); //Clear the screen
			gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		    camera.update();

		    world.step(1/60f, 6, 2); //sets our FPS and general speed of the game

			dude.draw(); //draw our player
		    light.render(); //draw this light
		    
		    debugRenderer.render(world, cameraCopy.scl(BOX_TO_WORLD)); //run the debug renderer so we can see outlines of our bodies
		    
		    //This allows us to ESCAPE to get back to the menu screen
		    if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			   game.setScreen(game.mainmenu);
		    }
		}
	    
/*****************************************************************************************
					END - called on screen switch out
*****************************************************************************************/
		@Override
		// called when current screen changes from this to a different screen
	    public void hide() {
			Gdx.input.setCursorCatched(false);
			Sounds.music.pause();

	    }
		
		@Override
		public void dispose() {
			
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