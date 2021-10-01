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

  public int size() {
    return totSize;
  }

  public void insert(String key) {
    totFreq++;
    if(root == null){
      root = new Node(key);
      totSize++;
      return;
    }
    Node nowNode = root;
    while(true){
      switch(key.compareTo(nowNode.getKey())){
        case -1:
          if(nowNode.left == null){
            nowNode.left = new Node(key);
            totSize++;
            return;
          }
          nowNode = nowNode.left;
          break;
        case 0:
          nowNode.increaseFrequency();
          return;
        case 1:
          if(nowNode.right == null){
            nowNode.right = new Node(key);
            totSize++;
            return;
          }
          nowNode = nowNode.right;
      }
    }
  }
  public boolean find(String key) {
    Node nowNode=root;
    while(nowNode != null){
      switch(key.compareTo(nowNode.getKey())){
        case -1:
          nowNode = nowNode.left;
          break;
        case 1:
          nowNode = nowNode.right;
          break;
        case 0:
          return true;
      }
    }
    return false;
  }

  public int sumFreq() {
    return totFreq;
  }
  public int sumProbes() { }
  public int sumWeightedPath() { }
  public void resetCounters() { }

  public void nobst() { }	// Set NOBSTified to true.
  public void obst() { }	// Set OBSTified to true.
  public void print() {

  }

}

class Node {
  private String key;
  private int frequency, accessCnt;
  public Node left, right;
  public Node(String key){
    this.key = key;
    frequency = 1;
    accessCnt = 0;
    left = null;
    right = null;
  }
  public String getKey(){
    accessCnt++;
    return key;
  }
  public int getFrequency(){
    return frequency;
  }
  public void increaseFrequency(){
    frequency++;
  }


}