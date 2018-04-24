package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gamelogic.ThisGame;
import user.ActionCard;
import user.Card;
import user.CreatureCard;
import user.MagicCard;
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
		headfont = game.titlefont128();
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
		
		stage.addActor(btnViewDeck);
		stage.addActor(btnViewProfile);
	}
	
	public void btnViewDeckClicked() {
		game.setScreen(new DeckScreen(game));
	}
	
	public void btnViewProfileClicked() {
		game.setScreen(new ProfileScreen(game));
	}
	
	public void btnStartClicked() {

	}
	
	public Card copyCard(Card thisCard) {
		Card newCard = null;
		if (thisCard.getMytype().equals("creature"))
		{
			newCard = new CreatureCard ((CreatureCard)thisCard);
		}
		else if(thisCard.getMytype().equals("magic")) {
			newCard = new MagicCard ((MagicCard)thisCard);
		}
		else if(thisCard.getMytype().equals("action")) {
			newCard = new ActionCard ((ActionCard)thisCard);
		}
		return newCard;
	}
	
	public List<Card> getCopyOfDefaultDeck(){
		List<Card> newDeck = new ArrayList<Card>();
		for(int i = 0; i < game.getDefaultCardDeck().size(); i++) {
			newDeck.add(copyCard(game.getDefaultCardDeck().get(i)));
		}
		return newDeck;
	}
	
	public void startGameWithComputer() {
		Player computerPlayer = new Player(5);
		List<Card> comDeck = new ArrayList<Card>();

		computerPlayer.set_cardDeck(getCopyOfDefaultDeck());
		//or game could just have a default computer player thing
		if(game.getUser().get_player().get_cardDeck() == null) {
			game.getUser().get_player().set_cardDeck(getCopyOfDefaultDeck()); //get default card deck
		}
		game.setScreen(new GameBoardPage(game, new ThisGame(game.getUser().get_player(), computerPlayer))); // Go to the login page
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
		
		Texture texture = new Texture("startgame.jpg");
		TextureRegion mainBackground = new TextureRegion(texture, 0, 0, 1280, 800);
		batch.begin();
		batch.draw(mainBackground, 0, 0, w, h);
		
		headfont.draw(batch, "Start a New Game", w/8, h/2);
		batch.end();
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
