package edu.metrostate.ics370.grm.view;
import edu.metrostate.ics370.grm.model.Game;


public class GuiResult
{
	//public String state;
    //public RectTransform rt_goal;
	//public Text txt_nam;
    public Game game;


    /*
    private void Update()
    {
        if (state == "moving")
        {
            var scale = transform.localScale.x;
            scale -= 0.003f;
            transform.localScale = new Vector3(scale, scale, 1);

            Vector3 vel = rt_goal.transform.position - transform.position;
            vel *= 2;
            transform.localPosition = Vector3.SmoothDamp(transform.localPosition, rt_goal.transform.position, ref vel, 1f);
        }
        else
        if (state == "deleting")
        {
            var scale = transform.localScale.x;
            scale -= 0.003f;
            transform.localScale = new Vector3(scale, scale, 1);
        }
    }
    */
}
