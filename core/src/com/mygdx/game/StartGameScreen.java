package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartGameScreen implements Screen {
	private Stage stage;
	private Game game;
	private BitmapFont headfont;
	private BitmapFont bodyfont;
	private SpriteBatch batch;
	private int numPlayers;
	
	public StartGameScreen(Game game) {
		this.game = game;
		stage = new Stage();
		batch = new SpriteBatch();
		headfont = new BitmapFont();
		headfont.getData().setScale(3.0f, 3.0f);
		bodyfont = new BitmapFont();
		bodyfont.getData().setScale(1.5f, 1.5f);
		Gdx.input.setInputProcessor(stage);
		//Set numplayers
		numPlayers = 1;
		if (numPlayers > 2) {
			numPlayers = 2;
		}
		
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
		
		//Listeners
		btnViewDeck.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnViewDeckClicked();
			}
		});
		btnViewProfile.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnViewProfileClicked();
			}
		});
		btnStart.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnStartClicked();
			}
		});
		btnViewDeck.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnViewDeckClicked();
			}
		});
		
		//Adding players
		if(numPlayers != 0) {
			TextButton p1 = new TextButton("Simon\n\nLevel 2\n\n0 Wins /10 Losses", textSkin);
			p1.setPosition(75, 400);
			p1.setSize(300, 150);
			stage.addActor(p1);
			
			p1.addListener(new ClickListener(){
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button) {
					btnp1Clicked();
				}
			});
		}
		if(numPlayers == 2) {
			TextButton p2 = new TextButton("", textSkin);
			p2.setPosition(75, 200);
			p2.setSize(300, 150);
			stage.addActor(p2);
			
			p2.addListener(new ClickListener(){
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button) {
					btnp2Clicked();
				}
			});
		}

		stage.addActor(btnViewDeck);
		stage.addActor(btnViewProfile);
		stage.addActor(btnStart);
	}
	
	public void btnViewDeckClicked() {
		
	}
	
	public void btnViewProfileClicked() {
		
	}
	
	public void btnStartClicked() {
		
	}
	
	public void btnp1Clicked() {
		
	}
	
	public void btnp2Clicked() {
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 150/255f, 210/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		batch.begin();
		headfont.draw(batch, "Start a New Game", 475, 675);
		bodyfont.draw(batch, "Players looking for games", 75, 600);
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
