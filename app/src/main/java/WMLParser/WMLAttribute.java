package WMLParser;

import java.util.regex.Pattern;

public class WMLAttribute {
    static Pattern matchGettext = Pattern.compile("^_ \"(.*)\"$");
    String value;
    Boolean gettext = false;
    String text;

    WMLAttribute(String text) {
        this.text = text;
    }

    private static String isGettext(String string) {
        var match = matchGettext.matcher(string);
        if (match.lookingAt()) return match.group(1);

        return null;
    }

    private static String unquote(String string) {
        return string;
    }

    WMLAttribute parse() {
        var parsed = text.strip();

        if (isGettext(parsed) != null) {
            parsed = isGettext(parsed);
            gettext = true;
        }

        value = parsed;
        return this;
    }

    public String value() {
        return value;
    }

    public Boolean gettext() {
        return gettext;
    }
}
