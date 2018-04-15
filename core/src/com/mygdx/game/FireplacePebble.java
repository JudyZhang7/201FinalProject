package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import user.ActionCard;
import user.Card;
import user.CreatureCard;
import user.Decks;
import user.MagicCard;
import user.Player;
import user.User;

public class FireplacePebble extends Game{
	private User user;
	private String skin = "clean-crispy/skin/clean-crispy-ui.json";
	FreeTypeFontGenerator titleText;
	FreeTypeFontParameter titleParam;
	public BitmapFont titlefont128;
	public BitmapFont titlefont64;
	public BitmapFont regfont32;
	public BitmapFont regfont20;
	public BitmapFont regfont16;
    // NEW TOAST
	public Map<String, Integer> achievementMap = new HashMap<String, Integer>();
    private Toast.ToastFactory toastFactory;
    private final List<Toast> toasts = new LinkedList<Toast>();
	HashMap< String,CreatureCard> creatureCards = new HashMap< String,CreatureCard>();
	HashMap< String,ActionCard> actionCards = new HashMap< String,ActionCard>();
	HashMap< String,MagicCard> magicCards = new HashMap< String,MagicCard>();
    // NEW TOAST

	@Override
	public void create() {
		Assets.load();
		loadDefaultDeck();
		FreeTypeFontGenerator titleText = new FreeTypeFontGenerator(Gdx.files.internal("JMH.ttf"));
		FreeTypeFontParameter titleParam = new FreeTypeFontParameter();
		
		titleParam.size = 128;
		titlefont128 = titleText.generateFont(titleParam); // font size 128 pixels
		titleParam.size = 64;
		titlefont64 = titleText.generateFont(titleParam); // font size 64
		titleText.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator regText = new FreeTypeFontGenerator(Gdx.files.internal("DroidSans.ttf"));
		FreeTypeFontParameter regParam = new FreeTypeFontParameter();
		
		regParam.size = 32;
		regfont32 = regText.generateFont(regParam); // font size 32 pixels
		regParam.size = 16;
		regfont16 = regText.generateFont(regParam); // font size 16
		regParam.size = 20;
		regfont20 = regText.generateFont(regParam); 
		regText.dispose(); // don't forget to dispose to avoid memory leaks!
		
//		Assets.load();
//		this.setScreen(new StartGameScreen(this));
//		this.setScreen(new DeckScreen(this));
//		this.setScreen(new ProfileScreen(this));
		int w = Gdx.graphics.getWidth();
	    int h = Gdx.graphics.getHeight();
		
		// TOAST create factory
		//Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	    toastFactory = new Toast.ToastFactory.Builder().font(regfont20).positionY(9*h/10).build();
//	    // Add achievements here?
//	    achievementMap.put("Chosen a card!", 1);
//	    achievementMap.put("Played a Game!", 1);
//	    achievementMap.put("Attacked for the First Time!", 1);
		// TOAST create factory

		//THIS IS THE START
		this.setScreen(new FrontPage(this));
		//this.setScreen(new DragAndDropTutorial(this));
//		this.setScreen(new CreateNewDeckScreen(this));
		
	}
	
	public BitmapFont titlefont64() {
		return titlefont64;
	}
	
	public BitmapFont titlefont128() {
		return titlefont128;
	}
	
	public BitmapFont regfont16() {
		return regfont16;
	}
	
	public BitmapFont regfont20() {
		return regfont20;
	}
	
	public BitmapFont regfont32() {
		return regfont32;
	}
	
	public String getSkin() {
		return skin;
	}
	public void printCurrentUser() {
		System.out.println("Current logged in user: " + user.getUsername());
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public User getUser() {
		return user;
	}

	public String getCurrentUsername() {
		return user.getUsername();
	}
	
	public String getCurrentPassword() {
		return user.get_Password();
	}
	public int getCurrentWins() {
		return (user.get_wins());
	}
	
	public int getCurrentLoses() {
		return (user.get_losses());
	}
	
	public int getCurrentLevel() {
		return (user.get_level());
	}
	
	public Player getCurrentPlayer() {
		return user.get_player();
	}
	public Decks getCurrentDecks() {
		return user.get_decks();
	}
	// Displays long toast
	public void toastLong(String text) 
	{
		toasts.add(toastFactory.create(text, Toast.Length.LONG));
	}

	// Displays short toast
	public void toastShort(String text) 
	{
		toasts.add(toastFactory.create(text, Toast.Length.SHORT));
	}
	
	// Get achievement map
//	public Map<String, Integer> getAchievementMap()
//	{
//		return achievementMap;
//	}
	
	public void render() {
		super.render();
		
      // NEW TOAST
      // handle toast queue and display
      Iterator<Toast> it = toasts.iterator();
      while(it.hasNext()) 
      {
          Toast t = it.next();
          if (!t.render(Gdx.graphics.getDeltaTime())) 
          {
              it.remove(); // toast finished -> remove
          } 
          else 
          {
              break; // first toast still active, break the loop
          }
      }
      // NEW TOAST
	}
	
	public void loadDefaultDeck() {
		CreatureCard ratCard = new CreatureCard(3, 1, 2, "rat", Assets.myTexturesList.get(8));
		CreatureCard oxCard = new CreatureCard(5, 3, 5, "ox", Assets.myTexturesList.get(5));
		CreatureCard tigerCard = new CreatureCard(3, 3, 3, "tiger", Assets.myTexturesList.get(11));
		CreatureCard rabbitCard = new CreatureCard(2, 1, 2, "rabbit", Assets.myTexturesList.get(7));
		CreatureCard dragonCard = new CreatureCard(3, 3, 5, "dragon", Assets.myTexturesList.get(1));
		CreatureCard snakeCard = new CreatureCard(2, 2, 3, "snake", Assets.myTexturesList.get(10));
		CreatureCard horseCard = new CreatureCard(2, 2, 3, "horse", Assets.myTexturesList.get(3));
		CreatureCard goatCard = new CreatureCard(2, 2, 2, "goat", Assets.myTexturesList.get(2));
		CreatureCard monkeyCard = new CreatureCard(1, 3, 2, "monkey", Assets.myTexturesList.get(4));
		CreatureCard roosterCard = new CreatureCard(2, 3, 4, "rooster", Assets.myTexturesList.get(9));
		CreatureCard dogCard = new CreatureCard(3, 3, 4, "dog", Assets.myTexturesList.get(0));
		CreatureCard pigCard = new CreatureCard(1, 1, 1, "pig", Assets.myTexturesList.get(6));
		//PUT IN CREATURE CARD MAP
		creatureCards.put("rat", ratCard);
		creatureCards.put("ox", oxCard);
		creatureCards.put("tiger", tigerCard);
		creatureCards.put("rabbit", rabbitCard);
		creatureCards.put("dragon", dragonCard);
		creatureCards.put("snake", snakeCard);
		creatureCards.put("horse", horseCard);
		creatureCards.put("goat", goatCard);
		creatureCards.put("monkey", monkeyCard);
		creatureCards.put("rooster", roosterCard);
		creatureCards.put("dog", dogCard);
		creatureCards.put("pig", pigCard);
		
		///
		MagicCard Scorpio = new MagicCard(2, 2, 0, "scorpio", Assets.myTexturesList.get(21), this);
		MagicCard Sagittarius = new MagicCard(2, 4, 0, "sagittarius", Assets.myTexturesList.get(20), this);
		MagicCard Capricorn = new MagicCard(0, 3, 0, "capricorn", Assets.myTexturesList.get(15), this);
		MagicCard Aquarius = new MagicCard(0, 4, 0, "aquarius", Assets.myTexturesList.get(12), this);
		MagicCard Pisces = new MagicCard(0, 2, 0, "pisces", Assets.myTexturesList.get(19), this);
		MagicCard Aries = new MagicCard(0, 3, 5, "aries", Assets.myTexturesList.get(13), this);
		MagicCard Taurus = new MagicCard(0, 3, 3, "taurus", Assets.myTexturesList.get(22), this);
		MagicCard Gemini = new MagicCard(0, 4, 0, "gemini", Assets.myTexturesList.get(16), this);
		MagicCard Cancer = new MagicCard(0, 2, 0, "cancer", Assets.myTexturesList.get(14), this);
		MagicCard Leo = new MagicCard(0, 2, 0, "leo", Assets.myTexturesList.get(17), this);
		MagicCard Virgo = new MagicCard(2, 1, 0, "virgo", Assets.myTexturesList.get(23), this);
		MagicCard Libra = new MagicCard(0, 2, 1, "libra", Assets.myTexturesList.get(18), this);
		//PUT IN MAGIC CARD MAP
		magicCards.put("scorpio", Scorpio);
		magicCards.put("sagittarius", Sagittarius);
		magicCards.put("capricorn", Capricorn);
		magicCards.put("aquarius", Aquarius);
		magicCards.put("pisces", Pisces);
		magicCards.put("aries", Aries);
		magicCards.put("taurus", Taurus);
		magicCards.put("gemini", Gemini);
		magicCards.put("cancer", Cancer);
		magicCards.put("leo", Leo);
		magicCards.put("virgo", Virgo);
		magicCards.put("libra", Libra);
		
		///mana cost, damage, hp replenish, mana, actionname, sprite
		ActionCard Weapon = new ActionCard(3, 3, 0, 0, "Weapon", Assets.myTexturesList.get(28));
		ActionCard Heal = new ActionCard(2, 0, 3, 0, "Heal", Assets.myTexturesList.get(25));
		ActionCard DoubleDamage = new ActionCard(4, 0, 0, 0, "DoubleDamage", Assets.myTexturesList.get(24));
		ActionCard Preparation = new ActionCard(3, 0, 0, 0, "Preparation", Assets.myTexturesList.get(26));
		//PUT IN ACTION CARD MAP
		actionCards.put("weapon", Weapon);
		actionCards.put("heal", Heal);
		actionCards.put("doubleDamage", DoubleDamage);
		actionCards.put("preparation", Preparation);
	}
	public Card [] getAllCards(){
		Card [] allCards = new Card[28];
		int counter = 0;
		Set<Entry<String, CreatureCard>> cc = creatureCards.entrySet();   
		for (Map.Entry< String,CreatureCard> me:cc)
	    {
			System.out.print(me.getValue().getCardname());
	        allCards[counter] = me.getValue();
	        counter++;
	    }
		Set<Entry<String, ActionCard>> ac = actionCards.entrySet();   
		for (Map.Entry< String,ActionCard> me:ac)
	    {
			allCards[counter] = me.getValue();
			counter++;
	    }
		Set<Entry<String, MagicCard>> mc = magicCards.entrySet();   
		for (Map.Entry< String,MagicCard> me:mc)
	    {
			allCards[counter] = me.getValue();
			counter++;
	    }
		return allCards;
	}
}
