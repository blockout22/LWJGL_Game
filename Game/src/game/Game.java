package game;

import engine.GameSkeleton;
import engine.Window;

public class Game extends GameSkeleton{
	
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private String TITLE = "Game";
	private double OPENGL_VERSION = 3.2;
	
	public Game()
	{
		init();
	}

	@Override
	public void init() {
		Window.createWindow(WIDTH, HEIGHT, TITLE, OPENGL_VERSION);
		
		initGL();
		gameLoop();
	}

	@Override
	public void initGL() {
	}

	@Override
	public void gameLoop() {
		while(!Window.isCloseRequested())
		{
			render();
			update();
		}
		
		close();
	}

	@Override
	public void render() {
	}

	@Override
	public void update() {
		Window.update();
	}

	@Override
	public void close() {
		Window.close();
	}
}
