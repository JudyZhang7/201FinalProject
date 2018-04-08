package user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public class Deck implements Serializable{
	//card deck of 20 cards
	private Card [] cardDeck = new Card[20];
	
	public void shuffle() {
		Collections.shuffle(Arrays.asList(cardDeck));

	}

}
