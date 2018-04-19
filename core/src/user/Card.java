package user;

import java.io.Serializable;


import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import com.badlogic.gdx.graphics.Texture;

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
public abstract class Card implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String cardName;
	private int attack;
	private int effectAttack = 0;
	private int effectDefense = 0;
	private int hp;
	private int manaCost;
	private Texture _texture;
	private String cardname;
	private String desc;
	protected static CardType type;
	private Boolean onBoard;
	private String image;
	protected Player mPlayer;
	protected String mytype;
	private boolean lastedATurn = false;

	public String getMytype() {
		return mytype;
	}
	public static enum TargetType {OWNER, 
								  OPPONENT, 
								  ANY};
	

	public Player getmPlayer() {
		return mPlayer;
	}

	public void setmPlayer(Player mPlayer) {
		this.mPlayer = mPlayer;
	}

	public Card(CardType type) {
		this.type = type;
	}

	//public abstract getType();

	public int getAttack() {
		return attack;
	}
	
	public abstract Texture getTexture();
	
	public int getLife() {
		return hp;
	}

	public abstract String getCardname();

	public String getMyType() {
		return mytype;
	}
	
	public abstract Texture getClickedTexture();
	
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

	public Boolean Attack(CreatureCard target, Player opponent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract Boolean isDead();
	
	
		
	}
