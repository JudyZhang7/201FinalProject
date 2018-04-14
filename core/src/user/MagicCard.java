package user;

import java.util.Random;
import gamelogic.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.FireplacePebble;
import com.mygdx.game.GameBoardPage;

public class MagicCard extends Card{
	private int _hpRep;
	private int _manaCost;
	private int _damage;
	private String _astrological;
	private Astro state;
	private FireplacePebble game;
	private int turnCounter;
	private Texture _texture;
	private Boolean ADEAD;
	
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
	
	public MagicCard(int hp, int damage, int manaCost, String cre, Texture img, FireplacePebble game) {
		super(type);
		this.game = game;
		_hpRep = hp;
		_damage = damage;
		_manaCost = manaCost;
		_astrological = cre;
		_texture = img;
		
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

	@Override
	public void Attack() {
		
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
			this.ADEAD = isDead();
			break;
		case Sagittarius:
			if (game.getUser().get_player().getOpponent().getOpponentBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				
				game.getUser().get_player().getOpponent().getOpponentBoard().get(randomNum).setLife(0);
			}
			this.ADEAD = isDead();
			break;
		case Capricorn:
			if (game.getUser().get_player().getOpponent().getOpponentHand().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getOpponent().getmHand().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				game.getUser().get_player().getOpponent().getOpponentHand().remove(randomNum);
			}
			this.ADEAD = isDead();
			break;
		case Aquarius:
			int boardSize = game.getUser().get_player().getOpponent().getOpponentBoard().size();
			for (int i = 0; i < boardSize; i++) {
				game.getUser().get_player().getOpponent().getOpponentBoard().get(i).setLife(game.getUser().get_player().getOpponent().getOpponentBoard().get(i).getLife() - 1);
			}
			this.ADEAD = isDead();
			break;
		case Pisces:
			if (game.getUser().get_player().getPlayerBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				game.getUser().get_player().getPlayerBoard().get(randomNum).setLife(game.getUser().get_player().getPlayerBoard().get(randomNum).getLife() * 2);
			}
			this.ADEAD = isDead();
			break;
		case Aries:
			_damage += 5;
			game.getUser().get_player().set_hp(game.getUser().get_player().get_hp() - 5);
			// damage player by 5
			this.ADEAD = isDead();
			break;
		case Taurus:
			_damage += 3;
			this.ADEAD = isDead();
			break;
		case Gemini:
			if (game.getUser().get_player().getPlayerBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				game.getUser().get_player().getPlayerBoard().add(game.getUser().get_player().getPlayerBoard().get(randomNum));
			}
			this.ADEAD = isDead();
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
			this.ADEAD = isDead();
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
			this.ADEAD = isDead();
			break;
		case Virgo:
			if (game.getUser().get_player().getmHand().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = game.getUser().get_player().getPlayerBoard().size() - 1;
				int randomNum = rand.nextInt((max - min) + 1) + min;
				
				game.getUser().get_player().getPlayerBoard().add(game.getUser().get_player().getmHand().get(randomNum));
				game.getUser().get_player().getmHand().remove(randomNum);
			}
			this.ADEAD = isDead();
			break;
		case Libra:
			if (game.getUser().get_player().getOpponent().getOpponentBoard().isEmpty() == false) {
				Random rand = null;
				int min = 1;
				int max = 4;
				int randomNum = rand.nextInt((max - min) + 1) + min;
		    // randomly select target
				
				Random rand1 = null;
				int min1 = 1;
				int creatureHP = game.getUser().get_player().getOpponent().getOpponentBoard().get(randomNum).getLife();
				int randomNum1 = rand1.nextInt((creatureHP - min1) + 1) + min1;

				game.getUser().get_player().getOpponent().getOpponentBoard().get(randomNum).setLife(creatureHP - randomNum1);
			}
			this.ADEAD = isDead();
			break;
		default:
			break;
		}
	}
	
	public MagicCard(CardType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public Texture getTexture() {
		return _texture;
	}
	
	public FireplacePebble getGame() {
		return game;
	}

	public void setGame(FireplacePebble game) {
		this.game = game;
	}

	@Override
	public Boolean isDead() {
		// TODO Auto-generated method stub
		return true;
	}
}
