package user;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class MagicCard extends Card{
	private int _hpRep;
	private int _manaCost;
	private int _damage;
	private String _astrological;
	private Sprite _mSprite;
	
	private static enum Astro {
		Scorpio,
		Sagitarius,
		Capricorn,
		Aquarius,
		Pisces,
		Aries,
		Taurus,
		Gemini,
		Cancer,
		Leo,
		Virgo,
		Libra
	}
	
	public MagicCard(int hp, int damage, int manaCost, String cre, Sprite sprite) {
		super(type);
		_hpRep = hp;
		_damage = damage;
		_manaCost = manaCost;
		_astrological = cre;
		_mSprite = sprite;
	}

	
	
	public MagicCard(CardType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
//	private int damage;
//	private int heal;
}
