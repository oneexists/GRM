package edu.metrostate.ics370.grm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author skylar
 *
 */
public class User implements Serializable, Comparable<User> {
	public enum Gender {
		Male, Female, Other
	}

	/**
	 * Version of the bean
	 */
	private static final long serialVersionUID = 202110002L;
	private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getUsername);
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private LocalDate dateOfBirth;
	private Gender gender;
	private Collection<GameTag> personalTags;
	private Collection<Game> wishlist;
	private Collection<Game> hatelist;
	
	/**
	 * No-arg constructor
	 */
	public User() {
		personalTags = new TreeSet<GameTag>();
		wishlist = new TreeSet<Game>();
		hatelist = new TreeSet<Game>();
	}
	
	/**
	 * Convenience constructor, create new user
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	/**
	 * @param game to add to hatelist of the user
	 */
	public void addHatelist(Game game) { this.hatelist.add(game); }

	/**
	 * @param tag to add to user
	 */
	public void addPersonalTags(GameTag tag) { this.personalTags.add(tag); }

	/**
	 * @param game to add to the wishlist of the user
	 */
	public void addWishlist(Game game) { this.wishlist.add(game); }

	@Override
	public int compareTo(User user) {
		return USER_COMPARATOR.compare(this, user);
	}

	/**
	 * @return the dateOfBirth of the user
	 */
	public LocalDate getDateOfBirth() { return dateOfBirth; }

	/**
	 * @return the first name of the user
	 */
	public String getFirstName() { return firstName; }
	
	/**
	 * @return the gender of the user
	 */
	public Gender getGender() { return gender; }

	/**
	 * @return the hatelist of the user
	 */
	public Game[] getHatelist() { return hatelist.toArray(new Game[hatelist.size()]); }

	/**
	 * @return the last name of the user
	 */
	public String getLastName() { return lastName; }

	/**
	 * @return the personalTags of the user
	 */
	public GameTag[] getPersonalTags() { return personalTags.toArray(new GameTag[personalTags.size()]); }
	/**
	 * @return the username of the user
	 */
	public String getUsername() { return username; }

	/**
	 * @return the wishlist of the user
	 */
	public Game[] getWishlist() { return wishlist.toArray(new Game[wishlist.size()]); }
	
	/**
	 * @param game to remove from the user's hatelist
	 */
	public void removeHatelist(Game game) {
		// TODO remove game from hatelist
	}

	/**
	 * @param tag to remove from the user
	 */
	public void removePersonalTags(GameTag tag) {
		// TODO remove tag from user
	}

	/**
	 * @param game to remove from the user's wishlist
	 */
	public void removeWishlist(Game game) {
		// TODO remove game from wishlist
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

	/**
	 * @param firstName the first name to set
	 */
	public void setFirstName(String firstName) { this.firstName = firstName; }

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) { this.gender = gender; }

	/**
	 * @param lastName the last name to set
	 */
	public void setLastName(String lastName) { this.lastName = lastName; }

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) { this.password = password; }
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) { this.username = username; }

	/**
	 * Validates username and password of user
	 * 
	 * @param user
	 * @param pass
	 * @return {@code true} if username and password are valid
	 */
	public boolean validate(String user, String pass) {
		if (this.username == user && this.password == pass) {
			return true;
		}
		return false;
	}

}
