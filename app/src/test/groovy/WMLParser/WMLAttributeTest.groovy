package WMLParser

import spock.lang.Specification

class WMLAttributeTest extends Specification {
    def "attribute has value"() {
        setup:
        def attr = new WMLAttribute("attr")

        when:
        attr.parse()

        then:
        attr.value == "attr"
    }

    def "attribute unquotes when needed"() {
        setup:
        def attr = new WMLAttribute("\"attr\"")

        when:
        attr.parse()

        then:
        attr.value == "attr"
    }

    def "attribute unquotes double quotes"() {
        setup:
        def attr = new WMLAttribute("abc \"\"attr\"\" def")

        when:
        attr.parse()

        then:
        attr.value == "abc \"attr\" def"
    }

    def "attribute supports gettext"() {
        setup:
        def attr1 = new WMLAttribute("attr1")
        def attr2 = new WMLAttribute("_ \"attr2\"")

        when:
        attr1.parse()
        attr2.parse()

        then:
        attr1.value == "attr1"
        attr1.gettext == false

        attr2.value == "attr2"
        attr2.gettext == true
    }
}
