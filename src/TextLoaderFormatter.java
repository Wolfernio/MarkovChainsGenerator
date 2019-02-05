import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods which take a source file and split it into "words" based on how words are defined at the time.
 */
final class TextLoaderFormatter {

    /**
     * Don't let anyone instantiate this class.
     */
    private TextLoaderFormatter(){
    }

    /**
     * Takes a plaintext file and returns an ArrayList containing all the words and full stops in the file. This
     * method splits the contents of the file by spaces, then checks each element against the default regex string
     * "(\\$|£)?[\\w']+". If a complete match is found, the word is accepted. All letters are lowercased. If a word
     * has a comma or a full stop immediately following it, then the word is stripped of the punctuation point and
     * the point is entered as its own word.
     */
    static ArrayList<String> getFileContents(String filename) throws FileNotFoundException{
        return TextLoaderFormatter.getFileContents(filename, "(\\$|£)?[\\w']+");
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
    static ArrayList<String> getFileContents(String filename, String regexString) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        ArrayList<String> fileContents = new ArrayList<>();

        String fileAsString = "";

        while(scanner.hasNext()){
            fileAsString += scanner.nextLine() + " \n";
        }

        fileContents = TextLoaderFormatter.splitString(fileAsString, regexString);

        return fileContents;
    }

    /**
     * Splits a {@code \n} delimited string into words as defined by the {@code regexString}
     *
     * @param textToSplit the string to split
     * @param regexString definition of a word as a regex string
     * @return the words in the given text
     */
    private static ArrayList<String> splitString(String textToSplit, String regexString){
        ArrayList<String> formattedText = new ArrayList<>();

        String[] lines = textToSplit.toLowerCase().split("\\n");
        for(String line : lines){
            for(String currentWord : line.split(" ")){
                if(currentWord.matches(regexString)){
                    formattedText.add(currentWord);
                } else if(currentWord.matches(regexString + "\\.")){
                    formattedText.add(currentWord.substring(0, currentWord.length() - 1));
                    formattedText.add(".");
                } else if(currentWord.matches(regexString + ",")){
                    formattedText.add(currentWord.substring(0, currentWord.length() - 1));
                    formattedText.add(",");
                }
            }
        }

        return formattedText;
    }

    /**
     * Splits a {@code \n} delimited string into words as defined by the regex string {@code (\$|£)?[\w']+}
     *
     * @param textToSplit the string to split
     * @return the words in the given text
     */
    static ArrayList<String> splitText(String textToSplit){
        return TextLoaderFormatter.splitText(textToSplit, "(\\$|£)?[\\w']+");
    }


    /**
     * Splits a {@code \n} delimited string into words as defined by the {@code regexString}
     *
     * @param textToSplit the string to split
     * @param regexString definition of a word as a regex string
     * @return the words in the given text
     */
    static ArrayList<String> splitText(String textToSplit, String regexString){
        return splitString(textToSplit, regexString);
    }
}
