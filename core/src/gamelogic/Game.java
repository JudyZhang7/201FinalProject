package gamelogic;

import user.Player;

public class Game {
	private Player p1;
	private Player p2;
	private int winner = 0;
	
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
	
	public void UpdateWL() {
		if (winner == 1) {
			
		}
		else if(winner == 2) {
			
		}
	}
}
