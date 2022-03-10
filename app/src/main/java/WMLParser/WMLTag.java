package WMLParser;

import java.util.ArrayList;

public class WMLTag {
    public String name;
    public ArrayList<Object> values;

    WMLTag(String name) {
        this.name = name;
        values = new ArrayList<>();
    }
}
