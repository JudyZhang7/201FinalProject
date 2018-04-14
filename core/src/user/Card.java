package user;

import java.io.Serializable;


import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

//public abstract class Card {
//	private String cardName;
//	private int manaCost;
//	
//	public void effect() {
//		
//	}
//
//}


//import java.io.Serializable;
//
//import com.mygdx.game.desktop.Card;
//import com.mygdx.game.desktop.CardType;
//
public class Card implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String cardName;
	private int attack;
	private int effectAttack = 0;
	private int effectDefense = 0;
	private int hp;
	private int manaCost;
	private String cardname;
	private String desc;
	protected static CardType type;
	private Boolean onBoard;
	private String image;
	
	private String img; //name of the image
	
	public static enum TargetType {OWNER, 
								  OPPONENT, 
								  ANY};
	

	public Card(CardType type) {
		this.type = type;
	}
	
	public Card create() {
		Card c = new Card(this.type);
		c.setName(this.cardName);
		c.setAttack(this.attack);
		c.setLife(this.hp);
		c.setCardname(this.cardname);
		c.setCost(this.manaCost);
		c.setDesc(this.desc);
		return c;
	}

	public String getName() {
		return cardName;
	}

	public int getAttack() {
		return attack;
	}

	public String getImg() {
		return img;
	}
	public int getLife() {
		return hp;
	}

	public String getCardname() {
		return cardname;
	}

	public CardType getType() {
		return type;
	}

	public void setName(String name) {
		this.cardName = name;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}


	public void setLife(int life) {		
		this.hp = life;
	}
	
	public void incrementLife(int inc) {
		this.hp += inc;

	}
	public void decrementLife(int dec) {
		this.hp -= dec;
	}
	
	public int getCost() {
		return manaCost;
	}
	public void setCost(int cost) {
		this.manaCost = cost;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void Effect() {}
	public void Attack() {}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void Attack(CreatureCard target, Player opponent) {
		// TODO Auto-generated method stub
		
	}

//	public void Attack(Card target, Player opponent) {
//		switch (_Creature) {
//		case rat:
//			break;
//		case ox:
//			effectAttack = 3;
//			break;
//		case tiger:
//			break;
//		case rabbit:
//			effectDefense = 999;
//			break;
//		case dragon:
//			break;
//		case snek:
//			break;
//		case horse:
//			break;
//		case goat:
//			break;
//		case monkey:
//			break;
//		case rooster:
//			break;
//		case dog:
//			break;
//		case pig:
//			//mPlayer.draw();
//			break;
//		
//		default:
//			break;
//		}
//		
//		
//		attackValue = target.getLife() - this.getAttack();
//		effectValue = target.getEffectDefense() - this.getEffectAttack();
//		
//		if
//		
//		Hi. ryan
		
	}
