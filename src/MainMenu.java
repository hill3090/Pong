import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

public class MainMenu extends JFrame implements Runnable {
	
	private Graphics2D g2;
	private KL keyListener = new KL();
	private Text startGame, exitGame, pong;
	private ML mouseListener = new ML();
	private boolean isRunning = true;

	public MainMenu()	{
		
		this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.setTitle(Constants.SCREEN_TITLE);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.startGame = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 140, Constants.SCREEN_HEIGHT / 2.0, Color.WHITE);
		this.exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 80, Constants.SCREEN_HEIGHT / 2.0 + 60, Color.WHITE);
		this.pong = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 100), Constants.SCREEN_WIDTH / 2.0 - 155, 200, Color.WHITE);
		
		
		g2 = (Graphics2D) this.getGraphics();
	}
	
	public void update(double dt)	{
		
		Image dbImage = createImage(getWidth(), getHeight());
		Graphics dbg = dbImage.getGraphics();
		this.draw(dbg);
		g2.drawImage(dbImage, 0, 0, this);
		System.out.println(mouseListener.getMouseX());
		System.out.println(mouseListener.getMouseY());
		
		if (mouseListener.getMouseX() > startGame.getX() && mouseListener.getMouseX() < startGame.getX() + startGame.getWidth()
			&& mouseListener.getMouseY() > startGame.getY() - startGame.getHeight() / 2 && mouseListener.getMouseY() < startGame.getY() + startGame.getHeight())	{
			startGame.color = new Color(158, 158, 158);
			
			if (mouseListener.isMousePressed())	{
				Main.changeState(1);
			}
			
		} else {
			startGame.color = Color.WHITE;
		}
		
		if (mouseListener.getMouseX() > exitGame.getX() && mouseListener.getMouseX() < exitGame.getX() + exitGame.getWidth()
		&& mouseListener.getMouseY() > exitGame.getY() - exitGame.getHeight() / 2 && mouseListener.getMouseY() < exitGame.getY() + exitGame.getHeight())	{
			exitGame.color = new Color(158, 158, 158);
			
			if (mouseListener.isMousePressed())	{
				Main.changeState(2);
			}
		} else {
			exitGame.color = Color.WHITE;
		}
		
	}
	
	public void draw(Graphics g)	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		pong.draw(g2);
		startGame.draw(g2);
		exitGame.draw(g2);
		
	}
	
	public void stop()	{
		isRunning = false;
	}
	
	
	public void run()	{
		double lastFrameTime = 0.0;
		while(isRunning)	{
			double time = Time.getTime();
			double deltaTime = time - lastFrameTime;
			lastFrameTime = time;
			
			update(deltaTime);
		}
		this.dispose();
		return;
	}
}

