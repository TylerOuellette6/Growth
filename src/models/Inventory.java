package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable{
    int wood;
    int grass;
    int apples;
    int stone;
    int copper;
    int copperBars;
    int bakedApples;
    int gold;
    int goldBars;
    int fish;
    int fishAndApples;

    public void setInventory(ArrayList inventory){
        this.wood = (int) inventory.get(0);
        this.grass = (int) inventory.get(1);
        this.apples = (int) inventory.get(2);
        this.stone = (int) inventory.get(3);
        this.copper = (int) inventory.get(4);
        this.copperBars = (int) inventory.get(1);

    }

}
