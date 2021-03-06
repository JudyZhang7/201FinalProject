package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.FireplacePebble;
import com.mygdx.game.GameBoardPage;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Fireplace Pebble";
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new FireplacePebble(), config);
	}
}
