package engine;

import game.Game;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TexturedMesh {

	private int vaoID;
	private int vboID;
	private int vboiID;
	private int texID;
	
	private int indicesSize;

	public TexturedMesh() {
		vaoID = GL30.glGenVertexArrays();

		vboID = GL15.glGenBuffers();
		vboiID = GL15.glGenBuffers();
		texID = GL15.glGenBuffers();
		
		Storage.addVAO(vaoID);
		Storage.addVBO(vboID);
		Storage.addVBO(vboiID);
		Storage.addVBO(texID);
	}
	
	public void add(Vector3f[] vertices, Vector2f[] texCoords, int[] indices)
	{
		indicesSize = indices.length;
		GL30.glBindVertexArray(vaoID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, Util.createFlippedBuffer(texCoords), GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		GL30.glBindVertexArray(0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiID);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public int setTexture(String fileName)
	{
		Texture texture = null;
		try{
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(fileName));
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to find texture: " + fileName);
			Game.close();
			System.exit(1);
		}
		
		int textureID = texture.getTextureID();
		Storage.addTexture(textureID);
		return textureID;
	}
	
	public void draw(int textureID)
	{
		GL30.glBindVertexArray(vaoID);
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiID);
		GL11.glDrawElements(GL11.GL_TRIANGLES, indicesSize, GL11.GL_UNSIGNED_INT, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		
		GL30.glBindVertexArray(0);
	}

}
