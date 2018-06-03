package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import Database.DatabaseHelper;

public class User implements Serializable{//Parcelable{
    private String username;
    private String email; //<<stretch>>
    private Sprite sprite;
//    private Dungeon[] dungeons;
    private ArrayList<Dungeon> dungeons;
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };
//
//    public User(Parcel in){
//        this.username = in.readString();
//        this.email = in.readString();
//        this.sprite =  in.readParcelable(Sprite.class.getClassLoader());
//        Object[] ar = in.readArray(Dungeon.class.getClassLoader());
//        this.dungeons = Arrays.copyOf(ar,ar.length,Dungeon[].class);
//        //TODO This might break!
//    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.dungeons = new ArrayList<Dungeon>();
        this.sprite = new Sprite(username);
    }

    public User(String username, String email, Sprite sprite, ArrayList<Dungeon> dungeons) {
        this.username = username;
        this.email = email;
        this.sprite = sprite;
        this.dungeons = dungeons;
    }

    public void addDungeon(String title, Date endDate, Difficulty d){
        Dungeon temp = new Dungeon(title, sprite.getMaxHealth(), endDate, d);
//        Dungeon[] oldArr = getDungeons();
////        Dungeon[] newArr = new Dungeon[oldArr.length + 1];
////        for(int i = 0; i < oldArr.length; i++){
////            newArr[i] = oldArr[i];
////        }
////        newArr[oldArr.length] = temp;
        ArrayList<Dungeon> arr = getDungeons();
        arr.add(temp);
        setDungeons(arr);
    }

    public void removeDungeon(Dungeon dungeon){
//        Dungeon[] oldArr = getDungeons();
////        Dungeon[] newArr = new Dungeon[oldArr.length - 1];
////        int j = 0;
////        for(int i = 0; i < oldArr.length; i++){
////            if(oldArr[i] == dungeon){
////                j--;
////            }
////            else {
////                newArr[i] = oldArr[i];
////            }
////            j++;
////        }
        ArrayList<Dungeon> arr = getDungeons();
        arr.remove(dungeon);
        setDungeons(arr);
    }

    public Dungeon getDungeonByTitle(String title){
        Dungeon output = null;
        ArrayList<Dungeon> d = getDungeons();
        for(int i = 0; i < d.size(); i++){
            String s = d.get(i).getTitle();
            if (s.equalsIgnoreCase(title)){
                output = d.get(i);
                break;
            }
        }
        return output;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(getUsername());
//        dest.writeString(getEmail());
//        getSprite().writeToParcel(dest,0);
//        dest.writeArray(getDungeons());
//        //TODO This might break!
//    }

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

    public ArrayList<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(ArrayList<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }
}
