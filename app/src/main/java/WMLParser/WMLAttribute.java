package WMLParser;

import utils.PatternMatcher;

import java.util.Objects;
import java.util.regex.Pattern;

public class WMLAttribute {
    static Pattern matchGettext = Pattern.compile("^_ \"(.*)\"$");
    static Pattern matchQuote = Pattern.compile("^\"(.*)\"$");
    static Pattern doubleQuote = Pattern.compile("\"\"");

    String value;
    Boolean gettext = false;
    String text;

    WMLAttribute(String text) {
        this.text = text;
    }

    private static String unquote(String string) {
        return string;
    }

    WMLAttribute parse() {
        var parsed = text.strip();

        if (PatternMatcher.match(parsed, matchGettext) != null) {
            parsed = PatternMatcher.match(parsed, matchGettext);
            gettext = true;
        }

        if (PatternMatcher.match(parsed, matchQuote) != null) {
            parsed = PatternMatcher.match(parsed, matchQuote);
        }

        parsed = parsed.replaceAll(doubleQuote.pattern(), "\"");

        value = parsed;
        return this;
    }

    @Override
    public String toString() {
        return "WMLAttribute{" +
                "value='" + value + '\'' +
                ", gettext=" + gettext +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WMLAttribute that)) return false;
        return Objects.equals(value, that.value) && gettext.equals(that.gettext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, gettext);
    }
}
