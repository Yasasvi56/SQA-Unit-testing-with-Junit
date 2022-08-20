package net.sliit.studentregister.model;

public class Teacher {
	
	private int TeacherID;
	private String Name;
	private String Qualification;
	
	public Teacher(int teacherID, String name, String qualification) {
		super();
		TeacherID = teacherID;
		Name = name;
		Qualification = qualification;
	}
	
	public Teacher(String name, String qualification) {
		super();
		Name = name;
		Qualification = qualification;
	}
	
	public Teacher() {
		super();
	}

	public int getTeacherID() {
		return TeacherID;
	}

	public void setTeacherID(int teacherID) {
		TeacherID = teacherID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}
	
}
