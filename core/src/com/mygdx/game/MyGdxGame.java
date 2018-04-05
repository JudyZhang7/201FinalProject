package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class MyGdxGame extends ApplicationAdapter {
	AssetManager libGDXAssetManager;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Stage stage;
	
	@Override
	public void create () {
		libGDXAssetManager = new AssetManager();

		this.stage = new Stage();

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10, 10);
		
		login();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 100/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "WELCOME", 1536, 960);
		stage.draw();
		stage.act();
		batch.end();
	}
	
	public void login () {
		TextField usernameTextField = new TextField("", AssetLoader.defaultSkin);
		usernameTextField.setPosition(24,73);
		usernameTextField.setSize(88, 14);

		stage.add(usernameTextField);            // <-- Actor now on stage 
		Gdx.input.setInputProcessor(stage);
		
		
		//get user info for LOGIN
//		MyTextInputListener listenerPassword= new MyTextInputListener();
//		Gdx.input.getTextInput(listenerPassword, "Password", "", "Password");
//		MyTextInputListener listenerUsername= new MyTextInputListener();
//		Gdx.input.getTextInput(listenerUsername, "Username", "", "Username");
				
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
