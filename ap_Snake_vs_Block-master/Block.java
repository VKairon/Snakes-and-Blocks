import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;


public class Block extends Group

{
	static int width = 100;
	static int length = 100;
	Rectangle rect;
	Text text;
	
	int points;
	
	public Block(int xpos, int ypos, int p)
	{
		rect = new Rectangle(xpos, ypos,length, width);
		rect.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//		this.setTranslateY(ypos);
//		this.setTranslateX(xpos);
		points = 3;
		text = new Text(xpos+50, ypos+50, Integer.toString(p));
		text.setFill(Color.WHITE);
		this.getChildren().addAll(rect,text);
	}
	
	
//	public void render(Pane gc, Color c)
//	{
//		gc.setFill(c);
//		gc.fillRect(xpos,ypos,100,100);
//		gc.strokeText(Integer.toString(points), xpos+50, ypos+50);
//	}
	
	public void animate()
	{
		this.setTranslateY(this.getTranslateY()+1);
	}
	public void setPoints(int p)
	{
		points = p;
	}
}
