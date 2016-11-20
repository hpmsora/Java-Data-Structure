//a simple BST tree (so we call it BSTJr) for C343
import java.lang.Math;
import java.util.Scanner;
public class BSTJr <K extends Comparable<?super K>> {
    BinNode<K> root;
    BinNode<K> curr;
    //for balance checking: if a node is unbalanced, the tree will be unbalanced
    BinNode<K> unbalanced = null;
    public BinNode<K> getCurr() {
	return curr;
    }
    public BSTJr() {
	root = null;
	curr = null;
    }
    public void build(K[] ks) {
	for(int i = 0; i < ks.length; i ++) insert(ks[i]);
    }
    public void insert(K k) {
	BinNode<K> t = new BinNode<K>(k);
	if(root == null) {
	    root = curr = t;
	}
	else {
	    curr = search(root, k);
	    if(k.compareTo(curr.getKey()) < 0) curr.setLeft(t);
	    else curr.setRight(t);
	}
    }
    public BinNode<K> search(BinNode<K> entry, K k) {
	if(entry == null) return null;
	else { 
	    entry.setSize(entry.getSize() + 1); //update the size of the subtree by one
	    if(entry.isLeaf()) return entry;
	    if(k.compareTo(curr.getKey()) < 0) {
		if(entry.getLeft() != null) return search(entry.getLeft(), k);
		else return entry;
	    }
	    else {
		if(entry.getRight() != null) return search(entry.getRight(), k);
		else return entry;
	    }
	}
    }


    //-----------------------------------------------------------
    //HW5
    //Won Yong Ha
    //Due to checking each of the elements going throught the if
    //statement to check the smallest number, it can be consider
    //as checking the depth of the node or tree itself. Dut to the
    //consideration of depth, we can easily find out the the time
    //complexity would be (log n). Therefore, Theta for this
    //algorithm woulb be Theta(log n).

    public K smallestK(BinNode<K> entry, int k) {
	/*if (k == 1) {
	    if (entry.getLeft() == null)
		return entry.getKey();
	    else
		return smallestK(entry.getLeft(), k);
	}*/


	//Due to differetce of building tree, this part does not necessay
	//;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	if (entry.isLeaf()) {
	    return entry.getKey();
	}
	else if (entry.getLeft() == null) {
	    if (k == 1) {
		//System.out.println("NULL " + k);
		return entry.getKey();
	    }
	    else {
		//System.out.println("NULL " + k);
		return smallestK(entry.getRight(), k - 1);
	    }
	}
	//;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	

	else if (k == (entry.getLeft().getSize() + 1)) {
	    //System.out.println("Same " + k);
	    return entry.getKey();
	}
	else if (k < (entry.getLeft().getSize() + 1)) {
	    //System.out.println("Left " + k);
	    return smallestK(entry.getLeft(), k);
	}
	else {
	    //System.out.println("Right " + (k - entry.getLeft().getSize() - 1 ));
	    //System.out.println(entry.getKey());
	    return smallestK(entry.getRight(), k - entry.getLeft().getSize() - 1);
	}
    }
    //----------------------------------------------------------------


    /*public K innerMostFront(BinNode<K> entry) {
	if (entry.getLeft() == null)
	    return entry.getKey();
	else
	    return innerMostFront(entry.getLeft());
     }*/

    public void display() { 
	if(root == null) return;
	System.out.println("Preorder enumeration: key(size-of-the-subtree)");
	preorder(root); 
	System.out.println();
    }
    public void preorder(BinNode<K> entry) {
	System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
	if(entry.getLeft() != null) preorder(entry.getLeft());
	if(entry.getRight() != null) preorder(entry.getRight());
    }
    public boolean IsBalanced(BinNode<K> entry) {
	if(entry == null)
	    return true;
	/*else if(entry.getLeft() == null && entry.getRight() != null) {
	      return IsBalanced(entry.getLeft()) && IsBalanced(entry.getRight())
	      && (Math.abs(0 - (getHeight(entry.getRight()))) >= 1);
	      }
	      else if(entry.getRight() == null && entry.getLeft() != null) {
	          return IsBalanced(entry.getLeft()) && IsBalanced(entry.getRight())
		  &&((Math.abs(getHeight(entry.getLeft()) - 0)) >= 1);
		  }
		  else if(entry.getRight() == null && entry.getLeft() == null) {
		      return IsBalanced(entry.getLeft()) && IsBalanced(entry.getRight());
		      }*/
	else {
	    return IsBalanced(entry.getLeft()) && IsBalanced(entry.getRight())
		&& ((Math.abs(getHeight(entry.getLeft()) - getHeight(entry.getRight()))) <= 1);
	}
    }
    public int getHeight(BinNode<K> entry) {
	if (entry == null)
	    return 0;
	else
	    return Math.max(getHeight(entry.getLeft()), getHeight(entry.getRight())) + 1;
    }
    public static void main(String[] argv) {
	Scanner scan = new Scanner(System.in);
	BSTJr<Integer> tree = new BSTJr<Integer>();
	Integer[] ks = {37, 24, 42, 7, 2, 40, 120};
	//Integer[] ks = {15, 20, 25, 18, 16, 5, 7};
	tree.build(ks);
	tree.display();
	//System.out.println(tree.root.getLeft().getSize());
	System.out.println(tree.IsBalanced(tree.getCurr()));
	//System.out.println(tree.root.getLeft().getLeft().getRight().getKey());
	//System.out.println(tree.root.getKey());
	//System.out.println(tree.root.getRight().getKey());
	//System.out.println(tree.root.getRight().getLeft().getKey());
	//System.out.println(tree.root.getSize());
	int x = 1;
	while (true) {
	System.out.println("Pleas Input the index number from smallest number");
	System.out.println("'0' for quit");
	x = scan.nextInt();
	if (x == 0) break;
	System.out.println(tree.smallestK(tree.root, x));
	}
    }
}
