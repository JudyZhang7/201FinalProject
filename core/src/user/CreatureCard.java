package user;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class CreatureCard extends Card {
	/**
	 * 
	 */
	private static enum _Creature {
		rat, 
		ox, 
		tiger, 
		rabbit, 
		dragon, 
		snake,
		horse, 
		goat, 
		monkey, 
		rooster, 
		dog, 
		pig;
	}
	
	private static final long serialVersionUID = 7502974519273870078L;
	private int _hp;
	private int _maxhp;
	private int _damage;
	private int _manaCost;
	private Sprite _mSprite;
	private _Creature state;
	private Boolean burn;
	private Boolean targetedFirst;
	private Boolean firstTurn;

	public CreatureCard(int hp, int damage, int manaCost, String cre, Sprite sprite) {
		super(type);
		_hp = hp;
		_maxhp = hp;
		_damage = damage;
		_manaCost = manaCost;
		//creature = cre;
		_mSprite = sprite;
		burn = false;
		targetedFirst = true;
		firstTurn = true;
		
		if(cre.equalsIgnoreCase("rat")) {
			state = _Creature.rat;
		}
		else if(cre.equalsIgnoreCase("ox")) {
			state = _Creature.ox;
		}
		else if(cre.equalsIgnoreCase("tiger")) {
			state = _Creature.tiger;
		}
		else if(cre.equalsIgnoreCase("rabbit")) {
			state = _Creature.rabbit;
		}
		else if(cre.equalsIgnoreCase("dragon")) {
			state = _Creature.dragon;
		}
		else if(cre.equalsIgnoreCase("snake")) {
			state = _Creature.snake;
		}
		else if(cre.equalsIgnoreCase("horse")) {
			state = _Creature.horse;
		}
		else if(cre.equalsIgnoreCase("goat")) {
			state = _Creature.goat;
		}
		else if(cre.equalsIgnoreCase("monkey")) {
			state = _Creature.monkey;
		}
		else if(cre.equalsIgnoreCase("rooster")) {
			state = _Creature.rooster;
		}
		else if(cre.equalsIgnoreCase("dog")) {
			state = _Creature.dog;
		}
		else if(cre.equalsIgnoreCase("pig")) {
			state = _Creature.pig;
		}
	}
	
	public _Creature getState() {
		return state;
	}
	public Boolean getBurn() {
		return burn;
	}
	public void burn() {
		burn = true;
	}
	public Boolean getTargetedFirst() {
		return targetedFirst;
	}
	public Boolean getFirstTurn() {
		return firstTurn;
	}
	public int getHP() {
		return _hp;
	}
	
	
	@Override
	public void Attack(CreatureCard target, Player opponent) {
		int attackValue = target.getHP() - _damage;
		int effectValue = 0;
		
		switch(state) {
		case rat:
			effectValue = _damage;
		case ox:
			_damage += 3;
			effectValue = _damage;
		case dragon:
			target.burn();
		case horse:
			if(firstTurn) {
				effectValue = _damage;
			}
		case rooster:
			_damage *= 2;
			effectValue = _damage;
		}
		
		if(target.getBurn()) {
			effectValue += 1;
		}
		
		if(attackValue < 0) {
			
		}
		else if(attackValue == 0) {
			
		}
		else {
			
		}
		
		
	}
	
}
//package user;
//
//public class CreatureCard extends Card{
//	public CreatureCard(CardType type) {
//		super(type);
//		// TODO Auto-generated constructor stub
//	}
////	private int hp;
////	private int damage;
////	private int heal;
////	Boolean isAlive = true;
////	Boolean targeted = false;
//	@Override
//	public void Effect() {
//		// TODO Auto-generated method stub
////		super.effect();
//		switch (this.getCardname()) {
//		case "RAT":
////			"Double Target: Can attack\n" + 
////			"twice in the same turn, but\n" + 
////			"must attack creature once\n" + 
////			"and then the opponent once\n" + 
//			
//			break;
//		case "OX":
//			break;
//		case "TIGER":
//			break;
//		case "RABBIT":
//			break;
//		case "DRAGON":
//			break;
//		case "SNAKE":
//			break;
//		case "HORSE":
//			break;
//		case "GOAT":
//			break;
//		case "MONKEY":
//			break;
//		case "ROOSTER":
//			break;
//		case "DOG":
//			break;
//		case "PIG":
//			break;
//		default:
//			break;
//		}
////		switch (this.getCardname()) {
////		case "RAT":
//////			"Double Target: Can attack\n" + 
//////			"twice in the same turn, but\n" + 
//////			"must attack creature once\n" + 
//////			"and then the opponent once\n" + 
////			
////			break;
////		case "OX":
////			break;
////		case "TIGER":
////			break;
////		case "RABBIT":
////			break;
////		case "DRAGON":
////			break;
////		case "SNAKE":
////			break;
////		case "HORSE":
////			break;
////		case "GOAT":
////			break;
////		case "MONKEY":
////			break;
////		case "ROOSTER":
////			break;
////		case "DOG":
////			break;
////		case "PIG":
////			break;
////		default:
////			break;
////		}
//	}
//	
//@Override
//	public void Attack() {
//		switch (this.getCardname()) {
//		case "RAT":
//			
//			break;
//		case "OX":
//			break;
//		case "TIGER":
//			break;
//		case "RABBIT":
//			break;
//		case "DRAGON":
//			break;
//		case "SNAKE":
//			break;
//		case "HORSE":
//			break;
//		case "GOAT":
//			break;
//		case "MONKEY":
//			break;
//		case "ROOSTER":
//			break;
//		case "DOG":
//			break;
//		case "PIG":
//			break;
//		default:
//			break;
//		}
//	}
//}
//}
