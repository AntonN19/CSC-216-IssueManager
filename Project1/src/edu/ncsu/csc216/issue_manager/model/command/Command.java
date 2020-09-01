/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.command;

/**
 * Class created to encapsulate user actions that cause a change in the sate of
 * the issue
 * 
 * @author Anton
 */
public class Command {

	/** The Fixed resolution value */
	public static final String R_FIXED = "Fixed";
	/** The Duplicate resolution value */
	public static final String R_DUPLICATE = "Duplicate";
	/** The WontFix resolution value */
	public static final String R_WONTFIX = "WontFix";
	/** The WorksForMe resolution value */
	public static final String R_WORKSFORME = "WorksForMe";
	/** The id of the owner */
	private String ownerId;
	/** The resolution of the command */
	private Resolution resolution;
	/** The value of the command */
	private CommandValue command;
	/** The note attached to the command */
	private String note;

	/**
	 * Getter for the commandValue of this class
	 * 
	 * @return the command value
	 */
	public CommandValue getCommand() {
		return this.command;
	}

	/**
	 * Getter for the Resolution of this class
	 * 
	 * @return the Resolution
	 */
	public Resolution getResolution() {
		return this.resolution;
	}

	/**
	 * Getter method for the ownerId
	 * 
	 * @return the ownerId of this class
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Getter for note
	 * 
	 * @return the note of this command class
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Constructor for the Command class
	 * 
	 * @param c       the Value of the command
	 * @param ownerId the id of the owner
	 * @param r       the resolution of the command
	 * @param note    Notes included with the command
	 */
	public Command(CommandValue c, String ownerId, Resolution r, String note) {
		if (c == null) {
			throw new IllegalArgumentException("Null CommandValue");
		}
		if (c == CommandValue.ASSIGN && (ownerId == null || ownerId.length() == 0)) {
			throw new IllegalArgumentException("no owner with ASSIGN");
		}
		if (c == CommandValue.RESOLVE && r == null) {
			throw new IllegalArgumentException("Resolved with no resolution");
		}
		if (note == null || note.length() == 0) {
			throw new IllegalArgumentException("null note");
		}
		this.command = c;
		this.ownerId = ownerId;
		this.resolution = r;
		this.note = note;
	}

	/**
	 * The resolution enumeration
	 * 
	 * @author Sarah Heckman
	 */
	public enum Resolution {
		/**
		 * Fixed resolution
		 */
		FIXED,
		/**
		 * Duplicate resolution
		 */
		DUPLICATE,
		/**
		 * WontFix resolution
		 */
		WONTFIX,
		/**
		 * WorksForMe resolution
		 */
		WORKSFORME;
	}

	/**
	 * The commandValue enumeration
	 * 
	 * @author Sarah Heckman
	 */
	public enum CommandValue {
		/**
		 * Assign Command value
		 */
		ASSIGN,
		/**
		 * Confirm Command value
		 */
		CONFIRM,
		/**
		 * Resolve Command value
		 */
		RESOLVE,
		/**
		 * Verify Command value
		 */
		VERIFY,
		/**
		 * Reopen Command value
		 */
		REOPEN
	}

}
