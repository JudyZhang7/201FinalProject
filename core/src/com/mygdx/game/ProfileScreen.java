package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class ProfileScreen implements Screen 
{
	// Private Variables
	private Game game;
	private Stage stage;
	private SpriteBatch batch;
	private BitmapFont font;
	private Skin skin;
	private Image profPic;
	
	// Public Variables
	public static final int colWidth = Gdx.graphics.getWidth() / 8;
    public static final int rowHeight = Gdx.graphics.getHeight() / 8;
    public static final int centerX = Gdx.graphics.getWidth() / 2;
    public static final int centerY = Gdx.graphics.getHeight() / 2;
	
	public ProfileScreen(Game g) 
	{
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		// Profile Page Heading
		/*batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2, 2);*/
		Label gameTitle = new Label("Player Profile", skin);
		gameTitle.setFontScale(3, 3);
		gameTitle.setSize(colWidth * 2, rowHeight * 2);
		gameTitle.setPosition(centerX - gameTitle.getWidth() / 2, centerY + rowHeight);
        gameTitle.setAlignment(Align.center);
        
        // Add in the image of something
        
        
        // Add in levels, wins and losses
        int level = 1;
        int wins = 0;
        int losses = 0;
        Label gameLevel = new Label("Level: " + level, skin);
		gameLevel.setFontScale(2, 2);
		gameLevel.setSize(colWidth * 2, rowHeight * 2);
		gameLevel.setPosition((centerX - gameTitle.getWidth() / 2) + 600, (centerY + rowHeight));
        //gameLevel.setAlignment(Align.center);
        
		Label gameWins = new Label("Wins: " + wins, skin);
		gameWins.setFontScale(2, 2);
		gameWins.setSize(colWidth * 2, rowHeight * 2);
		gameWins.setPosition((centerX - gameTitle.getWidth() / 2) + 600, (centerY + rowHeight) - 50);
        //gameWins.setAlignment(Align.center);
		
		Label gameLosses = new Label("Losses: " + losses, skin);
		gameLosses.setFontScale(2, 2);
		gameLosses.setSize(colWidth * 2, rowHeight * 2);
		gameLosses.setPosition((centerX - gameTitle.getWidth() / 2) + 600, (centerY + rowHeight) - 100);
        //gameLosses.setAlignment(Align.center);
		
		stage.addActor(gameTitle);
		stage.addActor(gameLevel);
		stage.addActor(gameWins);
		stage.addActor(gameLosses);
	}
	
//	public void btnLoginClicked() {
//		System.out.println(txfUsername.getText());
//		System.out.println(txfPassword.getText());
//	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 100/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		batch.begin();
//        font.draw(batch, "Player Profile", 300, 650);
//        batch.end();
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
//		batch.dispose();
//        font.dispose();
	}
	
}
