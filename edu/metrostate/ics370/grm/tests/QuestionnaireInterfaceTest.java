package edu.metrostate.ics370.grm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics370.grm.controller.QuestionnaireInterface;
import edu.metrostate.ics370.grm.model.Question;

class QuestionnaireInterfaceTest {

	@Test
	void testAddHatelist() {
		fail("Not yet implemented");
	}

	@Test
	void testAddWishlist() {
		fail("Not yet implemented");
	}

	@Test
	void testGetGames() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTags() {
		fail("Not yet implemented");
	}

	@Test
	void testGetQuestions() {
		Question[] questions = QuestionnaireInterface.getQuestions();
		assertEquals(40, questions.length);
	}

	@Test
	void testRemoveWishlist() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveHatelist() {
		fail("Not yet implemented");
	}

	@Test
	void testAddPersonalTag() {
		fail("Not yet implemented");
	}

}
