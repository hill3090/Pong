
public class AIController {
	
	private PlayerController playerController;
	private Rect ball;
	
	
	public AIController(PlayerController plrController, Rect ball)	{
		
		this.playerController = plrController;
		this.ball = ball;
		
	}
	
	public void update(double dt)	{
		
		playerController.update(dt); // Not sure why this is here. AI works this commented out. Left in for consistency with tutorial.
		
		if (ball.getY() < playerController.getRect().getY())	{
			playerController.moveUp(dt);
		} else if (ball.getY() + ball.getHeight() > playerController.getRect().getY() + playerController.getRect().getHeight())	{
			playerController.moveDown(dt);
		}
	}
}
