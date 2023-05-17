package main.camera_overlays;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import main.materials.Material;
import main.utils.FragmentShader;
import main.utils.Mat4;
import main.utils.Vector3;
import main.utils.Vector4;
import main.utils.VertexShader;

public class RGBWireframeOverlay extends CameraOverlay {

	@Override
	public void overlayFace(Graphics g, Mat4 invCamera, Vector3 face, List<Vector3> vertices, Mat4 mLocalObj, Material faceMaterial) {
		Vector4 vertexW1 = mLocalObj.dotVecMat(vertices.get((int) (face.x)).toVec4());
		Vector4 vertexW2 = mLocalObj.dotVecMat(vertices.get((int) (face.y)).toVec4());
		Vector4 vertexW3 = mLocalObj.dotVecMat(vertices.get((int) (face.z)).toVec4());
		Vector3 pos1 = VertexShader.rasterizeZ(vertexW1, invCamera);
		Vector3 pos2 = VertexShader.rasterizeZ(vertexW2, invCamera);
		Vector3 pos3 = VertexShader.rasterizeZ(vertexW3, invCamera);
		
		g.setColor(Color.GREEN);
		g.drawLine((int) Math.ceil(pos1.x * FragmentShader.pixelWidth), (int)Math.ceil(pos1.y * FragmentShader.pixelHeight), (int) Math.ceil(pos2.x * FragmentShader.pixelWidth), (int)Math.ceil(pos2.y * FragmentShader.pixelHeight));
		g.setColor(Color.RED);
		g.drawLine((int) Math.ceil(pos1.x * FragmentShader.pixelWidth), (int)Math.ceil(pos1.y * FragmentShader.pixelHeight), (int) Math.ceil(pos3.x * FragmentShader.pixelWidth), (int)Math.ceil(pos3.y * FragmentShader.pixelHeight));
		g.setColor(Color.BLUE);
		g.drawLine((int) Math.ceil(pos2.x * FragmentShader.pixelWidth), (int)Math.ceil(pos2.y * FragmentShader.pixelHeight), (int) Math.ceil(pos3.x * FragmentShader.pixelWidth), (int)Math.ceil(pos3.y * FragmentShader.pixelHeight));
	}
}
