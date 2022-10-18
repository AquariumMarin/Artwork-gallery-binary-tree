//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ArtGallery.java
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class models the Artwork Gallery implemented as a binary search tree. The search criteria
 * include the year of creation of the artwork, the name of the artwork and its cost.
 * 
 * @author Marin Suzuki & Xingzhen Cai
 *
 */
public class ArtGallery {

  private BSTNode<Artwork> root; // root node of the artwork catalog BST
  private int size; // size of the artwork catalog tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this ArtworkGallery is empty, false otherwise
   */
  public boolean isEmpty() {

    if (this.size == 0) { // no artwork in it

      return true;

    }
    return false;
  }

  /**
   * Returns the number of artwork pieces stored in this BST.
   * 
   * @return the size of this ArtworkGallery
   */
  public int size() {

    return this.size;

  }

  /**
   * Checks whether this ArtworkGallery contains a Artwork given its name, year, and cost.
   * 
   * @param name name of the Artwork to search
   * @param year year of creation of the Artwork to search
   * @param cost cost of the Artwork to search
   * @return true if there is a match with this Artwork in this BST, and false otherwise
   */
  public boolean lookup(String name, int year, double cost) {

    Artwork newArtwork = new Artwork(name, year, cost);

    if (this.isEmpty()) {
      return false; // no item, so no found
    }

    return lookupHelper(newArtwork, this.root); // start from root

  }

  /**
   * Recursive helper method to search whether there is a match with a given Artwork in the subtree
   * rooted at current
   * 
   * @param target  a reference to a Artwork we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {

    // Base case1: if I fall off the tree
    if (current == null) {

      return false;

    }

    // Base case2: matched found
    if (target.equals(current.getData())) {

      return true;

    }

    // Recursive case:
    int compare = current.getData().compareTo(target);
    if (compare < 0) {

      // go to the right child
      return lookupHelper(target, current.getRight());

    } else {

      // go to the left child
      return lookupHelper(target, current.getLeft());

    }

  }

  /**
   * Adds a new artwork piece to this ArtworkGallery
   * 
   * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
   * @return true if the newArtwork was successfully added to this gallery, and returns false if
   *         there is a match with this Artwork already stored in gallery.
   * @throws NullPointerException if newArtwork is null
   */
  public boolean addArtwork(Artwork newArtwork) {

    // NullPointerException if newArtwork is null
    if (newArtwork == null) {

      throw new NullPointerException("Artwork is null");

    }

    // Case1: when the tree is empty
    if (this.isEmpty()) {

      this.root = new BSTNode<Artwork>(newArtwork);
      this.size++;
      return true; // successfully added

    } else {

      // successfully added
      if (addArtworkHelper(newArtwork, this.root) == true) {

        this.size++;
        return true;

      } else { // unsuccessful

        return false;

      }
    }
  }

  /**
   * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at current.
   * 
   * @param current    The "root" of the subtree we are inserting new Artwork into.
   * @param newArtwork The Artwork to be added to a BST rooted at current.
   * @return true if the newArtwork was successfully added to this ArtworkGallery, false if a match
   *         with newArtwork is already present in the subtree rooted at current.
   */
  protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {

    // this value is greater or less than the current node or not
    int compare = current.getData().compareTo(newArtwork);

    if (compare < 0) {

      // Greater: check if the right child id null or not
      if (current.getRight() == null) {

        // right child is null, then put it there
        current.setRight(new BSTNode<Artwork>(newArtwork));
        return true;

      } else {

        // not null
        return addArtworkHelper(newArtwork, current.getRight());

      }

    } else if (compare > 0) {

      // check if the left child is null or not
      if (current.getLeft() == null) {

        // left child is null, then put it there
        current.setLeft(new BSTNode<Artwork>(newArtwork));
        return true;

      } else {

        // not null
        return addArtworkHelper(newArtwork, current.getLeft());

      }
    } else {

      return false; // duplicate found

    }

  }

  /**
   * Gets the recent best Artwork in this BST (meaning the largest artwork in this gallery)
   * 
   * @return the best (largest) Artwork (the most recent, highest cost artwork) in this
   *         ArtworkGallery, and null if this tree is empty.
   */
  public Artwork getBestArtwork() {

    if (this.isEmpty()) {
      return null;
    }

    BSTNode<Artwork> current = this.root;

    while (current.getRight() != null) {

      current = current.getRight(); // move to right child

    }

    return current.getData();

  }

  /**
   * Returns a String representation of all the artwork stored within this BST in the increasing
   * order of year, separated by a newline "\n". For instance
   * 
   * "[(Name: Stars, Artist1) (Year: 1988) (Cost: 300)]" + "\n" + "[(Name: Sky, Artist1) (Year:
   * 2003) (Cost: 550)]" + "\n"
   * 
   * @return a String representation of all the artwork stored within this BST sorted in an
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a ArtworkGallery is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current Artwork within this BST (root of a subtree)
   * @return a String representation of all the artworks stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Artwork> current) {

    if (current == null) { // empty tree
      return "";
    }

    // node expression
    String representation = "[(Name: " + current.getData().getName() + ") (Year: "
        + current.getData().getYear() + ") (Cost: $" + current.getData().getCost() + ")]" + "\n";

    return toStringHelper(current.getLeft()) + representation + toStringHelper(current.getRight());

  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {

    // Case 1: the tree is empty
    if (this.isEmpty()) {

      return 0;

    } else {

      return heightHelper(this.root);

    }

  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a ArtworkGallery (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Artwork> current) {

    int height = 0;

    // base case: no more children
    if (current.getRight() == null && current.getLeft() == null) {

      height = 1;

    } else if (current.getRight() == null && current.getLeft() != null) {

      // it has only left child
      height = 1 + heightHelper(current.getLeft());

    } else if (current.getLeft() == null && current.getRight() != null) {

      // it has only right child
      height = 1 + heightHelper(current.getRight());

    } else if (current.getRight() != null && current.getLeft() != null) {

      // it has both children
      height = 1 + Math.max(heightHelper(current.getRight()), heightHelper(current.getLeft()));

    }

    return height;

  }

  /**
   * Search for all artwork objects created on a given year and have a maximum cost value.
   * 
   * @param year creation year of artwork
   * @param cost the maximum cost we would like to search for a artwork
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost. If no artwork satisfies the lookup query, this method returns an empty arraylist
   */
  public ArrayList<Artwork> lookupAll(int year, double cost) {

    ArrayList<Artwork> artworkList = new ArrayList<Artwork>();

    if (this.isEmpty())
      return artworkList;// if the tree is empty return empty arraylist

    BSTNode<Artwork> current = this.root;
    artworkList = lookupAllHelper(year, cost, current);

    return artworkList;
  }

  /**
   * Recursive helper method to lookup the list of artworks given their year of creation and a
   * maximum value of cost
   * 
   * @param year    the year we would like to search for a artwork
   * @param cost    the maximum cost we would like to search for a artwork
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost stored in the subtree rooted at current. If no artwork satisfies the lookup query,
   *         this method returns an empty arraylist
   */
  protected static ArrayList<Artwork> lookupAllHelper(int year, double cost,
      BSTNode<Artwork> current) {

    ArrayList<Artwork> artworkList = new ArrayList<Artwork>();
    if (current == null) {
      return artworkList;
    }

    if (current.getData().getYear() == year) {
      if (cost - current.getData().getCost() >= -0.0001) {
        artworkList.add(current.getData()); // a match found
      }
    }

    if (current.getData().getYear() < year) // move to right child
      artworkList.addAll(lookupAllHelper(year, cost, current.getRight()));

    if (current.getData().getYear() >= year) // move to left child
      artworkList.addAll(lookupAllHelper(year, cost, current.getLeft()));

    return artworkList;
  }

  /**
   * Buy an artwork with the specified name, year and cost. In terms of BST operation, this is
   * equivalent to finding the specific node and deleting it from the tree
   * 
   * @param name name of the artwork, artist
   * @param year creation year of artwork
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria
   */

  public void buyArtwork(String name, int year, double cost) {

    // create new artwork
    Artwork artwork = new Artwork(name, year, cost);

    root = buyArtworkHelper(artwork, root);
    size--;
  }

  /**
   * Recursive helper method to buy artwork given the name, year and cost. In terms of BST
   * operation, this is equivalent to finding the specific node and deleting it from the tree
   * 
   * @param target  a reference to a Artwork we are searching to remove in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria in the BST rooted at current
   */
  protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current) {

    if (current == null)
      throw new NoSuchElementException("No match found");

    if (current.getData().equals(target)) { // matched found

      if (current.getLeft() == null) {
        if (current.getRight() == null) {

          current = null; // case1: the node doesn't have child
          return null; // new root is null

        } else {

          // case2: the node only have right one child

          return current.getRight();
        }
      } else if (current.getRight() == null) {
        // case3: the node only have left child
        return current.getLeft();


      } else { // case4: the current node have two children
        BSTNode<Artwork> successor = new BSTNode<Artwork>(getSuccessor(current));
        buyArtworkHelper(successor.getData(), current);
        successor.setLeft(current.getLeft());
        successor.setRight(current.getRight());
        return successor;
      }

    } else if (current.getData().compareTo(target) < 0) { // search right
      current.setRight(buyArtworkHelper(target, current.getRight()));
    } else if (current.getData().compareTo(target) > 0) { // search left
      current.setLeft(buyArtworkHelper(target, current.getLeft()));
    }

    return current;
  }

  /**
   * Helper method to find the successor of a node while performing a delete operation (buyArtwork)
   * The successor is defined as the smallest key in the right subtree. We assume by default that
   * node is not
   * 
   * @param node node whose successor is to be found in the tree
   * @return return the key of the successor node
   */

  protected static Artwork getSuccessor(BSTNode<Artwork> node) {

    // it has only right child which does not have left child
    if (node.getRight().getLeft() == null)
      return node.getRight().getData();

    // it has right child which has left child
    node = node.getRight();
    while (node.getLeft() != null) {
      node = node.getLeft();
    }
    Artwork data = node.getData();
    node = null;
    return data;
  }

}
