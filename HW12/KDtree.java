
// KD tree with insert and exact match functions
// getNeighbors(Key[] key, int r) is going to be implemented by the students

import java.lang.Integer;
import java.lang.Math;
import java.lang.String;

public class KDtree<Key extends Comparable<?super Key>, E> {
    private BinNode<Key, E> root;
    private int totalNode;
    private BinNode<Key, E> curr;   //works with find()
    private String enumStr;         //for enumeration
    private int dim;   //dimension of the key
    private int level; //which level; important for insertion & search
    public KDtree(int d) {
	root = curr = null;
	totalNode = 0;
	dim = d;
	level = 0;
    }
    public BinNode<Key, E> find(Key[] k) {
	if(root == null) return null;
	else {
	    return find(root, 0, k);
	}
    }
    public BinNode<Key, E> find(BinNode<Key, E> entry, int thislevel, Key[] k) {
	if(entry == null) return null;
	curr = entry;
	level = thislevel; //update level
	if(entry.getKey() == k) {
	    return curr;
	}
	else {
	    if(entry.isLeaf()) return null;
	    Key[] key2 = entry.getKey();
	    if (k[level % dim].compareTo(key2[level % dim]) >= 0) { //make sure the "right" key is used
		return find(entry.getRight(), thislevel + 1, k); //note thislevel + 1
	    }
	    else {
		return find(entry.getLeft(), thislevel + 1, k);
	    }
	}
    }
    public void insert(Key[] k, E v) {
	BinNode<Key, E> node = new BinNode <Key, E>(k, v);
	insert(node);
	//insert(root, node);
    }
    public void insert(BinNode<Key, E> node) {
	find(node.getKey());
	if(curr == null) {
	    root = node;
	}
	else {
	    Key[] key1 = node.getKey();
	    Key[] key2 = curr.getKey();
	    if (key1[level % dim].compareTo(key2[level % dim]) >= 0) {
		if(curr.getRight() != null) node.setRight(curr.getRight());
		curr.setRight(node);
	    }
	    else {
		if(curr.getLeft() != null) node.setLeft(curr.getLeft());
		curr.setLeft(node);
	    }
	}
	totalNode ++;
    }
    public void preorder() {
	enumStr = "";
	System.out.println("Total node = " + totalNode);
	if(root != null) preorder(root);
	System.out.println("Preorder enumeration: " + enumStr);
    }
    private void preorder(BinNode<Key, E> node) {
	if(node != null) System.out.println("root " + node.toString());
	if(node.getLeft() != null) System.out.println("   left " + node.getLeft().toString());
	if(node.getRight() != null) System.out.println("   right " + node.getRight().toString());
	
	if(node != null) {
	    enumStr += node.toString();
	}
	if(node.getLeft() != null) preorder(node.getLeft());
	if(node.getRight() != null) preorder(node.getRight());
    }
    //to be implemented 
    public void getNeighbors(Key[] key, int r) {

	getNeighbors_helper(key, r, 1, root);
    }
    public void getNeighbors_helper(Key[] key, int r, int xy, BinNode<Key, E> keys) {
	int used = 0;

	String tempstr = keys.toString();
	tempstr = tempstr.substring(1, tempstr.length());
	tempstr = tempstr.substring(0, tempstr.length() - 1);
	String[] tempstr_arr = tempstr.split(" ");
	
	int x_keys = Integer.parseInt(tempstr_arr[0]);
	int y_keys = Integer.parseInt(tempstr_arr[1]);
	
	tempstr = key[0].toString();
	int x_key = Integer.parseInt(tempstr);
	tempstr = key[1].toString();
	int y_key = Integer.parseInt(tempstr);

	if(xy == 1) {
	    if ((x_key - x_keys) > (-1 * r)) {
		if(keys.getRight() == null) {
		    if (used == 0) {
			if(Math.sqrt(Math.pow((x_keys - x_key), 2) + Math.pow((y_keys - y_key), 2)) <= r) {
			    System.out.println(keys.toString());
			    used++;
			}
		    }
		}
		else {
		    BinNode<Key, E> temp = keys.getRight();
		    getNeighbors_helper(key, r, xy * (-1), temp);
		}
	    }
	    if ((x_key - x_keys) < r) {
		if(keys.getLeft() == null) {
		    if(used == 0) {			
			if(Math.sqrt(Math.pow((x_keys - x_key), 2) + Math.pow((y_keys - y_key), 2)) <= r) {
			    System.out.println(keys.toString());
			    used++;
			}
		    }
		}
		else {
		    BinNode<Key, E> temp = keys.getLeft();
		    getNeighbors_helper(key, r, xy * (-1), temp);
		}
	    }
	}
	else {
	    if((y_key - y_keys) > (-1 * r)) {
		if(keys.getRight() == null) {
		    if(used == 0) {
			if(Math.sqrt(Math.pow((x_keys - x_key), 2) + Math.pow((y_keys - y_key), 2)) <= r) {
			    System.out.println(keys.toString());
			    used++;
			}
		    }
		}
		else {
		    BinNode<Key, E> temp = keys.getRight();
		    xy *= -1;
		    getNeighbors_helper(key, r, xy * (-1), temp);
		}
	    }
	    if ((y_key - y_keys) < r) {
		if(keys.getLeft() == null) {
		    if(used == 0) {
			if(Math.sqrt(Math.pow((x_keys - x_key), 2) + Math.pow((y_keys - y_key), 2)) <= r) {
			    System.out.println(keys.toString());
			    used++;
			}
		    }
		}
		else {
		    BinNode<Key, E> temp = keys.getLeft();
		    xy *= -1;
		    getNeighbors_helper(key, r, xy * (-1), temp);
		}
	    }
	}
    }

    //design your own examples to test the program!!
    public static void main(String[] args) {
	
	KDtree <Integer, String> kdt = new KDtree<Integer, String>(2);
	Integer[] dataA = {40, 45};
	kdt.insert(dataA, "A");
	Integer[] dataB = {15, 70};
	kdt.insert(dataB, "B");
	Integer[] dataC = {70, 10};
	kdt.insert(dataC, "C");
	Integer[] dataD = {69, 50};
	kdt.insert(dataD, "D");
	Integer[] dataE = {66, 85};
	kdt.insert(dataE, "E");
	Integer[] dataF = {85, 95};
	kdt.insert(dataF, "F");
	
	kdt.preorder();
	
	Integer[] dataG = {85, 93}; //G, close to F
	System.out.println("Query location: " + dataG[0] + " " + dataG[1]);
	BinNode<Integer, String> node = kdt.find(dataG); //exact match
	if(node == null) {
	    System.out.println("point not found");
	}
	else {
	    System.out.println("point found: " + node.toString());
	}
	
	int r = 3;
	System.out.println("Solution: ");
	kdt.getNeighbors(dataG, r); //get close neighbors
    }
}
