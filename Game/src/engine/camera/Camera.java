package engine.camera;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.Time;

public class Camera {
	
	private static final Vector yAxis = new Vector(0, 1, 0);
	
	private Vector pos;
	private Vector forward;
	private Vector up;
	
	public Camera()
	{
		this(new Vector(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0));
	}
	
	public Camera(Vector pos, Vector forward, Vector up) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;
		
		up.normalize();
		forward.normalize();
	}
	
	public void init()
	{
//		float moveAmt = (float)(10 * Time.getDelta());
//		float rotAmt = (float)(100 * Time.getDelta());
//		
//		if(Keyboard.isKeyDown(Keyboard.KEY_W))
//		{
//			move(getForward(), moveAmt);
//		}
//		
//		if(Keyboard.isKeyDown(Keyboard.KEY_S))
//		{
//			move(getForward(), -moveAmt);
//		}
//		
//		if(Keyboard.isKeyDown(Keyboard.KEY_A))
//		{
//			move(getRight(), moveAmt);
//		}
//		
//		if(Keyboard.isKeyDown(Keyboard.KEY_D))
//		{
//			move(getLeft(), moveAmt);
//		}
//		
//		if(Mouse.isButtonDown(2))
//		{
//			rotateY(Mouse.getDX());
//			rotateX(-Mouse.getDY());
//		}
	}
	
	public void move(Vector dir, float amt)
	{
		pos = pos.add(dir.mul(amt));
	}
	
	public void rotateY(float angle)
	{
		Vector hAxis = yAxis.cross(forward);
		hAxis.normalize();
		
		forward.rotate(angle, yAxis);
		forward.normalize();
		
		up = forward.cross(hAxis);
		up.normalize();
	}
	
	public void rotateX(float angle)
	{
		Vector hAxis = yAxis.cross(forward);
		hAxis.normalize();
		
		forward.rotate(angle, hAxis);
		forward.normalize();
		
		up = forward.cross(hAxis);
		up.normalize();
	}
	
	public Vector getLeft()
	{
		Vector left = up.cross(forward);
		left.normalize();
		return left;
	}
	
	public Vector getRight()
	{
		Vector right = forward.cross(up);
		right.normalize();
		return right;
	}

	public Vector getPos() {
		return pos;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}

	public Vector getForward() {
		return forward;
	}

	public void setForward(Vector forward) {
		this.forward = forward;
	}

	public Vector getUp() {
		return up;
	}

	public void setUp(Vector up) {
		this.up = up;
	}
}
