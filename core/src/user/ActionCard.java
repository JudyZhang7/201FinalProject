package user;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ActionCard extends Card {

	private int _manaCost;
	private int _damage;
	private int _hpReplenish;
	private int _mana;
	private String _actionName;
	private Sprite _mSprite;
	private Boolean ACTIONDEAD;
	
	private int _damageOpponentDoes = 0;
	private Action state;
	
	private static enum Action {
		Weapon,
		Shield,
		Heal,
		DoubleDamage,
		Preparation;
	}
	
	public ActionCard(int manaCost, int damage, int hpReplenish, int mana, String actionName, Sprite mSprite) {
		super(type);
		_manaCost = manaCost;
		_damage = damage;
		_hpReplenish = hpReplenish;
		_mana = mana;
		_actionName = actionName;
		_mSprite = mSprite;
		
		if(actionName.equalsIgnoreCase("Weapon")) {
			state = Action.Weapon;
		}
		else if(actionName.equalsIgnoreCase("Shield")) {
			state = Action.Shield;
		}
		else if(actionName.equalsIgnoreCase("Heal")) {
			state = Action.Heal;
		}
		else if(actionName.equalsIgnoreCase("DoubleDamage")) {
			state = Action.DoubleDamage;
		}
		else if(actionName.equalsIgnoreCase("Preparation")) {
			state = Action.Preparation;
		}
	}

	public void ActionEffect(Player you, Player opponent, CreatureCard opponentCreature)
	{
		switch(state) {
		case Weapon:
			opponent.set_hp(opponent.get_hp() - _damage);
			this.ACTIONDEAD = isDead();
			break;
		case Shield:
			// check if opponent attacked you
			// store damage inflicted to add back to your hp
			// if yes, add the damage the opponent inflicted back into your hp
			opponent.set_hp(opponent.get_hp() - _damageOpponentDoes);
			this.ACTIONDEAD = isDead();
			break;
		case Heal:
			you.set_hp(you.get_hp() + _hpReplenish);
			this.ACTIONDEAD = isDead();
			break;
		case DoubleDamage:
			opponent.set_hp(opponent.get_hp() - 2 * _damage);
			this.ACTIONDEAD = isDead();
			break;
		case Preparation:
			you.set_mana(you.get_mana() + _mana);
			this.ACTIONDEAD = isDead();
			break;
		}
	}
	
	public void set_damageOpponentDoes(int damage ) {
		_damageOpponentDoes = damage;
	}

	@Override
	public Boolean isDead() {
		// TODO Auto-generated method stub
		return true;
	}
}
