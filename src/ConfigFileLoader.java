import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods which take a source file and split it into "words" based on how words are defined at the time.
 */
public final class ConfigFileLoader {

    /**
     * Don't let anyone instantiate this class.
     */
    private ConfigFileLoader(){
    }

    /**
     * Takes a plaintext file and returns an ArrayList containing all the words and full stops in the file. This
     * method splits the contents of the file by spaces, then checks each element against the default regex string
     * "(\\$|£)?[\\w']+". If a complete match is found, the word is accepted. All letters are lowercased. If a word
     * has a comma or a full stop immediately following it, then the word is stripped of the punctuation point and
     * the point is entered as its own word.
     */
    public static ArrayList<String> getFileContents(String filename) throws FileNotFoundException{
        return ConfigFileLoader.getFileContents(filename, "(\\$|£)?[\\w']+");
    }

    /**
     * Takes a plaintext file and returns an ArrayList containing all the words and full stops in the file. This
     * method splits the contents of the file by spaces, then checks each element against a supplied regex string. If
     * a complete match is found, the word is accepted. All letters are lowercased. If a word has a comma or a full
     * stop immediately following it, then the word is stripped of the punctuation point and the point is entered as
     * its own word. Thus, do not include any of [,.] in your regex string.
     *
     * @param filename    the name of the file
     * @param regexString the regex string to match
     * @return an {@code ArrayList<String>} containing all the words in the text file, as well as full stops.
     * @throws FileNotFoundException if the file is not found
     */
    public static ArrayList<String> getFileContents(String filename, String regexString) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        ArrayList<String> fileContents = new ArrayList<>();

        String currentLine;

        while(scanner.hasNext()){
            currentLine = scanner.nextLine().toLowerCase();

            for(String currentWord : currentLine.split(" ")){
                if(currentWord.matches(regexString)){
                    fileContents.add(currentWord);
                } else if(currentWord.matches(regexString + "\\.")){
                    fileContents.add(currentWord.substring(0, currentWord.length() - 1));
                    fileContents.add(".");
                } else if(currentWord.matches(regexString + ",")){
                    fileContents.add(currentWord.substring(0, currentWord.length() - 1));
                    fileContents.add(",");
                }
            }
        }

        return fileContents;
    }
}
