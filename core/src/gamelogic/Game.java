package gamelogic;

import user.Player;

public class Game {
	private Player p1;
	private Player p2;
	
	public Game(Player first, Player second) {
		p1 = first;
		p2 = second;
	}
	
	public void Run() {
		while(true) {
			p1.Act();
			p2.Act();
		}
	}
	
	
}
