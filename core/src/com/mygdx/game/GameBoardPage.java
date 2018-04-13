package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameBoardPage implements Screen {
	private Stage stage;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
	private TextButton endTurnButton;
	private TextButton yourDeckButton;
	private TextButton opponentDeckButton;
	private Skin skin;
	private FireplacePebble game;
	
	int buttonHeight = 100;
	int buttonWidth = 100;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
	
	public GameBoardPage(FireplacePebble g) {
		System.out.println("GAME BOARD!");
		game = g;
		stage = new Stage();
		skin = new Skin(Gdx.files.internal(game.getSkin()));
		Gdx.input.setInputProcessor(stage);
		
		shapeRenderer = new ShapeRenderer();
		
		// End Turn button setup
		endTurnButton = new TextButton("END TURN", skin);
		endTurnButton.setPosition(11*w/12, h/2);
		endTurnButton.setSize(buttonWidth, buttonHeight);
				
		endTurnButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Debug statement, add backend here
				System.out.println("Turn end!");
			}
		});
		
//		// Deck zones setup
//		yourDeckButton = new TextButton("", skin);
//		yourDeckButton.setPosition(w/2, h/2);
//		yourDeckButton.setSize(buttonWidth, buttonHeight);
//		endTurnButton.addListener(new ClickListener() {
//			public void clicked(InputEvent event, float x, float y) {
//				// Debug statement, add backend here
//				System.out.println("My deck pressed!");
//			}
//		});
//		
//		opponentDeckButton = new TextButton("", skin);
//		opponentDeckButton.setPosition(w/3, h/3);
//		opponentDeckButton.setSize(buttonWidth, buttonHeight);
//		endTurnButton.addListener(new ClickListener() {
//			public void clicked(InputEvent event, float x, float y) {
//				// Debug statement, add backend here
//				System.out.println("Opponent deck pressed!");
//			}
//		});
		
		// Add all components onto stage
		stage.addActor(endTurnButton);
//		stage.addActor(yourDeckButton);
//		stage.addActor(opponentDeckButton);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 120/255f, 180/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(0, 180/255f, 0, 1);
		shapeRenderer.rect(0, 0, 3072, 350);
		
		shapeRenderer.setColor(180/255f, 0, 0, 1);
		shapeRenderer.rect(0, 1580, 3072, 350);
		
		shapeRenderer.end();
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
}
