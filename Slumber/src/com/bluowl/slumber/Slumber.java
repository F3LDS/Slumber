package com.bluowl.slumber;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Slumber implements Screen{

		private OrthographicCamera camera;
		Texture texture;
		Monster monster;
		SpriteBatch spriteBatch;
		int pos_status = 0;//because we want him to be standing initially

		GameClass game;
		
		public void create() {	
			spriteBatch = new SpriteBatch();
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();

			camera = new OrthographicCamera(w, h);
			monster = new Monster(camera);
			monster.create();//creates all the sprites
		}

		@Override
		public void dispose() {
			
		}
		
		public Slumber(GameClass game) {
			this.game = game;
		}

		public void render(float delta) {	
			//first time going through it will be zero because we set it that
			if(pos_status==0){
				monster.draw();
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
		   pos_status = monster.update();
			  //after looping through again it'll change if a key is pressed
		   //you'll be able to tell by the system print out in the console
				   
		}
	    
		@Override
	    public void show() {
	             // called when this screen is set as the screen with game.setScreen();
	    }
		@Override
	    public void hide() {
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
