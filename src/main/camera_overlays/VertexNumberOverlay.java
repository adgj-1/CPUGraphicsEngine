package main.camera_overlays;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import main.utils.FragmentShader;
import main.utils.Mat4;
import main.utils.Vector3;
import main.utils.Vector4;
import main.utils.VertexShader;

public class VertexNumberOverlay extends CameraOverlay {
	
	public void overlayVertex(Graphics g, Mat4 invCamera,  List<Vector3> vertices, Mat4 mLocalObj) {
		for (int i = 0; i < vertices.size(); i++) {
			Vector4 vertexW = mLocalObj.dotVecMat(vertices.get(i).toVec4());
			Vector3 pos = VertexShader.rasterizeZ(vertexW, invCamera);
			g.setColor(Color.RED);
			g.drawString("" + i, (int) Math.ceil((pos.x + 5) * FragmentShader.pixelWidth), (int)Math.ceil(pos.y * FragmentShader.pixelHeight));
			g.fillRect((int) Math.ceil(pos.x * FragmentShader.pixelWidth), (int)Math.ceil(pos.y * FragmentShader.pixelHeight), 1, 1);
		}
	}

}
