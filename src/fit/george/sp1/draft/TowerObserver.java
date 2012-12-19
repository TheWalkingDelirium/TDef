package fit.george.sp1.draft;



import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.primitive.Line;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import android.util.Log;



/**
 * Class <code>TowerObserver</code> represents tower generator and tower observer in the game.
 * @author Radmir Usmanov
 *
 * 
 */
public class TowerObserver {
	
	
	public static List<Tower> list;
	private Tower tower;
	private int count = 0;
	private float x, y;
	private int r;
	
	
	/**
	 * Constructor of the class. Creates new instance of the <code>TowerObserver</code> class.
	 * 
	 */
	public TowerObserver() {
		list = new ArrayList<Tower>();	
	}
	
	
	
	/**
	 * Method that handle touch event.
	 * 
	 */
	public void touch(TouchEvent pSceneTouchEvent) {
		
		/*
		if (pSceneTouchEvent.isActionUp()) {
	        
			y = pSceneTouchEvent.getY(); 
			x = pSceneTouchEvent.getX(); 
			

			if(Matrix.matrix[((int) (y / 60)) + 1][((int) (x / 60)) + 1] == Constants.EMPTY && (game_Scene.game_instance.money.GetMoney() - Tower.GetPrice()) >= Constants.EMPTY) {
			
				createTower(x - (x % 60), y - (y % 60));
				game_Scene.game_instance.money.DeductMoney(Tower.GetPrice());
				Matrix.matrix[((int) (y / 60)) + 1][((int) (x / 60)) + 1] = Matrix.TOWER;

			}
			
	    }
	    */
		
	}
	
	
	
	/**
	 * Tower creation method.
	 * 
	 */
	public void createBasicTower(float x, float y) {
		
		tower = new BasicTower(x, y, MainActivity.instance.dragon, MainActivity.instance.getVertexBufferObjectManager());
		game_Scene.game_instance.attachChild(tower);
		game_Scene.game_instance.registerTouchArea(tower);
		tower.setZIndex(2);
		game_Scene.game_instance.sortChildren();
		
		list.add(tower);
		count++;
		game_Scene.game_instance.money.DeductMoney(BasicTower.GetPrice());
		Matrix.matrix[((int) (y / 60)) + 1][((int) (x / 60)) + 1] = Matrix.TOWER;

		
	}
	
	
	public void createFrozenTower(float x, float y) {
		
		tower = new FrozenTower(x, y, MainActivity.instance.tower1, MainActivity.instance.getVertexBufferObjectManager());
		game_Scene.game_instance.attachChild(tower);
		game_Scene.game_instance.registerTouchArea(tower);
		tower.setZIndex(2);
		game_Scene.game_instance.sortChildren();
		
		list.add(tower);
		count++;
		game_Scene.game_instance.money.DeductMoney(FrozenTower.GetPrice());
		Matrix.matrix[((int) (y / 60)) + 1][((int) (x / 60)) + 1] = Matrix.TOWER;

		
	}
	
	
	public void createQuickfireTower(float x, float y) {
		
		tower = new QuickfireTower(x, y, MainActivity.instance.tower2, MainActivity.instance.getVertexBufferObjectManager());
		game_Scene.game_instance.attachChild(tower);
		game_Scene.game_instance.registerTouchArea(tower);
		tower.setZIndex(2);
		game_Scene.game_instance.sortChildren();
		
		list.add(tower);
		count++;
		game_Scene.game_instance.money.DeductMoney(QuickfireTower.GetPrice());
		Matrix.matrix[((int) (y / 60)) + 1][((int) (x / 60)) + 1] = Matrix.TOWER;
		
	}
	
	
	
	
	
	

}
