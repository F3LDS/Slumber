package com.bluowl.slumber;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClass extends Game {
	
    MainMenu mainmenu;
    Slumber slumber;


   @Override
    public void create() {
            mainmenu = new MainMenu(this);
            slumber = new Slumber(this);
            setScreen(mainmenu);              
    }
}
