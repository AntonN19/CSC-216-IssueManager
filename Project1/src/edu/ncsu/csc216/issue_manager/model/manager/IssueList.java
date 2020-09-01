/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.*;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Class made to manage the list of issues during execution
 * 
 * @author Anton
 */
public class IssueList {
	/** The list of issues */
	private List<Issue> issues;

	/**
	 * Constructor for the IssueList
	 */
	public IssueList() {
		this.issues = new ArrayList<Issue>();
		Issue.setCounter(0);
	}

	/**
	 * Method used to add issue to the list
	 * 
	 * @param issue   The issue type
	 * @param summary Summary of the issue
	 * @param note    Notes that go along with the issue
	 * @return the id of the issue that just got added to the list
	 */
	public int addIssue(IssueType issue, String summary, String note) {
		Issue newIss = new Issue(issue, summary, note);
		issues.add(newIss);
		return newIss.getIssueId();
	}

	/**
	 * Method used to add multiple issues at a time to the issue list
	 * 
	 * @param issues The issues that need to be added as a List
	 */
	public void addIssues(List<Issue> issues) {
		int maxId = 0;
		this.issues.clear();
		for (int i = 0; i < issues.size(); i++) {
			if(maxId < issues.get(i).getIssueId()) {
				maxId = issues.get(i).getIssueId();
			}
			this.issues.add(issues.get(i));
		}
		Issue.setCounter(maxId + 1);
	}

	/**
	 * Method made to return a current list of issues
	 * 
	 * @return the issues as an ArrayList
	 */
	public List<Issue> getIssues() {
		return this.issues;
	}

	/**
	 * Method made to only get certain type of issues from the list
	 * 
	 * @param type The type of issues that the list is being searched for
	 * @return the issues of the type passed to this method
	 */
	public List<Issue> getIssuesByType(String type) {
		if(type == null) {
			throw new IllegalArgumentException();
		}
		String issueType;
		ArrayList<Issue> retIssues = new ArrayList<Issue>();
		for (int i = 0; i < issues.size(); i++) {
			issueType = issues.get(i).getIssueType();
			if (issueType.equals(type)) {
				retIssues.add(issues.get(i));
			}
		}
		return retIssues;
	}

	/**
	 * Method used to find an issue on the issue list by issue id
	 * 
	 * @param id The issue id to be searched for
	 * @return the Issue found null if nothing was found
	 */
	public Issue getIssueById(int id) {
		for (int i = 0; i < issues.size(); i++) {
			if (issues.get(i).getIssueId() == id) {
				return issues.get(i);
			}
		}
		return null;
	}

	/**
	 * Method used to execute a command on a certain issue in the list
	 * 
	 * @param id The id of the issue to execute the command on
	 * @param c  The command to be executed
	 */
	public void executeCommand(int id, Command c) {
		if(id >= 0) {
			Issue iss = this.getIssueById(id);
			int index = issues.indexOf(iss);
			if(index >= 0) {
				issues.get(index).update(c);
			}
		}
	}
	

	/**
	 * Method used to find an issue by id and delete it
	 *
	 * @param id The id of the issue to be deleted
	 */
	public void deleteIssueById(int id) {
		if(id >= 0) {
			Issue iss = this.getIssueById(id);
			int index = issues.indexOf(iss);
			if(index >= 0) {
				issues.remove(index);
			}
		}
	}
}
