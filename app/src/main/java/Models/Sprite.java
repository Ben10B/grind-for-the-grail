package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sprite implements Parcelable{

    private String name;//<<stretch>>

    private int maxHealth;
    private int exp;
    private int level;
    private int gold;//<<stretch>>
    private List<SpriteObject> items;//<<stretch>>
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Sprite createFromParcel(Parcel in) {
            return new Sprite(in);
        }

        public Sprite[] newArray(int size) {
            return new Sprite[size];
        }
    };
    public Sprite(Parcel in){
        this.name = in.readString();
        this.maxHealth = in.readInt();
        this.exp = in.readInt();
        this.level = in.readInt();
        this.gold = in.readInt();
        this.items = in.readArrayList(SpriteObject.class.getClassLoader());
        Object[] arr = in.readArray(SpriteObject.class.getClassLoader());
        SpriteObject[] soArr = Arrays.copyOf(arr,arr.length,SpriteObject[].class);
        this.items = Arrays.asList(soArr);
        //TODO This might break......
    }

    public Sprite(String name) {
        this.name = name;
        this.maxHealth = 2;
        this.exp = 0;
        this.level = 1;
        this.gold = 0;
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

    public Sprite(String name, int maxHealth, int exp, int level, int gold, List<SpriteObject> items) {
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
    }

    public void addExp(int amt) {
        setExp(getExp() + amt);
        int nextLvl = ((getLevel() * 2) + 3);
        if (getExp() >= nextLvl) {
            levelUp((nextLvl - getExp()));
        }
    }

    public void addGold(int amt) {
        setGold(getGold() + amt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeInt(getMaxHealth());
        dest.writeInt(getExp());
        dest.writeInt(getLevel());
        dest.writeInt(getGold());
        dest.writeArray(getItems().toArray());
        //TODO This might break......
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

    public List<SpriteObject> getItems() {
        return items;
    }

    public void setItems(List<SpriteObject> items) {
        this.items = items;
    }
}
