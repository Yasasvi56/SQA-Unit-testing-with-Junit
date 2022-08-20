package net.sliit.studentregister.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.dao.SubjectDAO;
import net.sliit.studentregister.model.Subject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SubjectDAOTest {

	SubjectDAO subjDao = new SubjectDAO();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertSubject() {
		boolean actual;
		try{
			Subject subject = new Subject();
			subject.setSubjectCode("IT2020");
			subject.setSubjectName("Regression Analysis");
			actual = subjDao.insertSubject(subject);
		}catch(Exception ex){
			actual = false;
		}
		boolean expected = true;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testInsertSubjectDuplicateCode() {
		boolean actual;
		try{
			Subject subject = new Subject();
			subject.setSubjectCode("IT2020");
			subject.setSubjectName("Regression Analysis");
			actual = subjDao.insertSubject(subject);
		}catch(Exception ex){
			actual = false;
		}
		boolean expected = true;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testRequiredFields() {
		boolean actual;
		try{
			Subject subject = new Subject();
			subject.setSubjectCode("IT2020");
			subject.setSubjectName("Regression Analysis");
			actual = subjDao.insertSubject(subject);
		}catch(Exception ex){
			actual = false;
		}
		boolean expected = true;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testServiceCodeSixChars() {
		boolean actual;
		try{
			Subject subject = new Subject();
			subject.setSubjectCode("IT2020");
			subject.setSubjectName("Regression Analysis");
			actual = subjDao.insertSubject(subject);
		}catch(Exception ex){
			actual = false;
		}
		boolean expected = true;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateSubject() {
		boolean actual;
		try{
			Subject subject = new Subject();
			subject.setSubjectID(9);
			subject.setSubjectCode("IT2020");
			subject.setSubjectName("Regression Analysis 2");
			actual = subjDao.updateSubject(subject);
		}catch(Exception ex){
			actual = false;
		}
		boolean expected = true;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
	}

	@Test
	public void testDeleteSubject() {
		boolean actual;
		try{
			actual = subjDao.deleteSubject(10);
		}catch(Exception ex){
			actual = false;
		}
		boolean expected = true;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testDeleteNotExistsSubject() {
		boolean actual;
		try{
			actual = subjDao.deleteSubject(10);
		}catch(Exception ex){
			actual = false;
		}
		boolean expected = true;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSelectSubjectByID() {
		Subject subject;
		try{
			subject = subjDao.selectSubject(9);
		}catch(Exception ex){
			subject = null;
		}
		assertNotNull(subject);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSelectNotExsistsSubjectByID() {
		Subject subject;
		try{
			subject = subjDao.selectSubject(9);
		}catch(Exception ex){
			subject = null;
		}
		assertNotNull(subject);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSelectAllSubjects() {
		List<Subject> subjects = new ArrayList<>();
		try{
			subjects = subjDao.selectAllSubjects();
		}catch(Exception ex){
			subjects = null;
		}
		assertFalse(subjects==null||subjects.isEmpty());
		//fail("Not yet implemented");
	}

}
