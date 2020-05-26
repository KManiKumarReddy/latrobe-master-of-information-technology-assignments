import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class LexiconEntry {
    String word;
    ArrayList<String> neighbourList;
    int frequency;

    public LexiconEntry(String word) {
        this.word = word;
        this.frequency = 1;
        this.neighbourList = new ArrayList<String>();
    }

    public void addNeighbour(String neighbour) {
        neighbourList.add(neighbour);
    }

    public void increaseFrequency() {
        this.frequency++;
    }

    public String toString() {
        return this.word + " " + this.frequency + " " + this.neighbourList.toString();
    }

}

class Lexicon {
    ArrayList<LexiconEntry> lexicon;
    public Lexicon() {
        lexicon = new ArrayList<LexiconEntry>();
    }

    public String toString() {
        String lexiconString = "";
        for(LexiconEntry i : lexicon) {
            lexiconString += i;
        }
        return lexiconString;
    }

    public int searchWordPosition(String searchValue) {

        int low = 0;
        int high = lexicon.size() - 1;
        int mid = (low + high) / 2;

        while (low <= high && !lexicon.get(mid).word.equals(searchValue)) {

            if (lexicon.get(mid).word.compareTo(searchValue) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            mid = (low + high) / 2;

        }

        return mid;

    }

    public void addWordToLexicon(String word) {
        
        if(lexicon.size() == 0) {
            lexicon.add(new LexiconEntry(word));
            return;
        }
        int position = searchWordPosition(word);
        int linguisticDifference = lexicon.get(position).word.compareTo(word);
        if(linguisticDifference == 0) {
            lexicon.get(position).increaseFrequency();
        }
        else if (linguisticDifference > 0){
            do {
                position --;
            } while(position > 0 && lexicon.get(position).word.compareTo(word) > 0);
            lexicon.add(position + 1, new LexiconEntry(word));
        }
        else {

            do {
                position ++;
            } while(position < lexicon.size() && lexicon.get(position).word.compareTo(word) < 0);
            lexicon.add(position, new LexiconEntry(word));
        }

    }

}



class LexiconTester {

    public static int searchWordPosition(ArrayList<LexiconEntry> lexicon, String searchValue) {

        int low = 0;
        int high = lexicon.size() - 1;
        int mid = (low + high) / 2;

        while (low <= high && !lexicon.get(mid).word.equals(searchValue)) {

            if (lexicon.get(mid).word.compareTo(searchValue) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            mid = (low + high) / 2;

        }

        return mid;

    }

    public static void addWordToLexicon(ArrayList<LexiconEntry> lexicon, String word) {
        
        if(lexicon.size() == 0) {
            lexicon.add(new LexiconEntry(word));
            return;
        }
        int position = searchWordPosition(lexicon, word);
        int linguisticDifference = lexicon.get(position).word.compareTo(word);
        if(linguisticDifference == 0) {
            lexicon.get(position).increaseFrequency();
        }
        else if (linguisticDifference > 0){
            do {
                position --;
            } while(position > 0 && lexicon.get(position).word.compareTo(word) > 0);
            lexicon.add(position + 1, new LexiconEntry(word));
        }
        else {

            do {
                position ++;
            } while(position < lexicon.size() && lexicon.get(position).word.compareTo(word) < 0);
            lexicon.add(position, new LexiconEntry(word));
        }

    }

    public static void insertWordsFromFile(String fileName, ArrayList<LexiconEntry> lexicon) throws IOException{

        File wordsFile =  new File(fileName);
        if(!wordsFile.exists() || !wordsFile.canRead()){
            System.out.println("\n\tFile - "+fileName+" does not exist");
            return;
        }
        Scanner fileScanner = new Scanner(wordsFile);
        
        while(fileScanner.hasNext()) {
            String word = "";
            for(char c : fileScanner.next().toLowerCase().toCharArray()) {
                if(c >= 'a' && c <= 'z')
                    word += c;
            }
            addWordToLexicon(lexicon, word);
        }
        fileScanner.close();
    }

    public static void main(String args[]) throws IOException{
        ArrayList<LexiconEntry> lexicon = new ArrayList<LexiconEntry>();
        insertWordsFromFile("sample1.txt", lexicon);
        for(LexiconEntry i : lexicon) {
            System.out.println(i);
        }
    }
}