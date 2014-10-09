package game;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.Camera;
import engine.Shader;
import engine.Storage;
import engine.TexturedMesh;
import engine.Time;
import engine.Window;

public class Game {

	private int WIDTH = 800;
	private int HEIGHT = 600;
	private String TITLE = "Game";
	private int FPS_CAP = 60;
	
	private long LAST_FPS;
	private int FPS;
	
	private Input input;

	private TexturedMesh mesh1, mesh2;
	private Shader shader;
	
	private int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation;
	
	private static Camera camera;

	public Game() {
		init();
	}

	public void init() {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		Window.setResizable(true);
		Window.setViewport();
		
		Time.getDelta();
		LAST_FPS = Time.getTime();

		initGL();
		gameLoop();
	}

	public void initGL() {
		Vector3f[] vertices = new Vector3f[] {
				//
				new Vector3f(-1f, -0.2f, -0), // Left top ID: 0
				new Vector3f(-1f, -1f, 0), // Left bottom ID: 1
				new Vector3f(-0.2f, -1f, 0), // Right bottom ID: 2
				new Vector3f(-0.2f, -0.2f, 0) // Right left ID: 3

		};

		Vector3f[] vertices2 = new Vector3f[] {
				//
				new Vector3f(-0.5f, 0.5f, 0f), //
				new Vector3f(-0.5f, -0.5f, 0f), //
				new Vector3f(0.5f, -0.5f, 0f), //
				new Vector3f(0.5f, 0.5f, 0f) //
		};

		Vector2f[] texCoords = new Vector2f[] {
				//
				new Vector2f(0, 0), //
				new Vector2f(0, 1), //
				new Vector2f(1, 1), //
				new Vector2f(1, 0) //
		};

		int[] indices = new int[] {
				//
				0, 1, 2, //
				2, 3, 0, //
		};
		
		input = new Input();

		mesh1 = new TexturedMesh();
		mesh1.setTexture("src/image0.png");
		mesh1.add(vertices, texCoords, indices);
		
		mesh2 = new TexturedMesh();
		mesh2.setTexture("src/image1.png");
		mesh2.add(vertices2, texCoords, indices);

		shader = new Shader();
		shader.loadShaders("src/vertex.glsl", "src/fragment.glsl");
		shader.createProgram();
		shader.bindAttribLocation(0, "position");
		shader.bindAttribLocation(1, "tex_Coords");
		shader.linkAndValidate();
		
		projectionMatrixLocation = shader.getUniformLocation("projectionMatrix");
		viewMatrixLocation = shader.getUniformLocation("viewMatrix");
		modelMatrixLocation = shader.getUniformLocation("modelMatrix");
		
		camera = new Camera(60f, Window.getWidth() / Window.getHeight(), 0.1f, 100f);
		camera.setup();

	}

	public void gameLoop() {
		while (!Window.isCloseRequested()) {
			int delta = Time.getDelta();
			if(Time.getTime() - LAST_FPS > 1000)
			{
				System.out.println(FPS);
				FPS = 0;
				LAST_FPS += 1000;
			}
			
			FPS++;
			render();
			update();
			
			Window.sync(FPS_CAP);
		}

		close();
	}

	public void render() {
		Window.clearAll(1f, 0.4f, 0.1f, 1f);

		shader.useProgram();
		shader.getUniformMatrix4f(camera.getProjectionMatrix(), projectionMatrixLocation);
		shader.getUniformMatrix4f(camera.getViewMatrix(), viewMatrixLocation);
		shader.getUniformMatrix4f(camera.getModelMatrix(), modelMatrixLocation);
		mesh1.draw();
		mesh2.draw();
	}

	public void update() {
		input.update();
		camera.useView();
		Window.update();
	}
	
	public static Camera getCamera()
	{
		return camera;
	}

	public static void close() {
		Storage.cleanup();
		Window.close();
	}
}
