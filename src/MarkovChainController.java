import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class MarkovChainController {

    ArrayList<MarkovPoint> markovPoints;

    public MarkovChainController(String filename) throws FileNotFoundException{
        ArrayList<String> words = ConfigFileLoader.getFileContents(filename);
        this.markovPoints = this.createMarkovPoints(words);
        this.finaliseMarkovPoints();
    }

    private ArrayList<MarkovPoint> createMarkovPoints(ArrayList<String> words){
        ArrayList<MarkovPoint> markovPoints = new ArrayList<>();
        boolean found;
        int offset = 0;

        for(int index = 0; index < words.size() - 1; index++){
            found = false;

            for(int k = 0; k < markovPoints.size(); k++){
                if(markovPoints.get(k).getSource().equals(words.get(index))){
                    markovPoints.get(k).addWord(words.get(index + 1));
                    found = true;
                    offset++;
                    break;
                }
            }

            if(!found || markovPoints.size() == 0){
                markovPoints.add(new MarkovPoint(words.get(index)));
                markovPoints.get(index - offset).addWord(words.get(index + 1));
            }
        }

        return markovPoints;
    }

    private void finaliseMarkovPoints(){
        for(MarkovPoint currentMarkovPoint : this.markovPoints){
            currentMarkovPoint.resolveMappings(this.markovPoints);
        }
    }

    public String generateText(int length){
        String output = "";
        Random random = new Random();

        MarkovPoint currentPoint = this.markovPoints.get(random.nextInt(this.markovPoints.size()));
        MarkovPoint nextPoint;

        for(int i = 0; i < length; i++){
            nextPoint = currentPoint.nextPoint();

            if(nextPoint.getSource().equals(".") || nextPoint.getSource().equals(",")){
                output += currentPoint.getSource();
            } else {
                output += currentPoint.getSource() + " ";
            }

            currentPoint = nextPoint;
        }

        String correctedOutput = "";
        boolean mustCapitalise = true;
        char currentChar;

        for(int i = 0; i < output.length(); i++){
            currentChar = output.charAt(i);
            if(currentChar == '.'){
                mustCapitalise = true;
            } else if(currentChar != ' ' && mustCapitalise){
                currentChar = Character.toUpperCase(currentChar);
                mustCapitalise = false;
            }
            correctedOutput += currentChar;
        }

        return correctedOutput;
    }
}
