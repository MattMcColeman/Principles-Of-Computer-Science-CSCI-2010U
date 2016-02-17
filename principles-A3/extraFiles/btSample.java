
import java.util.Random;


public class btSample {
	  public static void main(String[] args) {

	    int integerRange = 100;
	    int streamLength = 1;
	    int seed = 0;

	    Stream stream = new Stream(integerRange, seed);  // 100 is the range of integers
	    StreamProcessor processor = new StreamProcessor();


	    for (int i=0; i<streamLength; ++i) {
	      processor.consume(stream.getNext());
	    }

	    // HERE YOU WILL PERFORM SEARCHES AND COLLECT TIMES
	    // AND PREPARE THE OUTPUT

	    processor.show();
	    System.out.println(processor.printGraphViz());
	    //int[] target = processor.search(92);
	    //System.out.println(processor.search(92));
	   // System.out.println(processor.at(6));
	}
}


class BNode {
  public int value;
  public int index;
  public BNode left;
  public BNode right;
  int tree_height;

  public BNode() {
    left = right = null;
  }

  public BNode(int value, int index) {
    this.value = value;
    this.index = index;
    left = right = null;
  }

  public String toString() {
    String s = "value = " + this.value + " index = " + this.index;
    if (isLeaf()) {
      s += " (Leaf)";
    }
    return s;
  }

  public boolean isLeaf() {
    return left == null && right == null;
  }
  
 
}


class BTree {
  public static BNode root;
  int tree_height;

  public BTree() {
    root = null;
  }

/*
  public int height() {
	    // TO DO
	    tree_height = 0;
	    _height(root, 0);
	    System.out.println(tree_height);
	    return tree_height;
	  }
*/
	  public int height(BNode node, int depth) {
	    if(node.left != null){
	      if(tree_height < depth) tree_height = depth;
	    }

	    if(node.left != null){
	      height(node.left, depth+1);
	    }
	    if(node.right != null){
	      height(node.right, depth+1);
	    }
	    return depth;
	  }
	
  int max(int lhs, int rhs){
	  if(lhs > rhs){
		  return lhs;
	  } else {
		  return rhs;
	  }
  }
  
  public void insert(int value, int index) {
    if (root == null) {
      root = new BNode(value, index);
    }
    else {
      insert(root, value, index);
    }
  }

  public void insert(BNode node, int value, int index) {
    if (value <= node.value) {
      if (node.left == null) {
        node.left = new BNode(value, index);
      }
      else {
        insert(node.left, value, index);
      }
    }
    else {
      if (node.right == null) {
        node.right = new BNode(value, index);
      }
      else {
        insert(node.right, value, index);
      }
    }

  }

  public void insertAVL(int value, int index) {
    
    // IMPLEMENT THIS METHOD
    // IT WILL INSERT A NODE AND IF THE INSERTION
    // VIOLATES THE AVL CONDITION, THE METHOD WILL
    // BALANCE THE TREE
  
  }
  
  public BNode insertAVL(BNode node, int value, int index){
	  if(node==null){
		  node = new BNode(value, index);
	  } else if(value < node.value){
		  node.left = insertAVL(node, value, index);
		  if(height(node.left, 0) - height(node.right, 0) == 2){
			  if(value < node.left.value){
				  node = rotateWithLeftChild(node);
			  }else{
				  node = doubleWithLeftChild(node);
			  }
		  }
	  } else if(value > node.value) {
		  node.right = insertAVL(node, value, index);
		  if(height(node.right, 0) - height(node.left, 0) == 2){
			  if(value > node.right.value){
				  node = rotateWithRightChild(node);
			  } else {
				  node = doubleWithRightChild(node);
			  }
		  }
	  } else {
		  ;
	  }
	return node;
 }
   
  
  private BNode rotateWithLeftChild(BNode k2){
	  BNode k1 = k2.left;
	  k2.left = k1.right;
	  k1.right= k2;
	  return k1;
  }
  
  private BNode rotateWithRightChild(BNode k1){
	  BNode k2 = k1.right;
	  k1.right = k2.left;
	  k2.left = k1;
	  return k2;
  }
  
  private BNode doubleWithLeftChild(BNode k3){
	  k3.left = rotateWithRightChild(k3.left);
	  return rotateWithLeftChild(k3);
  }
  
  private BNode doubleWithRightChild(BNode k1){
	  k1.right = rotateWithLeftChild(k1.right);
	  return rotateWithRightChild(k1);
  }

  public void show() {
    show(root);
  }

  public void show(BNode node) {
    if (node != null) {
      show(node.left);
      System.out.println(node);
      show(node.right);
    }
  }
}


class Stream {
  private Random generator;
  private int max;

  public Stream(int max, int seed) 
  {

    // Don't pass anything to the Random number
    // generator if you want to create a different
    // sequence of numbers at each time.
    //
    // I want to generate the same sequence.  It is 
    // easier to test this *simple* program that way.
    // 
    // But please do remember to always test your program
    // by generating difference sequences of 
    // integers.
    generator = new Random(seed);

    this.max = max;
  }

  int getNext() {
    return generator.nextInt(max) + 1;
  }
}

class StreamProcessor{
  private int index;
  private BTree storage;
  
  // YOU MIGHT HAVE TO MODIF THIS CLASS BY ADDING NEW METHODS AND MEMBERS

  private String graphViz;

  public String printGraphViz() {
    graphViz = "digraph G {\n";
    _printGraphViz(BTree.root);
    graphViz += "}";
    return graphViz;
  }

  private void _printGraphViz(BNode n) {
    if (n == null) return;

    if (n.left != null) {
      graphViz += (n.index + "->" + n.left.index + " [label=l];\n"); 
    }
    _printGraphViz(n.left);
    if (n.right != null) {
      graphViz += (n.index + "->" + n.right.index + " [label=r];\n");
    }
    _printGraphViz(n.right);
  }

  public StreamProcessor() {
    this.index = 0;
    this.storage = new BTree();
  }

  public void consume(int v)
  {
    storage.insert(v, index);
    index++;
  }

  public BNode search(int v) {
	    
	    // YOU NEED TO IMPLEMENT THIS METHOD
	  BNode focusNode = BTree.root;
	  
	  while(focusNode.value != v){
		  if(v < focusNode.value){
			  focusNode=focusNode.left;
		  }else{
			  focusNode=focusNode.right;
		  }
		  if(focusNode==null){
			  return null;
		  }
	  }

	  return focusNode;
   }
  

  public int at(int i) {

    // YOU NEED TO IMPLEMENT THIS METHOD
	  BNode focusNode = BTree.root;
	  
	  while(focusNode.index != i){
		  if(index < focusNode.index){
			  focusNode=focusNode.left;
		  }else{
			  focusNode=focusNode.right;
		  }
		  if(focusNode==null){
			  return -1;
		  }
	  }

	  return focusNode.value;
  }

  public void show() {    
    storage.show();
  }
}