package edu.metrostate.ics370.grm.questionaire;


public class User
{
    public SubGame[] dbGamesWishlist = new SubGame[100];
    public SubGame[] dbGamesHatelist = new SubGame[100];
    public SubTag[] dbTags = new SubTag[100];
    public int numTagsUsed;
    public int numWishlist;
    public int numHatelist;
    
    
    public User()
    {
    	for (int i = 0; i < dbGamesWishlist.length; i++)
    	{
    		dbGamesWishlist[i] = new SubGame();
    		dbGamesHatelist[i] = new SubGame();
    		dbTags[i] = new SubTag();
    	}
    }


    public void add_choice(String ttag, int val)
    {
        boolean included = false;
        for (int x = 0; x < dbTags.length; x++)
        {
            if (dbTags[x].tag == ttag)
            {
                included = true;
                dbTags[x].val += val;
                break;
            }
        }

        if (!included)
        {
            var ntag = new SubTag();
            ntag.tag = ttag;
            ntag.val = val;
            dbTags[numTagsUsed] = ntag;
            numTagsUsed++;
            //dbTags.add(ntag);
        }
    }
}
