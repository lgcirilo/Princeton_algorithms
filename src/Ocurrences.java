// TODO - move to separate project. Not part of princeton's algorithms. Check if part of an online judge.
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

// counts
class Ocurrences {

    public Map<Character,Integer> countOcurrences(String str) {
        Map<Character, Integer> ocurrences = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c != ' ') {
                if (!ocurrences.containsKey(c)) {
                    ocurrences.put(c, 1);
                } else {
                    ocurrences.put(c, ocurrences.get(c)+1);
                }
            }
        }
        return ocurrences;
    }

    public static void main(String[] args) {
        Ocurrences ocr = new Ocurrences();
        Map gustavo = ocr.countOcurrences("luiz gustavo santos cirilo");
        System.out.println(gustavo.toString());
    }
}