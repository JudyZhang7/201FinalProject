package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import user.*;

public class GameBoardPage implements Screen {
	//THE ACTUAL GAME OBJECT
	private ThisGame currentGame;
	//private ArrayList<Card>
	//THE ACTUAL GAME OBJECT ^^^
	private Player player;
	private Player otherPlayer;
	private boolean playerTurn;
	private boolean opponentTurn;
	private int opponentAttackCount = 0;
	
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
	ArrayList<ImageButton> opponentImages = new ArrayList<ImageButton>();
	private int numTurnsSoFar = 0; // Counting the number of turns so far
	private boolean attackInMotion = false;
	private CreatureCard yourCard = null;
	private CreatureCard opponentCardToAttack = null;
	
	private Skin skin;
	private FireplacePebble game;
	ShapeRenderer sr = new ShapeRenderer();

	
	int buttonHeight = 100;
	int buttonWidth = 100;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
	
	public GameBoardPage(FireplacePebble g, ThisGame cg) {
		System.out.println("GAME BOARD!");
		game = g;
		currentGame = cg; //THE ACTUAL GAME LOGIC GAME
		
		// Creating a new player for testing
		player = cg.getP1();
		otherPlayer = cg.getP2();
//		player = new Player(10);
//		otherPlayer = new Player(10);
//		
		final Player p1 = player;
		final Player p2 = otherPlayer;
//		game.user = new User(p1);
//		game.oppo = new User(p2);
//		final User myUser = game.user;
//		final User yourUser = game.oppo;
//		// Max 20 cards in a deck, create that deck in the Player
//		List<Card> dummyDeck = new ArrayList<Card>();
//		List<Card> opponentDummyDeck = new ArrayList<Card>();
//		// Add 10 GOATS
//		CreatureCard dummyCCard = game.creatureCards.get("goat");
//		for (int i = 0; i < 10; i++)
//		{
//			Card toAdd = new CreatureCard(dummyCCard);
//			dummyDeck.add(toAdd);
//			opponentDummyDeck.add(toAdd);
//		}
//		// Add 10 Libras
//		MagicCard dummyMCard = game.magicCards.get("libra");
//		for (int i = 0; i < 10; i++)
//		{
//			Card toAdd = new MagicCard(dummyMCard);
//			dummyDeck.add(toAdd);
//			opponentDummyDeck.add(toAdd);
//		}
//		// Now, we have a deck with 20 cards. Add it to the Player
//		player.set_cardDeck(dummyDeck);
//		otherPlayer.set_cardDeck(opponentDummyDeck);
		//List<Card> a = dummyPlayer.get_cardDeck();
		// Hard Set it to this player's turn first
		playerTurn = true;
		opponentTurn = false;
		
		
//		this.player = currentGame.getP1();
//		this.otherPlayer = currentGame.getP2();
		final ThisGame gg = currentGame;
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
				endTurnButtonClicked(p1, p2, gg);
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
		
		// DECK BUTTON BELOW
		// Create Deck Button
		// Make a deck picture
		final Player forDeck = player;
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
				deckButtonClicked(forDeck);
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
		// Draw the first 3 cards
		for (int i = 1; i <= 3; i++)
		{
			player.drawCards();
		}
		// Get the Hand
		ArrayList<Card> currHand = player.getmHand();
//		System.out.println(currHand.size());
		// Get the Textures of the cards to output them
		for (int i = 0; i < currHand.size(); i++)
		{
			final Player p = player; // Must be final?
			final Card cardToAddToGameBoard = currHand.get(i);
			Texture currCard = currHand.get(i).getTexture();
			TextureRegion TEMP_C = new TextureRegion(currCard);
			TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
			final ImageButton HandButton = new ImageButton(TEMP_CARD);
			// Set position of the Hand to be next to the Deck
			HandButton.setPosition(cw * i + (w/2 + 100), h/12); // Hand Position
			HandButton.setSize(cw, ch);
			HandButton.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button)
				{
					System.out.println("Hand Button Clicked, Put that card on the Gameboard!");
					HandButtonClicked(cardToAddToGameBoard, HandButton, p);
				}
				@Override
				public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
				{
					return true;
				}
			});
			// Hand Images store the images of our Hand at this current time
			handImages.add(HandButton);
		}
		for (int i = 0; i < handImages.size(); i++) 
		{
			stage.addActor(handImages.get(i));
		}
		// HAND BUTTONS ABOVE
		// WHEN USER ENTERS A GAME, HERE ARE THE CARDS DRAWN AND ON THE HAND. NO CARDS ON THE GAMEBOARD YET
//		ArrayList<Card> opponentBoard = new ArrayList<Card>();
//		CreatureCard cd = game.creatureCards.get("pig");
//		opponentBoard.add(new CreatureCard(cd));
//		for (int i = 0; i < opponentBoard.size(); i++)
//		{
//			final Card oppCard = opponentBoard.get(i);
//			final Player opp = otherPlayer;
//			final Card youCard = yourCard;
//			final ThisGame gg = currentGame;
//			Texture currCard = opponentBoard.get(i).getTexture();
//			TextureRegion TEMP_C = new TextureRegion(currCard);
//			TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
//			final ImageButton GameBoardButton = new ImageButton(TEMP_CARD);
//			GameBoardButton.setPosition(cw + (w/5), h/2 + 25);  
//			GameBoardButton.setSize(cw, ch);
//			GameBoardButton.addListener(new ClickListener() {
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int button)
//				{
//					System.out.println("Enemy GameBoard Card Clicked!");
//					EnemyGameBoardCardClicked(oppCard, youCard, GameBoardButton, opp, gg);
//				}
//				@Override
//				public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
//				{
//					return true;
//				}
//			});
//			opponentImages.add(GameBoardButton);
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
	
	public void endTurnButtonClicked(Player p1, Player p2, ThisGame cg) {
		batch.begin();
		BitmapFont titleFont64 = game.titlefont64();
		titleFont64.draw(batch, "Healthfds - " + (p1.get_hp()-1), w/2, (h)/2);
		font.draw(batch, "Mana - " + p1.get_mana(), w/8, (h)/6);
		
		playerTurn = !playerTurn;
		opponentAttackCount++;
		
		if (playerTurn == false) {
			// Output P2's cards on the gameboard
			// If opponent's first turn
			if (opponentAttackCount == 1)
			{
				p2.drawCards();
				p2.drawCards();
				p2.drawCards();
			}
			else
			{
				p2.drawCards();
			}
			Random rand = new Random();
			int min = 1;
			int max = p2.getmHand().size();
			System.out.println("maxxxx: " + max);
			int randomNum = rand.nextInt(max) + 1;
			
			// Add to the Player 2 Gameboard, and remove from Player 2 hand
			Card chosenOne = p2.getmHand().get(randomNum-1);
			p2.getPlayerBoard().add(chosenOne);
			p2.getmHand().remove(chosenOne);
			// display all cards from opponent board arraylist
			for (int i = 0; i < p2.getPlayerBoard().size(); i++)
			{
				final Card oppCard = p2.getPlayerBoard().get(i);
				final Player opp = otherPlayer;
				final Card youCard = yourCard;
				final ThisGame gg = cg;
				Texture currCard = p2.getPlayerBoard().get(i).getTexture();
				TextureRegion TEMP_C = new TextureRegion(currCard);
				TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
				final ImageButton GameBoardButton = new ImageButton(TEMP_CARD);
				GameBoardButton.setPosition(cw + (w/5), h/2 + 25);  
				GameBoardButton.setSize(cw, ch);
				GameBoardButton.addListener(new ClickListener() {
					@Override
					public void touchUp(InputEvent e, float x, float y, int point, int button)
					{
						System.out.println("Enemy GameBoard Card Clicked!");
						EnemyGameBoardCardClicked(oppCard, youCard, GameBoardButton, opp, gg);
					}
					@Override
					public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
					{
						return true;
					}
				});
				opponentImages.add(GameBoardButton);
				// Need to remove all the images of dead creatures
			}
			for (int i = 0; i < opponentImages.size(); i++) 
			{
				stage.addActor(opponentImages.get(i));
			}
			// if p2 has cards on its gameboard, it can attack
			if (p2.getPlayerBoard().size() > 0) {
				// if p1 has cards on the gameboard, it can attack
				if (opponentAttackCount % 2 == 0 && p1.getPlayerBoard().size() > 0) {
					// Select a random card from the computer's gameboard
					Random rand1 = new Random();
					int min1 = 1;
					int max1 = p2.getPlayerBoard().size() - 1;
					int randomNum1 = rand1.nextInt((max1 - min1) + 1) + min1;
					// Select a random card from the player's gameboard to attack
					Random rand2 = new Random();
					int min2 = 1;
					int max2 = p1.getPlayerBoard().size() - 1;
					int randomNum2 = rand2.nextInt((max2 - min2) + 1) + min2;
					// attack
					cg.Act(p2.getPlayerBoard().get(randomNum1), p1.getPlayerBoard().get(randomNum2));
//					Card p2CardChosen = p2.getPlayerBoard().get(randomNum1);
//					p2CardChosen.Attack((CreatureCard)(p1.getPlayerBoard().get(randomNum2)), p1);
//					opponentAttackCount = 0;
				}
			}
			
			playerTurn = !playerTurn;
		}
		
		//OPPONENT
		font.draw(batch, "Health - " + p2.get_hp(), 6*w/8, (5*h)/6 + buttonHeight/2);
		font.draw(batch, "Mana - " + p2.get_mana(), 6*w/8, (5*h)/6);
		batch.end();
		numTurnsSoFar++;
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
	public void deckButtonClicked(Player forDeck)
	{
		// Draw a card from the deck in thisGame and output it on the Screen.
		int currHandSize = forDeck.getmHand().size();
		if (currHandSize < 3)
		{
			forDeck.drawCards();
			// Add one more card to the Hand
			System.out.println(currHandSize);
			ArrayList<Card> currHand = forDeck.getmHand();
//			System.out.println(currHand.size());
			// Get the Textures of the cards to output them
			for (int i = 0; i < currHand.size(); i++)
			{
				final Player p = forDeck; // Must be final?
				final Card cardToAddToGameBoard = currHand.get(i);
				Texture currCard = currHand.get(i).getTexture();
				TextureRegion TEMP_C = new TextureRegion(currCard);
				TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
				final ImageButton HandButton = new ImageButton(TEMP_CARD);
				// Set position of the Hand to be next to the Deck
				HandButton.setPosition(cw * i + (w/2 + 100), h/12); // Hand Position
				HandButton.setSize(cw, ch);
				HandButton.addListener(new ClickListener() {
					@Override
					public void touchUp(InputEvent e, float x, float y, int point, int button)
					{
						System.out.println("Hand Button Clicked, Put that card on the Gameboard!");
						HandButtonClicked(cardToAddToGameBoard, HandButton, p);
					}
					@Override
					public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
					{
						return true;
					}
				});
				// Hand Images store the images of our Hand at this current time
				handImages.add(HandButton);
			}
			for (int i = 0; i < handImages.size(); i++) 
			{
				stage.addActor(handImages.get(i));
			}
		}
	}
	
	// Hand Button Clicked()
	public void HandButtonClicked(final Card cardToAdd, ImageButton handButton, final Player currPlayer)
	{
		// Check who's turn it is first
		if (playerTurn)
		{
			// If Creature Card, then remove the card from the hand, and add the card to the Gameboard
			// if the gameboard is not full. If Magic or Action Card, play the effect of that card.
			if (cardToAdd.getMyType().equalsIgnoreCase("magic"))
			{
				// TODO Magic Card Effect
//				MagicCard mc = (MagicCard) cardToAdd;
//				mc.AstroEffect();
			}
			else if (cardToAdd.getMyType().equalsIgnoreCase("action"))
			{
				// TODO Action Card Effect
			}
			else // Creature Card - No effect, just putting card on the gamebaord
			{
				ArrayList<Card> yourGameBoard = currPlayer.getPlayerBoard();
				ArrayList<Card> yourHand = currPlayer.getmHand();
				// if the gameBoard is not full, then remove the Card from yourHand, and add it to your GameBoard
				if (yourGameBoard.size() < 3 && GameBoardImages.size() < 3)
				{
					yourHand.remove(cardToAdd); // Remove from your hand
					yourGameBoard.add(cardToAdd); // Add to the Gameboard
					System.out.println("On your gameboard now: " + yourGameBoard.size() + " and on your hand now: " + currPlayer.getmHand().size());
					handImages.remove(handButton); // Remove the Image Button from the Hand Space
					handButton.remove(); // Remove the Picture completely from the stage
					Texture currCard = cardToAdd.getTexture();
					TextureRegion TEMP_C = new TextureRegion(currCard);
					TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
					final ImageButton GameBoardButton = new ImageButton(TEMP_CARD);
					if (yourGameBoard.size() == 1) // 1 card going to be on Gameboard
					{
						GameBoardButton.setPosition(cw + (w/5), h/3); // Gameboard 1st Position
						GameBoardButton.setSize(cw, ch);
					}
					else if (yourGameBoard.size() == 2) // 2 cards going to be on Gameboard
					{
						GameBoardButton.setPosition(cw * 3 + (w/5), h/3);  // Gameboard 2nd Position
						GameBoardButton.setSize(cw, ch);
					}
					else if (yourGameBoard.size() == 3) // 3 cards going to be on Gameboard
					{
						GameBoardButton.setPosition(cw * 5 + (w/5), h/3);  // Gameboard 3rd Position
						GameBoardButton.setSize(cw, ch);
					}
					GameBoardButton.addListener(new ClickListener() 
					{
						@Override
						public void touchUp(InputEvent e, float x, float y, int point, int button)
						{
							System.out.println("GameBoard Button Clicked, User now clicks enemy Gameboard!");
							GameBoardCardClicked(cardToAdd, GameBoardButton, currPlayer);
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
		}
	}
	
	// GameBoard Card Clicked
	public void GameBoardCardClicked(Card yourCard, ImageButton yourButton, Player currPlayer)
	{
		// Now, the card is a Creature, the user has to click a card on the opponent's Gameboard to attack
		// Card will never be a Magic or Action Card because they are played directly from the hand
		
		// Requirements:
		// 1) Player cannot attack on the first turn at all
		// 2) Player cannot attack right after he/she moved the Card to the Gameboard. Need to wait until next
		//    turn to do so
		// 3) Player needs enough mana to attack
		// 4) Check if all cards on the gameboard is dead. If dead, then remvove the image and card completely
		// 5) If opponent has no card on the gameboard, attack the opponent directly
		sr.begin(ShapeType.Line);
		sr.setColor(new Color(0,0,1,0));
		sr.rect(cw, ch, cw, ch);
		System.out.println("In Gameboard Card Clicked Function");
		if (numTurnsSoFar != 0)
		{
			attackInMotion = true;
			this.yourCard = (CreatureCard) yourCard;
		}
	}
	
	// Enemy GameBoard Card Clicked
	public void EnemyGameBoardCardClicked(Card opponentCard, Card yourCard, ImageButton enemyButton, Player playerThatGetsHurt, ThisGame gg)
	{
		System.out.println("In Enemy Game Board Card Clicked Function");
		// Once here, the user wants to attack 
		if (attackInMotion)
		{
			opponentCardToAttack = (CreatureCard) opponentCard;
			opponentCardToAttack.setPlayer(playerThatGetsHurt);
			if (yourCard != null)
			{
//				yourCard.Attack(opponentCardToAttack, otherPlayer);
				gg.Act(yourCard, opponentCard);
			}
			if (opponentCardToAttack.isDead())
			{
				enemyButton.remove();
			}
			this.yourCard = null;
			this.opponentCardToAttack = null;
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
