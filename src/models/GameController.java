package models;

import datalayer.UserDao;

import java.util.ArrayList;

public class GameController {

    //Checks if the outputText array is too long and trims it if it is
    static public void checkOutputTextLength(UserModel user){
        ArrayList<String> oldOutputText = user.getOutputText();
        ArrayList<String> newOutputText;

        if(oldOutputText.size() == 6){
            oldOutputText.remove(0);
            newOutputText = oldOutputText;
        }
        else{
            newOutputText = oldOutputText;
        }

        user.resetOutputText(newOutputText);
    }

    //Handles when tools are crafted
    static public void craftTool(UserModel user, String item1, int item1ReqQty, String item2, int item2ReqQty,
                                 String newTool, String toolType){
        //Necessary info from player
        int item1CurQty = user.getInventoryItemAmt(item1);
        int item2CurQty = user.getInventoryItemAmt(item2);

        //Make sure the player has enough of the raw materials
        if(item1CurQty < item1ReqQty && item2CurQty < item2ReqQty){return;}
        //If the player has enough materials, remove them from the inventory
        else{
            user.removeFromInventory(item1, item1ReqQty);
            user.removeFromInventory(item2, item2ReqQty);
        }

        //Adds output text to allow the user to know what happened
        user.addStringToOutputText("Crafted a(n) " + newTool + ".");
        GameController.checkOutputTextLength(user);

        //Add new tool to the tool HashMap
        user.addTool(toolType, newTool);

        //Save the new information
        UserDao.saveUser(user);

    }

    //Handles when inventory items need to be crafted
    static public void craftItem(UserModel user, String item1, int item1ReqQty,
                                 String newItem){
        //Necessary info from player
        int item1CurQty = user.getInventoryItemAmt(item1);

        //Make sure the player has enough of the raw materials
        if(item1CurQty < item1ReqQty){return; }
        //If the player has enough materials, remove them from the inventory
        else{user.removeFromInventory(item1, item1ReqQty);}

        //Since all the current craftable items require the inventory,
        //Check if the user has an oven, and add the item if they do
        if(!(user.getToolByName("Oven").equals("Oven"))){return;}
        else {user.addToInventory(newItem, 1);}

        //Adds output text to allow the user to know what happened
        user.addStringToOutputText("Crafted a(n) " + newItem + ".");
        GameController.checkOutputTextLength(user);

        //Save the new information
        UserDao.saveUser(user);
    }

    //Handles updating the time
    //This function accounts for "hour rollover" (i.e. "1:60" -> 2:00)
    //This function also accounts for "12-to-1" rollover
    static private void updateTime(UserModel user, int addedTime){
        //Necessary Info from player
        int currentHourAmt = user.getHourNum();
        String tempMinAmt = user.getMinuteNum();
        int currentMinuteAmount = Integer.valueOf(tempMinAmt);
        int dayNum = user.getDayNum();
        String AMorPM = user.getAMorPM();

        //Actually updates the time
        currentMinuteAmount += addedTime;

        //Loop to check if the minute amount is >60 or if hour amount is >=13
        do{
            if(currentMinuteAmount >= 60){
                //Subtracts 60 minutes, adds 1 hour
                currentMinuteAmount -= 60;
                currentHourAmt += 1;
            }

            if(currentHourAmt >= 13){
                currentHourAmt -= 12;
                if(AMorPM.equals("AM")){
                    AMorPM = "PM";
                }
                else if(AMorPM.equals("PM")){
                    AMorPM = "AM";
                    dayNum += 1;
                }
            }
        }while (currentMinuteAmount >= 60);

        //Converts new minute amount to string in order to display nicely on the page
        if(currentMinuteAmount == 0){tempMinAmt = String.valueOf(currentMinuteAmount + "0");}
        else{tempMinAmt = String.valueOf(currentMinuteAmount);}

        //Set all the user's information to the new values
        user.setMinuteNum(tempMinAmt);
        user.setHourNum(currentHourAmt);
        user.setAMorPM(AMorPM);
        user.setDayNum(dayNum);
    }

    //Checks if the user has enough XP to level up
    static private void checkLevelUp(UserModel user){
        //Necessary information from the user
        int currentXP = user.getCurrentXP();
        int necessaryXP = user.getCurrentXPRequired();
        int currentLevelNum = user.getLevelNum();

        //If the user has more XP than the required amount for the level
        //Accounts for rollover XP, adds 1 to the levelNum, and sets the new required XP
        if(currentXP >= necessaryXP){
            int rolloverXP = currentXP - necessaryXP;
            currentXP = rolloverXP;
            currentLevelNum += 1;
            if(currentLevelNum == 2){necessaryXP = 20;}
            else if(currentLevelNum == 3){necessaryXP = 40;}
            else if(currentLevelNum == 4){necessaryXP = 60;}
            else if(currentLevelNum == 5){necessaryXP = 80;}
            else if(currentLevelNum == 6){necessaryXP = 100;}
            else if(currentLevelNum == 7){necessaryXP = 120;}
            else if(currentLevelNum == 8){necessaryXP = 150;}
            else if(currentLevelNum == 9){necessaryXP = 200;}
            else if(currentLevelNum == 10){necessaryXP = 400;}
        }

        //Updates all of the user's information
        user.setCurrentXP(currentXP);
        user.setCurrentXPRequired(necessaryXP);
        user.setLevelNum(currentLevelNum);
    }

    //Generates a random number and returns an amount (either 1, 2, or 3)
    static public int randomCollectionGenerator(){
        double rand = Math.random();
        if(rand >= 0 && rand <= 0.34){return 1;} //34% chance for one item
        else if(rand > 0.34 && rand <= 0.67){return 2;} //33% chance for two items
        else{return 3;} //33% chance for three items
    }

    //Whenever an action is performed, the user's info needs to be updated
    //This method updates the user's energy, health, inventory, and XP if applicable
    static public void actionPerformed(UserModel user, String action, String item, int amtAdded, int lostEnergy, int time){
        //Get any variables that might be needed
        int currentEnergy = user.getEnergy();
        int currentXP = user.getCurrentXP();
        int currentHealth = user.getHealth();

        //Update the inventory with the new items
        user.addToInventory(item, amtAdded);

        //Update the energy after performing the action
        user.setEnergy(currentEnergy - lostEnergy);

        //Only give the user XP if they get 3 of an item and the action wasn't fighting
        if(amtAdded == 3 && !(action.equals("fight"))){user.setCurrentXP(currentXP + 1);}

        //Fighting has slightly different mechanics than the other actions
        //It always adds 6XP, still costs energy, and always costs 10 health
        if(action.equals("fightFinal")){
            user.setCurrentXP(currentXP + 6);
            user.setEnergy(currentEnergy - lostEnergy);
            user.setHealth(currentHealth - 10);
        }

        //Adds output text to allow the user to know what happened
        user.addStringToOutputText("Gathered " + amtAdded + " " + item + ".");
        GameController.checkOutputTextLength(user);

        //Check if the user leveled up
        checkLevelUp(user);

        //Update the time
        updateTime(user, time);

        //Finally, saves the user's info
        UserDao.saveUser(user);
    }

    //Handles when the user eats something
    static public void eat(UserModel user, String food){
        //Get any variables that might be needed
        int currentEnergy = user.getEnergy();
        int currentHealth = user.getHealth();
        int foodAmt = user.getInventoryItemAmt(food);

        //New variables that will be needed
        int newEnergy = 0;
        int newHealth = 0;

        //You can't eat food if there is none
        if(foodAmt == 0 || (currentHealth == 50 && currentEnergy == 50)){return;}//THIS DOESN'T WORK
        else{
            if(food.equals("Apples")){
                newHealth = currentHealth + 3;
                newEnergy = currentEnergy + 3;
            }
            else if(food.equals("Baked Apples")){
                newHealth = currentHealth + 5;
                newEnergy = currentEnergy + 5;
            }
            else if(food.equals("Fish")){
                newHealth = currentHealth + 7;
                newEnergy = currentEnergy + 7;
            }
            else if(food.equals("Fish and Apples")){
                newHealth = currentHealth + 12;
                newEnergy = currentEnergy + 12;
            }
            user.removeFromInventory(food, 1);
        }

        //Doesn't allow user to have more energy than their current max
        if(newEnergy > user.getMaxEnergy()){newEnergy = user.getMaxEnergy();}

        //Doesn't allow user to have more health than their current max
        if(newHealth > user.getMaxHealth()){newHealth = user.getMaxHealth();}

        //Adds output text to allow the user to know what happened
        user.addStringToOutputText("Ate a(n) " + food + ".");
        GameController.checkOutputTextLength(user);

        //Sets the user's variables with the new info
        user.setEnergy(newEnergy);
        user.setHealth(newHealth);

        //Finally, saves the user's info
        UserDao.saveUser(user);
    }
}
