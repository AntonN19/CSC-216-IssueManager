/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Test for the IssueWriter Class
 * 
 * @author Anton
 */
public class IssueWriterTest {

	/**
	 * Tests writing an list of issues to a file.
	 */
	@Test
	public void testWritingToFile() {
		// Variables necessary for testing declaration
		ArrayList<String> note1 = new ArrayList<String>();
		note1.add("note 1");
		Issue.setCounter(0);
		Issue newEnhancement = new Issue(IssueType.ENHANCEMENT, "Issue description", "note");
		Issue closedBug = new Issue(4, "Closed", "Bug", "Issue description", "", false, "WontFix", note1);
		Issue confirmedBug = new Issue(5, "Confirmed", "Bug", "Issue description", "null", true, "", note1);
		Issue.setCounter(0);
		Issue newBug = new Issue(IssueType.BUG, "Issue description", "note 1");
		Issue verifyingBug = new Issue(1, "Verifying", "Bug", "Issue description", "owner2", true, "Fixed", note1);
		Issue workingBug = new Issue(7, "Working", "Bug", "Issue description", "owner", true, "", note1);

		// testing
		ArrayList<Issue> testIssue = new ArrayList<Issue>();
		// new Enhancement
		testIssue.add(0, newEnhancement);
		IssueWriter.writeIssuesToFile("test-files/act_enhancement_new.txt", testIssue);
		checkFiles("test-files/exp_enhancement_new.txt", "test-files/act_enhancement_new.txt");
		// closed bug
		testIssue.add(0, closedBug);
		IssueWriter.writeIssuesToFile("test-files/act_issue_closed.txt", testIssue);
		checkFiles("test-files/exp_issue_closed.txt", "test-files/act_issue_closed.txt");
		// confirmed bug
		testIssue.add(0, confirmedBug);
		IssueWriter.writeIssuesToFile("test-files/act_issue_confirmed.txt", testIssue);
		checkFiles("test-files/exp_issue_confirmed.txt", "test-files/act_issue_confirmed.txt");
		// new bug
		testIssue.add(0, newBug);
		IssueWriter.writeIssuesToFile("test-files/act_issue_new.txt", testIssue);
		checkFiles("test-files/exp_issue_new.txt", "test-files/act_issue_new.txt");
		// verifying bug
		testIssue.add(0, verifyingBug);
		IssueWriter.writeIssuesToFile("test-files/act_issue_verifying.txt", testIssue);
		checkFiles("test-files/exp_issue_verifying.txt", "test-files/act_issue_verifying.txt");
		// working bug
		testIssue.add(0, workingBug);
		IssueWriter.writeIssuesToFile("test-files/act_issue_working.txt", testIssue);
		checkFiles("test-files/exp_issue_working.txt", "test-files/act_issue_working.txt");

		try {
			IssueWriter.writeIssuesToFile("test-files/act_issue_working.png", testIssue);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to save file", e.getMessage());
		}

	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expF expected output
	 * @param actF actual output
	 */
	private void checkFiles(String expF, String actF) {
		try {
			Scanner expScanner = new Scanner(new File(expF));
			Scanner actScanner = new Scanner(new File(actF));
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
