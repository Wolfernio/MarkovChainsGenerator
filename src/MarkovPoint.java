import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Implementation of a single link in a Markov chain. Stores a word, and a list of words (and respective probabilities)
 * it's possible to go to.
 */

public class MarkovPoint {

    private final String source;
    private HashMap<String, Integer> mappings;
    private HashMap<MarkovPoint, Integer> resolvedMappings;
    private boolean mappingsAreResolved;

    /**
     * Constructs a {@code MarkovPoint} with the specified originator word
     *
     * @param source the originator word
     */
    public MarkovPoint(String source){
        this.source = source;
        this.mappings = new HashMap<>();
        this.resolvedMappings = new HashMap<>();
        this.mappingsAreResolved = false;
    }

    /**
     * Returns the originator word for this point
     *
     * @return the originator word for this point
     */
    public String getSource(){
        return source;
    }

    /**
     * Adds a possible next word to this point. If the word is already present, it increases the probability that
     * this word will be chosen as the next word
     *
     * @param word the specified word to be added
     */
    public void addWord(String word){
        if(this.mappings.containsKey(word)){
            this.mappings.replace(word, this.mappings.get(word) + 1);
        } else {
            this.mappings.put(word, 1);
        }
    }

    /**
     * Returns the next word from this point. This is chosen with a roulette wheel selection
     *
     * @return the next word from this point
     */
    @SuppressWarnings("Duplicates")
    public String nextWord(){
        int total = 0;
        String key;
        Integer value;

        for(Integer currentValue : this.mappings.values()){
            total += currentValue;
        }

        Random random = new Random();
        int pickedNumber = random.nextInt(total) + 1;

        for(HashMap.Entry<String, Integer> entry : this.mappings.entrySet()){
            key = entry.getKey();
            value = entry.getValue();

            if(pickedNumber <= value){
                return key;
            } else {
                pickedNumber -= value;
            }
        }

        throw new RuntimeException(
                "A word was not chosen somehow.. If you're seeing this then mike's a bad programmer");
    }

    /**
     * Creates an internal mapping list that maps all possible next words to their respective probabilities. Words
     * are stored as MarkovPoints instead of strings, so this method requires an ArrayList of all instantiated
     * MarkovPoints as an argument
     *
     * @param allMarkovPoints all instantiated MarkovPoints
     */
    public void resolveMappings(ArrayList<MarkovPoint> allMarkovPoints){
        for(MarkovPoint markovPoint : allMarkovPoints){
            for(HashMap.Entry<String, Integer> entry : this.mappings.entrySet()){
                if(markovPoint.getSource().equals(entry.getKey())){
                    this.resolvedMappings.put(markovPoint, entry.getValue());
                }
            }
        }
    }

    /**
     * A computationally faster way of retrieving the next word than {@code nextWord} as this method returns a
     * MarkovPoint instead of a string. Requires the execution of {@code resolveMappings} before executing.
     * @return the next MarkovPoint to visit from this point.
     */
    @SuppressWarnings("Duplicates")
    public MarkovPoint nextPoint(){
        if(!this.mappingsAreResolved){
            throw new RuntimeException(
                    "Mappings must be resolved before using nextPoint. Use the resolveMappings method.");
        }

        int total = 0;
        MarkovPoint key;
        Integer value;

        for(Integer currentValue : this.resolvedMappings.values()){
            total += currentValue;
        }

        Random random = new Random();
        int pickedNumber = random.nextInt(total) + 1;

        for(HashMap.Entry<MarkovPoint, Integer> entry : this.resolvedMappings.entrySet()){
            key = entry.getKey();
            value = entry.getValue();

            if(pickedNumber <= value){
                return key;
            } else {
                pickedNumber -= value;
            }
        }

        throw new RuntimeException("A point was not chosen somehow.. If you're seeing this then mike's a bad " +
                "programmer");
    }
}
