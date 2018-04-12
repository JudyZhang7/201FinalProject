package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import user.AccessSQLDatabase;

public class FireplacePebble extends Game{
	
	@Override
	public void create() {
//		Assets.load();
//		this.setScreen(new LoginScreen(this));
//		this.setScreen(new DeckScreen(this));
//		this.setScreen(new ProfileScreen(this));
		this.setScreen(new SignupScreen(this));
		
	}
	
	public void render() {
		super.render();
	}
}
