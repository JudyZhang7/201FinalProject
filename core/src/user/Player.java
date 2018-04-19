package user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Player implements Serializable{
	private static final long serialVersionUID = 1L;

	private int _hp;
	private int _mana;
	private List<Card> _cardDeck;
	private Boolean _turn;
	private User myUser;
	private Boolean newPlayer;
	private int handSize;
	
	private ArrayList<Card> mHand = new ArrayList<Card>();
//	private ArrayList<Card> opponentHand = new ArrayList<Card>();
//	private Player opponent;
	private ArrayList<Card> playerBoard = new ArrayList<Card>();
//	private ArrayList<Card> opponentBoard = new ArrayList<Card>();
	
	// Dummy Constructor for Dummy
	public Player(int hp)
	{
		System.out.println("In Constructor!");
		this._hp = hp;
		this._mana = 5;
		_turn = true;
		myUser = null;
		handSize = 3;
		_cardDeck = null;
	}
	
	public void setCardDeck(List<Card> cardDeck) {
		_cardDeck = cardDeck;
	}
	
	public ArrayList<Card> getmHand() {
		return mHand;
	}

	public void setmHand(ArrayList<Card> mHand) {
		this.mHand = mHand;
	}

	public ArrayList<Card> getPlayerBoard() {
		return playerBoard;
	}

	public void setPlayerBoard(ArrayList<Card> playerBoard) {
		this.playerBoard = playerBoard;
	}
	
	public Player(int hp, int mana) {
		_hp = hp;
		_mana = mana;
	}
	
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
	
	public Boolean isDead() {
		if(_hp <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void drawCards() {
		if (this.get_turn()) {
			if (mHand.size() == 3) {
				double rangeMin = 0.0f;
				double rangeMax = 1.0f;
				Random r = new Random();
				double createRand = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				this.get_cardDeck().remove((int)(createRand * this.get_cardDeck().size()));
			} 
			else {
				double rangeMin = 0.0f;
				double rangeMax = 1.0f;
				Random r = new Random();
				double createRand = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				
				mHand.add(this.get_cardDeck().get((int)(createRand * this.get_cardDeck().size())));
				
				this.get_cardDeck().remove((int)(createRand * this.get_cardDeck().size()));
			}
		}
	}
	
	public void placeCardOnBoard() {
		int currHandSize = mHand.size();
		
		//place card on board
		// somehow user choose card or index
		
		playerBoard.add(null);
		
		// player has to remove card from hand
		mHand.remove(null);
	}

//	public Player getOpponent() {
//		return opponent;
//	}
//
//	public void setOpponent(Player opponent) {
//		this.opponent = opponent;
//	}
//
//	public ArrayList<Card> getOpponentBoard() {
//		return opponentBoard;
//	}
//
//	public void setOpponentBoard(ArrayList<Card> opponentBoard) {
//		this.opponentBoard = opponentBoard;
//	}
//
//	public ArrayList<Card> getOpponentHand() {
//		return opponentHand;
//	}
//
//	public void setOpponentHand(ArrayList<Card> opponentHand) {
//		this.opponentHand = opponentHand;
//	}
	
}

