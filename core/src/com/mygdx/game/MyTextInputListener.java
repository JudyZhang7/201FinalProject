package com.mygdx.game;

import com.badlogic.gdx.Input.TextInputListener;

class MyTextInputListener implements TextInputListener {
	   @Override
	   public void input (String text) {
		   System.out.println(text);
	   }

	   @Override
	   public void canceled () {
	   }
	}
