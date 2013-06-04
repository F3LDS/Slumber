package com.bluowl.slumber;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Slumber";
		cfg.useGL20 = false;
		cfg.width = 880;
		cfg.height = 620;
		
		new LwjglApplication(new GameClass(), cfg);
	}
}
