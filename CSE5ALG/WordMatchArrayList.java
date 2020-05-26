import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

class WordMatchArrayList {


    public static void insertWordsFromFile(String fileName, LexiconArrayList lexicon) throws IOException{

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

    static void printMatchingWordsToFile(LexiconArrayList lexicon, String pattern,FileWriter writer) throws IOException{
        // changing all 
        int quesionMarkIndex = pattern.indexOf('?'), asterixIndex;
        if(quesionMarkIndex != -1) {
            for(char i = 'a'; i <= 'z'; i ++)
                printMatchingWordsToFile(lexicon, pattern.substring(0, quesionMarkIndex) + i + pattern.substring(quesionMarkIndex+ 1), writer); 
        }
        else if((asterixIndex = pattern.indexOf('*')) != -1 ) {
            String startingLetters = pattern.substring(0,asterixIndex) + "0";
            String lastPossiblePattern = pattern.substring(0,asterixIndex) + "{";
            LexiconEntryArrayList entry;
            Pattern compiledPattern = Pattern.compile(pattern.toLowerCase().replace("*", "[a-z]*"));
            for(int i = lexicon.searchWordPosition(startingLetters); (entry = lexicon.lexicon.get(i)).word.compareTo(lastPossiblePattern) < 0; i++ ) {
                if(compiledPattern.matcher(entry.word).matches()) {
                    writer.write(entry.word+"\t"+entry.frequency+"\n");
                }
            }
        }
        else {
            LexiconEntryArrayList word = lexicon.lexicon.get(lexicon.searchWordPosition(pattern));
            if(word.word.compareTo(pattern) == 0)
            writer.write(pattern+"\t"+word.frequency+"\n");
        }
    }

    public static void main(String args[]) throws IOException{
        long startTime = System.currentTimeMillis();
        if(args.length != 4) {
            System.out.println("Invalid command line arguments provided");
            return;
        }
        LexiconArrayList lexicon = new LexiconArrayList();
        File in1File =  new File(args[0]);
        if(!in1File.exists() || !in1File.canRead()){
            System.out.println("\n\tFile - "+args[0]+" does not exist");
            return;
        }
        Scanner fileScanner = new Scanner(in1File);

        if(fileScanner.hasNext()) {
            while(fileScanner.hasNext()) {
                insertWordsFromFile(fileScanner.next(), lexicon);
            }
        }
        else {
            System.out.println("\n\tFile - "+args[0]+" is empty");
            fileScanner.close();
            return;
        }
        long lexiconCreatedTime = System.currentTimeMillis();
        System.out.println("lexicon created time : " + (lexiconCreatedTime - startTime));
        fileScanner.close();
        File out1File = new File(args[1]);
        FileWriter outFileWriter = new FileWriter(out1File);
        if(!out1File.exists()) {
            out1File.createNewFile();
        }
        outFileWriter.write(lexicon.toString());
        outFileWriter.close();


        String patternString;
        File in2File =  new File(args[2]);
        if(!in1File.exists() || !in1File.canRead()){
            System.out.println("\n\tFile - "+args[2]+" does not exist");
            return;
        }
        fileScanner = new Scanner(in2File);
        if(fileScanner.hasNext()) {
            patternString = fileScanner.next();
        }
        else {
            System.out.println("\n\tFile - "+args[2]+" is empty");
            fileScanner.close();
            return;
        }
        File out2File = new File(args[3]);
        if(!out2File.exists()) {
            out2File.createNewFile();
        }
        outFileWriter = new FileWriter(out2File);
        // More optimised own implementation recursive way
        printMatchingWordsToFile(lexicon, patternString.toLowerCase(), outFileWriter);
        // Java regex way
        /*
        Pattern pattern = Pattern.compile(patternString.toLowerCase().replace("*", "[a-z]*").replace("?", "[a-z]"));
        int firstWildCardPostiton = patternString.indexOf("?") != -1 ? (patternString.indexOf("*") != -1 && patternString.indexOf("*") < patternString.indexOf("?") ? patternString.indexOf("*") : patternString.indexOf("?") ) : (patternString.indexOf("*") != -1  ? patternString.indexOf("*") : patternString.length() ) ;
        String startingLetters = patternString.substring(0,firstWildCardPostiton);
        for(int i = lexicon.searchWordPosition(startingLetters); i< lexicon.lexicon.size(); i++ ) {
            LexiconEntryArrayList entry = lexicon.lexicon.get(i);
            String w = entry.word;
            if(pattern.matcher(w).matches()) {
                outFileWriter.write(w+"\t"+entry.frequency+"\n");
            }
        }
        */
        long searchTime = System.currentTimeMillis();
        System.out.println("search time : " + (searchTime - lexiconCreatedTime));
        System.out.println("total time : " + (searchTime - startTime));
        fileScanner.close();
        outFileWriter.close();
        
    }
}