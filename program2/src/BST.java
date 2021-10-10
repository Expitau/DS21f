// (Nearly) Optimal Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)

public class BST { // Binary Search Tree implementation

  protected boolean NOBSTified = false;
  protected boolean OBSTified = false;
  protected Node root;
  protected int totSize, totFreq;

  public BST() {
    root = null;
    totSize = 0;
    totFreq = 0;
  }

  // Tree node operation
  protected Node makeNewNode(String key){
    return new Node(key);
  }
  protected void increaseFrequency(Node node){
    node.increaseFrequency();
  }

  // return nextNode
  // if there are no nextNode, make node or increase frequency
  protected Node getNextNode(Node node, String key){
    int flag = key.compareTo(node.getKey());
    if(flag < 0){
      if(node.left == null){
        node.left = makeNewNode(key);
        return null;
      }
      return node.left;
    }else if(flag > 0){
      if(node.right == null){
        node.right = makeNewNode(key);
        return null;
      }
      return node.right;
    }else{
      increaseFrequency(node);
      return null;
    }
  }

  //inserting key
  public void insert(String key) {
    System.out.println(key);
    // check root is null
    if(root == null){
      root = makeNewNode(key);
      return;
    }
    
    // find insert place
    Node node = root;
    while(node != null){
      node = getNextNode(node, key);
    }
  }

  //find key
  public boolean find(String key) {
    Node node=root;
    while(node != null){
      
      int flag = key.compareTo(node.key);
      //System.out.println("find "+key+" : "+node.key + "    " + flag);
      if(flag < 0) node = node.left;
      else if(flag > 0) node = node.right;
      else return true;
    }
    return false;
  }


  public int size() {
    return totSize;
  }

  public int sumFreq() {
    return totFreq;
  }
  public int sumProbes() { return 1;}
  public int sumWeightedPath() { return 1; }
  public void resetCounters() { }

  public void nobst() { }	// Set NOBSTified to true.
  public void obst() { }	// Set OBSTified to true.
  public void print() {

  }

}