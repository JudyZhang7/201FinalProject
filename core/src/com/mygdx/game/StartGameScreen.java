package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StartGameScreen implements Screen {
	Stage stage;
	Game game;
	
	public StartGameScreen(Game game) {
		this.game = game;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		//Placeholder skins
		Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		//View my deck button
		TextButton btnViewDeck = new TextButton("View My Deck", textSkin);
		btnViewDeck.setPosition(950, 600);
		btnViewDeck.setSize(225, 75);
		
		//View my profile button
		TextButton btnViewProfile = new TextButton("View My Profile", textSkin);
		btnViewProfile.setPosition(950, 500);
		btnViewProfile.setSize(225, 75);
		
		
		stage.addActor(btnViewDeck);
		stage.addActor(btnViewProfile);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
