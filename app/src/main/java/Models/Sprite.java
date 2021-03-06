package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Sprite implements Serializable{

    private String name;//<<stretch>>
    private int maxHealth;
    private int exp;
    private int level;
    private int gold;//<<stretch>>
    private ArrayList<SpriteObject> items;

    public Sprite(String name) {
        this.name = name;
        this.maxHealth = 2;
        this.exp = 0;
        this.level = 1;
        this.gold = 0;
//        this.items = new SpriteObject[0];
        this.items = new ArrayList<SpriteObject>();
    }

    public Sprite(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.exp = 0;
        this.level = 1;
        this.gold = 0;
        this.items = new ArrayList<SpriteObject>();
    }

    public Sprite(String name, int maxHealth, int exp, int level) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.exp = exp;
        this.level = level;
        this.gold = 0;
        this.items = new ArrayList<SpriteObject>();
    }

    public Sprite(String name, int maxHealth, int exp, int level, int gold, ArrayList<SpriteObject> items) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.exp = exp;
        this.level = level;
        this.gold = gold;
        this.items = items;
    }

    public void levelUp(int overflow) {
        setMaxHealth(getMaxHealth() + 1);
        setExp(0 + overflow);
        setLevel(getLevel()+1);
    }

    public void addExp(int amt) {
        setExp(getExp() + amt);
        int nextLvl = ((getLevel() * 2) + 3);
        if (getExp() >= nextLvl) {
            levelUp((getExp() - nextLvl));
        }
    }

    public void addGold(int amt) {
        setGold(getGold() + amt);
    }

    //Getters and Setters beyond this point

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<SpriteObject> getItems() {
        return items;
    }

    public void setItems(ArrayList<SpriteObject> items) {
        this.items = items;
    }
}
