package engine;

import game.Game;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Window {
	
	private static String defaultTexutre = "src/default.png";

	/**
	 * create window size of width & height
	 * 
	 * @param width
	 * @param height
	 */
	public static void createWindow(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		loadDefaultTexture(defaultTexutre);
	}

	/**
	 * create window size of width & height & set title
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public static void createWindow(int width, int height, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		loadDefaultTexture(defaultTexutre);
	}

	/**
	 * create window size of width & height & set version to use
	 * 
	 * @param width
	 * @param height
	 * @param version
	 */
	public static void createWindow(int width, int height, double version) {
		String[] v = String.valueOf(version).split("\\.");
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAtrributes = new ContextAttribs(Integer.valueOf(v[0]), Integer.valueOf(v[1])).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(pixelFormat, contextAtrributes);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		loadDefaultTexture(defaultTexutre);
	}

	/**
	 * create window size of width & height, set title & set version to use
	 * 
	 * @param width
	 * @param height
	 * @param title
	 * @param version
	 */
	public static void createWindow(int width, int height, String title, double version) {
		String[] v = String.valueOf(version).split("\\.");
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAtrributes = new ContextAttribs(Integer.valueOf(v[0]), Integer.valueOf(v[1])).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create(pixelFormat, contextAtrributes);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		loadDefaultTexture(defaultTexutre);
	}
	
	/**
	 * sets the fps cap
	 * @param fps
	 */
	public static void sync(int fps)
	{
		Display.sync(fps);
	}
	
	/**
	 * sets whether the window can be resized
	 * @param resizable
	 */
	public static void setResizable(boolean resizable)
	{
		Display.setResizable(resizable);
	}
	
	/**
	 * returns true if the window was resized
	 * @return
	 */
	public static boolean wasResized()
	{
		return Display.wasResized();
	}
	
	public static void setViewport()
	{
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public static void setViewport(int x, int y, int width, int height)
	{
		GL11.glViewport(x, y, width, height);
	}

	/**
	 * check if the close button is clicked
	 * 
	 * @return
	 */
	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}

	/**
	 * clear color buffer bit
	 */
	public static void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * clear color buffer bit.
	 *  if depth = true also clear depth buffer
	 * @param depth
	 */
	public static void clear(boolean depth) {
		if (depth) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		}else
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		}
	}
	
	/**
	 * set default color of display
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void clearColor(float red, float green, float blue, float alpha)
	{
		GL11.glClearColor(red, green, blue, alpha);
	}
	
	/**
	 * clear color/depth buffer & set default color of display
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void clearAll(float red, float green, float blue, float alpha)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(red, green, blue, alpha);
	}

	/**
	 * update the display
	 */
	public static void update() {
		Display.update();
	}

	/**
	 * closes & destroys the display
	 */
	public static void close() {
		Display.destroy();
		System.exit(0);
	}
	
	/**
	 * Loads a default texture so the TexturedMesh has a texture to load if non is already specified 
	 * @param fileName
	 */
	private static void loadDefaultTexture(String fileName)
	{
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to find texture: " + fileName);
			Game.close();
			System.exit(1);
		}

		int textureID = texture.getTextureID();
		Storage.addTexture(textureID);
	}
	
	public static int getWidth()
	{
		return Display.getWidth();
	}
	
	public static int getHeight()
	{
		return Display.getHeight();
	}
}
