package fit.george.sp1.draft;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
//import org.andengine.opengl.font.Font;
//import org.andengine.opengl.font.FontFactory;
//import android.graphics.Color;
import android.view.KeyEvent;


public class mainState extends Scene {
	
	public static menu_Scene _Menu_Scene = new menu_Scene();
	public static game_Scene _Game_Scene = new game_Scene();
	public static about_Scene _About_Scene = new about_Scene();
	public static settings_Scene _Settings_Scene = new settings_Scene();

	
	public enum SceneType
	{
		SPLASH,
		MENU,
		SETTINGS,
		ABOUT,
		GAME
	}
	
	private static SceneType currentScene = SceneType.MENU;
	
	public mainState() {
		attachChild(_Menu_Scene);
		attachChild(_Game_Scene);
		attachChild(_About_Scene);
		attachChild(_Settings_Scene);
		ShowMainScene();
	}
	
	public static void ShowMainScene() {
		_Menu_Scene.Show();
		_Game_Scene.Hide();
		_About_Scene.Hide();
		_Settings_Scene.Hide();
		currentScene = SceneType.MENU;;
	}
	
	public static void ShowSettingsScene() {
		_Menu_Scene.Hide();
		_Game_Scene.Hide();
		_About_Scene.Hide();
		_Settings_Scene.Show();
		currentScene = SceneType.SETTINGS;;
	}
	
	public static void ShowAboutScene() {
		_Menu_Scene.Hide();
		_Game_Scene.Hide();
		_About_Scene.Show();
		_Settings_Scene.Hide();
		currentScene = SceneType.ABOUT;;
	}
	
	public static void ShowGameScene() {
		_Menu_Scene.Hide();
		_Game_Scene.Show();
		_About_Scene.Hide();
		_Settings_Scene.Hide();
		currentScene = SceneType.GAME;
	}
	
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		switch (currentScene)
		{
		case MENU:
			_Menu_Scene.onSceneTouchEvent(pSceneTouchEvent);
			break;
		case GAME:
			_Game_Scene.onSceneTouchEvent(pSceneTouchEvent);
			break;
		case SETTINGS:
			_Settings_Scene.onSceneTouchEvent(pSceneTouchEvent);
			break;
		case ABOUT:
			break;
		default:
			break;			
		}
		return super.onSceneTouchEvent(pSceneTouchEvent);
	}
	
	public boolean onKeyDownPressed(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
	    {	    	
	    	switch (currentScene)
	    	{
	    		case SPLASH:
	    			break;
	    		case MENU:
	    			System.exit(0);
	    			break;
	    		case SETTINGS:
	    			ShowMainScene();
                    currentScene = SceneType.MENU;
	    			break;
	    		case ABOUT:
	    			ShowMainScene();
                    currentScene = SceneType.MENU;
	    			break;
	    		case GAME:
	    			ShowMainScene();
                    currentScene = SceneType.MENU;
	    			break;
	    	}
	    }
	    return false; 
	}



	
}

