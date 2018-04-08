package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	SpriteBatch batch;
	
	OrthographicCamera camera;
	
	public DeckScreen(Game g) {
		myCards = new ArrayList<TextButton>();
		this.game = g;
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		//LOGIN BUTTON
		TextButton btnLogin = new TextButton ("Back to Game", textSkin);
				
		// login button
		btnLogin.setPosition(100, 1000);
		btnLogin.setSize(300, 60);
		
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
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// rendering code
//		batch.draw(Assets.mySpriteList.get(4), 0, 0);
		batch.draw(Assets.mySpriteList.get(8), 0, 0, 150, 150);
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
