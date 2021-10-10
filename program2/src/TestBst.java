class TestBst{
  public static void main(String[] args){
    BST avl = new AVL();
    avl.insert("3");
    avl.insert("1");
    avl.insert("4");
    avl.insert("2");
    avl.insert("6");
    avl.insert("5");
    avl.insert("9");
    avl.insert("7");
    System.out.println(avl.find("1"));
    System.out.println(avl.find("2"));
    System.out.println(avl.find("3"));
    System.out.println(avl.find("4"));
    System.out.println(avl.find("5"));
    System.out.println(avl.find("6"));
    System.out.println(avl.find("7"));
    System.out.println(avl.find("8"));
    System.out.println(avl.find("9"));
    System.out.println(avl.find("10"));
  }
}