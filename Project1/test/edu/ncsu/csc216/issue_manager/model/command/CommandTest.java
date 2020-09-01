package edu.ncsu.csc216.issue_manager.model.command;

import static org.junit.Assert.*;

import org.junit.*;

import edu.ncsu.csc216.issue_manager.model.command.Command.*;

/**
 * Test designed for the Command class
 * 
 * @author Anton
 */
public class CommandTest {

	/**
	 * Test made to test the constructor
	 */
	@Test
	public void testConstructor() {
		CommandValue commandValue = CommandValue.ASSIGN;
		Resolution resolutionValue = Resolution.DUPLICATE;
		try {
			new Command(commandValue, "", resolutionValue, "notes");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "no owner with ASSIGN");
		}
		try {
			new Command(commandValue, null, resolutionValue, "notes");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "no owner with ASSIGN");
		}
		commandValue = null;
		resolutionValue = Resolution.DUPLICATE;
		try {
			new Command(commandValue, "anikuls", resolutionValue, "notes");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Null CommandValue");
		}
		commandValue = CommandValue.RESOLVE;
		resolutionValue = null;
		try {
			new Command(commandValue, "anikuls", resolutionValue, "notes");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Resolved with no resolution");
		}
		resolutionValue = Resolution.WORKSFORME;
		try {
			new Command(commandValue, "anikuls", resolutionValue, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "null note");
		}
	}

	/**
	 * Testing the Command class getters.
	 */
	@Test
	public void testGetters() {
		CommandValue commandValue = CommandValue.ASSIGN;
		Resolution resolutionValue = Resolution.FIXED;
		try {
			Command c = new Command(commandValue, "anikuls", resolutionValue, "notes");
			assertEquals(c.getCommand(), CommandValue.ASSIGN);
			assertEquals(c.getNote(), "notes");
			assertEquals(c.getOwnerId(), "anikuls");
			assertEquals(c.getResolution(), Resolution.FIXED);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

}
