package utils


import spock.lang.Specification

import java.util.regex.Pattern

class PatternMatcherTest extends Specification {
    def "patternMatcher parses tags"() {
        setup:
        def tag_open = Pattern.compile("^\\[([a-zA-Z0-9_]+)]")

        when:
        def result = PatternMatcher.match("[foo]", tag_open)

        then:
        result == "foo"
    }
}
