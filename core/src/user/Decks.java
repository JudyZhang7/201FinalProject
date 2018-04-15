package user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Decks implements Serializable{
	private List <Deck> _decks;
	
	public Decks(List <Deck> decks) {
		this._decks = decks;
	}
	public Decks() {
		this._decks = new ArrayList<Deck>();
	}
	
	public List<Deck> get_decks() {
		return _decks;
	}
	
	public void addDeck(Deck d) {
		_decks.add(d);
	}
	
}
