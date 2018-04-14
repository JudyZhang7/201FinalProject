package com.mygdx.game;

import java.util.ArrayList;
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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import gamelogic.AchievementThread;
import gamelogic.ThisGame;
import user.Player;
import user.User;

public class GameBoardPage implements Screen {
	//THE ACTUAL GAME OBJECT
	private ThisGame currentGame;
	//private ArrayList<Card>
	//THE ACTUAL GAME OBJECT ^^^
	private Player player;
	private Player otherPlayer;
	private Stage stage = new Stage();
	private BitmapFont font;
	private TextButton yourDeckButton;
	private TextButton opponentDeckButton;
	public static Texture texture;
	public static TextureRegion mainBackground;
	private SpriteBatch batch = new SpriteBatch();
	private ArrayList<ImageButton> myCards;
	private int numCardsRow = 1;
	private int numCardsCol = 3;
	private int ch = 125;
	private int cw = 125;
	
	private Skin skin;
	private FireplacePebble game;
	
	int buttonHeight = 100;
	int buttonWidth = 100;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
	
	public GameBoardPage(FireplacePebble g, ThisGame cg) {
		System.out.println("GAME BOARD!");
		game = g;
		
		currentGame = cg; //THE ACTUAL GAME LOGIC GAME
		this.player = currentGame.getP1();
		this.otherPlayer = currentGame.getP2();
		skin = new Skin(Gdx.files.internal(game.getSkin()));
        font = game.regfont32();
        font.setColor(Color.BLACK);
		Gdx.input.setInputProcessor(stage);		
		// End Turn button setup
		TextButton endTurnButton = new TextButton("END TURN", skin);
		endTurnButton.setPosition(11*w/12, h/2);
		endTurnButton.setSize(buttonWidth, buttonHeight);
				
		endTurnButton.addListener(new ClickListener() {
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				System.out.println("End turn bttn");
				endTurnButtonClicked();
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
		
		myCards = new ArrayList<ImageButton>(); // This is the arrayList that stores the cards
		
		//stage.setDebugAll(true); // Weird
		
		// Create Deck Button
		
		Texture DECK_T = new Texture(Gdx.files.internal("Cards/Dog.png"));
		TextureRegion DECK_TR = new TextureRegion(DECK_T);
		TextureRegionDrawable DECK_TRD = new TextureRegionDrawable(DECK_TR);
		ImageButton DeckButton = new ImageButton(DECK_TRD);
		
		// Set position of Deck Button to Bottom Right Corner and Clickable
		System.out.println("w in this computer is: " + w);
		System.out.println("h in this computer is: " + h);
		DeckButton.setPosition(cw + (w/2 + 400), h/12); // Should edit to fit others' resolution
		DeckButton.setSize(cw, ch);
		DeckButton.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button)
			{
				System.out.println("Deck Clicked, Draw a Card!");
			}
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
			{
				return true;
			}
		});
		stage.addActor(DeckButton);
		
		int counter = 0;
		
		for (int i = 0; i < numCardsCol; i++) {
//			final Card thisCard = fullDeck[counter]; //why is this final?
//			Texture cardT = new Texture(Gdx.files.internal("Cards/" +thisCard.getImg()));
//			TextureRegion cardTR = new TextureRegion(cardT);
//			TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(cardTR);
//			ImageButton cardButton = new ImageButton(myTexRegionDrawable); //Set the button up
				
			Texture TEMP_T = new Texture(Gdx.files.internal("Cards/Pig.png"));
			TextureRegion TEMP_TR = new TextureRegion(TEMP_T);
			TextureRegionDrawable TEMP_TRD = new TextureRegionDrawable(TEMP_TR);
			ImageButton cardButton = new ImageButton(TEMP_TRD); //Set the button up
			/*if (i == 1) // Dog
			{
				cardButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Cards/Dog.png")))));
			}*/
			cardButton.setPosition(cw * i + 550, h/4 + 50); // Should edit to fit others' resolution
			cardButton.setSize(cw, ch);
			//currCards.setItems(cardButton);
			myCards.add(cardButton);
			counter++; //increment the card
		}
		for (int i = 0; i < myCards.size(); i++) {
			stage.addActor(myCards.get(i));
		}
	}
	public void btnBackClicked() {
		game.setScreen(new ProfileScreen(game));
	}
	
	public void endTurnButtonClicked() {
		batch.begin();
		BitmapFont titleFont64 = game.titlefont64();
		titleFont64.draw(batch, "Healthfds - " + (player.get_hp()-1), w/2, (h)/2);
		font.draw(batch, "Mana - " + player.get_mana(), w/8, (h)/6);
		
		//OPPONENT
		font.draw(batch, "Health - " + otherPlayer.get_hp(), 6*w/8, (5*h)/6 + buttonHeight/2);
		font.draw(batch, "Mana - " + otherPlayer.get_mana(), 6*w/8, (5*h)/6);
		batch.end();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 120/255f, 180/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		texture = new Texture("GamePage.png");
		mainBackground = new TextureRegion(texture, 0, 0, 1920, 1080);

		player = game.getUser().get_player();
		otherPlayer = game.getUser().get_player();
		
		batch.begin();
		batch.draw(mainBackground, 0, 0, w, h);
		
		font.draw(batch, "Health - " + player.get_hp(), w/8, (h)/6 + buttonHeight/2);
		font.draw(batch, "Mana - " + player.get_mana(), w/8, (h)/6);
		
		//OPPONENT
		font.draw(batch, "Health - " + otherPlayer.get_hp(), 6*w/8, (5*h)/6 + buttonHeight/2);
		font.draw(batch, "Mana - " + otherPlayer.get_mana(), 6*w/8, (5*h)/6);
		
		batch.end();
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
