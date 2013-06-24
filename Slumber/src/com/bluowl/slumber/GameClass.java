package com.bluowl.slumber;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameClass extends Game {
	
    MainMenu mainmenu;
    Slumber slumber;
    

   @Override
    public void create() {
	   System.out.print("create\n");
	   GL20 gl = Gdx.graphics.getGL20();
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            mainmenu = new MainMenu(this);
            slumber = new Slumber(this);
            setScreen(mainmenu);     
            slumber.create();
    }
   /*public void render(){
	   System.out.print("render");
	   GL20 gl = Gdx.graphics.getGL20();
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	   //super.render();
   }*/
}
