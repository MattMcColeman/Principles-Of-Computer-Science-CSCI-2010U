package btSample;

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