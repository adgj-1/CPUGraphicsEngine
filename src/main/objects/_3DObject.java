package main.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.Engine;
import main.utils.Mat3;
import main.utils.Mat4;
import main.utils.Vector3;

public class _3DObject {

	List<Vector3> vertices;
	List<Vector3> faces;
	
	public Vector3 pos;
	public Vector3 rot;
	
	Mat4 transform;
	Mat4 invtransform;
	
	public _3DObject() {
		vertices = new ArrayList<Vector3>();
		faces = new ArrayList<Vector3>();
		rot = new Vector3(0,0,0);
		pos = new Vector3(0,0,0);
	}
	
	public void convertTransform() {
		Mat3 rx = new Mat3(new float[] {1,0,0}, new float[] {0,(float) Math.cos(rot.x),(float) -Math.sin(rot.x)}, new float[] {0, (float) Math.sin(rot.x), (float) Math.cos(rot.x)}) ;
		Mat3 ry = new Mat3(new float[] {(float) Math.cos(rot.y),0,(float) Math.sin(rot.y)}, new float[] {0,1,0}, new float[] {(float) -Math.sin(rot.y),0,(float) Math.cos(rot.y)}) ;
		Mat3 rz = new Mat3(new float[] {(float) Math.cos(rot.z), (float) -Math.sin(rot.z),0}, new float[] {(float) Math.sin(rot.z), (float) Math.cos(rot.z), 0}, new float[] {0,0,1}) ;
		Mat3 rotationMat = rx.multiply(ry).multiply(rz);
		float[] row1 = new float[] {rotationMat.value[0][0],rotationMat.value[0][1],rotationMat.value[0][2],0};
		float[] row2 = new float[] {rotationMat.value[1][0],rotationMat.value[1][1],rotationMat.value[1][2],0};
		float[] row3 = new float[] {rotationMat.value[2][0],rotationMat.value[2][1],rotationMat.value[2][2],0};
		float[] row4 = new float[] {pos.x,pos.y,pos.z,1};
		transform = new Mat4(row1, row2, row3, row4);
		rotationMat = rotationMat.transpose(); 
		row1 = new float[] {rotationMat.value[0][0],rotationMat.value[0][1],rotationMat.value[0][2],0};
		row2 = new float[] {rotationMat.value[1][0],rotationMat.value[1][1],rotationMat.value[1][2],0};
		row3 = new float[] {rotationMat.value[2][0],rotationMat.value[2][1],rotationMat.value[2][2],0};
		row4 = new float[] {-pos.x,-pos.y,-pos.z,1};
		invtransform = new Mat4(row1, row2, row3, row4);
	}
	
	public void render(Graphics g) {
		Engine.cam.render(g, this);
	}
}
