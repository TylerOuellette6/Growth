package models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable {
    private String playerName;
    private int levelNum;
    private ArrayList inventory;

    public ArrayList getInventory() {return inventory;}

    public void setInventory(ArrayList inventory) {this.inventory = inventory;}

    public int getLevelNum() {return levelNum;}

    public void setLevelNum(int levelNum) {this.levelNum = levelNum;}

    public String getPlayerName() {return playerName;}

    public void setPlayerName(String playerName) {this.playerName = playerName;}
}