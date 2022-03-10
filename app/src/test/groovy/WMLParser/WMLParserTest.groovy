package WMLParser

import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.stream.Collectors;

class WMLParserTest extends Specification {
    def "parser parses WML file"() {
        setup:
        def stream = WMLParserTest.class.getClassLoader().getResourceAsStream("Archer.cfg");
        def parser = new WMLParser(stream.readLines().stream());

        when:
        def result = parser.parse();
//        print(result.collect(Collectors.joining("\n")));

        then:
        result['_block_type'] == "unit_type"
        result["id"] == "Elvish Archer"
    }
}
