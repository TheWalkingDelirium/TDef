package fit.george.sp1.draft;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
//import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.input.touch.TouchEvent;

public class menu_Scene extends CameraScene {
	
	public menu_Scene() {
		
		super(MainActivity.mBoundChaseCamera);
		this.setBackground(new Background(255 / 255f, 255 / 255f, 255 / 255f));
		
		Text _text_name = new Text(350, 15, MainActivity.mUbuntuLFont, // переопределение какой-то функции, чтобы включить обработку нажатий
				"    Tower\nDefence\n      v0.01", null);
		
		Text _text_start = new Text(100, 250, MainActivity.mUbuntuLFont,
				"Start", null)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionUp()) {
					mainState.ShowGameScene();
				}
				return true;
			}
		};

		Text _text_settings = new Text(100, 300, MainActivity.mUbuntuLFont,
				"Settings", null) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionUp()) {
					mainState.ShowSettingsScene();
				}
				return true;
			}
		};
		
		Text _text_about = new Text(100, 350, MainActivity.mUbuntuLFont,
				"About", null) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionUp()) {
					mainState.ShowAboutScene();
				}
				return true;
			}
		};

		this.registerTouchArea(_text_start);
		this.registerTouchArea(_text_about);
		this.registerTouchArea(_text_settings);

		
		_text_name.setColor(255 / 255f, 0 / 255f, 0 / 255f); // Цвет текста
		_text_start.setColor(20 / 255f, 20 / 255f, 20 / 255f); // Цвет текста
		_text_settings.setColor(20 / 255f, 20 / 255f, 20 / 255f); // Цвет текста
		_text_about.setColor(20 / 255f, 20 / 255f, 20 / 255f); // Цвет текста
		
		MainActivity.tower.setPosition((MainActivity.CAMERA_WIDTH - MainActivity.tower.getWidth())-50f, (MainActivity.CAMERA_HEIGHT - MainActivity.tower.getHeight())-50f);
    	
		this.attachChild(MainActivity.tower);
		this.attachChild(_text_name);
		this.attachChild(_text_start);
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
