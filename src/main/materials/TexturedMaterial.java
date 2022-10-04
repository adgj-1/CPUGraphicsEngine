package main.materials;

import java.awt.Color;
import java.awt.image.BufferedImage;

import main.utils.Vector2;
import main.utils.Vector3;

public class TexturedMaterial extends Material {

	private Vector2[] mapping;
	private BufferedImage image;
	
	public TexturedMaterial() {
	mapping = new Vector2[] {new Vector2(1,0), new Vector2(0,0), new Vector2(0,1)};
	}
	
	public TexturedMaterial(Vector2[] mapping) {
		this.mapping = mapping;
	}
	
	public TexturedMaterial(Vector2[] mapping, BufferedImage image) {
		this.mapping = mapping;
		this.image = image;
	}
	
	public Vector2[] getTextureMap() {
		return mapping;
	}
	
	public Color getColor(Vector2 mapping, Vector3 v1, Vector3 v2, Vector3 v3, Vector2 pos, float pointZ) {
		if (image == null) {
			return getDefaultGradientColor(mapping);
		}
		return new Color(image.getRGB((int)(mapping.x * image.getWidth()), (int)(mapping.y * image.getHeight())));
	}
	
	/**
	 * 
	 * @param mapping - UV mapping coordinate of the fragment
	 * @return Color of fragment generated as a default color gradient
	 */
	public Color getDefaultGradientColor(Vector2 mapping) {
		return new Color(125, (int)(mapping.x * 255), (int)(mapping.y * 255));
	}
}
