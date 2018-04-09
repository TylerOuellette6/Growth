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
    public void addStringToOutputText(String action){outputText.add(action);}

    //Allows outside classes to "reset" the output text
    //This just allows for pruning so the ArrayList is never too long
    public void resetOutputText(ArrayList outputText){this.outputText = outputText;}

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

    public HashMap<String, String> getPlayerTools(){return  playerTools;}
    public String getToolByName(String toolType){return playerTools.get(toolType);}

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

    public ArrayList<String> getOutputText() {return outputText;}
}