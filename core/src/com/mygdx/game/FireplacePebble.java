package com.mygdx.game;

import com.badlogic.gdx.Game;

public class FireplacePebble extends Game{
	
	@Override
	public void create() {
		this.setScreen(new FrontPage(this));
	}
	
	public void render() {
		super.render();
	}
}
