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
        var unit_tag = findTag(data, "unit_type");
        if (unit_tag == null) return null;

        var unit = new Unit(unit_tag.attributes.get("id").value);
        unit.name = unit_tag.attributes.get("name").value;
        unit.race = unit_tag.attributes.get("race").value;
        unit.gender = unit_tag.attributes.get("gender").value;
        unit.alignment = unit_tag.attributes.get("alignment").value;
        unit.image = Paths.get(unit_tag.attributes.get("image").value);
        unit.description = unit_tag.attributes.get("description").value;
        unit.usage = unit_tag.attributes.get("usage").value;
        unit.hitpoints = Integer.valueOf(unit_tag.attributes.get("hitpoints").value);
        unit.movement = Integer.valueOf(unit_tag.attributes.get("movement").value);
        unit.experience = Integer.valueOf(unit_tag.attributes.get("experience").value);
        unit.level = Integer.valueOf(unit_tag.attributes.get("level").value);
        unit.cost = Integer.valueOf(unit_tag.attributes.get("cost").value);
        unit.advances_to = unit_tag.attributes.get("advances_to").value;
        unit.movement_type = unit_tag.attributes.get("movement_type").value;

        return unit;
    }


}
