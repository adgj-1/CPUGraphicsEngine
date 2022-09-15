package main.utils;

public class Mat3 {

	public float[][] value = new float[3][3];
	
	public Mat3(float[] dx, float[] dy, float[] dz) {
		value[0] = dx;
		value[1] = dy;
		value[2] = dz;
	}
	
	public Mat3 transpose() {
		return new Mat3(new float[] {value[0][0], value[1][0], value[2][0]},
				new float[] {value[0][1], value[1][1], value[2][1]},
				new float[] {value[0][2], value[1][2], value[2][2]});
	}
	
	public Mat3 multiply(Mat3 r) {
		Mat3 rT = r.transpose();
//		System.out.println("===" + Printer.printMat3(rT));
		float[] row1 = new float[]{arr3dot(value[0], rT.value[0]), arr3dot(value[0], rT.value[1]), arr3dot(value[0], rT.value[2])};
		float[] row2 = new float[]{arr3dot(value[1], rT.value[0]), arr3dot(value[1], rT.value[1]), arr3dot(value[1], rT.value[2])};
		float[] row3 = new float[]{arr3dot(value[2], rT.value[0]), arr3dot(value[2], rT.value[1]), arr3dot(value[2], rT.value[2])};
		return new Mat3(row1, row2, row3);
	}
	
	public static float arr3dot(float[] a1, float[] a2) {
		float res = 0;
		for (int i = 0; i < 3; i++) {
			res += a1[i] * a2[i];
		}
		return res;
	}
}
