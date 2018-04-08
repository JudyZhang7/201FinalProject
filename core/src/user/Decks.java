package user;

import java.io.Serializable;
import java.util.List;

public class Decks implements Serializable{
	private List <Deck> _decks;
	public Decks(List <Deck> decks) {
		this._decks = decks;
	}
	public Decks() {}
}
