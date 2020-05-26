import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

class WordMatchTester {


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

    public static void main(String args[]) throws IOException{
        if(args.length != 1) {
            System.out.println("Invalid command line arguments provided");
            return;
        }
        LexiconArrayList lexicon = new LexiconArrayList();
        insertWordsFromFile("sample1.txt", lexicon);
        insertWordsFromFile("sample2.txt", lexicon);

        Pattern pattern = Pattern.compile(args[0].toLowerCase().replace("*", "[a-z]+").replace("?", "[a-z]"));
        File outFile = new File("wordmatchtesteroutput.txt");
        if(!outFile.exists()) {
            outFile.createNewFile();
        }
        FileWriter outFileWriter = new FileWriter(outFile);
        boolean wordsFound = false;
        for(String w : lexicon.toWordArray()) {
            if(pattern.matcher(w).matches()) {
                if(!wordsFound) {
                    System.out.println("The pattern");
                    System.out.println("\t"+args[0]);
                    System.out.println("result in the output:");
                    outFileWriter.write("The pattern\n");
                    outFileWriter.write("\t"+args[0]+"\n");
                    outFileWriter.write("result in the output:\n");
                    wordsFound = true;
                }
                System.out.println("\t"+w);
                outFileWriter.write("\t"+w+"\n");
            }
        }
        if(!wordsFound) {
            System.out.println("\tNo words in the lexicon match the pattern");
            outFileWriter.write("\tNo words in the lexicon match the pattern\n");
        }
        outFileWriter.close();
        
    }
}