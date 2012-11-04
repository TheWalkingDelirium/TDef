package fit.george.sp1.draft;

import org.andengine.entity.scene.CameraScene;

public class game_Scene extends CameraScene {
	public game_Scene() {
		super(MainActivity.camera);
		setBackgroundEnabled(false);
		
	}
	
	public void Show(){
		setVisible(true);
    	setIgnoreUpdate(false);
//		
	}
	
    public void Hide(){
    	setVisible(false);
    	setIgnoreUpdate(true);
//		
	}
}
