package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameBoardPage extends ApplicationAdapter {
	private SpriteBatch batch;
	private Stage stage;
	private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 150/255f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
