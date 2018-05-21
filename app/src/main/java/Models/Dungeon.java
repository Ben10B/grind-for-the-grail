package Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Dungeon {
    private String title;
    private int maxHealth;
    private int currentHealth;
//    private String ultimateReward;// <<stretch>>
//    private String regReward;// <<stretch>>
//    private String ultimatePenalty;// <<stretch>>
//    private String regPenalty;// <<stretch>>
    private ArrayList<DungeonDate> dungeonDates;// <<stretch>>
//    private Difficulty difficulty;// <<stretch>>
//    private boolean heroMode;// <<stretch>>

    public Dungeon(String title, int maxHealth, Date endDate) {
        this.title = title;
        this.maxHealth = maxHealth;
        dungeonDates = new ArrayList<DungeonDate>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 7;i++){//TODO adjust for endDate addition
            DungeonDate temp = new DungeonDate(c.getTime(), Status.Inactive);
            dungeonDates.add(temp);
            c.add(Calendar.DATE, 1);

        }
    }

    public void FailDay(){
        currentHealth -= getHealthLost();
        boolean isAlive = getCurrentHealth() > 0? true : false;
        if (isAlive){
            //TODO yes?: regPenalty
            //TODO does a day get added?
//            Calendar c = Calendar.getInstance();
//            c.setTime(getDungeonDates().get(getDungeonDates().size()).getDate());
//            c.add(Calendar.DATE, 1);
//            DungeonDate temp = new DungeonDate(c.getTime(), Status.Inactive);
//            dungeonDates.add(temp);

        }else{
            //TODO no?: ultimatePenalty

        }

    }

    private int getHealthLost(){
        int healthLost = 1;
        ArrayList<DungeonDate> dates = getDungeonDates();
        int index = -1;
        for (int i = 0; i < dates.size();i++){
            if (dates.get(i).getDate() == Calendar.getInstance().getTime()){
                index = i;
                break;
            }
        }
        if (index > 0){
            for (int i = index; i > dates.size(); i--){
                if (dates.get(i).getStatus() != Status.Failed){
                    break;
                }
                else if (dates.get(i).getStatus() == Status.Failed){
                    healthLost *= 2;
                }
            }
        }
        return healthLost;
    }

    //TODO implement SucceedDay method
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
    public ArrayList<DungeonDate> getDungeonDates() {
        return dungeonDates;
    }

    public void setDungeonDates(ArrayList<DungeonDate> dungeonDates) {
        this.dungeonDates = dungeonDates;
    }
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
