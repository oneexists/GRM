package edu.metrostate.ics370.grm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics370.grm.controller.LoadData;
import edu.metrostate.ics370.grm.model.GameTag;

class LoadDataTest {

	@Test
	void testGetTags() {
		GameTag[] tags = LoadData.getTags();
		
		assertEquals(tags.length, 30);
	}

}
