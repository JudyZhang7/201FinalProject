package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
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
import user.Card;
import user.CreatureCard;
import user.MagicCard;
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
	
	ArrayList<ImageButton> handImages = new ArrayList<ImageButton>();
	ArrayList<ImageButton> GameBoardImages = new ArrayList<ImageButton>();
	private boolean attackInMotion = false;
	private Card yourCardToAttack = null;
	private Card opponentCardToAttack = null;
	
	private Skin skin;
	private FireplacePebble game;
	
	int buttonHeight = 100;
	int buttonWidth = 100;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
	
	public GameBoardPage(FireplacePebble g, ThisGame cg) {
		System.out.println("GAME BOARD!");
		game = g;
		
		// Creating a new player for testing
		Player dummyPlayer = new Player(10);
		// Max 20 cards in a deck, create that deck in the Player
		List<Card> dummyDeck = new ArrayList<Card>();
		// Add 10 GOATS
		CreatureCard dummyCCard = game.creatureCards.get("goat");
		for (int i = 0; i < 10; i++)
		{
			Card toAdd = new CreatureCard(dummyCCard);
			dummyDeck.add(toAdd);
		}
		// Add 10 Libras
		MagicCard dummyMCard = game.magicCards.get("libra");
		for (int i = 0; i < 10; i++)
		{
			Card toAdd = new MagicCard(dummyMCard);
			dummyDeck.add(toAdd);
		}
		// Now, we have a deck with 20 cards. Add it to the Player
		dummyPlayer.set_cardDeck(dummyDeck);
		this.player = dummyPlayer;
		
		currentGame = cg; //THE ACTUAL GAME LOGIC GAME
//		this.player = currentGame.getP1();
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
		
		//stage.setDebugAll(true); // Weird
		
		// DECK BUTTON BELOW
		// Create Deck Button
		Texture DECK_T = new Texture(Gdx.files.internal("Cards/Dog.png")); // Make this deck picture
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
				deckButtonClicked();
			}
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
			{
				return true;
			}
		});
		stage.addActor(DeckButton);
		// DECK BUTTON ABOVE
		
		// HAND BUTTONS BELOW
		// Draw the first 5 cards
		for (int i = 1; i <= 5; i++)
		{
			dummyPlayer.drawCards();
		}
		// Get the Hand
		ArrayList<Card> currHand = dummyPlayer.getmHand();
		// Get the Textures of the cards to output them
		for (int i = 0; i < currHand.size(); i++)
		{
			final Card cardToAddToGameBoard = currHand.get(i);
			Texture currCard = currHand.get(i).getTexture();
			TextureRegion TEMP_C = new TextureRegion(currCard);
			TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
			final ImageButton HandButton = new ImageButton(TEMP_CARD);
			HandButton.setPosition(cw * i + (w/2 + 200), h/12); 
			HandButton.setSize(cw, ch);
			HandButton.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button)
				{
					System.out.println("Hand Button Clicked, Put that card on the Gameboard!");
					HandButtonClicked(cardToAddToGameBoard, HandButton);
				}
				@Override
				public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
				{
					return true;
				}
			});
			handImages.add(HandButton);
		}
		for (int i = 0; i < handImages.size(); i++) 
		{
			stage.addActor(handImages.get(i));
		}
		// HAND BUTTONS ABOVE
		// WHEN USER ENTERS A GAME, HERE ARE THE CARDS DRAWN AND ON THE HAND. NO CARDS ON THE GAMEBOARD YET
		
//		// GameBoard Cards
//		// Get your GameBoard, and get the Opponent's Gameboard
//		ArrayList<Card> yourBoard = currentGame.getmPlayer().getPlayerBoard();
//		ArrayList<Card> opponentBoard = currentGame.getmPlayer().getOpponentBoard();
//		// Get the textures of the cards to output them
//		ArrayList<ImageButton> yourImages = new ArrayList<ImageButton>();
//		ArrayList<ImageButton> opponentImages = new ArrayList<ImageButton>();
//		for (int i = 0; i < yourBoard.size(); i++)
//		{
//			Texture currCard = yourBoard.get(i).getTexture();
//			TextureRegion TEMP_C = new TextureRegion(currCard);
//			TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
//			ImageButton GameBoardButton = new ImageButton(TEMP_CARD);
//			GameBoardButton.setPosition(cw * i + (w/3), h/4);  // Lower
//			GameBoardButton.setSize(cw, ch);
//			GameBoardButton.addListener(new ClickListener() {
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int button)
//				{
//					System.out.println("GameBoard Card Clicked!");
//					GameBoardCardClicked();
//				}
//				@Override
//				public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
//				{
//					return true;
//				}
//			});
//			yourImages.add(GameBoardButton);
//		}
//		for (int i = 0; i < opponentBoard.size(); i++)
//		{
//			Texture currCard = opponentBoard.get(i).getTexture();
//			TextureRegion TEMP_C = new TextureRegion(currCard);
//			TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
//			ImageButton GameBoardButton = new ImageButton(TEMP_CARD);
//			GameBoardButton.setPosition(cw * i + (w/3), h/4);  // Lower
//			GameBoardButton.setSize(cw, ch);
//			GameBoardButton.addListener(new ClickListener() {
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int button)
//				{
//					System.out.println("Enemy GameBoard Card Clicked!");
//					EnemyGameBoardCardClicked();
//				}
//				@Override
//				public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
//				{
//					return true;
//				}
//			});
//			opponentImages.add(GameBoardButton);
//		}
//		for (int i = 0; i < yourImages.size(); i++) 
//		{
//			stage.addActor(yourImages.get(i));
//		}
//		for (int i = 0; i < opponentImages.size(); i++) 
//		{
//			stage.addActor(opponentImages.get(i));
//		}
//		myCards = new ArrayList<ImageButton>(); // This is the arrayList that stores the cards
//		int counter = 0;
//		
//		for (int i = 0; i < numCardsCol; i++) {
////			final Card thisCard = fullDeck[counter]; //why is this final?
////			Texture cardT = new Texture(Gdx.files.internal("Cards/" +thisCard.getImg()));
////			TextureRegion cardTR = new TextureRegion(cardT);
////			TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(cardTR);
////			ImageButton cardButton = new ImageButton(myTexRegionDrawable); //Set the button up
//				
//			Texture TEMP_T = new Texture(Gdx.files.internal("Cards/Pig.png"));
//			TextureRegion TEMP_TR = new TextureRegion(TEMP_T);
//			TextureRegionDrawable TEMP_TRD = new TextureRegionDrawable(TEMP_TR);
//			final ImageButton cardButton = new ImageButton(TEMP_TRD); //Set the button up
//
//			cardButton.setPosition(cw * i + (w/2 + 100), h/12); // Hand Position
//			cardButton.setSize(cw, ch);
//			cardButton.addListener(new ClickListener() {
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int button)
//				{
//					System.out.println("Clicked!");
//					HandButtonClicked(null, cardButton);
//				}
//				@Override
//				public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
//				{
//					return true;
//				}
//			});
//			//currCards.setItems(cardButton);
//			myCards.add(cardButton);
//			counter++; //increment the card
//		}
//		for (int i = 0; i < myCards.size(); i++) {
//			stage.addActor(myCards.get(i));
//		}
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
	
	// Deck Button Clicked
	public void deckButtonClicked()
	{
		// Draw a card from the deck in thisGame and output it on the Screen.
		int currHandSize = currentGame.getCurrentPlayer().getmHand().size();
		if (currHandSize < 5)
		{
			currentGame.getCurrentPlayer().drawCards();
		}
	}
	
	// Hand Button Clicked()
	public void HandButtonClicked(Card cardToAdd, ImageButton handButton)
	{
		// Remove the card from the hand, and
		// Add the card to the Gameboard if the gameboard is not full
//		ArrayList<Card> yourGameBoard = currentGame.getCurrentPlayer().getPlayerBoard();
//		ArrayList<Card> yourHand = currentGame.getCurrentPlayer().getmHand();
		// if Gameboard Size is less than 3, then remove the Card from yourHand, 
		// and add it to your GameBoard
		// Testing
		ArrayList<Integer> yourGameBoard = new ArrayList<Integer>();
		yourGameBoard.add(1);
		yourGameBoard.add(2);
		
		if (yourGameBoard.size() < 3 && GameBoardImages.size() < 3)
		{
//			yourHand.remove(cardToAdd);
//			yourGameBoard.add(cardToAdd);
			handButton.remove();
//			Texture currCard = cardToAdd.getTexture();
			Texture currCard = new Texture(Gdx.files.internal("Cards/Pig.png"));
			TextureRegion TEMP_C = new TextureRegion(currCard);
			TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
			final ImageButton GameBoardButton = new ImageButton(TEMP_CARD);
			if (yourGameBoard.size() == 0) // Nothing on Gameboard
			{
				GameBoardButton.setPosition(cw + (w/4), h/3); // Gameboard 1st Position
				GameBoardButton.setSize(cw, ch);
			}
			else if (yourGameBoard.size() == 1) // 1 card already on Gameboard
			{
				GameBoardButton.setPosition(cw * 3 + (w/4), h/3);  // Gameboard 2nd Position
				GameBoardButton.setSize(cw, ch);
			}
			else if (yourGameBoard.size() == 2) // 2 cards already on Gameboard
			{
				GameBoardButton.setPosition(cw * 5 + (w/4), h/3);  // Gameboard 3rd Position
				GameBoardButton.setSize(cw, ch);
			}
			GameBoardButton.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button)
				{
					System.out.println("GameBoard Button Clicked, User now clicks enemy Gameboard!");
				}
				@Override
				public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
				{
					return true;
				}
			});
			GameBoardImages.add(GameBoardButton);
		}
		for (int i = 0; i < GameBoardImages.size(); i++)
		{
			stage.addActor(GameBoardImages.get(i));
		}
	}
	
	// GameBoard Card Clicked
	public void GameBoardCardClicked(Card yourCard, ImageButton yourButton)
	{
		// Now the user has to click a card on the opponent's Gameboard to attack
		attackInMotion = true;
		yourCardToAttack = yourCard;
	}
	
	// GameBoard Card Clicked
	public void EnemyGameBoardCardClicked(Card opponentCard, ImageButton enemyButton)
	{
		// the user has to click a card on the opponent's Gameboard to attack
		if (attackInMotion)
		{
			opponentCardToAttack = opponentCard;
			//currentGame.Act(yourCardToAttack, opponentCardToAttack);
			// Set the cards to be not null
			currentGame.setCards(yourCardToAttack, opponentCardToAttack);
			if (opponentCardToAttack.isDead())
			{
				enemyButton.remove();
			}
			yourCardToAttack = null;
			opponentCardToAttack = null;
		}
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
