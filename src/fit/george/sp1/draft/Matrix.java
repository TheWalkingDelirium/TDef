package fit.george.sp1.draft;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import android.util.Log;

/**
 * Class <code>Matrix</code> represents the matrix of map. 
 * @author Usmanov Radmir, Trushin Artyom
 *  
 */

public class Matrix implements Constants {
	
	
	public final static int MATRIX_W = 10;
	public final static int MATRIX_H = 15;
	// 1 ���� - 40px na 40px;
	
	
	
	
	
	public static int[][] matrix;
	
	
	/**
	 * Get value in position x,y.
	 * 
	 * @return
	 * 	<code>Integer</code> value
	 */
	public int GetValue(int i, int j) {
		return matrix[i][j];
	}
	
	
	/**
	 * Set value in position x,y.
	 * 
	 */
	public void SetValue(int i, int j, int value) {
		matrix[i][j] = value;
	}
	
	
	
	/**
	 * Get matrix.
	 * 
	 * @return
	 * 	<code>Integer [][]</code> matrix
	 */
	public int [][] GetMatrix()
	{
		return matrix;
	}
	
	
	/**
	 * Initialization matrix
	 * 
	 */
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
	
	
	/**
	 * Get path for creeps moving
	 * 
	 * @return
	 * 	object of <code>Path</code> class 
	 */
	public static final Path getPath(int x, int y) {
		  final  Path path = new Path(7).to(2*60 + x, 0 + y).to(2*60 + x, 5*60 + y).to(6*60 + x, 5*60 + y).
		    to(6*60 + x, 2*60 + y).to(8*60 + x, 2*60 + y).to(8*60 + x, 5*60 + y).to(11*60 + x, 5*60 + y);
		  return path;
		 }
	
	
	
	/**
	 * Get direction for creep.
	 * 
	 * @return
	 * 	<code>Integer</code> 
	 */
	public static int getDirection(int direction) {
		
		if ( direction == 2) return UP;
		if ( direction == 0 || direction == 4) return DOWN;
		if ( direction == 1 || direction == 3 || direction == 5) return RIGHT;
		
		return 0;
	}
	
	
	/**
	 * Initialization map
	 */
	private void RoadInit()
	{
		
		  matrix[1][3] = NPC_GENERATOR; //�������� ����� ������
	    
		  
		  //������ ������
		  for(int i = 2; i < 7; i++)
		   matrix[i][3] = ROAD;
		  
		  for(int i = 3; i < 8; i++)
		   matrix[6][i] = ROAD;
		  
		  for(int i = 3; i < 7; i++)
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
		  
		  
		  for(int i = 5; i < 14; i++)
		   matrix[1][i] = LANDSCAPE;
		  for(int i = 2; i < 5; i++)
		   matrix[i][13] = LANDSCAPE;
		  for(int i = 1; i < 14; i++)
		   matrix[8][i] = LANDSCAPE;
		  for(int i = 1; i < 8; i++)
		   matrix[i][1] = LANDSCAPE;
		
	}
	
	
	/**
	 * Auxiliary method for print matrix
	 * 
	 */
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
	
	
	
	/**
	 * Simple constructor of the class. Creates new instance of the <code>Matrix</code> class. 
	 * 
	 */
	public Matrix() {
		
		matrix = new int[MATRIX_W][MATRIX_H];
		this.Init();
		this.RoadInit();
		
		
	}
	
	
	
	/**
	 * Get count of road parts
	 * 
	 * @return
	 * <code>Integer</code> 
	 */
	public int GetCountOfRoad() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == ROAD ) count++;
		
		return count;
	}
	
	
	
	/**
	 * Get count of trees (type 1)
	 * 
	 * @return
	 * <code>Integer</code> 
	 */
	public int GetCountOfTree1() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_1 ) count++;
		
		return count;
	}
	
	
	/**
	 * Get count of trees (type 2)
	 * 
	 * @return
	 * <code>Integer</code> 
	 */
	public int GetCountOfTree2() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_2 ) count++;
		
		return count;
	}
	
	
	/**
	 * Get count of trees (type 3)
	 * 
	 * @return
	 * <code>Integer</code> 
	 */
	public int GetCountOfTree3() {
		int count = 0;
		for ( int i = 0; i < MATRIX_W; i++)
			for ( int j = 0; j < MATRIX_H; j++)
				if( matrix[i][j] == LANDSCAPE_TREE_3 ) count++;
		
		return count;
	}
	
	/*
	public void readFromFile() {
		
		// try opening the myfilename.txt
		  try {
		    // open the file for reading
		    InputStream instream  = openFileInput("myfilename.txt");
		 
		    // if file the available for reading
		    if (instream) {
		      // prepare the file for reading
		      InputStreamReader inputreader = new InputStreamReader(instream);
		      BufferedReader buffreader = new BufferedReader(inputreader);
		                 
		      String line;
		 
		      // read every line of the file into the line-variable, on line at the time
		      while (( line = buffreader.readLine())) {
		        // do something with the settings from the file
		      }
		 
		    }
		     
		    // close the file again       
		    instream.close();
		  } catch (java.io.FileNotFoundException e) {
		    // do something if the myfilename.txt does not exits
		  }
	}
	*/
	
	

}