package com.theironyard;

/**
 * Created by ericweidman on 2/22/16.
 */
public class User {
    String name;
    String userText;
    String userPassword;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String userText) {
        this.name = name;
        this.userText = userText;
    }
}
