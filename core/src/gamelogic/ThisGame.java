package gamelogic;

import user.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.game.*;

public class ThisGame {

	private Player p1;
	private Player p2;
	private int winner = 0;
	FireplacePebble game;
	
	private Player mPlayer;
	
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
	
	public void playActionCard(ActionCard yourCard, Player opponent) {
		
	}
	
	public void setWinner(int winner) {
		this.winner = winner;
	}

	public Player getmPlayer() {
		return mPlayer;
	}

	public void setmPlayer(Player mPlayer) {
		this.mPlayer = mPlayer;
	}

	public void run() {
		while(winner == 0) {
//			p1.Act();
//			p2.Act();
			
			if(p1.get_hp() <= 0) {
				winner = 2;
			}
			else if(p2.get_hp() <= 0) {
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
		mPlayer = new Player();
	}
	
}
