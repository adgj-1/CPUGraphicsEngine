package main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import main.materials.Material;
import main.utils.FragmentShader;
import main.utils.Mat4;
import main.utils.Vector2;
import main.utils.Vector3;
import main.utils.Vector4;
import main.utils.VertexShader;

public class Camera extends _3DObject {
	public static Vector2 screenSize = new Vector2(600, 600);
	public FragmentShader faceShader;
	
	public Camera(Vector3 pos, Vector3 rot) {
		this.pos = pos;
		this.rot = rot;
		this.faceShader = new FragmentShader();
		this.convertTransform();
	}
	
	public void render(Graphics g, _3DObject obj) {
		obj.convertTransform();
		
//		renderVertices(g, obj.vertices, obj.transform);
		for (int i = 0; i < obj.faces.size(); i++) {
			renderFace(g, obj.faces.get(i), obj.vertices, obj.transform, obj.materials.get(i));
		}
	}
	
	public void renderVertices(Graphics g, List<Vector3> vertices, Mat4 mLocalObj) {
		for (int i = 0; i < vertices.size(); i++) {
			Vector4 vertexW = mLocalObj.dotVecMat(vertices.get(i).toVec4());
			Vector2 pos = VertexShader.rasterize(vertexW, invtransform);
			g.setColor(Color.RED);
			g.drawString("" + i, (int)pos.x + 5, (int)pos.y);
			g.fillRect((int)pos.x, (int)pos.y, 1, 1);
		}
	}
	
	public void renderFace(Graphics g, Vector3 face, List<Vector3> vertices, Mat4 mLocalObj, Material faceMaterial) {
		Vector4 vertexW1 = mLocalObj.dotVecMat(vertices.get((int) (face.x)).toVec4());
		Vector4 vertexW2 = mLocalObj.dotVecMat(vertices.get((int) (face.y)).toVec4());
		Vector4 vertexW3 = mLocalObj.dotVecMat(vertices.get((int) (face.z)).toVec4());
		Vector3 pos1 = VertexShader.rasterizeZ(vertexW1, invtransform);
		Vector3 pos2 = VertexShader.rasterizeZ(vertexW2, invtransform);
		Vector3 pos3 = VertexShader.rasterizeZ(vertexW3, invtransform);
		

		
		faceShader.preRenderFace(pos1, pos2, pos3, faceMaterial);
	}
	
	public void postFrameRender(Graphics g, _3DObject obj) {
		for (int i = 0; i < obj.faces.size(); i++) {
//			overlayFace(g, obj.faces.get(i), obj.vertices, obj.transform, obj.materials.get(i));
		}
	}
	
	public void overlayFace(Graphics g, Vector3 face, List<Vector3> vertices, Mat4 mLocalObj, Material faceMaterial) {
		Vector4 vertexW1 = mLocalObj.dotVecMat(vertices.get((int) (face.x)).toVec4());
		Vector4 vertexW2 = mLocalObj.dotVecMat(vertices.get((int) (face.y)).toVec4());
		Vector4 vertexW3 = mLocalObj.dotVecMat(vertices.get((int) (face.z)).toVec4());
		Vector3 pos1 = VertexShader.rasterizeZ(vertexW1, invtransform);
		Vector3 pos2 = VertexShader.rasterizeZ(vertexW2, invtransform);
		Vector3 pos3 = VertexShader.rasterizeZ(vertexW3, invtransform);
		
		g.setColor(Color.GREEN);
		g.drawLine((int)pos1.x, (int)pos1.y, (int)pos2.x, (int)pos2.y);
		g.setColor(Color.RED);
		g.drawLine((int)pos1.x, (int)pos1.y, (int)pos3.x, (int)pos3.y);
		g.setColor(Color.BLUE);
		g.drawLine((int)pos2.x, (int)pos2.y, (int)pos3.x, (int)pos3.y);
	}
	
	public void renderFaces(Graphics g) {
		faceShader.renderFaces(g);
	}
}
