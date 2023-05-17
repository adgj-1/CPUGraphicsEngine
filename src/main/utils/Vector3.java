package main.utils;

public class Vector3 {

	public float x;
	public float y;
	public float z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector4 toVec4() {
		return new Vector4(x,y,z,1);
	}
	
	public Vector3 normalize() {
		float magnitude = (float) Math.cbrt(x*x + y*y + z*z);
		return new Vector3(x/magnitude, y/magnitude, z/magnitude);
	}
	
	public Vector3 cross(Vector3 v) {
		float rx = y*v.z - z*y;
		float ry = z*v.x - x*v.z;
		float rz = x*v.y - y*v.x;
		return new Vector3(rx, ry, rz);
	}
}
