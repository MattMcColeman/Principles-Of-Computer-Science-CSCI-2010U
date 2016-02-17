// If you have GraphViz installed, you can
// use the following command to visualize the
// tree.
//
// e

import java.io.*;
import java.util.*;

class BTNodeQueue {
  Queue<BTNode> queue;
  
  public BTNodeQueue() {
    queue = new PriorityQueue<BTNode>();
  }
  public BTNode pop() {
    return queue.remove();
  }

  public void push(BTNode node) {
    queue.add(node);
  }

  public int size() {
    return queue.size();
  }  
}

class BTNode implements Comparable<BTNode> {
  public int value;
  public BTNode left;
  public BTNode right;


  public int compareTo(BTNode o) {
    return 0; // value - o.value; -- since Java priority queue uses it.  Forcing priority queue to behave like a regular queue.
  }

  public BTNode(int value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }

  public String toString() {
    return "v: " + value;
  }
}

class BT {
  private String graphViz;

  public BTNode root;

  int tree_height;

  public BT() {
    this.root = null;
  }

  ////////////////////////////////////////
  //  
  // Please ignore this section.  It is 
  // here to visualize the tree using
  // GraphViz
  //
  public String printGraphViz() {
    graphViz = "digraph G {\n";
    _printGraphViz(root);
    graphViz += "}";
    return graphViz;
  }

  private void _printGraphViz(BTNode n) {
    if (n == null) return;

    if (n.left != null) {
      graphViz += (n.value + "->" + n.left.value + " [label=l];\n"); 
    }
    _printGraphViz(n.left);
    if (n.right != null) {
      graphViz += (n.value + "->" + n.right.value + " [label=r];\n");
    }
    _printGraphViz(n.right);
  }
  //
  // GraphViz section end.
  //
  ////////////////////////////////////////

  public void printInOrder() {
    _printInOrder(root);
  }

  private void _printInOrder(BTNode n) {
    if (n == null) return;

    _printInOrder(n.left);
    System.out.println(n);
    _printInOrder(n.right);
  }

  public void bft() {
    BTNodeQueue q = new BTNodeQueue();
    q.push(root);
    while ( q.size() != 0 ) {
      BTNode n = q.pop();
      if (n.left != null) q.push(n.left);
      if (n.right != null) q.push(n.right);
      System.out.println(n);
    }
  }

  public void insertSorted(int value) {
    if (root == null) {
      root = new BTNode(value);
    }
    else {
      _insertSorted(root, value);
    }
  }

  public void _insertSorted(BTNode node, int value) {
    if (value <= node.value) {
      if (node.left == null) {
        node.left = new BTNode(value);
      }
      else {
        _insertSorted(node.left, value);
      }
    }
    else {
      if (node.right == null) {
        node.right = new BTNode(value);
      }
      else {
        _insertSorted(node.right, value);
      }
    }
  }


  public BTNode find(int value) {
    return _find(root, value);
  }

  public BTNode _find(BTNode node, int value) {
    if (node == null) {
        return null;
    }

    if (node.value == value) {
        return node;
    }
    else if (value < node.value) {
        return _find(node.left, value);
    }
    else {
        return _find(node.right, value);
    }
  }

  public int height() {
    tree_height = 0;
    _height(root, 0);

    return tree_height;
  }

  public int height(BTNode node) {
    tree_height = 0;
    _height(node, 0);
  
    return tree_height;
  }

  public void _height(BTNode node, int depth) {
    if (node.left == null && node.right == null) {
      if (tree_height < depth) tree_height = depth;
    }

    if (node.left != null) {
      _height(node.left, depth+1);
    }
    if (node.right != null) {
      _height(node.right, depth+1);
    }
  }

  public BTNode is_avl_violated() {
    return null;
  }

}


public class BinaryTreeAVL {
  public static void main(String[] args) {
    //test1();
     test2();
    // test3();
    // test4();
    //test5();
  }

  public static void test1() {    
    System.out.println("test1");

    BTNode n1 = new BTNode(1);
    BTNode n2 = new BTNode(2);
    BTNode n3 = new BTNode(3);
    BTNode n4 = new BTNode(4);
    BTNode n5 = new BTNode(5);


    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    BT bt = new BT();
    bt.root = n1;
     
    // The following is used to 
    // visualize the tree. Feel free
    // to comment out the line if you
    // don't have GraphViz installed.
    System.out.println(bt.printGraphViz());
    bt.printInOrder();
  }

  public static void test2() {
    //System.out.println("test2");

    BT bt2 = new BT();
    int[] vals = {3, 2, 1, 5, 4, 6, 8, 7};
    for (int i=0; i<vals.length; ++i) {
      bt2.insertSorted(vals[i]);
    }

    // The following is used to 
    // visualize the tree. Feel free
    // to comment out the line if you
    // don't have GraphViz installed.
    System.out.println(bt2.printGraphViz());
    //bt2.printInOrder();    
  }

  public static void test3() {
    System.out.println("test3");

    BT bt2 = new BT();
    int[] vals = {3, 2, 1, 5, 4, 6, 8, 7};
    for (int i=0; i<vals.length; ++i) {
      bt2.insertSorted(vals[i]);
    }

    System.out.println( bt2.find(100) );
    System.out.println( bt2.find(6) );
  }

  public static void test4() {
    System.out.println("test4");

    BT bt2 = new BT();
    int[] vals = {3, 2, 1, 5, 4, 6, 8, 7};
    for (int i=0; i<vals.length; ++i) {
      bt2.insertSorted(vals[i]);
    }

    System.out.println( bt2.height() );
    System.out.println( bt2.height( bt2.find(6) ) );

  }

  public static void test5() {
    System.out.println("test5");

    // This violates after 1 is added
    BT bt2 = new BT();
    int[] vals1 = {3, 2, 1, 5, 4};
    for (int i=0; i<vals1.length; ++i) {
      bt2.insertSorted(vals1[i]);
      BTNode bad_node = bt2.is_avl_violated();
      if (bad_node != null) {
        System.out.println("avl violated when inserting" + vals1[i] + "at node " + bad_node);
        break;
      }
    }

    // This violates after 5 is added
    bt2 = new BT();
    int[] vals2 = {2, 1, 3, 4, 5, 8, 9};
    for (int i=0; i<vals2.length; ++i) {
      bt2.insertSorted(vals2[i]);
      BTNode bad_node = bt2.is_avl_violated();
      if (bad_node != null) {
        System.out.println("avl violated when inserting" + vals2[i] + "at node " + bad_node);
        break;
      }
    }

    // This violates after 4 is added
    bt2 = new BT();
    int[] vals3 = {7, 3, 10, 6, 4, 1, 3};
    for (int i=0; i<vals3.length; ++i) {
      bt2.insertSorted(vals3[i]);
      BTNode bad_node = bt2.is_avl_violated();
      if (bad_node != null) {
        System.out.println("avl violated when inserting" + vals3[i] + "at node " + bad_node);
        break;
      }
    }

  }

}