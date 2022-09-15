package main.utils;

import java.awt.Color;
import java.awt.Graphics;

import main.objects.Camera;

public class FaceShader {
	public static Pixel[][] framebuffer = new Pixel[(int) Camera.screenSize.x][(int) Camera.screenSize.y];
	public static boolean bufferResizing = false;
	
	public static void preRenderFace(Vector3 v1, Vector3 v2, Vector3 v3, Color c) {
		if (bufferResizing) {
			return;
		}
		for (int i = (int) Math.min(Math.min(v1.x, v2.x), v3.x); i < Math.max(Math.max(v1.x, v2.x), v3.x); i++) {
			for (int j = (int) Math.min(Math.min(v1.y, v2.y), v3.y); j < Math.max(Math.max(v1.y, v2.y), v3.y); j++) {
				if (isPixelInTriangle(v1, v2, v3, new Vector2(i, j))) {
					if (i < 0 || i >= framebuffer.length || j < 0 || j >= framebuffer[0].length) {
						continue;
					}
					if (framebuffer[i][j] == null || interpolate(v1, v2, v3).z < framebuffer[i][j].z) {
						framebuffer[i][j] = new Pixel(c, interpolate(v1, v2, v3).z);
					}

				}
			}
		}
	}
	
	public static void renderFaces(Graphics g) {
		if (bufferResizing) {
			framebuffer = new Pixel[(int) Camera.screenSize.x][(int) Camera.screenSize.y];
			bufferResizing = false;
			return;
		}
		for (int i = 0; i < framebuffer.length; i++) {
			for (int j = 0; j < framebuffer.length; j++) {
				if (i < 0 || i >= framebuffer.length || j < 0 || j >= framebuffer[0].length) {
					continue;
				}
				if (framebuffer[i][j] != null) {
					g.setColor(framebuffer[i][j].c);
					g.fillRect(i, j, 1, 1);
					framebuffer[i][j] = null;
				}
			}
		}
		
	}
	
	public static Vector3 interpolate(Vector3 v1, Vector3 v2, Vector3 v3) {
		return new Vector3((v1.x+v2.x+v3.x)/3, (v1.y+v2.y+v3.y)/3, (v1.z+v2.z+v3.z)/3);
	}
	
	public static boolean isPixelInTriangle(Vector3 v1, Vector3 v2, Vector3 v3, Vector2 point) {
		float E01 = (point.x - v1.x)*(v2.y - v1.y) - (point.y - v1.y) * (v2.x - v1.x);
		float E02 = (point.x - v2.x)*(v3.y - v2.y) - (point.y - v2.y) * (v3.x - v2.x);
		float E03 = (point.x - v3.x)*(v1.y - v3.y) - (point.y - v3.y) * (v1.x - v3.x);
		return E01 > 0 && E02 > 0 && E03 > 0;
	}
	
	public static void resizeBuffer() {
		bufferResizing = true;
	}
}
