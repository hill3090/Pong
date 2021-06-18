
public class Ball {
	
	private Rect rect;
	private Rect leftPaddle, rightPaddle;
	
	private double vy = 10.0;
	private double vx = -200.0;
	
	private Text rightScoreText;
	private Text leftScoreText;
	
	public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText)	{
		this.rect = rect;
		this.leftPaddle = leftPaddle;
		this.rightPaddle = rightPaddle;
		this.leftScoreText = leftScoreText;
		this.rightScoreText = rightScoreText;
	}
	
	
	public double calculateNewVelocityAngle(Rect paddle)	{
		double relativeIntersectY = (paddle.getY() + (paddle.getHeight() / 2.0)) - (this.rect.getY() + (this.rect.getHeight() / 2.0));
		double normalIntersectY = relativeIntersectY / (paddle.getHeight() / 2.0);
		double theta = normalIntersectY * Constants.MAX_ANGLE;
		
		return Math.toRadians(theta);
	}
	
	// Double check how the grid on swing works. Just to make sure this is %100 understood with ball collision.
	public void update(double dt)	{
		
		// If ball is traveling left, check for collision with player paddle.
		if (vx < 0)	{
			if (this.rect.getX() <= this.leftPaddle.getX() + this.leftPaddle.getWidth() && this.rect.getX() + this.rect.getWidth() >= this.leftPaddle.getX() &&
				this.rect.getY() >= this.leftPaddle.getY() && this.rect.getY() <= this.leftPaddle.getY() + this.leftPaddle.getHeight())	{
				
				double theta = calculateNewVelocityAngle(leftPaddle);
				double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
				double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;
				
				double oldSin = Math.signum(vx);
				this.vx = newVx * (-1.0 * oldSin);
				this.vy = newVy;
				
				// this.vx *= -1;
				// this.vy *= -1; This seems to make the ball go back exactly the same way when hitting the paddle
			} else if (this.rect.getX() + this.rect.getWidth() < this.leftPaddle.getX()) {
				//System.out.println("You lost.");
			}
		// If ball is traveling right, check for collision with ai
		} else if (vx > 0)	{
			if (this.rect.getX() + this.rect.getWidth() >= this.rightPaddle.getX() && this.rect.getX() <= this.rightPaddle.getX() + this.rightPaddle.getWidth() &&
				this.rect.getY() >= this.rightPaddle.getY() && this.rect.getY() <= this.rightPaddle.getY() + this.rightPaddle.getHeight())	{
				
				double theta = calculateNewVelocityAngle(rightPaddle);
				double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
				double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;
				
				double oldSin = Math.signum(vx);
				this.vx = newVx * (-1.0 * oldSin);
				this.vy = newVy;
				
				// this.vx *= -1;
				// this.vy *= -1; This seems to make the ball go back exactly the same way when hitting the paddle
			} else if (this.rect.getX() + this.rect.getWidth() > this.rightPaddle.getX() + this.rightPaddle.getWidth())	{
				//System.out.println("AI has lost a point");
			}
		}
		
		if (vy > 0)	{
			if (this.rect.getY() + this.rect.getHeight() > Constants.SCREEN_HEIGHT)	{
				this.vy *= -1;
			}
		} else if (vy < 0)	{
			if (this.rect.getY() < Constants.TOOLBAR_HEIGHT)	{
				this.vy *= -1;
			}
		}
		
	
		this.rect.setX(this.rect.getX() + vx * dt);
		this.rect.setY(this.rect.getY() + vy *  dt);
		
		if (this.rect.getX() + this.rect.getWidth() < leftPaddle.getX())	{
			int rightScore = Integer.parseInt(rightScoreText.getText());
			rightScore++;
			rightScoreText.setText("" + rightScore);
			this.rect.setX(Constants.SCREEN_WIDTH /2.0);
			this.rect.setY(Constants.SCREEN_HEIGHT / 2.0);
			this.vx = -200;
			this.vy = 10;
			if(rightScore >= Constants.WIN_SCORE)	{
				Main.changeState(2);
			}
			
		} else if (this.rect.getX() > rightPaddle.getX() + rightPaddle.getWidth())	{
			int leftScore = Integer.parseInt(leftScoreText.getText());
			leftScore++;
			leftScoreText.setText("" + leftScore);
			this.rect.setX(Constants.SCREEN_WIDTH /2.0);
			this.rect.setY(Constants.SCREEN_HEIGHT / 2.0);
			this.vx = 200;
			this.vy = 10;
			if(leftScore >= Constants.WIN_SCORE)	{
				Main.changeState(2);
			}
			
		}
	}
	
	public Rect getRect()	{
		return rect;
	}
}
