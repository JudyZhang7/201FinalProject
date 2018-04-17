package user;

import java.util.List;

public class User {
	//object that holds all of the user's decks
	private Decks _decks;
	
	private String _Username;
	private String _Password;
	private Player _player;
	
	private int _wins;
	private int _losses;
	private int _level;
	
	// Dummy function
	public void printUsername()
	{
		System.out.println("Username is: " + _Username);
	}
	
	public User(Decks decks, String username, String password, Player player, int wins, int losses, int level) {
		_decks = decks;
		_Username = username;
		_Password = password;
		_player = player;
		_wins= wins;
		_losses = losses;
		_level = level;
	}
	
	public User(Player player) {
		_player = player;
	}
	
	public User(String username, String password) {
		this._Username = username;
		this._Password = password;
		this._player = new Player();
		this._decks = new Decks();
		this._wins = 0;
		this._losses = 0;
		this._level = 1;
	}
	
	public void addDeck(Deck d) {
		if(_decks == null) {
			System.out.println("decks is null");
		}
		if(d == null) {
			System.out.println("deck is null - the object");
		}
		_decks.addDeck(d);
	}
	
	public Deck getTopDeck() {
		if(_decks == null) {
			return null;
		}
		if(_decks.get_decks().isEmpty()) {
			return null;
		}
		return _decks.get_decks().get(0); //the first deck
	}
	public Decks get_decks() {
		return _decks;
	}

	public void set_decks(Decks _decks) {
		this._decks = _decks;
	}

	public String get_Username() {
		return _Username;
	}

	public void set_Username(String _Username) {
		this._Username = _Username;
	}

	public String get_Password() {
		return _Password;
	}

	public void set_Password(String _Password) {
		this._Password = _Password;
	}

	public Player get_player() {
		return _player;
	}

	public void set_player(Player _player) {
		this._player = _player;
	}

	public int get_wins() {
		return _wins;
	}

	public void set_wins(int _wins) {
		this._wins = _wins;
	}

	public int get_losses() {
		return _losses;
	}

	public void set_losses(int _losses) {
		this._losses = _losses;
	}

	public int get_level() {
		return _level;
	}

	public void set_level(int _level) {
		this._level = _level;
	}

	public void AddWin() {
		_wins++;
	}
	public void AddLoss() {
		_losses++;
	}
	public String getUsername() {
		return _Username;
	}
}