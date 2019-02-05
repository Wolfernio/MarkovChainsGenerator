import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Handles everything to do with generating text so you don't have to! This class sets itself up on instantiation and
 * is ready to {@code generateText(int length)} out of the box.
 */
class MarkovChainController {

    private ArrayList<MarkovPoint> markovPoints;

    /**
     * Creates a MarkovChainController and sets it up ready to go. Don't be scared if this takes a short while!
     *
     * @param data the path to source file containing the text to mimic, or the source to mimic
     * @param isUsingFile whether or not to use a source file or just plain text as a string
     * @throws FileNotFoundException if the source file is not found
     */
    MarkovChainController(String data, boolean isUsingFile) throws FileNotFoundException{
        if(isUsingFile){
            this.markovPoints = this.createMarkovPoints(TextLoaderFormatter.getFileContents(data));
            this.finaliseMarkovPoints();
        } else {
            this.markovPoints = this.createMarkovPoints(TextLoaderFormatter.splitText(data));
            this.finaliseMarkovPoints();
        }
    }

    /**
     * Generates text based on the {@link MarkovPoint}s in the chain.
     * @param length the number of words to generate
     * @return the generated text
     */
    public String generateText(int length){
        String output = "";
        Random random = new Random();

        MarkovPoint currentPoint = this.markovPoints.get(random.nextInt(this.markovPoints.size()));
        MarkovPoint nextPoint;

        for(int i = 0; i < length; i++){
            nextPoint = currentPoint.nextPoint();

            if(nextPoint.getSource().equals(".") || nextPoint.getSource().equals(",")){
                if(currentPoint.getSource().equals("i")){
                    output += currentPoint.getSource().toUpperCase();
                } else {
                    output += currentPoint.getSource();
                }
            } else {
                if(currentPoint.getSource().equals("i")){
                    output += currentPoint.getSource().toUpperCase() + " ";
                } else {
                    output += currentPoint.getSource() + " ";
                }
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

    /**
     * Corrects the markov points to be mapped to other markov points, rather than just words.
     */
    private void finaliseMarkovPoints(){
        for(MarkovPoint currentMarkovPoint : this.markovPoints){
            currentMarkovPoint.resolveMappings(this.markovPoints);
        }
    }

    // Private methods

    /**
     * Creates all MarkovPoints and maps them to strings.
     *
     * @param words all words in order in the source file
     * @return all markov points
     */
    private ArrayList<MarkovPoint> createMarkovPoints(ArrayList<String> words){
        ArrayList<MarkovPoint> markovPoints = new ArrayList<>();
        boolean found;
        int offset = 0;

        for(int index = 0; index < words.size() - 1; index++){
            found = false;

            for(MarkovPoint markovPoint : markovPoints){
                if(markovPoint.getSource().equals(words.get(index))){
                    markovPoint.addWord(words.get(index + 1));
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
    // End private methods
}
