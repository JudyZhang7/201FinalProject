package com.mygdx.game;

import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gamelogic.AchievementThread;

public class GameBoardPage implements Screen {
	private Stage stage;
	private BitmapFont font;
	private TextButton endTurnButton;
	private TextButton yourDeckButton;
	private TextButton opponentDeckButton;
	public static Texture texture;
	public static TextureRegion mainBackground;
	private SpriteBatch spriteBatch = new SpriteBatch();
	
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
		TextButton btnBack = new TextButton ("Quit", skin);
		btnBack.setPosition(w/40, 18*h/20);
		btnBack.setSize(buttonHeight/2, buttonWidth/2);
		btnBack.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnBackClicked();
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		stage.addActor(btnBack);
		
		// Thread to run achievements - Will output the achievement that has been unlocked
		boolean cardPicked = true;
		if (cardPicked)
		{
			game.achievementMap.put("Card Picked!", 1);
			game.achievementMap.put("Played a Game!", 1);
			new AchievementThread(game);
		}
	}
	public void btnBackClicked() {
		game.setScreen(new ProfileScreen(game));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 120/255f, 180/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		texture = new Texture("GamePage.png");
		mainBackground = new TextureRegion(texture, 0, 0, 1920, 1080);
		spriteBatch.begin();
		spriteBatch.draw(mainBackground, 0, 0, w, h);
        spriteBatch.end();
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose () {
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
