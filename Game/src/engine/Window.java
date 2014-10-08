package engine;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Window {

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
	}
}
