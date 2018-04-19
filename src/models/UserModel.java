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

    //Achievements
    private HashMap<String, Boolean> achievementsList;

    //Energy consumption info
    private int woodEnergyConsumption = 4;
    private int stoneEnergyConsumption = 6;
    private int fightEnergyConsumption = 12;
    private int fishEnergyConsumption = 6;

    //Night-time attack info
    private double minAttackValue = 4;
    private double maxAttackValue = 9;

    //Inventory Info
    private HashMap<String, Integer> playerInventory;
    private HashMap<String, String> playerTools;

    //Experience Points Info
    private int currentXP;
    private int currentXPRequired;

    //Day and time info
    private int hourNum;
    private String minuteNum;
    private int dayNum;
    private String AMorPM;

    //Sets the player's inventory and tools
    public void setPlayerInventory(HashMap inventory){
        this.playerInventory = inventory;
    }
    public void setPlayerTools(HashMap tools) {this.playerTools = tools;}

    //ArrayList of strings with recent actions
    private ArrayList<String> outputText = new ArrayList<String>();

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
    }

    //Allows outside classes to update the achievements
    public void completeAchievement(String achievementName){this.achievementsList.put(achievementName, true);}

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

    //Allows outside classes to add a new tool to the user
    public void addTool(String toolType, String addedTool){playerTools.put(toolType, addedTool);}

    //Allows outside classes to add a new string to the output text
    public void addStringToOutputText(String action, UserModel user){
        outputText.add(action);
        GameController.checkOutputTextLength(user);
    }

    //Allows outside classes to "reset" the output text
    //This just allows for pruning so the ArrayList is never too long
    public void resetOutputText(ArrayList outputText){this.outputText = outputText;}

    //Ugly Setters
    public void setAchievementsList(HashMap achievementsList){this.achievementsList = achievementsList;}
    public void setEnergy(int newEnergyAmt){this.energy = newEnergyAmt;}
    public void setHealth(int newHealth){this.health = newHealth;}
    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}
    public void setMaxEnergy(int maxEnergy){this.maxEnergy = maxEnergy;}

    public void setMinAttackValue(int minAttack){this.minAttackValue = minAttack;}
    public void setMaxAttackValue(int maxAttack){this.maxAttackValue = maxAttack;}

    public void setFightEnergyConsumption(int fightEnergyConsumption) {this.fightEnergyConsumption = fightEnergyConsumption;}
    public void setFishEnergyConsumption(int fishEnergyConsumption) {this.fishEnergyConsumption = fishEnergyConsumption;}
    public void setStoneEnergyConsumption(int stoneEnergyConsumption) {this.stoneEnergyConsumption = stoneEnergyConsumption;}
    public void setWoodEnergyConsumption(int woodEnergyConsumption) {this.woodEnergyConsumption = woodEnergyConsumption;}

    public void setCurrentXP(int newCurrentXPAmt){this.currentXP = newCurrentXPAmt;}
    public void setCurrentXPRequired(int newCurrentXPRequired){this.currentXPRequired = newCurrentXPRequired;}
    public void setLevelNum(int newLevelNum){this.levelNum = newLevelNum;}

    public void setHourNum(int hourNum) {this.hourNum = hourNum;}
    public void setMinuteNum(String minuteNum) {this.minuteNum = minuteNum;}
    public void setAMorPM(String AMorPM) {this.AMorPM = AMorPM;}
    public void setDayNum(int dayNum){this.dayNum = dayNum;}


    //Ugly Getters
    public int getHealth() {return health;}
    public int getEnergy() {return energy;}
    public int getLevelNum() {return levelNum;}
    public String getPlayerName() {return playerName;}
    public int getMaxEnergy() {return maxEnergy;}
    public int getMaxHealth() {return maxHealth;}

    public boolean getAchievementByName(String achievementName){return this.achievementsList.get(achievementName);}
    public HashMap<String, Boolean> getAchievementsList() {return achievementsList; }

    public int getFightEnergyConsumption() {return fightEnergyConsumption;}
    public int getFishEnergyConsumption() {return fishEnergyConsumption;}
    public int getStoneEnergyConsumption() {return stoneEnergyConsumption;}
    public int getWoodEnergyConsumption() {return woodEnergyConsumption;}

    public double getMinAttackValue() {return minAttackValue;}
    public double getMaxAttackValue() {return maxAttackValue;}

    public HashMap<String, Integer> getPlayerInventory() {return playerInventory;}
    public int getInventoryItemAmt(String item) {return playerInventory.get(item);}

    public int getCurrentXP() {return currentXP;}
    public int getCurrentXPRequired() {return currentXPRequired;}

    public int getHourNum() {return hourNum;}
    public String getMinuteNum() {return minuteNum;}
    public int getDayNum() {return dayNum;}
    public String getAMorPM() {return AMorPM;}

    public HashMap<String, String> getPlayerTools(){return  playerTools;}
    public String getToolByName(String toolType){return playerTools.get(toolType);}

    public ArrayList<String> getOutputText() {return outputText;}
}