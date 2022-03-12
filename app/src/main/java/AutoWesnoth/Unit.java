package AutoWesnoth;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class Unit {
    final public String id;
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

    public Unit(String id) {
        this.id = id;
    }

    public Unit(String id, Map<String, Object> attributes) {
        this(id);
        this.updateAttributes(attributes);
    }

    public void updateAttributes(Map<String, Object> attributes) {
        setName((String) attributes.get("name"));
        setRace((String) attributes.get("race"));
        setGender((String) attributes.get("gender"));
        setAlignment((String) attributes.get("alignment"));
        setImage((String) attributes.get("image"));
        setDescription((String) attributes.get("description"));
        setUsage((String) attributes.get("usage"));
        setHitpoints(attributes.get("hitpoints"));
        setMovement(attributes.get("movement"));
        setExperience(attributes.get("experience"));
        setLevel(attributes.get("level"));
        setCost(attributes.get("cost"));
        setAdvances_to((String) attributes.get("advances_to"));
        setMovement_type((String) attributes.get("movement_type"));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public void setImage(Path image) {
        this.image = image;
    }

    public void setImage(String image) {
        setImage(Paths.get(image));
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void setHitpoints(Object hitpoints) {
        setHitpoints((Integer) hitpoints);
    }

    public void setMovement(Integer movement) {
        this.movement = movement;
    }

    public void setMovement(Object movement) {
        setMovement((Integer) movement);
    }

    public void setMovement_type(String movement_type) {
        this.movement_type = movement_type;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setExperience(Object experience) {
        setExperience((Integer) experience);
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setLevel(Object level) {
        setLevel((Integer) level);
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setCost(Object cost) {
        setCost((Integer) cost);
    }

    public void setAdvances_to(String advances_to) {
        this.advances_to = advances_to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit unit)) return false;
        return id.equals(unit.id) && Objects.equals(name, unit.name) && Objects.equals(race, unit.race) && Objects.equals(gender, unit.gender) && Objects.equals(alignment, unit.alignment) && Objects.equals(image, unit.image) && Objects.equals(description, unit.description) && Objects.equals(usage, unit.usage) && Objects.equals(hitpoints, unit.hitpoints) && Objects.equals(movement, unit.movement) && Objects.equals(experience, unit.experience) && Objects.equals(level, unit.level) && Objects.equals(cost, unit.cost) && Objects.equals(advances_to, unit.advances_to) && Objects.equals(movement_type, unit.movement_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, race, gender, alignment, image, description, usage, hitpoints, movement, experience, level, cost, advances_to, movement_type);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", gender='" + gender + '\'' +
                ", alignment='" + alignment + '\'' +
                ", image=" + image +
                ", description='" + description + '\'' +
                ", usage='" + usage + '\'' +
                ", hitpoints=" + hitpoints +
                ", movement=" + movement +
                ", experience=" + experience +
                ", level=" + level +
                ", cost=" + cost +
                ", advances_to='" + advances_to + '\'' +
                ", movement_type='" + movement_type + '\'' +
                '}';
    }
}
