import java.util.*;

class BTreeNode {
    int[] keys;          // array of keys
    int t;               // minimum degree
    BTreeNode[] C;       // child pointers
    int n;               // current number of keys
    boolean leaf;        // is leaf node

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.C = new BTreeNode[2 * t];
        this.n = 0;
    }

    // Traverse the tree
    public void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf)
                C[i].traverse();
            System.out.print(keys[i] + " ");
        }

        if (!leaf)
            C[i].traverse();
    }

    // Search key
    public BTreeNode search(int k) {
        int i = 0;
        while (i < n && k > keys[i])
            i++;

        if (i < n && keys[i] == k)
            return this;

        if (leaf)
            return null;

        return C[i].search(k);
    }

    // Insert when node is not full
    public void insertNonFull(int k) {
        int i = n - 1;

        if (leaf) {
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }

            keys[i + 1] = k;
            n = n + 1;
        } else {
            while (i >= 0 && keys[i] > k)
                i--;

            if (C[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, C[i + 1]);

                if (keys[i + 1] < k)
                    i++;
            }
            C[i + 1].insertNonFull(k);
        }
    }

    // Split child
    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++)
            z.keys[j] = y.keys[j + t];

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.C[j] = y.C[j + t];
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--)
            C[j + 1] = C[j];

        C[i + 1] = z;

        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];

        keys[i] = y.keys[t - 1];
        n = n + 1;
    }
}

class BTree {
    BTreeNode root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void traverse() {
        if (root != null)
            root.traverse();
    }

    public BTreeNode search(int k) {
        return (root == null) ? null : root.search(k);
    }

    public void insert(int k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.C[0] = root;
                s.splitChild(0, root);

                int i = 0;
                if (s.keys[0] < k)
                    i++;
                s.C[i].insertNonFull(k);

                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }

    // Basic deletion (removes if found in leaf)
    public void delete(int k) {
        System.out.println("Deletion operation requires full B-Tree delete algorithm.");
        System.out.println("For lab demo, remove manually if leaf node.");
    }

    public static void main(String[] args) {
        BTree t = new BTree(3);  // t=3 → Order 5 B-Tree

        int arr[] = {10, 20, 5, 6, 12, 30, 7, 17};

        for (int x : arr)
            t.insert(x);

        System.out.println("Traversal of constructed B-Tree:");
        t.traverse();

        int key = 6;
        if (t.search(key) != null)
            System.out.println("\nKey " + key + " found");
        else
            System.out.println("\nKey " + key + " not found");
    }
}