package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Models.Difficulty;
import Models.Dungeon;
import Models.DungeonDate;
import Models.Sprite;
import Models.Status;
import Models.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "grail.db";
    public static final int DATABASEVERSION = 1;

    public static final String CREATE_TABLE_USER = "create table " + DatabaseUserContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseUserContract.ContractEntry.NAME + " text primary key, " +
            DatabaseUserContract.ContractEntry.EMAIL + " text);";
    public static final String CREATE_TABLE_SPRITE = "create table " + DatabaseSpriteContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseSpriteContract.ContractEntry.SPRITEID + " number primary key, " +
            DatabaseSpriteContract.ContractEntry.MAXHEALTH + " number, " +
            DatabaseSpriteContract.ContractEntry.EXP + " number, " +
            DatabaseSpriteContract.ContractEntry.LEVEL + " number, " +
            DatabaseSpriteContract.ContractEntry.GOLD + " gold);";
    public static final String CREATE_TABLE_DUNGEON = "create table " + DatabaseDungeonContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseDungeonContract.ContractEntry.DUNGEONID + " number primary key, " +
            DatabaseDungeonContract.ContractEntry.NAME + " text, " +
            DatabaseDungeonContract.ContractEntry.MAXHEALTH + " number, " +
            DatabaseDungeonContract.ContractEntry.HEALTH + " number, " +
            DatabaseDungeonContract.ContractEntry.DIFFICULTY + " text, " +
            DatabaseDungeonContract.ContractEntry.REGULARPENALTY + " text, " +
            DatabaseDungeonContract.ContractEntry.REGULARREWARD + " text, " +
            DatabaseDungeonContract.ContractEntry.ULTIMATEFAILURE + " text, " +
            DatabaseDungeonContract.ContractEntry.ULTIMATEREWARD + " text, " +
            DatabaseDungeonContract.ContractEntry.HEROMODE + " text);";
    public static final String CREATE_TABLE_DUNGEONDATES = "create table " + DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseDungeonDatesContract.ContractEntry.DATEID + " number primary key, " +
            DatabaseDungeonDatesContract.ContractEntry.DUNGEONID + " number, " +
            DatabaseDungeonDatesContract.ContractEntry.DATE + " text, " +
            DatabaseDungeonDatesContract.ContractEntry.STATUS + " text);";
    public static final String CREATE_TABLE_ITEM = "create table " + DatabaseItemContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseItemContract.ContractEntry.ITEMID + " number primary key, " +
            DatabaseItemContract.ContractEntry.ITEMNAME + " text, " +
            DatabaseItemContract.ContractEntry.COST + " number);";
    public static final String DROP_TABLE_USER = "drop table if exists " + DatabaseUserContract.ContractEntry.TABLE_NAME;
    public static final String DROP_TABLE_SPRITE = "drop table if exists " + DatabaseSpriteContract.ContractEntry.TABLE_NAME;
    public static final String DROP_TABLE_DUNGEON = "drop table if exists " + DatabaseDungeonContract.ContractEntry.TABLE_NAME;
    public static final String DROP_TABLE_DUNGEONDATES = "drop table if exists " + DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME;
    public static final String DROP_TABLE_ITEM = "drop table if exists " + DatabaseItemContract.ContractEntry.TABLE_NAME;
    public DatabaseHelper(Context context){
        super(context,DATABASENAME,null,DATABASEVERSION);
        Log.d("Database", "Database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        Log.d("Database", DatabaseUserContract.ContractEntry.TABLE_NAME + " Table created...");
        db.execSQL(CREATE_TABLE_SPRITE);
        Log.d("Database", DatabaseSpriteContract.ContractEntry.TABLE_NAME + " Table created...");
        db.execSQL(CREATE_TABLE_DUNGEON);
        Log.d("Database", DatabaseDungeonContract.ContractEntry.TABLE_NAME + " Table created...");
        db.execSQL(CREATE_TABLE_DUNGEONDATES);
        Log.d("Database", DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME + " Table created...");
        db.execSQL(CREATE_TABLE_ITEM);
        Log.d("Database", DatabaseItemContract.ContractEntry.TABLE_NAME + " Table created...");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_SPRITE);
        db.execSQL(DROP_TABLE_DUNGEON);
        db.execSQL(DROP_TABLE_DUNGEONDATES);
        db.execSQL(DROP_TABLE_ITEM);
        onCreate(db);
    }

    public void addUser(String name, String email, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseUserContract.ContractEntry.NAME, name);
        contentValues.put(DatabaseUserContract.ContractEntry.EMAIL, email);
        db.insert(DatabaseUserContract.ContractEntry.TABLE_NAME,null,contentValues);
        Log.d("Database", "One row inserted in " + DatabaseUserContract.ContractEntry.TABLE_NAME);
    }
    public void addSprite(int maxhealth, int exp, int level, int gold, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSpriteContract.ContractEntry.MAXHEALTH, maxhealth);
        contentValues.put(DatabaseSpriteContract.ContractEntry.EXP, exp);
        contentValues.put(DatabaseSpriteContract.ContractEntry.LEVEL, level);
        contentValues.put(DatabaseSpriteContract.ContractEntry.GOLD, gold);
        db.insert(DatabaseSpriteContract.ContractEntry.TABLE_NAME,null,contentValues);
        Log.d("Database", "One row inserted in " + DatabaseSpriteContract.ContractEntry.TABLE_NAME);
    }
    public void addDungeon(String name,int maxhealth, int health, String difficulty, String regularpenalty, String regularreward,
                           String ultimatefailure, String ultimatereward , String heromode, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseDungeonContract.ContractEntry.NAME, name);
        contentValues.put(DatabaseDungeonContract.ContractEntry.MAXHEALTH, maxhealth);
        contentValues.put(DatabaseDungeonContract.ContractEntry.HEALTH, health);
        contentValues.put(DatabaseDungeonContract.ContractEntry.DIFFICULTY, difficulty);
        contentValues.put(DatabaseDungeonContract.ContractEntry.REGULARPENALTY, regularpenalty);
        contentValues.put(DatabaseDungeonContract.ContractEntry.REGULARREWARD, regularreward);
        contentValues.put(DatabaseDungeonContract.ContractEntry.ULTIMATEFAILURE, ultimatefailure);
        contentValues.put(DatabaseDungeonContract.ContractEntry.ULTIMATEREWARD, ultimatereward);
        contentValues.put(DatabaseDungeonContract.ContractEntry.HEROMODE, heromode);
        db.insert(DatabaseDungeonContract.ContractEntry.TABLE_NAME,null,contentValues);
        Log.d("Database", "One row inserted in " + DatabaseDungeonContract.ContractEntry.TABLE_NAME);
    }
    public void addDungeonDate(int dungeonid, String date, String status, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseDungeonDatesContract.ContractEntry.DUNGEONID, dungeonid);
        contentValues.put(DatabaseDungeonDatesContract.ContractEntry.DATE, date);
        contentValues.put(DatabaseDungeonDatesContract.ContractEntry.STATUS, status);
        db.insert(DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME,null,contentValues);
        Log.d("Database", "One row inserted in " + DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME);
    }
    public void addItem(String itemname, int cost, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseItemContract.ContractEntry.ITEMNAME, itemname);
        contentValues.put(DatabaseItemContract.ContractEntry.COST, cost);
        db.insert(DatabaseItemContract.ContractEntry.TABLE_NAME,null,contentValues);
        Log.d("Database", "One row inserted in " + DatabaseItemContract.ContractEntry.TABLE_NAME);
    }
    public Cursor readAllUsers(SQLiteDatabase db){
        String[] projections = {
                DatabaseUserContract.ContractEntry.NAME,
                DatabaseUserContract.ContractEntry.EMAIL
        };
        return db.query(DatabaseUserContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public Cursor readAllSprites(SQLiteDatabase db){
        String[] projections = {
                DatabaseSpriteContract.ContractEntry.SPRITEID,
                DatabaseSpriteContract.ContractEntry.MAXHEALTH,
                DatabaseSpriteContract.ContractEntry.LEVEL,
                DatabaseSpriteContract.ContractEntry.EXP,
                DatabaseSpriteContract.ContractEntry.GOLD
        };
        return db.query(DatabaseSpriteContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public Cursor readAllDungeons(SQLiteDatabase db){
        String[] projections = {
                DatabaseDungeonContract.ContractEntry.DUNGEONID,
                DatabaseDungeonContract.ContractEntry.NAME,
                DatabaseDungeonContract.ContractEntry.MAXHEALTH,
                DatabaseDungeonContract.ContractEntry.HEALTH,
                DatabaseDungeonContract.ContractEntry.DIFFICULTY,
                DatabaseDungeonContract.ContractEntry.REGULARPENALTY,
                DatabaseDungeonContract.ContractEntry.REGULARREWARD,
                DatabaseDungeonContract.ContractEntry.ULTIMATEFAILURE,
                DatabaseDungeonContract.ContractEntry.ULTIMATEREWARD,
                DatabaseDungeonContract.ContractEntry.HEROMODE
        };
        return db.query(DatabaseDungeonContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public Cursor readDungeon(int dungeonid, SQLiteDatabase db){
        String[] projections = {
                DatabaseDungeonContract.ContractEntry.DUNGEONID,
                DatabaseDungeonContract.ContractEntry.NAME,
                DatabaseDungeonContract.ContractEntry.MAXHEALTH,
                DatabaseDungeonContract.ContractEntry.HEALTH,
                DatabaseDungeonContract.ContractEntry.DIFFICULTY,
                DatabaseDungeonContract.ContractEntry.REGULARPENALTY,
                DatabaseDungeonContract.ContractEntry.REGULARREWARD,
                DatabaseDungeonContract.ContractEntry.ULTIMATEFAILURE,
                DatabaseDungeonContract.ContractEntry.ULTIMATEREWARD,
                DatabaseDungeonContract.ContractEntry.HEROMODE
        };
        String selection = DatabaseDungeonContract.ContractEntry.DUNGEONID + " = " + dungeonid;
        return db.query(DatabaseDungeonContract.ContractEntry.TABLE_NAME,projections,selection,null,null,null,null);
    }

    public Cursor readAllDungeonDates(SQLiteDatabase db){
        String[] projections = {
                DatabaseDungeonDatesContract.ContractEntry.DATEID,
                DatabaseDungeonDatesContract.ContractEntry.DUNGEONID,
                DatabaseDungeonDatesContract.ContractEntry.DATE,
                DatabaseDungeonDatesContract.ContractEntry.STATUS
        };
        return db.query(DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public Cursor readDungeonDatesByDungeon(int dungeonid, SQLiteDatabase db){
        String[] projections = {
                DatabaseDungeonDatesContract.ContractEntry.DATEID,
                DatabaseDungeonDatesContract.ContractEntry.DUNGEONID,
                DatabaseDungeonDatesContract.ContractEntry.DATE,
                DatabaseDungeonDatesContract.ContractEntry.STATUS
        };
        String selection = DatabaseDungeonDatesContract.ContractEntry.DUNGEONID + " = " + dungeonid;
        return db.query(DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME,projections,selection,null,null,null,null);
    }
    public Cursor readAllItems(SQLiteDatabase db){
        String[] projections = {
                DatabaseItemContract.ContractEntry.ITEMID,
                DatabaseItemContract.ContractEntry.ITEMNAME,
                DatabaseItemContract.ContractEntry.COST
        };
        return db.query(DatabaseItemContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public void updateUser(String name, String email, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseUserContract.ContractEntry.NAME, name);
        contentValues.put(DatabaseUserContract.ContractEntry.EMAIL, email);

        String selection = DatabaseUserContract.ContractEntry.NAME + " = " + name;
        db.update(DatabaseUserContract.ContractEntry.TABLE_NAME, contentValues, selection, null);
    }
    public void updateSpite(int spriteid,int maxhealth, int exp, int level, int gold, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSpriteContract.ContractEntry.MAXHEALTH, maxhealth);
        contentValues.put(DatabaseSpriteContract.ContractEntry.EXP, exp);
        contentValues.put(DatabaseSpriteContract.ContractEntry.LEVEL, level);
        contentValues.put(DatabaseSpriteContract.ContractEntry.GOLD, gold);

        String selection = DatabaseSpriteContract.ContractEntry.SPRITEID + " = " + spriteid;
        db.update(DatabaseSpriteContract.ContractEntry.TABLE_NAME, contentValues, selection, null);
    }
    public void updateDungeon(int dungeonid, String name, int maxhealth, int health, String difficulty, String regularpenalty, String regularreward,
                              String ultimatefailure, String ultimatereward , String heromode, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseDungeonContract.ContractEntry.NAME, name);
        contentValues.put(DatabaseDungeonContract.ContractEntry.MAXHEALTH, maxhealth);
        contentValues.put(DatabaseDungeonContract.ContractEntry.HEALTH, health);
        contentValues.put(DatabaseDungeonContract.ContractEntry.DIFFICULTY, difficulty);
        contentValues.put(DatabaseDungeonContract.ContractEntry.REGULARPENALTY, regularpenalty);
        contentValues.put(DatabaseDungeonContract.ContractEntry.REGULARREWARD, regularreward);
        contentValues.put(DatabaseDungeonContract.ContractEntry.ULTIMATEFAILURE, ultimatefailure);
        contentValues.put(DatabaseDungeonContract.ContractEntry.ULTIMATEREWARD, ultimatereward);
        contentValues.put(DatabaseDungeonContract.ContractEntry.HEROMODE, heromode);

        String selection = DatabaseDungeonContract.ContractEntry.DUNGEONID + " = " + dungeonid;
        db.update(DatabaseDungeonContract.ContractEntry.TABLE_NAME, contentValues, selection, null);
    }
    public void updateDungeonDates(int dateid,int dungeonid, String date, String status, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseDungeonDatesContract.ContractEntry.DUNGEONID, dungeonid);
        contentValues.put(DatabaseDungeonDatesContract.ContractEntry.DATE, date);
        contentValues.put(DatabaseDungeonDatesContract.ContractEntry.STATUS, status);

        String selection = DatabaseDungeonDatesContract.ContractEntry.DATEID + " = " + dateid;
        db.update(DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME, contentValues, selection, null);
    }
    public void updateItems(int itemid,String itemname, int cost, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseItemContract.ContractEntry.ITEMNAME, itemname);
        contentValues.put(DatabaseItemContract.ContractEntry.COST, cost);

        String selection = DatabaseItemContract.ContractEntry.ITEMID + " = " + itemid;
        db.update(DatabaseItemContract.ContractEntry.TABLE_NAME, contentValues, selection, null);
    }
    public void deleteUser(String name, SQLiteDatabase db){
        String selection = DatabaseUserContract.ContractEntry.NAME + " = " + name;
        db.delete(DatabaseUserContract.ContractEntry.TABLE_NAME, selection, null);
    }
    public void deleteSprite(String spriteid, SQLiteDatabase db){
        String selection = DatabaseSpriteContract.ContractEntry.SPRITEID + " = " + spriteid;
        db.delete(DatabaseSpriteContract.ContractEntry.TABLE_NAME, selection, null);
    }
    public void deleteDungeon(int dungeonid, SQLiteDatabase db){
        String selection = DatabaseDungeonContract.ContractEntry.DUNGEONID + " = " + dungeonid;
        db.delete(DatabaseDungeonContract.ContractEntry.TABLE_NAME, selection, null);
    }
    public void deleteDungeonDate(int dateid, SQLiteDatabase db){
        String selection = DatabaseDungeonDatesContract.ContractEntry.DATEID + " = " + dateid;
        db.delete(DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME, selection, null);
    }
    public void deleteItem(int itemid, SQLiteDatabase db){
        String selection = DatabaseItemContract.ContractEntry.ITEMID + " = " + itemid;
        db.delete(DatabaseItemContract.ContractEntry.TABLE_NAME, selection, null);
    }
    public User generateUserFromDatabase(){
        Cursor userCursor = readAllUsers(this.getReadableDatabase());
        userCursor.moveToFirst();
        String username = userCursor.getString(userCursor.getColumnIndex(DatabaseUserContract.ContractEntry.NAME));
        String email = userCursor.getString(userCursor.getColumnIndex(DatabaseUserContract.ContractEntry.EMAIL));
        userCursor.close();

        Cursor spriteCursor = readAllSprites(this.getReadableDatabase());
        spriteCursor.moveToFirst();
        int maxhealth = spriteCursor.getInt(spriteCursor.getColumnIndex(DatabaseSpriteContract.ContractEntry.MAXHEALTH));
        int level = spriteCursor.getInt(spriteCursor.getColumnIndex(DatabaseSpriteContract.ContractEntry.LEVEL));
        int exp = spriteCursor.getInt(spriteCursor.getColumnIndex(DatabaseSpriteContract.ContractEntry.EXP));
        int gold = spriteCursor.getInt(spriteCursor.getColumnIndex(DatabaseSpriteContract.ContractEntry.GOLD));
        Sprite sprite = new Sprite("",maxhealth,exp,level,gold,null);
        spriteCursor.close();
        Cursor dungeonCursor = readAllDungeons(this.getReadableDatabase());
        dungeonCursor.moveToFirst();
        ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
        if (dungeonCursor.moveToFirst()){
            while(!dungeonCursor.isAfterLast()){
                int dungeonid = dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DUNGEONID));
                Cursor dungeonDatesCursor = readDungeonDatesByDungeon(dungeonid,this.getReadableDatabase());
                ArrayList<DungeonDate> dungeonDateList = new ArrayList<DungeonDate>();
                if (dungeonDatesCursor.moveToFirst()) {
                    while (!dungeonDatesCursor.isAfterLast()) {
                        String date = dungeonDatesCursor.getString(dungeonDatesCursor.getColumnIndex(DatabaseDungeonDatesContract.ContractEntry.DATE));
                        String status = dungeonDatesCursor.getString(dungeonDatesCursor.getColumnIndex(DatabaseDungeonDatesContract.ContractEntry.STATUS));
                        try {
                            DungeonDate dungeonDate = new DungeonDate(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(date), Status.valueOf(status));
                            dungeonDateList.add(dungeonDate);
                            dungeonDatesCursor.moveToNext();
                        } catch(ParseException e){ }
                    }
                }
                dungeonDatesCursor.close();
                String dungeonname = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.NAME));
                int maxhealthdungeon = dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.MAXHEALTH));
                int health = dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.HEALTH));
                String difficulty = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DIFFICULTY));
                String heromode = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.HEROMODE));
                String regularpenalty = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.REGULARPENALTY));
                String regularreward = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.REGULARREWARD));
                String ultimatepenalty = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.ULTIMATEFAILURE));
                String ultimatereward = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.ULTIMATEREWARD));

//                Dungeon dungeon = new Dungeon(dungeonname,maxhealthdungeon,health,ultimatereward,regularreward,ultimatepenalty,regularpenalty,dungeonDateList.toArray(new DungeonDate[dungeonDateList.size()]), Difficulty.valueOf(difficulty));
                Dungeon dungeon = new Dungeon(dungeonname,maxhealthdungeon,health,ultimatereward,regularreward,ultimatepenalty,regularpenalty,dungeonDateList, Difficulty.valueOf(difficulty));
                dungeonList.add(dungeon);
                dungeonCursor.moveToNext();
            }
        }
        dungeonCursor.close();
//        User user = new User(username,email,sprite,dungeonList.toArray(new Dungeon[dungeonList.size()]));
        User user = new User(username,email,sprite,dungeonList);

        return user;
    }
}
