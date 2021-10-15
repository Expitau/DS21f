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
        if (node == null) {
            node = new Node(key);
            refreshHeight();
            return;
        } else {
            int flag = key.compareTo(node.key);
            if (flag == 0) {
                node.frequency += 1;
            } else if (flag < 0) {
                if (left == null)
                    left = new BST();
                left.insert(key);
            } else {
                if (right == null)
                    right = new BST();
                right.insert(key);
            }
        }
        refreshHeight();
    }

    // find key
    public boolean find(String key) {
        updateFlag(false, false, true, false);
        if (node != null) {
            node.probe += 1;
            int flag = key.compareTo(node.key);
            if (flag == 0)
                return true;
            else if (flag < 0) {
                if (left != null)
                    return left.find(key);
            } else {
                if (right != null)
                    return right.find(key);
            }
        }
        return false;
    }

    public void updateFlag(boolean sz, boolean fr, boolean pr, boolean wp) {
        if (sz)
            totSize = null;
        if (fr)
            totFreq = null;
        if (pr)
            totProbes = null;
        if (wp)
            totWeightedPath = null;
    }

    // Tree status
    public int size() {
        if (totSize == null) {
            if (node != null)
                totSize = 1;
            else
                totSize = 0;

            if (left != null)
                totSize += left.size();
            if (right != null)
                totSize += right.size();
        }
        return (int) totSize;
    }

    public int sumFreq() {
        if (totFreq == null) {
            if (node != null)
                totFreq = node.frequency;
            else
                totFreq = 0;

            if (left != null)
                totFreq += left.sumFreq();
            if (right != null)
                totFreq += right.sumFreq();
        }
        return (int) totFreq;
    }

    public int sumProbes() {
        if (totProbes == null) {
            if (node != null)
                totProbes = node.probe;
            else
                totProbes = 0;

            if (left != null)
                totProbes += left.sumProbes();
            if (right != null)
                totProbes += right.sumProbes();
        }
        return (int) totProbes;
    }

    public int sumWeightedPath() {
        if (totWeightedPath == null) {
            totWeightedPath = sumFreq();
            if (left != null)
                totWeightedPath += left.sumWeightedPath();
            if (right != null)
                totWeightedPath += right.sumWeightedPath();
        }
        return (int) totWeightedPath;
    }

    public void resetCounters() {
        updateFlag(false, true, true, true);

        node.frequency = 0;
        node.probe = 0;
        if (left != null)
            left.resetCounters();
        if (right != null)
            right.resetCounters();
    }

    public void refresh() {
        updateFlag(true, true, true, true);
        refreshHeight();
        size();
        sumFreq();
        sumProbes();
        sumWeightedPath();
    }

    public void refreshHeight() {
        node.height = Math.max(height(left), height(right)) + 1;
    }

    public static int height(BST bst) {
        if (bst == null)
            return 0;
        return bst.node.height;
    }

    public void assign(BST bst) {
        this.node = bst.node;
        this.left = bst.left;
        this.right = bst.right;
        this.totSize = bst.totSize;
        this.totFreq = bst.totFreq;
        this.totProbes = bst.totProbes;
        this.totWeightedPath = bst.totWeightedPath;
    }

    private static int fillNodesList(BST bst, Node[] nodes, int index) {
        if (bst == null || bst.node == null)
            return index;
        index = fillNodesList(bst.left, nodes, index);
        nodes[index++] = bst.node;
        index = fillNodesList(bst.right, nodes, index);
        return index;
    }

    private static Node[] getNodesList(BST bst) {
        Node nodes[] = new Node[bst.size() + 1];
        fillNodesList(bst, nodes, 1);
        return nodes;
    }

    private static int[] getWeightSum(Node[] nodes) {
        int sz = nodes.length;
        int[] weightSum = new int[sz];
        for (int i = 1; i < sz; i++) {
            weightSum[i] = weightSum[i - 1] + nodes[i].frequency;
        }
        return weightSum;
    }

    public void nobst() { // Set NOBSTified to true.
        NOBSTified = true;
        Node[] nodes = getNodesList(this);
        int[] weightSum = getWeightSum(nodes);
        assign(nobst(weightSum, nodes, 1, size()));
    }

    private static BST nobst(int[] weightSum, Node[] nodes, int l, int r) {
        if (l > r)
            return null;

        BST ret = new BST();

        int root = l, lastMin = weightSum[r] - weightSum[l];
        for (int i = l + 1; i <= r; i++) { // TODO.change to tenery search!!
            int difference = Math.abs((weightSum[i - 1] - weightSum[l - 1]) - (weightSum[r] - weightSum[i]));
            if (difference < lastMin) { // until right is heavier
                root = i;
                lastMin = difference;
            }
        }

        ret.node = nodes[root];
        ret.left = nobst(weightSum, nodes, l, root - 1);
        ret.right = nobst(weightSum, nodes, root + 1, r);
        ret.refresh();
        return ret;
    }

    public void obst() { // Set OBSTified to true.
        OBSTified = true;
        Node[] nodes = getNodesList(this);
        int[] weightSum = getWeightSum(nodes);
        int[][] rootTable = getRootTable(this, nodes, weightSum);
        assign(obst(nodes, rootTable, 1, size()));
    }

    private static int[][] getRootTable(BST bst, Node[] nodes, int[] weightSum) { // TODO. reduce time!!!!!!!!!
        int n = bst.size();
        int[][] costTable = new int[n + 2][n + 1];
        int[][] rootTable = new int[n + 2][n + 1];

        for (int i = 1; i <= n; i++) { // j == i
            rootTable[i][i] = i;
            costTable[i][i] = weightSum[i] - weightSum[i - 1];
        }
        for (int i = 1; i < n; i++) { // j == i+1
            if (costTable[i][i] >= costTable[i + 1][i + 1]) {
                rootTable[i][i + 1] = i;
                costTable[i][i + 1] = costTable[i + 1][i + 1] + weightSum[i + 1] - weightSum[i - 1];
            } else {
                rootTable[i][i + 1] = i + 1;
                costTable[i][i + 1] = costTable[i][i] + weightSum[i + 1] - weightSum[i - 1];
            }
        }

        for (int k = 2; k < n; k++) {
            for (int i = 1; i + k <= n; i++) {
                int j = i + k;
                int root = rootTable[i][j - 1];
                int minCost = costTable[i][root - 1] + costTable[root + 1][j];

                for (int r = rootTable[i][j - 1]; r <= rootTable[i + 1][j]; r++) {
                    if (costTable[i][r - 1] + costTable[r + 1][j] < minCost) {
                        minCost = costTable[i][r - 1] + costTable[r + 1][j];
                        root = r;
                    }
                }

                rootTable[i][j] = root;
                costTable[i][j] = minCost + weightSum[j] - weightSum[i - 1];
            }
        }
        return rootTable;
    }

    private static BST obst(Node[] nodes, int[][] rootTable, int l, int r) {
        if (l > r)
            return null;
        BST ret = new BST();
        int root = rootTable[l][r];
        ret.node = nodes[root];
        ret.left = obst(nodes, rootTable, l, root - 1);
        ret.right = obst(nodes, rootTable, root + 1, r);
        ret.refresh();
        return ret;
    }

    public void print() {
        if (left != null)
            left.print();
        if (node != null)
            System.out.println(node);
        if (right != null)
            right.print();
    }

}