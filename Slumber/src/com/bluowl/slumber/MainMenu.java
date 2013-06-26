package com.bluowl.slumber;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen {

	 GameClass game; // Note it's "MyGame" not "Game"
	 private SpriteBatch spriteBatch;
     private Texture splsh;

     // constructor to keep a reference to the main Game class
      public MainMenu(GameClass game){
    	  	  
              this.game = game;
      }
      public void create() {
    	  System.out.println("mainmenucreate\n");
      }
      
  
	@Override
      public void render(float delta) {
              // update and draw stuff
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splsh, 0, 0);
        spriteBatch.end();
           if (Gdx.input.justTouched()) // use your own criterion here
               game.setScreen(game.slumber);
           	
           
      }



     @Override
      public void resize(int width, int height) {
      }


     @Override
      public void show() {
         spriteBatch = new SpriteBatch();
         splsh = new Texture(Gdx.files.internal("assets/data/menu.png"));
           // called when this screen is set as the screen with game.setScreen();
			
      }


     @Override
      public void hide() {
           // called when current screen changes from this to a different screen
      }


     @Override
      public void pause() {
      }


     @Override
      public void resume() {
      }


     @Override
      public void dispose() {
              // never called automatically
      }
}
