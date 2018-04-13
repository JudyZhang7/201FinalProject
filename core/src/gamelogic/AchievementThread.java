package gamelogic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mygdx.game.FireplacePebble;
import com.mygdx.game.Toast;

public class AchievementThread extends Thread
{
	/* During the game, Achievement Thread will run and output notifications during the game
	 * when user successfully finishes quests. A new achievement thread will be created when
	 * the game is being played, and finishes when the game is over. */
	
	// Private Variables
	private FireplacePebble game; // Game is where the game is being played
	// String, Boolean Map. String is the achievement, Boolean is to determine whether
	//  Achievement has been unlocked or not
	private Map<String, Boolean> myMap = new HashMap<String, Boolean>();
	
	// NEW TOAST
    private Toast.ToastFactory toastFactory;
    private final List<Toast> toasts = new LinkedList<Toast>();
    //private final List<Toast> toasts = new ArrayList<Toast>();
    //private Toast toast;
    // NEW TOAST
	
	// Constructor
	public AchievementThread(FireplacePebble game)
	{
		this.game = game;
	}
	
	// Run Method
	public void run()
	{
		// TOAST create factory
		//Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		toastFactory = new Toast.ToastFactory.Builder().font(game.regfont20).positionY(735).build();
		toastLong("Achievement Unlocked! Levelled up!");
		toastShort("Hello World!");
		// TOAST create factory
		
		while (true)
		{
			for (Map.Entry<String, Boolean> entry : myMap.entrySet())
			{
				if (entry.getValue() == true) // Achievement Unlocked
				{
					
				}
			}
		}
	}
}
