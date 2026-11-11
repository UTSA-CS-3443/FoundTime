package edu.utsa.cs3443.foundtime.model;

public class User {
    private String username;
    private String password;
    private int userID;

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

    //TODO: Some formatted string to display the users information
    @Override
    public String toString() {
        return "PLACEHOLDER";
    }
}
