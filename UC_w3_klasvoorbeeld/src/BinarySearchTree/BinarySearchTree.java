package BinarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// of this package.
/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 * @author erik van der Schriek : uitbreiding met tree walks
 */
public class BinarySearchTree {

    /* The tree root. */
    protected BinaryNode root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /** 
     * driver methode: start met aanroep van recursieve methode
     * geeft maximale diepte van de boom
     */
    public int depth() {
        if (this.root != null) {
            return depth(this.root);
        } else {
            return 0;
        }
    }

    /**
     * recursieve methode voor bepalen diepte van de boom
     * @param t
     * @return 
     */
    private int depth(BinaryNode t) {
        int returnvalue = 1;
        if (t.left != null) {
            returnvalue = returnvalue + this.depth(t.left);
        }
        if (t.right != null) {
            returnvalue = returnvalue + this.depth(t.right);
        }
        return returnvalue;
    }

    /**
     * Driver methode: roept recursieve methode aan
     * geeft aantal BinaryNodes in de boom 
     * @return
     */
    public int size() {
        if (this.root != null) {
            return size(this.root);
        } else {
            return 0;
        }
    }

    /**
     * recursieve methode
     * @param t
     * @return 
     */
    private int size(BinaryNode t) {
        int returnvalue = 1;
        if (t.left != null) {
            returnvalue += this.size(t.left);
        }
        if (t.right != null) {
            returnvalue += this.size(t.right);
        }
        return returnvalue;
    }

    /**
     * geef elementen terug in levelorder. Drivermethode
     * boom bestaat uit levels:
     * 0 : null
     * 1 : root
     * 2 : children van root
     * 
     * de knooppunten uit de levels zijn in de list te vinden op positie:
     * 0 : p < 2^0
     * 1 : 2^0 <= p < 2^1
     * 2 : 2^1 <= p < 2^2
     * 
     * L : 2^(L-1) <= p < 2^L
     *  
     * er wordt ruimte gereserveerd voor het maximum aantal mogelijke nodes
     * 
     * @return
     */
    public List levelOrderWalk() {
        int maxNodes = (int) Math.pow(2, this.depth());
        List<Comparable> list = new ArrayList<Comparable>(maxNodes);
        // vul list met dummy objecten
        // Collections.fill(list, new String(" "));  DIT WERKT NIET????
        for (int i = 0; i < maxNodes; i++) {
            list.add(null);
        }
        list.set(0, " ");
        this.levelOrderWalk(list, this.root, 1);
        return list;

    }

    private void levelOrderWalk(List list, BinaryNode t, int position) {
        if (t != null) {
            try {
                list.set(position, t.element);
            } catch (IndexOutOfBoundsException ie) {
                ie.getCause();
            }
            if (t.left != null) {
                this.levelOrderWalk(list, t.left, 2 * position);
            }
            if (t.right != null) {
                this.levelOrderWalk(list, t.right, 2 * position + 1);
            }
        }
    }

    /**
     * deze methode berekent het level van een knoop in de boom op basis van 
     * een positie.
     */
    public int positionToLevel(int position) {
        int returnValue = 0;
        double d = Math.log10(position) / Math.log10(2);
        if (d == Double.NaN) {
            returnValue = 0;
        } else {
            returnValue = (int) d;
        }
        return returnValue;
    }

    /**
     * drivermethode voor preorderwalk
     * @return 
     */
    public List preOrderWalk() {
        List<Comparable> list = new ArrayList<Comparable>(this.size());
        this.preOrderWalk(list, this.root);
        return list;
    }

    private void preOrderWalk(List list, BinaryNode t) {
        if (t != null) {
            list.add(t.element);
            if (t.left != null) {
                this.preOrderWalk(list, t.left);
            }
            if (t.right != null) {
                this.preOrderWalk(list, t.right);
            }
        }
    }

    /**
     * drivermethode voor postorderwalk
     * @return 
     */
    public List postOrderWalk() {
        List<Comparable> list = new ArrayList<Comparable>(this.size());
        this.postOrderWalk(list, this.root);
        return list;
    }

    private void postOrderWalk(List list, BinaryNode t) {
        if (t != null) {
            if (t.left != null) {
                this.postOrderWalk(list, t.left);
            }
            if (t.right != null) {
                this.postOrderWalk(list, t.right);
            }
            list.add(t.element);
        }
    }

    /**
     * drivermethode voor inorderwalk
     * @return 
     */
    public List inOrderWalk() {
        List<Comparable> list = new ArrayList<Comparable>(this.size());
        this.inOrderWalk(list, this.root);
        return list;
    }

    private void inOrderWalk(List list, BinaryNode t) {
        if (t != null) {
            if (t.left != null) {
                this.inOrderWalk(list, t.left);
            }
            list.add(t.element);
            if (t.right != null) {
                this.inOrderWalk(list, t.right);
            }
        }
    }

    /**
     * Insert into the tree.
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void insert(Comparable x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree..
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove(Comparable x) {
        root = remove(x, root);
    }

    /**
     * Remove minimum item from the tree.
     * @throws ItemNotFoundException if tree is empty.
     */
    public void removeMin() {
        root = removeMin(root);
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public Comparable findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public Comparable findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public Comparable find(Comparable x) {
        return elementAt(find(x, root));
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private Comparable elementAt(BinaryNode t) {
        return t == null ? null : t.element;
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode insert(Comparable x, BinaryNode t) {
        if (t == null) {
            t = new BinaryNode(x);
        } else if (x.compareTo(t.element) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            t.right = insert(x, t.right);
        } else {
            throw new DuplicateItemException(x.toString());  // Duplicate
        }
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode remove(Comparable x, BinaryNode t) {
        if (t == null) {
            throw new ItemNotFoundException(x.toString());
        }
        if (x.compareTo(t.element) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = removeMin(t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode removeMin(BinaryNode t) {
        if (t == null) {
            throw new ItemNotFoundException();
        } else if (t.left != null) {
            t.left = removeMin(t.left);
            return t;
        } else {
            return t.right;
        }
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected BinaryNode findMin(BinaryNode t) {
        if (t != null) {
            while (t.left != null) {
                t = t.left;
            }
        }

        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode findMax(BinaryNode t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode find(Comparable x, BinaryNode t) {
        while (t != null) {
            if (x.compareTo(t.element) < 0) {
                t = t.left;
            } else if (x.compareTo(t.element) > 0) {
                t = t.right;
            } else {
                return t;    // Match
            }
        }

        return null;         // Not found
    }

    // Test program
    public static void main(String[] args) {
        BinarySearchTree t = new BinarySearchTree();
        final int NUMS = 4000;
        final int GAP = 37;

        System.out.println("Checking... ");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
            t.insert(new Integer(i));
        }

        for (int i = 1; i < NUMS; i += 2) {
            t.remove(new Integer(i));
        }

        if (((Integer) (t.findMin())).intValue() != 2
                || ((Integer) (t.findMax())).intValue() != NUMS - 2) {
            System.out.println("FindMin or FindMax error!");
        }

        for (int i = 2; i < NUMS; i += 2) {
            if (((Integer) (t.find(new Integer(i)))).intValue() != i) {
                System.out.println("Find error1!");
            }
        }

        for (int i = 1; i < NUMS; i += 2) {
            if (t.find(new Integer(i)) != null) {
                System.out.println("Find error2!");
            }
        }

        System.out.println(" success");
    }
}
class BinaryNode {
    // Constructors

    BinaryNode(Comparable theElement) {
        element = theElement;
        left = right = null;
    }
    // Friendly data; accessible by other package routines
    Comparable element;      // The data in the node
    BinaryNode left;         // Left child
    BinaryNode right;        // Right child
}




 