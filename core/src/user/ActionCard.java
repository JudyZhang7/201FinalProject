package user;

public class ActionCard extends Card {

	private int manaCost;
	private int damage;
	private int hpReplenish;
	private int mana;
	
	private static enum Action {
		weapon,
		shield,
		heal,
		doubleDamage,
		preparation
	}
	
	public ActionCard(CardType type) {
		
	}

}
