package main.materials;

public class MaterialEnumerator {

	// TODO allow custom parameters
	public static Material getNewMaterial(int id) {
		switch (id) {
		
		case 1:
		{
			return new TexturedMaterial();
		}
		
		case 2:
		{
			return new DepthMaterial();
		}
		
		default:
		{
			return new Material();
		}
		}
	}
}
