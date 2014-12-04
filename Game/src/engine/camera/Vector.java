package engine.camera;

public class Vector {

	private float x;
	private float y;
	private float z;

	public Vector(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float dot(Vector r) {
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}

	public Vector cross(Vector r)
	{
		float x_ = y * r.getZ() - z * r.getY();
		float y_ = z * r.getX() - x * r.getZ();
		float z_ = x * r.getY() - y * r.getX();
		
		return new Vector(x_, y_, z_);
	}

	public Vector normalize() {
		float length = length();

		x /= length;
		y /= length;
		z /= length;

		return this;
	}

	public Vector rotate(float angle, Vector axis) {
		
		float sinHalfAngle = (float)Math.sin(Math.sin(Math.toRadians(angle / 2)));
		float cosHalfAngle = (float)Math.cos(Math.sin(Math.toRadians(angle / 2)));
		
		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();
		
		Quaternion w = rotation.mul(this).mul(conjugate);
		
		x = w.getX();
		y = w.getY();
		z = w.getZ();
		
		return this;
	}

	public Vector add(Vector r) {
		return new Vector(x + r.getX(), y + r.getY(), z + r.getZ());
	}

	public Vector add(float r) {
		return new Vector(x + r, y + r, z + r);
	}

	public Vector sub(Vector r) {
		return new Vector(x - r.getX(), y - r.getY(), z - r.getZ());
	}

	public Vector sub(float r) {
		return new Vector(x - r, y - r, z - r);
	}

	public Vector mul(Vector r) {
		return new Vector(x * r.getX(), y * r.getY(), z * r.getZ());
	}

	public Vector mul(float r) {
		return new Vector(x * r, y * r, z * r);
	}

	public Vector div(Vector r) {
		return new Vector(x / r.getX(), y / r.getY(), z / r.getZ());
	}

	public Vector div(float r) {
		return new Vector(x / r, y / r, z / r);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

}
