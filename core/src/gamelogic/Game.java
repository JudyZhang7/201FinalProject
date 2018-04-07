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
		while(winner == 0) {
			p1.Act();
			p2.Act();
			
			if(p1.GetHP() <= 0) {
				winner = 2;
			}
			else if(p2.GetHP() <= 0) {
				winner = 1;
			}
			UpdateWL();
		}
	}
	
	public void UpdateWL() {
		if (winner == 1) {
			p1.GetUser().AddWin();
			p2.GetUser().AddLoss();
		}
		else if(winner == 2) {
			p1.GetUser().AddLoss();
			p2.GetUser().AddWin();
		}
	}
}
