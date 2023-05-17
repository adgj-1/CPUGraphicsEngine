package main.utils;

public class Vector2 {

	public float x;
	public float y;
	
	public Vector2(float x2, float z) {
		this.x = x2;
		this.y = z;
	}
	
	public Vector2 difference(Vector2 v) {
		return new Vector2(x - v.x, y - v.y);
	}
	
	public Vector3 toVec3(float z) {
		return new Vector3(x, y, z);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
