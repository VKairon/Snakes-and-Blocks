import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DestroyBlocks extends Group
{
	Circle cir;
	int points;
	
	public DestroyBlocks(int xpos, int ypos,int s)
	{
		Image image = new Image("bomb111.png");
		cir = new Circle((double)xpos,(double)ypos,s);
		cir.setFill(new ImagePattern(image));
		this.getChildren().addAll(cir);
	}

}
