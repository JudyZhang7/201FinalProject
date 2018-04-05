package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class LoginPage extends ApplicationAdapter {
	Stage stage;
	SpriteBatch batch;
	TextButton loginButton;
	TextButtonStyle loginStyle;
	
	@Override
	public void create () {
		stage = new Stage();
		batch = new SpriteBatch();
		loginStyle = new TextButtonStyle();
		loginButton = new TextButton("LOGIN", loginStyle);
		stage.addActor(loginButton);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 200/255f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		stage.addActor(loginButton);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}