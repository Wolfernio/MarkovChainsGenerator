import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigFileLoader {

    /**
     * Takes a plaintext file and returns an ArrayList containing all the words and full stops in the file. Ensures
     * that only words are permitted with the default regex string /(\$|£)?[\w']+/ and all letters are lowercased.
     * Other regex strings are permitted.
     *
     * @param filename    the name of the file
     * @param regexString the regex string to match
     * @return an {@code ArrayList<String>} containing all the words in the text file, as well as full stops.
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<String> getFileContents(String filename, String regexString) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        ArrayList<String> fileContents = new ArrayList<>();

        String currentLine;

        while(scanner.hasNext()){
            currentLine = scanner.nextLine();

            for(String currentWord : currentLine.split(" ")){
                if(currentWord.matches("(\\$|£)?[\\w']+")){
                    fileContents.add(currentWord);
                } else if(currentWord.matches("(\\$|£)?[\\w']+\\.")){
                    fileContents.add(currentWord.substring(0, currentWord.length() - 1));
                    fileContents.add(".");
                } else if(currentWord.matches("(\\$|£)?[\\w']+,")){
                    fileContents.add(currentWord.substring(0, currentWord.length() - 1));
                    fileContents.add(",");
                }
            }
        }

        return fileContents;
    }
}
