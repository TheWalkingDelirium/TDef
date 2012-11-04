package fit.george.sp1.draft;

import android.util.Log;

public class Matrix implements Constants {
	
	
	private final static int MATRIX_W = 10;
	private final static int MATRIX_H = 15;
	// 1 поле - 40px na 40px;
	
	
	private static int[][] matrix;
	
	
	
	public int GetValue(int i, int j) {
		return matrix[i][j];
	}
	
	public void SetValue(int i, int j, int value) {
		matrix[i][j] = value;
	}
	
	
	
	
	public int [][] GetMatrix()
	{
		return matrix;
	}
	
	
	
	private void Init()
	{
		
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
	
	
	
	
	private void RoadInit()
	{
		
		matrix[1][3] = NPC_GENERATOR; //исходная точка крипов
				
		
		//строим дорогу
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
	
	
	
	public void print()
	{
		
		String str = "";
		
		for(int i = 0; i < MATRIX_W; i++) {
			for(int j = 0; j < MATRIX_H; j++) {
				str = str + Integer.toString(matrix[i][j]);
			}
			str += "\n";
		}
		
		Log.d("Matrix", str);
		
		
	}
	
	
	
	
	public Matrix() {
		
		matrix = new int[MATRIX_W][MATRIX_H];
		this.Init();
		this.RoadInit();
		
	}
	
	
	
	
	
	public int GetCountOfRoad() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == ROAD ) count++;
		
		return count;
	}
	
	public int GetCountOfTree1() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_1 ) count++;
		
		return count;
	}
	
	public int GetCountOfTree2() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_2 ) count++;
		
		return count;
	}
	
	public int GetCountOfTree3() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_3 ) count++;
		
		return count;
	}
	

}