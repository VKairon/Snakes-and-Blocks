import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationDemo extends Application implements EventHandler<KeyEvent>
{

	public static void main(String[] args)
	{
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception 
	{
		stage = new Stage();
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
	
//		BlockChain rects = BlockChain.createBlockChain();
//		pane.getChildren().addAll(rects);
		Block b = new Block(100,100,5);
		Ball ball = new Ball(200,200,30, null);
	    pane.getChildren().addAll(b, ball);
//	      b.setTranslateY(0);
	        new AnimationTimer() {
	            public void handle(long now) 
	            {
//	                 TranslateTransition trans = new TranslateTransition();
//	                 trans.setDuration(Duration.seconds(7));
//	                  trans.setToY(850);
//	                  trans.setNode(b);
	                  //  trans.play();
//	                  s.getChildren().add(trans); 
	                b.animate();
	            }
	        }.start();
		stage.setScene(scene);
		stage.show();
	}

	public boolean checkCollision(Node a, Node b)
	{
		if(a.getBoundsInParent().intersects(b.getBoundsInParent()) )
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}