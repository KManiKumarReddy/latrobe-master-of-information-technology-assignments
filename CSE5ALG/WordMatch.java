import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class WordMatch {
    static int wordsRead = 0;

    public static void insertWordsFromFile(String fileName, Lexicon lexicon) throws IOException {

        File wordsFile = new File(fileName);
        if (!wordsFile.exists() || !wordsFile.canRead()) {
            System.out.println("\n\tFile - " + fileName + " does not exist");
            return;
        }
        Scanner fileScanner = new Scanner(wordsFile);
        while (fileScanner.hasNext()) {
            String word = "";
            for (char c : fileScanner.next().toLowerCase().toCharArray()) {
                if (c >= 'a' && c <= 'z')
                    word += c;
            }
            if (!word.isEmpty()){
                lexicon.addWord(word);
                wordsRead++;
            }
        }
        fileScanner.close();
    }

    public static void main(String args[]) throws IOException {
        long startTime = System.currentTimeMillis();
        if (args.length != 4) {
            System.out.println("Invalid command line arguments provided");
            return;
        }
        Lexicon lexicon = new Lexicon();
        File in1File = new File(args[0]);
        if (!in1File.exists() || !in1File.canRead()) {
            System.out.println("\n\tFile - " + args[0] + " does not exist");
            return;
        }
        Scanner fileScanner = new Scanner(in1File);

        if (fileScanner.hasNext()) {
            while (fileScanner.hasNext()) {
                insertWordsFromFile(fileScanner.next(), lexicon);
            }
        } else {
            System.out.println("\n\tFile - " + args[0] + " is empty");
            fileScanner.close();
            return;
        }
        long lexiconCreatedTime = System.currentTimeMillis();
        System.out.println("lexicon created time : " + (lexiconCreatedTime - startTime));
        System.out.println("words Read: "+ wordsRead);
        fileScanner.close();
        File out1File = new File(args[1]);
        FileWriter outFileWriter = new FileWriter(out1File);
        if (!out1File.exists()) {
            out1File.createNewFile();
        }
        outFileWriter.write(lexicon.toString());
        outFileWriter.close();

        // lexicon.print();

        String patternString;
        File in2File = new File(args[2]);
        if (!in1File.exists() || !in1File.canRead()) {
            System.out.println("\n\tFile - " + args[2] + " does not exist");
            return;
        }
        fileScanner = new Scanner(in2File);
        if (fileScanner.hasNext()) {
            patternString = fileScanner.next();
        } else {
            System.out.println("\n\tFile - " + args[2] + " is empty");
            fileScanner.close();
            return;
        }
        File out2File = new File(args[3]);
        if (!out2File.exists()) {
            out2File.createNewFile();
        }
        outFileWriter = new FileWriter(out2File);
        // Brute force
        /*
         * Pattern pattern = Pattern.compile(patternString.toLowerCase().replace("*",
         * "[a-z]*").replace("?", "[a-z]")); int firstWildCardPostiton =
         * patternString.indexOf("?") != -1 ? (patternString.indexOf("*") != -1 &&
         * patternString.indexOf("*") < patternString.indexOf("?") ?
         * patternString.indexOf("*") : patternString.indexOf("?") ) :
         * (patternString.indexOf("*") != -1 ? patternString.indexOf("*") :
         * patternString.length() ) ; String startingLetters =
         * patternString.substring(0,firstWildCardPostiton); for(int i =
         * lexicon.searchWordPosition(startingLetters); i< lexicon.lexicon.size(); i++ )
         * { LexiconEntry entry = lexicon.lexicon.get(i); String w = entry.word;
         * if(pattern.matcher(w).matches()) {
         * outFileWriter.write(w+"\t"+entry.frequency+"\n"); } }
         */
        // More optimised own implementation recursive way
        lexicon.printMatchingWordsToFile(patternString.toLowerCase(), outFileWriter);
        long searchTime = System.currentTimeMillis();
        System.out.println("search time : " + (searchTime - lexiconCreatedTime));
        System.out.println("total time : " + (searchTime - startTime));
        fileScanner.close();
        outFileWriter.close();

    }
}