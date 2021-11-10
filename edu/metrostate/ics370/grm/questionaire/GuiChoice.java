package edu.metrostate.ics370.grm.questionaire;
import edu.metrostate.ics370.grm.model.GameTag;


public class GuiChoice
{
    //public Text txt_choice;
	private int num;
	private GameTag[] dbTags = new GameTag[100];
    
    
    public GuiChoice()
    {
    	for (int i = 0; i < getDbTags().length; i++)
    	{
    		getDbTags()[i] = new GameTag();
    	}
    }


	public int getNum()
	{
		return num;
	}


	public void setNum(int num)
	{
		this.num = num;
	}


	public GameTag[] getDbTags()
	{
		return dbTags;
	}


	public void setDbTags(GameTag[] dbTags)
	{
		this.dbTags = dbTags;
	}
}
