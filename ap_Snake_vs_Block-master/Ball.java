import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Ball extends Group
{
//	int size;
	Circle cir;
	Text text;
	int points;
	Color c = Color.CHOCOLATE;
	public Ball(int xpos, int ypos,int s, Color c)
	{
		cir = new Circle((double)xpos,(double)ypos,s);
		if(c==null)
		{
			cir.setFill(Color.CHOCOLATE);
		}
		else
		{
			cir.setFill(c);
		}
		this.getChildren().addAll(cir);
//		size = s;
	}
	
	public Ball(int xpos, int ypos, int s, String t, int p, Color c)
	{
		cir = new Circle((double)xpos,(double)ypos,s);
		if(c==null)
		{
			cir.setFill(Color.CHOCOLATE);
		}
		else
		{
			cir.setFill(c);
		}
		points = p;
		text = new Text(xpos+50, ypos+50, Integer.toString(p));
		text.setFill(Color.WHITE);
		this.getChildren().addAll(cir,text);
	}
	
//	public void render(Scene scene, Color c)
//	{
//		Circle ball = new Circle(xpos, ypos,10,c);
//		
//		Image flame = new Image("flame.png");
//		ImageView iv = new ImageView(flame);
//		gc.drawImage(flame,xpos,ypos);
//		
//		gc.setFill(c);
//		gc.fillOval(xpos,ypos,size,size);
//	}
}
