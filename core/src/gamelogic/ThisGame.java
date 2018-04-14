package gamelogic;

import user.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.game.*;

public class ThisGame {
	HashMap< String,CreatureCard> creatureCards = new HashMap< String,CreatureCard>();
	HashMap< String,ActionCard> actionCards = new HashMap< String,ActionCard>();
	HashMap< String,MagicCard> magicCards = new HashMap< String,MagicCard>();

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
	public void playCreatureCard(CreatureCard yourCard, CreatureCard opponentCard) {
		yourCard 
	}
	
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
	
	
	public void loadDefaultDeck(FireplacePebble game) {
		this.game = game;
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
		//PUT IN CREATURE CARD MAP
		creatureCards.put("rat", ratCard);
		creatureCards.put("ox", oxCard);
		creatureCards.put("tiger", tigerCard);
		creatureCards.put("rabbit", rabbitCard);
		creatureCards.put("dragon", dragonCard);
		creatureCards.put("snake", snakeCard);
		creatureCards.put("horse", horseCard);
		creatureCards.put("goat", goatCard);
		creatureCards.put("monkey", monkeyCard);
		creatureCards.put("rooster", roosterCard);
		creatureCards.put("dog", dogCard);
		creatureCards.put("pig", pigCard);
		
		///
		MagicCard Scorpio = new MagicCard(2, 2, 0, "scorpio", Assets.mySpriteList.get(21), this.game);
		MagicCard Sagittarius = new MagicCard(2, 4, 0, "sagittarius", Assets.mySpriteList.get(20), this.game);
		MagicCard Capricorn = new MagicCard(0, 3, 0, "capricorn", Assets.mySpriteList.get(15), this.game);
		MagicCard Aquarius = new MagicCard(0, 4, 0, "aquarius", Assets.mySpriteList.get(12), this.game);
		MagicCard Pisces = new MagicCard(0, 2, 0, "pisces", Assets.mySpriteList.get(19), this.game);
		MagicCard Aries = new MagicCard(0, 3, 5, "aries", Assets.mySpriteList.get(13), this.game);
		MagicCard Taurus = new MagicCard(0, 3, 3, "taurus", Assets.mySpriteList.get(22), this.game);
		MagicCard Gemini = new MagicCard(0, 4, 0, "gemini", Assets.mySpriteList.get(16), this.game);
		MagicCard Cancer = new MagicCard(0, 2, 0, "cancer", Assets.mySpriteList.get(14), this.game);
		MagicCard Leo = new MagicCard(0, 2, 0, "leo", Assets.mySpriteList.get(17), this.game);
		MagicCard Virgo = new MagicCard(2, 1, 0, "virgo", Assets.mySpriteList.get(23), this.game);
		MagicCard Libra = new MagicCard(0, 2, 1, "libra", Assets.mySpriteList.get(18), this.game);
		//PUT IN MAGIC CARD MAP
		magicCards.put("scorpio", Scorpio);
		magicCards.put("sagittarius", Sagittarius);
		magicCards.put("capricorn", Capricorn);
		magicCards.put("aquarius", Aquarius);
		magicCards.put("pisces", Pisces);
		magicCards.put("aries", Aries);
		magicCards.put("taurus", Taurus);
		magicCards.put("gemini", Gemini);
		magicCards.put("cancer", Cancer);
		magicCards.put("leo", Leo);
		magicCards.put("virgo", Virgo);
		magicCards.put("libra", Libra);
		
		///mana cost, damage, hp replenish, mana, actionname, sprite
		ActionCard Weapon = new ActionCard(3, 3, 0, 0, "Weapon", Assets.mySpriteList.get(28));
		ActionCard Shield = new ActionCard(2, 0, 0, 0, "Shield", Assets.mySpriteList.get(27));
		ActionCard Heal = new ActionCard(2, 0, 3, 0, "Heal", Assets.mySpriteList.get(25));
		ActionCard DoubleDamage = new ActionCard(4, 0, 0, 0, "DoubleDamage", Assets.mySpriteList.get(24));
		ActionCard Preparation = new ActionCard(3, 0, 0, 0, "Preparation", Assets.mySpriteList.get(26));
		//PUT IN ACTION CARD MAP
		actionCards.put("weapon", Weapon);
		actionCards.put("shield", Shield);
		actionCards.put("heal", Heal);
		actionCards.put("doubleDamage", DoubleDamage);
		actionCards.put("preparation", Preparation);
	}
	
}
