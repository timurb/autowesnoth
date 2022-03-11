package WMLParser;

import com.google.common.collect.Maps;
import com.google.common.collect.Streams;

import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WMLReader {
    final static Pattern comment = Pattern.compile("^#.*");
    final static Pattern macro = Pattern.compile("^\\{.*}$");
    final static Pattern tag_open = Pattern.compile("^\\[([a-zA-Z0-9_]+)]");
    final static Pattern tag_append = Pattern.compile("^\\[\\+([a-zA-Z0-9_]+)]");
    final static Pattern tag_close = Pattern.compile("^\\[/([a-zA-Z0-9_]+)]");
    final static Pattern assignment = Pattern.compile("^([a-zA-Z0-9_, ]+)=(.*)");
    private final Stream<String> stream;

    WMLReader(Stream<String> stream) {
        this.stream = stream;
    }

    private static WMLTag matchTag(String elem, Pattern pattern) {
        var match = pattern.matcher(elem);
        if (match.lookingAt()) {
            var name = match.group(1);
            return new WMLTag(name);
        }
        return null;
    }

    private static Map<String, String> matchAttribute(String line) {
        var match = assignment.matcher(line);

        if (match.lookingAt()) {
            var keys = match.group(1).split(",");
            var values = match.group(2).split(",");

            if (keys.length > 1)
                return Streams.zip(Stream.of(keys), Stream.of(values), Maps::immutableEntry)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            else {
                return Map.of(match.group(1), match.group(2));
            }
        }
        return null;
    }

    WMLTag parse() throws Exception {
        WMLTag tagTree = new WMLTag("_default");
        LinkedList<WMLTag> heads = new LinkedList<>();
        heads.push(tagTree);

        for (var elem : stream.toList()) {
            elem = elem.strip();
            elem = elem.replaceAll(comment.pattern(), ""); // comment
            elem = elem.replaceAll(macro.pattern(), "");   // macros
            if (elem.isEmpty()) continue;

            var head = heads.peek();

            var newTag = matchTag(elem, tag_open);
            if (newTag != null) {
                head.values.add(newTag);
                heads.push(newTag);
                continue;
            }

            var closeTag = matchTag(elem, tag_close);
            if (closeTag != null) {
                var popped = heads.pop();
                continue;
            }

            var attribute = matchAttribute(elem);
            if (attribute != null) {
                head.values.add(attribute);
                continue;
            }

            throw new Exception("Unsupported line to parse " + elem);
        }

        return tagTree;
    }
}
