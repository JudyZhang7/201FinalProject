package com.mygdx.game;

import com.badlogic.gdx.Game;

public class FireplacePebble extends Game{
	
	@Override
	public void create() {
<<<<<<< HEAD
		Assets.load();
		this.setScreen(new DeckScreen(this));
=======
		this.setScreen(new GameBoardPage(this));
>>>>>>> 2558471f33733c9441dbe3d2a25652d5801bce55
	}
	
	public void render() {
		super.render();
	}
}
