package com.mygdx.game;

import com.badlogic.gdx.Game;

public class FireplacePebble extends Game{
	
	@Override
	public void create() {
		Assets.load();
		this.setScreen(new GameBoardPage(this));
	}
	
	public void render() {
		super.render();
	}
}
