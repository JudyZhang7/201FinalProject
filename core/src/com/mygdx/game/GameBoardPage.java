package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class GameBoardPage extends ApplicationAdapter {
	Stage stage;
	BitmapFont font;
	TextButton endTurnButton;
	TextButtonStyle endTurnButtonStyle;
	Skin endTurnSkin;
	
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		font = new BitmapFont();
		endTurnSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		endTurnButton = new TextButton("END TURN", endTurnSkin);
		endTurnButton.setPosition(2500, 960);
		endTurnButton.setSize(500, 100);
		
		stage.addActor(endTurnButton);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 150/255f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {

	}
}
