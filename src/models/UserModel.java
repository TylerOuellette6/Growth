package models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable {
    //Basic Player Info
    private String playerName;
    private int levelNum;
    private int health;
    private int energy;
    private int remainingXP;

    //Inventory Info
    private ArrayList<Integer> playerInventory;

    //Player Tools
    String axe;
    String pickaxe;
    String sword;
    String hat;
    String shirt;
    String pants;
    boolean oven;
    String fishingRod;

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
    public void setPlayerInventory(ArrayList inventory){
        this.playerInventory = inventory;
    }

    //Creates the player stats
    public void createPlayer(String name, int levelNum, int health, int energy, int xp){
        this.playerName = name;
        this.levelNum = levelNum;
        this.health = health;
        this.energy = energy;
        this.remainingXP = xp;
        makeTools("None", "None","None","None","None",
                "None",false,"None");
    }

    //Inventory Adders
    public void addWood(int amt){
        int currentAmt = playerInventory.get(0);
        playerInventory.remove(0);
        playerInventory.add(0, currentAmt+amt);
        int newCurrent = playerInventory.get(0);
    }

    //Ugly Getters
    public ArrayList<Integer> getPlayerInventory() {return playerInventory;}
    public int getRemainingXP() {return remainingXP;}
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