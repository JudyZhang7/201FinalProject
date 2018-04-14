package com.mygdx.game;

import java.util.ArrayList;

import javax.sound.midi.VoiceStatus;
import javax.swing.plaf.basic.BasicToolBarUI.DockingListener;
import javax.xml.soap.Text;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters.LoadedCallback;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {
	
	public static Texture texture_back;
	public static Sprite sprite_back;
	public static ArrayList<Sprite> mySpriteList;
	public static ArrayList<Texture> myTexturesList;

	public static void load() {
		mySpriteList = new ArrayList<Sprite>();
		myTexturesList = new ArrayList<Texture>();
		
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Dog.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Dragon.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Goat.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Horse.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Monkey.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/OX.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Pig.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Rabbit.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/RAT.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Rooster.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Snake.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Zodiac/Tiger.png")));
		
		//
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Aquarius.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Aries.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Cancer.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Capricorn.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Gemini.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Leo.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Libra.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Pisces.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Sagittarius.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Scorpio.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Taurus.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Astro/[D]Card-Virgo.png")));
		//
		
		myTexturesList.add(new Texture(Gdx.files.internal("Action/[D]DoubleDamage.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Action/[D]Heal.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Action/[D]Preparation.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Action/[D]Shield.png")));
		myTexturesList.add(new Texture(Gdx.files.internal("Action/[D]Weapon.png")));
	}
	
	public static void loadWithString(String textureName) {
		texture_back = new Texture(Gdx.files.internal(textureName));
		texture_back.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite_back = new Sprite(texture_back);
		sprite_back.flip(false, true);
		mySpriteList.add(sprite_back);
		myTexturesList.add(texture_back);
	}
}
