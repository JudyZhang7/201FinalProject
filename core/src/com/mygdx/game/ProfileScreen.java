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
	private FireplacePebble game;
	private Stage stage;
	private SpriteBatch batch;
	private BitmapFont usernameFont;
	private BitmapFont statsFont;
	private Skin skin;
	private Image profPic;
	private TextButton startGameButton;

	// Public Variables
	public static final int colWidth = Gdx.graphics.getWidth() / 8;
    public static final int rowHeight = Gdx.graphics.getHeight() / 8;
    public static final int centerX = Gdx.graphics.getWidth() / 2;
    public static final int centerY = Gdx.graphics.getHeight() / 2;
    
    int buttonHeight = 200;
	int buttonWidth = 60;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
	
	public ProfileScreen(FireplacePebble g) 
	{
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal(game.getSkin()));
		batch = new SpriteBatch();   
		usernameFont = game.titlefont128();
        usernameFont.setColor(Color.WHITE);
        statsFont = game.titlefont64();


		startGameButton = new TextButton("Start New Game", skin); // Creating a button
		startGameButton.setPosition(30, (7*h)/8);// Setting the position of the button
		startGameButton.setSize(buttonHeight, buttonWidth); // Setting the size of the button
		startGameButton.addListener(new ClickListener() 
		{
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button)
			{
				startGameClicked();
			}
		});
		
		stage.addActor(startGameButton);
		TextButton logout = new TextButton ("Logout", skin);
		logout.setPosition(17*w/20, 16*h/20);
		logout.setSize(buttonHeight/2, buttonWidth/2);
		logout.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				logoutClicked();
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		stage.addActor(logout);
	}
	public void logoutClicked() {
		game.setUser(null);
		game.setScreen(new FrontPage(game));
	}
	
	public void startGameClicked() {
		game.setScreen(new StartGameScreen(game));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
        String username = game.getCurrentUsername();
        int level = game.getCurrentLevel();
        int wins = game.getCurrentWins();
        int losses = game.getCurrentLoses();
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 100/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		batch.begin();
		usernameFont.draw(batch, username, w/4, (3*h)/4);
        statsFont.draw(batch, "Level : " + level, 3*w/4, (10*h)/15);
        statsFont.draw(batch, "Wins: " + wins, 3*w/4, (8*h)/15);
        statsFont.draw(batch, "Losses: " + losses, 3*w/4, (6*h)/15);
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
//		batch.dispose();
//        font.dispose();
	}
	
}
