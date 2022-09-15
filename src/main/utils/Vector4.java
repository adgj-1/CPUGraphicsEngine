package main.utils;

public class Vector4 {

	public float x;
	public float y;
	public float z;
	public float h;
	
	public Vector4(float x, float y, float z, float h) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.h = h;
	}

	public Vector3 toVec3() {
		return new Vector3(x,y,z);
	}
}
