import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Magnet extends Group
{
	Circle cir;
	int points = 15;
	int validTime = 5;
	double range = 20; //cm radius? 
	
	public Magnet(int xpos, int ypos,int s)
	{
		Image image = new Image("magnet1.png");
		cir = new Circle((double)xpos,(double)ypos,s);
		cir.setFill(new ImagePattern(image));
		this.getChildren().addAll(cir);
	}
}
