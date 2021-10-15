package game_recommendation_test;
import java.util.List;


public class SubGame
{
    public String nam;
    public float rating;
    public SubTag[] dbTags = new SubTag[100];
    
    
    public SubGame()
    {
    	for (int i = 0; i < dbTags.length; i++)
    	{
    		dbTags[i] = new SubTag();
    	}
    }
}
