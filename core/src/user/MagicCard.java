package user;

import java.util.Random;
import gamelogic.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.FireplacePebble;
import com.mygdx.game.GameBoardPage;

public class MagicCard extends Card{
	private int _hpRep;
	private int _manaCost;
	private int _damage;
	private String _astrological;
	private Astro state;
	private FireplacePebble game;
	private int turnCounter;
	private Texture _texture;
	private Texture _clickedTexture;
	private Boolean ADEAD;
	
	private enum Astro {
		Scorpio,
		Sagittarius,
		Capricorn,
		Aquarius,
		Pisces,
		Aries,
		Taurus,
		Gemini,
		Cancer,
		Leo,
		Virgo,
		Libra;
	}
	
	// Copy Constructor
	public MagicCard(MagicCard mc)
	{
		super(type);
		this.game = mc.game;
		_hpRep = mc._hpRep;
		_damage = mc._damage;
		_manaCost = mc._manaCost;
		_astrological = mc._astrological;

		//_mSprite = sprite;
		mytype = "magic";
		_texture = mc._texture;
		_clickedTexture = mc._clickedTexture;
		
		if(mc._astrological.equalsIgnoreCase("Scorpio")) {
			state = Astro.Scorpio;
			turnCounter = 3;
		}
		else if(mc._astrological.equalsIgnoreCase("Sagitarius")) {
			state = Astro.Sagittarius;
		}
		else if(mc._astrological.equalsIgnoreCase("Capricorn")) {
			state = Astro.Capricorn;
		}
		else if(mc._astrological.equalsIgnoreCase("Aquarius")) {
			state = Astro.Aquarius;
		}
		else if(mc._astrological.equalsIgnoreCase("Pisces")) {
			state = Astro.Pisces;
		}
		else if(mc._astrological.equalsIgnoreCase("Aries")) {
			state = Astro.Aries;
		}
		else if(mc._astrological.equalsIgnoreCase("Taurus")) {
			state = Astro.Taurus;
		}
		else if(mc._astrological.equalsIgnoreCase("Gemini")) {
			state = Astro.Gemini;
		}
		else if(mc._astrological.equalsIgnoreCase("Cancer")) {
			state = Astro.Cancer;
		}
		else if(mc._astrological.equalsIgnoreCase("Leo")) {
			state = Astro.Leo;
		}
		else if(mc._astrological.equalsIgnoreCase("Virgo")) {
			state = Astro.Virgo;
		}
		else if(mc._astrological.equalsIgnoreCase("Libra")) {
			state = Astro.Libra;
		}
	}
	
	public MagicCard(int hp, int manaCost, int damage, String cre, Texture img, Texture clicked, FireplacePebble game) {
		super(type);
		System.out.println("Magic card manaCost: " + manaCost);
		this.game = game;
		_hpRep = hp;
		_damage = damage;
		_manaCost = manaCost;
		_astrological = cre;

		//_mSprite = sprite;
		mytype = "magic";
		_texture = img;
		_clickedTexture = clicked;
		
		if(cre.equalsIgnoreCase("Scorpio")) {
			state = Astro.Scorpio;
			turnCounter = 3;
		}
		else if(cre.equalsIgnoreCase("Sagitarius")) {
			state = Astro.Sagittarius;
		}
		else if(cre.equalsIgnoreCase("Capricorn")) {
			state = Astro.Capricorn;
		}
		else if(cre.equalsIgnoreCase("Aquarius")) {
			state = Astro.Aquarius;
		}
		else if(cre.equalsIgnoreCase("Pisces")) {
			state = Astro.Pisces;
		}
		else if(cre.equalsIgnoreCase("Aries")) {
			state = Astro.Aries;
		}
		else if(cre.equalsIgnoreCase("Taurus")) {
			state = Astro.Taurus;
		}
		else if(cre.equalsIgnoreCase("Gemini")) {
			state = Astro.Gemini;
		}
		else if(cre.equalsIgnoreCase("Cancer")) {
			state = Astro.Cancer;
		}
		else if(cre.equalsIgnoreCase("Leo")) {
			state = Astro.Leo;
		}
		else if(cre.equalsIgnoreCase("Virgo")) {
			state = Astro.Virgo;
		}
		else if(cre.equalsIgnoreCase("Libra")) {
			state = Astro.Libra;
		}
	}

	public Boolean AstroEffect(Player you, Player opponent) {
		//MANA IS SUBTRACTED IN GAMEBOARDPAGE
		Random rand = new Random();
		int yourmax = 1;
		if(!you.getPlayerBoard().isEmpty())
		{
			yourmax = you.getPlayerBoard().size();
		}
		int yourrandomNum = rand.nextInt(yourmax);
		
		int opmax = 1;
		if(!opponent.getPlayerBoard().isEmpty())
		{
			opmax = opponent.getPlayerBoard().size();
		}
		int oprandomNum = rand.nextInt(opmax);
		if (turnCounter != 0) {
			turnCounter--;
			// need player hp to increase by 2;
			you.set_hp(you.get_hp() + 2);
		}
		switch (state) {
		case Scorpio: //DOES NOT NEED RANDOM NUMBER
			// need player turn
			turnCounter = 3;
			break;
		case Sagittarius: //NEEDS RANDOM NUMBER for opponent
			if (opponent.getPlayerBoard().isEmpty() == false) {				
				opponent.getPlayerBoard().get(oprandomNum).setLife(0);
			}else {
				return false;
			}
			break;
		case Capricorn: //NEEDS RANDOM NUMBER for opponent
			if (opponent.getmHand().isEmpty() == false) {
				opponent.getmHand().remove(oprandomNum);
			}else {
				return false;
			}
			break;
		case Aquarius: //NO RANDOM NUMBER
			int boardSize = opponent.getmHand().size();
			for (int i = 0; i < boardSize; i++) {
				opponent.getmHand().get(i).setLife(opponent.getmHand().get(i).getLife() - 1);
			}
			break;
		case Pisces: //NEEDS YOUR RANDOM NUMBER, SET
			if (you.getPlayerBoard().isEmpty() == false) {
				you.getPlayerBoard().get(yourrandomNum).setLife(you.getPlayerBoard().get(yourrandomNum).getLife() * 2);
			}else {
				return false;
			}
			break;
		case Aries:
			if (opponent.getPlayerBoard().isEmpty() == false){ //RANDOM NUMBER FOR OPPONENT
				opponent.getPlayerBoard().get(oprandomNum).setLife(opponent.getPlayerBoard().get(oprandomNum).getLife() - 5);
				you.set_hp(you.get_hp() - 5);
			}else {
				return false;
			}
			break;
		case Taurus:
			if (opponent.getPlayerBoard().isEmpty() == false) {
				opponent.getPlayerBoard().get(oprandomNum).setLife(opponent.getPlayerBoard().get(oprandomNum).getLife() - 3);
			}else {
				return false;
			}
			break;
		case Gemini:
			if (you.getPlayerBoard().isEmpty() == false) {
				CreatureCard newmc = new CreatureCard((CreatureCard)you.getPlayerBoard().get(yourrandomNum));
				you.getPlayerBoard().add(newmc);
			}else {
				return false;
			}
			break;
		case Cancer:
			if (opponent.getmHand().isEmpty() == false) {
				you.getmHand().add(opponent.getmHand().get(oprandomNum));
				opponent.getmHand().remove(oprandomNum);
			}else {
				return false;
			}
			break;
		case Leo:
			if (opponent.getPlayerBoard().isEmpty() == false) {					
				opponent.getmHand().add(opponent.getPlayerBoard().get(oprandomNum));
				opponent.getPlayerBoard().remove(oprandomNum);
			}else {
				return false;
			}
			break;
		case Virgo:
			if (you.getmHand().isEmpty() == false) {				
				you.getPlayerBoard().add(you.getmHand().get(yourrandomNum));
				you.getmHand().remove(yourrandomNum);
			}else {
				return false;
			}
			break;
		case Libra:
			if (opponent.getPlayerBoard().isEmpty() == false) {
				// randomly select target
				Random rand1 = new Random();
				int creatureHP = opponent.getPlayerBoard().get(oprandomNum).getLife();
				int max = Math.max(creatureHP, 4);
				int randomAttack = rand1.nextInt(max)+1;
				opponent.getPlayerBoard().get(oprandomNum).setLife(creatureHP - randomAttack);
			}else {
				return false;
			}
			break;
		default:
			break;
		}
		this.ADEAD = isDead(); //THIS CARD IS NOW DEAD
		return true;
	}
	
	public MagicCard(CardType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public Texture getTexture() {
		return _texture;
	}
	public String getMyType() {
		return mytype;
	}
	public FireplacePebble getGame() {
		return game;
	}

	public int get_hpRep() {
		return _hpRep;
	}

	public int get_manaCost() {
		return _manaCost;
	}

	public int get_damage() {
		return _damage;
	}

	public String get_astrological() {
		return _astrological;
	}

	public Astro getState() {
		return state;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public Texture get_texture() {
		return _texture;
	}

	public Boolean getADEAD() {
		return ADEAD;
	}

	public void setGame(FireplacePebble game) {
		this.game = game;
	}
	
	public Texture getClickedTexture() {
		return _clickedTexture;
	}

	@Override
	public Boolean isDead() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getCardname() {
		return _astrological;
	}
}
