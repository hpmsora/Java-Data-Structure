//a simple BST tree (so we call it BSTJr) for C343
import java.lang.Math;
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
	BSTJr<Integer> tree = new BSTJr<Integer>();
	Integer[] ks = {37, 24, 42, 7, 2, 40, 120};
	tree.build(ks);
	tree.display();
	System.out.println(tree.IsBalanced(tree.getCurr()));
    }
}
