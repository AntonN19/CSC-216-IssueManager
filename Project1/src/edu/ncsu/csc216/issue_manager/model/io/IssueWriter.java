/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Class made to write a list of issues into a text file
 * 
 * @author Anton
 */
public class IssueWriter {

	/**
	 * Method used to write an List of Issues to the specified text file.
	 * 
	 * @param filename The name of the text file to be written to
	 * @param issues   The list of issues to be written to the text file.
	 * @throws IllegalArgumentException If any sort of error occurs
	 */
	public static void writeIssuesToFile(String filename, List<Issue> issues) {
		try {
			PrintStream fileWriter = new PrintStream(filename);
			for (int i = 0; i < issues.size(); i++) {
				fileWriter.print(issues.get(i).toString());
			}
			fileWriter.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
}
