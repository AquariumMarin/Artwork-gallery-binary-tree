//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Artwork.java
// Course: CS 300 Spring 2022
//
// Author: Marin Suzuki
// Email: msuzuki9@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Xingzhen Cai
// Partner Email: xcai79@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models an artwork piece
 * 
 * @author Marin Suzuki & Xingzhen Cai
 *
 */
public class Artwork implements Comparable<Artwork> {

  private String name; // name of the artwork piece, artist
  private int year; // year in which the artwork was created
  private double cost; // cost of the artwork

  /**
   * Creates a new art piece with given attributes
   * 
   * @param name name of the artwork piece, artist
   * @param year year of creation of this artwork piece
   * @param cost the cost of this artwork piece in dollars
   * @throws an IllegalArgumentException if year is less than 1000, if cost is negative or zero or
   *            if name is null or an empty string
   */
  public Artwork(String name, int year, double cost) {
    // check the validity of the input parameters
    if (year < 1000)
      throw new IllegalArgumentException("Invalid year of creation. Too old.");
    if (cost <= 0.0)
      throw new IllegalArgumentException("Invalid cost. The cost must be positive");
    if (name == null || name.isBlank())
      throw new IllegalArgumentException("Invalid artwork name");
    // assign the artwork attributes
    this.year = year;
    this.cost = cost;
    this.name = name;
  }

  /**
   * Gets the cost of this artwork
   * 
   * @return the cost of artwork
   */
  public double getCost() {

    return cost;

  }

  /**
   * Gets the year when this artwork was created
   *
   * @return the year of creation of this artwork
   */
  public int getYear() {
    return year;
  }

  /**
   * Gets the name of the artwork piece, artist
   * 
   * @return the name of the artwork piece, artist
   */
  public String getName() {
    return name;
  }

  /**
   * Compares two artwork pieces for ordering with respect to their years, costs, and names
   * 
   * @return 0 if otherArtwork has the same year of creation, same name, and same cost as this
   *         artwork; an integer less then 0 if this artwork is older than the otherArtwork, or if
   *         they were created in the same year but this artwork is less expensive than the
   *         otherArtwork, or if they have the same year and the same cost and this artwork has a
   *         lower lexical order name than otherArtwork name; String comparisons are case-sensitive.
   *         otherwise returns an integer greater than 0.
   * @author Marin & Cai
   */
  @Override
  public int compareTo(Artwork otherArtwork) {

    // if otherArtwork has the same year of creation, same name, and same cost
    if (this.getYear() == otherArtwork.getYear() && this.getCost() == otherArtwork.getCost()
        && this.getName().equals(otherArtwork.getName())) {

      return 0;

    } else if (this.getYear() < otherArtwork.getYear()) {

      // this art is older
      return -1;

    } else if ((this.getYear() == otherArtwork.getYear())
        && (this.getCost() < otherArtwork.getCost())) {

      return -1; // they were created in the same year but this artwork is less expensive

    } else if ((this.getYear() == otherArtwork.getYear())
        && (this.getCost() == otherArtwork.getCost())
        && (this.getName().compareTo(otherArtwork.getName()) < 0)) {
      return -1;

    } else { // other case (other art work is larger)

      return 1;

    }

  }

  /**
   * Checks whether this artwork equals to another object passed as input
   * 
   * @param obj other object to compare
   * @return true if obj refers to an Artwork object with the same name and year of creation
   * @author Marin and Cai
   */
  @Override
  public boolean equals(Object obj) {

    if (obj instanceof Artwork) { // check if obj is object of Artwork

      Artwork obj1 = (Artwork) obj; // make object casted to Artwork
      if (this.getYear() == obj1.getYear() && this.getName().equals(obj1.getName())) {
        return true; // equal

      }
    }

    return false; // not equal
  }

  /**
   * Returns a String representation of the artwork attributes
   * 
   * @return the artwork as a String in the format
   */
  public String toString() {
    return "[(Name: " + name + ") (Year: " + year + ") (Cost: $" + cost + ")]";
  }

}

