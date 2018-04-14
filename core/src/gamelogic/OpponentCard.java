package gamelogic;

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
}
