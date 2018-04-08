package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FrontPage implements Screen
{	
	// Private Variables
	private Game game; // Needed
	private Stage stage; // Needed 
	private Skin skin; // Needed 
	private TextButton loginButton;
	private TextButton SignUpButton;
	private TextButton GuestButton;
	
	// Constructor
	public FrontPage(Game game)
	{
		this.game = game;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage); // Input to point to the stage
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		loginButton = new TextButton("Login", skin); // Creating a button
		loginButton.setPosition(300, 300);// Setting the position of the button
		loginButton.setSize(300, 60); // Setting the size of the button
		loginButton.addListener(new ClickListener() 
		{
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button)
			{
				// Changes the text in the button when clicked 
				//loginButton.setText("Here!");
				loginButtonClicked();
			}
		});
		
		SignUpButton = new TextButton("Sign-Up", skin);
		SignUpButton.setPosition(300, 150);
		SignUpButton.setSize(300, 60);
		SignUpButton.addListener(new ClickListener() 
		{
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button)
			{
				// Changes the text in the button when clicked 
				//loginButton.setText("Here!");
				SignUpButtonClicked();
			}
		});
		
		GuestButton = new TextButton("Sign-Up", skin);
		GuestButton.setPosition(150, 300);
		GuestButton.setSize(300, 60);
		GuestButton.addListener(new ClickListener() 
		{
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button)
			{
				// Changes the text in the button when clicked 
				//loginButton.setText("Here!");
				GuestButtonClicked();
			}
		});
		
		stage.addActor(loginButton); // Adding the button to the stage
		stage.addActor(SignUpButton);
		stage.addActor(GuestButton);
	}
	
	// Method for if loginButton is clicked
	public void loginButtonClicked()
	{
		System.out.println("Login Button Clicked!");
		//game.setScreen(new LoginScreen()); // Go to the login page
	}
	
	// Method for if SignUpButton is clicked
	public void SignUpButtonClicked()
	{
		System.out.println("Sign Button Clicked!");
		//game.setScreen(new SignUpScreen()); // Go to the Sign Up page
	}
	
	// Method for if GuestButton is clicked
	public void GuestButtonClicked()
	{
		System.out.println("Guest Button Clicked!");
		//game.setScreen(new GameScreen()); // Go to the Game Board page
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
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