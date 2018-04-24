package user;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import user.*;
import gamelogic.*;

import com.mygdx.game.Assets;
import com.mygdx.game.GameBoardPage;

public class ActionCard extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = 938784132360408501L;
	private int _manaCost;
	private int _damage;
	private int _hpReplenish;
	private int _mana;
	private String _actionName;

	private Texture _texture;
	private Texture _clickedTexture;
	private Boolean ACTIONDEAD;
	
	private int _damageOpponentDoes = 0;
	private Action state;
	private static enum Action {
		Weapon,
        Heal,
        DoubleDamage,
        Preparation;
    	}
	
	// Copy Constructor
	public ActionCard(ActionCard ac)
	{
		super(type);
		_manaCost = ac._manaCost;
		_damage = ac._damage;
		_hpReplenish = ac._hpReplenish;
		_mana = ac._mana;
		_actionName = ac._actionName;
		mytype = "action";
		_texture = ac._texture;
		state = ac.state;
		System.out.println("COPY CONSTRUCTOR FOR " + _actionName);

		TextureRegion TEMP_C = new TextureRegion(_texture);
		TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
		ImageButton newHB = new ImageButton(TEMP_CARD);
		ImageButton newHBact = new ImageButton(TEMP_CARD);
		
		this.ib = newHB;
		ib.setSize(150, 125);
		
	}
	
	public ActionCard(int manaCost, int damage, int hpReplenish, int mana, String actionName, Texture img, Texture clicked) {
		super(type);
		_manaCost = manaCost;
		_damage = damage;
		_hpReplenish = hpReplenish;
		_mana = mana;
		_actionName = actionName;
		mytype = "action";
		_texture = img;
		_clickedTexture = clicked;
		setib(null);
		if(actionName.equalsIgnoreCase("Weapon")) {
			state = Action.Weapon;
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

	public Boolean ActionEffect(Player you, Player opponent)
	{
		switch(state) {
		case Weapon:
			opponent.set_hp(opponent.get_hp() - _damage);
			this.ACTIONDEAD = isDead();
			break;
		case Heal:
			you.set_hp(you.get_hp() + _hpReplenish);
			this.ACTIONDEAD = isDead();
			break;
		case DoubleDamage:
			opponent.set_hp(opponent.get_hp() - _damage - _damage);
			this.ACTIONDEAD = isDead();
			break;
		case Preparation:
			you.set_hp(you.get_hp() + _hpReplenish + _hpReplenish);
			this.ACTIONDEAD = isDead();
			break;
		}
		return true;
	}
	
	public void set_damageOpponentDoes(int damage ) {
		_damageOpponentDoes = damage;
	}
	@Override
	public String getCardname() {
		// TODO Auto-generated method stub
		return _actionName;
	}
	public String getMyType() {
		return mytype;
	}
	public Texture getTexture() {
		return _texture;
	}
	
	public int get_manaCost(){
		return _manaCost;
	}

	public int get_damage() {
		return _damage;
	}

	public int get_hpReplenish() {
		return _hpReplenish;
	}

	public int get_mana() {
		return _mana;
	}

	public String get_actionName() {
		return _actionName;
	}

	public Texture get_texture() {
		return _texture;
	}
	public Texture getClickedTexture() {
		return _clickedTexture;
	}
	@Override
	public Boolean isDead() {
		// TODO Auto-generated method stub
		return null;
	}

}
