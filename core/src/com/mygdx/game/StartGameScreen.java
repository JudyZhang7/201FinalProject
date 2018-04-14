package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import user.Player;

public class StartGameScreen implements Screen {
	private Stage stage;
	private FireplacePebble game;
	private BitmapFont headfont;
	private BitmapFont bodyfont;
	private SpriteBatch batch;
	private int numPlayers;
	int buttonHeight = 200;
	int buttonWidth = 60;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    
	public StartGameScreen(FireplacePebble game) {
		this.game = game;
		stage = new Stage();
		batch = new SpriteBatch();
		headfont = game.titlefont64();
		bodyfont = game.regfont20();
		Gdx.input.setInputProcessor(stage);
		//Set numplayers
		numPlayers = 1;
		if (numPlayers > 2) {
			numPlayers = 2;
		}
		
		//Placeholder skins
		Skin textSkin = new Skin(Gdx.files.internal(game.getSkin()));
		
		//View my deck button
		TextButton btnViewDeck = new TextButton("View My Deck", textSkin);
		btnViewDeck.setPosition(3*w/4, 5*h/6);
		btnViewDeck.setSize(225, 75);
		
		//View my profile button
		TextButton btnViewProfile = new TextButton("View My Profile", textSkin);
		btnViewProfile.setPosition(3*w/4, 4*h/6);
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
		
		//PLAY WITH COMPUTER
		TextButton computer = new TextButton("Play with Computer", textSkin);
		computer.setPosition(w/14, 4*h/6);
		computer.setSize(200, 60);
		stage.addActor(computer);
		
		computer.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				startGameWithComputer();
			}
		});
		
//	Adding players
//		if(numPlayers != 0) {
//			TextButton p1 = new TextButton("", textSkin);
//			p1.setPosition(w/4, h/2);
//			p1.setSize(300, 150);
//			stage.addActor(p1);
//			
//			p1.addListener(new ClickListener(){
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int button) {
//					btnp1Clicked();
//				}
//			});
//		}
//		if(numPlayers == 2) { //what's this?
//			TextButton p2 = new TextButton("", textSkin);
//			p2.setPosition(w/4, h/3);
//			p2.setSize(300, 150);
//			stage.addActor(p2);
//			
//			p2.addListener(new ClickListener(){
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int button) {
//					btnp2Clicked();
//				}
//			});
//		}
		
		stage.addActor(btnViewDeck);
		stage.addActor(btnViewProfile);
		stage.addActor(btnStart);
	}
	

	public void btnViewDeckClicked() {
		game.setScreen(new DeckScreen(game));
	}
	
	public void btnViewProfileClicked() {
		game.setScreen(new ProfileScreen(game));
	}
	
	public void btnStartClicked() {
		Player computerPlayer = new Player();
		game.setScreen(new GameBoardPage(game, new Game(game.getUser().get_player(), computerPlayer))); // Go to the login page
	}
	
	public void startGameWithComputer() {
		
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
		Gdx.gl.glClearColor(0, 100/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		stage.act(delta);
		stage.draw();
		batch.begin();
		headfont.draw(batch, "Start a New Game", w/3, 5*h/6);
		bodyfont.draw(batch, "Players looking for games", w/13, 7*h/9);
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
