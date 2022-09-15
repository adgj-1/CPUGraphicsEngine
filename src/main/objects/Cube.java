package main.objects;

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
		
		this.faces.add(new Vector3(0,1,2));
		this.faces.add(new Vector3(0,2,3));
		this.faces.add(new Vector3(5,4,1));
		this.faces.add(new Vector3(5,1,0));
		this.faces.add(new Vector3(0,3,5));
		this.faces.add(new Vector3(6,5,3));
		this.faces.add(new Vector3(4,2,1));
		this.faces.add(new Vector3(2,4,7));
		this.faces.add(new Vector3(3,2,7));
		this.faces.add(new Vector3(6,3,7));
		this.faces.add(new Vector3(4,5,6));
		this.faces.add(new Vector3(6,7,4));
//		this.faces.add(new Vector3(0,1,4));
//		this.faces.add(new Vector3(0,2,4));
//		this.faces.add(new Vector3(2,4,7));
//		this.faces.add(new Vector3(0,2,4));
//		this.faces.add(new Vector3(2,3,7));
//		this.faces.add(new Vector3(0,4,5));
//		this.faces.add(new Vector3(3,5,6));
//		this.faces.add(new Vector3(3,6,7));
//		this.faces.add(new Vector3(4,5,6));
//		this.faces.add(new Vector3(4,6,7));
	}
}
