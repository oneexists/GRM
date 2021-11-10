package edu.metrostate.ics370.grm.questionaire;
import edu.metrostate.ics370.grm.model.GameTag;


public class SubQuestion
{
	private String choice;
	private GameTag[] dbTags = new GameTag[100];
    
    
    public SubQuestion(String wrd)
    {
    	this.setChoice(wrd);
    	
    	for (int i = 0; i < getDbTags().length; i++)
    	{
    		getDbTags()[i] = new GameTag();
    		getDbTags()[i].setTag("");
    		getDbTags()[i].setVal(1);
    	}
    }


	public GameTag[] getDbTags()
	{
		return dbTags;
	}


	public void setDbTags(GameTag[] dbTags)
	{
		this.dbTags = dbTags;
	}


	public String getChoice()
	{
		return choice;
	}


	public void setChoice(String choice)
	{
		this.choice = choice;
	}
}
