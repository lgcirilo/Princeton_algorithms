import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cyfa on 10/09/2018.
 */

public class Teste {
    public static void main(String[] args) {
        ArrayList<Integer> segments1 = new ArrayList<Integer>();
        ArrayList<Integer> uniqueSegments1 = new ArrayList<Integer>();
        for (int i = 0; i < 200; i ++) {
            segments1.add((int) Math.floor(Math.random() * 10));
        }
    for ( int i = 0; i < segments1.size(); i ++) {
        boolean exists = false;
        String str1 = segments1.get(i).toString();
        if (uniqueSegments1.size() == 0) {
            uniqueSegments1.add(segments1.get(i));
            exists = true;
        } else {
            for (int j = 0; j < uniqueSegments1.size(); j++) {
                String str2 = uniqueSegments1.get(j).toString();
                if (str2.equals(str1)) {
                    exists = true;
                }
            }
        }
        if (exists == false) {
            uniqueSegments1.add(segments1.get(i));
//                    exists = true;
        }
    }
        Collections.sort(uniqueSegments1);
            System.out.println("kjhgkhbj");
    }

}
