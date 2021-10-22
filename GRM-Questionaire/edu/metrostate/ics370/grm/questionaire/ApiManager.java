package edu.metrostate.ics370.grm.questionaire;


public class ApiManager
{
    public SubGame[] dbGames = new SubGame[100];
    
    
    public ApiManager()
    {
    	for (int i = 0; i < dbGames.length; i++)
    		dbGames[i] = new SubGame();
    	
    	dbGames[0].nam = "Gran Turismo";
    	dbGames[0].rating = 9;
    	dbGames[0].dbTags[0].tag = "online multiplayer";
    	dbGames[0].dbTags[1].tag = "fast";

    	dbGames[1].nam = "Warcraft 3";
    	dbGames[1].rating = 9.7f;
    	dbGames[1].dbTags[0].tag = "online multiplayer";
    	dbGames[1].dbTags[1].tag = "tactical";
    	dbGames[1].dbTags[2].tag = "team";
    	dbGames[1].dbTags[3].tag = "story";
    	dbGames[1].dbTags[4].tag = "engaging";

    	dbGames[2].nam = "Minecraft";
    	dbGames[2].rating = 8.8f;
    	dbGames[2].dbTags[0].tag = "online multiplayer";
    	dbGames[2].dbTags[1].tag = "solo";
    	dbGames[2].dbTags[2].tag = "creative";
    	dbGames[2].dbTags[3].tag = "relax";
    	dbGames[2].dbTags[4].tag = "single player";
    	dbGames[2].dbTags[5].tag = "dark dungeon";
    	dbGames[2].dbTags[6].tag = "bright";
    	dbGames[2].dbTags[7].tag = "happy";

    	dbGames[3].nam = "Dominos Master";
    	dbGames[3].rating = 5.5f;
    	dbGames[3].dbTags[0].tag = "online multiplayer";
    	dbGames[3].dbTags[1].tag = "solo";
    	dbGames[3].dbTags[2].tag = "realistic";
    	dbGames[3].dbTags[3].tag = "tactical";
    	dbGames[3].dbTags[4].tag = "relax";

    	dbGames[4].nam = "First Person Painter";
    	dbGames[4].rating = 7.7f;
    	dbGames[4].dbTags[0].tag = "frantic";
    	dbGames[4].dbTags[1].tag = "creative";
    	dbGames[4].dbTags[2].tag = "happy";
    	dbGames[4].dbTags[3].tag = "bright";
    	dbGames[4].dbTags[4].tag = "engaging";

    	dbGames[5].nam = "2 Sisters";
    	dbGames[5].rating = 8.5f;
    	dbGames[5].dbTags[0].tag = "online multiplayer";
    	dbGames[5].dbTags[1].tag = "local multiplayer";
    	dbGames[5].dbTags[2].tag = "hero";
    	dbGames[5].dbTags[3].tag = "story";
    	dbGames[5].dbTags[4].tag = "team";

    	dbGames[6].nam = "Fantasy Mostly Dark";
    	dbGames[6].rating = 7.67f;
    	dbGames[6].dbTags[0].tag = "dark dungeon";
    	dbGames[6].dbTags[1].tag = "solo";
    	dbGames[6].dbTags[2].tag = "relax";
    	dbGames[6].dbTags[3].tag = "story";
    	dbGames[6].dbTags[4].tag = "engaging";

    	dbGames[7].nam = "Balloon Pop 12";
    	dbGames[7].rating = 8f;
    	dbGames[7].dbTags[0].tag = "bright";
    	dbGames[7].dbTags[1].tag = "happy";
    	dbGames[7].dbTags[2].tag = "relax";
    	dbGames[7].dbTags[3].tag = "casual";

    	dbGames[8].nam = "World Builder";
    	dbGames[8].rating = 8.3f;
    	dbGames[8].dbTags[0].tag = "realistic";
    	dbGames[8].dbTags[1].tag = "casual";

    	dbGames[9].nam = "Universe of Heroes";
    	dbGames[9].rating = 7.1f;
    	dbGames[9].dbTags[0].tag = "hero";
    	dbGames[9].dbTags[1].tag = "frantic";
    	dbGames[9].dbTags[2].tag = "relax";
}
}
