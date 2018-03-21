package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable{
    int wood;
    int grass;
    int apples;
    int stone;

    public int getWood() {
        return wood;
    }

    public int getGrass() {
        return grass;
    }

    public int getApples() {
        return apples;
    }

    public int getStone() {
        return stone;
    }

    public int getCopper() {
        return copper;
    }

    public int getCopperBars() {
        return copperBars;
    }

    public int getBakedApples() {
        return bakedApples;
    }

    public int getGold() {
        return gold;
    }

    public int getGoldBars() {
        return goldBars;
    }

    public int getFish() {
        return fish;
    }

    public int getFishAndApples() {
        return fishAndApples;
    }

    int copper;
    int copperBars;
    int bakedApples;
    int gold;
    int goldBars;
    int fish;
    int fishAndApples;

    public void setInventory(ArrayList inventory){
        this.wood = (int)inventory.get(0);
        this.grass = (int)inventory.get(1);
        this.apples = (int)inventory.get(2);
        this.stone = (int)inventory.get(3);
        this.copper = (int)inventory.get(4);
        this.copperBars = (int)inventory.get(5);
        this.bakedApples = (int)inventory.get(6);
        this.gold = (int)inventory.get(7);
        this.goldBars = (int)inventory.get(8);
        this.fish = (int)inventory.get(9);
        this.fishAndApples = (int)inventory.get(10);

    }

}
