/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * Class made to store all of the information about a single issue.
 * 
 * @author Anton
 */
public class Issue {

	/** Enhancement Issue type constant */
	public static final String I_ENHANCEMENT = "Enhancement";
	/** Bug Issue type constant */
	public static final String I_BUG = "Bug";
	/** id of the issue */
	private int issueId;
	/** The current state of this issue */
	private IssueState state;
	/** The type of this issue */
	private IssueType issueType;
	/** Summary of the issue */
	private String summary;
	/** Owner of the issue */
	private String owner;
	/** Whether or not the issue is confirmed */
	private boolean confirmed;
	/** The resolution type of the issue */
	private Resolution resolution;
	/** A list of notes for the issue */
	private ArrayList<String> notes;
	/** The new issue name constant */
	public static final String NEW_NAME = "New";
	/** The Working issue name constant */
	public static final String WORKING_NAME = "Working";
	/** The Confirmed issue name constant */
	public static final String CONFIRMED_NAME = "Confirmed";
	/** The Verifying issue name constant */
	public static final String VERIFYING_NAME = "Verifying";
	/** The Closed issue name constant */
	public static final String CLOSED_NAME = "Closed";
	/** Counter for the class */
	private static int counter = 0;

	/**
	 * Constructor for Issue when only the issue type, note, and summary is provided
	 * 
	 * @param issueType The type of issue
	 * @param summary   Summary about what the issue consists of
	 * @param note      notes about the issue
	 * @throws IllegalArgumentException when any of the values passed to it are
	 *                                  null.
	 */
	public Issue(IssueType issueType, String summary, String note) {
		if (issueType == null || summary == null || note == null || summary.equals("") || note.equals("")) {
			throw new IllegalArgumentException("Null value");
		}
		notes = new ArrayList<String>();
		this.issueId = counter;
		incrementCounter();
		this.state = new NewState();
		this.issueType = issueType;
		this.summary = summary;
		this.owner = null;
		this.confirmed = false;
		this.resolution = null;
		notes.add(note);
	}

	/**
	 * The full constructor for Issue
	 * 
	 * @param issueId    The id of the issue
	 * @param state      The current state of the issue
	 * @param issueType  the type of issue
	 * @param summary    The summary of issue
	 * @param owner      The issues owner
	 * @param confirmed  whether the issue is confirmed or not
	 * @param resolution The resolution state of the issue
	 * @param notes2     Issue notes as an List
	 */
	public Issue(int issueId, String state, String issueType, String summary, String owner, boolean confirmed,
			String resolution, ArrayList<String> notes2) {
		notes = new ArrayList<String>();
		if (issueType == null || summary == null || summary.equals("") || notes2 == null) {
			throw new IllegalArgumentException("Null value");
		}
		if (owner == null) {
			owner = "";
		}
		if (resolution == null) {
			resolution = "";
		}
		setIssueId(issueId);
		if (issueId > Issue.counter) {
			setCounter(issueId + 1);
		}
		if (notes2.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if ((owner.length() == 0) && (state.equals(VERIFYING_NAME) || state.equals(WORKING_NAME))) {
			throw new IllegalArgumentException();
		}
		if (resolution.length() > 1 && (!(state.equals(VERIFYING_NAME) || state.equals(CLOSED_NAME)))) {
			throw new IllegalArgumentException();
		}
		if (state.equals(CONFIRMED_NAME) && issueType.equals(I_ENHANCEMENT)) {
			throw new IllegalArgumentException();
		}
		if (state.equals(WORKING_NAME) && (owner.length() == 0 || owner == null)) {
			throw new IllegalArgumentException();
		}
		if (issueType.equals(I_ENHANCEMENT) && resolution.equals(Command.R_WORKSFORME)) {
			throw new IllegalArgumentException();
		}
		if (state.equals(WORKING_NAME) && issueType.equals(I_BUG) && !confirmed) {
			throw new IllegalArgumentException();
		}
		if (state.equals(VERIFYING_NAME) && !resolution.equals(Command.R_FIXED)) {
			throw new IllegalArgumentException();
		}
		if (state.equals(CLOSED_NAME) && resolution.length() == 0) {
			throw new IllegalArgumentException();
		}
		if (confirmed && issueType.equals(I_ENHANCEMENT)) {
			throw new IllegalArgumentException();
		}
		setState(state);
		setIssueType(issueType);
		setSummary(summary);
		setOwner(owner);
		setConfirmed(confirmed);
		setResolution(resolution);
		setNotes(notes2);
	}

	/**
	 * Method made to return the issueId of the Issue
	 * 
	 * @return the issueId
	 */
	public int getIssueId() {
		return issueId;
	}

	/**
	 * Method made to set the issueId of the Issue
	 * 
	 * @param issueId the issueId to set
	 */
	private void setIssueId(int issueId) {
		if (issueId >= 0) {
			this.issueId = issueId;
		} else {
			throw new IllegalArgumentException("Invalid issue id");
		}
	}

	/**
	 * Method used to get the Issue summary
	 * 
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Method used to change the Issue summary
	 * 
	 * @param summary the summary to set
	 */
	private void setSummary(String summary) {
		if (summary.length() > 0) {
			this.summary = summary;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method used to return the owner of the issue
	 * 
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Method used to change the owner of the issue
	 * 
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		if (owner.equals("")) {
			this.owner = null;
		} else {
			this.owner = owner;
		}

	}

	/**
	 * Method used to set the confirmed state of the issue to either true or false
	 * 
	 * @param confirmed the confirmed to set
	 */
	private void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * Method used to set the resolution state of this issue
	 * 
	 * @param resolution The resolution state to be set
	 */
	private void setResolution(String resolution) {
		if (resolution.equals(Command.R_DUPLICATE)) {
			this.resolution = Resolution.DUPLICATE;
		} else if (resolution.equals(Command.R_FIXED)) {
			this.resolution = Resolution.FIXED;
		} else if (resolution.equals(Command.R_WONTFIX)) {
			this.resolution = Resolution.WONTFIX;
		} else if (resolution.equals(Command.R_WORKSFORME)) {
			this.resolution = Resolution.WORKSFORME;
		} else {
			this.resolution = null;
		}
	}

	/**
	 * Method used to get the current state of this issues resolution
	 * 
	 * @return the resolution as a String
	 */
	public String getResolution() {
		if (this.resolution == Resolution.DUPLICATE) {
			return Command.R_DUPLICATE;
		} else if (this.resolution == Resolution.FIXED) {
			return Command.R_FIXED;
		} else if (this.resolution == Resolution.WONTFIX) {
			return Command.R_WONTFIX;
		} else if (this.resolution == Resolution.WORKSFORME) {
			return Command.R_WORKSFORME;
		}
		return null;
	}

	/**
	 * Method used to return the Issue notes
	 * 
	 * @return the notes
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}

	/**
	 * Method used to change the issue notes
	 * 
	 * @param notes the ArrayList of notes to add.
	 */
	private void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}

	/**
	 * Method used to change the State of the issue
	 * 
	 * @param state the state to change to
	 */
	private void setState(String state) {
		if (state.equals(NEW_NAME)) {
			this.state = new NewState();
		} else if (state.equals(VERIFYING_NAME)) {
			this.state = new VerifyingState();
		} else if (state.equals(WORKING_NAME)) {
			this.state = new WorkingState();
		} else if (state.equals(CONFIRMED_NAME)) {
			this.confirmed = true;
			this.state = new ConfirmedState();
		} else if (state.equals(CLOSED_NAME)) {
			this.state = new ClosedState();
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method uses to return the State of the issue
	 * 
	 * @return State of the issue as a String
	 */
	public String getState() {
		return this.state.getStateName();
	}

	/**
	 * Method used to change the type of the issue
	 * 
	 * @param issueType the issueType to change to
	 */
	private void setIssueType(String issueType) {
		if (issueType.equals(I_ENHANCEMENT)) {
			this.issueType = IssueType.ENHANCEMENT;
		} else if (issueType.equals(I_BUG)) {
			this.issueType = IssueType.BUG;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method uses to return the IssueType of the issue
	 * 
	 * @return the issue type as a String
	 */
	public String getIssueType() {
		if (issueType == IssueType.BUG) {
			return I_BUG;
		}
		return I_ENHANCEMENT;
	}

	/**
	 * Method used to return true or false base on whether the issue is confirmed or
	 * not
	 * 
	 * @return the confirmed
	 */
	public boolean isConfirmed() {
		return confirmed;
	}

	/**
	 * Method used to increase counter
	 */
	public static void incrementCounter() {
		counter++;
	}

	/**
	 * Method used to convert the List notes into a simple string and return it
	 * 
	 * @return the notes as a String
	 */
	public String getNotesString() {
		String retStr = "";
		for (int i = 0; i < notes.size(); i++) {
			retStr += "-" + notes.get(i) + "\n";
		}
		return retStr;
	}

	/**
	 * The method used to convert the information contained in the issue class to a
	 * String
	 * 
	 * @return The Issue class as a String
	 */
	@Override
	public String toString() {
		String ret;
		if (issueType == IssueType.BUG && resolution == null) {
			ret = "*" + this.getIssueId() + "+" + this.getState() + "+" + this.getIssueType() + "+" + this.getSummary()
					+ "+" + this.getOwner() + "+" + confirmed + "+\n";
			ret += getNotesString();
		} else {
			ret = "*" + this.getIssueId() + "+" + this.getState() + "+" + this.getIssueType() + "+" + this.getSummary()
					+ "+" + this.getOwner() + "+" + confirmed + "+" + this.getResolution() + "\n";
			ret += getNotesString();
		}
		return ret;
	}

	/**
	 * Method which drives the FSM by delegating the parameter passed to it to the
	 * current state
	 * 
	 * @param c The command being passed
	 */
	public void update(Command c) {
		state.updateState(c);
	}

	/**
	 * Method used to change the counter of this Issue class
	 * 
	 * @param count The value to be assigned to counter
	 */
	public static void setCounter(int count) {
		counter = count;
	}

	/**
	 * The enumeration of IssueType
	 * 
	 * @author Sarah Heckman
	 */
	public enum IssueType {
		/**
		 * Enhancement Issue Type
		 */
		ENHANCEMENT,
		/**
		 * Bug Issue type
		 */
		BUG;
	}

	/**
	 * Interface for states in the Issue State Pattern. All concrete issue states
	 * must implement the IssueState interface. The IssueState interface should be a
	 * private interface of the Issue class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private interface IssueState {

		/**
		 * Update the Issue based on the given Command. An UnsupportedOperationException
		 * is throw if the Command is not a valid action for the given state.
		 * 
		 * @param command Command describing the action that will update the Issue's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the given state.
		 */
		void updateState(Command command);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}

	/**
	 * Class made for the closed State of an issue
	 * 
	 * @author Anton
	 */
	public class ClosedState implements IssueState {

		/**
		 * Private constructor for ClosedState class
		 */
		private ClosedState() {

		}

		/**
		 * Method used to update the state of the Issue to closedState
		 * 
		 * @param c The Command being passed to the method
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.REOPEN) {
				if (owner != null && owner.length() != 0) {
					if (issueType == IssueType.BUG && confirmed) {
						throw new UnsupportedOperationException();
					} else {
						setState("Working");
						notes.add(c.getNote());
					}
				} else if (owner == null || owner.length() == 0) {
					if (issueType == IssueType.BUG && confirmed) {
						setState("Confirmed");
						notes.add(c.getNote());
					} else {
						setState("New");
						notes.add(c.getNote());
					}
				}
				resolution = null;
			} else {
				throw new UnsupportedOperationException();
			}
			resolution = c.getResolution();
		}

		/**
		 * Returns the State name of closedState
		 * 
		 * @return The State name
		 */
		@Override
		public String getStateName() {
			return CLOSED_NAME;
		}
	}

	/**
	 * Class representing the confirmed State in the Issue Manager FSM
	 * 
	 * @author Anton
	 */
	public class ConfirmedState implements IssueState {
		/**
		 * Private constructor for this class
		 */
		private ConfirmedState() {

		}

		/**
		 * Method used to update the state of the Issue
		 * 
		 * @param c The Command being passed to the method
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.ASSIGN && !(c.getOwnerId() == null || c.getOwnerId().length() == 0)) {
				notes.add(c.getNote());
				setState("Working");
			} else if (c.getCommand() == CommandValue.RESOLVE && c.getResolution() == Resolution.WONTFIX) {
				notes.add(c.getNote());
				resolution = c.getResolution();
				setState("Closed");
			} else {
				throw new UnsupportedOperationException();
			}
			owner = c.getOwnerId();
		}

		/**
		 * Returns the State name of ConfirmedState
		 * 
		 * @return The State name
		 */
		@Override
		public String getStateName() {
			return CONFIRMED_NAME;
		}
	}

	/**
	 * Class representing the New State in the Issue Manager FSM
	 * 
	 * @author Anton
	 */
	public class NewState implements IssueState {
		/**
		 * Private constructor for NewState class
		 */
		private NewState() {

		}

		/**
		 * Method used to update the state of the Issue to NewState
		 * 
		 * @param c The Command being passed to the method
		 * @throws UnsupportedOperationException if the owner id is null or left empty
		 */
		@Override
		public void updateState(Command c) {
			if (issueType == IssueType.ENHANCEMENT && c.getCommand() == CommandValue.ASSIGN) {
				if (c.getOwnerId() == null || c.getOwnerId().length() == 0) {
					throw new UnsupportedOperationException();
				} else {
					notes.add(c.getNote());
					setState("Working");
				}
			} else if (issueType == IssueType.BUG && c.getCommand() == CommandValue.CONFIRM) {
				notes.add(c.getNote());
				setState("Confirmed");
			} else if (c.getCommand() == CommandValue.RESOLVE) {
				if (issueType == IssueType.ENHANCEMENT) {
					if (c.getResolution() == Resolution.FIXED || c.getResolution() == Resolution.WORKSFORME) {
						throw new UnsupportedOperationException();
					}
					notes.add(c.getNote());
					resolution = c.getResolution();
					setState("Closed");
				} else if (issueType == IssueType.BUG) {
					if (c.getResolution() != Resolution.FIXED) {
						resolution = c.getResolution();
						setState("Closed");
						notes.add(c.getNote());
					} else {
						throw new UnsupportedOperationException();
					}
				}
			} else {
				throw new UnsupportedOperationException();
			}
			owner = c.getOwnerId();
		}

		/**
		 * Returns the State name of NewState
		 * 
		 * @return The State name
		 */
		@Override
		public String getStateName() {
			return NEW_NAME;
		}
	}

	/**
	 * Class representing the Verifying State in the Issue Manager FSM
	 * 
	 * @author Anton
	 */
	public class VerifyingState implements IssueState {
		/**
		 * Private constructor for VerifyingState class
		 */
		private VerifyingState() {

		}

		/**
		 * Method used to update the state of the Issue to VerifyingState
		 * 
		 * @param c The Command being passed to the method
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.VERIFY) {
				setState("Closed");
				notes.add(c.getNote());
			} else if (c.getCommand() == CommandValue.REOPEN) {
				setState("Working");
				resolution = null;
				notes.add(c.getNote());
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns the State name of VerifyingState
		 * 
		 * @return The State name
		 */
		@Override
		public String getStateName() {
			return VERIFYING_NAME;
		}
	}

	/**
	 * Class representing the Working State in the Issue Manager FSM
	 * 
	 * @author Anton
	 */
	public class WorkingState implements IssueState {
		/**
		 * Private constructor for the WorkingState class
		 */
		private WorkingState() {

		}

		/**
		 * Method used to update the state of the Issue to WorkingState
		 * 
		 * @param c The Command being passed to the method
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.RESOLVE && c.getResolution() == Resolution.FIXED) {
				setState("Verifying");
				notes.add(c.getNote());
			} else if (c.getCommand() == CommandValue.RESOLVE && c.getResolution() != Resolution.FIXED) {
				if (c.getResolution() == Resolution.WORKSFORME && issueType != IssueType.BUG) {
					throw new UnsupportedOperationException();
				}
				if(c.getResolution() == Resolution.WORKSFORME && issueType == IssueType.BUG) {
					setState("Verifying");
					notes.add(c.getNote());
				}
				setState("Closed");
				notes.add(c.getNote());
			} else {
				throw new UnsupportedOperationException();
			}
			resolution = c.getResolution();
		}

		/**
		 * Returns the State name of State
		 * 
		 * @return The State name
		 */
		@Override
		public String getStateName() {
			return WORKING_NAME;
		}
	}

}
