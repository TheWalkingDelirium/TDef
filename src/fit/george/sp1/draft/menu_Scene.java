package fit.george.sp1.draft;

import org.andengine.entity.scene.CameraScene;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.text.Text;
//import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.input.touch.TouchEvent;

public class menu_Scene extends CameraScene {
	
	public menu_Scene() {
		
		super(MainActivity.mBoundChaseCamera);
		//this.setBackground(new Background(241 / 255f, 228 / 255f, 209 / 255f));
		this.setBackground(new SpriteBackground(MainActivity.menu_background));		
//		Text _text_name = new Text(350, 15, MainActivity.mUbuntuLFont,
//				"    Tower\nDefence\n      v0.01", null);
		
		Text _text_start = new Text(50, 225, MainActivity.mHarringtonLFont,
				"New Game", null)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					mainState.ShowGameScene();
				}
				return true;
			}
		};

		Text _text_resume = new Text(50, 285, MainActivity.mHarringtonLFont,
				"Resume", null)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					mainState.ShowGameScene();
				}
				return true;
			}
		};
		
		Text _text_settings = new Text(50, 345, MainActivity.mHarringtonLFont,
				"Settings", null) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					mainState.ShowSettingsScene();
				}
				return true;
			}
		};
		
		Text _text_about = new Text(50, 405, MainActivity.mHarringtonLFont,
				"About", null) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					mainState.ShowAboutScene();
				}
				return true;
			}
		};

		this.registerTouchArea(_text_start);
		this.registerTouchArea(_text_resume);
		this.registerTouchArea(_text_about);
		this.registerTouchArea(_text_settings);

		
		
//		_text_name.setColor(255 / 255f, 0 / 255f, 0 / 255f); // Цвет текста
		_text_start.setColor(20 / 255f, 20 / 255f, 20 / 255f); // Цвет текста
		_text_resume.setColor(20 / 255f, 20 / 255f, 20 / 255f); // Цвет текста
		_text_settings.setColor(20 / 255f, 20 / 255f, 20 / 255f); // Цвет текста
		_text_about.setColor(20 / 255f, 20 / 255f, 20 / 255f); // Цвет текста
		
		//MainActivity.tower.setPosition((MainActivity.CAMERA_WIDTH - MainActivity.tower.getWidth())-50f, (MainActivity.CAMERA_HEIGHT - MainActivity.tower.getHeight())-50f);
    	
//		this.attachChild(MainActivity.tower);
//		this.attachChild(_text_name);
		this.attachChild(_text_start);
		this.attachChild(_text_resume);
		this.attachChild(_text_settings);
		this.attachChild(_text_about);
	}

	public void Show() {
		setVisible(true);
		setIgnoreUpdate(false);
		//
	}

	public void Hide() {
		setVisible(false);
		setIgnoreUpdate(true);
		//
	}
}
