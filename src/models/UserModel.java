package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserModel implements Serializable {
    //Basic Player Info
    private String playerName;
    private int levelNum;
    private int health;
    private int energy;
    private int maxEnergy;
    private int maxHealth;

    //Inventory Info
    private HashMap<String, Integer> playerInventory;

    //Player Tools
    private String axe;
    private String pickaxe;
    private String sword;
    private String hat;
    private String shirt;
    private String pants;
    private boolean oven;
    private String fishingRod;

    //Experience Points Info
    private int currentXP;
    private int currentXPRequired;

    //Day and time info
    private int hourNum;
    private String minuteNum;
    private int dayNum;
    private String AMorPM;

    //Called by "constructor" class to set the tools;
    private void makeTools(String axe, String pickaxe, String sword, String hat,
                           String shirt, String pants, boolean oven, String fishingRod){
        this.axe = axe;
        this.pickaxe = pickaxe;
        this.sword = sword;
        this.hat = hat;
        this.shirt = shirt;
        this.pants = pants;
        this.oven = oven;
        this.fishingRod = fishingRod;
    }

    //Sets the player's inventory
    public void setPlayerInventory(HashMap inventory){
        this.playerInventory = inventory;
    }

    //Creates the player stats
    public void createPlayer(String name, int levelNum, int health, int energy, int hour, String minute,
                             String ampm, int dayNum, int maxEnergy, int maxHealth){
        this.playerName = name;
        this.levelNum = levelNum;
        this.health = health;
        this.energy = energy;

        this.currentXP = 0;
        this.currentXPRequired = 10;

        this.hourNum = hour;
        this.minuteNum = minute;
        this.dayNum = dayNum;
        this.AMorPM = ampm;

        this.maxEnergy = maxEnergy;
        this.maxHealth = maxHealth;

        makeTools("None", "None","None","None","None",
                "None",false,"None");
    }

    //Allows outside classes to add things to the inventory
    public void addToInventory(String item, int amtAdded){
        int current = playerInventory.get(item);
        playerInventory.put(item, current + amtAdded);
    }

    //Allows outside classes to remove things from the intentory
    public void removeFromInventory(String item, int amtRemoved){
        int current = playerInventory.get(item);
        playerInventory.put(item, current - amtRemoved);
    }

    //Ugly Setters
    public void setEnergy(int newEnergyAmt){this.energy = newEnergyAmt;}
    public void setHealth(int newHealth){this.health = newHealth;}

    public void setCurrentXP(int newCurrentXPAmt){this.currentXP = newCurrentXPAmt;}
    public void setCurrentXPRequired(int newCurrentXPRequired){this.currentXPRequired = newCurrentXPRequired;}
    public void setLevelNum(int newLevelNum){this.levelNum = newLevelNum;}

    public void setHourNum(int hourNum) {this.hourNum = hourNum;}
    public void setMinuteNum(String minuteNum) {this.minuteNum = minuteNum;}
    public void setAMorPM(String AMorPM) {this.AMorPM = AMorPM;}
    public void setDayNum(int dayNum){this.dayNum = dayNum;}

    //Ugly Getters
    public HashMap<String, Integer> getPlayerInventory() {return playerInventory;}
    public int getInventoryItemAmt(String item) {return playerInventory.get(item);}

    public int getCurrentXP() {return currentXP;}
    public int getCurrentXPRequired() {return currentXPRequired;}

    public int getHealth() {return health;}
    public int getEnergy() {return energy;}
    public int getLevelNum() {return levelNum;}
    public String getPlayerName() {return playerName;}
    public int getMaxEnergy() {return maxEnergy;}
    public int getMaxHealth() {return maxHealth;}

    public int getHourNum() {return hourNum;}
    public String getMinuteNum() {return minuteNum;}
    public int getDayNum() {return dayNum;}
    public String getAMorPM() {return AMorPM;}

    public String getAxe() {return axe;}
    public String getPickaxe() {return pickaxe;}
    public String getSword() {return sword;}
    public String getHat() {return hat;}
    public String getShirt() {return shirt;}
    public String getPants() {return pants;}
    public boolean isOven() {return oven;}
    public String getFishingRod() {return fishingRod;}
}