import java.util.HashMap;
import java.util.Random;

public class MarkovChain {

    private final String source;
    private HashMap<String, Integer> mappings;

    public MarkovChain(String source){
        this.source = source;
        this.mappings = new HashMap<>();
    }

    public String getSource(){
        return source;
    }

    public void addWord(String word){
        if(this.mappings.containsKey(word)){
            this.mappings.replace(word, this.mappings.get(word) + 1);
        } else {
            this.mappings.put(word, 1);
        }
    }

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
