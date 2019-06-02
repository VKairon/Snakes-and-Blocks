import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Shield extends Group
{
	Circle cir;
	int points = 10;
	int validTime = 5;
	
	public Shield(int xpos, int ypos,int s)
	{
		Image image = new Image("shield.png");
		cir = new Circle((double)xpos,(double)ypos,s);
		cir.setFill(new ImagePattern(image));
		this.getChildren().addAll(cir);
	}
	
	public void setPoints(int p)
	{
		points = p;
	}

}
