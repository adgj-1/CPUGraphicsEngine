package main.objects;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.materials.Material;
import main.utils.Vector3;

public class Model extends _3DObject {
	
	private Color c;
	
	public Model(String url, Vector3 offset, float scale, Color c) {
		this.c = c;
		this.pos = offset;
		try {
			BufferedReader in = new BufferedReader(new FileReader(url));
			while (true) {
				String ln = in.readLine();
				if (ln == null) {
					break;
				}
				
				String[] lnSplit = ln.split(" ");
				if (lnSplit[0].equals("v")) {
					
					this.vertices.add(new Vector3(Float.parseFloat(lnSplit[1]) * scale, Float.parseFloat(lnSplit[2]) * scale, Float.parseFloat(lnSplit[3]) * scale));
				}
				
				if (lnSplit[0].equals("f") && this.faces.size() < 2000) {
					this.addFace(new Vector3(Float.parseFloat(lnSplit[1].split("/")[0])-1, Float.parseFloat(lnSplit[2].split("/")[0])-1, Float.parseFloat(lnSplit[3].split("/")[0])-1));
				}
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(this);
	}
	
	public Model(String url, Vector3 offset, float scale) {
		
		this(url, offset, scale, null);
	}
	
	public void addFace(Vector3 face) {
		faces.add(face);
		if (c != null) {
			materials.add(new Material(c));
		} else {
			materials.add(new Material());
		}
	}

}
