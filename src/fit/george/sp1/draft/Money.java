package fit.george.sp1.draft;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;




public class Money {
	
	private int money = 400;
	private Text text;
	private final static int TEXT_X = 700;
	private final static int TEXT_Y = 20;
	
	
	
	
	public Money(int money) {
		
		this.money = money;
		
		
		text = new Text(TEXT_X, TEXT_Y, MainActivity.instance.mFont, "$" + Integer.toString(this.money), 
				new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		
		game_Scene.game_instance.attachChild(text);
		
	}
	
	
	
	
	public Money() {}
	
	
	
	
	public int GetMoney() {
		return money;
	}
	
	
	
	
	public void AddMoney(int value) {
		
		this.money += value;
		text.setText("$" + Integer.toString(this.money));
		
	}
	
	
	
	public boolean DeductMoney(int value) {
		
		if(this.money - value >= 0) {
			this.money -= value;
			text.setText("$" + Integer.toString(this.money));
			return true;
		} else return false;
		
	}
	
	

}
