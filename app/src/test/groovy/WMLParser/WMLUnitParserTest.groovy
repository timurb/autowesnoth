package WMLParser

import AutoWesnoth.Unit
import spock.lang.Specification

import java.nio.file.Paths

class WMLUnitParserTest extends Specification {
    def "parser builds units"() {
        setup:
        def stream = WMLReaderTest.class.getClassLoader().getResourceAsStream("Archer.cfg")
        def parser = new WMLReader(stream.readLines().stream())
        WMLTag raw_data = parser.parse()

        when:
        def unit = new WMLUnitParser(raw_data).parse()
        def target = new Unit()
        target.id = "Elvish Archer"
        target.name = "Elvish Archer"
        target.race = "elf"
        target.gender = "male,female"
        target.alignment = "neutral"
        target.image = Paths.get("units/elves-wood/archer.png")
        target.hitpoints = 29
        target.movement = 6
        target.advances_to = "Elvish Ranger,Elvish Marksman"

        then:
        unit == target
    }

    def "parser finds unit defs in AST"() {
        setup:
        def unit = new WMLTag("unit_type")
        def ast = new WMLTag("root")
        def leaf1 = new WMLTag("leaf1")
        def leaf2 = new WMLTag("leaf2")
        def leaf3 = new WMLTag("leaf3")
        leaf2.tags.add(unit)
        ast.tags.add(leaf1)
        ast.tags.add(leaf2)
        ast.tags.add(leaf3)

        when:
        def found1 = WMLUnitParser.findTag(unit, "unit_type")
        def found2 = WMLUnitParser.findTag(ast, "unit_type")
        def found3 = WMLUnitParser.findTag(ast, "leaf3")
        def found4 = WMLUnitParser.findTag(ast, "leaf" + "3")

        then:
        found1 == unit
        found2 == unit
        found3 == leaf3
        found4 == leaf3
    }
}
