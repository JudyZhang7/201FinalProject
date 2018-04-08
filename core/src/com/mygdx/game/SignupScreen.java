package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SignupScreen implements Screen{
	private SpriteBatch batch;
    private BitmapFont font;
	private Game game;
	private Stage stage;
	private TextField txfUsername;
	private TextField txfPassword;
	
	public SignupScreen(Game g) {
		batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		//LOGIN BUTTON
		TextButton btnLogin = new TextButton ("Sign Up", textSkin);
		btnLogin.setPosition(300, 200);
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
		batch.begin();
        font.draw(batch, "Username", 300, 400);
        font.draw(batch, "Password", 300, 345);
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
