package WMLParser;

import java.util.ArrayList;
import java.util.Objects;

public class WMLTag {
    public final String name;
    public final ArrayList<Object> values;

    WMLTag(String name) {
        this.name = name;
        values = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "WMLTag{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WMLTag wmlTag = (WMLTag) o;
        return name.equals(wmlTag.name) && values.equals(wmlTag.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, values);
    }
}
