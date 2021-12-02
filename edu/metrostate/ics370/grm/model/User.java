package edu.metrostate.ics370.grm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author skylar
 */
public class User implements Serializable, Comparable<User> {
	public enum Gender {
		Male, Female, Other
	}

	/**
	 * Version of the bean
	 */
	private static final long serialVersionUID = 202111002L;
	private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getUsername);
	
	private String firstName;
	private String lastName;
	private String username;
	private LocalDate dateOfBirth;
	private Gender gender;
	private GameTag[] personalTags;
	private Game[] wishlist;
	private Game[] hatelist;

	/**
	 * Convenience constructor, get user from database
	 * 
	 * @param username the username of the user
	 * @param firstName the first name of the user
	 * @param lastName the last name of the user
	 * @param dateOfBirth the date of birth of the user
	 * @param gender the gender of the user
	 */
	public User(String username, String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;		
	}

	/**
	 * @param game to add to hatelist of the user
	 */
	public void addHatelist(Game game) {
		ArrayList<Game> games = new ArrayList<Game>();
		if (hatelist != null) {
			for (Game hateGame : hatelist) {
				games.add(hateGame);
			}
		}
		games.add(game);
		hatelist = games.toArray(new Game[games.size()]);
	}

	/**
	 * @param tag to add to user
	 */
	public void addPersonalTags(GameTag tag) { 
		ArrayList<GameTag> tags = new ArrayList<GameTag>();
		if (personalTags != null) {
			for (GameTag personalTag : personalTags) {
				if (personalTag.getName().equals(tag.getName())) {
					personalTag.upVal();
				} else {
					tags.add(personalTag);					
				}
			}
		}
		tags.add(tag);
		personalTags = tags.toArray(new GameTag[tags.size()]);
	}

	/**
	 * @param game to add to the wishlist of the user
	 */
	public void addWishlist(Game game) { 
		ArrayList<Game> games = new ArrayList<Game>();
		if (wishlist != null) {
			for (Game wishGame : wishlist) {
				games.add(wishGame);
			}
		}
		games.add(game);
		wishlist = games.toArray(new Game[games.size()]);
	}

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
	public Game[] getHatelist() { 
		if (hatelist == null) {
			return null;
		}
		return hatelist.clone(); 
	}

	/**
	 * @return the last name of the user
	 */
	public String getLastName() { return lastName; }

	/**
	 * @return the personalTags of the user
	 */
	public GameTag[] getPersonalTags() { 
		if (personalTags == null) {
			return null;
		}
		return personalTags.clone(); 
	}

	/**
	 * @return the username of the user
	 */
	public String getUsername() { return username; }

	/**
	 * @return the wishlist of the user
	 */
	public Game[] getWishlist() { 
		if (wishlist == null) {
			return null;
		}
		return wishlist.clone();
	}

}
