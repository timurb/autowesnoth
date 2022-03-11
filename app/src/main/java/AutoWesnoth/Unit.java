package AutoWesnoth;

import java.nio.file.Path;
import java.util.Objects;

public class Unit {
    public String id;
    public String name;
    public String race;
    public String gender;
    public String alignment;
    public Path image;
    public String description;
    public String usage;   // AI uses as this type
    public Integer hitpoints;
    public Integer movement;
    public Integer experience;
    public Integer level;
    public Integer cost;
    public String advances_to;
    public String movement_type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit unit)) return false;
        return Objects.equals(id, unit.id) && Objects.equals(name, unit.name) && Objects.equals(race, unit.race) && Objects.equals(gender, unit.gender) && Objects.equals(alignment, unit.alignment) && Objects.equals(image, unit.image) && Objects.equals(hitpoints, unit.hitpoints) && Objects.equals(movement, unit.movement) && Objects.equals(advances_to, unit.advances_to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, race, gender, alignment, image, hitpoints, movement, advances_to);
    }
}
