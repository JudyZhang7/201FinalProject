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
    private TextureRegionDrawable trd;
    
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
				if(!deckFinished()) {}
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		stage.addActor(deckFinish);
		// cards
		this.currentUser = game.getUser();
		
		int counterr = 0;
		for (int j = 0; j < numCardsRow; j++) {
			for (int i = 0; i < numCardsCol; i++) {
				final Card thisCard = fullDeck[counterr]; //why is this final?
				counterr++;
				TextureRegion cardTR = new TextureRegion(thisCard.getTexture());
				TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(cardTR);
				final ImageButton cardButton = new ImageButton(myTexRegionDrawable); //Set the button up
		        				
				cardButton.setPosition(((i*(128)) % w), (j*150));
				cardButton.setSize(cw, ch);
				cardButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						Card newCard = copyCard(thisCard);
						if(!addCardToDeck(newCard)) { //deck is full!
							deckFinished();
						}
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
		titleFont64.draw(batch, "Cards in \nNew Deck: \n" + numCards, 3*w/4, 18*h/30);
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

	public boolean showMessage(String message, Card thisCard) {
		batch.begin();
		BitmapFont titleFont64 = game.titlefont64();
		titleFont64.draw(batch, "Cards in \nNew Deck: \n" + numCards, 3*w/4, 28*h/30);
		// rendering code
		batch.end();
		return false;
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 100/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Texture texture = new Texture("profile.jpg");
		TextureRegion mainBackground = new TextureRegion(texture, 0, 0, 1920, 1200);
		batch.begin();
		batch.draw(mainBackground, 0, 0, w, h);

		titleFont = game.titlefont128();
		titleFont.setColor(Color.WHITE);
		titleFont.draw(batch, "Cards", w/4, 29*h/30);
		
		BitmapFont titleFont64 = game.titlefont64();
		titleFont64.draw(batch, "Cards in \nNew Deck: \n" + numCards, 3*w/4, 18*h/30);
		// rendering code
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
