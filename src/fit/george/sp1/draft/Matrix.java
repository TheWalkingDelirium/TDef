package fit.george.sp1.draft;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import android.util.Log;

public class Matrix implements Constants{
	
	private final static int MATRIX_W = 10;
	private final static int MATRIX_H = 15;
	
	private static int[][] matrix;
	
	public Matrix() {
		
		matrix = new int[MATRIX_W][MATRIX_H];
		this.init();
		this.roadInit();
	
		final Path creepLevel1Path = new Path(7).to(130, 0).to(130, 340).to(370, 340).to(370, 100).to(490, 100).to(490, 280).to(620, 280);
		
		CreepLevel1.instance.registerEntityModifier(new LoopEntityModifier(new PathModifier(30, creepLevel1Path, null, new IPathModifierListener() {
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {

			}

			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
				switch(pWaypointIndex) {
					case 0:
						CreepLevel1.instance.animate(new long[]{200, 200, 200}, 6, 8, true);
						break;
					case 1:
						CreepLevel1.instance.animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
					case 2:
						CreepLevel1.instance.animate(new long[]{200, 200, 200}, 0, 2, true);
						break;
					case 3:
						CreepLevel1.instance.animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
					case 4:
						CreepLevel1.instance.animate(new long[]{200, 200, 200}, 6, 8, true);
						break;
					case 5:
						CreepLevel1.instance.animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
				}
			}

			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
			}

			
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
				CreepLevel1.instance.setPosition(130, 0);
				}
		})));
		
		
		
		
		final Path creepLevel2Path = new Path(7).to(130, 0).to(130, 340).to(370, 340).to(370, 100).to(490, 100).to(490, 280).to(620, 280);
		
		CreepLevel2.instance.registerEntityModifier(new LoopEntityModifier(new PathModifier(10, creepLevel2Path, null, new IPathModifierListener() {
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {

			}

			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
				switch(pWaypointIndex) {
					case 0:
						CreepLevel2.instance.animate(new long[]{200, 200, 200}, 6, 8, true);
						break;
					case 1:
						CreepLevel2.instance.animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
					case 2:
						CreepLevel2.instance.animate(new long[]{200, 200, 200}, 0, 2, true);
						break;
					case 3:
						CreepLevel2.instance.animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
					case 4:
						CreepLevel2.instance.animate(new long[]{200, 200, 200}, 6, 8, true);
						break;
					case 5:
						CreepLevel2.instance.animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
				}
			}

			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
			}

			
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
				CreepLevel2.instance.setPosition(130, 0);
				}
		})));
		
		
	}
	
	public int getValue(int i, int j) {
		return matrix[i][j];
	}
	
	public void setValue(int i, int j, int value) {
		matrix[i][j] = value;
	}
	
	private void init()	{
		
		for(int i = 1; i < MATRIX_W; i++)
			for(int j = 1; j < MATRIX_H; j++)
				matrix[i][j] = 0;
		
		
		for(int i = 0; i < MATRIX_H; i++)
			matrix[0][i] = FIELD_END;
		  
		for (int i = 0; i < MATRIX_W; i++)
			matrix[i][MATRIX_H-1] = FIELD_END;
		  
		for (int i = 0; i < MATRIX_H; i++)
			matrix[MATRIX_W-1][i] = FIELD_END;
		  
		for (int i = 0; i < MATRIX_W; i++)
			matrix[i][0] = FIELD_END;
		
	}
	
	private void roadInit()	{
		
		matrix[1][3] = NPC_GENERATOR; 				
		
		for(int i = 2; i < 8; i++)
			matrix[i][3] = ROAD;
		
		for(int i = 3; i < 8; i++)
			matrix[7][i] = ROAD;
		
		for(int i = 3; i < 8; i++)
			matrix[i][7] = ROAD;
		
		for(int i = 7; i < 10; i++)
			matrix[3][i] = ROAD;
		
		for(int i = 3; i < 7; i++)
			matrix[i][9] = ROAD;
		
		for(int i = 9; i < 12; i++)
			matrix[6][i] = ROAD;
		
		
		for(int i = 1; i < 4; i++)
			matrix[i][2] = LANDSCAPE_TREE_3;
		
		for(int i = 1; i < 4; i++)
			matrix[i][4] = LANDSCAPE_TREE_1;
		
		for(int i = 5; i < 8; i++)
			matrix[i][13] = LANDSCAPE_TREE_2;
		
		matrix[7][12] = LANDSCAPE_TREE_2;
		matrix[5][12] = LANDSCAPE_TREE_2;
		matrix[6][12] = CASTLE;
		
	}
	
	public void print()	{
		
		String str = "";
		
		for(int i = 0; i < MATRIX_W; i++) {
			for(int j = 0; j < MATRIX_H; j++) {
				str = str + Integer.toString(matrix[i][j]);
			}
			str += "\n";
		}
		
		Log.d("Matrix", str);

	}
	
	public int getCountOfRoad() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == ROAD ) count++;
		
		return count;
	}
	
	public int getCountOfTree1() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_1 ) count++;
		
		return count;
	}
	
	public int getCountOfTree2() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_2 ) count++;
		
		return count;
	}
	
	public int getCountOfTree3() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_3 ) count++;
		
		return count;
	}

}