package gamelogic;

import user.*;
import com.mygdx.game.*;

public class ThisGame {
	private Player p1;
	private Player p2;
	private int winner = 0;
	
	private Player mPlayer;
	
	public ThisGame(Player first, Player second) {
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
	
	
	public void loadDefaultDeck() {
		CreatureCard ratCard = new CreatureCard(3, 1, 2, "rat", Assets.mySpriteList.get(8));
		CreatureCard oxCard = new CreatureCard(3, 1, 2, "ox", Assets.mySpriteList.get(5));
		CreatureCard tigerCard = new CreatureCard(3, 1, 2, "tiger", Assets.mySpriteList.get(11));
		CreatureCard rabbitCard = new CreatureCard(3, 1, 2, "rabbit", Assets.mySpriteList.get(7));
		CreatureCard dragonCard = new CreatureCard(3, 1, 2, "dragon", Assets.mySpriteList.get(1));
		CreatureCard snakeCard = new CreatureCard(3, 1, 2, "snake", Assets.mySpriteList.get(10));
		CreatureCard horseCard = new CreatureCard(3, 1, 2, "horse", Assets.mySpriteList.get(3));
		CreatureCard goatCard = new CreatureCard(3, 1, 2, "goat", Assets.mySpriteList.get(2));
		CreatureCard monkeyCard = new CreatureCard(3, 1, 2, "monkey", Assets.mySpriteList.get(4));
		CreatureCard roosterCard = new CreatureCard(3, 1, 2, "rooster", Assets.mySpriteList.get(9));
		CreatureCard dogCard = new CreatureCard(3, 1, 2, "dog", Assets.mySpriteList.get(0));
		CreatureCard pigCard = new CreatureCard(3, 1, 2, "pig", Assets.mySpriteList.get(6));
		
		///
		MagicCard Scorpio = new MagicCard(2, 2, 0, "Scorpio", Assets.mySpriteList.get(21));
		MagicCard Sagittarius = new MagicCard(2, 4, 0, "Sagittarius", Assets.mySpriteList.get(20));
		MagicCard Capricorn = new MagicCard(0, 3, 0, "Capricorn", Assets.mySpriteList.get(15));
		MagicCard Aquarius = new MagicCard(0, 4, 0, "Aquarius", Assets.mySpriteList.get(12));
		MagicCard Pisces = new MagicCard(0, 2, 0, "Pisces", Assets.mySpriteList.get(19));
		MagicCard Aries = new MagicCard(0, 3, 5, "Aries", Assets.mySpriteList.get(13));
		MagicCard Taurus = new MagicCard(0, 3, 3, "Taurus", Assets.mySpriteList.get(22));
		MagicCard Gemini = new MagicCard(0, 4, 0, "Gemini", Assets.mySpriteList.get(16));
		MagicCard Cancer = new MagicCard(0, 2, 0, "Cancer", Assets.mySpriteList.get(14));
		MagicCard Leo = new MagicCard(0, 2, 0, "Leo", Assets.mySpriteList.get(17));
		MagicCard Virgo = new MagicCard(2, 1, 0, "Virgo", Assets.mySpriteList.get(23));
		MagicCard Libra = new MagicCard(0, 2, 1, "Libra", Assets.mySpriteList.get(18));
		
		
		
	}
	
}
