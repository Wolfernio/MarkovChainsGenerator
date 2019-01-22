import java.util.HashMap;
import java.util.Random;

/**
 * Implementation of a single link in a Markov chain. Stores a word, and a list of words it's possible to go to from
 * that point.
 */

public class MarkovPoint {

    private final String source;
    private HashMap<String, Integer> mappings;

    /**
     * Constructs a {@code MarkovPoint} with the specified originator word
     *
     * @param source the originator word
     */
    public MarkovPoint(String source){
        this.source = source;
        this.mappings = new HashMap<>();
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
     * @return the next word from this point
     */
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

        return null;
    }
}
