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
	private Boolean newPlayer;
	
	public Player() {}
	
	public Boolean isNew() {
		return newPlayer;
	}
	
	public List<Card> GetCardDeck() {
		return _cardDeck;
	}
	
	//what the player is going to do
	public void Act(Player opponent, Card card) {
		
	}

	public int get_hp() {
		return _hp;
	}

	public void set_hp(int _hp) {
		this._hp = _hp;
	}

	public int get_mana() {
		return _mana;
	}

	public void set_mana(int _mana) {
		this._mana = _mana;
	}

	public List<Card> get_cardDeck() {
		return _cardDeck;
	}

	public void set_cardDeck(List<Card> _cardDeck) {
		this._cardDeck = _cardDeck;
	}

	public Boolean get_turn() {
		return _turn;
	}

	public void set_turn(Boolean _turn) {
		this._turn = _turn;
	}

	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
}

