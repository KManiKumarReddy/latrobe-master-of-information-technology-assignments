import java.util.ArrayList;

public class LexiconArrayList {
    ArrayList<LexiconEntryArrayList> lexicon;
    public LexiconArrayList() {
        lexicon = new ArrayList<LexiconEntryArrayList>();
    }

    //modified binary Search to find Word probable position
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
            lexicon.add(new LexiconEntryArrayList(word));
            return;
        }
        int position = searchWordPosition(word);
        int linguisticDifference = lexicon.get(position).word.compareTo(word);
        if(linguisticDifference == 0) {
            lexicon.get(position).increaseFrequency();
        }
        else { 
            LexiconEntryArrayList newWordEntry;
            if (linguisticDifference > 0){
                do {
                    position --;
                } while(position > 0 && lexicon.get(position).word.compareTo(word) > 0);
                position ++;
                newWordEntry = new LexiconEntryArrayList(word);
            }
            else {
                do {
                    position ++;
                } while(position < lexicon.size() && lexicon.get(position).word.compareTo(word) < 0);
                newWordEntry = new LexiconEntryArrayList(word);
            }
            // find and add Neighbours
            // brute force - go through every word see if its a neighbour - n * len(word) , apparently n >> len(word)
            /* for(LexiconEntryArrayList w : lexicon) {
                if(isNeighbour(w.word, word)) {
                    newWordEntry.neighbourList.add(w.word);
                    w.addNeighbour(word);
                }
            }*/
            // create all possible neighbours and search them - 26 * len(word) * log(n) , apparently n >> len(word)
            for(int i = 0; i < word.length(); i ++) {
                for(char variedLetter = 'a'; variedLetter <= 'z'; variedLetter ++) {
                    String possibleNeighbour = word.substring(0, i) + variedLetter + word.substring(i + 1);
                    int pos = searchWordPosition(possibleNeighbour);
                    if(lexicon.get(pos).word.compareTo(word) == 0) {
                        newWordEntry.neighbourList.add(possibleNeighbour);
                        lexicon.get(pos).addNeighbour(word);
                    }
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


    public String[] toWordArray() {
        String[] wordList = new String[lexicon.size()];
        for(int i = 0; i < lexicon.size(); i ++) {
            wordList[i] = lexicon.get(i).word;
        }
        return wordList;
    }

    public String toString() {
        String lexiconString = "";
        for(LexiconEntryArrayList i : lexicon) {
            lexiconString += i + "\n";
        }
        return lexiconString;
    }

}

