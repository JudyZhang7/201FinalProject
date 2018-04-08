package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class DeckScreen implements Screen {

	private Game game;
	private Stage stage;
	private TextField txfUsername;
	private TextField txfPassword;
	private ArrayList<TextButton> myCards;
	private int numCardsRow = 5;
	private int numCardsCol = 6;
	
	public DeckScreen(Game g) {
		myCards = new ArrayList<TextButton>();
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		//LOGIN BUTTON
		TextButton btnLogin = new TextButton ("Back to Game", textSkin);
		
//		TextButton cardButton = new TextButton("", card1);
		
		// login button
		btnLogin.setPosition(100, 1000);
		btnLogin.setSize(300, 60);
		
		btnLogin.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnLoginClicked();
			}
		});
		//LOGIN FIELDS
		txfUsername = new TextField("", textSkin);
		txfUsername.setPosition(300, 300);
		stage.addActor(txfUsername);
		txfPassword = new TextField("", textSkin);
		txfPassword.setPosition(300, 350);
		stage.addActor(txfPassword);
		
		stage.addActor(btnLogin);
		
		// cards
		for (int j = 0; j < numCardsRow; j++) {
			for (int i = 0; i < numCardsCol; i++) {
				TextButton cardButton = new TextButton("", textSkin);
				cardButton.setPosition(((i*(200)) % 1150) + 300, (j*150) + 200);
				cardButton.setSize(125, 125);
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
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
