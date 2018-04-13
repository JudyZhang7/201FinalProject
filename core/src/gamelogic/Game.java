package gamelogic;

import user.*;
import com.mygdx.game.*;

public class Game {
	private Player p1;
	private Player p2;
	private int winner = 0;
	
	public Game(Player first, Player second) {
		p1 = first;
		p2 = second;
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
			
		}
	}
	
	
	public void loadDefaultDeck() {
		CreatureCard ratCard = new CreatureCard(3, 1, 2, "rat", Assets.mySpriteList.get(0));
		CreatureCard oxCard = new CreatureCard();
		CreatureCard tigerCard = new CreatureCard();
		CreatureCard rabbitCard = new CreatureCard();
		CreatureCard dragonCard = new CreatureCard();
		CreatureCard snakeCard = new CreatureCard();
		CreatureCard horseCard = new CreatureCard();
		CreatureCard goatCard = new CreatureCard();
		CreatureCard monkeyCard = new CreatureCard();
		CreatureCard roosterCard = new CreatureCard();
		CreatureCard dogCard = new CreatureCard();
		CreatureCard pigCard = new CreatureCard();
		
		///
		MagicCard Scorpio = new MagicCard();
		MagicCard Sagittarius = new MagicCard();
		MagicCard Capricorn = new MagicCard();
		MagicCard Aquarius = new MagicCard();
		MagicCard Pisces = new MagicCard();
		MagicCard Aries = new MagicCard();
		MagicCard Taurus = new MagicCard();
		MagicCard Gemini = new MagicCard();
		MagicCard Cancer = new MagicCard();
		MagicCard Leo = new MagicCard();
		MagicCard Virgo = new MagicCard();
		MagicCard Libra = new MagicCard(type);
		
		
		
	}
	
}
