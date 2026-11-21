package edu.utsa.cs3443.foundtime.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private int userID;
    private static User currentUser;

    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }
    private void setUserID(int userID) {
        this.userID = userID;
    }


    //Finds the user in the users.csv file.
    public static User authenticate(String username, String password){
        String filePath = "data/users.csv";

        try {
            //username, password, userid
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            scanner.nextLine(); //Throws out the first row
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                String fileUsername = data[0].trim();
                String filePassword = data[1].trim();
                int fileUserID = Integer.parseInt(data[2].trim());

                if (fileUsername.equals(username) && filePassword.equals(password)) {
                    return new User(
                            fileUsername,
                            filePassword,
                            fileUserID
                    );
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Details: " + e.getMessage());
        }

        return null;
    }

    public static boolean findUsername(String username){
        String filePath = "data/users.csv";

        try {
            //username, password, userid
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            scanner.nextLine(); //Throws out the first row
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                String fileUsername = data[0].trim();

                if (fileUsername.equals(username)) {
                    return true; //if it was true then we found the user in the users.csv
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Details: " + e.getMessage());
        }

        return false;
    }

    public static void addUser(User currUser){
        String filePath = "data/users.csv";

        ArrayList<User> users = new ArrayList<>();
        try {
            //username, password, userid
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            scanner.nextLine(); //Throws out the first row
            //Grabs all current users in the file.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                users.add(lineToUser(line));
            }
            users.add(currUser);
            scanner.close();
        } catch (IOException e) {
            System.err.println("Details: " + e.getMessage());
        }

        try{
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write("username,password,userID\n");
            for (User user : users){
                fileWriter.write(userToLine(user));
            }
            fileWriter.close();
        }
        catch (IOException e){
            System.err.println("Details: " + e.getMessage());
        }

    }

    private static String userToLine(User user){
        if(user == null){
            return null;
        }

        return (user.getUsername() + "," + user.getPassword() + "," + user.getUserID() + "\n");
    }

    private static User lineToUser(String line){
        if(line.trim().isEmpty()){
            return null;
        }

        String [] data = line.split(",");

        return new User(data[0].trim(), data[1].trim(), Integer.parseInt(data[2].trim()));
    }

    public static void setCurrentUser(User user){
        if (user != null){
            currentUser = user;
        }
    }
    public static User getCurrentUser() {return currentUser; }


    //TODO: Some formatted string to display the users information
    @Override
    public String toString() {
        return "PLACEHOLDER";
    }
}
