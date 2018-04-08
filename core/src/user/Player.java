package user;

import java.io.Serializable;
import java.util.List;


public class Player implements Serializable{
	private static final long serialVersionUID = 1L;

	private int _hp;
	private int _mana;
	private List<Card> _cardDeck;
	private Boolean _turn;
	private User myUser;
	
	private String TESTNAME;
	public Player() {}
	public Player(String name) {
		TESTNAME = name;
	}
	public void setName(String name) {
		TESTNAME = name;
	}
	public String getName() {
		return TESTNAME;

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

