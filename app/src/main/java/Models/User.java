package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable{
    private String username;
    private String email; //<<stretch>>
    private Sprite sprite;
    private ArrayList<Dungeon> dungeons;

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
