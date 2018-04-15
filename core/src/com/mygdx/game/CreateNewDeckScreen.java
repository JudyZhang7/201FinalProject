package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import gamelogic.ThisGame;
import user.ActionCard;
import user.Card;
import user.CreatureCard;
import user.Deck;
import user.MagicCard;
import user.User;

public class CreateNewDeckScreen implements Screen {

	private FireplacePebble game;
	private Stage stage = new Stage();
	private TextField txfUsername;
	private TextField txfPassword;
	private ArrayList<ImageButton> myCards;
	private int numCardsRow = 4;
	private int numCardsCol = 7;
	private int ch = 125;
	private int cw = 125;
	SpriteBatch batch = new SpriteBatch();
	int buttonHeight = 200;
	int buttonWidth = 60;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    private int counter;
    private BitmapFont titleFont;
    
    //BACKEND STUFF
    private User currentUser;
    private Deck newDeck = new Deck();
    private Card [] cardDeck = new Card[20];
    private Card [] fullDeck = new Card[28];
    private int numCards = 0;
    
	public CreateNewDeckScreen(FireplacePebble g) {
		System.out.println("Create new deck screen!");
		myCards = new ArrayList<ImageButton>();
		this.game = g;
		fullDeck = game.getAllCards();
		Gdx.input.setInputProcessor(stage);
		Skin textSkin = new Skin(Gdx.files.internal(game.getSkin()));

		//BACK BUTTON
		TextButton btnBack = new TextButton ("Back", textSkin);
		btnBack.setPosition(1*w/20, 19*h/20);
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
		//FINISH DECK
		TextButton deckFinish = new TextButton ("Finish Deck", textSkin);
		deckFinish.setPosition(18*w/20, 3*h/20);
		deckFinish.setSize(buttonHeight/2, buttonWidth/2);
		deckFinish.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				if(!deckFinished()) {
					
				}
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		stage.addActor(deckFinish);
		// cards
		this.currentUser = game.getUser();
		
		for (int j = 0; j < numCardsRow; j++) {
			for (int i = 0; i < numCardsCol; i++) {
				final Card thisCard = fullDeck[i+j]; //why is this final?
				TextureRegion cardTR = new TextureRegion(thisCard.getTexture());
				TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(cardTR);
				ImageButton cardButton = new ImageButton(myTexRegionDrawable); //Set the button up
				
//				Texture TEMP_T = new Texture(Gdx.files.internal("Cards/Pig.png"));
//				TextureRegion TEMP_TR = new TextureRegion(TEMP_T);
//				TextureRegionDrawable TEMP_TRD = new TextureRegionDrawable(TEMP_TR);
//				ImageButton cardButton = new ImageButton(TEMP_TRD); //Set the button up
		        				
				cardButton.setPosition(((i*(128)) % w), (j*150) + h/4);
				cardButton.setSize(cw, ch);
				
				cardButton.addListener(new ClickListener(){
					@Override
					public void touchUp(InputEvent e, float x, float y, int point, int button) {
						if(numCards == 20) { //deck is full!
							System.out.println("Deck is full. \nIt's been created." + numCards);
							showMessage("Deck is full. \nIt's been created.");
							newDeck.addCardDeck(cardDeck);
							currentUser.addDeck(newDeck);
						}
//						Card gotCard = cardClicked(thisCard); //get the Card
						Card newCard = copyCard(thisCard);
						
						addCardToDeck(newCard);
						if(!addCardToDeck(thisCard)) { //deck is full!
							deckFinished();
						}
					}
					public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
						return true;
					}
				});
				myCards.add(cardButton);
				counter++; //increment the card
			}
		}
		
		for (int i = 0; i < myCards.size() ; i++) {
			stage.addActor(myCards.get(i));
		}
		
	}
	
	public boolean deckFinished() {
		if(cardDeck[19] == null) {
			showMessage("Not enough cards!");
			return false;
		}
		newDeck.addCardDeck(cardDeck);
		currentUser.addDeck(newDeck);
		//GOOD!
		System.out.println("Deck created!");
		game.setScreen(new DeckScreen(game));
		return true;
	}
	
	public boolean addCardToDeck(Card c) {
		//update screen to show how many cards selected
		batch.begin();
		BitmapFont titleFont64 = game.titlefont64();
		titleFont64.draw(batch, "Cards in New Deck: " + numCards, w/2, 6*h/30);
		// rendering code
		batch.end();
		
		if(numCards < 20) {
			numCards++;
			cardDeck[numCards-1] = c;
			return true;
		}
		return false;
	}
	
	public Card copyCard(Card thisCard) {
		Card newCard = null;
		if (thisCard.getMytype().equals("creature"))
		{
			int hp = ((CreatureCard)thisCard).get_hp();
			int damage = ((CreatureCard)thisCard).get_damage();
			int manaCost = ((CreatureCard)thisCard).get_manaCost();
			String name = ((CreatureCard)thisCard).getName();
			Texture text =((CreatureCard)thisCard).get_texture();
			newCard = new CreatureCard (hp, damage, manaCost, name, text);
		}
		else if(thisCard.getMytype().equals("magic")) {
			//(int hp, int damage, int manaCost, String cre, Texture img, FireplacePebble game) {
			int hp = ((MagicCard)thisCard).get_hpRep();
			int damage = ((MagicCard)thisCard).get_damage();
			int manaCost = ((MagicCard)thisCard).get_manaCost();
			String name = ((MagicCard)thisCard).get_astrological();
			Texture text =((MagicCard)thisCard).get_texture();
			newCard = new MagicCard (hp, damage, manaCost, name, text, game);
		}
		else if(thisCard.getMytype().equals("action")) {
			//public ActionCard(int manaCost, int damage, int hpReplenish, int mana, String actionName, Texture img) {
			int hp = ((ActionCard)thisCard).get_hpReplenish();
			int damage = ((ActionCard)thisCard).get_damage();
			int manaCost = ((ActionCard)thisCard).get_manaCost();
			String name = ((ActionCard)thisCard).getCardname();
			Texture text =((ActionCard)thisCard).get_texture();
			newCard = new ActionCard (manaCost, damage, hp, 0, name, text);
		}
		return newCard;
	}
	
	public void btnBackClicked() {
		game.setScreen(new DeckScreen(game));
	}
	
	public void btnLoginClicked() {
		System.out.println(txfUsername.getText());
		System.out.println(txfPassword.getText());
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void showMessage(String message) {
		batch.begin();
		titleFont = game.regfont32();
		titleFont.setColor(Color.WHITE);
		titleFont.draw(batch, message, 3*w/4, 4*h/30);
		// rendering code
		batch.end();
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 100/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		batch.begin();
		
		titleFont = game.titlefont128();
		titleFont.setColor(Color.WHITE);
		titleFont.draw(batch, "Cards", w/4, 29*h/30);
		
		BitmapFont titleFont64 = game.titlefont64();
		titleFont64.draw(batch, "Cards in New Deck: " + numCards, w/6, 6*h/30);
		// rendering code
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
