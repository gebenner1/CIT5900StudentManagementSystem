package roles;
import java.util.ArrayList;
import java.util.List;

import courses.Course;

/**
 * Represents a professor, extends User class
 * @author gracebenner
 *
 */
public class Professor extends User {
	
	// instance variables
	/**
	 * List of courses a professor teaches
	 */
	private List<Course> courses;
	
	// constructor 
	/**
	 * Construct an instance of a professor
	 * @param id of professor
	 * @param name of professor
	 * @param username of professor
	 * @param password of professor
	 */
	public Professor(String id, String name, String username, String password) {
		super(id, name, username, password);
		courses = new ArrayList<>();
	}
	
	/**
	 * Get the list of courses for a professor.
	 * @return list of courses
	 */
	public List<Course> getCourses() {
		return courses;
	}
	
}
