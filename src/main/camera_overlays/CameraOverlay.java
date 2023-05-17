package main.camera_overlays;

import java.awt.Graphics;
import java.util.List;

import main.materials.Material;
import main.utils.Mat4;
import main.utils.Vector3;

/**
 * Post render overlays to draw on canvas
 * Default root class for all overlays
 * Blank overlay
 * @author Aaron
 *
 */
public class CameraOverlay {

	public CameraOverlay() {
		
	}
	
	public void overlayFace(Graphics g, Mat4 invCamera, Vector3 face, List<Vector3> vertices, Mat4 mLocalObj, Material faceMaterial) {
		
	}
	
	public void overlayVertex(Graphics g, Mat4 invCamera,  List<Vector3> vertices, Mat4 mLocalObj) {
		
	}
	
	public void overlayUI(Graphics g) {
		
	}
}
