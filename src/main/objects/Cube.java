package main.objects;

import java.awt.image.BufferedImage;

import main.materials.Material;
import main.materials.TexturedMaterial;
import main.utils.Vector2;
import main.utils.Vector3;

public class Cube extends _3DObject {
	public Cube(Vector3 pos, Vector3 rot, float scale) {
		this.pos = pos;
		this.rot = rot;
		this.vertices.add(new Vector3(0,0,0));
		this.vertices.add(new Vector3(scale,0,0));
		this.vertices.add(new Vector3(scale,scale,0));
		this.vertices.add(new Vector3(0,scale,0));
		this.vertices.add(new Vector3(scale,0,scale));
		this.vertices.add(new Vector3(0,0,scale));
		this.vertices.add(new Vector3(0,scale,scale));
		this.vertices.add(new Vector3(scale,scale,scale));
		
		this.addFace(new Vector3(0,1,2));
		this.addFace(new Vector3(0,2,3));
		this.addFace(new Vector3(5,4,1));
		this.addFace(new Vector3(5,1,0));
		this.addFace(new Vector3(0,3,5));
		this.addFace(new Vector3(6,5,3));
		this.addFace(new Vector3(4,2,1));
		this.addFace(new Vector3(2,4,7));
		this.addFace(new Vector3(3,2,7));
		this.addFace(new Vector3(6,3,7));
		this.addFace(new Vector3(4,5,6));
		this.addFace(new Vector3(6,7,4));
	}
	
	public Cube(Vector3 pos, Vector3 rot, float scale, BufferedImage[] texture) {
		this.pos = pos;
		this.rot = rot;
		this.vertices.add(new Vector3(0,0,0));
		this.vertices.add(new Vector3(scale,0,0));
		this.vertices.add(new Vector3(scale,scale,0));
		this.vertices.add(new Vector3(0,scale,0));
		this.vertices.add(new Vector3(scale,0,scale));
		this.vertices.add(new Vector3(0,0,scale));
		this.vertices.add(new Vector3(0,scale,scale));
		this.vertices.add(new Vector3(scale,scale,scale));
		
		this.addFace(new Vector3(0,1,2), texture[0], true);
		this.addFace(new Vector3(2,3,0), texture[0], false);
		this.addFace(new Vector3(5,4,1), texture[0], true);
		this.addFace(new Vector3(1,0,5), texture[0], false);
		this.addFace(new Vector3(5,0,3), texture[0], true);
		this.addFace(new Vector3(3,6,5), texture[0], false);
		this.addFace(new Vector3(2,1,4), texture[0], true);
		this.addFace(new Vector3(4,7,2), texture[0], false);
		this.addFace(new Vector3(3,2,7), texture[0], true);
		this.addFace(new Vector3(7,6,3), texture[0], false);
		this.addFace(new Vector3(4,5,6), texture[0], true);
		this.addFace(new Vector3(6,7,4), texture[0], false);
	}
	
	public void addFace(Vector3 face) {
		faces.add(face);
		materials.add(new Material());
	}
	
	public void addFace(Vector3 face, BufferedImage img, boolean top) {
		faces.add(face);
		if (top) {
			materials.add(new TexturedMaterial(new Vector2[] {new Vector2(1,0), new Vector2(0,0), new Vector2(0,1)}, img));
		} else {
			materials.add(new TexturedMaterial(new Vector2[] {new Vector2(0,1), new Vector2(1,1), new Vector2(1,0)}, img));
		}
	}
}
