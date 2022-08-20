package net.sliit.studentregister.model;

import java.sql.Date;

public class Student {
	private int StudentID;
	private String FName;
	private String LName;
	private int Age;
	private Date DOB;
	private int PhoneNo;
	private String Email;

	public Student(int studentID, String fName, String lName, int age,
			Date dOB, int phoneNo, String email) {
		super();
		StudentID = studentID;
		FName = fName;
		LName = lName;
		Age = age;
		DOB = dOB;
		PhoneNo = phoneNo;
		Email = email;
	}
	
	public Student(String fName, String lName, int age,
			Date dOB, int phoneNo, String email) {
		super();
		FName = fName;
		LName = lName;
		Age = age;
		DOB = dOB;
		PhoneNo = phoneNo;
		Email = email;
	}
	
	public Student() {
		super();
	}
	
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String getFName() {
		return FName;
	}
	public void setFName(String fName) {
		FName = fName;
	}
	public String getLName() {
		return LName;
	}
	public void setLName(String lName) {
		LName = lName;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public int getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		PhoneNo = phoneNo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
}
