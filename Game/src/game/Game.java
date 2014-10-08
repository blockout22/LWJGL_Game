package game;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.Mesh;
import engine.Shader;
import engine.Storage;
import engine.TexturedMesh;
import engine.Window;

public class Game {

	private int WIDTH = 800;
	private int HEIGHT = 600;
	private String TITLE = "Game";
	private double OPENGL_VERSION = 3.2;

//	private Mesh mesh;
	private TexturedMesh mesh1, mesh2;
	private Shader shader;
	
	private int t1, t2;

	public Game() {
		init();
	}

	public void init() {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		Window.setViewport();

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

	}

	public void gameLoop() {
		while (!Window.isCloseRequested()) {
			render();
			update();
		}

		close();
	}

	public void render() {
		Window.clearAll(1f, 0.4f, 0.1f, 1f);

		shader.useProgram();
		mesh1.draw();
		mesh2.draw();
	}

	public void update() {
		Window.update();
	}

	public static void close() {
		Storage.cleanup();
		Window.close();
	}
}
