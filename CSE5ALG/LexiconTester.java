import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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