package main;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import main.objects._3DObject;
import main.utils.FaceShader;


@SuppressWarnings("serial")
public class Canvas extends JPanel {

	
	public Canvas() {
		
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
//		System.out.print("repaint");
		try {
			for (_3DObject obj : Engine.global_obj_list) {
				obj.render(g);
			}
		} catch (Exception e) {
			
		}
		FaceShader.renderFaces(g);
		
		try {
			for (_3DObject obj : Engine.global_obj_list) {
				obj.postFrameRender(g);
			}
		} catch (Exception e) {
			
		}
	}
}
