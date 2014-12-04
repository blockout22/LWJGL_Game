package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class OBJLoader {

	public static Mesh load(String fileName) {
		ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String line;
			while((line = br.readLine()) != null)
			{
				String[] spaceSplitter = line.split(" ");
				
				if(line.startsWith("v "))
				{
					vertices.add(new Vector3f(Float.valueOf(spaceSplitter[1]), Float.valueOf(spaceSplitter[2]), Float.valueOf(spaceSplitter[3])));
				}
				
				if(line.startsWith("f "))
				{
					indices.add(Integer.valueOf(spaceSplitter[1]));
					indices.add(Integer.valueOf(spaceSplitter[2]));
					indices.add(Integer.valueOf(spaceSplitter[3]));
				}
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Vector3f[] vertexData = new Vector3f[vertices.size()];
		vertices.toArray(vertexData);
		
		Integer[] indicesData = new Integer[indices.size()];
		indices.toArray(indicesData);

		Mesh mesh = new Mesh();
		mesh.add(vertexData, toIntArray(indicesData));

		return mesh;
	}

	/**
	 * 
	 * @param fileName
	 *            = obj file
	 * @param textureName
	 *            = texture file
	 * @return
	 */
	public static TexturedMesh loadTextured(String fileName, String textureName) {
		
		ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
		ArrayList<Vector2f> texCoords = new ArrayList<Vector2f>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String line;
			while((line = br.readLine()) != null)
			{
				String[] spaceSplitter = line.split(" ");
				
				if(line.startsWith("v "))
				{
					vertices.add(new Vector3f(Float.valueOf(spaceSplitter[1]), Float.valueOf(spaceSplitter[2]), Float.valueOf(spaceSplitter[3])));
				}
				
				if(line.startsWith("vt "))
				{
					texCoords.add(new Vector2f(Float.valueOf(spaceSplitter[1]), Float.valueOf(spaceSplitter[2])));
				}
				
				if(line.startsWith("f "))
				{
					indices.add(Integer.valueOf(spaceSplitter[1].split("/")[0]) - 1);
					indices.add(Integer.valueOf(spaceSplitter[2].split("/")[0]) - 1);
					indices.add(Integer.valueOf(spaceSplitter[3].split("/")[0]) - 1);
				}
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Vector3f[] vertexData = new Vector3f[vertices.size()];
		vertices.toArray(vertexData);
		
		Vector2f[] textureData = new Vector2f[texCoords.size()];
		texCoords.toArray(textureData);

		Integer[] indicesData = new Integer[indices.size()];
		indices.toArray(indicesData);

		TexturedMesh mesh = new TexturedMesh();
		mesh.add(vertexData, textureData, toIntArray(indicesData));
		mesh.setTexture(textureName);

		return mesh;
	}
	
	private static int[] toIntArray(Integer[] data) {
		int[] result = new int[data.length];
		
		for(int i = 0; i< data.length; i++)
		{
			result[i] = data[i].intValue();
		}
		
		return result;
	}

}
