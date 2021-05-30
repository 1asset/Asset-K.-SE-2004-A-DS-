import java.util.*;

public class BinarySearchTree {
    public static void main(String[] args) {
        BST T = new BST(); 
    }
}

class BST {
    class Node {
        int value; 
        
        Node lft, rght; // links to left and right sons
        
        Node() {} // empty constructor
    
        Node(int value) { // constructor
            this.value = value;
            this.lft = null;
            this.rght = null;
        }
    }

    Node rt; // a root of our tree

    BST() { 
        rt = null;
    }

    public void insert(int x) { 
        rt = insert(rt, x); // updating root
    }

    private Node insert(Node cur, int x) {
        if (cur == null) { 
	        cur = new Node(x); // if we found an empty node to place our new value to, then do it
	        return cur;
        }
        if (x <= cur.value) {
		    cur.lft = insert(cur.lft, x); 
        } else {
		    cur.rght = insert(cur.rght, x); 
        }
	    return cur;
    }

    public Node search(int value) {
        return search(rt, value);
    }

    public Node search(Node cur, int value) {
        if (cur == null || cur.value == value) {
		    return cur;
        }
	    if (value < cur.value) {
		    return search(cur.lft, value); 
	    }        
	    if (value > cur.value) {
		    return search(cur.rght, value); 
        }
	    return cur; 
    }
    
    public void delete(int value) { 
        rt = delete(rt, value);
    }
    
    public Node delete(Node cur, int value) {
        if (cur == null) {
		    return null;
        }
	    if (cur.value == value) { 
            if (cur.rght == null) {
                return cur.lft; 
            }
            if (cur.lft == null) {
                return cur.rght;
            }
            Node mx = cur.lft;
            while (mx.rght != null) {
                mx = mx.rght; 
            }
            cur.value = mx.value;
            cur.lft = delete(cur.lft, mx.value);
            return cur;
        }
        if (value < cur.value) { 
            cur.lft = delete(cur.lft, value);
        } else {
            cur.rght = delete(cur.rght, value);
        }
        return cur;
    }
    
    public void print() { 
        dfs(rt);
        System.out.print("\n");
    }
    
    public void dfs(Node cur) { // inorder traversal
        if (cur == null) {
		    return;
        }
	    dfs(cur.lft);
        System.out.print(cur.value + " ");
        dfs(cur.rght);
    }
}
