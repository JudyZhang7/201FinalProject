package com.mygdx.game;

import java.util.Arrays;
import java.util.Collections;

public class Deck {
	//card deck of 20 cards
	private Card [] cardDeck = new Card[20];
	
	public void shuffle() {
		Collections.shuffle(Arrays.asList(cardDeck));

	}

}
