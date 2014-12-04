package game;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.Camera;
import engine.OBJLoader;
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

	private TexturedMesh mesh1;
	private TexturedMesh model;
	private Shader shader;

	private int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation;
//	private int transformMatrixLocation;
//	private Transform transform;

	private static Camera camera;

	public Game() {
		init();
	}

	public void init() {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		// Window.setResizable(true);
		Window.setViewport();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		Time.getDelta();
		LAST_FPS = Time.getTime();

		initGL();
		gameLoop();
	}

	public void initGL() {
		Vector3f[] vertices = new Vector3f[] {
				//
				new Vector3f(-1f, -1f, -2f), // Left top ID: 0
				new Vector3f(0f, 1f, -2f), // Left bottom ID: 1
				new Vector3f(1f, -1f, -2f), // Right bottom ID: 2
				new Vector3f(0f, -1f, -2f) // Right left ID: 3

		};

//		Vector3f[] vertices2 = new Vector3f[] {
//				//
//				new Vector3f(-0.5f, 0.5f, 0f), //
//				new Vector3f(-0.5f, -0.5f, 0f), //
//				new Vector3f(0.5f, -0.5f, 0f), //
//				new Vector3f(0.5f, 0.5f, 0f) //
//		};

		Vector2f[] texCoords = new Vector2f[] {
				//
				new Vector2f(0, 0), //
				new Vector2f(0, 1), //
				new Vector2f(1, 1), //
				new Vector2f(1, 0) //
		};

		int[] indices = new int[] {
				//
				0, 1, 3, //
				3, 1, 2, //
				2, 1, 0, //
				0, 2, 3 //
		};

		input = new Input();

		mesh1 = new TexturedMesh();
		mesh1.setTexture("src/image0.png");
		mesh1.add(vertices, texCoords, indices);
		
		model = OBJLoader.loadTextured("src/stall.obj", "src/stall.png");

//		mesh2 = new TexturedMesh();
//		mesh2.setTexture("src/image1.png");
//		mesh2.add(vertices2, texCoords, indices);
		
		camera = new Camera(70f, Window.getWidth() / Window.getHeight(), 0.1f, 1000f);

		shader = new Shader();
		shader.loadShaders("src/vertex.glsl", "src/fragment.glsl");
		shader.createProgram();
		shader.bindAttribLocation(0, "position");
		shader.bindAttribLocation(1, "tex_Coords");
		shader.linkAndValidate();

		projectionMatrixLocation = shader.getUniformLocation("projectionMatrix");
		viewMatrixLocation = shader.getUniformLocation("viewMatrix");
		modelMatrixLocation = shader.getUniformLocation("modelMatrix");
		
//		transformMatrixLocation = shader.getUniformLocation("transform");

	}

	public void gameLoop() {
		while (!Window.isCloseRequested()) {
			int delta = Time.getDelta();
			if (Time.getTime() - LAST_FPS > 1000) {
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
		shader.getUniformMatrix4f(projectionMatrixLocation, camera.getProjectionMatrix());
		shader.getUniformMatrix4f(viewMatrixLocation, camera.getViewMatrix());
		shader.getUniformMatrix4f(modelMatrixLocation, camera.getModelMatrix());
//		shader.getUniformMatrix4f(transformMatrixLocation, transform.getProjectedTransformation());
		mesh1.draw();
		model.draw();
	}

	public void update() {
		input.update();
		camera.useView();
		Window.update();
	}

	public static Camera getCamera() {
		return camera;
	}

	public static void close() {
		Storage.cleanup();
		Window.close();
	}
}
