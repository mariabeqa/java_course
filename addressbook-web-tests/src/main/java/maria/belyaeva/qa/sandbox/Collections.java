package maria.belyaeva.qa.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "PHP", "Python"};

        for(String l : langs) {
            System.out.println("I like " + l);
        }

        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("PHP");
        languages.add("C#");
        for (String l : languages) {
            System.out.println("I like " + l);
        }

        List<String> languages2 = Arrays.asList("a", "b", "c", "d");
        for(String l : languages2) {
            System.out.println("This is " + l);
        }

        for(int j=0;j<languages2.size(); j++) {
            System.out.println("This is " + languages2.get(j));
        }

    }
}
