package user;

import java.io.Serializable;


import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

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
	protected String mytype;
	private boolean lastedATurn = false;
	protected ImageButton ib;
	
	public ImageButton getib() {
		return ib;
	}
	public void setib(ImageButton ib) {
		this.ib = ib;
	}
	
	public String getMytype() {
		return mytype;
	}
	public static enum TargetType {OWNER, 
								  OPPONENT, 
								  ANY};

	public Card(CardType type) {
		this.type = type;
	}

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
	
	public abstract int get_manaCost();
	
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
	
	public abstract Boolean isDead();

}