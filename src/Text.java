import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Text {
	
	public String text;
	public Font font;
	public double x, y;
	public double width, height;
	public Color color = Color.WHITE;
	
	
	public Text(String text, Font font, double x, double y, Color color)	{
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
		this.width = font.getSize() * text.length();
		this.height = font.getSize();	
	}
	
	public Text(int text, Font font, double x, double y)	{
		this.text = "" + text;
		this.font = font;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2)	{
		g2.setColor(color);
		g2.setFont(font);
		g2.drawString(text, (float) x, (float) y);
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getWidth()	{
		return width;
	}
	
	public double getHeight()	{
		return height;
	}
}
