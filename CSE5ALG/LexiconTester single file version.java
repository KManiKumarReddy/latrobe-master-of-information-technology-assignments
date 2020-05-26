import java.io.File;
import java.io.FileWriter;
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

    public void addWord(String word) {
        
        if(lexicon.size() == 0) {
            lexicon.add(new LexiconEntry(word));
            return;
        }
        int position = searchWordPosition(word);
        int linguisticDifference = lexicon.get(position).word.compareTo(word);
        if(linguisticDifference == 0) {
            lexicon.get(position).increaseFrequency();
        }
        else { 
            LexiconEntry newWordEntry;
            if (linguisticDifference > 0){
                do {
                    position --;
                } while(position > 0 && lexicon.get(position).word.compareTo(word) > 0);
                position ++;
                newWordEntry = new LexiconEntry(word);
            }
            else {
                do {
                    position ++;
                } while(position < lexicon.size() && lexicon.get(position).word.compareTo(word) < 0);
                newWordEntry = new LexiconEntry(word);
            }
            // find and add Neighbours
            for(LexiconEntry w : lexicon) {
                // System.out.println(w.word + word + isNeighbour(w.word, word));
                if(isNeighbour(w.word, word)) {
                    newWordEntry.addNeighbour(w.word);
                    w.addNeighbour(word);
                }
            }
            lexicon.add(position, newWordEntry);
        }


    }

    public boolean isNeighbour(String word1, String word2) {
        if(word1.length() != word2.length())
            return false;
        boolean oneDiffLetterFound = false;
        for(int i = 0; i < word1.length(); i++) { 
            if(word1.charAt(i) != word2.charAt(i)) { 
                if(oneDiffLetterFound) { 
                    return false;
                }
                oneDiffLetterFound = true;
            }
        }
        return true;
    }

    public String toString() {
        String lexiconString = "";
        for(LexiconEntry i : lexicon) {
            lexiconString += i + "\n";
        }
        return lexiconString;
    }

}



class LexiconTester {


    public static void insertWordsFromFile(String fileName, Lexicon lexicon) throws IOException{

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
            if(!word.isEmpty())
                lexicon.addWord(word);
        }
        fileScanner.close();
    }

    public static void main(String args[]) throws IOException{
        Lexicon lexicon = new Lexicon();
        insertWordsFromFile("sample1.txt", lexicon);
        insertWordsFromFile("sample2.txt", lexicon);
        // System.out.println(lexicon);
        File outFile = new File("sample3.txt");
        if(!outFile.exists()) {
            outFile.createNewFile();
        }
        FileWriter outFileWriter = new FileWriter(outFile);
        outFileWriter.write(lexicon.toString());
        outFileWriter.close();
    }
}