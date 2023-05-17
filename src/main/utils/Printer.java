package main.utils;

public class Printer {

	public static String printMat4(Mat4 mat) {
		String res = "";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res += "," + mat.value[i][j];
			}
			res += "; ";
					
		}
		return res;
		
	}
	
	public static String printMat3(Mat3 mat) {
		String res = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				res += "," + mat.value[i][j];
			}
			res += "; ";
					
		}
		return res;
		
	}
}
