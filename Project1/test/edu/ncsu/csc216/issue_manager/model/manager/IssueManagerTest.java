/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Test for the IsseuManager Class
 * 
 * @author Anton
 */
public class IssueManagerTest {

	/**
	 * Test case for the IssueManager class
	 */
	@Test
	public void issueManagerTest() {
		IssueManager manager;		
		manager = IssueManager.getInstance();
		manager.createNewIssueList();
		manager.addIssueToList(IssueType.BUG, "summary", "note");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary", "note");
		try {
			manager.getIssueListAsArrayByIssueType(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Null Issue Type", e.getMessage());
		}
		Object[][] retArr = manager.getIssueListAsArrayByIssueType("Bug");
		assertEquals(1, retArr.length);

		retArr = manager.getIssueListAsArray();
		assertEquals(2, retArr.length);
		assertEquals(0, retArr[0][0]);
		assertEquals("New", retArr[0][1]);
		assertEquals("Bug", retArr[0][2]);
		assertEquals("summary", retArr[0][3]);

		Issue.setCounter(0);
		Issue retIssue = manager.getIssueById(0);
		Issue expIssue = new Issue(IssueType.BUG, "summary", "note");
		assertEquals(expIssue.toString(), retIssue.toString());
		
		manager.deleteIssueById(0);
		
		manager.saveIssuesToFile("test-files/issueMang.txt");
	}

}
