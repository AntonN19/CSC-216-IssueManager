/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.issue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Test for the Issue class
 * 
 * @author Anton
 */
public class IssueTest {

	/**
	 * Test setters and getters
	 */
	@Test
	public void testGetSet() {
		String state = "New";
		IssueType issueTypeI = IssueType.ENHANCEMENT;
		String issueType = "Bug";
		String summary = "Enhancement Summary";
		String owner = "anikuls";
		boolean confirmed = true;
		String resolution = "Duplicate";
		String note = "note";
		ArrayList<String> notes = new ArrayList<String>();
		notes.add(note);
		notes.add(note);
		ArrayList<String> expNotes = new ArrayList<String>();
		expNotes.add(note);
		Issue testIssue = new Issue(issueTypeI, summary, note);
		assertEquals(testIssue.getSummary(), "Enhancement Summary");
		assertEquals(testIssue.getNotes(), expNotes);
		assertEquals("New", testIssue.getState());
		assertNull(testIssue.getOwner());
		new Issue(3, "New", "Bug", "summary", "", false, "", notes);
		assertNull(testIssue.getResolution());
		assertFalse(testIssue.isConfirmed());

		try {
			testIssue = new Issue(-1, state, issueType, summary, owner, confirmed, resolution, notes);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid issue id", e.getMessage());
		}
	}

	/**
	 * Test FSM in the Issue class
	 */
	@Test
	public void issueFsmTest() {
		IssueType issueTypeI = IssueType.ENHANCEMENT;
		IssueType issueType = IssueType.BUG;
		String summary = "Enhancement Summary";
		String note = "note";
		List<String> notes = new ArrayList<String>();
		notes.add(note);
		notes.add(note);
		Command c = new Command(CommandValue.ASSIGN, "anikul", null, "assigned to anikul");
		Issue newIssue = new Issue(issueTypeI, summary, note);
		newIssue.update(c);
		assertEquals(newIssue.getState(), "Working");
		c = new Command(CommandValue.RESOLVE, "anikul", Resolution.FIXED, "fixed");
		newIssue.update(c);
		assertEquals("Verifying", newIssue.getState());
		c = new Command(CommandValue.VERIFY, "anikul", null, "verified");
		newIssue.update(c);
		assertEquals("Closed", newIssue.getState());
		c = new Command(CommandValue.REOPEN, "anikul", null, "Reopened");
		newIssue.update(c);
		assertEquals("Working", newIssue.getState());
		try {
			newIssue.update(c);
		} catch (UnsupportedOperationException e) {
			assertEquals("Working", newIssue.getState());
		}
		try {
			c = new Command(CommandValue.RESOLVE, "anikul", Resolution.WORKSFORME, "works for me");
			newIssue.update(c);
		} catch (UnsupportedOperationException e) {
			assertEquals("Working", newIssue.getState());
		}
		c = new Command(CommandValue.RESOLVE, "anikul", Resolution.DUPLICATE, "duplicate");
		newIssue.update(c);
		assertEquals("Closed", newIssue.getState());

		newIssue = new Issue(issueType, summary, note);
		c = new Command(CommandValue.CONFIRM, null, null, "confirmed");
		newIssue.update(c);
		assertEquals("Confirmed", newIssue.getState());
		c = new Command(CommandValue.RESOLVE, null, Resolution.WONTFIX, "wont fix");
		newIssue.update(c);
		assertEquals("Closed", newIssue.getState());
		c = new Command(CommandValue.REOPEN, null, null, "Reopen");
		newIssue.update(c);
		assertEquals("Confirmed", newIssue.getState());
		c = new Command(CommandValue.ASSIGN, "anikul", null, "Reopen");
		newIssue.update(c);
		assertEquals("Working", newIssue.getState());
	}

	/**
	 * Second FSM Test
	 */
	@Test
	public void issueFsmTest2() {
		IssueType issueTypeI = IssueType.ENHANCEMENT;
		String summary = "Enhancement Summary";
		String note = "note";
		List<String> notes = new ArrayList<String>();
		notes.add(note);
		notes.add(note);
		Command c = new Command(CommandValue.ASSIGN, "anikul", null, "assigned to anikul");
		Issue newIssue = new Issue(issueTypeI, summary, note);
		newIssue.update(c);
		assertEquals(newIssue.getState(), "Working");
		c = new Command(CommandValue.RESOLVE, "anikul", Resolution.FIXED, "fixed");
		newIssue.update(c);
		assertEquals("Verifying", newIssue.getState());
		c = new Command(CommandValue.REOPEN, "anikul", null, "verified");
		newIssue.update(c);
		assertEquals("Working", newIssue.getState());
	}

	/**
	 * Third FSM Test
	 */
	@Test
	public void issueFsmTest3() {
		IssueType issueTypeI = IssueType.ENHANCEMENT;
		IssueType issueType = IssueType.BUG;
		String summary = "Enhancement Summary";
		String note = "note";
		List<String> notes = new ArrayList<String>();
		notes.add(note);
		notes.add(note);
		Command c = new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, "note");
		Issue newIssue = new Issue(issueTypeI, summary, note);
		newIssue.update(c);
		assertEquals(newIssue.getState(), "Closed");

		c = new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, "note");
		newIssue = new Issue(issueType, summary, note);
		newIssue.update(c);
		assertEquals(newIssue.getState(), "Closed");

		c = new Command(CommandValue.REOPEN, "", null, "note");
		newIssue.update(c);
		assertEquals(newIssue.getState(), "New");
	}

}
