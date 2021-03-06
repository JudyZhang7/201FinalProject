package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import user.Card;
import user.Deck;
import user.Decks;
import user.User;

public class DeckScreen implements Screen {

	private FireplacePebble game;
	private Stage stage = new Stage();
	private TextField txfUsername;
	private TextField txfPassword;
	private ArrayList<ImageButton> myCards;
	private ArrayList<ImageButton> myDecks;
	private int numCardsRow = 4;
	private int numCardsCol = 5;
	private int ch = 125;
	private int cw = 125;
	SpriteBatch batch = new SpriteBatch();
	int buttonHeight = 200;
	int buttonWidth = 60;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    private BitmapFont titleFont;
    
    //BACKEND STUFF
    User currentUser;
	Deck currentDeck;
	Card [] cardDeck;
	List<Deck> currentDecks;
	public DeckScreen(FireplacePebble g) {
		myCards = new ArrayList<ImageButton>();
		this.game = g;
		Gdx.input.setInputProcessor(stage);
		
		Skin textSkin = new Skin(Gdx.files.internal(game.getSkin()));
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
		
		TextButton newDeck = new TextButton ("Create New Deck", textSkin);
		newDeck.setPosition(15*w/20, 14*h/20);
		newDeck.setSize(buttonHeight, buttonWidth);
		newDeck.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				newDeckClicked();
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		stage.addActor(newDeck);
		// cards
		this.currentUser = game.getUser();
		this.currentDeck = currentUser.getTopDeck();
		if(currentDeck== null) {
			return;
		}
		cardDeck = currentUser.getTopDeck().getCardDeck();
		currentDecks = currentUser.get_decks().get_decks();
		int counter = 0;
		for (int j = 0; j < numCardsRow; j++) {
			for (int i = 0; i < numCardsCol; i++) {
				final Card thisCard = cardDeck[counter]; //why is this final?
				TextureRegion cardTR = new TextureRegion(thisCard.getTexture());
				TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(cardTR);
				ImageButton cardButton = new ImageButton(myTexRegionDrawable); //Set the button up
		        				
				cardButton.setPosition(((i*(150)) % w)+100, (j*150)+50);
				cardButton.setSize(cw, ch);
				
				cardButton.addListener(new ClickListener(){
					@Override
					public void touchUp(InputEvent e, float x, float y, int point, int button) {
						Card gotCard = cardClicked(thisCard); //get the Card
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
		System.out.println("Current deck size: " + currentDecks.size());
		for(int i = 0; i < currentDecks.size(); i++) {
			final Card [] theseCards = currentDecks.get(i).getCardDeck();
			TextButton thisDeck = new TextButton ("Deck " + i, textSkin);
			thisDeck.setPosition(15*w/20, (12*h/20 - ch*i/2));
			thisDeck.setSize(buttonHeight, buttonWidth);
			thisDeck.addListener(new ClickListener(){
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button) {
					deckClicked(theseCards);
				}
				public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
					return true;
				}
			});
			stage.addActor(thisDeck);
		}
	}
	public void deckClicked(Card[]c) {
		List<Card>newdeckofcards = new ArrayList<Card>();
		for(int i = 0; i < 20; i++) {
			newdeckofcards.add(c[i]);
		}
		currentUser.get_player().set_cardDeck(newdeckofcards);
		int counter = 0;
		for (int j = 0; j < numCardsRow; j++) {
			for (int i = 0; i < numCardsCol; i++) {
				final Card thisCard = c[counter]; //why is this final?
				TextureRegion cardTR = new TextureRegion(thisCard.getTexture());
				TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(cardTR);
				ImageButton cardButton = new ImageButton(myTexRegionDrawable); //Set the button up
		        				
				cardButton.setPosition(((i*(150)) % w)+100, (j*150)+50);
				cardButton.setSize(cw, ch);
				
				cardButton.addListener(new ClickListener(){
					@Override
					public void touchUp(InputEvent e, float x, float y, int point, int button) {
						Card gotCard = cardClicked(thisCard); //get the Card
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
	public Card cardClicked(Card c) {
		return c;
	}
	public void btnBackClicked() {
		game.setScreen(new ProfileScreen(game));
	}
	public void newDeckClicked() {
		game.setScreen(new CreateNewDeckScreen(game));
	}
	public void btnLoginClicked() {
		System.out.println(txfUsername.getText());
		System.out.println(txfPassword.getText());
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
		
		Texture texture = new Texture("profile.jpg");
		TextureRegion mainBackground = new TextureRegion(texture, 0, 0, 1920, 1200);
		batch.begin();
		batch.draw(mainBackground, 0, 0, w, h);

		
		titleFont = game.titlefont128();
		titleFont.setColor(Color.WHITE);
		titleFont.draw(batch, "Cards", w/4, 29*h/30);
		
		titleFont.draw(batch, "Decks", 3*w/4, 29*h/30);
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