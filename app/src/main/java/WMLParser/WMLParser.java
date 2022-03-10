package WMLParser;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WMLParser {
    private Stream<String> stream;

    final Pattern comment = Pattern.compile("^#.*");
    final Pattern macro = Pattern.compile("^\\{.*\\}$");

    final Pattern tag_open = Pattern.compile("^\\[([a-zA-Z0-9_]+)\\]");
    final Pattern tag_append = Pattern.compile("^\\[\\+([a-zA-Z0-9_]+)\\]");
    final Pattern tag_close = Pattern.compile("^\\[/([a-zA-Z0-9_]+)\\]");

//    Pattern assignment = Pattern.compile("^([a-zA-Z0-9_])+=(.*)");

    WMLParser(Stream<String> stream) {
        this.stream = stream;
    }

    Object parse() {    // FIXME result type
        Object result = null;
        var head = new WMLTag("_default");
        var tagTree = head;

        stream.map(String::trim)
                .map(elem -> elem.replaceAll(comment.pattern(),"")) // comment
                .map(elem -> elem.replaceAll(macro.pattern(),""))   // macros
                .filter(s-> !s.isEmpty())
                .forEach(elem ->
                        {
                            System.out.println(elem);
                            var match = tag_open.matcher(elem);
                            if (match.lookingAt()) {
                                var name = match.group(1);
                                var newTag = new WMLTag(name);
                                head = newTag;
                            }
                        }
                    );
        ;
        return result;
    }
}
