package engine;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private float FIELD_OF_VIEW;
	private float ASPECT_RATIO;
	private float Z_NEAR;
	private float Z_FAR;

	public int DIR_RIGHT = 0;
	public int DIR_LEFT = 1;
	public int DIR_UP = 2;
	public int DIR_DOWN = 3;
	public int DIR_BACK = 4;
	public int DIR_FORWARD = 5;

	private Matrix4f projectionMatrix, modelMatrix, viewMatrix;
	private Vector3f modelPos, modelAngle, modelScale, cameraPos;

	private Vector3f AXIS_X, AXIS_Y, AXIS_Z;

	private float yaw, pitch;

	public Camera(float fov, float aspectRatio, float z_near, float z_far) {
		this.FIELD_OF_VIEW = fov;
		this.ASPECT_RATIO = aspectRatio;
		this.Z_NEAR = z_near;
		this.Z_FAR = z_far;

		setupMatrix();
		setup();
	}

	private void setupMatrix() {
		projectionMatrix = new Matrix4f();
		modelMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
	}

	private void setup() {
		// float y_scale = Maths.coTangent(Maths.degreesToRadians(FIELD_OF_VIEW
		// / 2f));
		float y_scale = 1f / (float) Math.tan(Math.toRadians(FIELD_OF_VIEW / 2f));
		float x_scale = y_scale / ASPECT_RATIO;
		float frustum_length = Z_FAR - Z_NEAR;

		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((Z_FAR + Z_NEAR) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * Z_NEAR * Z_FAR) / frustum_length);
		projectionMatrix.m33 = 0;

		modelPos = new Vector3f(0, 0, 0);
		modelAngle = new Vector3f(0, 0, 0);
		modelScale = new Vector3f(1, 1, 1);
		cameraPos = new Vector3f(0, 0, -1);

		AXIS_X = new Vector3f(1, 0, 0);
		AXIS_Y = new Vector3f(0, 1, 0);
		AXIS_Z = new Vector3f(0, 0, 1);
	}

	// public void move(int direction, float amount) {
	// if (direction == DIR_UP) {
	// modelPos.y -= amount;
	// }
	//
	// if (direction == DIR_DOWN) {
	// modelPos.y += amount;
	// }
	//
	// if (direction == DIR_LEFT) {
	// modelPos.x += amount;
	// }
	//
	// if (direction == DIR_RIGHT) {
	// modelPos.x -= amount;
	// }
	//
	// if(direction == DIR_BACK)
	// {
	// Vector3f.add(modelScale, new Vector3f(-amount, -amount, -amount),
	// modelScale);
	// }
	//
	// if(direction == DIR_FORWARD)
	// {
	// Vector3f.add(modelScale, new Vector3f(amount, amount, amount),
	// modelScale);
	// }
	// }
	// TODO make 3d rotation
	// public void rotate(float amount1, float amount2)
	// {
	// float x_ = cameraPos.y * modelScale.getZ() - cameraPos.z *
	// modelScale.getZ();
	// float y_ = cameraPos.z * modelScale.getX() - cameraPos.x *
	// modelScale.getZ();
	// float z_ = cameraPos.x * modelScale.getY() - cameraPos.y *
	// modelScale.getX();
	//
	// Vector3f c = new Vector3f(x_, y_, z_);
	//
	// float sin = (float)Math.sin(Math.sin(Math.toRadians(5 / 2)));
	// float cos = (float)Math.cos(Math.sin(Math.toRadians(5 / 2)));
	//
	// float rX = c.getX() * sin;
	// float rY = c.getY() * sin;
	// float rZ = c.getZ() * sin;
	// float rW = cos;
	//
	// Quaternion w = new Quaternion(rX, rY, rZ, rW);
	//
	// float x = w.getX();
	// float y = w.getY();
	// float z = w.getZ();
	//
	// cameraPos.x += x;
	// cameraPos.y += y;
	// cameraPos.z += z;
	// }

	public void RotateXY(float amount1, float amount2) {
		// modelAngle.y += amount1 / 20;
		// modelAngle.x -= amount2 / 20;
		// yaw += amount1 / 20;
		// pitch -= amount2 / 20;
	}

	public void rotate(float amount1, float amount2) {
		modelAngle.y += amount1;
		modelAngle.x -= amount2;
	}

	public void move(float amount, float dir) {
		cameraPos.z += amount * Math.sin(Math.toRadians(modelAngle.y + 90 * dir));
		cameraPos.x += amount * Math.cos(Math.toRadians(modelAngle.y + 90 * dir));
	}

	public void useView() {
		viewMatrix = new Matrix4f();
		modelMatrix = new Matrix4f();

		Matrix4f.translate(cameraPos, viewMatrix, viewMatrix);
		Matrix4f.scale(modelScale, modelMatrix, modelMatrix);
		Matrix4f.translate(modelPos, modelMatrix, modelMatrix);
		Matrix4f.rotate(Maths.degreesToRadians(modelAngle.x), AXIS_X, modelMatrix, modelMatrix);
		Matrix4f.rotate(Maths.degreesToRadians(modelAngle.y), AXIS_Y, modelMatrix, modelMatrix);
		Matrix4f.rotate(Maths.degreesToRadians(modelAngle.z), AXIS_Z, modelMatrix, modelMatrix);
	}

	public void setProjectionMatrix(Matrix4f projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}

	public void setModelMatrix(Matrix4f modelMatrix) {
		this.modelMatrix = modelMatrix;
	}

	public void setViewMatrix(Matrix4f viewMatrix) {
		this.viewMatrix = viewMatrix;
	}

	public float getFIELD_OF_VIEW() {
		return FIELD_OF_VIEW;
	}

	public void setFIELD_OF_VIEW(float fIELD_OF_VIEW) {
		FIELD_OF_VIEW = fIELD_OF_VIEW;
	}

	public float getASPECT_RATIO() {
		return ASPECT_RATIO;
	}

	public void setASPECT_RATIO(float aSPECT_RATIO) {
		ASPECT_RATIO = aSPECT_RATIO;
	}

	public float getZ_NEAR() {
		return Z_NEAR;
	}

	public void setZ_NEAR(float z_NEAR) {
		Z_NEAR = z_NEAR;
	}

	public float getZ_FAR() {
		return Z_FAR;
	}

	public void setZ_FAR(float z_FAR) {
		Z_FAR = z_FAR;
	}

	public Vector3f getModelPos() {
		return modelPos;
	}

	public Vector3f getModelAngle() {
		return modelAngle;
	}

	public Vector3f getModelScale() {
		return modelScale;
	}

	public Vector3f getCameraPos() {
		return cameraPos;
	}

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	public Matrix4f getModelMatrix() {
		return modelMatrix;
	}

}
