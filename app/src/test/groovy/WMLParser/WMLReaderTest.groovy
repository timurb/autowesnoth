package WMLParser

import spock.lang.Specification

import java.util.stream.Stream

class WMLReaderTest extends Specification {
    def "reader parses tags"() {
        when:
        def result = WMLReader.matchTag("[foo]", WMLReader.tag_open)

        then:
        result == new WMLTag("foo")
    }

    def "reader parses single assignments"() {
        when:
        def result = WMLReader.matchAttribute("a=b")
        def target = HashMap.of("a", "b")

        then:
        result == target
    }

    def "reader parses multiple assignments"() {
        when:
        def result = WMLReader.matchAttribute("a,b=1,2")
        def target = HashMap.of("a", "1", "b", "2")

        then:
        result == target
    }

    def "reader parses commas in right hand side correctly"() {
        when:
        def result = WMLReader.matchAttribute("advances_to=Elvish Ranger,Elvish Marksman")
        def target = HashMap.of("advances_to", "Elvish Ranger,Elvish Marksman")

        then:
        result == target
    }

    def "reader throws exception on ill formatted lines"() {
        setup:
        def stream = Stream.of("a1")
        def reader = new WMLReader(stream)

        when:
        reader.parse()

        then:
        thrown(Exception)
    }

    def "reader parses WML file"() {
        setup:
        def stream = WMLReaderTest.class.getClassLoader().getResourceAsStream("Archer.cfg")
        def reader = new WMLReader(stream.readLines().stream())

        when:
        def result = reader.parse()
        print(result.inspect())

        then:
        result.name == "_default"
        result.values[0].name == "unit_type"
        result.values[0].values[0] == Map.of("id", "Elvish Archer")
    }
}
