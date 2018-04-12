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
	
	public void printCurrentUser() {
		System.out.println("Current logged in user: " + user.getUsername());
	}
	
	public void setUser(User u) {
		this.user = u;
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
	
	public void render() {
		super.render();
	}
}
