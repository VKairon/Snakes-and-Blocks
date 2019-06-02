import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Snake extends Group
{
	int  length = -2; 
	double currXPos;
	
	public void setCurrXPos(Double x)
	{
		currXPos = x;
	}
	
	public void add(Ball b)
	{
			this.getChildren().add(b);
			length = length + 1;
	}
	
	public boolean decreaseLength(int dl)
	{
		System.out.println("snake len = " + length);
		if(length <= dl)
		{
			return false;
		}
		
		for(int i = 0; i < dl; i++)
		{
			System.out.println("removing index = " + (length-1));
			this.getChildren().remove(length+1);
			length = length - 1;
		}
		
		return true;
	}
	
	public void increaseLength()
	{
		
	}
	
	
	
	public void initializeSnake()
	{
//		Ball ball1 = new Ball(450,650,30, null);
		 int startX = 450; int startY = 650;
		Ball eyeball = new Ball(435,630,7,Color.BLACK);
		Ball eyeball2 = new Ball(465,630,7, Color.BLACK);
		Ball ball2 = new Ball(450,700,30, null);
		Ball ball3 = new Ball(450,750,30, null);
		Ball ball4 = new Ball(450,800,30,null);
				
//		add(ball1);
		add(eyeball);
		add(eyeball2);
		add(ball2);
		add(ball3);
		add(ball4);
		
		
	}
	
	public void addNewBalls(int n)
	{
		// todo
		int startY = 650;
		
		
		for(int i = 0; i < n; i++)
		{
			Ball b = new Ball((int)this.getLayoutX(), startY+(50*(length)),30, null);
			add(b);
			
		}
	}
}

