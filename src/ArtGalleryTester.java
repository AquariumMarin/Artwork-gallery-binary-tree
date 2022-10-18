//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ArtGalleryTester.java
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 * 
 * @author Xingzhen Cai & Marin Suzuki
 *
 */

public class ArtGalleryTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {

    try {
      Artwork a = new Artwork("aaa", 2000, 300);
      Artwork b = new Artwork("aaa", 2000, 300);
      Artwork c = new Artwork("aa", 2000, 300);
      Artwork d = new Artwork("aaa", 2001, 300);
      Artwork e = new Artwork("aaa", 2000, 301);
      Artwork f = new Artwork("aab", 2000, 300);

      // test equals: case 1234
      if (!a.equals(b)) {
        return false;
      }
      if (!a.equals(e)) {
        return false;
      }
      if (a.equals(c)) {
        return false;
      }
      if (a.equals(d)) {
        return false;
      }

      // test compareTo case1234
      if (a.compareTo(b) != 0) {
        return false;
      }
      if (a.compareTo(c) <= 0) {
        return false;
      }
      if (a.compareTo(d) >= 0) {
        return false;
      }
      if (a.compareTo(e) >= 0) {
        return false;
      }
      if (a.compareTo(f) >= 0) {
        return false;
      }
    } catch (Exception e) {
      return false; // incorrect
    }
    return true;

  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {

    try {
      // (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty, * and
      // that its string representation is an empty string "".

      {
        ArtGallery gallery = new ArtGallery();
        if (gallery.size() != 0) {
          return false;
        }
        if (!gallery.toString().equals("")) {
          System.out.println(gallery.toString());
          return false;
        }
      }

      // (2) try adding one artwork and then check that the addArtwork() method call returns true,
      // the tree is not empty, its size is 1, and the .toString() called on the tree returns the
      // expected output.
      {
        ArtGallery gallery = new ArtGallery();

        try {
          gallery.addArtwork(null);
          return false; // incorrect
        } catch (NullPointerException e) {

        }

        Artwork a = new Artwork("a", 2001, 300.0);
        gallery.addArtwork(a); // size should be 1

        if (gallery.size() != 1) {
          return false;
        }
        if (!gallery.toString().trim().equals("[(Name: a) (Year: 2001) (Cost: $300.0)]")) {
          System.out.println(gallery.toString());
          return false;
        }

        // (3) Try adding another artwork which is smaller that the artwork at the root,
        Artwork b = new Artwork("b", 2000, 200.0);
        gallery.addArtwork(b); // size should be 2

        if (gallery.size() != 2) {
          System.out.println(gallery.size());
          return false;
        }

        if (!gallery.toString().trim().equals("[(Name: b) (Year: 2000) (Cost: $200.0)]" + "\n"
            + "[(Name: a) (Year: 2001) (Cost: $300.0)]")) {

          System.out.println(gallery.toString());
          return false;
        }

        // (4) Try adding a third artwork which is greater than the one at the root,
        Artwork c = new Artwork("c", 200000, 20000.0);
        gallery.addArtwork(c); // size should be 3

        if (gallery.size() != 3) {
          System.out.println(gallery.size());
          return false;
        }

        if (!gallery.toString().trim()
            .equals("[(Name: b) (Year: 2000) (Cost: $200.0)]" + "\n"
                + "[(Name: a) (Year: 2001) (Cost: $300.0)]" + "\n"
                + "[(Name: c) (Year: 200000) (Cost: $20000.0)]")) {

          System.out.println(gallery.toString());
          return false;
        }

        // (5) Try adding at least two further artwork such that one must be added at the left
        // subtree, and the other at the right subtree. For all the above scenarios, and
        // more, double check each time that size() method returns the expected value, the add
        // method call returns true, and that the .toString() method returns the expected string
        // representation of the contents of the binary search tree in an increasing order from the
        // smallest to the greatest artwork with respect to year, cost, and then name.
        Artwork d = new Artwork("d", 1500, 200.0);
        gallery.addArtwork(d); // size should be 4

        if (gallery.size() != 4) {
          System.out.println(gallery.size());
          return false;
        }

        if (!gallery.toString().trim()
            .equals("[(Name: d) (Year: 1500) (Cost: $200.0)]" + "\n"
                + "[(Name: b) (Year: 2000) (Cost: $200.0)]" + "\n"
                + "[(Name: a) (Year: 2001) (Cost: $300.0)]" + "\n"
                + "[(Name: c) (Year: 200000) (Cost: $20000.0)]")) {

          System.out.println(gallery.toString());
          return false;
        }

        // add larger
        Artwork e = new Artwork("e", 200000, 300000.0);
        gallery.addArtwork(e); // size should be 5

        if (gallery.size() != 5) {
          System.out.println(gallery.size());
          return false;
        }

        if (!gallery.toString().trim()
            .equals("[(Name: d) (Year: 1500) (Cost: $200.0)]" + "\n"
                + "[(Name: b) (Year: 2000) (Cost: $200.0)]" + "\n"
                + "[(Name: a) (Year: 2001) (Cost: $300.0)]" + "\n"
                + "[(Name: c) (Year: 200000) (Cost: $20000.0)]" + "\n"
                + "[(Name: e) (Year: 200000) (Cost: $300000.0)]")) {

          System.out.println(gallery.toString());
          return false;
        }

        // (6) Try adding a artwork already stored in the tree. Make sure that the addArtwork()
        // method call returned false, and that the size of the tree did not change.
        if (gallery.addArtwork(d) != false) {
          return false; // already there!
        }

        if (gallery.size() != 5) { // size does not change
          return false;
        }

      }

    } catch (Exception e) {

      return false; // incorrect

    }

    return true; // passed
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {

    try {
      ArtGallery gallery = new ArtGallery();

      // (1) Create a new ArtworkGallery. Then, check that calling the lookup() method on an empty
      // ArtworkGallery returns false. nothing in it
      if (gallery.lookup("a", 1001, 30)) {

        return false;

      }

      // (2) Consider a ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to
      // call lookup() method to search for the artwork having a match at the root of the tree.
      // (3) Then, search for a artwork at the right and left subtrees at different levels
      // considering successful and unsuccessful search operations.

      Artwork a = new Artwork("a", 2001, 300);
      Artwork b = new Artwork("b", 2002, 300);
      Artwork c = new Artwork("c", 2003, 300);
      Artwork d = new Artwork("d", 1980, 300);
      Artwork e = new Artwork("e", 1999, 300);

      gallery.addArtwork(c);
      gallery.addArtwork(b);
      gallery.addArtwork(d);
      gallery.addArtwork(a);
      gallery.addArtwork(e);

      if (!gallery.lookup("d", 1980, 300) || !gallery.lookup("a", 2001, 300)
          || !gallery.lookup("b", 2002, 300) || !gallery.lookup("c", 2003, 300)
          || !gallery.lookup("e", 1999, 300)) {
        return false;
      }

      if (gallery.lookup("dd", 19000, 400) != false) {
        return false;
      }

    } catch (Exception e) {
      return false; // false
    }

    return true; // passed


  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*)
   * (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {

    try {

      // case1: create gallery which has height of 4
      {
        ArtGallery gallery = new ArtGallery();
        gallery.addArtwork(new Artwork("A, Marin", 1937, 30000));
        gallery.addArtwork(new Artwork("B, Maddie", 1937, 20000));
        gallery.addArtwork(new Artwork("Nerdy, Computer Science", 1942, 40000));
        gallery.addArtwork(new Artwork("High Salary, CS major1", 1503, 10000));
        gallery.addArtwork(new Artwork("We are happy, CS major2", 2000, 50000));
        gallery.addArtwork(new Artwork("We can do this, CS major3", 2021, 60800));
        gallery.addArtwork(new Artwork("We can do this, CS major3", 1940, 60800));

        if (gallery.height() != 4) {

          System.out.println(gallery.height());
          return false;

        }
      }

      // case2: crate gallery which has height of 0
      {
        ArtGallery gallery = new ArtGallery();

        if (gallery.height() != 0) {

          System.out.println(gallery.height());
          return false;

        }
      }

      // case3:(3) it has only one node
      {
        ArtGallery gallery = new ArtGallery();
        gallery.addArtwork(new Artwork("A, Marin", 1937, 30000));

        if (gallery.height() != 1) {

          System.out.println(gallery.height());
          return false;

        }
      }

    } catch (Exception e) {
      return false; // incorrect
    }

    return true; // passed

  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {

    try {

      // case 1: successful case1 unbalanced
      {
        ArtGallery gallery = new ArtGallery();

        Artwork a = new Artwork("a", 2001, 300);
        Artwork b = new Artwork("b", 2002, 300);
        Artwork c = new Artwork("c", 2003, 300);
        Artwork d = new Artwork("d", 2004, 300);
        Artwork e = new Artwork("e", 2005, 300);
        Artwork f = new Artwork("f", 2006, 300);


        gallery.addArtwork(c);
        gallery.addArtwork(b);
        gallery.addArtwork(d);
        gallery.addArtwork(a);
        gallery.addArtwork(e);
        gallery.addArtwork(f);

        Artwork expectedBestWork = f;

        if (!gallery.getBestArtwork().equals(expectedBestWork)) {

          System.out.println(gallery.getBestArtwork());
          return false;

        }

      }

      // case 2: successful case2 unbalanced
      {
        ArtGallery gallery = new ArtGallery();

        Artwork a = new Artwork("a", 2000, 50000);
        Artwork b = new Artwork("b", 2000, 3000);
        Artwork c = new Artwork("c", 2000, 3005);
        Artwork d = new Artwork("d", 2000, 30550);
        Artwork e = new Artwork("e", 2000, 30550);
        Artwork f = new Artwork("f", 2000, 30550);


        gallery.addArtwork(c);
        gallery.addArtwork(b);
        gallery.addArtwork(d);
        gallery.addArtwork(a);
        gallery.addArtwork(e);
        gallery.addArtwork(f);

        Artwork expectedBestWork = a;

        if (!gallery.getBestArtwork().equals(expectedBestWork)) {

          System.out.println(gallery.getBestArtwork());
          return false;

        }

      }

      // case 3: successful case2 balanced
      {
        ArtGallery gallery = new ArtGallery();

        Artwork a = new Artwork("a", 2990, 50000);
        Artwork b = new Artwork("b", 200000, 3000);
        Artwork c = new Artwork("c", 20050, 3005);
        Artwork d = new Artwork("d", 20050, 30550);
        Artwork e = new Artwork("e", 20400, 30550);
        Artwork f = new Artwork("f", 200220, 30550);


        gallery.addArtwork(c);
        gallery.addArtwork(b);
        gallery.addArtwork(d);
        gallery.addArtwork(a);
        gallery.addArtwork(e);
        gallery.addArtwork(f);

        Artwork expectedBestWork = f;

        if (!gallery.getBestArtwork().equals(expectedBestWork)) {

          System.out.println(gallery.getBestArtwork());
          return false;

        }
      }

      // case4: empty case
      {
        ArtGallery gallery = new ArtGallery();

        if (gallery.getBestArtwork() != null) {

          System.out.println(gallery.getBestArtwork());
          return false;

        }
      }

    } catch (Exception e) {

      return false; // incorrect

    }

    return true; // test passed
  }


  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty
   * arraylist when called on an empty tree. (2) Ensures that the ArtworkGallery.lookupAll() method
   * returns an array list which contains all the artwork satisfying the search criteria of year and
   * cost, when called on a non empty artwork tree with one match, and two matches and more. Vary
   * your search criteria such that the lookupAll() method must check in left and right subtrees.
   * (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
   * on a non-empty artwork tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {

    try {
      // case1:empty tree
      ArtGallery gallery = new ArtGallery();

      if (!gallery.lookupAll(2000, 300).isEmpty()) {
        return false;
      }

      // (2) Ensures that the ArtworkGallery.lookupAll() method
      // returns an array list which contains all the artwork satisfying the search criteria of year
      // and cost, when called on a non empty artwork tree with one match, and two matches and more.
      // Vary your search criteria such that the lookupAll() method must check in left and right
      // subtrees.
      // case2:contains all the artwork
      Artwork a = new Artwork("a", 3000, 50000);
      Artwork b = new Artwork("b", 3000, 50000);
      Artwork c = new Artwork("c", 3000, 50000);
      Artwork d = new Artwork("d", 20050, 30550);
      Artwork e = new Artwork("e", 20400, 30550);
      Artwork f = new Artwork("f", 200220, 30550);

      gallery.addArtwork(c);
      gallery.addArtwork(b);
      gallery.addArtwork(d);
      gallery.addArtwork(a);
      gallery.addArtwork(e);
      gallery.addArtwork(f);

      String artworks = "";
      for (Artwork artwork : gallery.lookupAll(3000, 55000)) {
        artworks += artwork + " ";
      }

      String expected = "[(Name: c) (Year: 3000) (Cost: $50000.0)] "
          + "[(Name: b) (Year: 3000) (Cost: $50000.0)] "
          + "[(Name: a) (Year: 3000) (Cost: $50000.0)] ";

      if (!artworks.equals(expected)) {
        return false;
      }

      // (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when
      // called on a non-empty artwork tree with no search results found.
      String artworks1 = "";
      for (Artwork artwork : gallery.lookupAll(3000000, 50)) {
        artworks1 += artwork + " ";
      }

      String expected1 = "";

      if (!artworks1.equals(expected1)) {
        return false;
      }

    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {

    try {
      ArtGallery gallery = new ArtGallery();

      if (gallery.lookup("a", 1001, 30)) {
        return false;
      }

      Artwork a = new Artwork("a", 2001, 300);
      Artwork b = new Artwork("b", 2002, 300);
      Artwork c = new Artwork("c", 2003, 300);
      Artwork d = new Artwork("d", 2004, 300);
      Artwork e = new Artwork("e", 2005, 300);
      Artwork f = new Artwork("f", 2006, 300);


      gallery.addArtwork(c);
      gallery.addArtwork(b);
      gallery.addArtwork(d);
      gallery.addArtwork(a);
      gallery.addArtwork(e);
      gallery.addArtwork(f);

      // (1) Buying artwork that is at leaf node
      gallery.buyArtwork("f", 2006, 300);
      if (!gallery.toString().trim()
          .equals("[(Name: a) (Year: 2001) (Cost: $300.0)]\n"
              + "[(Name: b) (Year: 2002) (Cost: $300.0)]\n"
              + "[(Name: c) (Year: 2003) (Cost: $300.0)]\n"
              + "[(Name: d) (Year: 2004) (Cost: $300.0)]\n"
              + "[(Name: e) (Year: 2005) (Cost: $300.0)]")) {
        return false;
      }

      if (gallery.lookup("f", 2006, 300)) {
        return false;
      }


      // System.out.println(gallery.toString());
      // 2) Buying artwork at non-leaf node
      gallery.buyArtwork("b", 2002, 300);

      if (gallery.lookup("b", 2002, 300)) {
        return false; // no more b
      }
      if (!gallery.toString().trim()
          .equals("[(Name: a) (Year: 2001) (Cost: $300.0)]\n"
              + "[(Name: c) (Year: 2003) (Cost: $300.0)]\n"
              + "[(Name: d) (Year: 2004) (Cost: $300.0)]\n"
              + "[(Name: e) (Year: 2005) (Cost: $300.0)]")) {
        return false;
      }

      // (3) ensures that the ArtworkGallery.buyArtwork() method throws
      // a NoSuchElementException when called on an artwork that is not present in the BST
      try {
        gallery.buyArtwork("c", 2002, 300);
        return false;
      } catch (NoSuchElementException e1) {
      } catch (Exception e1) {
        return false;
      }

    } catch (Exception e) {
      return false; // incorrect
    }

    return true;
  }

  public static boolean additional() {
    ArtGallery gallery = new ArtGallery();
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
    System.out.println(gallery);
    gallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
    gallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
    System.out.println("==============================================================");
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
    System.out.println(gallery);

    gallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
    gallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
    gallery.addArtwork(new Artwork("Whistler, Abbott", 1871, 5000));
    gallery.addArtwork(new Artwork("Amazone, Tsalapatanis", 2021, 6080));

    System.out.println("==============================================================");
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
    System.out.println(gallery);
    gallery.addArtwork(new Artwork("Persistence of Memory, Dali", 1931, 7000));
    gallery.addArtwork(new Artwork("Der Schrei, Silber", 2019, 12160));
    gallery.addArtwork(new Artwork("Gothic, Wood", 1930, 6000));
    gallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
    gallery.addArtwork(new Artwork("Cantabrico, Torices", 2021, 3870));

    System.out.println("==============================================================");
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
    System.out.println(gallery);
    try {
      System.out.println("This gallery contains (Mona Lisa, DaVinci, 1503, 1000): "
          + gallery.lookup("Mona Lisa, DaVinci", 1503, 1000));
      System.out.println("This gallery contains (Whistler, Abbott, 1871, 5000): "
          + gallery.lookup("Whistler, Abbott", 1871, 5000));
      System.out.println();
      System.out.println("This gallery contains (Chaplin, Brainwash\", 2020, 9090): "
          + gallery.lookup("Chaplin, Brainwash", 2020, 9090));
      System.out.println();
      System.out.println("Best (greatest) artwork: " + gallery.getBestArtwork());
      System.out.println();
      System.out.println(
          "Lookup query: search for the artworks of 2021 whose costs do not exceed $5000.00:");
      System.out.println(gallery.lookupAll(2021, 5000));
      System.out.println();
      System.out.println(
          "Lookup query: search for the artworks of 2021 whose costs do not exceed $10000.00:");
      System.out.println(gallery.lookupAll(2021, 10000));
      System.out.println();
      System.out.println("Buy \"Der Schrei, Silber\", 2019, 12160:");
      gallery.buyArtwork("Der Schrei, Silber", 2019, 12160);
      System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
      System.out.println(gallery);
      System.out.println();
      System.out.println("Buy \"Mona Lisa, DaVinci\", 1503, 1000:");
      gallery.buyArtwork("Mona Lisa, DaVinci", 1503, 1000);
      System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
      System.out.println(gallery);
      System.out.println();
      System.out.println("Buy \"Guernica, Picasso\", 1937, 3000:");
      gallery.buyArtwork("Guernica, Picasso", 1937, 3000);
      System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
      System.out.println(gallery);
      System.out.println();
      System.out.println("Buy \"Mona Lisa, DaVinci\", 1503, 1000:");
      gallery.buyArtwork("Mona Lisa, DaVinci", 1503, 1000);
    } catch (NoSuchElementException e) {

      System.out.println(e.getMessage());
    }

    return true;
  }


  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   * 
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {

    return testArtworkCompareToEquals() && testAddArtworkToStringSize() && testLookup()
        && testHeight() && testGetBestArtwork() && testLookupAll() && testBuyArtwork();
    // && additional();

  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testArworkCompareToEquals(): " + testArtworkCompareToEquals());
    System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
    System.out.println("testLookupAll(): " + testLookupAll());
    System.out.println("testBuyArtwork(): " + testBuyArtwork());
    System.out.println("runAllTests(): " + runAllTests());
    // System.out.println(additional());
  }

}
