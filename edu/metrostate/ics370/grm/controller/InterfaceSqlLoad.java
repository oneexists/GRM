package edu.metrostate.ics370.grm.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.metrostate.ics370.grm.model.GameTag;
import edu.metrostate.ics370.grm.model.Question;
import edu.metrostate.ics370.grm.model.QuestionChoice;


/**
 * @author skylar
 */
public abstract class InterfaceSqlLoad {	
	/**
	 * Returns an array of the questions 
	 * 
	 * @return questionArray with all of the questions
	 */
	public static Question[] getQuestions() {
		Question[] questionArray = null;
		String sql = "SELECT question_prompt, choice_text, tag_name "
				+ "FROM Question, SelectChoices, Choice, ChoiceTags, GameTag "
				+ "WHERE Question.question_id = SelectChoices.question_id "
				+ "AND SelectChoices.choice_id = Choice.choice_id "
				+ "AND Choice.choice_id = ChoiceTags.choice_id "
				+ "AND ChoiceTags.tag_id = GameTag.tag_id";
		try (	Statement stmt = Connector.getInstance().getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			ArrayList<Question> questions = new ArrayList<Question>();
			while (rs.next()) {
				String prompt = rs.getString("question_prompt");
				ArrayList<QuestionChoice> choices = new ArrayList<QuestionChoice>();
				while (prompt == rs.getString("question_prompt")) {
					String choice = rs.getString("choice_text");
					ArrayList<GameTag> tags = new ArrayList<GameTag>();
					while (choice == rs.getString("choice_text")) {
						String tag = rs.getString("tag_name");						
						tags.add(new GameTag(tag));
					}
					GameTag[] tagArray = tags.toArray(new GameTag[tags.size()]);
					choices.add(new QuestionChoice(choice, tagArray));
				}
				QuestionChoice[] choiceArray = choices.toArray(new QuestionChoice[choices.size()]);
				questions.add(new Question(prompt, choiceArray));
			}
			questionArray = questions.toArray(new Question[questions.size()]);
		} catch (SQLException e) {
			
		}
		Question[] questionSet =  prepareQuestions(questionArray);
		return questionSet;
	}

	/**
	 * @param questionArray array of the questions with all choices
	 * @return qSet array of questions with three choices each
	 */
	private static Question[] prepareQuestions(Question[] questionArray) {
		ArrayList<Question> qSet = new ArrayList<Question>();
		for (Question q : questionArray) {
			for (int i=0; i<q.getChoices().length-2; i=i+3) {
				QuestionChoice[] choices = { q.getChoices()[i/3], q.getChoices()[(i+1)/3], q.getChoices()[(i+2)/3] };
				Question newQuestion = new Question(q.getPrompt(), choices);
				qSet.add(newQuestion);
				
			}
		}
		return qSet.toArray(new Question[qSet.size()]);
	}
}
