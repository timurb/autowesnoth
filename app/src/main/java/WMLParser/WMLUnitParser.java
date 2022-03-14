package WMLParser;

import AutoWesnoth.Attack;
import AutoWesnoth.Unit;

import java.nio.file.Paths;
import java.util.ArrayList;

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

    private static ArrayList<Attack> findAttacks(WMLTag unit) {
        ArrayList<Attack> attacks = new ArrayList<>();

        for (var tag : unit.tags) {
            if (tag.name.equals("attack")) {
                var attack = new Attack(tag.attributes);
                attacks.add(attack);
            }
        }

        return attacks;
    }

    Unit parse() {
        var unit_tag = findTag(data, "unit_type");
        if (unit_tag == null) return null;

        var unit = new Unit(unit_tag.attributes.get("id").value);
        unit.setName(unit_tag.attributes.get("name").value);
        unit.setRace(unit_tag.attributes.get("race").value);
        unit.setGender(unit_tag.attributes.get("gender").value);
        unit.setAlignment(unit_tag.attributes.get("alignment").value);
        unit.setImage(Paths.get(unit_tag.attributes.get("image").value));
        unit.setDescription(unit_tag.attributes.get("description").value);
        unit.setUsage(unit_tag.attributes.get("usage").value);
        unit.setHitpoints(Integer.valueOf(unit_tag.attributes.get("hitpoints").value));
        unit.setMovement(Integer.valueOf(unit_tag.attributes.get("movement").value));
        unit.setExperience(Integer.valueOf(unit_tag.attributes.get("experience").value));
        unit.setLevel(Integer.valueOf(unit_tag.attributes.get("level").value));
        unit.setCost(Integer.valueOf(unit_tag.attributes.get("cost").value));
        unit.setAdvances_to(unit_tag.attributes.get("advances_to").value);
        unit.setMovement_type(unit_tag.attributes.get("movement_type").value);

        unit.attacks.addAll(findAttacks(unit_tag));

        return unit;
    }


}
