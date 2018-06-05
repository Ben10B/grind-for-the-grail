package Models;

import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Dungeon implements Serializable {
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
        this.currentHealth = maxHealth;
        this.ultimateReward = "";
        this.regReward = "";
        this.ultimatePenalty = "";
        this.regPenalty = "";
        this.dungeonDates = createDungeonDates(endDate);
        this.difficulty = Difficulty.None;
//        this.heroMode = false;
    }

    public Dungeon(String title, int maxHealth, Date endDate, Difficulty difficulty) {
        this.title = title;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.ultimateReward = "";
        this.regReward = "";
        this.ultimatePenalty = "";
        this.regPenalty = "";
        dungeonDates = createDungeonDates(endDate);
        this.difficulty = difficulty;
//        this.heroMode = false;
    }

    public Dungeon(String title, int maxHealth, Date endDate, String ultimateReward, String regReward, String ultimatePenalty, String regPenalty, Difficulty difficulty) {//, boolean heroMode) {
        this.title = title;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.ultimateReward = ultimateReward;
        this.regReward = regReward;
        this.ultimatePenalty = ultimatePenalty;
        this.regPenalty = regPenalty;
        dungeonDates = createDungeonDates(endDate);
        this.difficulty = difficulty;
//        this.heroMode = heroMode;
    }

    public Dungeon(String title, int maxHealth, int currentHealth, String ultimateReward, String regReward, String ultimatePenalty, String regPenalty, ArrayList<DungeonDate> dungeonDates, Difficulty difficulty) {
        this.title = title;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.ultimateReward = ultimateReward;
        this.regReward = regReward;
        this.ultimatePenalty = ultimatePenalty;
        this.regPenalty = regPenalty;
        this.dungeonDates = dungeonDates;
        this.difficulty = difficulty;
    }

    public String failDay(Date day) {
        ArrayList<DungeonDate> dates = getDungeonDates();
        String penalty = "";
        DungeonDate failedDay = dates.get(getDungeonDateIndexByDate(day));
        failedDay.setStatus(Status.Failed);
        int hpLost = getHealthLost();
        currentHealth -= hpLost;
        boolean isAlive = getCurrentHealth() > 0;
        if (isAlive && !getRegPenalty().isEmpty()) {
            penalty = getRegPenalty();
        } else if (!isAlive && !getUltimatePenalty().isEmpty()) {
            penalty = getUltimatePenalty();
        }
        String output = "- " + hpLost + " hp\n" + penalty;
        setDungeonDates(dates);
        return output;
    }

    public String completeDay(Date day, Sprite sprite) {
        ArrayList<DungeonDate> dates = getDungeonDates();
        String reward = "";
        Random rand = new Random();
        DungeonDate completedDay = dates.get(getDungeonDateIndexByDate(day));
        completedDay.setStatus(Status.Completed);
        int exp = 0;
        int gold = rand.nextInt(10) + 1;
        switch (getDifficulty()) {
            case Squire:
            case None:
                exp = 1;
                break;
            case Knight:
                exp = 2;
                break;
            case Grail:
                exp = 3;
                break;
        }
        if (!isCompleted() && !getRegReward().isEmpty()) {
            reward = getRegReward();
        } else if (isCompleted()) {
            if (!getUltimateReward().isEmpty()) {
                reward = getUltimateReward();
            }
            //exp = length * diffMod
            //gold = (length * 7-10)* diffMod
            switch (getDifficulty()) {
                case Squire:
                case None:
                    exp += (getDungeonDates().size() * 1);
                    gold += (getDungeonDates().size() * rand.nextInt(4) + 7) * 1;
                    break;
                case Knight:
                    exp += (getDungeonDates().size() * 1.5);
                    gold += (getDungeonDates().size() * rand.nextInt(4) + 7) * 1.5;
                    break;
                case Grail:
                    exp += (getDungeonDates().size() * 2);
                    gold += (getDungeonDates().size() * rand.nextInt(4) + 7) * 2;
                    break;
            }
        }
        sprite.addExp(exp);
        sprite.addGold(gold);
        String output = "+ " + gold + " gp + " + exp + " xp\n" + reward;
        setDungeonDates(dates);
        return output;
    }

    /**
     * Determines the amount of health that the player will lose when they fail a day.
     * This is based on how many days in a row they have failed.
     * Helper Method.
     */
    private int getHealthLost() {
        int healthLost = 1;
        ArrayList<DungeonDate> dates = getDungeonDates();
        int index = getDungeonDateIndexByDate(Calendar.getInstance().getTime());
        if (index > 0) {
            for (int i = index; i >= 0; i--) {
                if (dates.get(i).getStatus() != Status.Failed) {
                    break;
                } else if (dates.get(i).getStatus() == Status.Failed) {
                    healthLost *= 2;
                }
            }
        }
        return healthLost;
    }

    /**
     * Determines if the Dungeon is completed by checking the Status of all DungeonDates.
     *
     * @return - Whether the Dungeon is completed.
     */
    private boolean isCompleted() {
        boolean output = true;
        ArrayList<DungeonDate> dates = getDungeonDates();
        for (DungeonDate dDate : dates) {
            if (dDate.getStatus() != Status.Completed && dDate.getStatus() != Status.Failed) {
                output = false;
                break;
            }
        }
        return output;
    }

    /**
     * Finds the DungeonDate that matches the given Date. Returns null if there is no match.
     * Helper Method.
     *
     * @param date - Date of the desired DungeonDate
     * @return - Matching DungeonDate
     */
    private int getDungeonDateIndexByDate(Date date) {
        int index = 0;
//        DungeonDate output = null;
        ArrayList<DungeonDate> dates = getDungeonDates();
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).getDate() == date) {
//                output = dates[i];
                index = i;
                break;
            }
        }
//        return output;
        return index;
    }

    /**
     * Creates the initial collection of DungeonDates from the current date to the given end Date.
     * Helper Method
     *
     * @param endDate - Date of the last day
     * @return - Collection of DungeonDates from current day to endDate
     */
    private ArrayList<DungeonDate> createDungeonDates(Date endDate) {
        ArrayList<DungeonDate> dates = new ArrayList<DungeonDate>();
        Calendar c = Calendar.getInstance();
        DungeonDate temp = new DungeonDate(c.getTime(), Status.Unresolved);
        do {
            dates.add(temp);
            c.add(Calendar.DATE, 1);
            temp = new DungeonDate(c.getTime(), Status.Inactive);
        }
        while (!isSameDay(c.getTime(),endDate));
        dates.add(temp);
        return dates;
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        boolean sameYear = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)-1900;
        boolean sameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
        boolean sameDay = calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
        return (sameDay && sameMonth && sameYear);
    }

    public void updateDungeonDates() {
        ArrayList<DungeonDate> dates = getDungeonDates();
        int index = getDungeonDateIndexByDate(Calendar.getInstance().getTime());
        for (int i = index; i >= 0; i--) {
            DungeonDate ddate = dates.get(i);
            if (ddate.getStatus() == Status.Inactive) {
                ddate.setStatus(Status.Unresolved);
            }
        }
        setDungeonDates(dates);
    }

    //Getters and Setters beyond this point

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
