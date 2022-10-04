package main.materials;

import java.awt.Color;

import main.utils.Vector2;
import main.utils.Vector3;

public class DepthMaterial extends Material {

	public Color getColor(Vector2 mapping, Vector3 v1, Vector3 v2, Vector3 v3, Vector2 pos, float pointZ) {
		// color based on z depth
//		return new Color((int)pointZ*2 % 255, (int)pointZ*2 % 255, (int)pointZ*2 % 255);
		
		// Preserve Color, Add z depth
		return new Color((materialColor.getRed() * (int) pointZ*4)/255 % 255, (materialColor.getGreen() * (int)pointZ*4)/255 % 255, (materialColor.getBlue() * (int)pointZ*4)/255 % 255);
	}
}
