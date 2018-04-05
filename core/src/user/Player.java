package user;

import java.util.List;

public class Player {
	private int _hp;
	private int _mana;
	private List<Card> _cardDeck;
	private Boolean _turn;
	private User myUser;
	
	public Player() {
		
	}
	
	public List<Card> GetCardDeck() {
		return _cardDeck;
	}
	
	public void Act() {
		
	}
	
	public User GetUser() {
		return myUser;
	}
	
	public int GetHP() {
		return _hp;
	}
}

