package engine;

import java.io.BufferedReader;
import java.io.FileReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {
	
	private int programID;
	private int vertexID;
	private int fragmentID;
	
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
