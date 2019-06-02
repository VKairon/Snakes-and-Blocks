

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javafx.*;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX_Demo extends Application implements EventHandler<KeyEvent>
{
	static Stage stage2;
	static Label gameTitle;
	static Button playGame;
	static Button pauseGame;
	static Button continueGame;
	static Label continueGame2;
	static Button resumeGame;
	static Button restartGame;
	static Label restartGame2;
	static Button exitGame;
	static Label exitGame2;
	static Button quitGame;
	static Button dispLB;
	static Button viewScore;
	static Label coins;
	static int coinText;
	static Label score;
	static int scoreText;
	static Stage stage = new Stage();
	static BlockChain blocks;
	static TranslateTransition trans;
	static TranslateTransition snakeTrans;
	static double collidedY = 0.0;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		gameTitle = new Label("Welcome To Snake Vs. Block Game!");

		InputStream input = getClass().getResourceAsStream("play.png");
		Image image = new Image(input);
		ImageView imageView = new ImageView(image);
		playGame = new Button("", imageView);
		playGame.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		input = getClass().getResourceAsStream("pause.png");
		image = new Image(input);
		imageView = new ImageView(image);
		pauseGame = new Button("", imageView);
		pauseGame.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

		input = getClass().getResourceAsStream("exit.png");
		image = new Image(input);
		imageView = new ImageView(image);
		exitGame = new Button("", imageView);
		exitGame.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

		input = getClass().getResourceAsStream("quit.png");
		image = new Image(input);
		imageView = new ImageView(image);
		quitGame = new Button("", imageView);
		quitGame.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

		input = getClass().getResourceAsStream("continue.png");
		image = new Image(input);
		imageView = new ImageView(image);
		continueGame = new Button("", imageView);
		continueGame.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

		input = getClass().getResourceAsStream("replay.png");
		image = new Image(input);
		imageView = new ImageView(image);
		restartGame = new Button("", imageView);
		restartGame.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

		Image crown = new Image("crown.png");
		imageView = new ImageView(crown);
		dispLB = new Button("", imageView);
		dispLB.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		dispLB.setFont(Font.loadFont("file:///C:/Users/tanya/Downloads/Montserrat-Black.ttf", 25));
		dispLB.setTextFill(Color.ANTIQUEWHITE);

		Image star = new Image("star.png");
		imageView = new ImageView(star);
		viewScore = new Button("Score", imageView);
		viewScore.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		viewScore.setFont(Font.loadFont("file:///C:/Users/tanya/Downloads/Montserrat-Black.ttf", 25));
		viewScore.setTextFill(Color.ANTIQUEWHITE);
		openMenu();
	}


	public static void openMenu()
	{
		playGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when play game is pressed	
				stage.close();
				try {
					openGameWindow();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});



		quitGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when quit game is pressed			
				Platform.exit();
			}
		});

		dispLB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when leader board button is pressed
				LBPage();
			}
		});

		viewScore.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when score button is pressed
				scorePage();
			}
		});

		HBox root = new HBox(10,playGame,continueGame,quitGame,dispLB, viewScore );
		root.setAlignment(Pos.BOTTOM_CENTER);
		Scene scene = new Scene(root,600,600);
		BackgroundImage bg = new BackgroundImage(new Image("bg-50.jpg"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		root.setBackground(new Background(bg));
		stage.setScene(scene);
		stage.show();
	}

	public static void openGameWindow() throws InterruptedException
	{
		stage2 = new Stage();
		BorderPane pane = new BorderPane();
		Scene scene2 = new Scene(pane);
		pane.setPrefSize(1000,1000);
		HBox labels = new HBox();
		labels.setPadding(new Insets(15, 12, 15, 12));
		labels.setSpacing(100);
		coins = new Label("Coins: " + coinText);
		coins.setTextFill(Color.ANTIQUEWHITE);
		coins.setFont(Font.font("Cambria", 32));
//		coinText = new Label("Coins: " + 5);
//		coinText.setTextFill(Color.ANTIQUEWHITE);
//		coinText.setFont(Font.font("Cambria", 32));
		score = new Label("Score: " + scoreText);
		score.setTextFill(Color.WHITESMOKE);
		score.setFont(Font.font("Cambria", 32));
		

		Pane centerPane = new Pane();
		centerPane.setPrefSize(600,600);

		// to create the snake
		Ball ball1 = new Ball(450,650,30, null);
//		Ball eyeball = new Ball(435,630,7,Color.BLACK);
//		Ball eyeball2 = new Ball(465,630,7, Color.BLACK);
//		Ball ball2 = new Ball(450,700,30, null);
//		Ball ball3 = new Ball(450,750,30, null);
//		Ball ball4 = new Ball(450,800,30,null);
		
		
		

		Snake snake = new Snake();
		snake.add(ball1);
		snake.initializeSnake();
//		snake.addNewBalls(2);
		
//		snake.add(ball1);
//		snake.add(ball2);
//		snake.add(ball3);
//		snake.add(ball4);
//		snake.add(eyeball);
//		snake.add(eyeball2);
		centerPane.getChildren().addAll(snake);


		// moving the snake on pressing left and right keys

		ParallelTransition snakePT = new ParallelTransition();
		snakeTrans = new TranslateTransition();


		pane.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case RIGHT:
					//     	                    System.out.println("Right key released");
					//     	            		trans2.setToX(700);
					for(Node n: snake.getChildren())
					{
						//     	               		 snakeTrans = new TranslateTransition();
						snakeTrans = new TranslateTransition();
						snakeTrans.setDuration(Duration.millis(30));
						snakeTrans.setNode(n);
						//     	                	System.out.println("n inside =" + n);
						snakeTrans.setByX(30);
						snakePT.getChildren().add(snakeTrans);
					}
					snakePT.play();
//					snake.setCurrXPos(ball1.getLayoutX());
					break;
				case LEFT:

					for(Node n: snake.getChildren())
					{
						snakeTrans = new TranslateTransition();
						snakeTrans.setDuration(Duration.millis(30));
						snakeTrans.setNode(n);
						snakeTrans.setByX(-30);
						snakePT.getChildren().add(snakeTrans);
					}
					snakePT.play();
//					snake.setCurrXPos(ball1.getLayoutX());
					break;
				}
			}

		});	


		// creating the blockchain and using sequential transition to bring blockchains down the screen	
		//COLLISION FINALLY WORKS HOORAY
		SequentialTransition s = new SequentialTransition(); 
		

		for(int i = 0; i < 100; i++) // to change to do-while for infinite game
		{
//			lenReduced = false;
//			System.out.println("inside seq, lenred = "+lenReduced);
			ParallelTransition p = new ParallelTransition();

			blocks = BlockChain.createBlockChain();
			centerPane.getChildren().addAll(blocks);

			for(Node n: blocks.getChildren())
			{
				TranslateTransition blockTrans = new TranslateTransition();
				blockTrans.setDuration(Duration.seconds(10));
				blockTrans.setToY(2200);
				blockTrans.setNode(n);
				p.getChildren().add(blockTrans);
				
				
				n.translateYProperty().addListener(
						(observable, oldValue, newValue) ->
						{
//							System.out.println("old val ="+oldValue);
//							System.out.println("new  old val ="+collidedY);
							// if oldvalue is more than 200 Collided Y value  then reset collided Y value to 0
							if((collidedY>0)&&((double)oldValue - collidedY > 200))
								{
									collidedY = 0;
								}
							
							if((checkCollision(n,ball1)==true)&&((double)oldValue - collidedY > 200))
							{

//									System.out.println("Block Collided"); 
									
									collidedY = (double) oldValue;
									snake.decreaseLength(1);
									scoreText += 3;
									score.setText("Score: "+ scoreText);
//									lenReduced = true;
	//								s.pause();
							}
						}

						);   

			}

			s.getChildren().add(p);
		}
		s.play();

		pauseGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when pause game is pressed	
				s.pause();
//				snakeTrans.pause();

				Stage stage3 = new Stage();
				VBox root2 = new VBox();
				root2.setPrefSize(300,300);
				continueGame2 = new Label("Resume Game");
				exitGame2 = new Label("Exit Game");
				restartGame2 = new Label("Restart Game");

				continueGame.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) 
					{
						//when continue game is pressed			
						stage3.hide();
						snakeTrans.play();
						s.play();


					}
				});

				exitGame.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) 
					{
						//when exit game is pressed			
						stage3.close();
						Platform.exit();
					}
				});

				restartGame.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) 
					{
						//when restart game is pressed			
						stage3.close();
						try {
							openGameWindow();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

				root2.getChildren().addAll(continueGame,continueGame2,restartGame, restartGame2,exitGame,exitGame2);
				Scene scene3 = new Scene(root2);
				stage3.setScene(scene3);
				stage3.show();
				//				openPauseMenu();
			}
		});

		exitGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when exit game is pressed	
				stage2.hide();
				openMenu();
			}
		});

		// movement of tokens

		//magnet movement
		SequentialTransition s2 = new SequentialTransition();
		for(int i = 0; i < 40; i++)
		{
			//	          System.out.println(i);
			int k = (int) (Math.random()*600);
			//	    	System.out.println("k = " +k);
			Magnet magnet = new Magnet(k,-500,40);
			centerPane.getChildren().addAll(magnet);
			TranslateTransition trans3 = new TranslateTransition();
			trans3.setDuration(Duration.seconds(9));
			trans3.setToY(2500);
			trans3.setNode(magnet);
			//  trans.play();
			magnet.translateYProperty().addListener(
					(observable, oldValue, newValue) ->
					{
						//	                      System.out.println("y position changed");

						if(checkCollision(magnet,ball1))
						{
							s2.pause();
							System.out.println("Collided");
						}
					}

					);   

			s2.getChildren().add(trans3);


		}
		s2.play();


		// coin movement

		SequentialTransition s4 = new SequentialTransition();
		for(int i = 0; i < 40; i++)
		{
			int k = (int) (Math.random()*600);
			Coin coin = new Coin(k,-500,40);
			centerPane.getChildren().addAll(coin);
			TranslateTransition trans3 = new TranslateTransition();
			trans3.setDuration(Duration.seconds(9));
			trans3.setToY(2200);
			trans3.setNode(coin);
			coin.translateYProperty().addListener(
					(observable, oldValue, newValue) ->
					{
						
						if(checkCollision(ball1,coin))
						{
							System.out.println("Coin Collided");
							
//							s4.pause();
//							snake.addNewBalls(1);
						}
					}

					);   

			s4.getChildren().add(trans3);


		}
		s4.play();

		//shield movement
		
		SequentialTransition s3 = new SequentialTransition();
		for(int i = 0; i < 40; i++)
		{
			//		          System.out.println(i);
			int k = (int) (Math.random()*600);
			//		    	System.out.println("k = " +k);
			Shield shield = new Shield(k,-500,40);
			centerPane.getChildren().addAll(shield);
			TranslateTransition trans3 = new TranslateTransition();
			trans3.setDuration(Duration.seconds(9));
			trans3.setToY(2200);
			trans3.setNode(shield);
			//  trans.play();
			shield.translateYProperty().addListener(
					(observable, oldValue, newValue) ->
					{
						//		                      System.out.println("y position changed");

						if(checkCollision(ball1,shield))
						{
							s3.pause();
							System.out.println("Collided");
						}
					}

					);   

			s3.getChildren().add(trans3);


			//for collision


		}
		s3.play();

		//destroyBlock Movement

		SequentialTransition s5 = new SequentialTransition();
		for(int i = 0; i < 40; i++)
		{
			
			int k = (int) (Math.random()*600);
			
			DestroyBlocks destroy = new DestroyBlocks(k,-500,40);
			centerPane.getChildren().addAll(destroy);
			TranslateTransition trans3 = new TranslateTransition();
			trans3.setDuration(Duration.seconds(9));
			trans3.setToY(2200);
			trans3.setNode(destroy);
			//  trans.play();
			destroy.translateYProperty().addListener(
					(observable, oldValue, newValue) ->
					{
						if(checkCollision(ball1,destroy))
						{
							s5.pause();
							System.out.println("Collided");
						}
					}

					);   

			s5.getChildren().add(trans3);
		}
		s5.play();



		//	    centerPane.getChildren().addAll(magnet,shield,coin,destroy);
		labels.getChildren().addAll(score,coins, pauseGame, exitGame);
		pane.setCenter(centerPane);
		pane.setTop(labels);
		//		pane.setStyle("-fx-background-image: url('file:///C:/Users/tanya/Downloads/background.png');");
		BackgroundImage bg = new BackgroundImage(new Image("background.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		pane.setBackground(new Background(bg));
		stage2.setScene(scene2);
		stage2.show();

	}

	public static void LBPage()
	{
		Stage stage4 = new Stage();
		VBox root4 = new VBox();
		Label LB = new Label("5");

		Image crown = new Image("crown.png");
		ImageView imageView = new ImageView(crown);
		root4.getChildren().add(imageView);
		root4.getChildren().add(LB);
		//font and font size
		LB.setFont(Font.loadFont("file:///C:/Users/tanya/Downloads/Montserrat-Black.ttf", 25));
		LB.setTextFill(Color.BLACK);
		root4.setAlignment(Pos.CENTER);

		Scene scene4 = new Scene(root4,500,400);
		stage4.setScene(scene4);
		stage4.show();

	}

	public static void scorePage()
	{
		Stage stage5 = new Stage();
		VBox root5 = new VBox();
		Label sc = new Label("24");

		Image star = new Image("star.png");
		ImageView imageView = new ImageView(star);
		root5.getChildren().add(imageView);
		root5.getChildren().add(sc);
		//font and font size
		sc.setFont(Font.loadFont("file:///C:/Users/tanya/Downloads/Montserrat-Black.ttf", 25));
		sc.setTextFill(Color.BLACK);
		root5.setAlignment(Pos.CENTER);

		Scene scene5 = new Scene(root5,500,400);
		stage5.setScene(scene5);
		stage5.show();

	}
	public static void openPauseMenu()
	{
		Stage stage3 = new Stage();
		VBox root2 = new VBox();
		root2.setPrefSize(300,300);
		continueGame2 = new Label("Resume Game");
		exitGame2 = new Label("Exit Game");
		restartGame2 = new Label("Restart Game");

		continueGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when continue game is pressed			
				stage3.hide();

			}
		});

		exitGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when exit game is pressed			
				stage3.close();
				Platform.exit();
			}
		});

		restartGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				//when restart game is pressed			
				stage3.close();
				try {
					openGameWindow();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		root2.getChildren().addAll(continueGame,continueGame2,restartGame, restartGame2,exitGame,exitGame2);
		Scene scene3 = new Scene(root2);
		stage3.setScene(scene3);
		stage3.show();
	}

	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	//checks to see if any two nodes have boundaries intersecting, i.e, if there is a collision
	public static boolean checkCollision(Node a, Node b)
	{
		//		System.out.println("head = " + b.getBoundsInParent());
		if(a.getBoundsInParent().intersects(b.getBoundsInParent()) )
		{
//			System.out.println("true");
			return true;
		}
		else 
		{
			//			System.out.println("false");
			return false;
		}
	}

}
