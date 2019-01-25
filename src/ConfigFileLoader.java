import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigFileLoader {

    public ArrayList<String> getFileContents(String filename) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        ArrayList<String> fileContents = new ArrayList<>();

        while(scanner.hasNext()){
            fileContents.add(scanner.nextLine());
        }

        return fileContents;
    }
}
