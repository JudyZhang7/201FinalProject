package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import user.AccessSQLDatabase;
import user.AddToSQLDatabase;
import user.User;

public class SignupScreen implements Screen{
	private SpriteBatch batch;
    private BitmapFont font;
	private FireplacePebble game;
	private Stage stage;
	private TextField txfUsername;
	private TextField txfPassword;
	private String inputtedUsername;
	private String inputtedPassword;
	
	int buttonHeight = 200;
	int buttonWidth = 60;
	float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
	
    public SignupScreen(FireplacePebble g) {
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin textSkin = new Skin(Gdx.files.internal(game.getSkin()));
		
		batch = new SpriteBatch();    
        font = game.regfont16();
        font.setColor(Color.WHITE);
		
		//LOGIN BUTTON
		TextButton btnLogin = new TextButton ("Sign Up", textSkin);
		btnLogin.setPosition(w/3, (1*h)/4);
		btnLogin.setSize(buttonHeight, buttonWidth);

		btnLogin.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnLoginClicked();
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		
		TextButton btnBack = new TextButton ("Back", textSkin);
		btnBack.setPosition(w/6, (3*h)/4);
		btnBack.setSize(buttonHeight/2, buttonWidth/2);
		btnBack.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button) {
				btnBackClicked();
			}
			public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
				return true;
			}
		});
		//LOGIN FIELDS
		txfUsername = new TextField("", textSkin);
		txfUsername.setPosition(w/3, (h)/4 + buttonHeight);
		stage.addActor(txfUsername);
		txfPassword = new TextField("", textSkin);
		txfPassword.setPosition(w/3, (h)/4 + (2*buttonHeight)/3);
		stage.addActor(txfPassword);
		
		stage.addActor(btnLogin);
		stage.addActor(btnBack);
	}
	
	public void btnBackClicked() {
		game.setScreen(new FrontPage(game));
	}
	
	public void btnLoginClicked() {
		System.out.println(txfUsername.getText());
		System.out.println(txfPassword.getText());
		//SEND TO DA BACK
		AddToSQLDatabase ATSD = new AddToSQLDatabase();
		// fields must not be empty
		if (!txfUsername.getText().isEmpty() && !txfUsername.getText().isEmpty())
		{
			inputtedUsername = txfUsername.getText();
			inputtedPassword = txfPassword.getText();
			User user = ATSD.addToDatabase(inputtedUsername, inputtedPassword);
			if(user == null) {
				System.out.println("INVALID SIGNUP!");
				error();
		        return;
			}
			//SET USER
			game.setUser(user);
			game.printCurrentUser(); //for testing
			game.setScreen(new ProfileScreen(game));
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
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
	    label1.setPosition(w/3, Gdx.graphics.getHeight()-row_height*(18/3));
	    stage.addActor(label1);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Texture texture = new Texture("login.jpg");
		TextureRegion mainBackground = new TextureRegion(texture, 0, 0, 800, 500);
		batch.begin();
		batch.draw(mainBackground, 0, 0, w, h);
		
        font.draw(batch, "Username", w/3, (h)/2 + buttonHeight/4);
        font.draw(batch, "Password", w/3, (h)/2 - buttonHeight/12);
        batch.end();
        stage.act(delta);
		stage.draw();
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
