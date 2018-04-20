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

	private int winner = 0;
	FireplacePebble game;
	
	//private Player mPlayer;
	private Card p1Card;
	private Card p2Card;
	
	// set p1 card and p2 card to be played
	public void setCards(Card p1Card, Card p2Card)
	{
		this.p1Card = p1Card;
		this.p2Card = p2Card;
	}
	
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

	public int getWinner() {
		return winner;
	}
	
	public void setWinner(int winner) {
		this.winner = winner;
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
	
	public void UpdateWL() {
		if (winner == 1) {
			p1.getMyUser().AddWin();
			p2.getMyUser().AddLoss();
		}
		else if(winner == 2) {
			p1.getMyUser().AddLoss();
			p2.getMyUser().AddWin();
		}
	}
}
