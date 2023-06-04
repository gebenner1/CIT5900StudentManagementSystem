package roles;

import java.util.ArrayList;

/**
 * Represents a student, extends user class
 * @author gracebenner
 *
 */
public class Student extends User {
	
	// instance variables associated only with students
	/**
	 * ArrayList of String Array that has course ID and grade
	 */
	private ArrayList<String[]> courseGrades;
	/**
	 * ArrayList of enrolled course IDs
	 */
	private ArrayList<String> enrolledCourses = new ArrayList<>();
	
	//constructor
	/**
	 * Construct an instance of a student
	 * @param id of student
	 * @param name of student
	 * @param username of student
	 * @param password of student
	 * @param courseGrades of student
	 */
	public Student(String id, String name, String username, String password, ArrayList<String[]> courseGrades) {
		super(id, name, username, password);
		this.courseGrades = courseGrades;
	}
	
	// methods
	// other
	/**
	 * Return an ArrayList of previous course IDs of student
	 * @return ArrayList of previous course IDs
	 */
	public ArrayList<String> getCourses() {
		// create new array list
		ArrayList<String> list = new ArrayList<>();
		// for each array in course grades
		for (String[] courseGrade : courseGrades) {
			// add just the course ID to the new list
			list.add(courseGrade[0]);
		}
		return list;
	}
	
	// getters
	/**
	 * Get ArrayList of enrolled course IDs of student
	 * @return ArrayList of enrolled course IDs
	 */
	public ArrayList<String> getEnrolledCourses() {
		return enrolledCourses;
	}
	
	/**
	 * Get the ArrayList of course ID and grades of student
	 * @return ArrayList of course ID and grades
	 */
	public ArrayList<String[]> getCourseGrades() {
		return courseGrades;
	}

	
}
