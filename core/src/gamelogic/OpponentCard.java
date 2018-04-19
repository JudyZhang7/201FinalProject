package gamelogic;

import com.badlogic.gdx.graphics.Texture;

import user.*;

public class OpponentCard extends Card {
	public OpponentCard(CardType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	private int hp;
	private int manaCost;
	private int attack;
	private int hpReplenish;
	@Override
	public Boolean isDead() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getCardname() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMyType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Texture getClickedTexture() {
		// TODO Auto-generated method stub
		return null;
	}
}
