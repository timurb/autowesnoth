package WMLParser

import AutoWesnoth.Unit
import spock.lang.Specification

class WMLUnitParserTest extends Specification {
    def "parser builds units"() {
        setup:
        def stream = WMLReaderTest.class.getClassLoader().getResourceAsStream("Archer.cfg")
        def parser = new WMLReader(stream.readLines().stream())
        WMLTag raw_data = parser.parse()
        def attributes = Map.ofEntries(
                Map.entry("a", "b"),
                Map.entry("id", "Elvish Archer"),
                Map.entry("name", "Elvish Archer"),
                Map.entry("race", "elf"),
                Map.entry("gender", "male,female"),
                Map.entry("alignment", "neutral"),
                Map.entry("image", "units/elves-wood/archer.png"),
                Map.entry("hitpoints", 29),
                Map.entry("movement", 6),
                Map.entry("advances_to", "Elvish Ranger,Elvish Marksman"),
                Map.entry("usage", "archer"),
                Map.entry("description", "As primarily foragers and hunters, most elves learn to become proficient archers from a young age. Besides being only a practical skill, archery is also a common pastime and many competitions are held in sport for the entertainment of spectators and participants alike. This ability is readily turned to battle in times of war, where many elves will wield bows as their weapons of choice. Though not as sturdy as their human or orc counterparts, Elvish archers are still effective combatants, especially when fighting from the safety of their forests."),
                Map.entry("experience", 44),
                Map.entry("level", 1),
                Map.entry("cost", 17),
                Map.entry("movement_type", "woodland")
        )

        when:
        def unit = new WMLUnitParser(raw_data).parse()
        def target = new Unit("Elvish Archer", attributes)

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
