package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import Database.DatabaseHelper;

public class User implements Parcelable{
    public User(String username, String email, Sprite sprite, Dungeon[] dungeons) {
        this.username = username;
        this.email = email;
        this.sprite = sprite;
        this.dungeons = dungeons;
    }

    private String username;
    private String email; //<<stretch>>
    private Sprite sprite;
    private Dungeon[] dungeons;
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(String username, String password) {
        this.username = username;
        this.dungeons = new Dungeon[5];
        this.sprite = new Sprite(username);
    }

    public User(Parcel in){
        this.username = in.readString();
        this.email = in.readString();
        this.sprite =  in.readParcelable(Sprite.class.getClassLoader());
        Object[] ar = in.readArray(Dungeon.class.getClassLoader());
        this.dungeons = Arrays.copyOf(ar,ar.length,Dungeon[].class);
        //TODO This might break!
    }

    public void addDungeon(String title, Date endDate){
        Dungeon temp = new Dungeon(title, sprite.getMaxHealth(), endDate);
        Dungeon[] oldArr = getDungeons();
        Dungeon[] newArr = new Dungeon[oldArr.length + 1];
        for(int i = 0; i < oldArr.length; i++){
            newArr[i] = oldArr[i];
        }
        newArr[oldArr.length] = temp;
        setDungeons(newArr);
    }

    public void removeDungeon(Dungeon dungeon){
        Dungeon[] oldArr = getDungeons();
        Dungeon[] newArr = new Dungeon[oldArr.length - 1];
        int j = 0;
        for(int i = 0; i < oldArr.length; i++){
            if(oldArr[i] == dungeon){
                j--;
            }
            else {
                newArr[i] = oldArr[i];
            }
            j++;
        }
        setDungeons(newArr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getUsername());
        dest.writeString(getEmail());
        getSprite().writeToParcel(dest,0);
        dest.writeArray(getDungeons());
        //TODO This might break!
    }

    //Getters and Setters beyond this point
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Dungeon[] getDungeons() {
        return dungeons;
    }

    public void setDungeons(Dungeon[] dungeons) {
        this.dungeons = dungeons;
    }
}
