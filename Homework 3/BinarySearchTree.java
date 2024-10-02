import java.util.List;
import java.util.ArrayList;

/**
* Generic class that implements a binary search tree, building upon the
* existing BinaryTree class.
* @param <T> the type of value stored, must be Comparable<T>
* @author Dave Reed, Owen McGrath
* @version 8/30/24
*/
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T>
{
    /**
    * Overrides the super.add method to add according to the BST property.
    * @param value the value to be added to the tree
    */
    public void add(T value)
    {
        //System.out.println("Adding value: " + value);
        this.root = this.add(this.root, value);
       //System.out.println("Current height after adding: " + this.height() + ", size: " + this.size());
        
        if (this.height() > (1 + Math.log(this.size()) / Math.log(2)))
        {
            //System.out.println("Tree is unbalanced, rebalance triggered.");
            this.rebalance();
        }
    }
    
    private TreeNode<T> add(TreeNode<T> current, T value)
    {
        if (current == null)
        {
           // System.out.println("Creating new node for value: " + value);
            return new TreeNode<T>(value, null, null);
        }

        //System.out.println("Current node: " + current.getData());

        if (value.compareTo(current.getData()) < 0)
        {
            //System.out.println("Going left to add value: " + value);
            current.setLeft(this.add(current.getLeft(), value));
        }
        else
        {
           // System.out.println("Going right to add value: " + value);
            current.setRight(this.add(current.getRight(), value));
        }
        
        return current;
    }

    /**
    * Overrides the super.contains method to take advantage of binary search.
    * @param value the value to be searched for
    * @return true if that value is in the tree, otherwise false
    */
    public boolean contains(T value)
    {
        return this.contains(this.root, value);
    }
    
    private boolean contains(TreeNode<T> current, T value)
    {
        if (current == null)
        {
           // System.out.println("Value " + value + " not found.");
            return false;
        }
        else if (value.equals(current.getData()))
        {
           //System.out.println("Value " + value + " found.");
            return true;
        }
        else if (value.compareTo(current.getData()) < 0)
        {
           // System.out.println("Going left to find value: " + value);
            return this.contains(current.getLeft(), value);
        }
        else
        {
           // System.out.println("Going right to find value: " + value);
            return this.contains(current.getRight(), value);
        }
    }

    private void rebalance()
    {
        //System.out.println("Rebalancing the tree...");
        List<T> sortedElements = this.asList(); //creates a new list of sorted elements
       //System.out.println("Sorted elements: " + sortedElements);
        this.root = buildBalancedTree(sortedElements, 0, sortedElements.size() - 1); //call this method, which takes the list, zero (as the start), and size minus one (as the end)
    }

    private TreeNode<T> buildBalancedTree(List<T> sortedElements, int start, int end)
    {
        if (start > end) //base case, if the start value is bigger than the end value, then there is no need to parse
        {
            return null;
        }
        int midpoint = (start + end) / 2; //calcualte the midpoint, or the "root" of the entire tree
       // System.out.println("Midpoint: " + sortedElements.get(midpoint));
                
        TreeNode<T> node = new TreeNode<T>(sortedElements.get(midpoint), null, null); //store the midpoint as a node
        node.setLeft(buildBalancedTree(sortedElements, start, midpoint - 1)); //from the sorted elements, build a balanced tree with everything from the left
        node.setRight(buildBalancedTree(sortedElements, midpoint + 1, end)); //from the sorted elements, build a balanced tree with everything from the right
        return node;
    }

    @Override
    public boolean remove(T value)
    {
        //System.out.println("Removing node: " + value);
        boolean status = super.remove(value);

        //System.out.println("Updated Height: " + this.height() + ", Size: " + this.size());
        if (this.height() > (1 + Math.log(this.size()) / Math.log(2)))
        {
            //System.out.println("Rebalancing tree...");
            this.rebalance();
        }
        return status;
    }

    ////////////////////////////////////////////////////////////////////////////
    /// FOR TESTING PURPOSES
    ////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args)
    {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(7);
        tree.add(2);
        tree.add(12);
        tree.add(1);
        tree.add(5);
        tree.add(6);
        tree.add(99);
        tree.add(88);
        System.out.println(tree);

        System.out.println("size = " + tree.size());
        System.out.println(tree.contains(2) + " " + tree.contains(7)
                                            + " " + tree.contains(8));

        tree.remove(99);
        tree.remove(7);
        System.out.println(tree);
        System.out.println("size = " + tree.size());
        System.out.println(tree.contains(2) + " " + tree.contains(7)
                                            + " " + tree.contains(8));
    }
}
