import javafx.scene.Group;

public class BlockChain extends Group
{
	int xpos = 0;
	int ypos = 0;
	
	public void add(Block b)
	{
		this.getChildren().add(b);
	}
	
	public static BlockChain createBlockChain()
    {
        BlockChain blocks = new BlockChain();
        
        int j = (int)(Math.random()*12);
        int k = (int)Math.random()*800;
        
        for(int i=0;i<11;i++)
        {
            int addBlock = (int) (Math.random()*2);
//            System.out.println("addBlock = "+addBlock);
            if(addBlock == 1)
            {
                blocks.add(new Block(k,-250,(int)(Math.random()*50)));
            }
            k = k + 100;
        }     
//        blocks.setTranslateX(k);
//        blocks.setTranslateY(-250);
        return blocks;
    }
	
	public void animate()
	{
		this.setTranslateY(this.getTranslateY()+1);
	}
}
