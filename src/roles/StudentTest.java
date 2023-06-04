package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {
	
	Student student;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void getCourses() {
		
		// scenario 1, student has no courses and grades
		student = new Student("001", "Grace Benner", "gb", "password590", new ArrayList<String[]>());
		ArrayList<String> emptyArrayList = new ArrayList<String>();
		assertEquals(emptyArrayList, student.getCourses());
		assertEquals(0, student.getCourses().size());
		
		// scenario 2, student has 1+ courses and grades
		ArrayList<String[]> courseGrades = new ArrayList<String[]>();
		courseGrades.add(new String[] {"CIT590", "A-"});
		student = new Student("001", "Grace Benner", "gb", "password590", courseGrades);
		// check if added course to list
		assertEquals("CIT590", student.getCourses().get(0));
		// check if length is one
		assertEquals(1, student.getCourses().size());
		// add another set of course and grade
		courseGrades.add(new String[] {"CIS592", "B"});
		// check if added course to list
		assertEquals("CIS592", student.getCourses().get(1));
		// check if length is two
		assertEquals(2, student.getCourses().size());
	
	}

}
