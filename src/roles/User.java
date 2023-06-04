package roles;

/**
 * Represents an instance of a user.
 * @author gracebenner
 *
 */
public abstract class User {
	
	// instance variables common to all users
	/**
	 * ID of user
	 */
	private String id;
	/**
	 * Name of user
	 */
	private String name;
	/**
	 * Username of user
	 */
	private String username;
	/**
	 * Password of user
	 */
	private String password;
	
	// constructor
	/**
	 * Construct an instance of a user
	 * @param id of user
	 * @param name of user
	 * @param username of user
	 * @param password of user
	 */
	public User(String id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	// methods (only getters)
	/**
	 * Get the name of user
	 * @return name of user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the username of user
	 * @return username of user
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get the password of user
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Get the ID of user
	 * @return ID of user
	 */
	public String getId() {
		return id;
	}
	
}
