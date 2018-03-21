package datalayer;

import models.StoryModel;
import models.UserModel;

import java.io.*;
import java.util.ArrayList;

public class UserDao {

    /**
     * Given a username, return the user model.
     * Returns null, if no such user.
     */
    public static UserModel getUser(String username) {
        if (username == null) {
            return null;
        }

        File file = new File(getFilePath(username));
        return getUser(file);
    }

    /*
     * Given a user, delete it from storage.
     */
    public static void deleteUser(String username) {
        File file = new File(getFilePath(username));
        file.delete();
    }

    /*
     * Save the given user model.
     */
    public static void saveUser(UserModel userModel){
        try {
            File file = new File(getFilePath(userModel.getPlayerName()));
            file.createNewFile();
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userModel);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a story ID, where are we saving it to storage (file name)?
     */
    private static String getFilePath(String username) {
        return DaoUtils.storageDirectoryName() + File.separator + "user" + username + ".txt";
    }

    /*
     * Given a username, return the user that's saved in the file.
     * Returns null if not found.
     */
    private static UserModel getUser(File file) {
        UserModel user = null;
        try {
            user = new UserModel();

            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                user = (UserModel) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }

        return user;
    }

    /**
     * Unit test program.
     *
     * @param args
     */
    public static void main(String[] args) {
        testUserDao();
    }

    private static void testUserDao() {
        String playerName = "Tyler";
        int levelNum = 1;
        int health = 50;
        int energy = 50;

        UserDao dao = new UserDao();
        UserModel user = new UserModel();
        user.createPlayer("Tyler", 0,0,0,0);
        dao.saveUser(user);

        user = dao.getUser(playerName);
        assert(user != null);
        assert(user.getPlayerName().compareTo(playerName) == 0);
        assert(user.getLevelNum() == levelNum);
        assert(user.getHealth() == health);
        assert(user.getEnergy() == energy);

        System.out.println("Name " + playerName);
        System.out.println(user.getPlayerName());
        System.out.println("Level " + levelNum);
        System.out.println(user.getLevelNum());
        System.out.println("Health " + health);
        System.out.println(user.getHealth());
        System.out.println("Energy " + energy);
        System.out.println(user.getEnergy());

        dao.deleteUser(playerName);
    }

}
