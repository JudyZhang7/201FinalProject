package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameBoardPage extends ApplicationAdapter {
	Stage stage;
	BitmapFont font;
	TextButton endTurnButton;
	TextButtonStyle endTurnButtonStyle;
	Skin endTurnSkin;
	float ENDTURN_X = 2500;
	float ENDTURN_Y = 960;
	
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		font = new BitmapFont();
		endTurnSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		endTurnButton = new TextButton("END TURN", endTurnSkin);
		endTurnButton.setPosition(ENDTURN_X, ENDTURN_Y);
		endTurnButton.setSize(500, 100);
		endTurnButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Debug statement, add backend here
				System.out.println("Turn end!");
			}
		});
		
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
