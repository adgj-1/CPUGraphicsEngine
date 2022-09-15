package main.utils;

public class Mat4 {
	public float[][] value = new float[4][4];
	
	public Mat4(float[] dx, float[] dy, float[] dz, float[] t) {
		value[0] = dx;
		value[1] = dy;
		value[2] = dz;
		value[3] = t;
	}
	
	public Vector4 dot(Vector4 vec) {
		float x = vec.x * value[0][0] + vec.y * value[0][1] + vec.z * value[0][2] + vec.h * value[0][3];
		float y = vec.x * value[1][0] + vec.y * value[1][1] + vec.z * value[1][2] + vec.h * value[1][3];
		float z = vec.x * value[2][0] + vec.y * value[2][1] + vec.z * value[2][2] + vec.h * value[2][3];
		float h = vec.x * value[3][0] + vec.y * value[3][1] + vec.z * value[3][2] + vec.h * value[3][3];
		return new Vector4(x,y,z,h);
	}
	
	public Vector4 dotVecMat(Vector4 vec) {
		float x = vec.x * value[0][0] + vec.y * value[1][0] + vec.z * value[2][0] + vec.h * value[3][0];
		float y = vec.x * value[0][1] + vec.y * value[1][1] + vec.z * value[2][1] + vec.h * value[3][1];
		float z = vec.x * value[0][2] + vec.y * value[1][2] + vec.z * value[2][2] + vec.h * value[3][2];
		float h = vec.x * value[0][3] + vec.y * value[1][3] + vec.z * value[2][3] + vec.h * value[3][3];
		return new Vector4(x,y,z,h);
	}
}
