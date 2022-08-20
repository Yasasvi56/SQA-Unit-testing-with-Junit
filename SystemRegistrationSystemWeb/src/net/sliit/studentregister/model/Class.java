package net.sliit.studentregister.model;

public class Class {
	
	private int ClassID;
	private int SubjectID;
	private int TeacherID;
	private String Description;
	private String Schedule;
	private String Time;
	
	public Class(int classID, int subjectID, int teacherID, String description,
			String schedule, String time) {
		super();
		ClassID = classID;
		SubjectID = subjectID;
		TeacherID = teacherID;
		Description = description;
		Schedule = schedule;
		Time = time;
	}
	
	public Class(int subjectID, int teacherID, String description,
			String schedule, String time) {
		super();
		SubjectID = subjectID;
		TeacherID = teacherID;
		Description = description;
		Schedule = schedule;
		Time = time;
	}
	
	public Class() {
		super();
	}

	public int getClassID() {
		return ClassID;
	}

	public void setClassID(int classID) {
		ClassID = classID;
	}

	public int getSubjectID() {
		return SubjectID;
	}

	public void setSubjectID(int subjectID) {
		SubjectID = subjectID;
	}

	public int getTeacherID() {
		return TeacherID;
	}

	public void setTeacherID(int teacherID) {
		TeacherID = teacherID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSchedule() {
		return Schedule;
	}

	public void setSchedule(String schedule) {
		Schedule = schedule;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
	
}
