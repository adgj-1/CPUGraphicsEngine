package main.utils;

import java.awt.Graphics;

import main.materials.Material;
import main.objects.Camera;

public class FragmentShader {
	public Pixel[][] framebuffer = new Pixel[resolutionX][resolutionY];
	public boolean bufferResizing = false;
	public static int minResolution = 600;
	public static int resolutionX = 600;
	public static int resolutionY = 600;
	public static float pixelWidth = 1;
	public static float pixelHeight = 1;
	public static boolean scaleToProportion = true;
	
	public void preRenderFace(Vector3 v1, Vector3 v2, Vector3 v3, Material c) {
		if (bufferResizing) {
			return;
		}
		for (int i = (int) Math.min(Math.min(v1.x, v2.x), v3.x); i < Math.max(Math.max(v1.x, v2.x), v3.x); i++) {
			for (int j = (int) Math.min(Math.min(v1.y, v2.y), v3.y); j < Math.max(Math.max(v1.y, v2.y), v3.y); j++) {
				if (isPixelInTriangle(v1, v2, v3, new Vector2(i, j))) {
					if (i < 0 || i >= framebuffer.length || j < 0 || j >= framebuffer[0].length) {
						continue;
					}

					Vector3 barycentric = calculateBarycentric(new Vector2(v1.x, v1.y), new Vector2(v2.x, v2.y), new Vector2(v3.x, v3.y), new Vector2(i, j));
					
					float pointZ = interpolateZBuffer(barycentric, v1.z, v2.z, v3.z);


					if (framebuffer[i][j] == null || pointZ < framebuffer[i][j].z) {
						Vector2[] tmap = c.getTextureMap();
						Vector2 rscloc = interpolateResourceMapping(barycentric, tmap[0].toVec3(v1.z), tmap[1].toVec3(v2.z), tmap[2].toVec3(v3.z), pointZ);
						
						//Depth rendering
//						framebuffer[i][j] = new Pixel(c.getColor(v1, v2, v3, new Vector2(i,j), pointZ), pointZ);
						
						//Texturing
//						framebuffer[i][j] = new Pixel(c.getColor(rscloc), pointZ);
						
						// Universal fragment shading
						framebuffer[i][j] = new Pixel(c.getColor(rscloc,v1, v2, v3, new Vector2(i,j), pointZ), pointZ);
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
	
	
	
	
	public static float tArea(Vector2 v1, Vector2 v2, Vector2 v3) {
		return Math.abs(v3.difference(v2).toVec3(0).cross(v1.difference(v2).toVec3(0)).z) / 2;
	}
	
	public static float dist(Vector2 v1, Vector2 v2) {
		return (v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y);
	}
	
	public void renderFaces(Graphics g) {
		
		if (bufferResizing) {
			if (scaleToProportion) {
				generateProportionalResolution();
			}
			pixelWidth = (float)(Math.round((Camera.screenSize.x / resolutionX)) * 10000)/10000;
			pixelHeight = (float)(Math.round((Camera.screenSize.y / resolutionY)) * 10000)/10000;
			framebuffer = new Pixel[resolutionX][resolutionY];
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
					g.fillRect(Math.round(i * pixelWidth), Math.round(j * pixelHeight), (int)Math.ceil(pixelWidth), (int)Math.ceil(pixelHeight));
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
	
	public void resizeBuffer() {
		bufferResizing = true;
	}
	
	public void generateProportionalResolution() {
		if (Camera.screenSize.x >= Camera.screenSize.y) {
			resolutionX = minResolution;
			resolutionY = (int) (minResolution * Camera.screenSize.y / Camera.screenSize.x);
		} else {
			resolutionY = minResolution;
			resolutionX = (int) (minResolution * Camera.screenSize.x / Camera.screenSize.y);
		}
	}
}
