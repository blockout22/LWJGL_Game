package engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Util {

	public static IntBuffer createFlippedBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();

		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();

		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(Vector3f[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length * 3);

		for (int i = 0; i < data.length; i++) {
			buffer.put(data[i].getX());
			buffer.put(data[i].getY());
			buffer.put(data[i].getZ());
		}

		buffer.flip();

		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(Vector2f[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length * 2);

		for (int i = 0; i < data.length; i++) {
			buffer.put(data[i].getX());
			buffer.put(data[i].getY());
		}

		buffer.flip();

		return buffer;
	}

}
