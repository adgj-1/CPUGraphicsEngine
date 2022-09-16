package main.utils;

import java.awt.Graphics;

import main.materials.Material;
import main.objects.Camera;

public class FaceShader {
	public static Pixel[][] framebuffer = new Pixel[(int) Camera.screenSize.x][(int) Camera.screenSize.y];
	public static boolean bufferResizing = false;
	
	public static void preRenderFace(Vector3 v1, Vector3 v2, Vector3 v3, Material c) {
		if (bufferResizing) {
			return;
		}
		for (int i = (int) Math.min(Math.min(v1.x, v2.x), v3.x); i < Math.max(Math.max(v1.x, v2.x), v3.x); i++) {
			for (int j = (int) Math.min(Math.min(v1.y, v2.y), v3.y); j < Math.max(Math.max(v1.y, v2.y), v3.y); j++) {
				if (isPixelInTriangle(v1, v2, v3, new Vector2(i, j))) {
					if (i < 0 || i >= framebuffer.length || j < 0 || j >= framebuffer[0].length) {
						continue;
					}
//					if (framebuffer[i][j] == null || interpolate(v1, v2, v3).z < framebuffer[i][j].z) {
//						framebuffer[i][j] = new Pixel(c.getColor(v1, v2, v3, new Vector2(i,j)), interpolate(v1, v2, v3).z);
//					}
					
					Vector3 barycentric = calculateBarycentric(new Vector2(v1.x, v1.y), new Vector2(v2.x, v2.y), new Vector2(v3.x, v3.y), new Vector2(i, j));
					
					float pointZ = interpolateZBuffer(barycentric, v1.z, v2.z, v3.z);
//					float pointZ = interpolateZBuffer(v1, v2, v3, new Vector2(i, j));

					if (framebuffer[i][j] == null || pointZ < framebuffer[i][j].z) {
						Vector2[] tmap = c.getTextureMap();
						Vector2 rscloc = interpolateResourceMapping(barycentric, tmap[0].toVec3(v1.z), tmap[1].toVec3(v2.z), tmap[2].toVec3(v3.z), pointZ);
//						Vector2 rscloc = interpolateResourceMapping(barycentric, new Vector3(1, 0, v1.z), new Vector3(0, 0, v2.z), new Vector3(0, 1, v3.z), pointZ);
						
						//Deprecated code
//						Vector2 rscloc= interpolateResourceMapping(new Vector4(v1.x, v1.y, 1, 0), new Vector4(v2.x, v2.y, 0, 0), new Vector4(v3.x, v3.y, 0, 1), new Vector2(i,j), v1.z, v2.z, v3.z, pointZ);
						
						//Depth rendering
//						framebuffer[i][j] = new Pixel(c.getColor(v1, v2, v3, new Vector2(i,j), pointZ), pointZ);
						
						//Texturing
						framebuffer[i][j] = new Pixel(c.getColor(rscloc), pointZ);
					}

				}
			}
		}
	}
	
	public static float interpolateZBuffer(Vector3 b, float z1, float z2, float z3) {
		float invZ = b.x/z1 + b.y/z2 + b.z/z3;
		return 1/invZ;
	}
	
	
	public static Vector2 interpolateResourceMapping(Vector3 b, Vector3 v1, Vector3 v2, Vector3 v3, float zp) {
		
		float x = b.x * v1.x / v1.z + b.y * v2.x / v2.z + b.z * v3.x / v3.z;
		float y = b.x * v1.y / v1.z + b.y * v2.y / v2.z + b.z * v3.y / v3.z;
		
		x *= zp;
		y *= zp;
		return new Vector2(x, y);
	}

	
	public static Vector3 calculateBarycentric(Vector2 v1_, Vector2 v2_, Vector2 v3_, Vector2 pos) {
		float b1 = tArea(v2_, v3_, pos) / tArea(v1_, v2_, v3_);
		float b2 = tArea(v3_, v1_, pos) / tArea(v1_, v2_, v3_);
		float b3 = tArea(v1_, v2_, pos) / tArea(v1_, v2_, v3_);
		return new Vector3(b1, b2, b3);
	}
	
	
	public static float interpolateZBuffer(Vector3 v1, Vector3 v2, Vector3 v3, Vector2 pos) {
		Vector2 v1_ = new Vector2(v1.x, v1.y);
		Vector2 v2_ = new Vector2(v2.x, v2.y);
		Vector2 v3_ = new Vector2(v3.x, v3.y);
		float b1 = tArea(v2_, v3_, pos) / tArea(v1_, v2_, v3_);
		float b2 = tArea(v3_, v1_, pos) / tArea(v1_, v2_, v3_);
		float b3 = tArea(v1_, v2_, pos) / tArea(v1_, v2_, v3_);
		
		float invZ = b1/v1.z + b2/v2.z + b3/v3.z;
		return 1/invZ;
	}
	
	
	public static Vector2 interpolateResourceMapping(Vector4 v1, Vector4 v2, Vector4 v3, Vector2 pos, float z1, float z2, float z3, float zp) {
		Vector2 v1_ = new Vector2(v1.x, v1.y);
		Vector2 v2_ = new Vector2(v2.x, v2.y);
		Vector2 v3_ = new Vector2(v3.x, v3.y);
		float b1 = tArea(v2_, v3_, pos) / tArea(v1_, v2_, v3_);
		float b2 = tArea(v3_, v1_, pos) / tArea(v1_, v2_, v3_);
		float b3 = tArea(v1_, v2_, pos) / tArea(v1_, v2_, v3_);
		
		float x = b1 * v1.z / z1 + b2 * v2.z / z2 + b3 * v3.z / z3;
		float y = b1 * v1.h / z1 + b2 * v2.h / z2 + b3 * v3.h / z3;
		
		
		x *= zp;
		y *= zp;
		return new Vector2(x, y);
	}
	
	public static float tArea(Vector2 v1, Vector2 v2, Vector2 v3) {
		return Math.abs(v3.difference(v2).toVec3(0).cross(v1.difference(v2).toVec3(0)).z) / 2;
	}
	
	public static float dist(Vector2 v1, Vector2 v2) {
		return (v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y);
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
