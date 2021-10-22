package edu.metrostate.ics370.grm.questionaire;


public class GuiChoice
{
    //public Text txt_choice;
    public int num;
    public SubTag[] dbTags = new SubTag[100];
    
    
    public GuiChoice()
    {
    	for (int i = 0; i < dbTags.length; i++)
    	{
    		dbTags[i] = new SubTag();
    	}
    }
}
