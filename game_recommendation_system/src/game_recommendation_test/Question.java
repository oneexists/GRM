package game_recommendation_test;
import java.util.List;


public class Question
{
    public String quest;
    public SubQuestion[] dbChoices = new SubQuestion[3];
    
    
    public Question(String q, String c1, String c2, String c3)
    {
    	this.quest = q;
    	dbChoices[0] = new SubQuestion(c1);
    	dbChoices[1] = new SubQuestion(c3);
    	dbChoices[2] = new SubQuestion(c2);
    }
}
