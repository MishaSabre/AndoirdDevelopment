package com.etity.sablin;

import java.util.ArrayList;

/**
 * За основу была взята модель из любой RPG игры, где есть классы, за которые можно играть
 *
 * Entity - абстрактный класс сущности
 * Warrior - класс воина
 * Archer - класс лучника
 * Wizard - класс мага
 *
 * @author MishaSabre (Sablin Mikhail)
 */

//Класс сущности
abstract class Entity implements Comparable<Entity>{
    abstract int getHealth();

    public int compareTo(Entity entity){
        if (entity == null) return 1;

        if(this.getHealth() > entity.getHealth()) return 1;
        else if(this.getHealth() < entity.getHealth()) return -1;
        else return 0;

    }
}

class Warrior extends Entity{

    int armor;
    int health;
    String weapon;

    Warrior(int armor, int health){
        this.armor = armor;
        this.health = health;
        this.weapon = "Меч";
    }

    public String getWeapon() {
        return weapon;
    }

    @Override
    int getHealth() {
        return this.armor + this.health;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Warrior warrior = (Warrior) o;

        return this.getWeapon().equals(warrior.getWeapon()) && this.armor == warrior.armor
                && this.health == warrior.health;

    }

    @Override
    public String toString(){
        return "Это воин, его оружие - " + this.weapon + ", его здоровье " + this.health +", его броня " + this.armor;
    }
}

class Archer extends Entity{

    int health;
    String weapon;

    Archer(int health){
        this.health = health;
        this.weapon = "Лук";
    }

    public String getWeapon() {
        return weapon;
    }

    @Override
    int getHealth() {
        return this.health;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Archer archer = (Archer) o;

        return this.getWeapon().equals(archer.getWeapon()) && this.health == archer.health;

    }

    @Override
    public String toString(){
        return "Это лучник, его оружие - " + this.weapon + ", его здоровье " + this.health;
    }
}

class Wizard extends Entity{

    int health;
    int magicShield;
    String weapon;

    Wizard(int health, int magicShield){
        this.health = health;
        this.magicShield = magicShield;
        this.weapon = "Посох";
    }

    public String getWeapon() {
        return weapon;
    }

    @Override
    int getHealth() {
        return this.health + this.magicShield;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wizard wizard = (Wizard) o;

        return this.getWeapon().equals(wizard.getWeapon()) && this.magicShield == wizard.magicShield
                && this.health == wizard.health;

    }

    @Override
    public String toString(){
        return "Это маг, его оружие - " + this.weapon + ", его здоровье " + this.health + ", запас магического щита "
                + this.magicShield;
    }
}

public class Main {

    public static void main(String[] args) {
        /*Решил продемонстировать сравнения и сортировку только для воина, так как остальные классы сделаны очень похоже
          и результат будет везде примерно одинаков
         */


        Warrior warrior1 = new Warrior(50, 30);
        Warrior warrior2 = new Warrior(100, 30);
        Warrior warrior3 = warrior1;

        //Сравниваем воинов, воин1 и воин2 это разные воины, а воин1 и воин3 один и тот же воин
        System.out.println("warrior1 == warrior2 - " + warrior1.equals(warrior2) + ", warrior1 == warrior3 - "
                + warrior1.equals(warrior3) + "\n");

        //Показываю сортировку
        ArrayList<Warrior> warriors = new ArrayList<>();
        warriors.add(warrior2);
        warriors.add(warrior1);
        warriors.add(warrior3);

        for (Warrior warrior : warriors) System.out.println(warrior);
        System.out.println("\n");

        warriors.sort(Warrior::compareTo);

        for (Warrior warrior : warriors) System.out.println(warrior);

    }
}
