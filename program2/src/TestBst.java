import java.io.*;
import java.lang.management.*;

class TestBst {
    public static ThreadMXBean TMB;
    public static void main(String[] args) {
        long cputime;
        TMB = ManagementFactory.getThreadMXBean();
        if (!TMB.isThreadCpuTimeSupported()) {
            System.out.println("ThreadCpuTime is not supported.");
            System.exit(0);
        }

        String fileName = "sawyer.txt";
        String keyFileName = "sawyer.txt";
        BST bst = new BST();
        buildBST(bst, "./program2/public/"+fileName);
        AVL avl = new AVL();
        buildBST(avl, "./program2/public/"+fileName);
        System.out.println(
                "Number of words in the BST: " + bst.size() + " (number of insertions: " + bst.sumFreq() + ")");

        System.out.println("Sum of Weighted Path Lengths (BST): " + bst.sumWeightedPath());
        bst.resetCounters();
        probeBST(bst, "./program2/public/"+fileName);

        System.out.println("Sum of Weighted Path Lengths (AVL): " + avl.sumWeightedPath());
        avl.resetCounters();
        probeBST(avl, "./program2/public/"+keyFileName);
    }

    public static void buildBST(BST bst, String input) {
        TextInputStream sfs = new TextInputStream(input);

        long cputime = TMB.getCurrentThreadCpuTime();
        while (sfs.ready())
            bst.insert(sfs.readWord());
        cputime = TMB.getCurrentThreadCpuTime() - cputime;

        //bst.print();
        String bstType = (bst instanceof AVL) ? "AVL" : "BST";
        System.out.println("CPU time to build a(n) " + bstType + ": " + (cputime / 1000000) + " millisec");
    }

    public static void probeBST(BST bst, String keys) {
        TextInputStream qfs = new TextInputStream(keys);
        int notfound = 0;

        long cputime = TMB.getCurrentThreadCpuTime();
        while (qfs.ready()) {
            String queryWord = qfs.readWord();
            if (bst.find(queryWord) == false) {
                System.out.println("The word `" + queryWord + "' not found.");
                notfound++;
            }
        }
        cputime = TMB.getCurrentThreadCpuTime() - cputime;

        //bst.print();
        String bstType = "BST";
        if (bst instanceof AVL)
            bstType = "AVL";
        else if (bst.NOBSTified == true)
            bstType = "NOBST";
        else if (bst.OBSTified == true)
            bstType = "OBST";

        System.out.println("Total number of node accesses (" + bstType + "): " + bst.sumProbes() + " (failed searches: "
                + notfound + ")");
        System.out.println("CPU time for searching keys (" + bstType + "): " + (cputime / 1000000) + " millisec");
    }
}