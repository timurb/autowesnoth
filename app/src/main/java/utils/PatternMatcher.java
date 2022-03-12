package utils;

import java.util.regex.Pattern;

public class PatternMatcher {
    public static String match(String string, Pattern pattern) {
        var match = pattern.matcher(string);
        if (match.lookingAt()) return match.group(1);

        return null;
    }

}
