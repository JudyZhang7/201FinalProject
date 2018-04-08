package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.FireplacePebble;
import com.mygdx.game.GameBoardPage;
import com.mygdx.game.LoginPage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SignUpPage;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1920;
		config.width = 1080;
		new LwjglApplication(new FireplacePebble(), config);
	}
}
