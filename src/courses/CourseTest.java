package courses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {
	
	Course course;

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void getEndTimeNum() {
		// check get end time num for time of "18:00"
		course = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "16:00", "18:00", 80);
		assertTrue(1800 == course.getEndTimeNum());
		
		// see if it works with both "09:00" and "9:00"
		course = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "08:00", "09:00", 80);
		Course course2 = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "08:00", "9:00", 80);
		assertTrue(course2.getEndTimeNum() == course.getEndTimeNum());
		assertTrue(900 == course.getEndTimeNum());
		assertTrue(course2.getEndTimeNum() == 900);
	
	}
	
	@Test
	void getStartTimeNum() {
		// check get start time num for time of "16:00"
		course = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "16:00", "18:00", 80);
		assertTrue(1600 == course.getStartTimeNum());
		
		// see if it works with both "08:00" and "8:00"
		course = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "08:00", "09:00", 80);
		Course course2 = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "08:00", "9:00", 80);
		assertTrue(course2.getStartTimeNum() == course.getStartTimeNum());
		assertTrue(800 == course.getStartTimeNum());
		assertTrue(course2.getStartTimeNum() == 800);

	}
	
	@Test
	void incrementStudents() {
		// check that students is initialized at 0
		course = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "16:00", "18:00", 80);
		assertTrue(0 == course.getStudents());
		// keep incrementing students and check that it updates 
		course.incrementStudents();
		assertTrue(1 == course.getStudents());
		course.incrementStudents();
		assertTrue(2 == course.getStudents());
		course.incrementStudents();
		assertTrue(3 == course.getStudents());
		course.incrementStudents();
		assertTrue(4 == course.getStudents());
		
		// try with new course
		course = new Course("CIT100", "Computer Tech", "Grace Benner", "TR", "10:00", "12:00", 700);
		assertTrue(0 == course.getStudents());
		course.incrementStudents();
		assertTrue(1 == course.getStudents());
		course.incrementStudents();

	}
	
	@Test
	void decrementStudents() {
		// check that students is initialized at 0
		course = new Course("CIT590", "Computer Science", "Dr. Smith", "MWF", "16:00", "18:00", 80);
		assertTrue(0 == course.getStudents());
		// keep incrementing students and check that it updates 
		course.incrementStudents();
		course.incrementStudents();
		course.incrementStudents();
		assertTrue(3 == course.getStudents());
		// keep decrementing students and check that it updates 
		course.decrementStudents();
		assertTrue(2 == course.getStudents());
		course.decrementStudents();
		assertTrue(1 == course.getStudents());
		course.decrementStudents();
		assertTrue(0 == course.getStudents());
		
		// check that you can't get negative students
		course = new Course("CIT100", "Computer Tech", "Grace Benner", "TR", "10:00", "12:00", 700);
		assertTrue(0 == course.getStudents());
		course.decrementStudents();
		assertTrue(0 == course.getStudents());
		course.decrementStudents();
		assertTrue(0 == course.getStudents());
		
		
	}

}
