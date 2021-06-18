import java.awt.event.KeyEvent;

public class PlayerController {
	
	private Rect rect;
	private KL keyListener;
	
	public PlayerController(Rect rect, KL keyListener)	{
		this.rect = rect;
		this.keyListener = keyListener;
		
	}
	
	public PlayerController(Rect rect)	{
		this.rect = rect;
		this.keyListener = null;
	}
	
	public void update(double dt)	{
		if (keyListener != null)	{
			if (keyListener.isKeyPressed(KeyEvent.VK_DOWN))	{
				moveDown(dt);
			} else if (keyListener.isKeyPressed(KeyEvent.VK_UP))	{
				moveUp(dt);
			}
		}
	} 
	
	public void moveUp(double dt)	{
		if (rect.getY() - Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT)
			rect.setY((rect.getY() - Constants.PADDLE_SPEED * dt));
	}
	
	public void moveDown(double dt)	{
		if ((rect.getY() - Constants.PADDLE_SPEED * dt) + rect.getHeight() < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)
			rect.setY((rect.getY() + Constants.PADDLE_SPEED * dt));	
	}
	
	public Rect getRect()	{
		return rect;
	}
}