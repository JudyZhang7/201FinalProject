package gamelogic;

import user.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.game.*;

public class ThisGame {
	private Player p1; //is the currentPlayer
	private Player p2;
	private Player currentPlayer;
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	FireplacePebble game;

	public ThisGame(Player first, Player second) {
		//first will always be the current player, second computer.
		p1 = first;
		p2 = second;
	}
	
	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public boolean Act(Card selected, Card target, Player you, Player opp) {
		String selectedtype = selected.getMytype();
		if(selectedtype.equalsIgnoreCase("creature")) {
			if(((CreatureCard)selected).Attack((CreatureCard)target, opp, you)){
				System.out.println("creature card is acting.");
				return true;
			}
		}
		else if(selectedtype.equalsIgnoreCase("magic")) {
			if(((MagicCard)selected).AstroEffect(you, opp)) {
				System.out.println("magic card is acting.");
				return true;
			}
		}
		else if(selectedtype.equalsIgnoreCase("action")) {
			if(((ActionCard)selected).ActionEffect(you, opp)) {
				System.out.println("action card is acting.");
				return true;
			}
		}
		return false;
	}
}
