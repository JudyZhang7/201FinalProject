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
	private Skin endTurnSkin;
	private Skin deckZoneSkin;
	private Game game;
	
	float ENDTURN_X = 2500;
	float ENDTURN_Y = 910;
	float YOURDECK_X = 2700;
	float YOURDECK_Y = 50;
	float OPPONENTDECK_X = 175;
	float OPPONENTDECK_Y = 1620;
	float DECKWIDTH = 180;
	float DECKHEIGHT = 252;
	
	public GameBoardPage(Game g) {
		System.out.println("GAME BOARD!");
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		
		// End Turn button setup
		endTurnSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		endTurnButton = new TextButton("END TURN", endTurnSkin);
		endTurnButton.setPosition(ENDTURN_X, ENDTURN_Y);
		endTurnButton.setSize(500, 100);
		endTurnButton.getLabel().setFontScale(3);
		endTurnButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Debug statement, add backend here
				System.out.println("Turn end!");
			}
		});
		
		// Deck zones setup
		deckZoneSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		yourDeckButton = new TextButton("", deckZoneSkin);
		yourDeckButton.setPosition(YOURDECK_X, YOURDECK_Y);
		yourDeckButton.setSize(DECKWIDTH, DECKHEIGHT);
		endTurnButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Debug statement, add backend here
				System.out.println("My deck pressed!");
			}
		});
		
		opponentDeckButton = new TextButton("", deckZoneSkin);
		opponentDeckButton.setPosition(OPPONENTDECK_X, OPPONENTDECK_Y);
		opponentDeckButton.setSize(DECKWIDTH, DECKHEIGHT);
		endTurnButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Debug statement, add backend here
				System.out.println("Opponent deck pressed!");
			}
		});
		
		// Add all components onto stage
		stage.addActor(endTurnButton);
		stage.addActor(yourDeckButton);
		stage.addActor(opponentDeckButton);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 120/255f, 180/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(0, 0, 3072, 350);
		
		shapeRenderer.setColor(Color.RED);
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
