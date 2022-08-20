package net.sliit.studentregister.model;

import java.sql.Date;

public class Exam {
	
	private int ExamID;
	private int SubjectID;
	private String Description;
	private Date ExamDate;
	private String Time;
	private String Location;
	
	public Exam(int examID, int subjectID, String description, Date examDate,
			String time, String location) {
		super();
		ExamID = examID;
		SubjectID = subjectID;
		Description = description;
		ExamDate = examDate;
		Time = time;
		Location = location;
	}
	
	public Exam(int subjectID, String description, Date examDate,
			String time, String location) {
		super();
		SubjectID = subjectID;
		Description = description;
		ExamDate = examDate;
		Time = time;
		Location = location;
	}
	
	public Exam() {
		super();
	}

	public int getExamID() {
		return ExamID;
	}

	public void setExamID(int examID) {
		ExamID = examID;
	}

	public int getSubjectID() {
		return SubjectID;
	}

	public void setSubjectID(int subjectID) {
		SubjectID = subjectID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getExamDate() {
		return ExamDate;
	}

	public void setExamDate(Date examDate) {
		ExamDate = examDate;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}
	
}
