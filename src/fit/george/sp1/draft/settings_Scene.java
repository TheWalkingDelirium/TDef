package fit.george.sp1.draft;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

public class settings_Scene extends CameraScene {
	private boolean text_sound;
	private boolean text_grid;
	
	public settings_Scene() {
		super(MainActivity.mBoundChaseCamera);
		this.setBackground(new Background(255/255f, 255/255f, 255/255f));
	    Text _text_sound = new Text(100, 250, MainActivity.mUbuntuLFont, "Sound", MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					//yoursound.setvolume(0.0f);
					if(text_sound==false){ 
						this.setColor(Color.GREEN);
						text_sound = true;
					}
					else {
						this.setColor(Color.BLACK);
						text_sound = false;
					}
				}
				return true;
			}
		};
		 
		Text _text_grid = new Text(100, 300, MainActivity.mUbuntuLFont, "Grid", MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if(text_grid==false){ 
						this.setColor(Color.GREEN);
						game_Scene.game_instance.grid_switch(false);
						text_grid = true;
						
					}
					else {
						this.setColor(Color.BLACK);
						game_Scene.game_instance.grid_switch(true);
						text_grid = false;
					}
				}
				return true;
			}
		};
		
		_text_sound.setColor(Color.BLACK); //Text color
		_text_grid.setColor(Color.BLACK);
		this.registerTouchArea(_text_sound);
		this.registerTouchArea(_text_grid);
		this.attachChild(_text_sound);
		this.attachChild(_text_grid);
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
