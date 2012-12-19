
package fit.george.sp1.draft;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;

import android.util.Log;



public class TowerMenu {
	
	private static TowerMenu instance = null;
	private int x, y;
	private Sprite tower1Icon, tower2Icon, tower3Icon, towerCancel;
	private Text text1, text2, text3;
	//private boolean touch = false;
	
	
	protected TowerMenu() {
	
		
		text1 = new Text(0, 0, MainActivity.instance.towerFont, "Gold: " +  BasicTower.GetPrice(), 
				new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		text1.setZIndex(4);
		text2 = new Text(0, 0, MainActivity.instance.towerFont, "Gold: " +  QuickfireTower.GetPrice(), 
				new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		text2.setZIndex(4);
		text3 = new Text(0, 0, MainActivity.instance.towerFont, "Gold: " +  FrozenTower.GetPrice(), 
				new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		text3.setZIndex(4);
		
		
		
		//Tower1 icon
		tower1Icon = new Sprite(0, 0, MainActivity.instance.mTextureRegionTower1Icon, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					
					if((game_Scene.game_instance.money.GetMoney() - BasicTower.GetPrice()) >= 0) {
						game_Scene.game_instance.towerObserver.createBasicTower(x - (x % 60), y - (y % 60));
						hide();
					}
					
				}
				return true;
			}
		};
		tower1Icon.setScale(1.0f);
		tower1Icon.setZIndex(4);
		
		
		
		
		//Tower2 icon
		tower2Icon = new Sprite(0, 0, MainActivity.instance.mTextureRegionTower2Icon, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					
					if((game_Scene.game_instance.money.GetMoney() - QuickfireTower.GetPrice()) >= 0) {
						game_Scene.game_instance.towerObserver.createQuickfireTower(x - (x % 60), y - (y % 60));
						hide();
					}
					
				}
				return true;
			}
		};
		tower2Icon.setScale(1.0f);
		tower2Icon.setZIndex(4);
				
		
		
		//Tower3 icon
		tower3Icon = new Sprite(0, 0, MainActivity.instance.mTextureRegionTower3Icon, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					
					if((game_Scene.game_instance.money.GetMoney() - FrozenTower.GetPrice()) >= 0) {
						game_Scene.game_instance.towerObserver.createFrozenTower(x - (x % 60), y - (y % 60));
						hide();
					}
					
				}
				return true;
			}
		};
		tower3Icon.setScale(1.0f);
		tower3Icon.setZIndex(4);
		
			
		
		//Tower cancel icon
		towerCancel = new Sprite(0, 0, MainActivity.instance.mTextureRegionCancel, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					
					hide();
					
				}
				return true;
			}
		};
		towerCancel.setScale(0.8f);
		towerCancel.setZIndex(4);
		
		
	}
	
	
	private void show(int pX, int pY) {
		
		
		/*if(pX == 720 || pY == 420 || pX == 0 || pY == 0) touch = true;
		if(pX == 0 && pY == 0) touch = false;
		if(pX == 720 && pY == 0) touch = false;
		if(pX == 720 && pY == 420) touch = false;
		if(pX == 0 && pY == 420) touch = false;
		*/
		
		/*if(pX == 720) pX -= 60;
		if(pY == 420) pY -= 60;
		if(pX == 0) pX += 60;
		if(pY == 0) pY += 60;
		*/
		
		tower1Icon.setPosition(pX - 60, pY);
		game_Scene.game_instance.attachChild(tower1Icon);
		game_Scene.game_instance.registerTouchArea(tower1Icon);
		tower1Icon.setVisible(true);
		
		
		tower2Icon.setPosition(pX + 60, pY);
		game_Scene.game_instance.attachChild(tower2Icon);
		game_Scene.game_instance.registerTouchArea(tower2Icon);
		tower2Icon.setVisible(true);
		
		
		tower3Icon.setPosition(pX, pY - 60);
		game_Scene.game_instance.attachChild(tower3Icon);
		game_Scene.game_instance.registerTouchArea(tower3Icon);
		tower3Icon.setVisible(true);
		
		
		towerCancel.setPosition(pX, pY + 60);
		game_Scene.game_instance.attachChild(towerCancel);
		game_Scene.game_instance.registerTouchArea(towerCancel);
		towerCancel.setVisible(true);
		
		
		text1.setPosition(pX - 60, pY + 65);
		game_Scene.game_instance.attachChild(text1);
		text1.setVisible(true);
		
		text2.setPosition(pX + 60, pY + 65);
		game_Scene.game_instance.attachChild(text2);
		text2.setVisible(true);
		
		text3.setPosition(pX, pY + 5);
		game_Scene.game_instance.attachChild(text3);
		text3.setVisible(true);
		
		
		game_Scene.isMenuOpen = true;
		
	}
	
	
	
	public void hide() {
		
		
		//if(touch == false) {
			
			tower1Icon.setVisible(false);
			game_Scene.game_instance.unregisterTouchArea(tower1Icon);
			game_Scene.game_instance.detachChild(tower1Icon);
			
			tower2Icon.setVisible(false);
			game_Scene.game_instance.unregisterTouchArea(tower2Icon);
			game_Scene.game_instance.detachChild(tower2Icon);
			
			tower3Icon.setVisible(false);
			game_Scene.game_instance.unregisterTouchArea(tower3Icon);
			game_Scene.game_instance.detachChild(tower3Icon);
			
			towerCancel.setVisible(false);
			game_Scene.game_instance.unregisterTouchArea(towerCancel);
			game_Scene.game_instance.detachChild(towerCancel);
			
			game_Scene.game_instance.detachChild(text1);
			text1.setVisible(false);
			game_Scene.game_instance.detachChild(text2);
			text2.setVisible(false);
			game_Scene.game_instance.detachChild(text3);
			text3.setVisible(false);
			
			game_Scene.isMenuOpen = false;
		
		//}
		
		//touch = false;
		
	}
	

	
	public void touch(TouchEvent pSceneTouchEvent) {
			int controller_w, controller_h;
		if (pSceneTouchEvent.isActionUp()) {
	        
			y = (int) pSceneTouchEvent.getY(); //matrix w 
			x = (int) pSceneTouchEvent.getX(); //matrix h
			
			controller_w = (int) (y / 60) + 1;
			controller_h = (int) (x / 60) + 1;
			if(controller_w > Matrix.MATRIX_W || controller_h > Matrix.MATRIX_H) return;
			if(Matrix.matrix[((int) (y / 60)) + 1][((int) (x / 60)) + 1] == Constants.EMPTY) {
				//&& (game_Scene.game_instance.money.GetMoney() - Tower.GetPrice()) >= Constants.EMPTY) {
				
				show(x - (x % 60), y - (y % 60));
			
			}
			
	    }
		
	}
	
	
	public static TowerMenu getInstance() {
		
		if(instance == null) {
			instance = new TowerMenu();
		}
		
		return instance;
	
	}
	
	

}


