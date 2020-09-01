/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Test class for the IssueList Class
 * 
 * @author Anton
 */
public class IssueListTest {

	/**
	 * Test for the issue list class
	 */
	@Test
	public void testIssueList() {
		Issue.setCounter(0);
		List<Issue> expIssueList = new ArrayList<Issue>();
		Issue bug = new Issue(IssueType.BUG, "B Summary", "note");
		expIssueList.add(bug);
		
		Issue.setCounter(0);
		IssueList testList = new IssueList();
		testList.addIssue(IssueType.BUG, "B Summary", "note");
		testList.addIssue(IssueType.ENHANCEMENT, "E Summary", "note");
		
		testList.deleteIssueById(0);
		testList.deleteIssueById(0);
		Issue.setCounter(0);
		
		testList.addIssues(expIssueList);
		
		List<Issue> returnedIssueList;
		returnedIssueList = testList.getIssuesByType("Bug");
		assertEquals(expIssueList.toString(), returnedIssueList.toString());
		testList.deleteIssueById(0);
		testList.deleteIssueById(0);
		testList = new IssueList();
		testList.addIssue(IssueType.BUG, "B Summary", "note");
		
		Command c = new Command(CommandValue.CONFIRM, "", null, "note 1");
		testList.executeCommand(0, c);
		bug.update(c);
		Issue retIssue = testList.getIssueById(0);
		assertEquals(bug.toString(), retIssue.toString());
		
	}

}
