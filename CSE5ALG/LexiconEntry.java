import java.util.ArrayList;

public class LexiconEntry {
    private String word;
    private int frequency, height;
    private LexiconEntry left, right;
    ArrayList<String>neighbourList;

    public LexiconEntry(String word) {
        this.word = word;
        this.frequency = 1;
        this.neighbourList = new ArrayList<String>();
        this.right = null;
        this.left = null;
    }

    public void addNeighbour(String neighbour) {
        int i = 0;
        while(i < neighbourList.size() && neighbourList.get(i).compareTo(neighbour) < 0)
            i ++;
        neighbourList.add(i, neighbour);
    }

    public void increaseFrequency() {
        this.frequency++;
    }

    public LexiconEntry getLeftChild() {
        return left;
    }

    public LexiconEntry getRightChild() {
        return right;
    }

    public void setLeftChild(LexiconEntry child) {
        this.left = child;
    }

    public void setRightChild(LexiconEntry child) {
        this.right = child;
    }
    
    public String getWord(){
        return word;
    }

	public int getHeight() {
		return height;
	}

	public void setHeight(int i) {
        height = i;
    }
    
    public String toString() {
        return this.word + " " + this.frequency + " " + this.neighbourList.toString();
    }

	public int getFrequency() {
		return this.frequency;
	}

}