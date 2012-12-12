package fit.george.sp1.draft;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;


/**
 * Class <code>Money</code> represents players money in the game. 
 * @author Usmanov Radmir, Trushin Artyom
 *  
 */

public class Money {
	
	private Sprite faceBoss, faceCool, facePoker, faceTroll, faceWhy;
	private int money;
	private Text text, sight;
	private final static int TEXT_X = 690;
	private final static int TEXT_Y = 20;
	public static Money instance;
	
	
	/**
	 * Constructor of the class with parameter. Creates new instance of the <code>Money</code> class. 
	 * 
	 * @param Value of money
	 */
	public Money(int money) {
		instance = this;
		this.money = money;
		
		text = new Text(TEXT_X, TEXT_Y, MainActivity.instance.mFont, "$" + "10000", 
				new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		
		faceBoss = new Sprite( 12*60 , 1*60 , MainActivity.instance.mTextureRegionBoss, MainActivity.instance.getVertexBufferObjectManager());
		faceCool = new Sprite( 12*60 , 1*60 , MainActivity.instance.mTextureRegionCool, MainActivity.instance.getVertexBufferObjectManager());
		facePoker = new Sprite( 12*60 , 1*60 , MainActivity.instance.mTextureRegionPoker, MainActivity.instance.getVertexBufferObjectManager());
		faceTroll = new Sprite( 12*60 , 1*60 , MainActivity.instance.mTextureRegionTroll, MainActivity.instance.getVertexBufferObjectManager());
		faceWhy = new Sprite( 12*60 , 1*60 , MainActivity.instance.mTextureRegionWhy, MainActivity.instance.getVertexBufferObjectManager());
		
		game_Scene.game_instance.attachChild(text);
		game_Scene.game_instance.attachChild(faceBoss);
		game_Scene.game_instance.attachChild(faceCool);
		game_Scene.game_instance.attachChild(facePoker);
		game_Scene.game_instance.attachChild(faceTroll);
		game_Scene.game_instance.attachChild(faceWhy);
		
		text.setText("$" + Integer.toString(this.money));
		
		SetFace();
		
	}
	
	
	
	/**
	 * Simple constructor of the class. Creates new instance of the <code>Matrix</code> class. 
	 * 
	 */
	public Money() { this.money = 400;}
	
	
	
	/**
	 * Get actual value of money 
	 * 
	 */
	public int GetMoney() {
		return money;
	}
	
	
	
	/**
	 * Add money 
	 * 
	 * @param value of money
	 */
	public void AddMoney(int value) {
		
		this.money += value;
		text.setText("$" + Integer.toString(this.money));
		SetFace();
		
	}
	
	
	/**
	 * Set money status face
	 * 
	 */
	private void SetFace() {
		
		if ( this.money > 800) {
			faceBoss.setVisible(true);
			faceCool.setVisible(false);
			facePoker.setVisible(false);
			faceTroll.setVisible(false);
			faceWhy.setVisible(false);
		}
		else if ( this.money > 500) {
			faceBoss.setVisible(false);
			faceCool.setVisible(true);
			facePoker.setVisible(false);
			faceTroll.setVisible(false);
			faceWhy.setVisible(false);
		}
		else if ( this.money > 200) {
			faceBoss.setVisible(false);
			faceCool.setVisible(false);
			facePoker.setVisible(true);
			faceTroll.setVisible(false);
			faceWhy.setVisible(false);
		}
		else if ( this.money > 0) {
			faceBoss.setVisible(false);
			faceCool.setVisible(false);
			facePoker.setVisible(false);
			faceTroll.setVisible(true);
			faceWhy.setVisible(false);
		}
		else {
			faceBoss.setVisible(false);
			faceCool.setVisible(false);
			facePoker.setVisible(false);
			faceTroll.setVisible(false);
			faceWhy.setVisible(true);
		}
		
		
	}
	
	/**
	 * Deduct money 
	 * 
	 * @param value of money
	 */
	public boolean DeductMoney(int value) {
		
		if(this.money - value >= 0) {
			this.money -= value;
			text.setText("$" + Integer.toString(this.money));
			SetFace();
			
			return true;
		} else return false;
		
	}
	
	

}
