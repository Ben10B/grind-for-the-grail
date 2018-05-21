package Models;

import java.util.ArrayList;

public class Dungeon {
    private String title;

    private int maxHealth;
    private int currentHealth;
//    private String ultimateReward;// <<stretch>>
//    private String regReward;// <<stretch>>
//    private String ultimatePenalty;// <<stretch>>
//    private String regPenalty;// <<stretch>>
//    private ArrayList<DungeonDate> dungeonDates;// <<stretch>>
//    private Difficulty difficulty;// <<stretch>>
//    private boolean heroMode;// <<stretch>>

    public Dungeon(String title, int maxHealth) {
        this.title = title;
        this.maxHealth = maxHealth;
    }

    public void FailDay(){
        //take health
        //check if dead
            //yes?: ultimatePenalty
            //no?: regPenalty
    }

    public int SucceedDay(){
        //check if dungeon is completed
            //yes?: ultimateReward
            //no?: regReward and advance day
        //return exp earned
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

//    public String getUltimateReward() {
//        return ultimateReward;
//    }
//
//    public void setUltimateReward(String ultimateReward) {
//        this.ultimateReward = ultimateReward;
//    }
//
//    public String getRegReward() {
//        return regReward;
//    }
//
//    public void setRegReward(String regReward) {
//        this.regReward = regReward;
//    }
//
//    public String getUltimatePenalty() {
//        return ultimatePenalty;
//    }
//
//    public void setUltimatePenalty(String ultimatePenalty) {
//        this.ultimatePenalty = ultimatePenalty;
//    }
//
//    public String getRegPenalty() {
//        return regPenalty;
//    }
//
//    public void setRegPenalty(String regPenalty) {
//        this.regPenalty = regPenalty;
//    }
//
//    public ArrayList<DungeonDate> getDungeonDates() {
//        return dungeonDates;
//    }
//
//    public void setDungeonDates(ArrayList<DungeonDate> dungeonDates) {
//        this.dungeonDates = dungeonDates;
//    }
//
//    public Difficulty getDifficulty() {
//        return difficulty;
//    }
//
//    public void setDifficulty(Difficulty difficulty) {
//        this.difficulty = difficulty;
//    }
//
//    public boolean isHeroMode() {
//        return heroMode;
//    }
//
//    public void setHeroMode(boolean heroMode) {
//        this.heroMode = heroMode;
//    }


}
