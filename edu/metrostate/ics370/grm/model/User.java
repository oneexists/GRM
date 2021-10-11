/**
 * 
 */
package edu.metrostate.ics370.grm.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author skylar
 *
 */
public class User implements Serializable, Comparable<User> {

	/**
	 * Version of the bean
	 */
	private static final long serialVersionUID = 202110001L;
	private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getUsername);
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(User user) {
		return USER_COMPARATOR.compare(this, user);
	}

}
