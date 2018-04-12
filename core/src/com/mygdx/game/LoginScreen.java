package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import user.AccessSQLDatabase;
import user.User;

public class LoginScreen implements Screen{
	private SpriteBatch batch;
    private BitmapFont font;
	private Game game;
	private Stage stage;
	private TextField txfUsername;
	private TextField txfPassword;
	private String inputtedUsername;
	private String inputtedPassword;
	
	public LoginScreen(Game g) {
		batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin textSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		//LOGIN BUTTON
		TextButton btnLogin = new TextButton ("Login", textSkin);
		btnLogin.setPosition(300, 200);
		btnLogin.setSize(300, 60);
		
		btnLogin.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnLoginClicked();
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		//LOGIN FIELDS
		txfPassword = new TextField("", textSkin);
		txfPassword.setPosition(300, 300);
		stage.addActor(txfPassword);
		txfUsername = new TextField("", textSkin);
		txfUsername.setPosition(300, 350);
		stage.addActor(txfUsername);
		
		stage.addActor(btnLogin);
	}
	
	public void btnLoginClicked() {
		
		System.out.println("Username is: " + txfUsername.getText());
		System.out.println("Password is: " + txfPassword.getText());
		AccessSQLDatabase acc = new AccessSQLDatabase(this);
		// fields must not be empty
		if (!txfUsername.getText().isEmpty() && !txfUsername.getText().isEmpty())
		{
			inputtedUsername = txfUsername.getText();
			inputtedPassword = txfPassword.getText();
			User myUser = acc.getUser(inputtedUsername, inputtedPassword);
			if(myUser==null)
			{
				System.out.println("INVALID SIGNUP!");
				error();
		        return;
			}
			game.setScreen(new ProfileScreen(game));
		}
	}
	
	public void error() {
		System.out.println("Trying to draw error");
		
		int row_height = Gdx.graphics.getWidth() / 12;
	    int col_width = Gdx.graphics.getWidth() / 12;
		Label.LabelStyle label1Style = new Label.LabelStyle();
	    BitmapFont myFont = new BitmapFont();
	    label1Style.font = myFont;
	    label1Style.fontColor = Color.RED;
	 
	    Label label1 = new Label("Invalid input!",label1Style);
	    label1.setSize(Gdx.graphics.getWidth(),row_height);
	    label1.setPosition(0,Gdx.graphics.getHeight()-row_height*4);
	    label1.setAlignment(Align.center);
	    stage.addActor(label1);
	}
	
	public String getUsername() {
		return inputtedUsername;
	}
	
	public String getPassword() {
		return inputtedPassword;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		batch.begin();
        font.draw(batch, "Username", 300, 400);
        font.draw(batch, "Password", 300, 345);
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
