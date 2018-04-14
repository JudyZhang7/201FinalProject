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

	public void ActionEffect(Player player)
	{
		
	}
}
