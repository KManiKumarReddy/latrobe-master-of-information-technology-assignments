import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

// AVL tree implementation to store words in lexicon, slightly faster than binary search on arrayList
class Lexicon {

    LexiconEntry root;

    public Lexicon() {
        root = null;
    }

    public LexiconEntry search(String word) {
        LexiconEntry p = root;
        while (p != null) {
            if (p.getWord().equals(word)) {
                break;
            } else if (word.compareTo(p.getWord()) < 0) {
                p = p.getLeftChild();
            } else {
                p = p.getRightChild();
            }
        }
        return p;
    }

    public LexiconEntry searchDeepestNodeInSubtree(LexiconEntry localRoot, String word) {
        int lexicalDifference = 0;
        while (true) {
            lexicalDifference = word.compareTo(localRoot.getWord());
            if (lexicalDifference < 0 && localRoot.getLeftChild() != null) {
                localRoot = localRoot.getLeftChild();
            } else if (lexicalDifference > 0 && localRoot.getRightChild() != null) {
                localRoot = localRoot.getRightChild();
            } else {
                return localRoot;
            }
        }
    }

    // INSERT A NODE INTO AVL TRRE
    public void addWord(String word) {
        // Case: tree is empty
        if (root == null) {
            root = new LexiconEntry(word);
            return;
        }

        // Case: tree is not empty
        LexiconEntry p = root;

        // move p down as far as we can
        p = searchDeepestNodeInSubtree(p, word);
        // check if the actual word is found
        int lexicalDifference = word.compareTo(p.getWord());
        if (lexicalDifference == 0) {
            p.increaseFrequency();
            return;
        }

        // insert new node to as a child of node p
        LexiconEntry newEntry = new LexiconEntry(word);
        // find and add Neighbours
        // brute force - go through every word see if its a neighbour - n * len(word) ,
        // apparently n >> len(word)
        /*
         * for(LexiconEntry w : lexicon) { if(isNeighbour(w.getWord(), word)) {
         * newWordEntry.neighbourList.add(w.getWord()); w.addNeighbour(word); } }
         */
        // create all possible neighbours and search them - 26 * len(word) * log(n) ,
        // apparently n >> len(word)
        LexiconEntry neighbour;
        for (int i = 0; i < word.length(); i++) {
            for (char variedLetter = 'a'; variedLetter <= 'z'; variedLetter++) {
                neighbour = search(word.substring(0, i) + variedLetter + word.substring(i + 1));
                if (neighbour != null) {
                    // already sorted
                    newEntry.neighbourList.add(neighbour.getWord());
                    // sorted addition handled in LexiconEntry
                    neighbour.addNeighbour(word);
                }
            }
        }
        if (lexicalDifference < 0) {
            p.setLeftChild(newEntry);
        } else {
            p.setRightChild(newEntry);
        }

        rebalanceInsertionPath(newEntry);
    }

    private void rebalanceInsertionPath(LexiconEntry node) {
        // Get the path nodes from the root to the node
        ArrayList<LexiconEntry> nodes = getAllNodesOnPath(node.getWord());

        // Reverse the list to get the path from the node to the root
        Collections.reverse(nodes);

        // Set the heights of the nodes along the path, which may have
        // changed by the insertion of the new node
        for (int i = 0; i < nodes.size(); i++) {
            setHeight(nodes.get(i));
        }

        // Rebalance all the nodes (subtrees) on path from inserted node
        // to the root
        //
        // To rebalance a node, we need the pointer to its parent so that
        // we can attach the rebalanced subtree to the whole tree
        //
        // The first node that may need rebalancing is at index 2 (grandparent
        // of the inserted node), and the parent of this node is at index 3
        //
        for (int i = 3; i < nodes.size(); i++) {
            LexiconEntry pp = nodes.get(i); // pointer to parent
            LexiconEntry pc = nodes.get(i - 1); // pointer to child, the node that may
                                                // actually need to be rebalanced
            if (pp.getLeftChild() == pc) {
                LexiconEntry localRoot = rebalance(pc);
                pp.setLeftChild(localRoot);
                if (localRoot != pc)// some rotation has actually been made
                {
                    break;
                }
            } else {
                LexiconEntry localRoot = rebalance(pc);
                pp.setRightChild(localRoot);
                if (localRoot != pc) // some rotation has actually been made
                {
                    break;
                }
            }
        }

        // .. This is done outside the loop because root does not
        // .. have a parent
        root = rebalance(root);
    }

    private ArrayList<LexiconEntry> getAllNodesOnPath(String word)
    // The tree is not empty and the key is in the tree
    {
        ArrayList<LexiconEntry> nodes = new ArrayList<LexiconEntry>();

        // Add root as the first node
        // Otherwise, it will not be included
        nodes.add(root);

        LexiconEntry p = root;
        while (p != null && p.getWord() != word) {
            if (word.compareTo(p.getWord()) < 0) {
                p = p.getLeftChild();
            } else {
                p = p.getRightChild();
            }
            nodes.add(p);
        }

        return nodes;
    }

    private void setHeight(LexiconEntry node) {
        LexiconEntry leftChild = node.getLeftChild();
        LexiconEntry rightChild = node.getRightChild();
        int leftHeight = leftChild == null ? -1 : leftChild.getHeight();
        int rightHeight = rightChild == null ? -1 : rightChild.getHeight();

        if (leftHeight >= rightHeight) {
            node.setHeight(leftHeight + 1);
        } else {
            node.setHeight(rightHeight + 1);
        }
    }

    private LexiconEntry rebalance(LexiconEntry node) {
        int diff = getHeightDifference(node);

        if (diff == 2) {
            if (getHeightDifference(node.getLeftChild()) == -1) {
                node = leftRightRotation(node);
            } else {
                node = rightRotation(node);
            }
        } else if (diff == -2) {
            if (getHeightDifference(node.getRightChild()) == 1) {
                node = rightLeftRotation(node);
            } else {
                node = leftRotation(node);
            }
        }

        return node;
    }

    private int getHeightDifference(LexiconEntry node) {
        LexiconEntry leftChild = node.getLeftChild();
        LexiconEntry rightChild = node.getRightChild();
        int leftHeight = leftChild == null ? -1 : leftChild.getHeight();
        int rightHeight = rightChild == null ? -1 : rightChild.getHeight();

        return leftHeight - rightHeight;
    }

    private LexiconEntry rightRotation(LexiconEntry g) {

        LexiconEntry p = g.getLeftChild();
        LexiconEntry x = p.getRightChild(); // x is right child of p
        p.setRightChild(g);
        g.setLeftChild(x);
        setHeight(g);
        setHeight(p);
        return p;
    }

    private LexiconEntry leftRotation(LexiconEntry g) {

        LexiconEntry p = g.getRightChild();
        LexiconEntry x = p.getLeftChild(); // x is left child of p
        p.setLeftChild(g);
        g.setRightChild(x);
        setHeight(g);
        setHeight(p);
        return p;
    }

    private LexiconEntry rightLeftRotation(LexiconEntry g) {

        LexiconEntry p = g.getRightChild();
        g.setRightChild(rightRotation(p));
        return leftRotation(g);
    }

    private LexiconEntry leftRightRotation(LexiconEntry g) {

        LexiconEntry p = g.getLeftChild();
        g.setLeftChild(leftRotation(p));
        return rightRotation(g);
    }

    private String accumulateLexiconString(LexiconEntry localRoot) {
        if (localRoot != null)
            return accumulateLexiconString(localRoot.getLeftChild()) + localRoot.toString() + "\n"
                    + accumulateLexiconString(localRoot.getRightChild());
        return "";

    }

    public String toString() {
        return accumulateLexiconString(this.root);
    }

    public void printMatchingWordsToFile(String pattern, FileWriter writer) throws IOException {
        int quesionMarkIndex = pattern.indexOf('?');
        if (quesionMarkIndex != -1) {
            for (char i = 'a'; i <= 'z'; i++)
                printMatchingWordsToFile(
                        pattern.substring(0, quesionMarkIndex) + i + pattern.substring(quesionMarkIndex + 1), writer);
        } else if (pattern.indexOf('*') != -1) {
            Pattern compiledPattern = Pattern.compile(pattern.toLowerCase().replace("*", "[a-z]*"));
            searchForPattern(root, compiledPattern, writer);
        } else {
            LexiconEntry entry = search(pattern);
            if (entry != null)
                writer.write(pattern + "\t" + entry.getFrequency() + "\n");
        }
    }

    private void searchForPattern(LexiconEntry localRoot, Pattern pattern, FileWriter writer) throws IOException {
        if (localRoot.getLeftChild() != null) {
            searchForPattern(localRoot.getLeftChild(), pattern, writer);
        }
        if (pattern.matcher(localRoot.getWord()).matches())
            writer.write(localRoot.getWord() + "\t" + localRoot.getFrequency() + "\n");
        if (localRoot.getRightChild() != null) {
            searchForPattern(localRoot.getRightChild(), pattern, writer);
        }
    }
}
