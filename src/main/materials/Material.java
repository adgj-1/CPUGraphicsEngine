package main.materials;

import java.awt.Color;

import main.utils.Vector2;
import main.utils.Vector3;

public class Material {

	private static int indexCounter = 0;
	protected Color materialColor;
	
	public Material() {
		materialColor = indexColor(indexCounter, 9);
		indexCounter = (indexCounter + 1) % 100;
	}
	
	public Material(Color c) {
		materialColor = c;
	}
	
	public Color getColor(Vector3 v1, Vector3 v2, Vector3 v3, Vector2 pos) {
		return materialColor;
	}
	
	/**
	 * 
	 * @param mapping - UV mapping coordinates of the fragment
	 * @param v1 - screen coordinate of vertex 1 with Z axis
	 * @param v2 - screen coordinate of vertex 2 with Z axis
	 * @param v3 - screen coordinate of vertex 3 with Z axis
	 * @param pos - screen coordinate of fragment to render
	 * @param pointZ - Z axis of fragment to render
	 * @return Color - the color of the fragment
	 */
	public Color getColor(Vector2 mapping, Vector3 v1, Vector3 v2, Vector3 v3, Vector2 pos, float pointZ) {
		return getColor(v1, v2, v3, pos);
	}
	
	
	
	
	
	// Default mapping
	public Vector2[] getTextureMap() {
		return new Vector2[] {new Vector2(1,0), new Vector2(0,0), new Vector2(0,1)};
	}
	
	public static Color indexColor(int index, int max) {
		switch (index % max) {
		case 0: {
			return Color.RED;
		}
		
		case 1: {
			return Color.GREEN;
		}
		
		case 2: {
			return Color.BLUE;
		}
		
		case 3: {
			return Color.GRAY;
		}
		
		case 4: {
			return Color.CYAN;
		}
		
		case 5: {
			return Color.MAGENTA;
		}
		
		case 6: {
			return Color.YELLOW;
		}
		
		case 7: {
			return Color.DARK_GRAY;
		}
		
		case 8: {
			return Color.ORANGE;
		}
		
		case 9: {
			return Color.PINK;
		}
		
		default: {
			System.out.println(index%max);
			return Color.BLACK;
		}
		}
	}

	public void setColor(Color c) {
		materialColor = c;
	}
}
