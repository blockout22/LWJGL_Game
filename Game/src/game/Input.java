package game;

import org.lwjgl.input.Keyboard;

public class Input {
	
	public void update()
	{
		keyboard();
		mouse();
	}

	private void keyboard() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			Game.getCamera().move(Game.getCamera().DIR_UP, 0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			Game.getCamera().move(Game.getCamera().DIR_DOWN, 0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			Game.getCamera().move(Game.getCamera().DIR_LEFT, 0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			Game.getCamera().move(Game.getCamera().DIR_RIGHT, 0.01f);
		}
	}

	private void mouse() {
	}

}
