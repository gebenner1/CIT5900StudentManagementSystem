package courses;
import java.util.ArrayList;
import roles.Student;

/**
 * Represents an instance of a course.
 * @author gracebenner
 *
 */
public class Course {
	
	// instance variables
	/**
	 * ID of course
	 */
	private String id;
	/**
	 * Name of course
	 */
	private String name;
	/**
	 * Lecturer of course
	 */
	private String lecturer;
	/**
	 * Days of course
	 */
	private String days;
	/**
	 * Start time of course
	 */
	private String startTime;
	/**
	 * End time of course
	 */
	private String endTime;
	/**
	 * Capacity of course
	 */
	private int capacity;
	/**
	 * Number of students in the course
	 */
	private int students;
	/**
	 * List of student IDs of those enrolled in the course
	 */
	private ArrayList<Student> enrolledStudents = new ArrayList<>();
	
	// constructor
	/**
	 * Construct a course
	 * @param id of course
	 * @param name of course
	 * @param lecturer of course
	 * @param days of course
	 * @param startTime of course
	 * @param endTime of course
	 * @param capacity of course
	 */
	public Course(String id, String name, String lecturer, String days, String startTime, String endTime, int capacity) {
		this.id = id;
		this.name = name;
		this.lecturer = lecturer;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
	}
	
	//methods
	//other methods
	/**
	 * Get military end time as an integer
	 * @return start time as integer
	 */
	public int getEndTimeNum() {
		String[] parts = endTime.split(":");
		String time = parts[0] + parts[1];
		int endTimeNum = Integer.valueOf(time);
		return endTimeNum;
	}
	
	/**
	 * Get military start time as an integer
	 * @return start time as integer
	 */
	public int getStartTimeNum() {
		String[] parts = startTime.split(":");
		String time = parts[0] + parts[1];
		int startTimeNum = Integer.valueOf(time);
		return startTimeNum;
	}
	
	/**
	 * Decrease number of students for a given course.
	 */
	public void incrementStudents() {
		students++;
	}
	
	/**
	 * Increase number of students for a given course.
	 */
	public void decrementStudents() {
		if (students > 0) {
			students--;
		}
	}
	
	//getters 
	/**
	 * Get the array list of students IDs enrolled in class
	 * @return student IDs who are enrolled
	 */
	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	
	/**
	 * Get the professor of course
	 * @return professor name
	 */
	public String getLecturer() {
		return lecturer;
	}
	
	/**
	 * Get name of course
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get course capacity
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Get the days of the week.
	 * @return days
	 */
	public String getDays() {
		return days;
	}
	
	/**
	 * Get number of students.
	 * @return students
	 */
	public int getStudents() {
		return students;
	}
	
	/**
	 * Get course ID.
	 * @return ID.
	 */
	public String getId() {
		return id;
	}
	
	@Override
	/**
	 * Tells Java how to represent a course.
	 */
	public String toString() {
		return id + "|" + name + ", " + startTime + "-" + endTime + " on " + days + ", with course capacity: " + capacity + ", students: " + students + ", lecturer: " + lecturer;
	}
}
