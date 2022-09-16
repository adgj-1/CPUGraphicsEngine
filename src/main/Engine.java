package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.listeners.MouseMotionListener_;
import main.objects.Camera;
import main.objects.Cube;
import main.objects.Model;
import main.objects._3DObject;
import main.utils.FaceShader;
import main.utils.Vector2;
import main.utils.Vector3;

public class Engine {
	public static List<_3DObject> global_obj_list = new ArrayList<_3DObject>();
	public static Camera cam;
	static Cube testcube;
	static Cube testcube2;
	static Model monkey;
	public void init() {
		cam = new Camera(new Vector3(0, 0, 0), new Vector3(0, 0, 0));
		testcube = new Cube(new Vector3(-20,300f,-20), new Vector3(0,0,0), 50);
		testcube2 = new Cube(new Vector3(40,150f,-20), new Vector3(0,0,0), 50);
		global_obj_list.add(testcube);
		global_obj_list.add(testcube2);
		monkey = new Model("C:\\Users\\Aaron\\Downloads\\suzanne.obj", new Vector3(0,0,0), -20, Color.CYAN);
		monkey.rot.y = (float) (1*Math.PI);
		monkey.rot.x = (float) (0.5*Math.PI);
		global_obj_list.add(monkey);
	}
	
	public void startloop() {
		while (true) {
//			testcube.rot.z += 0.01f;
			monkey.rot.z = -(float)MouseMotionListener_.mX / 30;
			monkey.pos.x = -20;
			monkey.pos.y = 100;
			monkey.pos.z = -20;
			testcube.rot.z = -(float)MouseMotionListener_.mX / 100;
			testcube.rot.y = -(float)MouseMotionListener_.mY / 100;
//			monkey.pos.y = (float)MouseMotionListener_.mY + 50;
			testcube2.rot.z = -(float)MouseMotionListener_.mX / 100;
			testcube2.rot.y = -(float)MouseMotionListener_.mY / 100-0.5f;
			
			if (Camera.screenSize.x != Main.j.getWidth() || Camera.screenSize.y != Main.j.getHeight()) {
				Camera.screenSize = new Vector2(Main.j.getWidth(), Main.j.getHeight());
				FaceShader.resizeBuffer();
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
}
