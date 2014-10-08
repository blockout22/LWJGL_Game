package engine;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
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
	
	/**
	 * check if the close button is clicked
	 * @return
	 */
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	/**
	 * update the display
	 */
	public static void update()
	{
		Display.update();
	}
	
	/**
	 * closes & destroys the display
	 */
	public static void close()
	{
		Display.destroy();
	}
}
