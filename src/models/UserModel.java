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
    private int remainingXP;

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
    public void createPlayer(String name, int levelNum, int health, int energy){
        this.playerName = name;
        this.levelNum = levelNum;
        this.health = health;
        this.energy = energy;
        this.currentXP = 0;
        this.currentXPRequired = 10;
        makeTools("None", "None","None","None","None",
                "None",false,"None");
    }

    //Inventory Adders
    public void actionPerformed(String item, int amtAdded, int lostEnergy){
        int current = playerInventory.get(item);
        playerInventory.put(item, current + amtAdded);
        this.energy = this.energy - lostEnergy;
        if(amtAdded == 3){
            currentXP += 1;
        }
    }

    //Ugly Getters
    public HashMap<String, Integer> getPlayerInventory() {return playerInventory;}
    public int getCurrentXP() {return currentXP;}
    public int getCurrentXPRequired() {return currentXPRequired;}
    public int getHealth() {return health;}
    public int getEnergy() {return energy;}
    public int getLevelNum() {return levelNum;}
    public String getPlayerName() {return playerName;}
    public String getAxe() {return axe;}
    public String getPickaxe() {return pickaxe;}
    public String getSword() {return sword;}
    public String getHat() {return hat;}
    public String getShirt() {return shirt;}
    public String getPants() {return pants;}
    public boolean isOven() {return oven;}
    public String getFishingRod() {return fishingRod;}
}