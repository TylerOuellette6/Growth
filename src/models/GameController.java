package models;

public class GameController {
    static public int randomCollectionGenerator(){
        double rand = Math.random();
        if(rand >= 0 && rand <= 0.34){
           return 1;
        }
        else if(rand > 0.34 && rand <= 0.67){
            return 2;
        }
        else{
            return 3;
        }
    }
}
