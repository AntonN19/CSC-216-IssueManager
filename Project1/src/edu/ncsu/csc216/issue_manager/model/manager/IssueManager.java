/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.*;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.io.IssueWriter;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Class made create and modify multiple issue lists
 * 
 * @author Anton
 */
public class IssueManager {

	/** The list of issue lists */
	public IssueList issueList;

	/** Instance variable for IssueManager */
	private static IssueManager singleton = null;

	/**
	 * The instance method which is used in the GUI
	 * 
	 * @return The IssueManager instance
	 */
	public static IssueManager getInstance() {
		if (singleton == null) {
			singleton = new IssueManager();
		}
		return singleton;
	}

	/**
	 * Method made to save issues to a file
	 * 
	 * @param filename The name of the file to be saved to
	 */
	public void saveIssuesToFile(String filename) {
		List<Issue> issuesToWrite = issueList.getIssues();
		IssueWriter.writeIssuesToFile(filename, issuesToWrite);
	}

	/**
	 * Method made to load issues from a file.
	 * 
	 * @param fileName The name of the file containing the issue list
	 */
	public void loadIssuesFromFile(String fileName) {
		IssueList issueL = new IssueList();
		issueL.addIssues(IssueReader.readIssuesFromFile(fileName));
		this.issueList = issueL;
	}

	/**
	 * Method used to create a new IssueList
	 */
	public void createNewIssueList() {
		this.issueList = new IssueList();
	}

	/**
	 * Method used to convert the IssueList to a 2d array.
	 * 
	 * @return The IssueList as an Array
	 */
	public Object[][] getIssueListAsArray() {
		if(issueList == null) {
			issueList = new IssueList();
		}
		Object[][] retArr = new Object[issueList.getIssues().size()][4];
		for (int i = 0; i < issueList.getIssues().size(); i++) {
			Issue issue = issueList.getIssues().get(i);
			for (int j = 0; j < 4; j++) {
				if (j == 0) {
					retArr[i][j] = issue.getIssueId();
				} else if (j == 1) {
					retArr[i][j] = issue.getState();
				} else if (j == 2) {
					retArr[i][j] = issue.getIssueType();
				} else if (j == 3) {
					retArr[i][j] = issue.getSummary();
				}
			}
		}
		return retArr;
	}

	/**
	 * Method used to convert the IssueList to a 2d list while sorting it by issue
	 * type.
	 * 
	 * @param issueType The type of issue being looked for
	 * @return the Issue List as an array sorted by Issue type
	 */
	public Object[][] getIssueListAsArrayByIssueType(String issueType) {
		if (issueType == null) {
			throw new IllegalArgumentException("Null Issue Type");
		}
		if(issueList == null) {
			issueList = new IssueList();
		}
		int size = 0;
		for (int i = 0; i < issueList.getIssues().size(); i++) {
			if (issueList.getIssues().get(i).getIssueType() == issueType) {
				size++;
			}
		}
		Object[][] retArr = new Object[size][4];
		
		for (int i = 0; i < size; i++) {
			Issue issue = issueList.getIssues().get(i);
			if (issue.getIssueType() == issueType) {
				for (int j = 0; j < 4; j++) {
					if (j == 0) {
						retArr[i][j] = issue.getIssueId();
					} else if (j == 1) {
						retArr[i][j] = issue.getState();
					} else if (j == 2) {
						retArr[i][j] = issue.getIssueType();
					} else if (j == 3) {
						retArr[i][j] = issue.getSummary();
					}
				}
			}
		}
		return retArr;
	}

	/**
	 * Method used to find an issue by looking at the id.
	 * 
	 * @param id The id to be searched for
	 * @return The issue with the corresponding id, null if nothing is found
	 */
	public Issue getIssueById(int id) {
		if(issueList == null) {
			issueList = new IssueList();
		}
		return this.issueList.getIssueById(id);
	}

	/**
	 * Method used to execute a command on a certain Issue in the list
	 * 
	 * @param id The id of the issue that the command will be applied to
	 * @param c  The Command to be executed
	 */
	public void executeCommand(int id, Command c) {
		if(issueList == null) {
			issueList = new IssueList();
		}
		this.issueList.executeCommand(id, c);
	}

	/**
	 * Method used to delete the issue with the certain id
	 * 
	 * @param id The id of the issue to be deleted
	 */
	public void deleteIssueById(int id) {
		if(issueList == null) {
			issueList = new IssueList();
		}
		this.issueList.deleteIssueById(id);
	}

	/**
	 * Method used to add issue to the list
	 * 
	 * @param issue   The issue type
	 * @param summary Summary of the issue
	 * @param note    Notes that go along with the issue
	 */
	public void addIssueToList(IssueType issue, String summary, String note) {
		if(issueList == null) {
			issueList = new IssueList();
		}
		this.issueList.addIssue(issue, summary, note);
	}
}
