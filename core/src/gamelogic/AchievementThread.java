package gamelogic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.FireplacePebble;
import com.mygdx.game.Toast;

public class AchievementThread extends Thread
{
	/* During the game, Achievement Thread will run and output notifications during the game
	 * when user successfully finishes quests. A new achievement thread will be created when
	 * the game is being played, and finishes when the game is over. */
	
	// Private Variables
	private FireplacePebble game; // Game is where the game is being played
	
	// Constructor
	public AchievementThread(FireplacePebble game)
	{
		this.game = game;
		this.start();
	}
	
	// Run Method
	public void run()
	{
		System.out.println("In Run Method");
		Gdx.app.postRunnable(new Runnable()
		{
	        public void run()
	        {
	        		// Run this run method when the user has unlocked an achievement
        	 		// if value is 0, achievement still locked.
        	 		// if value is 1, achievement is unlocked
        	 		// if value is 2, achievement is unlocked already and has already been displayed to the user
        	 		int i = 0;
        	 		// While game is still ongoing? While true doesn't work
        	 		while (i < 5)
        	 		{	
        	 			if (i == 3)
        	 			{
        	 				game.achievementMap.put("Played a game!", 1);
        	 			}
        	 			for (Map.Entry<String, Integer> entry : game.achievementMap.entrySet())
        	 			{
        	 				if (entry.getValue() == 1) // Output achievment unlocked
        	 				{
        	 					game.toastLong("Achievement Unlocked! " + entry.getKey());
        	 					// Update the value of map value so that you don't output the
        	 					// achievement twice
        	 					game.achievementMap.put(entry.getKey(), 2);
        	 				}
        	 			}
        	 			i++;
        	 		}
	        }
	    });
	}
}
