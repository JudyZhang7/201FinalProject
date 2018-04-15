package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import user.Player;
import user.User;

public class FrontPage implements Screen
{	
	// Private Variables
	private FireplacePebble game; // Needed
	private Stage stage; // Needed 
	private Skin skin; // Needed 
	private TextButton loginButton;
	private TextButton SignUpButton;
	private TextButton GuestButton;
	public static Texture texture;
	public static TextureRegion mainBackground;
    private SpriteBatch spriteBatch = new SpriteBatch();
    
//    // NEW TOAST
//    private Toast.ToastFactory toastFactory;
//    private final List<Toast> toasts = new LinkedList<Toast>();
//    //private final List<Toast> toasts = new ArrayList<Toast>();
//    //private Toast toast;
//    // NEW TOAST
    
	int buttonHeight = 200;
	int buttonWidth = 60;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
	// Constructor
	public FrontPage(FireplacePebble game)
	{  
		this.game = game;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage); // Input to point to the stage
		skin = new Skin(Gdx.files.internal(game.getSkin()));
		
//		// TOAST create factory
//		//Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
//	    toastFactory = new Toast.ToastFactory.Builder().font(game.regfont20).positionY(735).build();
//		toastLong("Achievement Unlocked! Levelled up!");
//		toastShort("Hello World!");
//		// TOAST create factory
		
		loginButton = new TextButton("Login", skin); // Creating a button
		loginButton.setPosition(250, h/4);// Setting the position of the button
		loginButton.setSize(buttonHeight, buttonWidth); // Setting the size of the button
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
		
		SignUpButton = new TextButton("Sign Up", skin);
		SignUpButton.setPosition((w/2) - 100 , h/4);
		SignUpButton.setSize(buttonHeight, buttonWidth);
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
		
		GuestButton = new TextButton("Guest", skin);
		GuestButton.setPosition(w-450, h/4);
		GuestButton.setSize(buttonHeight, buttonWidth);
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
		game.setScreen(new LoginScreen(game)); // Go to the login page
	}
	
	// Method for if SignUpButton is clicked
	public void SignUpButtonClicked()
	{
		System.out.println("Sign Button Clicked!");
		game.setScreen(new SignupScreen(game)); // Go to the Sign Up page
	}
	
	// Method for if GuestButton is clicked
	public void GuestButtonClicked()
	{
		System.out.println("Guest Button Clicked!");
		game.setUser(new User("Guest", ""));
		game.setScreen(new StartGameScreen(game)); // Go to the Game Board page
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 100/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		texture = new Texture("Fireplace Pebble.png");
		mainBackground = new TextureRegion(texture, 0, 0, 1920, 1080);
		spriteBatch.begin();
		spriteBatch.draw(mainBackground, 0, 0, w, h);
        spriteBatch.end();
		
//        // NEW TOAST
//        // handle toast queue and display
//        Iterator<Toast> it = toasts.iterator();
//        while(it.hasNext()) 
//        {
//            Toast t = it.next();
//            if (!t.render(delta)) 
//            {
//                it.remove(); // toast finished -> remove
//            } 
//            else 
//            {
//                break; // first toast still active, break the loop
//            }
//        }
//        // NEW TOAST
        
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
