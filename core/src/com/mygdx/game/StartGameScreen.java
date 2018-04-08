package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StartGameScreen implements Screen {
	private Stage stage;
	private Game game;
	private BitmapFont font;
	private SpriteBatch batch;
	
	public StartGameScreen(Game game) {
		this.game = game;
		stage = new Stage();
		batch = new SpriteBatch();
		font = new BitmapFont();
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
		
		//Start button
		TextButton btnStart = new TextButton("Start", textSkin);
		btnStart.setPosition(550, 350);
		btnStart.setSize(200, 50);
		
		//
		
		stage.addActor(btnViewDeck);
		stage.addActor(btnViewProfile);
		stage.addActor(btnStart);
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
		batch.begin();
		font.draw(batch, "Start a New Game", 550, 675);
		font.draw(batch, "Players looking for games", 75, 600);
		batch.end();
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
