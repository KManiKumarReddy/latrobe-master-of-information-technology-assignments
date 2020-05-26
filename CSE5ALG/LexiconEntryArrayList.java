import java.util.ArrayList;

class LexiconEntryArrayList {
    String word;
    ArrayList<String> neighbourList;
    int frequency;

    public LexiconEntryArrayList(String word) {
        this.word = word;
        this.frequency = 1;
        this.neighbourList = new ArrayList<String>();
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

    public String toString() {
        return this.word + " " + this.frequency + " " + this.neighbourList.toString();
    }

}