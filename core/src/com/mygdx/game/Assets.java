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

	public static void load() {
		mySpriteList = new ArrayList<Sprite>();
		
		loadWithString("Zodiac/Dog.png");
		loadWithString("Zodiac/Dragon.png");
		loadWithString("Zodiac/Goat.png");
		loadWithString("Zodiac/Horse.png");
		loadWithString("Zodiac/Monkey.png");
		loadWithString("Zodiac/OX.png");
		loadWithString("Zodiac/Pig.png");
		loadWithString("Zodiac/Rabbit.png");
		loadWithString("Zodiac/RAT.png");
		loadWithString("Zodiac/Rooster.png");
		loadWithString("Zodiac/Snake.png");
		loadWithString("Zodiac/Tiger.png");
	}
	
	public static void loadWithString(String textureName) {
		texture_back = new Texture(Gdx.files.internal(textureName));
		texture_back.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite_back = new Sprite(texture_back);
		sprite_back.flip(false, true);
		mySpriteList.add(sprite_back);
	}
}