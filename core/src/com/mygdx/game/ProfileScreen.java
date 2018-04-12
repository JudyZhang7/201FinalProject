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
	private BitmapFont font;
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
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		// Profile Page Heading
		/*batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2, 2);*/
		String username = game.getCurrentUsername();
		Label gameTitle = new Label(username, skin);
		gameTitle.setFontScale(3, 3);
		gameTitle.setSize(colWidth * 2, rowHeight * 2);
		gameTitle.setPosition(centerX - gameTitle.getWidth() / 2, centerY + rowHeight);
        gameTitle.setAlignment(Align.center);
        
        // Add in the image of something
        
        
        // Add in levels, wins and losses
        int level = game.getCurrentLevel();
        int wins = game.getCurrentWins();
        int losses = game.getCurrentLoses();
        
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
		stage.addActor(gameTitle);
		stage.addActor(gameLevel);
		stage.addActor(gameWins);
		stage.addActor(gameLosses);
	}
	
//	public void btnLoginClicked() {
//		System.out.println(txfUsername.getText());
//		System.out.println(txfPassword.getText());
//	}
	
	public void startGameClicked() {
		game.setScreen(new StartGameScreen(game));
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
