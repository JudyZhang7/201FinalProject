package user;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

import user.*;

public class MagicCard extends Card{
	private int _hpRep;
	private int _manaCost;
	private int _damage;
	private String _astrological;
	private Sprite _mSprite;
	private Astro state;
	
	private int turnCounter;
	
	private static enum Astro {
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
	
	public MagicCard(int hp, int damage, int manaCost, String cre, Sprite sprite) {
		super(type);
		_hpRep = hp;
		_damage = damage;
		_manaCost = manaCost;
		_astrological = cre;
		_mSprite = sprite;
		
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

	public void AstroEffect() {
		
		switch (state) {
		case Scorpio:
			if () {
				
			}
			break;
		case Sagittarius:
			break;
		case Capricorn:
			break;
		case Aquarius:
			break;
		case Pisces:
			break;
		case Aries:
			break;
		case Taurus:
			break;
		case Gemini:
			break;
		case Cancer:
			break;
		case Leo:
			break;
		case Virgo:
			break;
		case Libra:
			break;
		default:
			break;
		}
	}
	
	public MagicCard(CardType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
//	private int damage;
//	private int heal;
}
