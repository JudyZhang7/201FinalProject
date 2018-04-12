package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DeckScreen implements Screen {

	private FireplacePebble game;
	private Stage stage;
	private TextField txfUsername;
	private TextField txfPassword;
	private ArrayList<TextButton> myCards;
	private int numCardsRow = 4;
	private int numCardsCol = 5;
	private int ch = 125;
	private int cw = 125;
	SpriteBatch batch;
	int buttonHeight = 200;
	int buttonWidth = 60;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    private BitmapFont titleFont;
	OrthographicCamera camera;
	
	public DeckScreen(FireplacePebble g) {
		myCards = new ArrayList<TextButton>();
		this.game = g;
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		Skin textSkin = new Skin(Gdx.files.internal(game.getSkin()));

		//LOGIN BUTTON
		TextButton btnLogin = new TextButton ("Back to Game", textSkin);
				
		// login button
		btnLogin.setPosition(100, 1000);
		btnLogin.setSize(buttonWidth, buttonHeight);
		
		btnLogin.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnLoginClicked();
			}
		});
		
		stage.addActor(btnLogin);
		
		// cards
		for (int j = 0; j < numCardsRow; j++) {
			for (int i = 0; i < numCardsCol; i++) {
		        
				TextButton cardButton = new TextButton("", textSkin);
				cardButton.setPosition(((i*(150)) % w)+100, (j*150)+50);
				cardButton.setSize(cw, ch);
				myCards.add(cardButton);
			}
		}
		
		for (int i = 0; i < myCards.size() ; i++) {
			stage.addActor(myCards.get(i));
		}
		
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
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		titleFont = game.titlefont128();
		titleFont.setColor(Color.BLACK);
		titleFont.draw(batch, "Cards", (2*w)/5, h/12);
		
		titleFont.draw(batch, "Decks", w, h/12);
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
