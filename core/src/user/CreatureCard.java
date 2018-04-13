package user;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class CreatureCard extends Card {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7502974519273870078L;
	private int _hp;
	private int _damage;
	private int _manaCost;
	private Sprite _mSprite;
	private String creature;
	
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
	
	
	public CreatureCard(int hp, int damage, int manaCost, String cre, Sprite sprite) {
		super(type);
		_hp = hp;
		_damage = damage;
		_manaCost = manaCost;
		creature = cre;
		_mSprite = sprite;
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
//	@Override
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
