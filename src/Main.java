import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        // TODO: MAKE EVERYTHING LOWERCASE SO MORE MATCHES ALSO WTF AM I DOING WITH FULL STOPS LOL
        // TODO: fix regex option thing

        ConfigFileLoader configFileLoader = new ConfigFileLoader();
        ArrayList<String> words;

        try {
            words = configFileLoader.getFileContents("src/sampletext", "");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed.");
            return;
        }

        ArrayList<MarkovPoint> markovPoints = new ArrayList<>();
        boolean found = false;
        int matches = 0;

        for(int index = 0; index < words.size() - 1; index++){
            found = false;

            for(int k = 0; k < markovPoints.size(); k++){
                if(markovPoints.get(k).getSource().equals(words.get(index))){
                    markovPoints.get(k).addWord(words.get(index + 1));
                    found = true;
                    matches++;
                    break;
                }
            }

            if(!found || markovPoints.size() == 0){
                markovPoints.add(new MarkovPoint(words.get(index)));
                markovPoints.get(index - matches).addWord(words.get(index + 1));
            }
        }

        for(MarkovPoint markovPoint : markovPoints){
            markovPoint.resolveMappings(markovPoints);
        }

        Random random = new Random();
        MarkovPoint currentPoint = markovPoints.get(random.nextInt(markovPoints.size()));

        while(true){
            System.out.println(currentPoint.getSource());
            currentPoint = currentPoint.nextPoint();
        }

        /**
         Random random = new Random();
         MarkovPoint currentPoint = markovPoints.get(random.nextInt(markovPoints.size()));
         String nextWord;

         for(int i = 0; i < 10000; i++){
         System.out.print(currentPoint.getSource() + " ");
         nextWord = currentPoint.nextWord();
         for(MarkovPoint tempPoint : markovPoints){
         if(tempPoint.getSource().equals(nextWord)){
         currentPoint = tempPoint;
         break;
         }
         }**/

        /**
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
         }
         }**/
    }
}
