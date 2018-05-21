package Models;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
//    private String email;
    private Sprite sprite;
    private ArrayList<Dungeon> dungeons;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void AddDungeon(String title){
        Dungeon temp = new Dungeon(title, sprite.getMaxHealth()); //TODO check health
        ArrayList<Dungeon> dungeonTemp = getDungeons();
        dungeonTemp.add(temp);
        setDungeons(dungeonTemp);
    }

//    public void RemoveDungeon(Dungeon dungeon){
//
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

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
