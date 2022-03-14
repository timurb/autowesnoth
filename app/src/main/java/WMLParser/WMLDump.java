package WMLParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class WMLDump {
    public static void main(String[] args) throws Exception {
        WMLReader reader;
        if (args[0] == null) {
            System.out.println("Usage: pass file to read as a param");
            System.exit(0);
        }
        reader = new WMLReader(new BufferedReader(new FileReader(args[0])).lines());
        var tag = reader.parse();
        System.out.println(tag);

        var unit = new WMLUnitParser(tag);
        System.out.println(unit.parse());
    }
}
