package fit.george.sp1.draft;


import org.andengine.entity.primitive.Line;
import org.andengine.util.color.Color;



public class RangeCircle {

	
	private int radius;
	private float x;
	private float y;
	private Line [] circle;
	private int corners;
	private Color color;
	private boolean visible;
	
	

	public boolean isVisible() {
		return visible;
	}
	
	
	
	public void setVisible(boolean b) {
		visible = b;
		for(int i = 0; i<corners; i++)
			circle[i].setVisible(visible);
	}
	
	
	
	public void calculateCircle(int r) {
		
		radius = r;
		float X, Y;
		
		
		float tmp_x = x + radius * (float) Math.cos(2 * Math.PI / 360 * (0 + 1) * 360 / corners);
		float tmp_y = y + radius * (float) Math.sin(2 * Math.PI / 360 * (0 + 1) * 360 / corners);
	
		
		for(int i = 1; i<=corners; i++) {
			
			X = x + radius * (float) Math.cos(2 * Math.PI / 360 * (i + 1) * 360 / corners);
			Y = y + radius * (float) Math.sin(2 * Math.PI / 360 * (i + 1) * 360 / corners);
			
				
			circle[i-1].setPosition(tmp_x, tmp_y, X, Y);
			
			tmp_x = X;
			tmp_y = Y;
			
		
		}
	}
	
	
	
	public void remove() {
		
		if(isVisible()) setVisible(false);
		for(int i = 0; i<corners; i++) {
			game_Scene.game_instance.detachChild(circle[i]);
			circle[i].dispose();
		}
	
	}
	
	
	
	public RangeCircle(float pX, float pY, int r, int con, Color pColor) {
		
		x = pX;
		y = pY;
		radius = r;
		corners = con;
		color = pColor;
		
		circle = new Line[corners];
		
		
		for(int i = 0; i<corners; i++) {
			
			circle[i] = new Line(0, 0, 0, 0, 1, MainActivity.instance.getVertexBufferObjectManager());
			circle[i].setZIndex(4);
			circle[i].setLineWidth(2.0f);
			circle[i].setColor(color);
			game_Scene.game_instance.attachChild(circle[i]);
			circle[i].setVisible(false);
	
		}
		
		calculateCircle(radius);
		
	}

	
	
}
