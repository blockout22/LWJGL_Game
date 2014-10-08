package game;

import engine.Storage;
import engine.Window;

public class Game{
	
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private String TITLE = "Game";
	private double OPENGL_VERSION = 3.2;
	
	public Game()
	{
		init();
	}

	public void init() {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		Window.setViewport();
		
		initGL();
		gameLoop();
	}

	public void initGL() {
	}

	public void gameLoop() {
		while(!Window.isCloseRequested())
		{
			render();
			update();
		}
		
		close();
	}

	public void render() {
		Window.clearAll(1f, 0.4f, 0.1f, 1f);
	}

	public void update() {
		Window.update();
	}

	public static void close() {
		Storage.cleanup();
		Window.close();
	}
}
