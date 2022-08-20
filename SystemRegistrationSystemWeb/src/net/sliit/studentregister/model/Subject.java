package net.sliit.studentregister.model;

/**
 * User.java
 * This is a model class represents a User entity
 */
public class Subject {
	private int SubjectID;
	private String SubjectCode;
	private String SubjectName;
	
	public Subject() {
		super();
	}
	
	public Subject(int subjectID, String subjectCode, String subjectName) {
		super();
		SubjectID = subjectID;
		SubjectCode = subjectCode;
		SubjectName = subjectName;
	}
	
	public int getSubjectID() {
		return SubjectID;
	}
	public void setSubjectID(int subjectID) {
		SubjectID = subjectID;
	}
	public String getSubjectCode() {
		return SubjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		SubjectCode = subjectCode;
	}
	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
}
