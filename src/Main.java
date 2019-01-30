import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args){
        // TODO: Capitalise the start of sentences

        MarkovChainController markovChainController;
        try {
            markovChainController = new MarkovChainController("src/sampletext");
        } catch(FileNotFoundException e) {
            return;
        }

        System.out.println(markovChainController.generateText(100));
    }
}
