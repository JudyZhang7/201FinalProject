package user;

import java.util.List;

public class Player {
	private int _wins;
	private int _hp;
	private int _mana;
	private List<Card> _cardDeck;
	private Boolean _turn;
	
	public Player() {
		
	}
	
	public List<Card> GetCardDeck() {
		return _cardDeck;
	}
	
	public void Act() {
		
	}
}

