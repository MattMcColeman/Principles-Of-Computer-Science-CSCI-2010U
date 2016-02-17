package btSample;

class StreamProcessor{
  private int index;
  private BTree storage;
  
  // YOU MIGHT HAVE TO MODIF THIS CLASS BY ADDING NEW METHODS AND MEMBERS

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