package edu.metrostate.ics370.grm.controller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import edu.metrostate.ics370.grm.model.Game;


public class GameLoader
{
	public int numGames;
    public Game[] dbGames = new Game[2000];
    
    
	public void readDb() throws Exception //**Can read data from a file**//
	{
    	for (int i = 0; i < dbGames.length; i++)
    		dbGames[i] = new Game();

        //String targetString = "";
        StringBuffer sb = new StringBuffer();
    	try
    	{
            FileReader reader = new FileReader("C:\\Users\\chris\\GRM_Clone\\lib\\library.txt");
            //int intValueOfChar;
            //while ((intValueOfChar = reader.read()) != -1)
            //    targetString += (char) intValueOfChar;
            
            Scanner sc = new Scanner(reader);
  		  	//Instantiating the StringBuffer class to hold the result
            while(sc.hasNext())
            {
            	sb.append(sc.next());
            	//System.out.println(sc.next());
            }

            
            reader.close();	
    	}
    	catch (IOException e)
    	{
            ((Throwable) e).printStackTrace();
    	}
        
		  //System.out.println("Step 0");

		  //Instantiating the URL class
		  //URL url = new URL("https://steamspy.com/api.php?request=tag&tag=rts");
		  //Retrieving the contents of the specified page
        //Scanner sc = new Scanner(url.openStream());
		  //Instantiating the StringBuffer class to hold the result
        //StringBuffer sb = new StringBuffer();
        //while(sc.hasNext())
        //{
        //sb.append(sc.next());
		     //System.out.println(sc.next());
        //}
		  //Retrieving the String from the String Buffer object
		  String result = sb.toString();
		  //System.out.println(result);
		  //Removing the HTML tags
		  result = result.replace(",", " ");
		  /*
		  result = result.replaceAll("<[^>]*>", " ");
		  result = result.replace("\"", "");
		  result = result.replace(":", " ");
		  result = result.replace("{", " ");
		  result = result.replace("}", " ");
		  */
		  //System.out.println("Contents of the web page: " + result);
		  //String[] dbNames = new String[30000];
		  //String[] dbId = new String[30000];
		  //float[] dbScore = new float[30000];
		  String[] words = result.split(" ");
		  
		  boolean inTags = false;
		  boolean inName = false;
		  String nam = "";
		
		  for (int x = 0; x < words.length; x++)
		  {
			  //System.out.println("Words: " + words[x]);
			  if (words[x].equals("appid"))
			  {
				  //dbId[numGames] = words[x + 1];
				  //System.out.println(words[x] + " " + words[x + 1]);
				  dbGames[numGames].setId(words[x + 1]);
			  }
			  if (words[x].equals("name"))
			  {
				  inName = true;
				  nam = "";
				  //dbNames[numGames] = words[x + 1];
			  }
			  else
			  if (words[x].equals("rating"))
			  {
				  inName = false;
				  float pos = Float.parseFloat(words[x + 1]);
				  dbGames[numGames].setRating(pos);
			  }
			  else
			  if (words[x].equals("tags"))
			  {
				  inTags = true;
			  }
			  else
			  if (words[x].equals("END"))
			  {
				  numGames++;
				  inTags = false;
			  }
			  else
			  if (inTags)
			  {
				  dbGames[numGames].addTag(words[x]);
			  }
			  else
			  if (inName)
			  {
				  if (nam != "")
					  nam += " ";
				  nam += words[x];
				  dbGames[numGames].setName(nam);
			  }
		  }
		  

		  //for (int i = 0; i < 100; i++)
		  //{
		  //String desc = "appid " + dbGames[i].getId() + " name " + dbGames[i].getName() + " rating " + dbGames[i].getRating() + " tags ";
		  //for (int z = 0; z < dbGames[i].getNumTags(); z++)
		  //desc += dbGames[i].getTag(z).getName() + " ";
			  
			  //System.out.println(desc);
		  //}
		  //System.out.println("Num Games: " + numGames);
		  
		  
		  /*
		  Scanner sc = new Scanner(new File("C:\\Users\\chris\\GRM_Clone\\lib\\tags.csv"));
		  String[] dbTags = new String[91];

		  sc.useDelimiter(",");
		  while (sc.hasNext())
		  {
			  System.out.print(sc.next());
		  }
		  sc.close();
		  */
	}
    
    
    public GameLoader() throws Exception
    {
    	readDb();
    }
}
