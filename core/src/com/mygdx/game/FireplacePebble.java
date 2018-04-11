package com.mygdx.game;

import com.badlogic.gdx.Game;

import user.AccessSQLDatabase;

public class FireplacePebble extends Game{
	
	@Override
	public void create() {
//		Assets.load();
		this.setScreen(new SignupScreen(this));
//		this.setScreen(new DeckScreen(this));
//		this.setScreen(new GameBoardPage(this));
	}
	
	public void render() {
		super.render();
	}
}
