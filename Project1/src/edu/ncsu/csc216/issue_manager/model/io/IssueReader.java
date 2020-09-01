/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Class made to load the list of issues from a text file.
 * 
 * @author Anton
 */
public class IssueReader {

	/**
	 * The method used to load the list of issues from a text file and return them
	 * as an List of Issues
	 * 
	 * @param filename The name of the file being read from
	 * @return The List of Issues
	 */
	public static List<Issue> readIssuesFromFile(String filename) {
		try {
			Scanner fileReader = new Scanner(new FileInputStream(filename));
			String fileContents = fileReader.nextLine();
			List<Issue> issues = new ArrayList<Issue>();
			while (fileReader.hasNextLine()) {
				fileContents += "\n" + fileReader.nextLine();
			}
			Scanner stringScan = new Scanner(fileContents);
			stringScan.useDelimiter("\\r?\\n[*]");
			while (stringScan.hasNext()) {
				issues.add(processIssue(stringScan.next()));
			}
			stringScan.close();
			fileReader.close();
			return issues;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file");
		}
	}

	/**
	 * A private helper method used to create an Issue object that will be added to
	 * the List of Issues in the method above
	 * 
	 * @param line The line of text to be added
	 * @return the created Issue object
	 */
	private static Issue processIssue(String line) {
		if (line.substring(0, 1).equals("*")) {
			line = line.substring(1);
		}
		Scanner lineScan = new Scanner(line);
		int counter = 0;
		int id = 0;
		String state = null;
		String issueType = null;
		String summary = null;
		String owner = null;
		String strConfirmed;
		boolean confirmed = false;
		String resolution = null;
		ArrayList<String> notes = new ArrayList<String>();
		lineScan.useDelimiter("\\+");
		while (lineScan.hasNext()) {
			switch (counter) {
			case 0:
				String s = lineScan.next();
				id = Integer.parseInt(s);
				counter++;
				break;
			case 1:
				state = lineScan.next();
				counter++;
				break;
			case 2:
				issueType = lineScan.next();
				counter++;
				break;
			case 3:
				summary = lineScan.next();
				counter++;
				break;
			case 4:
				owner = lineScan.next();
				counter++;
				break;
			case 5:
				strConfirmed = lineScan.next();
				if (Objects.equals(strConfirmed, "true")) {
					confirmed = true;
				}
				counter++;
				break;
			case 6:
				lineScan.useDelimiter("\\r?\\n[-]");
				resolution = lineScan.next();
				if (resolution.equals("+")) {
					resolution = "";
				} else {
					resolution = resolution.substring(1);
				}
				while (lineScan.hasNext()) {
					notes.add(lineScan.next());
				}
				counter++;
				break;
			default:
				break;
			}
		}
		lineScan.close();
		Issue retIssue = new Issue(id, state, issueType, summary, owner, confirmed, resolution, notes);
		return retIssue;
	}
}
