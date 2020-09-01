package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Test for the IssueReader class
 * 
 * @author Anton
 */
public class IssueReaderTest {

	/**
	 * Testing valid text files
	 */
	@Test
	public void testValidFiles() {
		ArrayList<String> note1 = new ArrayList<String>();
		note1.add("Note 1");
		ArrayList<String> note2 = new ArrayList<String>();
		note2.add("Note 1");
		note2.add("Note 2\nthat goes on a new line");
		ArrayList<String> note3 = new ArrayList<String>();
		note3.add("Note 1");
		note3.add("Note 2");
		note3.add("Note 3");
		ArrayList<String> note4 = new ArrayList<String>();
		note4.add("Note 1");
		note4.add("Note 2\nthat goes on a new line");
		note4.add("Note 3");
		note4.add("Note 4");
		ArrayList<String> note6 = new ArrayList<String>();
		note6.add("Note 1\nthat goes on a new line");
		note6.add("Note 2");
		note6.add("Note 3");
		note6.add("Note 4");
		note6.add("Note 5");
		note6.add("Note 6");
		List<Issue> expIssueList = new ArrayList<Issue>();
		expIssueList.add(new Issue(1, "New", "Enhancement", "Issue description", "", false, "", note1));
		expIssueList.add(new Issue(2, "Confirmed", "Bug", "Issue description", "", true, "", note2));
		expIssueList.add(new Issue(3, "Working", "Bug", "Issue description", "owner", true, "", note3));
		expIssueList.add(new Issue(4, "Verifying", "Enhancement", "Issue description", "owner", false, "Fixed", note4));
		expIssueList.add(new Issue(6, "Closed", "Enhancement", "Issue description", "owner", false, "WontFix", note6));

		List<Issue> actIssueList = new ArrayList<Issue>();
		actIssueList = IssueReader.readIssuesFromFile("test-files/issue1.txt");
		assertEquals(expIssueList.toString(), actIssueList.toString());
	}

	/**
	 * Testing illegal files to check for an exception being thrown
	 */
	@Test
	public void testIllegalFiles() {
		try {
			IssueReader.readIssuesFromFile("test-files/issue2.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue3.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue4.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue5.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue6.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue7.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue8.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue9.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue10.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue11.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue12.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue13.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue14.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue15.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue16.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
		try {
			IssueReader.readIssuesFromFile("test-files/issue17.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file", e.getMessage());
		}
	}

}
