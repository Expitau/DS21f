// AVL Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)

public class AVL extends BST {

    public AVL() {
        super();
    }

    @Override
    public void insert(String key) {
        updateFlag(true, true, false, true);

        if (node == null) {
            node = new Node(key);
            refreshHeight();
            return;
        }

        int flag = key.compareTo(node.key);
        if (flag == 0) {
            node.frequency += 1;
        } else if (flag > 0) {
            if (right == null)
                right = new AVL();
            right.insert(key);
            if (height(right) - height(left) == 2) {
                if (key.compareTo(right.node.key) > 0)
                    rotateLeft();
                else
                    rotateRightLeft();
            }
        } else {
            if (left == null)
                left = new AVL();
            left.insert(key);
            if (height(left) - height(right) == 2) {
                if (key.compareTo(left.node.key) < 0)
                    rotateRight();
                else
                    rotateLeftRight();
            }
        }
        refreshHeight();
    }

    private void rotateRight() {
        Node nodeS = node;
        Node nodeX = left.node;
        BST S = new AVL();

        S.node = nodeS;
        S.left = left.right;
        S.right = right;
        S.refresh();

        node = nodeX;
        left = left.left;
        right = S;
        refresh();
    }

    private void rotateLeft() {
        Node nodeS = node;
        Node nodeX = right.node;
        AVL S = new AVL();

        S.node = nodeS;
        S.left = left;
        S.right = right.left;
        S.refresh();

        node = nodeX;
        left = S;
        right = right.right;
        refresh();
    }

    private void rotateLeftRight() {
        ((AVL) left).rotateLeft();
        rotateRight();
    }

    private void rotateRightLeft() {
        ((AVL) right).rotateRight();
        rotateLeft();
    }

}
