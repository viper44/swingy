package model.characters.monsters;

import java.util.Random;

public abstract class MonsterFactory {
    private static String[] MonsterNames = {"Pavuk", "Juk", "Losyha", "Piven", "Gorobec", "Kozel"};
    public static Monster newMonster(int level){
        int hp = (new Random().nextInt(150 + level * 30) + 50);
        int damage = new Random().nextInt(15 + level * 15)+ 10;
        int defense = new Random().nextInt(5 + level * 2) + 1;
        return new Monster(hp, damage, defense,  MonsterNames[new Random().nextInt(10000) % 6]);
    }
}
