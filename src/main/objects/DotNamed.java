package main.objects;

import java.awt.Color;
import java.awt.Graphics;

import main.utils.Printer;
import main.utils.Vector3;
import main.utils.Vector4;

public class DotNamed extends _3DObject {

	public DotNamed(Vector3 pos, Vector3 dotPos) {
		super();
		this.pos = pos;
		this.vertices.add(dotPos);
	}
	
	public DotNamed(Vector3 pos, Vector3 rot, Vector3 dotPos) {
		super();
		this.pos = pos;
		this.rot = rot;
		this.vertices.add(dotPos);
	}
	
//	@Override
//	public void render(Graphics g) {
//		Vector3 v = this.vertices.get(0);
//		this.convertTransform();
//		Vector4 globalPos = this.transform.dotVecMat(v.toVec4());
//		g.setColor(Color.BLACK);
//		g.drawString("(" + Printer.printMat4(this.transform) , 10, 10);
//		g.drawString("(" + globalPos.x + " " + globalPos.y + " " + globalPos.z, 10, 30);
//		
//		Vector4 localPos = this.invtransform.dotVecMat(globalPos);
//		g.drawString("(" + localPos.x + " " + localPos.y + " " + localPos.z, 10, 60);
//	}
}
