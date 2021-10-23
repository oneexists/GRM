package edu.metrostate.ics370.grm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics370.grm.model.User;

class UserTest {

	@Test
	void testUser() {
		var newUser = new User();
		assertNull(newUser.getUsername());
	}

}
