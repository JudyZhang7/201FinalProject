package user;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import gamelogic.ThisGame;

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
	private Texture _texture;
	private Texture _clickedTexture;
	private _Creature state;
	private Boolean burn;
	private Boolean targetedFirst;
	private Boolean firstTurn;
	private String name;
	private int winner;
	
	private Label hpLabel;
	
	// Copy Constructor
	public CreatureCard(CreatureCard cc)
	{
		super(type);
		_hp = cc._hp;
		_maxhp = cc._maxhp;
		_damage = cc._damage;
		_manaCost = cc._manaCost;
		_texture = cc._texture;
		_clickedTexture = cc._clickedTexture;
		state = cc.state;
		burn = false;
		targetedFirst = true;
		firstTurn = true;
		
		Texture currCard = cc.getTexture();
		TextureRegion TEMP_C = new TextureRegion(currCard);
		TextureRegionDrawable TEMP_CARD = new TextureRegionDrawable(TEMP_C);
		ImageButton newHB = new ImageButton(TEMP_CARD);
		
		this.ib = newHB;
		ib.setSize(150, 125);
		Label statLabel = null;
		if(cc.getLabel()!= null) {
			statLabel = new Label("" + _hp, cc.getLabel().getStyle());
		}
		addLabel(statLabel);
		
		name = cc.name;
		mytype = "creature";
	}
	public void addLabel(Label label) {
		hpLabel = label;
	}
	public Label getLabel() {
		return hpLabel;
	}
	public void changeLabel(String newWords) {
		hpLabel.setText(newWords);
	}

	public CreatureCard(int hp, int damage, int manaCost, String cre, Texture img, Texture clicked) {
		super(type);
		_hp = hp;
		_maxhp = hp;
		_damage = damage;
		_manaCost = manaCost;
		_texture = img;
		_clickedTexture = clicked;
		burn = false;
		targetedFirst = true;
		firstTurn = true;
		setib(null);
		addLabel(null);
		name = cre;

		mytype = "creature";
		
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
	public Texture getTexture() {
		return _texture;
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
	public void setTargetedFirst(Boolean targetedFirst) {
		this.targetedFirst = targetedFirst;
	}
	public Boolean getFirstTurn() {
		return firstTurn;
	}
	public int getHP() {
		return _hp;
	}
	public void setHP(int newHP) {
		_hp = newHP;
	}
	@Override
	public Boolean isDead() {
		if(_hp <= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public Boolean Attack(CreatureCard target, Player opponent, Player player) {
		int attackValue = target.getHP() - _damage;
		int effectValue = 0;
		System.out.println("Here: " + opponent.get_hp());
		switch(state) {
		case rat:
			effectValue = 2 * _damage;
			break;
		case ox:
			_damage += 3;
			effectValue = _damage;
			break;
		case tiger:
			break;
		case rabbit:
			break;
		case dragon:
			target.burn();
			break;
		case snake: 
			break;
		case horse:
			if(firstTurn) {
				effectValue = _damage;
			}
			break;
		case goat: 
			break;
		case monkey: 
			break;
		case rooster:
			_damage *= 2;
			effectValue = _damage;
			break;
		case dog: 
			break;
		case pig:
			player.drawCards();
			break;
		}
		System.out.println("mana - " + player.get_mana());
		if(player.get_mana() - _manaCost < 0) {
			return false;
		}
		
		if(target.getBurn() && attackValue > 0) {
			effectValue += 1;
		}
		
		if(target.getState() == _Creature.tiger && target.getTargetedFirst()) {
			attackValue = target.getHP();
			effectValue = 0;
		}
		
		if(this.getState() == _Creature.snake) {
			int newHP = this.getHP() + (target.getHP() - attackValue);
			if(newHP > _maxhp) {
				newHP = _maxhp;
			}
			this.setHP(newHP);
		}
		
		if(attackValue < 0) {
			target.setHP(0);
			opponent.set_hp(opponent.get_hp() + attackValue - effectValue);
		}
		else if(attackValue == 0) {
			target.setHP(attackValue);
			opponent.set_hp(opponent.get_hp() - effectValue);
		}
		else {
			target.setHP(attackValue);
			//try applying damage to animal
			//if it dies, check if we harm the human
			if(target.getState() == _Creature.rabbit) {
				effectValue = 0;
			}
			
			if(target.getState() == _Creature.goat) {
				int retaliation = this.getHP() - effectValue;
				if(retaliation < 0) {
					this.setHP(0);
					player.set_hp(player.get_hp() + retaliation);
				}
				else {
					this.setHP(retaliation);
				}
			}
			else {
				int effectiveDamage = target.getHP() - effectValue;
				if(effectiveDamage < 0) {
					target.setHP(0);
					opponent.set_hp(opponent.get_hp() + effectiveDamage);
				}
				else {
					target.setHP(effectiveDamage);
				}
			}
			
			if(target.getState() == _Creature.dog && target.getTargetedFirst() && target.getHP() != 0) {
				int retaliation = this.getHP() - 2;
				if(retaliation < 0) {
					this.setHP(0);
					player.set_hp(player.get_hp() + retaliation);
				}
				else {
					this.setHP(retaliation);
				}
			}
		}
		
		if(this.getState() == _Creature.monkey) {
			if (player.get_hp() > 0) {
				player.set_hp(player.get_hp() + 1);
			}
		}
		
		firstTurn = false;
		target.setTargetedFirst(false);
		return true;
	}
	
	public void AttackPlayer(Player p1) {
		if(p1.get_hp() > 0) {
			p1.set_hp(p1.get_hp() - _damage);
			if (p1.isDead()) {
				winner = 1;
			}
		}
	}
	
	@Override
	public String getCardname() {
		// TODO Auto-generated method stub
		return name;
	}
	public String getMyType() {
		return mytype;
	}
	public int get_hp() {
		return _hp;
	}
	public int get_maxhp() {
		return _maxhp;
	}
	public int get_damage() {
		return _damage;
	}
	public int get_manaCost() {
		return _manaCost;
	}
	public void set_manaCost(int i) {
		_manaCost = i;
	}
	public Texture get_texture() {
		return _texture;
	}
	public Texture getClickedTexture() {
		return _clickedTexture;
	}
	public String getName() {
		return name;
	}
}
