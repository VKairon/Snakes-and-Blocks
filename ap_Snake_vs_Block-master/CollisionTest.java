import java.io.InputStream;

import com.sun.javafx.geom.Shape;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CollisionTest extends Application implements EventHandler<KeyEvent>{

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
		
//		Rectangle rect = new Rectangle(20,30,50,70);
//		rect.setFill(Color.GREEN);
//		Rectangle rect2 = new Rectangle(70,30,50,70);
//		rect2.setFill(Color.ORANGE);
//		Rectangle rect3 = new Rectangle(120,30,50,70);
//		rect3.setFill(Color.BLUE);
//		Circle cir = new Circle(100,200,30);
//		cir.setFill(Color.RED);
		Ball ball = new Ball(500,200,30, null);
	
		BlockChain rects = BlockChain.createBlockChain();
		pane.getChildren().addAll(rects,ball);
		stage.setScene(scene);
		stage.show();


//		System.out.println(checkCollision(rect, cir));
//		boolean collided = rect.getBoundsInParent().intersects(cir.getBoundsInParent());
			
		
//		TranslateTransition trans = new TranslateTransition();
//		trans.setDuration(Duration.seconds(7));
//		trans.setToY(700);
//		trans.setNode(rects);
//		trans.play();
		

/*
//		rect.yProperty().addListener(  
		rects.translateYProperty().addListener(
                (observable, oldValue, newValue) ->
                {
                	System.out.println("oldValue = "+oldValue);
                	System.out.println("newValue = "+ newValue);
//                	System.out.println("y position changed");
                    for(Node n: rects.getChildren())
             		{
//                    	System.out.println(n.getBoundsInParent());
                    	if(checkCollision(n,ball)==true)
                        {
                			System.out.println("Collided");
                       	    trans.pause();
//                			rect.setFill(Color.RED);
//                			cir.setFill(Color.GREEN);
                        }
                       
             		}
                     
                }
                     
                );
*/
		
// ver 3		

    	for(Node n: rects.getChildren())
 		{
    		TranslateTransition trans = new TranslateTransition();
    		trans.setDuration(Duration.seconds(7));
    		trans.setToY(700);
    		trans.setNode(n);
    		trans.play();

		    n.translateYProperty().addListener(
                (observable, oldValue, newValue) ->
                {
                	System.out.println("oldValue = "+oldValue);
                	System.out.println("newValue = "+ newValue);
//                	System.out.println("y position changed");
                    if(checkCollision(n,ball)==true)
                       {
                			System.out.println("Collided");
                       	    trans.pause();
//                			rect.setFill(Color.RED);
//                			cir.setFill(Color.GREEN);
                        }
                       
             		}
                     
                );
 		}

		
		
		
		
		
		
		
		
		/*		for(int i = 0; i<20; i++)
		{
			System.out.println("in loop i = "+i);
			if (rect.getBoundsInParent().intersects(cir.getBoundsInParent())) 
			{
			trans.pause();
			rect.setFill(Color.RED);
			cir.setFill(Color.GREEN);
			System.out.println("Collided");
			
			}
			Thread.sleep(500);
		}
       
	*/	
		
	}
	
	public boolean checkCollision(Node a, Node b)
	{
//		System.out.println("n = " +a.getBoundsInParent());
//		System.out.println("ball = " +b.getBoundsInParent());
//		System.out.println();
		
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
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
