package game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.Time;

public class Input {
	
	float moveAmt = (float)(10 * Time.getDelta());
	float rotAmt = (float)(100 * Time.getDelta());
	
	public void update()
	{
		keyboard();
		mouse();
	}

	private void keyboard() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
//			Game.getCamera().move(Game.getCamera().getForward(), moveAmt);
			Game.getCamera().move(0.1f, 1);
		}
//		
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			Game.getCamera().move(0.1f, 0);
//			Game.getCamera().move(Game.getCamera().getRight(), moveAmt);
		}
//		
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
//			Game.getCamera().move(Game.getCamera().getForward(), -moveAmt);
			Game.getCamera().move(-0.1f, 1);
		}
//		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			Game.getCamera().move(-0.1f, 0);
//			Game.getCamera().move(Game.getCamera().getLeft(), moveAmt);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_R))
		{
			
		}
	}

	private void mouse() {
		if(Mouse.isButtonDown(2))
		{
			Game.getCamera().rotate(Mouse.getDX(), Mouse.getDY());
		}
	}

}
