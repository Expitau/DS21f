// (Nearly) Optimal Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)

public class BST { // Binary Search Tree implementation

    protected boolean NOBSTified = false;
    protected boolean OBSTified = false;

    protected Node node;
    protected BST left, right;
    private Integer totSize, totFreq, totProbes, totWeightedPath;

    public BST() {
        totSize = 1;
        totFreq = 1;
    }

    // inserting key
    public void insert(String key) {
        updateFlag(true, true, false, true);
        if(node == null){
            node = new Node(key);
            return;
        }else{
            int flag = key.compareTo(node.key);
            if(flag == 0){
                node.frequency += 1;
            }else if(flag < 0){
                if(left == null) left = new BST();
                left.insert(key);
            }else{
                if(right == null) right = new BST();
                right.insert(key);
            }
        }
        refreshHeight();
    }

    // find key
    public boolean find(String key) {
        updateFlag(false, false, true, false);
        if(node != null){
            node.probe += 1;
            int flag = key.compareTo(node.key);
            if(flag == 0) return true;
            else if(flag < 0){
                if(left != null) return left.find(key);
            }else{
                if(right != null) return right.find(key);
            }
        }
        return false;
    }


    public void updateFlag(boolean sz, boolean fr, boolean pr, boolean wp){
        if(sz) totSize = null;
        if(fr) totFreq = null;
        if(pr) totProbes = null;
        if(wp) totWeightedPath = null;
    }

    //Tree status
    public int size() {
        if(totSize == null){
            if(node != null) totSize = 1;
            else totSize = 0;

            if(left != null) totSize += left.size();
            if(right != null) totSize += right.size();
        }
        return (int)totSize;
    }

    public int sumFreq() {
        if(totFreq == null){
            if(node != null) totFreq = node.frequency;
            else totFreq = 0;

            if(left != null) totFreq += left.sumFreq();
            if(right != null) totFreq += right.sumFreq();
        }
        return (int)totFreq;
    }

    public int sumProbes() {
        if(totProbes == null){
            if(node != null) totProbes = node.probe;
            else totProbes = 0;

            if(left != null) totProbes += left.sumProbes();
            if(right != null) totProbes += right.sumProbes();
        }
        return (int)totProbes;
    }

    public int sumWeightedPath() {
        if(totWeightedPath == null){
            totWeightedPath = sumFreq();
            if(left != null) totWeightedPath += left.sumWeightedPath();
            if(right != null) totWeightedPath += right.sumWeightedPath();
        }
        return (int)totWeightedPath;
    }

    public void resetCounters() {
        updateFlag(false, true, true, true);

        node.frequency = 0;
        node.probe = 0;
        if(left != null) left.resetCounters();
        if(right != null) right.resetCounters(); 
    }

    public void refresh(){
        updateFlag(true, true, true, true);
        refreshHeight();
    }

    public void refreshHeight(){
        node.height = Math.max(height(left), height(right)) + 1;
    }

    public static int height(BST bst){
        if(bst == null) return 0;
        return bst.node.height;
    }

    public void nobst() {
    } // Set NOBSTified to true.

    public void obst() {
    } // Set OBSTified to true.

    public void print() {
        if(left != null) left.print();
        if(node != null){
            if(this instanceof AVL) System.out.printf("%d %d   ", height(left), height(right));
            System.out.println(node);
        }
        if(right != null) right.print();
    }

}