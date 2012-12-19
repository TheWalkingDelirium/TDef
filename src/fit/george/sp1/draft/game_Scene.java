
package fit.george.sp1.draft;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import android.opengl.GLES20;



import android.util.Log;


/**
 * Class <code>game_Scene</code> represents game scene in the game. Inheritor of CameraScene.
 * @author Radmir Usmanov
 *
 * 
 */
public class game_Scene extends Scene {
	
	
	public static game_Scene game_instance;
	private Sprite [] road, tree1, tree2, tree3;
	private Creep_generator gen;
	private Castle castle;
	private Matrix matrix;
	public TowerObserver towerObserver;
	public Money money; //Player's money
	public Tower currentTower = null;
	public static boolean isMenuOpen = false;
	public TowerMenu towerMenu;
	
	
	private Text mGameOverText;
	private Text mWinText;
	public boolean isRunning;
	private Entity bogus; // fake entity for moving camera. Camera chases
							// invisible fake entity.
	private final Line[] line_vert = new Line[30];
	private final Line[] line_horiz = new Line[20];

	/**
	 * Method detects on scene touch event.
	 * 
	 * @param pSceneTouchEvent - touch event pointer
	 */
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.isActionUp() && isRunning ) {
			
			//if(currentTower == null) {
			if(isMenuOpen == false && currentTower == null) {
				
				towerMenu.touch(pSceneTouchEvent);
				
				//towerObserver.touch(pSceneTouchEvent);
			}
			
		}
		
		return super.onSceneTouchEvent(pSceneTouchEvent);
	
	}


	
	
	
	/**
	 * Constructor of the class. Creates new instance of the <code>game_Scene</code> class.
	 * 
	 */
	public game_Scene()  {
		
		//super(MainActivity.mBoundChaseCamera);
		game_instance = this;
		isRunning = true;
		towerMenu = TowerMenu.getInstance();
		
		
		
		setBackground(MainActivity.instance.mGrassBackground);
		
		matrix = new Matrix();
		money = new Money(300);
		
		bogus = new Entity();
	/*	bogus.setPosition(MainActivity.CAMERA_WIDTH / 3,
				MainActivity.CAMERA_HEIGHT / 3);*/
//		bogus = new Sprite(MainActivity.CAMERA_WIDTH / 2,
//				MainActivity.CAMERA_HEIGHT / 2,
//				MainActivity.instance.mTextureRegionTree1,
//				MainActivity.instance.getVertexBufferObjectManager());
//				
		this.loadMap();

		// adding the lines that match the bounds of the camera. Width==2. Color
		// - white. Gameworld is 4-times bigger than camera.
		final Rectangle ground = new Rectangle(0,
				MainActivity.CAMERA_HEIGHT * 2 - 2,
				MainActivity.CAMERA_WIDTH * 2, 2,
				MainActivity.instance.getVertexBufferObjectManager());
		final Rectangle roof = new Rectangle(0, 0,
				MainActivity.CAMERA_WIDTH * 2, 2,
				MainActivity.instance.getVertexBufferObjectManager());
		final Rectangle left = new Rectangle(0, 0, 2,
				MainActivity.CAMERA_HEIGHT * 2,
				MainActivity.instance.getVertexBufferObjectManager());
		final Rectangle right = new Rectangle(
				MainActivity.CAMERA_WIDTH * 2 - 2, 0, 2,
				MainActivity.CAMERA_HEIGHT * 2,
				MainActivity.instance.getVertexBufferObjectManager());
		attachChild(ground);
		attachChild(roof);
		attachChild(left);
		attachChild(right);
		// adding the grid
		
			for (int i = 1; i <= 26; i++) {
				line_vert[i - 1] = new Line(i * 60, 0, i * 60,
						MainActivity.GAMEWORLD_HEIGHT, 1,
						MainActivity.instance.getVertexBufferObjectManager());
				line_vert[i - 1].setColor(Color.WHITE);
				attachChild(line_vert[i - 1]);
			}

			for (int i = 1; i < 16; i++) {
				line_horiz[i - 1] = new Line(0, i * 60,
						MainActivity.GAMEWORLD_WIDTH, i * 60, 1,
						MainActivity.instance.getVertexBufferObjectManager());
				line_horiz[i - 1].setColor(Color.WHITE);
				attachChild(line_horiz[i - 1]);
			}
		
//=======================================================================
		// initializing analog_control for moving Bogus entity. Camera chases
		// bogus entity.
		final PhysicsHandler physicsHandler = new PhysicsHandler(bogus);
		bogus.registerUpdateHandler(physicsHandler);

		final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(
				MainActivity.CAMERA_WIDTH
						- MainActivity.mOnScreenControlBaseTextureRegion.getWidth()
						- 100,
				MainActivity.CAMERA_HEIGHT
						- MainActivity.mOnScreenControlBaseTextureRegion
								.getHeight(), MainActivity.mBoundChaseCamera,
				MainActivity.mOnScreenControlBaseTextureRegion,
				MainActivity.mOnScreenControlKnobTextureRegion, 0.1f, 700,
				MainActivity.instance.getVertexBufferObjectManager(),
				new IAnalogOnScreenControlListener() {
					@Override
					public void onControlChange(
							final BaseOnScreenControl pBaseOnScreenControl,
							final float pValueX, final float pValueY) {
						physicsHandler
								.setVelocity(pValueX * 100, pValueY * 100);
					}

					@Override
					public void onControlClick(
							final AnalogOnScreenControl pAnalogOnScreenControl) {
					}
				});
		analogOnScreenControl.getControlBase().setBlendFunction(
				GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.75f);
		analogOnScreenControl.getControlKnob().setScale(1.75f);
		analogOnScreenControl.refreshControlKnobPosition();

		MainActivity.mBoundChaseCamera.setChaseEntity(bogus);
		
		
		/*
		final Line [] line_vert = new Line[15];
		final Line [] line_horiz = new Line[10];
		
		for(int i = 1; i <= 13; i++) {
			line_vert[i-1] = new Line(i * 60, 0, i * 60, 480, 1, MainActivity.instance.getVertexBufferObjectManager());
			line_vert[i-1].setColor(Color.WHITE);
			attachChild(line_vert[i-1]);
		}
		
		for(int i = 1; i < 8; i++) {
			line_horiz[i-1] = new Line(0, i * 60, 800, i * 60, 1, MainActivity.instance.getVertexBufferObjectManager());
			line_horiz[i-1].setColor(Color.WHITE);
			attachChild(line_horiz[i-1]);
		}
		*/
		
		attachChild(bogus);

		setChildScene(analogOnScreenControl);
		towerObserver = new TowerObserver();
		this.sortChildren();
		
	
	}
		
		
	/**
	 * Loads map's landscape.
	 *
	 */	
	private void loadMap() {
		
			
			
			road = new Sprite[ matrix.GetCountOfRoad() ];
			tree1 = new Sprite[ matrix.GetCountOfTree1() ];
			tree2 = new Sprite[ matrix.GetCountOfTree2() ];
			tree3 = new Sprite[ matrix.GetCountOfTree3() ];
			int count_road = 0, count_tree1 = 0, count_tree2 = 0, count_tree3 = 0;
			
			
			for ( int i = 1; i < 9; i++)
				for ( int j = 1; j < 14; j++) {
					if ( matrix.GetValue(i, j) == Constants.ROAD ) {
						road[count_road] = new Sprite((j-1)*60 , (i-1)*60 , MainActivity.instance.mTextureRegionRoad, MainActivity.instance.getVertexBufferObjectManager());
						attachChild( road[count_road++]);
					}
					else if ( matrix.GetValue(i, j) == Constants.CASTLE )  {
						castle = new Castle((j-1)*60 , (i-1)*60 , MainActivity.instance.mTextureRegionCastle, MainActivity.instance.getVertexBufferObjectManager());
						attachChild( castle );
					}
					
					else if ( matrix.GetValue(i, j) == Constants.NPC_GENERATOR )  {
						gen = new Creep_generator((j-1)*60 , (i-1)*60 , MainActivity.instance.mTextureRegionGen, MainActivity.instance.getVertexBufferObjectManager());
						attachChild( gen );
					}
					
					else if ( matrix.GetValue(i, j) == Constants.LANDSCAPE_TREE_1 ) {
						tree1[count_tree1] = new Sprite((j-1)*60 , (i-1)*60 , MainActivity.instance.mTextureRegionTree1, MainActivity.instance.getVertexBufferObjectManager());
						attachChild( tree1[count_tree1++]);
					}
					
					else if ( matrix.GetValue(i, j) == Constants.LANDSCAPE_TREE_2 ) {
						tree2[count_tree2] = new Sprite((j-1)*60 , (i-1)*60 , MainActivity.instance.mTextureRegionTree2, MainActivity.instance.getVertexBufferObjectManager());
						attachChild( tree2[count_tree2++]);
					}
					
					else if ( matrix.GetValue(i, j) == Constants.LANDSCAPE_TREE_3 ) {
						tree3[count_tree3] = new Sprite((j-1)*60 , (i-1)*60 , MainActivity.instance.mTextureRegionTree3, MainActivity.instance.getVertexBufferObjectManager());
						attachChild( tree3[count_tree3++]);
					}
				}
			
	
	}
		
		
		
	/**
	 * Show game_Scene.
	 *
	 */	
	public void Show(){
		setVisible(true);
		if( isRunning ) setIgnoreUpdate(false); 
	}
	
	
	/**
	 * Hide game_Scene.
	 *
	 */	
	public void Hide(){
    	setVisible(false);
    	setIgnoreUpdate(true);
	}
	
	
	
	public void onGameOver() {
		isRunning = false;
		this.mGameOverText = new Text(0, 0, MainActivity.mHarringtonLFont, "Game Over", new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		this.mGameOverText.setPosition(( MainActivity.CAMERA_WIDTH - this.mGameOverText.getWidth()) * 0.5f, ( MainActivity.CAMERA_HEIGHT - this.mGameOverText.getHeight()) * 0.5f);
		this.attachChild(mGameOverText);
		
		setIgnoreUpdate(true);
		setOnSceneTouchListener(null);
	}
    
	public void onWin() {
		isRunning = false;
		this.mWinText = new Text(0,0, MainActivity.mHarringtonLFont, "Victory", new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		this.mWinText.setPosition(( MainActivity.CAMERA_WIDTH - this.mWinText.getWidth()) * 0.5f, ( MainActivity.CAMERA_HEIGHT - this.mWinText.getHeight()) * 0.5f);
		this.attachChild(mWinText);
		setIgnoreUpdate(true);
		setOnSceneTouchListener(null);
	}
    
    


	/**
	 * Switch grid visibility.
	 * @param grid - is grid visible or not
	 * 
	 */
	public void grid_switch(boolean grid) {
		if (grid == false){
			for (int i = 1; i <= 26; i++) {
					line_vert[i - 1].setVisible(false);
				}
			for (int i = 1; i < 16; i++) {
					line_horiz[i - 1].setVisible(false);
				}
		}
		else{
			for (int i = 1; i <= 26; i++) {
				line_vert[i - 1].setVisible(true);
			}
			for (int i = 1; i < 16; i++) {
				line_horiz[i - 1].setVisible(true);
			}
		}
	}

}