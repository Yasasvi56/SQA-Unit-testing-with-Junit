package net.sliit.studentregister.model;

import java.sql.Date;

public class Attendance {
	
	private int AttendanceID;
	private int StudentID;
	private int ClassID;
	private Date AttendanceDate;
	
	public Attendance(int attendanceID, int studentID, int classID,
			Date attendanceDate) {
		super();
		AttendanceID = attendanceID;
		StudentID = studentID;
		ClassID = classID;
		AttendanceDate = attendanceDate;
	}
	
	public Attendance(int studentID, int classID,
			Date attendanceDate) {
		super();
		StudentID = studentID;
		ClassID = classID;
		AttendanceDate = attendanceDate;
	}
	
	public Attendance() {
		super();
	}

	public int getAttendanceID() {
		return AttendanceID;
	}

	public void setAttendanceID(int attendanceID) {
		AttendanceID = attendanceID;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public int getClassID() {
		return ClassID;
	}

	public void setClassID(int classID) {
		ClassID = classID;
	}

	public Date getAttendanceDate() {
		return AttendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		AttendanceDate = attendanceDate;
	}

}
