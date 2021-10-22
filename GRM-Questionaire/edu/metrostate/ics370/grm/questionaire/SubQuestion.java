package edu.metrostate.ics370.grm.questionaire;


public class SubQuestion
{
    public String choice;
    public SubTag[] dbTags = new SubTag[100];
    
    
    public SubQuestion(String wrd)
    {
    	this.choice = wrd;
    	
    	for (int i = 0; i < dbTags.length; i++)
    	{
    		dbTags[i] = new SubTag();
    		dbTags[i].tag = "";
    		dbTags[i].val = 1;
    	}
    }
}
