/**
* Generic TreeNode class for storing nodes in a binary tree.
*   @author Dave Reed, Owen McGrath
*   @version 8/30/24b
*/
public class TreeNode<T>
{
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;
	private int leftSize;
	private int rightSize;
	private int leftHeight;
	private int rightHeight;

    /**
    * Constructs a node with the specified contents.
    * @param d the data value to be stored
    * @param l the left child/subtree
    * @param r the right child/subtree
    */
    public TreeNode(T d, TreeNode<T> l, TreeNode<T> r)
    {
        this.data = d;
        this.setLeft(l);
        this.setRight(r);

        if (l != null)
        {
            this.leftSize = l.getSize();
            this.leftHeight = l.getHeight();
        } 
        else
        {
            this.leftSize = 0;
            this.leftHeight = 0;
        }
        
        if (r != null)
        {
            this.rightSize = r.getSize();
            this.rightHeight = r.getHeight();
        } 
        else
        {
            this.rightSize = 0;
            this.rightHeight = 0;
        }
    }

    /**
    * Accessor method for the data value.
    * @return the data value stored in the node
    */
    public T getData()
    {
        return this.data;
    }

    /**
    * Accessor method for the left child/subtree.
    * @return the left child/subtree
    */
    public TreeNode<T> getLeft()
    {
        return this.left;
    }

    /**
    * Accessor method for the right child/subtree.
    * @return the right child/subtree
    */
    public TreeNode<T> getRight()
    {
        return this.right;
    }

    /**
    * Setter method for changing the data value.
    * @param newData the new data value
    */
    public void setData(T newData)
    {
        this.data = newData;
    }

    /**
    * Setter method for changing the left child/subtree.
    * @param newLeft the new left child/subtree
    */
    public void setLeft(TreeNode<T> newLeft)
    {
        this.left = newLeft;

        if (newLeft != null)
        {
            leftHeight = newLeft.getHeight();
            leftSize = newLeft.getSize();
            //System.out.println("Setting left child: Height = " + leftHeight + ", Size = " + leftSize);
        }
        else
        {
            leftHeight = 0;
            leftSize = 0;
            //System.out.println("Clearing left child: Height = " + leftHeight + ", Size = " + leftSize);
        }
    }

    /**
    * Setter method for changing the right child/subtree.
    * @param newRight the new right child/subtree
    */
    public void setRight(TreeNode<T> newRight)
    {
        this.right = newRight;
        
        if (newRight != null)
        {
            rightHeight = newRight.getHeight();
            rightSize = newRight.getSize();
            //System.out.println("Setting right child: Height = " + rightHeight + ", Size = " + rightSize);
        }
        else
        {
            rightHeight = 0;
            rightSize = 0;
            //System.out.println("Clearing right child: Height = " + rightHeight + ", Size = " + rightSize);
        }
    }

    /**
    * a method that calculates the size in O(1) time
    * @return size -> the height of the left and right subnodes
    */
    public int getSize()
    {
        //System.out.println("Getting size: " + (leftSize + rightSize + 1));
        return leftSize + rightSize + 1;
    }

    /**
    * a method that calculates the height of subnodes in O(1) and stores it in the node
    * @return height -> the height of the left and right subnodes
    */
    public int getHeight()
    {
        //System.out.println("Getting Height: " + (leftHeight + rightHeight + 1));
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
