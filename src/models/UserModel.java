package models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable {
    private String playerName;
    private int levelNum;
    private int health;
    private int energy;
    private int currentXP;
    private ArrayList<Integer> inventory;

    String axe;
    String pickaxe;
    String sword;
    String hat;
    String shirt;
    String pants;
    boolean oven;
    String fishingRod;

    public void setInventory(ArrayList<Integer> inventory) {this.inventory = inventory; }

    public int getCurrentXP() {return currentXP;}

    public void setCurrentXP(int remainingXP) {this.currentXP = remainingXP; }

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public int getEnergy() {return energy;}

    public void setEnergy(int energy) {this.energy = energy;}

    public int getLevelNum() {return levelNum;}

    public void setLevelNum(int levelNum) {this.levelNum = levelNum;}

    public String getPlayerName() {return playerName;}

    public void setPlayerName(String playerName) {this.playerName = playerName;}
}