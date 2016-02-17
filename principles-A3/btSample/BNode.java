package btSample;

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