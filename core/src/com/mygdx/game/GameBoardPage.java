package com.mygdx.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	private int ch = 150;
	private int cw = 125;
	boolean cardPlaySuccess = false;
	boolean firstCardPlay = false;
	ArrayList<ImageButton> handImages = new ArrayList<ImageButton>();
	ArrayList<ImageButton> GameBoardImages = new ArrayList<ImageButton>();
	ArrayList<ImageButton> opponentImages = new ArrayList<ImageButton>();
	private int numTurnsSoFar = 0; // Counting the number of turns so far
	private boolean attackInMotion = false;
	private CreatureCard yourCard = null;
	private CreatureCard opponentCardToAttack = null;
	private Texture CardBack = Assets.myTexturesList.get(58);

	private Skin skin;
	private FireplacePebble game;
	
	int buttonHeight = 100;
	int buttonWidth = 100;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    Label.LabelStyle labelStyle = new Label.LabelStyle();
    
    Label labelhealth;
    Label labelmana;
    Label oplabelhealth;
    Label oplabelmana;
    Label turnLabel;
    public void displayMessage(String message) {
    		turnLabel.setText(message);
    }
    
    public void updateStats() {
    		labelhealth.setText("Health - " + player.get_hp());
    		labelmana.setText("Mana - " + player.get_mana());
    		oplabelhealth.setText("Health - " + otherPlayer.get_hp());
    		oplabelmana.setText("Mana - " + otherPlayer.get_mana());
    }
    
	public GameBoardPage(FireplacePebble g, ThisGame cg) {
		System.out.println("GAME BOARD!");
		game = g;
		currentGame = cg; //THE ACTUAL GAME LOGIC GAME
		BitmapFont fontLabl = game.regfont32;
		fontLabl.setColor(Color.BLACK);
		labelStyle.font = fontLabl;
		// Creating a new player for testing
		player = cg.getP1();
		otherPlayer = cg.getP2();
		//LABELS FOR STATS, HEALTH AND MANA
		labelhealth = new Label("Health - " + player.get_hp() ,labelStyle);
	    labelmana = new Label("Mana - " + player.get_mana(), labelStyle);
	    oplabelhealth = new Label("Health - " + otherPlayer.get_hp() ,labelStyle);
	    oplabelmana = new Label("Mana - " + otherPlayer.get_mana(), labelStyle);
	    turnLabel = new Label("Your turn!", labelStyle);
	    
	    labelhealth.setPosition(w/8, (h)/6 - buttonHeight/2);
	    labelmana.setPosition(w/8, (h)/6);
	    oplabelhealth.setPosition(6*w/8, (5*h)/6 + buttonHeight/2);
	    oplabelmana.setPosition(6*w/8, (5*h)/6);
	    turnLabel.setPosition(w/4, h/28);
	    
	    stage.addActor(labelhealth);
	    stage.addActor(oplabelhealth);
	    stage.addActor(labelmana);
	    stage.addActor(oplabelmana);
	    stage.addActor(turnLabel);

		// Hard Set it to this player's turn first
		playerTurn = true;

		skin = new Skin(Gdx.files.internal(game.getSkin()));
        font = game.regfont32();
		Gdx.input.setInputProcessor(stage);		
		// End Turn button setup
		TextButton endTurnButton = new TextButton("END TURN", skin);
		endTurnButton.setPosition(11*w/12, h/2);
		endTurnButton.setSize(buttonWidth, buttonHeight);
				
		endTurnButton.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				displayMessage("Opponent turn");
				endTurnButtonClicked();
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
			{
				return true;
			}
		});
		
		// Add all components onto stage
		stage.addActor(endTurnButton);
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
		if (firstCardPlay)
		{
			game.achievementMap.put("Card Picked!", 1);
			game.achievementMap.put("Played a Game!", 1);
		}
		
		// DECK BUTTON BELOW
		// Create Deck Button
		// Make a deck picture

		TextureRegion DECK_TR = new TextureRegion(CardBack);
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
					if(firstCardPlay == false) {
						firstCardPlay = true;
						new AchievementThread(game);
					}
					updateStats();
					System.out.println("Hand Button Clicked, Put that card on the Gameboard!");
					HandButtonClicked(cardToAddToGameBoard, HandButton);
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
	
	public void btnBackClicked() {
		game.setScreen(new ProfileScreen(game));
	}
	

	public void endTurnButtonClicked() {
		player.set_mana(5); //reset Mana
		otherPlayer.set_mana(5);
		updateStats();
		playerTurn = !playerTurn;
		opponentAttackCount++;
		
		if (playerTurn == false) {
			// Output P2's cards on the gameboard
			//DRAW CARDS
			// If opponent's first turn
			if (opponentAttackCount == 1)
			{
				otherPlayer.drawCards();
				otherPlayer.drawCards();
				otherPlayer.drawCards();
			}
			else
			{
				otherPlayer.drawCards();
			}
			
			ArrayList<Card> opCurrHand = otherPlayer.getmHand();
			//DISPLAY the back of the hand cards
			for (int i = 0; i < opCurrHand.size(); i++)
			{
				TextureRegion DECK_TR = new TextureRegion(CardBack);
				TextureRegionDrawable DECK_TRD = new TextureRegionDrawable(DECK_TR);
				ImageButton DeckButton = new ImageButton(DECK_TRD);
				// Set position of Deck Button to Bottom Right Corner and Clickable
				DeckButton.setPosition(i*cw + (w/9), 9*h/12); // Should edit to fit others' resolution
				DeckButton.setSize(cw, ch);
				stage.addActor(DeckButton);
			}
			
			Random rand = new Random();
//			//play random creature card
//			if(!opCurrHand.isEmpty())
//			{
//				int max = opCurrHand.size();
//				int randomNum = rand.nextInt(max) + 1;
//				
//				// Add to the Player 2 Gameboard, and remove from Player 2 hand
//				Card chosenOne = opCurrHand.get(randomNum-1);
//				otherPlayer.getPlayerBoard().add(chosenOne);
//				otherPlayer.getmHand().remove(chosenOne);
//			}
			
			// display all cards from opponent board arraylist
			for (int i = 0; i < opCurrHand.size(); i++)
			{
				final Card oppCard = opCurrHand.get(i);
				Texture currCard = oppCard.getTexture();
				TextureRegion TEMP_C = new TextureRegion(currCard);
				TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
				final ImageButton GameBoardButton = new ImageButton(TEMP_CARD);
				
				if(oppCard.getMyType().equalsIgnoreCase("creature") && (otherPlayer.get_mana() >= oppCard.get_manaCost())) {
					//minus mana
					otherPlayer.set_mana(otherPlayer.get_mana() - oppCard.get_manaCost());
					final Card youCard = yourCard;
					otherPlayer.getPlayerBoard().add(oppCard);
					otherPlayer.getmHand().remove(oppCard);
					GameBoardButton.setPosition(cw + (w/5), h/2 + 25);  
					GameBoardButton.setSize(cw, ch);
					GameBoardButton.addListener(new ClickListener() {
						@Override
						public void touchUp(InputEvent e, float x, float y, int point, int button)
						{
							EnemyGameBoardCardClicked(oppCard, youCard, GameBoardButton);
						}
						@Override
						public boolean touchDown(InputEvent e, float x, float y, int point, int button) 
						{
							return true;
						}
					});
					stage.addActor(GameBoardButton);
				}
				else if(oppCard.getMyType().equalsIgnoreCase("magic") && (otherPlayer.get_mana() >= oppCard.get_manaCost())) {
					//minus mana
					otherPlayer.getmHand().remove(oppCard);
					otherPlayer.set_mana(otherPlayer.get_mana() - oppCard.get_manaCost());
					GameBoardButton.setPosition(cw + (w/30), h/2);  
					stage.addActor(GameBoardButton);
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					GameBoardButton.remove();
					currentGame.Act(oppCard, null, otherPlayer, player);
				}
				else if(oppCard.getMyType().equalsIgnoreCase("action") && (otherPlayer.get_mana() >= oppCard.get_manaCost())) {
					//minus mana
					otherPlayer.getmHand().remove(oppCard);
					otherPlayer.set_mana(otherPlayer.get_mana() - oppCard.get_manaCost());
					GameBoardButton.setPosition(cw + (w/30), h/2);  
					stage.addActor(GameBoardButton);
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					GameBoardButton.remove();
					currentGame.Act(oppCard, null, otherPlayer, player);
				}
				// if p2 has cards on its gameboard, it can attack
			}
			if (otherPlayer.getPlayerBoard().size() > 0) {
				// if p1 has cards on the gameboard, it can attack
				if (opponentAttackCount % 2 == 0 && player.getPlayerBoard().size() > 0) {
					// Select a random card from the computer's gameboard
					int max1 = otherPlayer.getPlayerBoard().size();
					int opRandom = rand.nextInt(max1);
					// Select a random card from the player's gameboard to attack
					int max2 = player.getPlayerBoard().size();
					int yourRandom = rand.nextInt(max2);
					// attack
					currentGame.Act(otherPlayer.getPlayerBoard().get(opRandom), player.getPlayerBoard().get(yourRandom), otherPlayer, player);
				}
			}
		}
		playerTurn = !playerTurn;
		numTurnsSoFar++;
		System.out.println("Numturnssofar: " + numTurnsSoFar);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		displayMessage("Your turn!");
		return;
	}
	
	// Deck Button Clicked
	public void deckButtonClicked()
	{
		// Draw a card from the deck in thisGame and output it on the Screen.
		int currHandSize = player.getmHand().size();
		if (currHandSize < 3)
		{
			player.drawCards();
			displayMessage("Cards drawn!");
			// Add one more card to the Hand
			System.out.println(currHandSize);
			ArrayList<Card> currHand = player.getmHand();
//			System.out.println(currHand.size());
			// Get the Textures of the cards to output them
			for (int i = 0; i < currHand.size(); i++)
			{				
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
						HandButtonClicked(cardToAddToGameBoard, HandButton);
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
		else {
			displayMessage("Cannot draw another card!");
		}
	}
	
	// Hand Button Clicked()
	public void HandButtonClicked(final Card cardToAdd, ImageButton handButton)
	{
		// Check who's turn it is first
		//player turn and player has enough mana
		cardPlaySuccess = false;
		if (playerTurn && (player.get_mana() >= cardToAdd.get_manaCost()))
		{
			// If Creature Card, then remove the card from the hand, and add the card to the Gameboard
			// if the gameboard is not full. If Magic or Action Card, play the effect of that card.
			if (cardToAdd.getMyType().equalsIgnoreCase("magic"))
			{
				if(currentGame.Act(cardToAdd, cardToAdd, player, otherPlayer)) { //second argument is just to satisfy, not actually used.
					cardPlaySuccess = true; //can remove card now.
				}
			}
			else if (cardToAdd.getMyType().equalsIgnoreCase("action"))
			{
				// TODO Action Card Effect
				if(currentGame.Act(cardToAdd, cardToAdd, player, otherPlayer)) {
					cardPlaySuccess = true;
				}
			}
			else // Creature Card - No effect, just putting card on the gamebaord
			{
				cardPlaySuccess = true;
				ArrayList<Card> yourGameBoard = player.getPlayerBoard();
				ArrayList<Card> yourHand = player.getmHand();
				// if the gameBoard is not full, then remove the Card from yourHand, and add it to your GameBoard
				if (yourGameBoard.size() < 3 && GameBoardImages.size() < 3)
				{
					yourHand.remove(cardToAdd); // Remove from your hand
					yourGameBoard.add(cardToAdd); // Add to the Gameboard
					System.out.println("On your gameboard now: " + yourGameBoard.size() + " and on your hand now: " + player.getmHand().size());
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
							GameBoardCardClicked(cardToAdd, GameBoardButton, player);
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
			
			if(cardPlaySuccess) {
				
				player.set_mana(player.get_mana() - cardToAdd.get_manaCost());
				System.out.println("Mana cost: " + cardToAdd.get_manaCost());
				updateStats();
				handImages.remove(handButton); // Remove the Image Button from the Hand Space
				player.getmHand().remove(cardToAdd);
				handButton.remove(); // Remove the Picture completely from the stage
			}
		}
		else {
			displayMessage("Cannot play this card yet!");
		}
	}
	
	//A CREATURE CARD WAS CLICKED
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

		TextureRegion TEMP_C = new TextureRegion(yourCard.getClickedTexture());
		TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
		yourButton.setBackground(TEMP_CARD);
		stage.addActor(yourButton);
		
		ImageButtonStyle _oldStyle = yourButton.getStyle();
		_oldStyle.imageUp = TEMP_CARD;
		yourButton.setStyle(_oldStyle);
		
		System.out.println("In Gameboard Card Clicked Function");
		if (numTurnsSoFar != 0)
		{
			attackInMotion = true;
			this.yourCard = (CreatureCard) yourCard;
		}
	}
	
	// Enemy GameBoard Card Clicked
	public void EnemyGameBoardCardClicked(Card opponentCard, Card yourCard, ImageButton enemyButton)
	{
		TextureRegion TEMP_C = new TextureRegion(((CreatureCard)opponentCard).getClickedTexture());
		TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
		ImageButtonStyle _oldStyle = enemyButton.getStyle();
		_oldStyle.imageUp = TEMP_CARD;
		enemyButton.setStyle(_oldStyle);
		// Once here, the user wants to attack 
		System.out.println(attackInMotion);
		if (attackInMotion)
		{
			System.out.println("An attack in motion...");
			opponentCardToAttack = (CreatureCard) opponentCard;
			if (yourCard != null)
			{
				System.out.println("An attack in progress...");
				currentGame.Act(yourCard, opponentCard, player, otherPlayer);
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
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 120/255f, 180/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		texture = new Texture("GamePage.png");
		mainBackground = new TextureRegion(texture, 0, 0, 1920, 1080);
		
		batch.begin();
		batch.draw(mainBackground, 0, 0, w, h);
		
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
