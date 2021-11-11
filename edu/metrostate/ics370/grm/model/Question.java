package edu.metrostate.ics370.grm.model;


public class Question
{
	private String quest;
	private SubQuestion[] dbChoices = new SubQuestion[3];
    
    
    public Question(String q, String c1, String c2, String c3)
    {
    	this.setQuest(q);
    	getDbChoices()[0] = new SubQuestion(c1);
    	getDbChoices()[1] = new SubQuestion(c3);
    	getDbChoices()[2] = new SubQuestion(c2);
    }


	public String getQuest()
	{
		return quest;
	}


	public void setQuest(String quest)
	{
		this.quest = quest;
	}


	public SubQuestion[] getDbChoices()
	{
		return dbChoices;
	}


	public void setDbChoices(SubQuestion[] dbChoices)
	{
		this.dbChoices = dbChoices;
	}
}
