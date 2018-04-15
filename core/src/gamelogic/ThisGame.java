package gamelogic;

import user.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.game.*;

public class ThisGame {

	private Player p1;
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
	
	public void playMagicCard(MagicCard yourCard) {
		yourCard.AstroEffect();
	}
//	public void playCreatureCard(CreatureCard yourCard, CreatureCard opponentCard) {
//		yourCard.Attack(opponentCard, opponentCard.getmPlayer()); 
//	}
	
	public void playActionCard(ActionCard yourCard, Player opponent, CreatureCard opponentCreature) {
		yourCard.ActionEffect(yourCard.getmPlayer(), opponent, opponentCreature);
	}
	
	public void setWinner(int winner) {
		this.winner = winner;
	}

	public void Act(Card selected, Card target) {
		String selectedtype = selected.getMytype();
		String targetedtype = target.getMytype();
		
		if(selectedtype.equalsIgnoreCase("creature")) {
			((CreatureCard)selected).Attack((CreatureCard)target, target.getmPlayer());
		}
		else if(selectedtype.equalsIgnoreCase("magic")) {
			((MagicCard)selected).AstroEffect();
		}
		else if(selectedtype.equalsIgnoreCase("action")) {
			((ActionCard)selected).ActionEffect(selected.getmPlayer(), target.getmPlayer(), (CreatureCard)target);
		}
	}
	
	public void run() {
		while(winner == 0) {
			// if player-card interaction chosen
			p1.Act(p2, p1Card);
			p2.Act(p1, p2Card);
			// if card-card interaction chosen
			Act(p1Card, p2Card);
			Act(p2Card, p1Card);
			
			if(p1.isDead()) {
				winner = 2;
			}
			else if(p2.isDead()) {
				winner = 1;
			}
			UpdateWL();
			GameStart();
		}
		
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
	
	public void GameStart() {
		if (User.get_Username().equals("Guest")) {
			// don't load from database
		}
		//mPlayer = new Player();
	}
	
}
