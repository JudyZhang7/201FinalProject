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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
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
	
	private int numTurnsSoFar = 0; // Counting the number of turns so far
	private boolean attackInMotion = false;
	
	private CreatureCard yourCard = null;
	private CreatureCard opponentCardToAttack = null;
	private Texture CardBack = Assets.myTexturesList.get(58);
	private ImageButton currentAttackIB;
	private Skin skin;
	private FireplacePebble game;
	
	int buttonHeight = 100;
	int buttonWidth = 100;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    Label.LabelStyle labelStyle = new Label.LabelStyle();
    
    boolean firstGamePlayed = false;
    boolean firstCardPlay = false;
    boolean firstCardDrawn = false;
    boolean firstTurnDone = false;
    boolean firstWin = false;
    boolean firstAttack = false;
    
    Label labelhealth;
    Label labelmana;
    Label oplabelhealth;
    Label oplabelmana;
    Label turnLabel;
      
    public void displayAll() {
    		displayImages(otherPlayer.getPlayerBoard());
		displayImages(player.getPlayerBoard());
		displayLabels(otherPlayer.getPlayerBoard());
		displayLabels(player.getPlayerBoard());
		displayImages(player.getmHand());
    }
    public void removeAll() {
    		removeImages(otherPlayer.getPlayerBoard());
		removeImages(player.getPlayerBoard());
		removeLabels(otherPlayer.getPlayerBoard());
		removeLabels(player.getPlayerBoard());
		removeImages(player.getmHand());
    }
    public void banner(int i) {
    		
    		BitmapFont titlefont = game.titlefont128;
		labelStyle.font = titlefont;
		
		Label banner = new Label("", labelStyle);
    		if(i == 1) {
    			if (firstWin == false) {
    				game.achievementMap.put("First Win!", 1);
    				new AchievementThread(game);
    				firstWin = true;
    			}
    			banner.setText("You win!");
    		} else {
    			banner.setText("You lose!");
    		}
    		banner.setColor(Color.BLACK);
    		banner.setPosition(w/3, h/2);
    		stage.addActor(banner);
    		if (firstGamePlayed == false) {
    			game.achievementMap.put("First Game Played!", 1);
				new AchievementThread(game);
				firstGamePlayed = true;
    		}
    }
    public void displayImages(ArrayList<Card> cards){
    		Card cardc;
//    		System.out.println("Size: " + cards.size() +"================ The cards placed are: ================ ");
    		for(int i = 0; i < cards.size(); i++) {
    			cardc = cards.get(i);
//    			System.out.println(cardc.getCardname());
    			stage.addActor(cardc.getib());
    		}
    }
    
    public void displayLabels(ArrayList<Card> cards){
    		CreatureCard cardc;
		for(int i = 0; i < cards.size(); i++) {
			cardc = (CreatureCard)cards.get(i);
			cardc.getLabel().setText(cardc.get_hp() + "");
			cardc.getLabel().setVisible(true);
			stage.addActor(cardc.getLabel());
		}
    }
    
    public void removeImages(ArrayList<Card> cards) {
		Card cardc;
		for(int i = 0; i < cards.size(); i++) {
			cardc = cards.get(i);
			cardc.getib().remove();
		}
    }
    
    public void removeLabels(ArrayList<Card> cards) {
    		CreatureCard cardc;
		for(int i = 0; i < cards.size(); i++) {
			cardc = (CreatureCard)cards.get(i);
			cardc.getLabel().setVisible(false);
		}
    }
    
    public void displayMessage(String message) {
    		System.out.println("Displaying message: " + message);
    		turnLabel.setText(message);
    }
    
    public void updateStats() {
    		labelhealth.setText("Health: " + player.get_hp());
    		labelmana.setText("Mana: " + player.get_mana());
    		oplabelhealth.setText("Health: " + otherPlayer.get_hp());
    		oplabelmana.setText("Mana: " + otherPlayer.get_mana());
    }
    
	public GameBoardPage(FireplacePebble g, ThisGame cg) {
		game = g;
		currentGame = cg; //THE ACTUAL GAME LOGIC GAME
		BitmapFont fontLabl = game.titlefont32;
		fontLabl.setColor(Color.BLACK);
		labelStyle.font = fontLabl;
		// Creating a new player for testing
		player = cg.getP1();
		otherPlayer = cg.getP2();
		player.set_mana(5); //reset Mana
		player.set_hp(10);
		otherPlayer.set_mana(5);
		
		//LABELS FOR STATS, HEALTH AND MANA
		labelhealth = new Label("Health: " + player.get_hp() ,labelStyle);
	    labelmana = new Label("Mana: " + player.get_mana(), labelStyle);
	    oplabelhealth = new Label("Health: " + otherPlayer.get_hp() ,labelStyle);
        oplabelmana = new Label("Mana: " + otherPlayer.get_mana(), labelStyle);
	    turnLabel = new Label("Your turn!", labelStyle);
	    labelhealth.setColor(Color.BLACK);
	    labelmana.setColor(Color.BLACK);
	    oplabelhealth.setColor(Color.BLACK);
	    oplabelmana.setColor(Color.BLACK);
	    turnLabel.setColor(Color.BLACK);
	    
	    labelhealth.setPosition(w/8, (h)/6 - buttonHeight/2);
	    labelmana.setPosition(w/8, (h)/6);
	    oplabelhealth.setPosition(6*w/8, (5*h)/6 + buttonHeight/2);
	    oplabelmana.setPosition(6*w/8, (5*h)/6);
	    
	    turnLabel.setPosition(Gdx.graphics.getWidth()/2-(turnLabel.getPrefWidth()/2), turnLabel.getPrefHeight()/2 - 20);
	    
	    stage.addActor(labelhealth);
	    stage.addActor(oplabelhealth);
	    stage.addActor(labelmana);
	    stage.addActor(oplabelmana);
	    stage.addActor(turnLabel);
	    
	    //reset player stats?
	  	player.getmHand().clear();
	  	player.getPlayerBoard().clear();
	  	
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
            public void clicked(InputEvent event, float x, float y) {
				displayMessage("Opponent turn");
				if (firstTurnDone == false)
				{
					game.achievementMap.put("Played first Turn!", 1);
					new AchievementThread(game);
					firstTurnDone = true;
				}
				System.out.println("First Turn Boolean Value: " + firstTurnDone);
				endTurnButtonClicked();
            }
		});
		
		// Add all components onto stage
		stage.addActor(endTurnButton);
		TextButton btnBack = new TextButton ("Quit", skin);
		btnBack.setPosition(w/40, 18*h/20);
		btnBack.setSize(buttonHeight/2, buttonWidth/2);
		
		btnBack.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				btnBackClicked();
            }
		});
		stage.addActor(btnBack);
		
		// Thread to run achievements - Will output the achievement that has been unlocked
		if (firstGamePlayed == false)
		{
			game.achievementMap.put("Playing first Game!", 1);
			new AchievementThread(game);
			firstGamePlayed = true;
        }
        
		TextureRegion DECK_TR = new TextureRegion(CardBack);
		TextureRegionDrawable DECK_TRD = new TextureRegionDrawable(DECK_TR);
		ImageButton DeckButton = new ImageButton(DECK_TRD);
		// Set position of Deck Button to Bottom Right Corner and Clickable
		DeckButton.setPosition(cw + (w/2 + 360), h/12); // Should edit to fit others' resolution
		DeckButton.setSize(cw, ch);
		DeckButton.addListener(new ClickListener() {
			@Override
            public void clicked(InputEvent event, float x, float y) {
				System.out.println("Deck Clicked, Draw a Card!");
				if (firstCardDrawn == false)
				{
					game.achievementMap.put("Drew first Card!", 1);
					new AchievementThread(game);
					firstCardDrawn = true;
				}
				System.out.println("First Card Drawn Boolean Value: " + firstTurnDone);
				deckButtonClicked();
            }
		});
		stage.addActor(DeckButton);

		// DRAW THE FIRST THREE CARDS
		for (int i = 0; i < 3; i++)
		{
			deckButtonClicked();
		}
		// Networking
		if (game.gameBoardMessage == true)
		{
			game.getGMasterComments(game);
			game.gameBoardMessage = false;
		}
	}
	
	public void btnBackClicked() {
		game.setScreen(new ProfileScreen(game));
	}

	public void endTurnButtonClicked() {
		//UPDATE THE SCREEN
        updateStats();
		if((player.get_hp() <= 0) || (otherPlayer.get_hp() <= 0))
		{
			if(player.get_hp() <= 0) {
				game.getUser().AddLoss();
				banner(0);
			} else {
				game.getUser().AddWin();
				banner(1);
			}
			return;
		}
		player.set_mana(5); //reset Mana
		otherPlayer.set_mana(5);
		updateStats();
		playerTurn = !playerTurn;
		opponentAttackCount++;
		
		if (playerTurn == false) {

			if (opponentAttackCount == 1)
			{
				otherPlayer.drawCards();
				otherPlayer.drawCards();
				otherPlayer.drawCards();
				
				//First time, DISPLAY the back of the hand cards
				for (int i = 0; i < 3; i++)
				{
					TextureRegion DECK_TR = new TextureRegion(CardBack);
					TextureRegionDrawable DECK_TRD = new TextureRegionDrawable(DECK_TR);
					ImageButton DeckButton = new ImageButton(DECK_TRD);
					// Set position of Deck Button to Bottom Right Corner and Clickable
					DeckButton.setPosition(i*cw + (w/9), 9*h/12); // Should edit to fit others' resolution
					DeckButton.setSize(cw, ch);
					stage.addActor(DeckButton);
				}
			}
			else
			{
				boolean canDraw = true;
				while(otherPlayer.getmHand().size() < 3 && canDraw) {
					if(!otherPlayer.drawCards()) {
						displayMessage("Opponent out of cards!");
						canDraw = false;
					}
				}
			}
			
			Random rand = new Random();
			ArrayList<Card> opCurrHand = otherPlayer.getmHand();
			// display all cards from opponent board arraylist
			
			// ============= REMOVE WHAT'S ON THE PLAYER BOARD =============
			removeAll();
			
			for (int i = 0; i < opCurrHand.size(); i++)
			{                
				final Card oppCard = opCurrHand.get(i);
				final ImageButton GameBoardButton = oppCard.getib();

				if(oppCard.getMyType().equalsIgnoreCase("creature") && (otherPlayer.get_mana() >= oppCard.get_manaCost())) {
                    //ADD AND REMOVE PROPERLY
                    if(otherPlayer.getPlayerBoard().size() >= 3) {
                    	//not enough room to play card
                    		return;
                    }
                    otherPlayer.set_mana(otherPlayer.get_mana() - oppCard.get_manaCost());
                    otherPlayer.getPlayerBoard().add(oppCard);
	                otherPlayer.getmHand().remove(oppCard);
                    
                    GameBoardButton.setSize(cw, ch);
					//CREATE NEW LABEL IF NONE EXISTS
					if(((CreatureCard)oppCard).getLabel() == null)
					{
						Label statLabel = new Label(""+((CreatureCard)oppCard).getHP(), labelStyle);
					  	((CreatureCard)oppCard).addLabel(statLabel);
					}
					Label statLabel = ((CreatureCard)oppCard).getLabel();
					
					GameBoardButton.clearListeners();
					GameBoardButton.addListener(new ClickListener() {
						@Override
			            public void clicked(InputEvent event, float x, float y) {
							if (firstAttack == false)
							{
								game.achievementMap.put("First Attack made!", 1);
								new AchievementThread(game);
								firstAttack = true;
							}
							EnemyGameBoardCardClicked(oppCard, GameBoardButton);
			            }
					});
				}
				else if(oppCard.getMyType().equalsIgnoreCase("magic") && (otherPlayer.get_mana() >= oppCard.get_manaCost())) {
					//minus mana
					otherPlayer.getmHand().remove(oppCard);
					otherPlayer.set_mana(otherPlayer.get_mana() - oppCard.get_manaCost());
					GameBoardButton.setPosition((w/2), 3*h/4);  
					//show the user the card
					GameBoardButton.setSize(cw, ch);
					stage.addActor(GameBoardButton);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					currentGame.Act(oppCard, null, otherPlayer, player); //this is correct
				}
				else if(oppCard.getMyType().equalsIgnoreCase("action") && (otherPlayer.get_mana() >= oppCard.get_manaCost())) {
					//minus mana
					otherPlayer.getmHand().remove(oppCard); //remove from hand
					otherPlayer.set_mana(otherPlayer.get_mana() - oppCard.get_manaCost());
					
					GameBoardButton.setPosition((w/2), 3*h/4);  
					GameBoardButton.setSize(cw, ch);
					stage.addActor(GameBoardButton);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					currentGame.Act(oppCard, null, otherPlayer, player); //correct
                }
            }
			//ATTACK PLAYER
            for(int i = 0; i < otherPlayer.getPlayerBoard().size(); i++){
                otherPlayer.getPlayerBoard().get(i).getib().setPosition(i*2*cw + (w/5), h/2 + 25); 
                ((CreatureCard)otherPlayer.getPlayerBoard().get(i)).getLabel().setPosition(i*2*cw + (w/5) - 30, h/2 + 50);
            }

			if (opponentAttackCount % 2 == 0 && player.getPlayerBoard().size() > 0 && otherPlayer.getPlayerBoard().size() > 0) {
					// Select a random card from the computer's gameboard
					int max1 = otherPlayer.getPlayerBoard().size();
					int opRandom = rand.nextInt(max1);
					// Select a random card from the player's gameboard to attack
					int max2 = player.getPlayerBoard().size();
					int yourRandom = rand.nextInt(max2);
					// attack
					Card cardAttacked = player.getPlayerBoard().get(yourRandom);
					
					currentGame.Act(otherPlayer.getPlayerBoard().get(opRandom), cardAttacked, otherPlayer, player); //correct
					//update HP
					((CreatureCard)cardAttacked).changeLabel(Integer.toString(((CreatureCard)cardAttacked).getHP()));
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
        
		//remove all player's dead cards and statlabels
		for(int i = 0; i < player.getPlayerBoard().size(); i++) {
			if(player.getPlayerBoard().get(i).isDead()) {
				System.out.println("Removing dead animatlfdsasdf...");
				player.getPlayerBoard().remove(i);
			}
		}
		for(int i = 0; i < otherPlayer.getPlayerBoard().size(); i++) {
			if(otherPlayer.getPlayerBoard().get(i).isDead()) {
				System.out.println("Removing dead animatlfdsasdf...");
				otherPlayer.getPlayerBoard().remove(i);
			}
		}
		//UPDATE THE SCREEN
        updateStats();
		// WIN OR LOSE?
		if((player.get_hp() <= 0) || (otherPlayer.get_hp() <= 0))
		{
			if(player.get_hp() <= 0) {
				game.getUser().AddLoss();
				banner(0);
			} else {
				game.getUser().AddWin();
				banner(1);
			}
		}
		// ============= DISPLAY WHAT'S ON THE PLAYER BOARD =============
		displayAll();
		return;
	}
	
	// Deck Button Clicked, 
	public void deckButtonClicked()
	{		
		// Draw a card from the deck in thisGame and output it on the Screen.
		if (player.getmHand().size() < 3)
		{
			// ============= REMOVE WHAT'S ON THE PLAYER BOARD =============
			if(!player.drawCards()) {
				displayMessage("You're out of cards!");
				return;
			}
			removeImages(player.getmHand());
			displayMessage("Cards drawn!");
			System.out.println("Current hand size: " + player.getmHand().size());

			for (int i = 0; i < player.getmHand().size(); i++)
			{				
				final ImageButton newHB = player.getmHand().get(i).getib();
				final Card cardToAddToGameBoard = player.getmHand().get(i);
				newHB.clearListeners();
				newHB.addListener(new ClickListener() {
					@Override
		            public void clicked(InputEvent event, float x, float y) {
						if (firstCardPlay == false)
						{
							game.achievementMap.put("First Card Played!", 1);
							new AchievementThread(game);
							firstCardPlay = true;
						}
						HandButtonClicked(cardToAddToGameBoard, newHB);
		            }
				});
				
				if(newHB.getClickListener() == null) {
					System.out.println("CLICK LISTENER IS NULLFSDLKJALSDKJF!");
				}
				// Set position of the Hand to be next to the Deck
				cardToAddToGameBoard.getib().setPosition(cw * i + (w/2 + 100), h/12); // Hand Position
				cardToAddToGameBoard.getib().setSize(cw, ch);
			}
			// ============= DISPLAY WHAT'S IN PLAYER HAND =============
			System.out.println("Display what is in hand: ");
			displayImages(player.getmHand());
		}
		else {
			displayMessage("Cannot draw another card!");
		}
	}
	
	public void HandButtonClicked(final Card cardToAdd, ImageButton handButton)
	{
		// ============= REMOVE WHAT'S ON THE PLAYER BOARD =============
		removeAll();
				
		cardPlaySuccess = false;
		System.out.println("Player mana: " + player.get_mana() + " card mana: " + cardToAdd.get_manaCost());
		if (playerTurn && (player.get_mana() >= cardToAdd.get_manaCost()))
		{
			System.out.println("Can play card!");
			// If Creature Card, then remove the card from the hand, and add the card to the Gameboard
			// if the gameboard is not full. If Magic or Action Card, play the effect of that card.
			if (cardToAdd.getMyType().equalsIgnoreCase("magic"))
			{
                System.out.println("Can play magic card!");
				if(currentGame.Act(cardToAdd, cardToAdd, player, otherPlayer)) { //second argument is just to satisfy, not actually used.
					cardPlaySuccess = true; //can remove card now.
				}
			}
			else if (cardToAdd.getMyType().equalsIgnoreCase("action"))
			{
                System.out.println("Can play action card!");
				if(currentGame.Act(cardToAdd, cardToAdd, player, otherPlayer)) {
					cardPlaySuccess = true;
				}
			}
			else // Creature Card - No effect, just putting card on the gameboard
			{
				System.out.println("Hand Button Clicked, Put that card on the Gameboard! " + cardToAdd.getCardname());
				
				if (player.getPlayerBoard().size() < 3)
				{
					cardPlaySuccess = true;
					player.getPlayerBoard().add(cardToAdd); // Add to the Gameboard
					int currentHP = ((CreatureCard)cardToAdd).getHP();
                    
                    //ADD NEW LABEL IF NO LABEL EXISTS
					if( ((CreatureCard)cardToAdd).getLabel() == null)
					{
						Label statLabel = new Label("" + currentHP, labelStyle);
						((CreatureCard)cardToAdd).addLabel(statLabel);
					}
					
					Label statLabel = ((CreatureCard)cardToAdd).getLabel();
					
				    labelhealth.setPosition(w/8, (h)/6 - buttonHeight/2);
				    
                    final ImageButton GameBoardButton = ((CreatureCard)cardToAdd).getib();
					GameBoardButton.clearListeners();
					GameBoardButton.addListener(new ClickListener()
					{
						@Override
			            public void clicked(InputEvent event, float x, float y) {
							System.out.println("GameBoard Button Clicked, User now clicks enemy Gameboard!");
							GameBoardCardClicked(cardToAdd, GameBoardButton, player);
			            }
					});					
					((CreatureCard)cardToAdd).setib(GameBoardButton);
				}
			}
		}
		//remove other player dead cards
		for(int i = 0; i < otherPlayer.getPlayerBoard().size(); i++) {
			if(otherPlayer.getPlayerBoard().get(i).isDead()) {
				System.out.println("Removing dead animatlfdsasdf...");
				otherPlayer.getPlayerBoard().remove(i);
			}
		}
		for(int i = 0; i < player.getPlayerBoard().size(); i++) {
			if(player.getPlayerBoard().get(i).isDead()) {
				System.out.println("Removing dead animatlfdsasdf...");
				player.getPlayerBoard().remove(i);
			}
		}
		
		//set playerboard places
		for(int i = 0; i < player.getPlayerBoard().size(); i++) {
			player.getPlayerBoard().get(i).getib().setPosition(i*2*cw + (w/5), h/4 + 25); 
            ((CreatureCard)player.getPlayerBoard().get(i)).getLabel().setPosition(i*2*cw + (w/5) - 30, h/4 + 50);
		}
		if(cardPlaySuccess) {
			player.set_mana(player.get_mana() - cardToAdd.get_manaCost());
			System.out.println("Mana cost: " + cardToAdd.get_manaCost());
			updateStats();
			player.getmHand().remove(cardToAdd);
        }
		else if(!cardPlaySuccess){
			displayMessage("Cannot play this card yet!");
		}
		System.out.println("On your gameboard now: " + player.getPlayerBoard().size() + " and on your hand now: " + player.getmHand().size());
		// ============= DISPLAY WHAT'S ON THE PLAYER BOARD =============
		displayAll();
	}
	
	//A CREATURE CARD WAS CLICKED
	public void GameBoardCardClicked(Card yourCard, ImageButton yourButton, Player currPlayer)
	{
		// Requirements:
		// 1) Player cannot attack on the first turn at all
		// 2) Player cannot attack right after he/she moved the Card to the Gameboard. Need to wait until next
		//    turn to do so
		// 3) Player needs enough mana to attack
		// 4) Check if all cards on the gameboard is dead. If dead, then remvove the image and card completely
		// 5) If opponent has no card on the gameboard, attack the opponent directly
				
		currentAttackIB = yourButton;
		TextureRegion TEMP_C = new TextureRegion(yourCard.getClickedTexture());
		TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
		yourButton.setBackground(TEMP_CARD);
		
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
	public void EnemyGameBoardCardClicked(Card opponentCard, ImageButton enemyButton)
	{
		// ============= REMOVE WHAT'S ON THE PLAYER BOARD =============
		removeAll();
		TextureRegion TEMP_C = new TextureRegion(((CreatureCard)opponentCard).getClickedTexture());
		TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
		ImageButtonStyle _oldStyle = enemyButton.getStyle();
		_oldStyle.imageUp = TEMP_CARD;
		enemyButton.setStyle(_oldStyle);
		// Once here, the user wants to attack 
		System.out.println(attackInMotion);
		if (attackInMotion)
		{
			opponentCardToAttack = (CreatureCard) opponentCard;
			if (yourCard != null)
			{
				System.out.println("An attack in progress...");
				currentGame.Act(yourCard, opponentCard, player, otherPlayer);
			}
			if (opponentCardToAttack.isDead())
			{
				//remove the card from playerBoard
				otherPlayer.getPlayerBoard().remove(opponentCard);
			}
		}
		((CreatureCard)opponentCard).changeLabel(Integer.toString(((CreatureCard)opponentCard).getHP()));
		//change texture of player and enemy card back to normal
		TextureRegion tr = new TextureRegion(yourCard.getTexture());
		TextureRegionDrawable trd = new TextureRegionDrawable(tr);
		
		ImageButtonStyle yourStyle = currentAttackIB.getStyle();
		yourStyle.imageUp = trd;
        currentAttackIB.setStyle(yourStyle);

        TextureRegion tre = new TextureRegion(opponentCard.getTexture());
        TextureRegionDrawable trde = new TextureRegionDrawable(tre);
        
        ImageButtonStyle enemyStyle = enemyButton.getStyle();
        enemyStyle.imageUp = trde;
        enemyButton.setStyle(enemyStyle);

		currentAttackIB = null;
		
		this.yourCard = null;
		this.opponentCardToAttack = null;
		//change texture of opponent card
		
		// ============= DISPLAY WHAT'S ON THE PLAYER BOARD =============
		displayAll();
		updateStats();
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
