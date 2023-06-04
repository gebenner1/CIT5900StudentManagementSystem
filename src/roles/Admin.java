package roles;

/**
 * Represents an admin, extends user.
 * @author gracebenner
 *
 */
public class Admin extends User {
	/**
	 * Construct an instance of admin.
	 * @param id of admin
	 * @param name of admin
	 * @param username of admin
	 * @param password of admin
	 */
	public Admin(String id, String name, String username, String password) {
		super(id, name, username, password);
	}
	
}
