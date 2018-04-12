package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import user.AccessSQLDatabase;
import user.User;

public class FireplacePebble extends Game{
	private User user;
	
	
	@Override
	public void create() {
//		Assets.load();
//		this.setScreen(new LoginScreen(this));
//		this.setScreen(new DeckScreen(this));
//		this.setScreen(new ProfileScreen(this));
		
		
		//THIS IS THE START
		this.setScreen(new FrontPage(this));
		
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public void render() {
		super.render();
	}
}
