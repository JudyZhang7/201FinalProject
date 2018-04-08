package com.mygdx.game;

import com.badlogic.gdx.Game;

import user.AccessSQLDatabase;

public class FireplacePebble extends Game{
	
	@Override
	public void create() {
		Assets.load();
		LoginScreen lScreen = new LoginScreen(this);
		AccessSQLDatabase acc = new AccessSQLDatabase(lScreen);
		this.setScreen(new DeckScreen(this));
		this.setScreen(new GameBoardPage(this));
	}
	
	public void render() {
		super.render();
	}
}
