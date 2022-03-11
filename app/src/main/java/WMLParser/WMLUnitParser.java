package WMLParser;

import AutoWesnoth.Unit;

import java.nio.file.Paths;

public class WMLUnitParser {
    private final WMLTag data;

    WMLUnitParser(WMLParser.WMLTag raw_data) {
        data = raw_data;
    }

    private static WMLTag findTag(WMLTag tag, String tag_name) {
        if (tag.name.equals(tag_name)) return tag;

        for (var iter : tag.tags) {
            var found = findTag(iter, tag_name);
            if (found != null) return found;
        }
        return null;
    }

    Unit parse() {
        var unit = new Unit();
        var unit_tag = findTag(data, "unit_type");
        if (unit_tag == null) return null;

        unit.id = unit_tag.attributes.get("id");
        unit.name = unit_tag.attributes.get("name");
        unit.race = unit_tag.attributes.get("race");
        unit.gender = unit_tag.attributes.get("gender");
        unit.alignment = unit_tag.attributes.get("alignment");
        unit.image = Paths.get(unit_tag.attributes.get("image"));
        unit.description = unit_tag.attributes.get("description");
        unit.usage = unit_tag.attributes.get("usage");
        unit.hitpoints = Integer.valueOf(unit_tag.attributes.get("hitpoints"));
        unit.movement = Integer.valueOf(unit_tag.attributes.get("movement"));
        unit.experience = Integer.valueOf(unit_tag.attributes.get("experience"));
        unit.level = Integer.valueOf(unit_tag.attributes.get("level"));
        unit.cost = Integer.valueOf(unit_tag.attributes.get("cost"));
        unit.advances_to = unit_tag.attributes.get("advances_to");
        unit.movement_type = unit_tag.attributes.get("movement_type");

        return unit;
    }


}
