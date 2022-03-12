package WMLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class WMLTag {
    public final String name;
    public final ArrayList<WMLTag> tags;
    public final HashMap<String, WMLAttribute> attributes;

    WMLTag(String name) {
        this.name = name;
        this.tags = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    @Override
    public String toString() {
        return "WMLTag{" +
                "name='" + name + '\'' +
                ",\n tags=" + tags +
                ",\n values=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WMLTag wmlTag)) return false;
        return Objects.equals(name, wmlTag.name) && Objects.equals(tags, wmlTag.tags) && Objects.equals(attributes, wmlTag.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tags, attributes);
    }
}
