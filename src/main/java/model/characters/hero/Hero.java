package model.characters.hero;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import model.characters.Characters;
import model.equipment.Armor.*;
import model.equipment.helmet.*;
import model.equipment.weapon.*;

import java.util.function.UnaryOperator;


/**
 * Created by msemenov on 11/14/18.
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Hero extends Characters implements UpdateConditions, GetLoot {

    UnaryOperator<Integer> square = i -> i * i;
    UnaryOperator<Integer> lvlCheck = i-> i * 1000 + (square.apply(i - 1)) * 450;
        String nameH;
        int heroDamage;
        int heroDefense;
        int heroHp;
        Weapon weapon;
        Armor armor;
        Helmet helmet;
        int level;
        int exp;
        Coordinates coordinates;

    @Override
    public String toString() {
        return "Hero{" +
                "heroName='" + nameH + '\'' +
                ", weapon=" + weapon +
                ", armor=" + armor +
                ", helmet=" + helmet +
                ", level=" + level +
                ", exp=" + exp +
                ", coordinates=" + coordinates +
                super.toString() +
                '}';
    }
    public void updateConditions(){
        this.setExp(this.getExp() + (600 * this.level / 2));
        if(this.getExp() >= lvlCheck.apply(level)){
            this.setLevel(level++);
        }
    }

    public Hero(Weapon weapon, Armor armor, Helmet helmet) {
            //this.setHeroHp(50 + (10 * this.level));
            this.weapon = weapon;
            this.armor = armor;
            this.helmet = helmet;
            this.setHeroHp();
            this.setHeroDamage();
            this.setHpCur(this.getHeroHp() + (10 * this.level) + helmet.getHp());
            this.setHpMax(this.getHpCur());
            this.setDefense(this.getHeroDefense() + (2 * this.level) + armor.getDefense());
        }
        public void setHeroDamage(){
            this.setHeroDamage(10 + (5 * this.level));
            this.setDamage(this.getHeroDamage() + this.weapon.getDmg());
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

        public void setHelmet(Helmet helmet){
            this.helmet = helmet;
            this.setHpCur(this.getHpCur());
        }
        public Hero setNameH(String heroname){
        this.nameH = heroname;
        this.setName(heroname);
        return this;
        }

        @Data
        @Accessors(chain = true, fluent = true)
        public static class HeroBuilder {
            Class<? extends Hero> heroType;
            String heroName;
            int level;
            int exp;
            Coordinates coordinates;

            public Hero build(Class <? extends Hero> heroType) {
                this.heroType = heroType;
                try {
                    return heroType.newInstance()
                            .setNameH(heroName)
                            .setLevel(level)
                            .setExp(exp)
                            .setCoordinates(coordinates);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

    }
}
