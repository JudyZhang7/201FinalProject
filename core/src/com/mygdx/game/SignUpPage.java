package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class SignUpPage extends ApplicationAdapter {
	Stage stage;
	SpriteBatch batch;
	TextButton signupButton;
	TextButtonStyle signupStyle;
	
	@Override
	public void create () {
		stage = new Stage();
		batch = new SpriteBatch();
		signupStyle = new TextButtonStyle();
		signupButton = new TextButton("SIGNUP", signupStyle);
		stage.addActor(signupButton);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 150/255f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		stage.addActor(signupButton);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
