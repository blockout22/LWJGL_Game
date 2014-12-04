package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

//import engine.camera.Matrix4f;
import org.lwjgl.util.vector.Matrix4f;

public class Shader {
	
	private int programID;
	private int vertexID;
	private int fragmentID;
	
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	//TODO
	private HashMap<String, Integer> uniforms = new HashMap<String, Integer>();
	
	/**
	 * call first
	 * @param vertexFile
	 * @param fragmentFile
	 */
	public void loadShaders(String vertexFile, String fragmentFile)
	{
		vertexID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
	}
	
	/**
	 * call second
	 */
	public void createProgram()
	{
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
	}
	
	/**
	 * call third
	 * @param attrib
	 * @param name
	 */
	public void bindAttribLocation(int attrib, String name)
	{
		GL20.glBindAttribLocation(programID, attrib, name);
	}
	
	/**
	 * call fourth (last);
	 */
	public void linkAndValidate()
	{
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
	}
	
	public void useProgram()
	{
		GL20.glUseProgram(programID);
	}
	
	public void stopProgram()
	{
		GL20.glUseProgram(0);
	}
	
	public int getUniformLocation(String name)
	{
		return GL20.glGetUniformLocation(programID, name);
	}
	
	//TODO
	public void addUniform(String uniform)
	{
		int uniformLocation = GL20.glGetUniformLocation(programID, uniform);
		
		if(uniformLocation == -1)
		{
			System.exit(1);
		}
		
		uniforms.put(uniform, uniformLocation);
	}
	
	//TODO
	public void setUniform(String uniformName, Matrix4f value)
	{
		GL20.glUniformMatrix4(uniforms.get(uniformName), true, createFlippedBuffer(value));
	}
	
	//TODO
	private FloatBuffer createFlippedBuffer(Matrix4f value) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
//				buffer.put(value.get(i, j));
			}
		}
		
		buffer.flip();
		
		return buffer;
	}

	public void getUniformMatrix4f(int value, Matrix4f matrix)
	{
		matrix.store(matrixBuffer);
		
//		for(int i = 0; i < 4; i++)
//		{
//			for(int j =0; j < 4; j++)
//			{
//				matrixBuffer.put(matrix.get(i, j));
//			}
//		}
		matrixBuffer.flip();
		GL20.glUniformMatrix4(value, false, matrixBuffer);
	}
	
	private int loadShader(String fileName, int type)
	{
		StringBuilder shaderSource = new StringBuilder();
		int shaderID = 0;
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String line;
			while ((line = br.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}

			br.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
		{
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.exit(-1);
		}

		return shaderID;
	}

}
