package com.mygdx.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import gamelogic.AchievementThread;
import user.AccessSQLDatabase;
import user.User;

public class FireplacePebble extends Game{
	private User user;
	private String skin = "clean-crispy/skin/clean-crispy-ui.json";
	FreeTypeFontGenerator titleText;
	FreeTypeFontParameter titleParam;
	
	public BitmapFont titlefont128;
	public BitmapFont titlefont64;
	public BitmapFont regfont32;
	public BitmapFont regfont20;
	public BitmapFont regfont16;
	
    // NEW TOAST
    private Toast.ToastFactory toastFactory;
    private final List<Toast> toasts = new LinkedList<Toast>();
    //private final List<Toast> toasts = new ArrayList<Toast>();
    //private Toast toast;
    // NEW TOAST

	@Override
	public void create() {
		FreeTypeFontGenerator titleText = new FreeTypeFontGenerator(Gdx.files.internal("JMH.ttf"));
		FreeTypeFontParameter titleParam = new FreeTypeFontParameter();
		
		titleParam.size = 128;
		titlefont128 = titleText.generateFont(titleParam); // font size 128 pixels
		titleParam.size = 64;
		titlefont64 = titleText.generateFont(titleParam); // font size 64
		titleText.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator regText = new FreeTypeFontGenerator(Gdx.files.internal("DroidSans.ttf"));
		FreeTypeFontParameter regParam = new FreeTypeFontParameter();
		
		regParam.size = 32;
		regfont32 = regText.generateFont(regParam); // font size 32 pixels
		regParam.size = 16;
		regfont16 = regText.generateFont(regParam); // font size 16
		regParam.size = 20;
		regfont20 = regText.generateFont(regParam); 
		regText.dispose(); // don't forget to dispose to avoid memory leaks!
		
//		Assets.load();
//		this.setScreen(new StartGameScreen(this));
//		this.setScreen(new DeckScreen(this));
//		this.setScreen(new ProfileScreen(this));
		
		// TOAST create factory
		//Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	    toastFactory = new Toast.ToastFactory.Builder().font(regfont20).positionY(735).build();
		toastLong("Achievement Unlocked! Levelled up!");
//		toastShort("Hello World!");
		// TOAST create factory

		//THIS IS THE START
<<<<<<< HEAD
		this.setScreen(new GameBoardPage(this));
=======
		this.setScreen(new CreateNewDeckScreen(this));
>>>>>>> 75f649b2af6962c169f9881abdb32de11149945c
		
	}
	
	public BitmapFont titlefont64() {
		return titlefont64;
	}
	
	public BitmapFont titlefont128() {
		return titlefont128;
	}
	
	public BitmapFont regfont16() {
		return regfont16;
	}
	
	public BitmapFont regfont20() {
		return regfont20;
	}
	
	public BitmapFont regfont32() {
		return regfont32;
	}
	
	public String getSkin() {
		return skin;
	}
	public void printCurrentUser() {
		System.out.println("Current logged in user: " + user.getUsername());
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public User getUser() {
		return user;
	}

	public String getCurrentUsername() {
		return user.getUsername();
	}
	
	public int getCurrentWins() {
		return (user.get_wins());
	}
	
	public int getCurrentLoses() {
		return (user.get_losses());
	}
	
	public int getCurrentLevel() {
		return (user.get_level());
	}
	
	// Displays long toast
	public void toastLong(String text) 
	{
		toasts.add(toastFactory.create(text, Toast.Length.LONG));
	}

	// Displays short toast
	public void toastShort(String text) 
	{
		toasts.add(toastFactory.create(text, Toast.Length.SHORT));
	}
	
	public void render() {
		super.render();
		
      // NEW TOAST
      // handle toast queue and display
      Iterator<Toast> it = toasts.iterator();
      while(it.hasNext()) 
      {
          Toast t = it.next();
          if (!t.render(Gdx.graphics.getDeltaTime())) 
          {
              it.remove(); // toast finished -> remove
          } 
          else 
          {
              break; // first toast still active, break the loop
          }
      }
      // NEW TOAST
	}
}
