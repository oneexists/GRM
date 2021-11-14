package edu.metrostate.ics370.grm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author skylar
 */
public class User {
	public enum Gender {
		Male, Female, Other
	}
	
	private String firstName;
	private String lastName;
	private String username;
	private LocalDate dateOfBirth;
	private Gender gender;
	private Collection<GameTag> personalTags;
	private Collection<Game> wishlist;
	private Collection<Game> hatelist;
	
	/**
	 * No-arg constructor
	 */
	public User() {

	}

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
		personalTags = new ArrayList<GameTag>();
		wishlist = new ArrayList<Game>();
		hatelist = new ArrayList<Game>();
	}

	/**
	 * @param game to add to hatelist of the user
	 */
	public void addHatelist(Game game) { this.hatelist.add(game); }

	/**
	 * @param tag to add to user
	 */
	public void addPersonalTags(GameTag tag) { 
		this.personalTags.add(tag); 
	}

	/**
	 * @param game to add to the wishlist of the user
	 */
	public void addWishlist(Game game) { this.wishlist.add(game); }

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
		if (hatelist != null) {
			return hatelist.toArray(new Game[hatelist.size()]); 			
		}
		return null;
	}

	/**
	 * @return the last name of the user
	 */
	public String getLastName() { return lastName; }

	/**
	 * @return the personalTags of the user
	 */
	public GameTag[] getPersonalTags() { 
		if (personalTags != null) {
			return personalTags.toArray(new GameTag[personalTags.size()]); 			
		}
		return null;
	}

	/**
	 * @return the username of the user
	 */
	public String getUsername() { return username; }

	/**
	 * @return the wishlist of the user
	 */
	public Game[] getWishlist() { 
		if (wishlist != null) {
			return wishlist.toArray(new Game[wishlist.size()]); 			
		}
		return null;
	}
	
	/**
	 * @param game to remove from the user's hatelist
	 */
	public void removeHatelist(Game game) {
		hatelist.remove(game);
	}

	/**
	 * @param tag to remove from the user
	 */
	public void removePersonalTags(GameTag tag) {
		personalTags.remove(tag);
	}

	/**
	 * @param game to remove from the user's wishlist
	 */
	public void removeWishlist(Game game) {
		wishlist.remove(game);
	}

}
