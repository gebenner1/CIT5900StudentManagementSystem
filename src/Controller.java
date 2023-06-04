import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;


/**
 * Overall controller for the student management system.
 * Contains majority of methods for the functionality
 * of student management system.
 * @author gracebenner
 *
 */
public class Controller {
	static User currentUser;

	/**
	 * ArrayList of courses
	 */
	static ArrayList<Course> courses = new ArrayList<>();
	/**
	 * ArrayList of students
	 */
	static ArrayList<Student> students = new ArrayList<>();
	/**
	 * ArrayList of professors
	 */
	static ArrayList<Professor> professors = new ArrayList<>();
	/**
	 * ArrayList of admins
	 */
	static ArrayList<Admin> admins = new ArrayList<>();

	/**
	 * Main method for running student management system
	 * @param args
	 */
	public static void main(String[] args) {
		// read all 4 files
		readFiles();
		Scanner sc = new Scanner(System.in);
		// main application logic
		while (true) {
			printMainMenu();
			int input =  getValidInput(sc, 4, "Please enter your option, eg. '1'");
			if (input == 1) {
				// student
				// get username and password
				boolean loggedIn = studentLogin(sc);
				if (loggedIn) {
					while (true) {
						printStudentMenu(currentUser.getName());
						input = getValidInput(sc, 6, "Please enter your option, eg. '1'");
						if (input == 1) {
							// view all courses
							viewAllCourses();
						} else if (input == 2) {
							//enroll in a course
							studentEnrollCourse(sc);
						} else if (input == 3) {
							// view enrolled courses
							studentViewEnrolledCourses();
						} else if (input == 4) {
							// drop a course
							studentDropCourse(sc);
						} else if (input == 5) {
							// view grades
							studentCheckGrades();
						} else if (input == 6) {
							// return to main menu
							break;
						}
					}
				} else {
					// not valid student login so continue
					continue;
				}
				
			} else if (input == 2) {
				// professor
				// get username and password
				boolean loggedIn = profLogin(sc);
				if (loggedIn) {
					while (true) {
						printProfessorMenu(currentUser.getName());
						input = getValidInput(sc, 3, "Please enter your option, eg. '1'");
						if (input == 1) {
							// view courses
							professorSeeCourses();
						} else if (input == 2) {
							//view students
							professorSeeStudents(sc);
						} else if (input == 3) {
							//return to main menu
							break;
						}
					}
				} else {
					// not valid professor login so continue
					continue;
				}
				
				
			} else if (input == 3) {
				// admin
				// get username and password
				boolean loggedIn = adminLogin(sc);
				if (loggedIn) {
					while (true) {
						printAdminMenu(currentUser.getName());
						input = getValidInput(sc, 8, "Please enter your option, eg. '1'");
						if (input == 1) {
							// view all courses
							viewAllCourses();
						} else if (input == 2) {
							// add new courses
							adminAddCourse(sc);
						} else if (input == 3) {
							// delete courses
							adminDeleteCourse(sc);
						} else if (input == 4) {
							// add new professors
							adminAddProfessor(sc);
						} else if (input == 5) {
							// delete professors
							adminDeleteProfessor(sc);
						} else if (input == 6) {
							// add new students
							adminAddStudent(sc);
						} else if (input == 7) {
							// delete students
							adminDeleteStudent(sc);
						} else if (input == 8) {
							// return to main menu
							break;
						}
					}
				} else {
					// not valid admin login so continue
					continue;
				}
				
			} else if (input == 4) {
				// quit
				System.out.println("Goodbye!");
				break;
			}
		}
		sc.close();
	}
	
	//METHODS FOR ADMINS
	/**
	 * Method for admin to add a new student.
	 * @param sc
	 */
	private static void adminAddStudent(Scanner sc) {
		String newStudentId = "";
		boolean wrongId = true;
		// get student id
		while (wrongId) {
			System.out.println("What student would you like to add? Enter the student's ID, eg. '001'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newStudentId = sc.next();
			// exit if enter q
			if (newStudentId.equals("q")) {
				return;
			} else {
				// check if student is already in list, if so keep asking for valid ID
				for (int i = 0; i < students.size(); i++) {
					if (students.get(i).getId().equals(newStudentId)) {
						System.out.println("That ID already exists.");
						System.out.println();
						wrongId = true;
						break; }
					wrongId = false; } } }
		
		// get student name
		System.out.println("What is the student's name?");
		System.out.println("Or enter 'q' to return to the previous menu.");
		sc.nextLine(); 
		String newStudentName = sc.nextLine();
		// exit if enter q
		if (newStudentName.equals("q")) {
			return; }
		
		// get student username
		String newStudentUsername = "";
		boolean wrongUsername = true;
		while (wrongUsername) {
			System.out.println("Please enter a username.");
			System.out.println("Or enter 'q' to return to the previous menu.");
			newStudentUsername = sc.next();
			// exit if enter q
			if (newStudentUsername.equals("q")) {
				return;
			} else {
				// check if username exists, keep asking for username if so
				for (int i = 0; i < students.size(); i++) {
					if (students.get(i).getUsername().equals(newStudentUsername)) {
						System.out.println("That username already exists.");
						System.out.println();
						wrongUsername = true;
						break; }
					wrongUsername = false; } } }
		
		// get student password
		System.out.println("Please enter a password.");
		System.out.println("Or enter 'q' to return to the previous menu.");
		String newStudentPassword = sc.next();
		// exit if enter q
		if (newStudentPassword.equals("q")) {
			return; }
		
		// get courses and grades
		ArrayList<String[]> newStudentCourseGrades = new ArrayList<String[]>();
		while (true) {
			boolean unsuccessful = true;
			System.out.println("Please enter a course ID for a course this student already took. One course at a time.");
			System.out.println("Enter 'n' if there are no more courses to add.");
			System.out.println("Or enter 'q' to return to the previous menu.");
			String newStudentCourseID = sc.next();
			// check for q and n to exit or break out of while loop
			if (newStudentCourseID.equals("q")) {
				return; 
			} else if (newStudentCourseID.equals("n")) {
					break;
			} else {
				// check if there is a course ID that matches 
				for (int i = 0; i < courses.size(); i++) {
					// if so have them add a grade
					if (courses.get(i).getId().equals(newStudentCourseID)) {
						System.out.println("Please enter the grade, eg. 'A-'");
						String newStudentGrade = sc.next();
						String regex = "[A-D][+-]?|F";
						if (newStudentGrade.matches(regex)) {
							newStudentCourseGrades.add(new String[] {newStudentCourseID, newStudentGrade});
							unsuccessful = false;
							break;
						}
					}
				}
				
				// if the grade is wrong or course ID does not match print a message
				if (unsuccessful) {
					System.out.println("Your course ID and/or the letter grade you entered is not valid.");
					System.out.println();
				}
			}
			
		}
		
		// create new student and add to students array list
		Student newStudent = new Student(newStudentId, newStudentName, newStudentUsername, newStudentPassword, newStudentCourseGrades);
		students.add(newStudent);
	}
	
	/**
	 * Method for admin to add a new professor.
	 * @param sc
	 */
	private static void adminAddProfessor(Scanner sc) {
		// get the professor's ID
		String newProfessorId = "";
		boolean wrongId = true;
		while (wrongId) {
			System.out.println("What professor would you like to add? Enter the professor's ID, eg. '001'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newProfessorId = sc.next();
			// if user enters q exit
			if (newProfessorId.equals("q")) {
				return;
			} else {
				// check to see if ID is already in the professors array list
				for (int i = 0; i < professors.size(); i++) {
					if (professors.get(i).getId().equals(newProfessorId)) {
						System.out.println("That ID already exists.");
						System.out.println();
						wrongId = true;
						break; }
					wrongId = false; } } }
		
		// get the professor's name
		System.out.println("What is the professor's name?");
		System.out.println("Or enter 'q' to return to the previous menu.");
		sc.nextLine(); 
		String newProfessorName = sc.nextLine();
		// if user enters q exit
		if (newProfessorName.equals("q")) {
			return; }
		
		// get the professor's username
		String newProfessorUsername = "";
		boolean wrongUsername = true;
		while (wrongUsername) {
			System.out.println("Please enter a username.");
			System.out.println("Or enter 'q' to return to the previous menu.");
			newProfessorUsername = sc.next();
			// if user enters q exit
			if (newProfessorUsername.equals("q")) {
				return;
			} else {
				// check if username already exists, if so keep asking
				for (int i = 0; i < professors.size(); i++) {
					if (professors.get(i).getUsername().equals(newProfessorUsername)) {
						System.out.println("That username already exists.");
						System.out.println();
						wrongUsername = true;
						break; }
					wrongUsername = false; } } }
		
		// get the professor's password
		System.out.println("Please enter a password.");
		System.out.println("Or enter 'q' to return to the previous menu.");
		String newProfessorPassword = sc.next();
		// if user enters q exit
		if (newProfessorPassword.equals("q")) {
			return; }
		
		// create new professor and add to list of professor's
		Professor newProfessor = new Professor(newProfessorId, newProfessorName, newProfessorUsername, newProfessorPassword);
		professors.add(newProfessor);
	}
	
	/**
	 * Method for admin to add a new course.
	 * @param sc
	 */
	private static void adminAddCourse(Scanner sc) {
		// get the new course ID
		String newCourseId = "";
		boolean wrongId = true;
		while (wrongId) {
			System.out.println("What course would you like to add? Enter the course's ID, eg. 'CIT590'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newCourseId = sc.next();
			// if user enters q exit
			if (newCourseId.equals("q")) {
				return;
			} else {
				// check if course ID already exists, keep asking if it does
				for (int i = 0; i < courses.size(); i++) {
					if (courses.get(i).getId().equals(newCourseId)) {
						System.out.println("That course already exists.");
						System.out.println();
						wrongId = true;
						break; }
					wrongId = false; } } }
		
		// get the course's name
		System.out.println("What is the course's name?");
		System.out.println("Or enter 'q' to return to the previous menu.");
		// scan input
		sc.nextLine(); 
		String newCourseName = sc.nextLine();
		// if user enters q exit
		if (newCourseName.equals("q")) {
			return; }
		
		// get the courses start time
		String newCourseStartTime = "";
		while(true) {
			System.out.println("Please enter the course start time, eg. '19:00'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newCourseStartTime = sc.next();
			// if user enters q exit
			if (newCourseStartTime.equals("q")) {
				return;
			} else {
				// check if in the right format (0:00 to 23:59), if so break
				String regex = "^([01]?[0-9]|2[0-3]):?([0-5][0-9])$";
				if (newCourseStartTime.matches(regex)) {
					break;
				}
				System.out.println("That start time is invalid");
			}
		}
		
		// get the course end time
		String newCourseEndTime = "";
		while(true) {
			System.out.println("Please enter the course end time, eg. '20:00'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newCourseEndTime = sc.next();
			// if user enters q exit
			if (newCourseEndTime.equals("q")) {
				return;
			} else {
				// check if in the right format (0:00 to 24:00), if so break
				String regex = "^([01]?[0-9]|2[0-3]):?([0-5][0-9])$|(24:?00)";
				if (newCourseEndTime.matches(regex)) {
					// check if bigger than start time
					if (numericTime(newCourseEndTime) > numericTime(newCourseStartTime)) {
						break;
					}
				}
				System.out.println("That end time is invalid");
				}
			}
			
		// get the course days of week
		String newCourseDays = "";
		while(true) {
			System.out.println("Please enter the course day(s), eg. 'MW'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newCourseDays = sc.next();
			// if user enters q exit
			if (newCourseDays.equals("q")) {
				return;
			} else {
				// check if roughly the right format, if so break
				String regex = "^[MTWRF]{1,5}$";
				if (newCourseDays.matches(regex)) {
					break;
				} else {
					System.out.println("Those day(s) are invalid.");
				}
			}
		}
		
		// get the course capacity
		String newCourseCapacity = "";
		while(true) {
			System.out.println("Please enter the course capacity, eg. '100'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newCourseCapacity = sc.next();
			// if user enters q exit
			if (newCourseCapacity.equals("q")) {
				return;
			} else {
				// check if right format to become numeric
				String regex = "[1-9][0-9]*";
				if (newCourseCapacity.matches(regex)) {
					break;
				} else {
					System.out.println("That capacity is invalid.");
				}
			}
		}
		// convert capacity to integer
		int newCourseCapacityInt = Integer.valueOf(newCourseCapacity);
		
		// get professor name (use ID to get there)
		String newCourseProfessorId = "";
		String newCourseProfessorName = "";
		while(true) {
			System.out.println("Please enter the course's lecturer ID, eg. '002'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan input
			newCourseProfessorId = sc.next();
			// if user enters q exit
			if (newCourseProfessorId.equals("q")) {
				return;
			} else {
				boolean notListed = true;
				// if professor is already in the list
				for (int i = 0; i < professors.size(); i++) {
					if (professors.get(i).getId().equals(newCourseProfessorId)) {
						notListed = false;
						// save their name and break
						newCourseProfessorName = professors.get(i).getName();
						break;
					}
				}
				
				// if the professor is not in the list
				if (notListed) {
					// create new professor
					System.out.println("That professor is not in the system. We first need to add them");
					adminAddProfessor(sc);
					// extract course lecturer name for constructor, break
					newCourseProfessorName = professors.get(professors.size() - 1).getName();
					System.out.println("Sucessfully added professor: " + newCourseProfessorName);
					break;
				} else {
					break;
				}
			}
		}
		
		// construct new course
		Course newCourse = new Course(newCourseId, newCourseName, newCourseProfessorName, newCourseDays, newCourseStartTime, newCourseEndTime, newCourseCapacityInt);
		
		// get arraylist of professor course ids
		ArrayList<String> currentCourseIds = new ArrayList<String>();
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getLecturer().equals(newCourseProfessorName)) {
				currentCourseIds.add(courses.get(i).getId());
			}
		}
		
		// check if the new course has a time conflict with professors current courses
		boolean timeConflict = timeConflictAdmin(newCourse, currentCourseIds);
		
		// if there is a time conflict don't add the course and exit
		if (timeConflict) {
			System.out.println("Cannot add course becuase professor " + newCourseProfessorName + " is already teaching at that time.");
		// otherwise add the course to list of courses and print
		} else {
			courses.add(newCourse);
			System.out.println("Successfully added course: ");
			System.out.println(newCourse);
		}
	}
	
	/**
	 * Convert string military time to integer.
	 * @param time as integer
	 * @return
	 */
	private static int numericTime(String time) {
		// split string on :
		String[] parts = time.split(":");
		// concatenate 
		String concatTime = parts[0] + parts[1];
		// convert to integer
		int intTime = Integer.valueOf(concatTime);
		return intTime;
	}
	
	/**
	 * Method for an admin to delete students.
	 * @param sc
	 */
	private static void adminDeleteStudent(Scanner sc) {
		while (true) {
			System.out.println("What student would you like to delete? Enter a student ID, eg. '001'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// get user input
			String studentToDelete = sc.next();
			// if user enters q exit
			if (studentToDelete.equals("q")) {
				return;
			} else {
				boolean unsucessful = true;
				// iterate through students
				for (int i = 0; i < students.size(); i++) {
					// if a student ID matches the one entered
					if (students.get(i).getId().equals(studentToDelete)) {
						// remove student
						System.out.println("Student " + studentToDelete + " was successfully deleted.");
						System.out.println();
						students.remove(i);
						unsucessful = false;
						continue;
					}
				}
				// if no mathces were found
				if (unsucessful) {
					System.out.println("There is no student in the system with ID " + studentToDelete);
					System.out.println();
				}
			}
		}
	}
	
	/**
	 * Method for an admin to delete professors.
	 * @param sc
	 */
	private static void adminDeleteProfessor(Scanner sc) {
		while (true) {
			System.out.println("What professor would you like to delete? Enter a professor ID, eg. '001'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan user input
			String profToDelete = sc.next();
			// if user enters q exit
			if (profToDelete.equals("q")) {
				return;
			} else {
				boolean unsucessful = true;
				// iterate through professors
				for (int i = 0; i < professors.size(); i++) {
					// if a professor has an id equal to the one entered
					if (professors.get(i).getId().equals(profToDelete)) {
						// remove the professor
						System.out.println("Professor " + profToDelete + " was successfully deleted.");
						System.out.println();
						professors.remove(i);
						unsucessful = false;
						continue;
					}
				}
				// if no match was found
				if (unsucessful) {
					System.out.println("There is no professor in the system with ID " + profToDelete);
					System.out.println();
				}
			}
		}
	}
	
	/**
	 * Method for an admin to delete courses.
	 * @param sc
	 */
	private static void adminDeleteCourse(Scanner sc) {
		while (true) {
			System.out.println("What course would you like to delete? Enter a course ID, eg. 'CIT590'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan user input
			String courseToDelete = sc.next();
			// if enter q exit program
			if (courseToDelete.equals("q")) {
				return;
			} else {
				boolean unsucessful = true;
				// iterate through courses
				for (int i = 0; i < courses.size(); i++) {
					// if a course ID mathces the user input
					if (courses.get(i).getId().equals(courseToDelete)) {
						// remove the course and continue
						System.out.println("Course " + courseToDelete + " was successfully deleted.");
						System.out.println();
						courses.remove(i);
						unsucessful = false;
						continue;
					}
				}
				// if there were no matches
				if (unsucessful) {
					System.out.println("There is no course in the system with ID " + courseToDelete);
					System.out.println();
				}
			}
		}
	}
	
	//METHODS FOR PROFESSORS
	/**
	 * Have the professor view their courses and 
	 * enter which course they would like to see 
	 * the students from.
	 * @param sc
	 */
	private static void professorSeeStudents(Scanner sc) {
		while (true) {
			// print professors courses
			int courseCount = 0;
			System.out.println("--- Your Courses ---");
			for (int i = 0; i < courses.size(); i++) {
				if (courses.get(i).getLecturer().equals(currentUser.getName())) {
					System.out.println(courses.get(i));
					courseCount ++;
				}
			}
			// if the professor is not teaching courses exit
			if (courseCount == 0) {
				System.out.println("You are not teaching any current courses.");
				return;
			}
			
			// if the professor is teaching at least one course
			System.out.println();
			System.out.println("What course's students would you like to see? Enter a course ID, eg. 'CIT590'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			// scan user input
			String courseToSee = sc.next();
			// if user enters q exit the program
			if (courseToSee.equals("q")) {
				return;
			} else {
				// iterate through courses
				for (int j = 0; j < courses.size(); j++)  {
					// if a course ID is equal to what user entered
					if (courses.get(j).getId().equals(courseToSee)) {
						// if a course has some enrolled students
						if (!courses.get(j).getEnrolledStudents().isEmpty()) {
							System.out.println("The students enrolled in " + courses.get(j).getId() + " are:");
							// print out each enrolled student's id and name
							for (int n = 0; n < courses.get(j).getEnrolledStudents().size(); n++) {
								System.out.println(courses.get(j).getEnrolledStudents().get(n).getId() + " " + courses.get(j).getEnrolledStudents().get(n).getName());
							} 
					// otherwise no students are enrolled
					} else {
						System.out.println("There are no students enrolled in " + courses.get(j).getId() + " currently.");
						}		
					}
				}
				System.out.println();
			}
		}	
	}
	
	/**
	 * Print out the courses for a professor.
	 */
	private static void professorSeeCourses() {
		int courseCount = 0;
		System.out.println("--- Your Courses ---");
		// for each of the courses
		for (int i = 0; i < courses.size(); i++) {
			// if the lecturer equals the professors name
			if (courses.get(i).getLecturer().equals(currentUser.getName())) {
				// print course and increment count
				System.out.println(courses.get(i));
				courseCount ++;
			}
		}
		// if the professor has no courses
		if (courseCount == 0) {
			System.out.println("You are not teaching any current courses.");
		}
	}

	//METHODS FOR STUDENTS
	/**
	 * Prints out the student's previous courses and their letter grades.
	 */
	private static void studentCheckGrades() {
		System.out.println("Here are the letter grades for your previous courses!");
		for (int i = 0; i < ((Student) currentUser).getCourseGrades().size(); i++) {
			System.out.println(((Student) currentUser).getCourseGrades().get(i)[0] + ": " + ((Student) currentUser).getCourseGrades().get(i)[1]);
		}
		
	}
	
	/**
	 * Method for student to drop courses.
	 * @param sc
	 */
	private static void studentDropCourse(Scanner sc) {
		while (true) {
			boolean unsuccessful = true;
			// check if enroll list is empty, if so they can't drop anything
			if (((Student) currentUser).getEnrolledCourses().isEmpty()) {
				System.out.println("You are not enrolled in any courses currently.");
				return; 
				}
			// print out their enrolled courses
			studentViewEnrolledCourses();
			System.out.println();
			System.out.println("What course would you like to drop? Enter a course ID, eg. 'CIT590'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			String courseToDrop = sc.next();
			// if user enters q then quit
			if (courseToDrop.equals("q")) {
				return;
			} else {
				// iterate through student's enrolled courses
				for (int i=0; i<((Student) currentUser).getEnrolledCourses().size(); i++) {
					// if one of the courses IDs match the entered id
					if(((Student) currentUser).getEnrolledCourses().get(i).equals(courseToDrop)) {
						// remove the class
						((Student) currentUser).getEnrolledCourses().remove(i);
						System.out.println("You have successfully dropped " + courseToDrop + " from your schedule!");
						System.out.println();
						unsuccessful = false;
						// decrement count of students for that class
						// delete student from enrolled students for that class
						for (int j = 0; j < courses.size(); j++) {
							if (courses.get(j).getId().equals(courseToDrop)) {
								courses.get(i).getEnrolledStudents().remove(((Student) currentUser));
								courses.get(i).decrementStudents();
							}
						}
						continue;
					}
				}
				// otherwise could not drop the course
				if (unsuccessful) {
					System.out.println("Course drop unsucessful. Make sure that the course ID is valid and is listed in your enrolled courses.");
					System.out.println();
				}
			}
		}
	}
	
	/**
	 * Method for student to enroll in courses.
	 * @param sc scanner
	 */
	private static void studentEnrollCourse(Scanner sc) {
		while (true) {
			boolean unsucessful = true;
			System.out.println("What course would you like to enroll in? Enter a course ID, eg. 'CIT590'");
			System.out.println("Or enter 'q' to return to the previous menu.");
			String courseToEnroll = sc.next();
			// exit if user enters q
			if (courseToEnroll.equals("q")) {
				return;
			}
			// iterate through courses
			for (int i=0; i<courses.size(); i++) {
				// if course is valid
				if (courses.get(i).getId().equals(courseToEnroll)) {
					// if course is at capacity don't enroll and continue
					if (courses.get(i).getCapacity() == courses.get(i).getStudents()) {
						System.out.println("That course is at capacity.");
						continue;
					}
					// if user has already taken that class don't enroll and continue
					if (((Student) currentUser).getCourses().contains(courseToEnroll)) {
						System.out.println("You are already took that course.");
						continue;
					}
					// if student is not enrolled in any courses enroll course and continue
					if (((Student) currentUser).getEnrolledCourses().isEmpty()) {
						((Student) currentUser).getEnrolledCourses().add(courseToEnroll);
						// add student to list for course
						courses.get(i).getEnrolledStudents().add(((Student) currentUser));
						courses.get(i).incrementStudents();
						System.out.println("Class added successfully.");
						unsucessful = false;
						continue;
					// if student is enrolled in at least one course
					} else {
						// check if already enrolled, if so do not enroll and continue
						if (((Student) currentUser).getEnrolledCourses().contains(courseToEnroll)) {
							System.out.println("You are already enrolled in that course.");
							continue;
						}
						// check if time conflict, if so do not enroll and continue
						boolean timeConflict = timeConflict(courseToEnroll, ((Student) currentUser).getEnrolledCourses());
						if (timeConflict) {
							System.out.println("This course conflicts with the time of another course you're enrolled in.");
							continue;
						}
						
						// add course ID to student's enrolled courses
						((Student) currentUser).getEnrolledCourses().add(courseToEnroll);
						// add student to list for course
						courses.get(i).getEnrolledStudents().add(((Student) currentUser));
						// increment student count for that course
						courses.get(i).incrementStudents();
						System.out.println("Class added successfully.");
						unsucessful = false;
						
						}
					}
				}
			
			if (unsucessful) {
				System.out.println("Course enroll unsuccessful.");
			}
		}
	}
	
	/**
	 * Method for student to view their enrolled courses.
	 */
	private static void studentViewEnrolledCourses() {
		// if the student is not enrolled in anything
		if (((Student) currentUser).getEnrolledCourses().isEmpty()) {
			System.out.println("You are not enrolled in any courses currently.");
		// else the student is enrolled in something
		} else {
			System.out.println("The courses you're enrolled in are:");
			// for each class they are enrolled in
			for (int i=0; i<((Student) currentUser).getEnrolledCourses().size(); i++) {
				// save the value of the course ID
				String course = ((Student) currentUser).getEnrolledCourses().get(i);
				// for all courses
				for (int j=0; j<courses.size(); j++) {
					// if the student's enrolled course equals one of the courses
					if (courses.get(j).getId().equals(course)) {
						// print out that course
						System.out.println(courses.get(j));
					}
				}
			}
		}
	}
	
	// METHODS TO CHECK TIME CONFLICT - USED BY STUDENT & ADMIN
	/**
	 * Check time conflict between course, and list of other course IDs
	 * @param courseToCheck course to see if time conflict
	 * @param coursesActiveIds list of course IDs to check against
	 * @return true if there is a time conflict
	 */
	private static boolean timeConflictAdmin(Course courseToCheck, ArrayList<String> coursesActiveIds) {
		// if there are no other courses, there is no time conflict
		if (coursesActiveIds.isEmpty()) {
			return false; 
		} else {
			ArrayList<Course> coursesActive = new ArrayList<>();
			// iterate through courses
			for	(int j=0; j<courses.size(); j++) {
				// iterate through the entered course IDs
				for (int k = 0; k<coursesActiveIds.size(); k++) {
					// match each course ID to an actual Course
					if(courses.get(j).getId().equals(coursesActiveIds.get(k))) {
						coursesActive.add(courses.get(j));
					}
				}
			}
	
			// check days of week
			// check times
			String regex = ".*[" + courseToCheck.getDays() + "].*";
			for (int n=0; n < coursesActive.size(); n++) {
				// if the new course has any of the same days 
				if(coursesActive.get(n).getDays().matches(regex)) {
					// and if the new course starts within the other course there is a conflict
					if (coursesActive.get(n).getStartTimeNum() <= courseToCheck.getStartTimeNum() 
							&& courseToCheck.getStartTimeNum() < coursesActive.get(n).getEndTimeNum()) {
						return true;
					// else if the new course ends within the other course there is a conflict
					} else if (coursesActive.get(n).getStartTimeNum() < courseToCheck.getEndTimeNum() 
							&& courseToCheck.getEndTimeNum() <= coursesActive.get(n).getEndTimeNum()) {
						return true;
					// otherwise there is no time conflict 
					} else {
						return false;
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * Check time conflict course ID, and list of other course IDs
	 * @param courseToCheck course ID to see if time conflict
	 * @param coursesActiveIds list of course IDs to check against
	 * @return true if there is a time conflict
	 */
	private static boolean timeConflict(String courseIdToCheck, ArrayList<String> coursesActiveIds) {
		Course courseToCheck = null;
		// if there are no other courses, there is no time conflict
		if (coursesActiveIds.isEmpty()) {
			return false; 
		} else {
			// iterate through courses
			for (int i=0; i<courses.size(); i++) {
				// find the Course to check given the ID
				if(courses.get(i).getId().equals(courseIdToCheck)) {
					courseToCheck = courses.get(i);
				}
			}
			
			ArrayList<Course> coursesActive = new ArrayList<>();
			// iterate through courses
			for	(int j=0; j<courses.size(); j++) {
				// iterate through the entered course IDs
				for (int k = 0; k<coursesActiveIds.size(); k++) {
					// match each course ID to an actual Course
					if(courses.get(j).getId().equals(coursesActiveIds.get(k))) {
						coursesActive.add(courses.get(j));
					}
				}
			}
	
			// check days of week
			// check times
			String regex = ".*[" + courseToCheck.getDays() + "].*";
			// for any of the courses to check against
			for (int n=0; n < coursesActive.size(); n++) {
				// if the new course has any of the same days 
				if(coursesActive.get(n).getDays().matches(regex)) {
					// and if the new course starts within the other course there is a conflict
					if (coursesActive.get(n).getStartTimeNum() <= courseToCheck.getStartTimeNum() 
							&& courseToCheck.getStartTimeNum() < coursesActive.get(n).getEndTimeNum()) {
						return true;
					// else if the new course ends within the other course there is a conflict
					} else if (coursesActive.get(n).getStartTimeNum() < courseToCheck.getEndTimeNum() 
							&& courseToCheck.getEndTimeNum() <= coursesActive.get(n).getEndTimeNum()) {
						return true;
					// otherwise there is no time conflict 
					} else {
						return false;
					}
				}
			}
			return false;
		}
	}
	
	// METHOD TO VIEW ALL COURSES - USED BY STUDENT & ADMIN
	/**
	 * Print out all of the courses
	 */
	private static void viewAllCourses() {
		// for every course print it out
		for (int i=0; i<courses.size(); i++) {
			System.out.println(courses.get(i));
		}
	}
	
	// METHODS FOR LOGGING IN
	/**
	 * Login method if the user is a student.
	 * @param sc scanner
	 * @return true if student login is valid.
	 */
	private static boolean studentLogin(Scanner sc) {
		while (true) {
			// get username
			String username = getUsername(sc);
			// exit if user inputs q
			if (username.equals("q")) {
				return false;
			}
			// get password
			String password = getPassword(sc);
			// exit if user inputs q
			if (password.equals("q")) {
				return false;
			}
			// check if username and password are valid for a student
			for (int i=0; i<students.size(); i++) {
				Student s = students.get(i);
				// if the username and password match a student, return true
				if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
					// set the current user
					currentUser = s;
					System.out.println("Logged in successfully.");
					return true;
				}
			}
			// otherwise password and username could not login
			System.out.println("Username and/or password not valid.");
		}
	}
	
	/**
	 * Login method if the user is a professor.
	 * @param sc scanner
	 * @return true if professor login is valid.
	 */
	private static boolean profLogin(Scanner sc) {
		while (true) {
			// get username
			String username = getUsername(sc);
			// exit if user inputs q
			if (username.equals("q")) {
				return false;
			}
			// get password
			String password = getPassword(sc);
			// exit if user inputs q
			if (password.equals("q")) {
				return false;
			}
			// check if username and password are valid for professor
			for (int i=0; i<professors.size(); i++) {
				Professor p = professors.get(i);
				// if the username and password match a professor, return true
				if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
					// set the current user
					currentUser = p;
					System.out.println("Logged in successfully.");
					return true;
				}
			}
			// otherwise password and username could not login
			System.out.println("Username and/or password not valid.");
		}
	}
	
	/**
	 * Login method if the user is an admin.
	 * @param sc scanner
	 * @return true if admin login is valid.
	 */
	private static boolean adminLogin(Scanner sc) {
		while (true) {
			// get username
			String username = getUsername(sc);
			// exit if user wants to quit
			if (username.equals("q")) {
				return false;
			}
			// get password
			String password = getPassword(sc);
			// exit if user wants to quit
			if (password.equals("q")) {
				return false;
			}
			// check if username and password are valid for admin
			for (int i=0; i<admins.size(); i++) {
				Admin a = admins.get(i);
				// if the username and password match an admin, return true
				if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
					// set the current user
					currentUser = a;
					System.out.println("Logged in successfully.");
					return true;
				}
			}
			// otherwise password and username could not login
			System.out.println("Username and/or password not valid.");
		}
	}
	
	/**
	 * Ask for the user's password
	 * @param sc scanner
	 * @return password
	 */
	private static String getPassword(Scanner sc) {
		System.out.println("Please enter password, or type 'q' to quit.");
		String password = sc.next();
		return password;
	}
	
	/**
	 * Ask for the user's username
	 * @param sc scanner
	 * @return username
	 */
	private static String getUsername(Scanner sc) {
		System.out.println("Please enter username, or type 'q' to quit.");
		String username = sc.next();
		return username;
	}
	
	/**
	 * Method to make sure user input is a valid integer
	 * @param sc the scanner
	 * @param maxOptions of valid integers
	 * @param question to ask
	 * @return integer of option the user wants
	 */
	private static int getValidInput(Scanner sc, int maxOptions, String question) {
		
		int choice = 0;
		System.out.println(question);
		// while there is a next item in scanner
		while (sc.hasNext()) {
			// if it is an int
			if (sc.hasNextInt()) {
				// set to choice
				choice = sc.nextInt();
				// if the choice is in the proper range, return it
				if (choice >= 1 && choice <= maxOptions) {
					return choice;
				// else the integer choice is out of bounds
				} else {
					System.out.println("Your input is invalid, please enter an integer from 1 to " + maxOptions);
					continue;
				}
			// else they did not enter an integer
			} else {
				System.out.println("Your input is invalid, please enter an integer from 1 to " + maxOptions);
				sc.next();
			}
		}
		return choice;
	}
	
	// METHOD FOR FILE READING
	/**
	 * Read in all four text files.
	 * Save each file respectively as array 
	 * lists of courses, students, professors, 
	 * and admins.
	 */
	public static void readFiles() {
		try {
			// get course data
			File courseFile = new File("courseInfo.txt");
			Scanner courseReader = new Scanner(courseFile);
			while (courseReader.hasNextLine()) {
				String data = courseReader.nextLine();
				// split on the ; separator 
				String[] splitData = data.split("; ");
				// create new course 
				Course c = new Course(splitData[0], splitData[1], splitData[2], splitData[3], splitData[4], splitData[5], Integer.valueOf(splitData[6]));
				// add to array list of courses
				courses.add(c);
			}
			courseReader.close();
			
			// get student data
			File studentFile = new File("studentInfo.txt");
			Scanner studentReader = new Scanner(studentFile);
			while (studentReader.hasNextLine()) {
				String data = studentReader.nextLine();
				// split on the ; separator 
				String[] splitData = data.split("; ");
				// store all the courses and grades as string
				String courseGradesString = splitData[4];
				ArrayList<String[]> courseGrades = new ArrayList<>();
				// split by the comma separating the courses
				String[] splitCourseGrades = courseGradesString.split(", ");
				// for the number of courses
				for (int i=0; i<splitCourseGrades.length; i++) {
					// split the course ID and grade
					String[] courseGrade = splitCourseGrades[i].split(": ");
					courseGrades.add(courseGrade);
					
				}
				// create new instance of student
				Student s = new Student(splitData[0], splitData[1], splitData[2], splitData[3], courseGrades);
				// add to array list of students
				students.add(s);
			}
			studentReader.close();
			
			// get professor data
			File profFile = new File("profInfo.txt");
			Scanner profReader = new Scanner(profFile);
			while (profReader.hasNextLine()) {
				String data = profReader.nextLine();
				// split on the ; separator 
				String[] splitData = data.split("; ");
				// create new instance of professor
				Professor p = new Professor(splitData[1], splitData[0], splitData[2], splitData[3]);
				// add to array list of professors
				professors.add(p);
			}
			profReader.close();
			
			// get admin data
			File adminFile = new File("adminInfo.txt");
			Scanner adminReader = new Scanner(adminFile);
			while (adminReader.hasNextLine()) {
				String data = adminReader.nextLine();
				// split on the ; separator 
				String[] splitData = data.split("; ");
				// create new instance of admin
				Admin a = new Admin(splitData[0], splitData[1], splitData[2], splitData[3]);
				// add to array list of admin
				admins.add(a);
			}
			adminReader.close();
			// catch file not found exception
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file");
			e.printStackTrace();
		}
	}
	
	//METHODS FOR PRINTING MENUS
	/**
	 * Print admin menu for the student management system
	 * @param name of admin
	 */
	private static void printStudentMenu(String name) {
		System.out.println("---------------------------");
		System.out.println("Welcome, " + name + "!");
		System.out.println("---------------------------");
		System.out.println("1 -- View all courses");
		System.out.println("2 -- Enroll in a course");
		System.out.println("3 -- View your enrolled courses");
		System.out.println("4 -- Drop a course");
		System.out.println("5 -- View grades");
		System.out.println("6 -- Return to main menu");
		
	}
	
	/**
	 * Print professor menu for the student management system
	 * @param name of professor
	 */
	private static void printProfessorMenu(String name) {
		System.out.println("---------------------------");
		System.out.println("Welcome, " + name + "!");
		System.out.println("---------------------------");
		System.out.println("1 -- View your courses");
		System.out.println("2 -- View students in given course");
		System.out.println("3 -- Return to main menu");
		
	}
	
	/**
	 * Print student menu for the student management system
	 * @param name of student
	 */
	private static void printAdminMenu(String name) {
		System.out.println("---------------------------");
		System.out.println("Welcome, " + name + "!");
		System.out.println("---------------------------");
		System.out.println("1 -- View all courses");
		System.out.println("2 -- Add new courses");
		System.out.println("3 -- Delete courses");
		System.out.println("4 -- Add new professor");
		System.out.println("5 -- Delete professor");
		System.out.println("6 -- Add new student");
		System.out.println("7 -- Delete student");
		System.out.println("8 -- Return to main menu");
		
	}
	
	/**
	 * Print main menu of student management system
	 */
	private static void printMainMenu() {
		System.out.println("---------------------------");
		System.out.println("Students Management System");
		System.out.println("---------------------------");
		System.out.println("1 -- Login as a student");
		System.out.println("2 -- Login as a professor");
		System.out.println("3 -- Login as an admin");
		System.out.println("4 -- Quit the system");
	}

}
