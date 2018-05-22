package Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Dungeon {
    private String title;
    private int maxHealth;
    private int currentHealth;
    private String ultimateReward;// <<stretch>>
    private String regReward;// <<stretch>>
    private String ultimatePenalty;// <<stretch>>
    private String regPenalty;// <<stretch>>
    private ArrayList<DungeonDate> dungeonDates;// <<stretch>>
    private Difficulty difficulty;// <<stretch>>
    private boolean heroMode;// <<stretch>>

    public Dungeon(String title, int maxHealth, Date endDate) {
        this.title = title;
        this.maxHealth = maxHealth;
        dungeonDates = new ArrayList<DungeonDate>();
        Calendar c = Calendar.getInstance();
        DungeonDate temp = new DungeonDate(c.getTime(), Status.Inactive);
        do {
            dungeonDates.add(temp);
            c.add(Calendar.DATE, 1);
            temp = new DungeonDate(c.getTime(), Status.Inactive);
        } while (temp.getDate() != endDate);
    }

    public void failDay() {
        currentHealth -= getHealthLost();
        boolean isAlive = getCurrentHealth() > 0;
        if (isAlive) {
            //TODO yes?: regPenalty
        } else {
            //TODO no?: ultimatePenalty
        }
    }

    /* This is a helper method for failDay(). It determines the amount of health that the player
    will lose, based on how many days in a row they have failed. */
    private int getHealthLost() {
        int healthLost = 1;
        ArrayList<DungeonDate> dates = getDungeonDates();
        int index = dates.indexOf(currentDungeonDate());
        if (index > 0) {
            for (int i = index; i > dates.size(); i--) {
                if (dates.get(i).getStatus() != Status.Failed) {
                    break;
                } else if (dates.get(i).getStatus() == Status.Failed) {
                    healthLost *= 2;
                }
            }
        }
        return healthLost;
    }

    public int succeedDay() {
        //TODO <<stretch>> They get gold too
        int exp = 0;
        switch (getDifficulty()) {
            case Squire:
                exp = 1;
                break;
            case Knight:
                exp = 2;
                break;
            case Grail:
                exp = 3;
                break;
        }
        if(isCompleted()){
            //TODO yes?: ultimateReward
        }else {
            //TODO no?: regReward
        }
        return exp;
    }

    private boolean isCompleted() {
        ArrayList<DungeonDate> dates = getDungeonDates();
        DungeonDate today = currentDungeonDate();
        DungeonDate endDate = dates.get(dates.size());
        return today.getDate() == endDate.getDate();
    }

    private DungeonDate currentDungeonDate() {
        DungeonDate output = null;
        ArrayList<DungeonDate> dates = getDungeonDates();
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).getDate() == Calendar.getInstance().getTime()) {
                output = dates.get(i);
                break;
            }
        }
        return output;
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

    public String getUltimateReward() {
        return ultimateReward;
    }

    public void setUltimateReward(String ultimateReward) {
        this.ultimateReward = ultimateReward;
    }

    public String getRegReward() {
        return regReward;
    }

    public void setRegReward(String regReward) {
        this.regReward = regReward;
    }

    public String getUltimatePenalty() {
        return ultimatePenalty;
    }

    public void setUltimatePenalty(String ultimatePenalty) {
        this.ultimatePenalty = ultimatePenalty;
    }

    public String getRegPenalty() {
        return regPenalty;
    }

    public void setRegPenalty(String regPenalty) {
        this.regPenalty = regPenalty;
    }

    public ArrayList<DungeonDate> getDungeonDates() {
        return dungeonDates;
    }

    public void setDungeonDates(ArrayList<DungeonDate> dungeonDates) {
        this.dungeonDates = dungeonDates;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isHeroMode() {
        return heroMode;
    }

    public void setHeroMode(boolean heroMode) {
        this.heroMode = heroMode;
    }


}
