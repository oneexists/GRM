package edu.metrostate.ics370.grm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics370.grm.model.User;

class UserTest {

	@Test
	void testValidation() {
		var user = new User("first", "last", "user", "pass");
		assertTrue(user.validate("user", "pass"));
		assertFalse(user.validate("user", "notpass"));
	}

}
