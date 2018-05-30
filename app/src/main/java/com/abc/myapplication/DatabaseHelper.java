package com.abc.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "grail.db";
    public static final int DATABASEVERSION = 1;

    public static final String CREATE_TABLE_USER = "create table " + DatabaseUserContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseUserContract.ContractEntry.NAME + " text primary key, " +
            DatabaseUserContract.ContractEntry.EMAIL + " text);";
    public static final String CREATE_TABLE_SPRITE = "create table " + DatabaseSpriteContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseSpriteContract.ContractEntry.MAXHEALTH + " number, " +
            DatabaseSpriteContract.ContractEntry.EXP + " number, " +
            DatabaseSpriteContract.ContractEntry.LEVEL + " number, " +
            DatabaseSpriteContract.ContractEntry.GOLD + " gold);";
    public static final String CREATE_TABLE_DUNGEON = "create table " + DatabaseDungeonContract.ContractEntry.TABLE_NAME +
            "(" + DatabaseDungeonContract.ContractEntry.DUNGEONID + " number primary key, " +
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
            "(" + DatabaseItemContract.ContractEntry.ITEMNAME + " text, " +
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
    public void addDungeon(int maxhealth, int health, String difficulty, String regularpenalty, String regularreward,
                           String ultimatefailure, String ultimatereward , String heromode, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
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
    public Cursor readUsers(SQLiteDatabase db){
        String[] projections = {
                DatabaseUserContract.ContractEntry.NAME,
                DatabaseUserContract.ContractEntry.EMAIL
        };
        return db.query(DatabaseUserContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public Cursor readSprites(SQLiteDatabase db){
        String[] projections = {
                DatabaseSpriteContract.ContractEntry.MAXHEALTH,
                DatabaseSpriteContract.ContractEntry.LEVEL,
                DatabaseSpriteContract.ContractEntry.LEVEL,
                DatabaseSpriteContract.ContractEntry.GOLD
        };
        return db.query(DatabaseSpriteContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public Cursor readDungeons(SQLiteDatabase db){
        String[] projections = {
                DatabaseDungeonContract.ContractEntry.DUNGEONID,
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
    public Cursor readDungeonDates(SQLiteDatabase db){
        String[] projections = {
                DatabaseDungeonDatesContract.ContractEntry.DATEID,
                DatabaseDungeonDatesContract.ContractEntry.DUNGEONID,
                DatabaseDungeonDatesContract.ContractEntry.DATE,
                DatabaseDungeonDatesContract.ContractEntry.STATUS
        };
        return db.query(DatabaseDungeonDatesContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
    public Cursor readItems(SQLiteDatabase db){
        String[] projections = {
                DatabaseItemContract.ContractEntry.ITEMNAME,
                DatabaseItemContract.ContractEntry.COST
        };
        return db.query(DatabaseItemContract.ContractEntry.TABLE_NAME,projections,null,null,null,null,null);
    }
}
