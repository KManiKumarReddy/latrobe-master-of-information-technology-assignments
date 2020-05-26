import java.util.*;
import java.io.*;

public class WordLink {

    int difficultyLevel;
    Dictionary dictionary;
    Scanner keyboard;

    public WordLink ( ) {
        dictionary = new Dictionary();
        difficultyLevel = 1;
        keyboard = new Scanner(System.in);
    }


    public static void main(String[] args) throws IOException
    {
        System.out.println("\n\t***Mani Kumar Reddy Kancharla 13B81A0579**");
        WordLink wl = new WordLink();
        wl.importDictionaryFromFile();
        wl.run();
    }
    
    public void importDictionaryFromFile() throws IOException {
        File dictionaryFile =  new File("dictionary.txt");
        if(!dictionaryFile.exists()){
            System.out.println("\n\tFile - dictionary.txt does not exist");
            return;
        }
        Scanner fileScanner = new Scanner(dictionaryFile);
        if(!fileScanner.hasNext()){
            System.out.println("\n\tFile - dictionary.txt is empty");
            fileScanner.close();
            return;
        }
        if(!fileScanner.hasNextInt()){
            System.out.println("\n\tInvalid start of dictionary file - " + fileScanner.next());
            fileScanner.close();
            return;
        }
        int difficulty;
        difficulty = fileScanner.nextInt();
        while(fileScanner.hasNext()) {
            if(fileScanner.hasNextInt())
                difficulty = fileScanner.nextInt();
            else
                this.dictionary.insertNewWord(fileScanner.next(), difficulty);
        }
        fileScanner.close();
        
        System.out.println("\n\tImported words from dictionary.txt");
        displayDictionary();
    }

    public void run( ) throws IOException
    {
        char choice = '\0';
        while( choice != 'E' )
        {
            displayMenu( );
            System.out.print( "\n\tSelect a function from the menu: ");
            choice = keyboard.next().charAt(0);
            System.out.println( );
            keyboard.nextLine( );
            process( choice );
        }
    }

    private void displayMenu() {
		System.out.println("\n\n\tWordLink");
		System.out.println("\n\t\tA set the difficulty level");
		System.out.println("\t\tB display the dictionary");
		System.out.println("\t\tC insert a word to the dictionary");
		System.out.println("\t\tD play the game (1 player)");
		System.out.println("\t\tE exit");
		System.out.println("\t\tF play the game (2 players)");
    }
    

	private void process( int choice ) throws IOException { 
		switch( choice ) {
			case 'A' :
			setDifficultyLevel();
			break;
			case 'B' :
			displayDictionary();
			break;
			case 'C' :
			insertWordToDictionary();
			break;
			case 'D' :
			play();
			break;
			case 'F' :
			playMultiplayer();
			break;
			case 'E':
			break;
			default:
			System.out.println("That is not a valid choice");
		}
    }
    
    private void setDifficultyLevel() {
        System.out.println("\nSet the difficulty level");
        System.out.print("\nThe current difficulty level is "+this.difficultyLevel+". Type the new level: ");
        int newLevel = keyboard.nextInt();
        while(newLevel != 1 && newLevel != 2){
            System.out.print("Enter either 1 or 2: ");
            newLevel = keyboard.nextInt();
        }
        this.difficultyLevel = newLevel;
        System.out.println("The difficulty level has now been set as "+this.difficultyLevel+" .");
        return;
    }
    
    private void insertWordToDictionary() {
        System.out.println("Insert a word to the dictionary");
        System.out.print("\nEnter the word: ");
        String word = keyboard.next().toLowerCase();
        if(word.length() == 0) {
            System.out.print("Please Enter a valid word: ");
            word = keyboard.next().toLowerCase();
        }
        System.out.print("Difficulty level: ");
        int difficultLevel = keyboard.nextInt();
        while(difficultLevel != 1 && difficultLevel != 2){
            System.out.print("Enter either 1 or 2: ");
            difficultLevel = keyboard.nextInt();
        }
        dictionary.insertNewWord(word, difficultyLevel);
        return;
    }
    
    private void displayDictionary() throws IOException {
        System.out.println("\n\n\tDisplay the dictionary\n");

        System.out.println("Level 1");
        char currentChar = 'a';
        boolean wordsRemaining = true;
        DictionaryNode currentNode = dictionary.getWordListOfCharacter(currentChar).getHead();
        while(wordsRemaining) {
            int lineCount = 0;
            while(lineCount < 5 && wordsRemaining) {
                int wordCount = 0;
                while(wordCount < 7 && wordsRemaining) {
                    while (currentNode == null && currentChar < 'z') {
                        currentNode = dictionary.getWordListOfCharacter(++currentChar).getHead();
                        while (currentNode != null && currentNode.getLevel() == 2) {
                            currentNode = currentNode.next();
                        }
                    }
                    if(currentChar >= 'z' && currentNode == null){
                        wordsRemaining = false;
                        break;
                    }
                    System.out.print(currentNode.word + "\t");
                    wordCount++;
                    currentNode = currentNode.next();
                }
                lineCount++;
                System.out.println();
            }
            System.out.println("\npress a key to continue ...");
            keyboard.nextLine();
        }
        
        System.out.println("Level 2");
        currentChar = 'a';
        wordsRemaining = true;
        currentNode = dictionary.getWordListOfCharacter(currentChar).getHead();
        while(wordsRemaining) {
            int lineCount = 0;
            while(lineCount < 5 && wordsRemaining) {
                int wordCount = 0;
                while(wordCount < 7 && wordsRemaining) {
                    while (currentNode == null && currentChar < 'z') {
                        currentNode = dictionary.getWordListOfCharacter(++currentChar).getHead();
                        while (currentNode != null && currentNode.getLevel() == 1) {
                            currentNode = currentNode.next();
                        }
                    }
                    if(currentChar >= 'z' && currentNode == null){
                        wordsRemaining = false;
                        break;
                    }
                    System.out.print(currentNode.word + "\t");
                    wordCount++;
                    currentNode = currentNode.next();
                }
                lineCount++;
                System.out.println();
            }
            System.out.println("\npress a key to continue ...");
            keyboard.nextLine();
        }
        return;
    }
    
    private void play() {
        System.out.println("\nplay the game (Level "+this.difficultyLevel+")");
        String userWord;
        DictionaryNode computerWord;
        String currString = "";
        System.out.print("\nEnter a word: ");
        userWord = keyboard.next().toLowerCase();
        while (true) {
            if(!dictionary.hasWord(userWord, difficultyLevel)){
                System.out.println("\n“"+userWord+"” doesn’t exist in the dictionary. You didn’t win.");
                return;
            }
            if(currString.indexOf(userWord) != -1 ) {
                System.out.println("\n“"+userWord+"” is already used in the dictionary. You didn’t win.");
                return;
            }
            currString += userWord + " - ";
            computerWord =  dictionary.getWordListOfCharacter(userWord.charAt(userWord.length() - 1)).getHead();
            while(computerWord != null && currString.indexOf(computerWord.word) != -1)
                computerWord = computerWord.next();
            if(computerWord == null) {
                System.out.println("\nWell done! You win.");
                return;
            }
            currString += computerWord.word + " - ";
            System.out.print("\n"+currString);
            userWord = keyboard.next();
        }
    }
    
    private void playMultiplayer() {
        System.out.println("\nplay the game (2 Players -  Level "+this.difficultyLevel+")");
        String userWord;
        String currString = "";
        System.out.print("\nP1: Enter a word: ");
        userWord = keyboard.next().toLowerCase();
        while (true) {
            if(!dictionary.hasWord(userWord, difficultyLevel)){
                System.out.println("\n“"+userWord+"” doesn’t exist in the dictionary!");
                System.out.println("\n--Player two wins!--");
                return;
            }
            if(currString.indexOf(userWord) != -1 ) {
                System.out.println("\n“"+userWord+"” is already used in the dictionary!");
                System.out.println("\n--Player two wins!--");
                return;
            }
            currString += userWord + " - ";
            System.out.print("P2: " + currString);
            userWord = keyboard.next().toLowerCase();
            if(!dictionary.hasWord(userWord, difficultyLevel)){
                System.out.println("\n“"+userWord+"” doesn’t exist in the dictionary!");
                System.out.println("\n--Player one wins!--");
                return;
            }
            if(currString.indexOf(userWord) != -1 ) {
                System.out.println("\n“"+userWord+"” is already used in the dictionary!");
                System.out.println("\n--Player onw wins!--");
                return;
            }
            currString += userWord + " - ";
            System.out.print("P1: "+currString);
            userWord = keyboard.next();
        }
    }

}

class DictionaryNode {
    protected String word; // word to be stored
    private int level; // level of the word
    private DictionaryNode next;
    public DictionaryNode(String _word, int _level) { 
        word = _word;
        level = _level;
        next = null;
    }
    public DictionaryNode(String _word, int _level, DictionaryNode _next) { 
        word = _word;
        level = _level;
        next = _next;
    }

    public void setNext(DictionaryNode _next) {
        this.next = _next;
    }

    public DictionaryNode next() {
        return this.next;
    }

    public int getLevel() {
        return level;
    }
}
class ListOfNodes {
    //object of the class represents a linked list of words starting
    //with a specific character.
    private DictionaryNode head = null; //head of the linked list

    public void addWord(String word, int difficultyLevel) {
        if(head == null || head.word.compareTo(word) > 0) {
            head = new DictionaryNode(word, difficultyLevel, head);
            return;
        }
        DictionaryNode current = head;
        while(current.next() != null && current.next().word.compareTo(word) < 0)
            current = current.next();
        if(current.word.compareTo(word) == 0){
            System.out.println("\n“"+word+"” exists in the dictionary. Insertion aborted.");
            return;
        }
        current.setNext(new DictionaryNode(word, difficultyLevel, current.next()));
        System.out.println("\n“"+word+"” is inserted.");
    }

    public DictionaryNode getHead(){
        return head;
    }

    public boolean hasWord(String word, int difficultLevel) {
        DictionaryNode currentNode = head;
        while (currentNode != null) {
            if(currentNode.word.equals(word)) {
                if(difficultLevel == 2)
                    return true;
                // if game difficuly level is 1 and word level is 2
                if(currentNode.getLevel() == 2)
                    return false;
                return true;
            }
            currentNode = currentNode.next();
        }
        return false;
    }
    
}
class Dictionary {
    //object of the class represents the whole dictionery
    private ListOfNodes[] data;

    public Dictionary() {
        data = new ListOfNodes[26];
        for(int i = 0; i < 26; i ++)
            data[i] = new ListOfNodes();
    }

    public ListOfNodes getWordListOfCharacter(char c) {
        return data[c - 'a'];
    }

    public void insertNewWord(String word, int difficultyLevel) {
        data[word.charAt(0) - 'a'].addWord(word, difficultyLevel);
    }

    public boolean hasWord(String word, int difficultyLevel) {
        return data[word.charAt(0) - 'a'].hasWord(word, difficultyLevel);
    }
}