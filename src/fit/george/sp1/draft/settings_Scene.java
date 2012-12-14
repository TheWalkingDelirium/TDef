package fit.george.sp1.draft;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

public class settings_Scene extends CameraScene {
	public settings_Scene() {
		super(MainActivity.mBoundChaseCamera);
		this.setBackground(new Background(255/255f, 255/255f, 255/255f));
	    Text _text_sound = new Text(100, 250, MainActivity.mUbuntuLFont, "Sound", null) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					//yoursound.setvolume(0.0f);
					if(this.getColor()==Color.BLACK){ 
						this.setColor(Color.GREEN);
					}
					else {
						this.setColor(Color.BLACK);
					}
				}
				return true;
			}
		};
		
		
		_text_sound.setColor(Color.BLACK); //Цвет текста
		this.registerTouchArea(_text_sound);
		this.attachChild(_text_sound);
	}
	
	public void Show(){
		setVisible(true);
    	setIgnoreUpdate(false);
	}
	
    public void Hide(){
    	setVisible(false);
    	setIgnoreUpdate(true);
	}
}
