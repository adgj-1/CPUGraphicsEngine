package main.utils;

import main.objects.Camera;

public class VertexShader {
	public static float zoom = 1f;
	public static Vector2 rasterizeOrth(Vector4 vertex, Mat4 cameraTransform) {
		
		Vector4 vertexC = cameraTransform.dotVecMat(vertex);
		return new Vector2(vertexC.x, vertexC.z);
	}
	
	public static Vector2 rasterize(Vector4 vertex, Mat4 cameraTransform) {	
		Vector4 vertexC = cameraTransform.dotVecMat(vertex);
		Vector2 cameraSpace = new Vector2(vertexC.x/vertexC.y * zoom, vertexC.z/vertexC.y * zoom);
		return new Vector2((cameraSpace.x + 0.5f) * Camera.screenSize.x, (cameraSpace.y + 0.5f) * Camera.screenSize.y);
	}
	
	public static Vector3 rasterizeZ(Vector4 vertex, Mat4 cameraTransform) {	
		Vector4 vertexC = cameraTransform.dotVecMat(vertex);
		Vector2 cameraSpace = new Vector2(vertexC.x/vertexC.y * zoom, vertexC.z/vertexC.y * zoom);
		float screenMaxDim = Math.max(FragmentShader.resolutionX, FragmentShader.resolutionY);
		return new Vector3((cameraSpace.x + 0.5f) * screenMaxDim, (cameraSpace.y + 0.5f) * screenMaxDim, vertexC.y);
	}
	
}
