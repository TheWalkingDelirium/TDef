package fit.george.sp1.draft;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;

public class about_Scene extends CameraScene {
	public about_Scene() {
		super(MainActivity.mBoundChaseCamera);
	    this.setBackground(new Background(255/255f, 255/255f, 255/255f));
		
		Text _text_rules = new Text(50, 40, MainActivity.mUbuntuFont, "This is easy game. The goal is to try to stop\ncreeps from crossing a map by\nbuilding towers which shoot at them as they pass.", null);
		Text _text_credits = new Text(430, 260, MainActivity.mUbuntuFont, "Made by CVUT FIT team:\nDausheyev George-Mukhammed\nUsmanov Radmir\nTrushin Artyom\nParakhnich Ilya", null);
		
		_text_rules.setColor(15/255f, 15/255f, 15/255f); //Цвет текста
		_text_credits.setColor(75/255f, 75/255f, 75/255f); //Цвет текста
		
		MainActivity.creep.setScale(1.5f);
		MainActivity.creep.setPosition(20, 220);
    	
		this.attachChild(MainActivity.creep);
		this.attachChild(_text_rules);
		this.attachChild(_text_credits);
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
