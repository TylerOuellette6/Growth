package models;

import datalayer.UserDao;

import java.util.ArrayList;

public class GameController {

    //Checks if the user died
    private static void checkDeath(UserModel user){
        if(user.getHealth() <= 0){
            user.addStringToOutputText(user.getPlayerName() + " has died.", user);
            user.addStringToOutputText("Press exit and start a new game.", user);
        }
    }

    //Check if the user is out of energy
    private static void checkExhuastion(UserModel user){
        if(user.getEnergy() == 0){
            user.addStringToOutputText("You're exhausted!", user);
            user.addStringToOutputText("Sleep or eat to restore energy.", user);
        }
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

            //Updates all of the user's information
            user.setCurrentXP(currentXP);
            user.setCurrentXPRequired(necessaryXP);
            user.setLevelNum(currentLevelNum);
            user.addStringToOutputText("Reached Level " + currentLevelNum, user);
        }
    }

    //Checks if the outputText array is too long and trims it if it is
    public static void checkOutputTextLength(UserModel user){
        ArrayList<String> oldOutputText = user.getOutputText();
        ArrayList<String> newOutputText;

        if(oldOutputText.size() == 6){
            oldOutputText.remove(0);
        }
        newOutputText = oldOutputText;
        user.resetOutputText(newOutputText);
    }

    //Helper method to reduce redundant code in checkAchievementCompletion
    private static void updateAchievementStats(UserModel user, String achievementName, int XPReceived){
        user.completeAchievement(achievementName);
        user.addStringToOutputText("Achievement Unlocked: " + achievementName + "(+ " + XPReceived + " XP)", user);
        user.setCurrentXP(user.getCurrentXP() + XPReceived);
    }

    //Checks if user crafted a tool that upgrades stats (all tools) and changes said stat
    private static void newToolStatsUpgrade(UserModel user, String toolName){
        int currentMaxHealth = user.getMaxHealth();

        if(toolName.equals("Basic Axe")){user.setWoodEnergyConsumption(user.getWoodEnergyConsumption() - 1);}
        else if(toolName.equals("Grass Hat")){user.setMaxHealth(currentMaxHealth + 3);}
        else if(toolName.equals("Grass Shirt")){user.setMaxHealth(currentMaxHealth + 5);}
        else if(toolName.equals("Grass Pants")){user.setMaxHealth(currentMaxHealth + 4);}
        else if(toolName.equals("Basic Pickaxe")){user.setStoneEnergyConsumption(user.getStoneEnergyConsumption() - 1);}
        else if(toolName.equals("Basic Sword")){user.setFightEnergyConsumption(user.getFightEnergyConsumption() - 2);}
        else if(toolName.equals("Copper Axe")){user.setWoodEnergyConsumption(user.getWoodEnergyConsumption() - 1);}
        else if(toolName.equals("Copper Pickaxe")){user.setStoneEnergyConsumption(user.getStoneEnergyConsumption() - 1);}
        else if(toolName.equals("Copper Sword")){user.setFightEnergyConsumption(user.getFightEnergyConsumption() - 1);}
        else if(toolName.equals("Basic Fishing Rod")){user.setFishEnergyConsumption(user.getFishEnergyConsumption() - 1);}
    }

    public static void buildStructure(UserModel user, String structure){
        if(structure.equals("Basic Shelter")){
            user.setMaxAttackValue(7);
            user.setMinAttackValue(2);
            user.setMaxEnergy(user.getMaxEnergy() + 15);
        }
        user.setEnergy(user.getEnergy() - 20);
        checkAchievementCompletion(user, structure);
    }

    //Handles when tools are crafted
    static public void craftTool(UserModel user, String item1, int item1ReqQty, String item2, int item2ReqQty,
                                 String newTool, String toolType){
        //Necessary info from player
        int item1CurQty = user.getInventoryItemAmt(item1);
        int item2CurQty = user.getInventoryItemAmt(item2);

        //Make sure the player has enough of the raw materials
        if(item1CurQty < item1ReqQty && item2CurQty <= item2ReqQty){
            user.addStringToOutputText("Insufficient Materials.", user);
            return;
        }
        else if(user.getToolByName(toolType).equals("Basic "+ toolType) && item2.equals("Copper Bars")){
            user.removeFromInventory(item1, item1ReqQty);
            user.removeFromInventory(item2, item2ReqQty);
            //Add new tool to the tool HashMap
            user.addTool(toolType, newTool);
        }
        else if (!(user.getToolByName(toolType)).equals("None")){return;}
        //If the player has enough materials, remove them from the inventory
        else{
            user.removeFromInventory(item1, item1ReqQty);
            user.removeFromInventory(item2, item2ReqQty);
            //Add new tool to the tool HashMap
            user.addTool(toolType, newTool);
        }

        //Adds output text to allow the user to know what happened
        user.addStringToOutputText("Crafted a(n) " + newTool + ".", user);
        //Update the player stats that are received from the new tool
        newToolStatsUpgrade(user, newTool);
        //Check if achievement is unlocked
        checkAchievementCompletion(user, "craft"); // This is for first time craftsman achievement
        checkAchievementCompletion(user, newTool); //This is for every other achievement
        //Save the new information
        UserDao.saveUser(user);
    }

    //Handles when inventory items need to be crafted
    static public void craftItem(UserModel user, String item1, int item1ReqQty,
                                 String newItem){
        //Necessary info from player
        int item1CurQty = user.getInventoryItemAmt(item1);

        //Make sure the player has enough of the raw materials
        if(item1CurQty < item1ReqQty){
            user.addStringToOutputText("Insufficient Materials.", user);
            return;
        }
        //If the player has enough materials, remove them from the inventory
        else{user.removeFromInventory(item1, item1ReqQty);}

        //Since all the current craftable items require the inventory,
        //Check if the user has an oven, and add the item if they do
        if(user.getToolByName("Oven").equals("Oven")){
            user.addToInventory(newItem, 1);
            user.addStringToOutputText("Crafted a(n) " + newItem + ".", user);
        }
        else {return;}

        //Check if achievement is unlocked
        checkAchievementCompletion(user, "craft"); // This is for first time craftsman achievement
        checkAchievementCompletion(user, newItem);
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
                if(AMorPM.equals("AM")){AMorPM = "PM";}
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

    //Generates a random number and returns an amount (either 1, 2, or 3)
    static public int randomCollectionGenerator(){
        double rand = Math.random();
        if(rand >= 0 && rand <= 0.34){return 1;} //34% chance for one item
        else if(rand > 0.34 && rand <= 0.67){return 2;} //33% chance for two items
        else{return 3;} //33% chance for three items
    }

    //When the user crafts the basic pickaxe, they can start to collect copper
    //This handles randomly deciding if the user finds copper
    private static void copperGenerator(UserModel user){
        double rand = Math.random();
        if(rand >= 0 && rand <= 0.5){
            user.addToInventory("Copper", 1);
            user.addStringToOutputText("You found copper!", user);
        }
        else{return;}
    }

    //When the user crafts the copper pickaxe, they can start to collect gold
    //This handles randomly deciding if the user finds gold
    private static void goldGenerator(UserModel user){
        double rand = Math.random();
        if(rand >= 0 && rand <= 0.5){
            user.addToInventory("Gold", 1);
            user.addStringToOutputText("You found gold!", user);
        }
        else{return;}
    }

    //Generates a random attack value when the user tries to perform an action between a certain time
    private static void attackGenerator(UserModel user){
        double rand = Math.random();
        double constantIncrementer = (double)(1.0/(user.getMaxAttackValue() - user.getMinAttackValue()));
        int attackValue = (int) user.getMinAttackValue();
        for(double i = 0; i<1; i += constantIncrementer){
            if(rand >= i && rand <= i+constantIncrementer){
                user.setHealth((int) (user.getHealth() - attackValue));
                user.addStringToOutputText("Attacked for " + attackValue + " health.", user);
                break;
            }
            else{attackValue++;}
        }
        checkAchievementCompletion(user, "Attack");
    }

    //Whenever an action is performed, the user's info needs to be updated
    //This method updates the user's energy, health, inventory, and XP if applicable
    static public void actionPerformed(UserModel user, String action, String item, int amtAdded, int lostEnergy, int time){
        //Get any variables that might be needed
        int currentEnergy = user.getEnergy();
        int currentXP = user.getCurrentXP();
        int currentHealth = user.getHealth();

        if(user.getEnergy() - lostEnergy <=0){
            user.addStringToOutputText("Insufficient Energy.", user);
            user.addStringToOutputText("Eat or sleep to restore energy.", user);
            return;
        }
        else{
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
            if(amtAdded > 0){user.addStringToOutputText("Gathered " + amtAdded + " " + item + ".", user);}

            //When the user crafts a better pickaxe, they have a random chance of finding certain ores
            if(action.equals("stones") && user.getToolByName("Pickaxe").equals("Basic Pickaxe")){
                copperGenerator(user);
            }
            else if(action.equals("stones") && user.getToolByName("Pickaxe").equals("Copper Pickaxe")){
                copperGenerator(user);
                goldGenerator(user);
            }

            //Only attack the user between 6PM and 9AM
            if((user.getHourNum() >= 6 && user.getAMorPM().equals("PM")) ||
                    (user.getHourNum() < 9 && user.getAMorPM().equals("AM"))
                    && !(action.equals("fight")) && !(action.equals("fightFinal"))){
                attackGenerator(user);
            }
            //Check if the player died or ran out of energy
            checkDeath(user);
            checkExhuastion(user);
            //Check if an achievement was unlocked
            checkAchievementCompletion(user, action);
            //Check if the user leveled up
            checkLevelUp(user);
            //Update the time
            updateTime(user, time);
        }
        //Finally, saves the user's info
        UserDao.saveUser(user);

    }

    //Handles when the user sleeps
    //This is separate from actionPerformed because it has to update so many of the player's stats
    static public void sleep(UserModel user){
        //Resets health and energy to max, adds 1 to day num, and sets time to 9AM
        user.setHealth(user.getMaxHealth());
        user.setEnergy(user.getMaxEnergy());
        user.setDayNum(user.getDayNum() + 1);
        user.setHourNum(9);
        user.setMinuteNum("00");
        user.setAMorPM("AM");

        //Outputs text to user knows what hapepned
        user.addStringToOutputText("Slept.", user);
        //Saves new info
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

        //Check if an achievement is unlocked
        checkAchievementCompletion(user, "eat");
        //Doesn't allow user to have more energy than their current max
        if(newEnergy > user.getMaxEnergy()){newEnergy = user.getMaxEnergy();}
        //Doesn't allow user to have more health than their current max
        if(newHealth > user.getMaxHealth()){newHealth = user.getMaxHealth();}
        //Adds output text to allow the user to know what happened
        user.addStringToOutputText("Ate a(n) " + food + ".", user);
        //Sets the user's variables with the new info
        user.setEnergy(newEnergy);
        user.setHealth(newHealth);
        //Finally, saves the user's info
        UserDao.saveUser(user);
    }

    //Check is a certain achievement was completed
    //Goes through every possible option
    public static void checkAchievementCompletion(UserModel user, String taskCompleted){
        //Bunch of if statements checking taskCompleted and boolean of achievementsList
        if(taskCompleted.equals("wood") && user.getAchievementByName("First Time Lumberjack") == false){updateAchievementStats(user, "First Time Lumberjack", 6);}
        if(taskCompleted.equals("craft") && user.getAchievementByName("First Time Craftsman") == false){updateAchievementStats(user, "First Time Craftsman", 8);}
        if(taskCompleted.equals("apples") && user.getAchievementByName("First Time Farmer") == false){updateAchievementStats(user, "First Time Farmer", 8);}
        if(taskCompleted.equals("eat") && user.getAchievementByName("First Time Eater") == false){updateAchievementStats(user, "First Time Eater", 8);}
        if(taskCompleted.equals("Basic Axe") && user.getAchievementByName("Basic Axe") == false){updateAchievementStats(user, "Basic Axe", 8);}
        if((taskCompleted.equals("Grass Shirt") || taskCompleted.equals("Grass Hat") || taskCompleted.equals("Grass Pants"))
                && user.getAchievementByName("Getting Dressed") == false){updateAchievementStats(user, "Getting Dressed", 10);}
        if((user.getToolByName("Shirt").equals("Grass Shirt") && user.getToolByName("Hat").equals("Grass Hat")
                && user.getToolByName("Pants").equals("Grass Pants")) && user.getAchievementByName("Fully Dressed") == false)
        {updateAchievementStats(user, "Fully Dressed", 30);}
        if(taskCompleted.equals("stones") && user.getAchievementByName("First Time Miner") == false){updateAchievementStats(user, "First Time Miner", 10);}
        if(taskCompleted.equals("Basic Pickaxe") && user.getAchievementByName("Basic Pickaxe") == false){updateAchievementStats(user, "Basic Pickaxe", 10);}
        if(taskCompleted.equals("Attack") && user.getAchievementByName("Attacked!") == false){updateAchievementStats(user, "Attacked!", 20);}
        if(taskCompleted.equals("Basic Sword") && user.getAchievementByName("Basic Sword") == false){updateAchievementStats(user, "Basic Sword", 20);}
        if(taskCompleted.equals("Copper Bars") && user.getAchievementByName("First Time Smelter") == false){updateAchievementStats(user, "First Time Smelter", 30);}
        if(taskCompleted.equals("fightFinal") && user.getAchievementByName("First Time Fighter") == false){updateAchievementStats(user, "First Time Fighter", 30);}
        if(taskCompleted.equals("Baked Apples") && user.getAchievementByName("First Time Chef") == false){updateAchievementStats(user, "First Time Chef", 20);}
        if((taskCompleted.equals("Copper Axe")) || (taskCompleted.equals("Copper Pickaxe")) || (taskCompleted.equals("Copper Sword"))
                && user.getAchievementByName("Tool Upgrade") == false){updateAchievementStats(user, "Tool Upgrade", 40);}
        if ((user.getToolByName("Pickaxe").equals("Copper Pickaxe") && user.getToolByName("Axe").equals("Copper Axe")
                && user.getToolByName("Sword").equals("Copper Sword")) && user.getAchievementByName("Full Tool Set Upgrade") == false){
            updateAchievementStats(user, "Full Tool Set Upgrade", 120);}
        if(user.getInventoryItemAmt("Gold") >= 1){updateAchievementStats(user, "Gold!", 30);}
        if(taskCompleted.equals("fish") && user.getAchievementByName("First Time Fisherman") == false){updateAchievementStats(user, "First Time Fisherman", 30);}
        if(taskCompleted.equals("Basic Fishing Rod") && user.getAchievementByName("New Rod") == false){updateAchievementStats(user, "New Rod", 50);}
        if(taskCompleted.equals("Fish and Apples") && user.getAchievementByName("Professional Chef") == false){updateAchievementStats(user, "Professional Chef", 50);}
        if(taskCompleted.equals("Basic Structure") && user.getAchievementByName("First Time Builder") == false){updateAchievementStats(user, "First Time Builder", 200);}
        if(user.getLevelNum() == 10 && user.getCurrentXP() >=400){updateAchievementStats(user, "The End?", 0);}

        //Save the users new information
        UserDao.saveUser(user);
    }

}

