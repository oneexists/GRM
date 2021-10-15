package game_recommendation_test;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;


public class mainWindow
{
	//private JFrame frame;


	public static void main(String[] args)
	{
		RecommendationManager rms = new RecommendationManager();
		
		rms.initializeQuestions(); //Makes the database of questions//
		rms.setQuestion(); //Sets the first question//
	}


	//public mainWindow()
	//{
		//initialize();
	//}


	//private void initialize()
	//{
		//frame = new JFrame();
		//frame.setBounds(100, 100, 878, 612);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//}
}
