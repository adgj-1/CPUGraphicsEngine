package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.camera_overlays.RGBWireframeOverlay;
import main.camera_overlays.VertexNumberOverlay;
import main.listeners.MouseMotionListener_;
import main.objects.Camera;
import main.objects.Cube;
import main.objects.Model;
import main.objects._3DObject;
import main.utils.ImageLoader;
import main.utils.Vector2;
import main.utils.Vector3;

public class Engine {
	public static List<_3DObject> global_obj_list = new ArrayList<_3DObject>();
	public static Camera cam;
	
	// Example Code
	static Cube testcube;
	static Cube testcube2;
	static Model monkey;
	static BufferedImage texturetest;
	
	public void init() {
		
		// Example Code
		
		cam = new Camera(new Vector3(0, 0, 0), new Vector3(0, 0, 0));
		cam.overlays.add(new RGBWireframeOverlay());
		cam.overlays.add(new VertexNumberOverlay());
		
		texturetest = ImageLoader.loadImage(new File("").getAbsolutePath() + "/test_resources/bliss.jpg");
		testcube = new Cube(new Vector3(-20,300f,-20), new Vector3(0,0,0), 50, new BufferedImage[] {texturetest,texturetest,texturetest,texturetest,texturetest,texturetest});
		global_obj_list.add(testcube);
		
		testcube2 = new Cube(new Vector3(40,150f,-20), new Vector3(0,0,0), 50);
		global_obj_list.add(testcube2);
		
		monkey = new Model(new File("").getAbsolutePath() + "/test_resources/suzanne.obj", new Vector3(0,0,0), -20, Color.CYAN);
		monkey.rot.y = (float) (1*Math.PI);
		monkey.rot.x = (float) (0.5*Math.PI);
		global_obj_list.add(monkey);
		monkey.overlayEnabled = false;
	}
	
	
	
	public void startloop() {
		while (true) {
			update();
			
			if (Camera.screenSize.x != Main.j.getWidth() || Camera.screenSize.y != Main.j.getHeight()) {
				Camera.screenSize = new Vector2(Main.j.getWidth(), Main.j.getHeight());
				cam.faceShader.resizeBuffer();
			}
			
			Main.c.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		//Example Code
		monkey.rot.z = -(float)MouseMotionListener_.mX / 30;
		monkey.pos.y = 10;
		testcube.rot.z = -(float)MouseMotionListener_.mX / 100;
		testcube.rot.y = -(float)MouseMotionListener_.mY / 100;
		monkey.pos.y = (float)MouseMotionListener_.mY + 10;
		testcube2.rot.z = -(float)MouseMotionListener_.mX / 100;
		testcube2.rot.y = -(float)MouseMotionListener_.mY / 100-0.5f;
	}
}
