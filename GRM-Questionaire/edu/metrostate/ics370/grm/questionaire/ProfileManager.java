package edu.metrostate.ics370.grm.questionaire;


public class ProfileManager
{
    //public User user;
    //public InputField if_search;
    //public GameObject prefab_gui_wishlist_game;
    public GuiWishlist[] dbWishlist = new GuiWishlist[100];
    
    
    public ProfileManager()
    {
    	for (int i = 0; i < dbWishlist.length; i++)
    	{
    		dbWishlist[i] = new GuiWishlist();
    	}
    }


    /*
    public void update_if()
    {
        for (int y = 0; y < dbWishlist.Count; y++)
        	dbWishlist[y].gameObject.SetActive(true);

        var search = if_search.text.ToLower();
        for (int x = 0; x < search.Length; x++)
        {
            for (int y = 0; y < dbWishlist.Count; y++)
            {
                var lnam = dbWishlist[y].game_s.nam.ToLower();
                if (dbWishlist[y].game_s.nam.Length <= x || search[x] != lnam[x])
                	dbWishlist[y].gameObject.SetActive(false);

                //!string.Equals(search[x], db_gui_morphs[y].morph_s.nam[x], System.StringComparison.OrdinalIgnoreCase);
            }
        }
    }

    
    void OnEnable()
    {
        for (int x = 0; x < dbWishlist.Count; x++)
            DestroyImmediate(dbWishlist[x].gameObject);
        dbWishlist.Clear();

        for (int x = 0; x < user_s.db_games_wishlist.Count; x++)
        {
            var tgo = (GameObject) Instantiate(prefab_gui_wishlist_game, prefab_gui_wishlist_game.transform.position, prefab_gui_wishlist_game.transform.rotation, prefab_gui_wishlist_game.transform.parent);
            var gres = tgo.GetComponent<GuiWishlist>();
            gres.txt_nam.text = user_s.db_games_wishlist[x].nam;
            gres.game_s = user_s.db_games_wishlist[x];
            db_gwgames.Add(gres);
            tgo.SetActive(true);
        }
    }
    */
}
