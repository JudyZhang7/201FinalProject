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
	
	public User(String username, String password) {
		this._Username = username;
		this._Password = password;
		this._player = new Player();
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
	
}