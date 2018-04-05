package user;

public abstract class Card {
	private String cardName;
	private int manaCost;
	
	public void effect() {
		
	}

}


//import java.io.Serializable;
//
//import com.mygdx.game.desktop.Card;
//import com.mygdx.game.desktop.CardType;
//
//public class Card implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//	
//	private String name;
//	
//	private int originalAttack = 0;
//	private int attack;
//	
//	private int hp = 0;
//	private int originalLife = 0;
//	
//	private int cost;
//	private int selfInflictingDamage = 0;
//	
//	private String cardname;
//	private String desc;
//	private CardType type;
//	
//	public static enum TargetType {OWNER, 
//								  OPPONENT, 
//								  ANY};
//	
//
//	public Card(CardType type) {
//		this.type = type;
//	}
//	
//	public Card create() {
//		Card c = new Card(this.type);
//		c.setName(this.name);
//		c.setAttack(this.attack);
//		c.setLife(this.hp);
//		c.setOriginalLife(this.originalLife);
//		c.setOriginalAttack(this.originalAttack);
//		c.setSelfInflictingDamage(this.selfInflictingDamage);
//		c.setCardname(this.cardname);
//		c.setCost(this.cost);
//		c.setDesc(this.desc);
//		return c;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public int getAttack() {
//		return attack;
//	}
//
//	public int getLife() {
//		return hp;
//	}
//
//	public String getCardname() {
//		return cardname;
//	}
//
//	public CardType getType() {
//		return type;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public void setAttack(int attack) {
//		this.attack = attack;
//	}
//
//
//	public void setLife(int life) {		
//		this.hp = life;
//	}
//	
//	public void incrementLife(int inc) {
//		this.hp += inc;
//
//	}
//	public void decrementLife(int dec) {
//		this.hp -= dec;
//	}
//	
//	public int getCost() {
//		return cost;
//	}
//	public void setCost(int cost) {
//		this.cost = cost;
//	}
//
//	public void setCardname(String cardname) {
//		this.cardname = cardname;
//	}
//
//	public void setType(CardType type) {
//		this.type = type;
//	}
//
//	public String getDesc() {
//		return desc;
//	}
//
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
//
//
//	public int getOriginalLife() {
//		return originalLife;
//	}
//
//	public void setOriginalLife(int originalLife) {
//		this.originalLife = originalLife;
//	}
//
//	public int getOriginalAttack() {
//		return originalAttack;
//	}
//
//	public void setOriginalAttack(int originalAttack) {
//		this.originalAttack = originalAttack;
//	}
//
//	public int getSelfInflictingDamage() {
//		return selfInflictingDamage;
//	}
//
//	public void setSelfInflictingDamage(int selfInflictingDamage) {
//		this.selfInflictingDamage = selfInflictingDamage;
//	}
//
//
//
//	
//}