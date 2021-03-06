package model.characters.hero;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import model.characters.Characters;
import model.equipment.armor.*;
import model.equipment.helmet.*;
import model.equipment.weapon.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;


/**
 * Created by msemenov on 11/14/18.
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Entity
public abstract class Hero extends Characters implements UpdateConditions, GetLoot, GetEquip {


    @Transient
    UnaryOperator<Integer> square = i -> i * i;
    @Transient
    UnaryOperator<Integer> lvlCheck = i-> i * 1000 + (square.apply(i - 1)) * 450;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;
        @Column(name = "name")
        String nameH;
        @Column(name = "damage")
        int heroDamage;
        @Column(name = "defense")
        int heroDefense;
        @Column(name = "hp")
        int heroHp;
        @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
        Weapon weapon;
        @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
        Armor armor;
        @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
        Helmet helmet;
        int level;
        @Enumerated(EnumType.STRING)
        @Column(length = 16)
        HeroClass heroType;
        int exp;
        @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
        Coordinates coordinates;

    @Override
    public String toString() {
        return "Hero name :"  + this.getName() + "\n" +
                "Hero class : " + this.heroType + "\n" +
                "Hero stats:\n" + "Damage: " + this.getDamage() + "\n" +
                "Defense: " + this.getDefense() + "\n" +
                "Max Health Point: " + this.getHpMax() + "\n" +
                "Current health point: " + this.getHpCur() + "\n" +
                "Exp : " + this.exp + "\n" +
                "Hero level: " + this.level + "\n" +
                "Weapon : " + this.weapon + "\n" +
                "Armor : " + this.armor + "\n" +
                "Helmet : " + this.helmet;
    }
    public void updateConditions(){
        this.setExp(this.getExp() + (600 * this.level / 2));
        if(this.getExp() >= lvlCheck.apply(level)){
            this.setLevel(++level);
            setHeroDamage();
            setHeroDefense();
            setHeroHp();
        }
    }


    public Hero() { }

    public void getEquip(){
        this.weapon = this.getMyWeapon();
        this.armor = this.getMyArmor();
        this.helmet = this.getMyHelmet();
        this.setHeroHp();
        this.setHeroDamage();
        this.setHeroDefense();
    }
        public void setHeroDamage(){
            this.setHeroDamage(10 + (5 * this.level));
            this.setDamage(this.getHeroDamage() + this.weapon.getDmg());
        }

        public void setHeroDefense(){
            this.setHeroDefense(2 + (2 * this.level));
            this.setDefense(heroDefense + armor.getDefense());
        }

        public void setHeroHp(){
            this.setHeroHp(50 + (10 * this.level));
            this.setHpMax(heroHp + this.helmet.getHp());
            this.setHpCur(heroHp + this.helmet.getHp());
        }

        public void setWeapon(Weapon weapon){
                    this.weapon = weapon;
                    this.setDamage(this.getHeroDamage() + this.weapon.getDmg());
        }

        public void setArmor(Armor armor){
            this.armor = armor;
            this.setDefense(this.getHeroDefense() + this.armor.getDefense());
        }

        public Hero setHeroType(HeroClass heroType){
        this.heroType = heroType;
        return this;
        }

        public void setHelmet(Helmet helmet){
            this.helmet = helmet;
            this.setHpMax(this.heroHp + this.helmet.getHp());
        }
        public Hero setNameH(String heroname){
        this.nameH = heroname;
        this.setName(heroname);
        return this;
        }

        @Data
        @Accessors(chain = true, fluent = true)
        public static class HeroBuilder {

            String heroName;
            int level;
            int exp;
            Coordinates coordinates;
            private static final Map<Enum<HeroClass>, Class<? extends Hero>> type = new HashMap<>();

            static {
                type.put(HeroClass.SPELLHOWLER, SpellHowler.class);
                type.put(HeroClass.TREASUREHUNTER, TreasureHunter.class);
                type.put(HeroClass.DARKKNIGHT, DarkKnight.class);
            }

            public Hero build(HeroClass heroType) {

                try {
                    return type.get(heroType).newInstance()
                            .setNameH(heroName)
                            .setLevel(level)
                            .setExp(exp)
                            .setHeroType(heroType)
                            .setCoordinates(coordinates);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

    }
}
