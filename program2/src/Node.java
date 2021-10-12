class Node {
    public String key;
    public int frequency, accessCnt;
    public Node left, right;
    public int h, bf;

    public Node(String key) {
        this.key = key;
        frequency = 1;
        accessCnt = 0;
        left = null;
        right = null;
        h = 0;
        bf = 0;
    }

    public static Node clone(Node node) {
        if (node == null)
            return node;
        Node ret = new Node("");
        ret.assignNode(node);
        return ret;
    }

    public void assignNode(Node node) {
        this.key = node.key;
        this.frequency = node.frequency;
        this.accessCnt = node.accessCnt;
        this.left = node.left;
        this.right = node.right;
        this.h = node.h;
        this.bf = node.bf;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getKey() {
        accessCnt++;
        return key;
    }

    public void increaseFrequency() {
        frequency++;
    }

    public void refreshHeight() {
        int lh = 0, rh = 0;
        if (left != null)
            lh = left.h;
        if (right != null)
            rh = right.h;
        h = 1 + Math.max(lh, rh);
        bf = lh - rh;
    }

}