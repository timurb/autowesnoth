package WMLParser

import spock.lang.Specification

import java.util.stream.Stream

class WMLParserTest extends Specification {
    def "parser parses tags"() {
        when:
        def result = WMLParser.matchTag("[foo]", WMLParser.tag_open)

        then:
        result == new WMLTag("foo")
    }

    def "parser parses single assignments"() {
        when:
        def result = WMLParser.matchAttribute("a=b")
        def target = HashMap.of("a", "b")

        then:
        result == target
    }

    def "parser parses multiple assignments"() {
        when:
        def result = WMLParser.matchAttribute("a,b=1,2")
        def target = HashMap.of("a", "1", "b", "2")

        then:
        result == target
    }

    def "parser throws exception on ill formatted lines"() {
        setup:
        def stream = Stream.of("a1")
        def parser = new WMLParser(stream)

        when:
        parser.parse()

        then:
        thrown(Exception)
    }

    def "parser parses WML file"() {
        setup:
        def stream = WMLParserTest.class.getClassLoader().getResourceAsStream("Archer.cfg")
        def parser = new WMLParser(stream.readLines().stream())

        when:
        def result = parser.parse()
        print(result.inspect())

        then:
        result.name == "_default"
        result.values[0].name == "unit_type"
        result.values[0].values[0] == Map.of("id", "Elvish Archer")
    }
}
