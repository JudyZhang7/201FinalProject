package user;

public enum CardType {

	CREATURE ("creature"),
	MAGIC ("magic"), 
	ACTION ("action");
	
	String title;
	
	private CardType(String name) {
		this.title = name;
	}
	
	public String getTitle() {
		return this.title;
	}

	public static CardType fromString(String text) {
		if (text != null) {
			for (CardType c : CardType.values()) {
				if (c.toString().equalsIgnoreCase(text)) {
					return c;
				}
			}
		}
		return null;
	}
	
}

