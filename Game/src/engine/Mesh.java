package engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

public class Mesh {
	
	private int vaoID;
	private int vboID;
	private int vboiID;
	
	private int indicesSize;
	
	public Mesh()
	{
		vaoID = GL30.glGenVertexArrays();
		
		vboID = GL15.glGenBuffers();
		vboiID = GL15.glGenBuffers();
		
		Storage.addVAO(vaoID);
		Storage.addVBO(vboID);
		Storage.addVBO(vboiID);
	}
	
	public void add(Vector3f[] vertices, int[] indices)
	{
		indicesSize = indices.length;
		GL30.glBindVertexArray(vaoID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		GL30.glBindVertexArray(0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiID);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void draw()
	{
		GL30.glBindVertexArray(vaoID);
		
		GL20.glEnableVertexAttribArray(0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiID);
		GL11.glDrawElements(GL11.GL_TRIANGLES, indicesSize, GL11.GL_UNSIGNED_INT, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL20.glDisableVertexAttribArray(0);
		
		GL30.glBindVertexArray(0);
	}
}
