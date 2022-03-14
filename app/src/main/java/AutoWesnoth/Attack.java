package AutoWesnoth;

import WMLParser.WMLAttribute;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class Attack {
    public final String name;
    String description;
    String type;
    Object specials;    // dummy
    Path icon;
    String range;
    Integer damage;
    Integer number;
    Object accuracy;
    Object parry;
    Object movement_used;
    Object attack_weight;
    Object defense_weight;

    public Attack(String name) {
        this.name = name;
    }

    public Attack(String name, HashMap<?, ?> attributes) {
        this(name);
        this.updateAttributes((HashMap<String, ?>) attributes);
    }

    public Attack(HashMap<String, ?> attributes) {
        this(((WMLAttribute) attributes.get("name")).getValue(), attributes);
    }

    private static String getAttribute(String name, HashMap<String, ?> attributes) {
        var attr = attributes.get(name);
        if (attr == null) return null;
        if (attr instanceof WMLAttribute)
            return ((WMLAttribute) attr).getValue();
        return (String) attr;
    }

    public void updateAttributes(HashMap<String, ?> attributes) {
        setDescription(getAttribute("description", attributes));
        setType(getAttribute("type", attributes));
        setIcon(getAttribute("icon", attributes));
        setRange(getAttribute("range", attributes));
        setDamage(getAttribute("damage", attributes));
        setNumber(getAttribute("number", attributes));
        setAccuracy(getAttribute("accuracy", attributes));
        setParry(getAttribute("parry", attributes));
        setMovement_used(getAttribute("movement_used", attributes));
        setAttack_weight(getAttribute("attack_weight", attributes));
        setDefense_weight(getAttribute("defense_weight", attributes));
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setIcon(Path icon) {
        this.icon = icon;
    }

    public void setIcon(String icon) {
        setIcon(Paths.get(icon));
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public void setDamage(String damage) {
        setDamage(Integer.valueOf(damage));
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setNumber(String number) {
        setNumber(Integer.valueOf(number));
    }

    //// The rest of actions are probably going to be unused, update them later if needed

    public void setAccuracy(Object accuracy) {
        this.accuracy = accuracy;
    }

    public void setParry(Object parry) {
        this.parry = parry;
    }

    public void setMovement_used(Object movement_used) {
        this.movement_used = movement_used;
    }

    public void setAttack_weight(Object attack_weight) {
        this.attack_weight = attack_weight;
    }

    public void setDefense_weight(Object defense_weight) {
        this.defense_weight = defense_weight;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", specials=" + specials +
                ", icon=" + icon +
                ", range='" + range + '\'' +
                ", damage=" + damage +
                ", number=" + number +
                ", accuracy=" + accuracy +
                ", parry=" + parry +
                ", movement_used=" + movement_used +
                ", attack_weight=" + attack_weight +
                ", defense_weight=" + defense_weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attack attack)) return false;
        return name.equals(attack.name) && Objects.equals(description, attack.description) && Objects.equals(type, attack.type) && Objects.equals(specials, attack.specials) && Objects.equals(icon, attack.icon) && Objects.equals(range, attack.range) && Objects.equals(damage, attack.damage) && Objects.equals(number, attack.number) && Objects.equals(accuracy, attack.accuracy) && Objects.equals(parry, attack.parry) && Objects.equals(movement_used, attack.movement_used) && Objects.equals(attack_weight, attack.attack_weight) && Objects.equals(defense_weight, attack.defense_weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, type, specials, icon, range, damage, number, accuracy, parry, movement_used, attack_weight, defense_weight);
    }
}
