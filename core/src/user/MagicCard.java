package user;

import java.util.Random;
import gamelogic.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.FireplacePebble;
import com.mygdx.game.GameBoardPage;

public class MagicCard extends Card{
	private int _hpRep;
	private int _manaCost;
	private int _damage;
	private String _astrological;
	private Sprite _mSprite;
	private Astro state;
	private FireplacePebble game;
	private int turnCounter;
	
	private static enum Astro {
		Scorpio,
		Sagittarius,
		Capricorn,
		Aquarius,
		Pisces,
		Aries,
		Taurus,
		Gemini,
		Cancer,
		Leo,
		Virgo,
		Libra;
	}
	
	public MagicCard(int hp, int damage, int manaCost, String cre, Sprite sprite, FireplacePebble game) {
		super(type);
		this.game = game;
		_hpRep = hp;
		_damage = damage;
		_manaCost = manaCost;
		_astrological = cre;
		_mSprite = sprite;
		
		if(cre.equalsIgnoreCase("Scorpio")) {
			state = Astro.Scorpio;
			turnCounter = 3;
		}
		else if(cre.equalsIgnoreCase("Sagitarius")) {
			state = Astro.Sagittarius;
		}
		else if(cre.equalsIgnoreCase("Capricorn")) {
			state = Astro.Capricorn;
		}
		else if(cre.equalsIgnoreCase("Aquarius")) {
			state = Astro.Aquarius;
		}
		else if(cre.equalsIgnoreCase("Pisces")) {
			state = Astro.Pisces;
		}
		else if(cre.equalsIgnoreCase("Aries")) {
			state = Astro.Aries;
		}
		else if(cre.equalsIgnoreCase("Taurus")) {
			state = Astro.Taurus;
		}
		else if(cre.equalsIgnoreCase("Gemini")) {
			state = Astro.Gemini;
		}
		else if(cre.equalsIgnoreCase("Cancer")) {
			state = Astro.Cancer;
		}
		else if(cre.equalsIgnoreCase("Leo")) {
			state = Astro.Leo;
		}
		else if(cre.equalsIgnoreCase("Virgo")) {
			state = Astro.Virgo;
		}
		else if(cre.equalsIgnoreCase("Libra")) {
			state = Astro.Libra;
		}
	}

	public void AstroAttack() {
		
		switch (state) {
		case Scorpio:
			break;
		case Sagittarius:
			//
			break;
		case Capricorn:
			
			break;
		case Aquarius:
			
			break;
		case Pisces:
			
			break;
		case Aries:
			
			break;
		case Taurus:

			break;
		case Gemini:
			
			break;
		case Cancer:
			
			break;
		case Leo:
			
			break;
		case Virgo:
			
			break;
		case Libra:
			
			break;
		default:
			break;
		}
	}
	
	public void AstroEffect() {
		
		switch (state) {
		case Scorpio:
			// need player turn
			if (game.getUser().get_player().get_turn() && turnCounter != 0) {
				turnCounter--;
				// need player hp to increase by 2;
				game.getUser().get_player().set_hp(game.getUser().get_player().get_hp() + 2);
			}
			break;
		case Sagittarius:
			if (game.getUser().get_player().getOpponent().getOpponentBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				
				game.getUser().get_player().getOpponent().getOpponentBoard().get(randomNum).setLife(0);
			}
			break;
		case Capricorn:
			if (game.getUser().get_player().getOpponent().getOpponentHand().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getOpponent().getmHand().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				game.getUser().get_player().getOpponent().getOpponentHand().remove(randomNum);
			}
			break;
		case Aquarius:
			int boardSize = game.getUser().get_player().getOpponent().getOpponentBoard().size();
			for (int i = 0; i < boardSize; i++) {
				game.getUser().get_player().getOpponent().getOpponentBoard().get(i).setLife(game.getUser().get_player().getOpponent().getOpponentBoard().get(i).getLife() - 1);
			}
			break;
		case Pisces:
			if (game.getUser().get_player().getPlayerBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				game.getUser().get_player().getPlayerBoard().get(randomNum).setLife(game.getUser().get_player().getPlayerBoard().get(randomNum).getLife() * 2);
			}
			break;
		case Aries:
			_damage += 5;
			game.getUser().get_player().set_hp(game.getUser().get_player().get_hp() - 5);
			// damage player by 5
			break;
		case Taurus:
			_damage += 3;
			break;
		case Gemini:
			if (game.getUser().get_player().getPlayerBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				game.getUser().get_player().getPlayerBoard().add(game.getUser().get_player().getPlayerBoard().get(randomNum));
			}
			break;
		case Cancer:
			if (game.getUser().get_player().getOpponent().getOpponentHand().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				game.getUser().get_player().getmHand().add(game.getUser().get_player().getOpponent().getOpponentHand().get(randomNum));
				game.getUser().get_player().getOpponent().getOpponentHand().remove(randomNum);
			}
			break;
		case Leo:
			if (game.getUser().get_player().getOpponentBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				
				game.getUser().get_player().getOpponent().getOpponentHand().add(game.getUser().get_player().getOpponentBoard().get(randomNum));
				game.getUser().get_player().getOpponent().getOpponentBoard().remove(randomNum);
			}
			break;
		case Virgo:
			
			break;
		case Libra:
			Random rand = null;
			int min = 1;
			int max = 4;
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    // randomly select target
			break;
		default:
			break;
		}
	}
	
	public MagicCard(CardType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
//	private int damage;
//	private int heal;

	public FireplacePebble getGame() {
		return game;
	}

	public void setGame(FireplacePebble game) {
		this.game = game;
	}
}
