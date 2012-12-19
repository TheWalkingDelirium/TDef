package fit.george.sp1.draft;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.text.Text;
//import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.input.touch.TouchEvent;

public class menu_Scene extends CameraScene {
	private Text _text_resume;
	private int show_resume;
	
	public menu_Scene() {
		
		super(MainActivity.mBoundChaseCamera);
		//this.setBackground(new Background(241 / 255f, 228 / 255f, 209 / 255f));
		this.setBackground(new SpriteBackground(MainActivity.menu_background));		
	
		_text_resume = new Text(50, 225, MainActivity.mHarringtonLFont,
				"Resume", null)
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
		
		
		
		Text _text_start = new Text(50, 285, MainActivity.mHarringtonLFont,
				"Start", null)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionUp()) {
					if (show_resume < 1) {
						mainState.ShowGameScene();
						show_resume++;
					}
					else mainState.newGame();
					
				}
				return true;
			}
		};

		Text _text_settings = new Text(50, 345, MainActivity.mHarringtonLFont,
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
		
		Text _text_about = new Text(50, 405, MainActivity.mHarringtonLFont,
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

		
//		_text_name.setColor(255 / 255f, 0 / 255f, 0 / 255f); 
		_text_start.setColor(20 / 255f, 20 / 255f, 20 / 255f); 
		_text_resume.setColor(20 / 255f, 20 / 255f, 20 / 255f);
		_text_settings.setColor(20 / 255f, 20 / 255f, 20 / 255f);
		_text_about.setColor(20 / 255f, 20 / 255f, 20 / 255f); 
		
	//	MainActivity.tower.setPosition((MainActivity.CAMERA_WIDTH - MainActivity.tower.getWidth())-50f, (MainActivity.CAMERA_HEIGHT - MainActivity.tower.getHeight())-50f);
    	
		//this.attachChild(MainActivity.tower);
	//	this.attachChild(_text_name);
		this.attachChild(_text_start);
		this.attachChild(_text_settings);
		this.attachChild(_text_about);
		this.attachChild(_text_resume);
		
		_text_resume.setVisible(false);
		show_resume = 0;
	}

	public void Show() {
		if( show_resume == 1) {
			this.registerTouchArea(_text_resume);
			_text_resume.setVisible(true);
			show_resume++;
		}
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
