package com.theironyard;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.HashMap;

public class Main {

    static User user;

    public static void main(String[] args) {
        Spark.init();


        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "index.html");
                    } else {
                        m.put("name", user.name);
                        m.put("messages", user.messages);
                        return new ModelAndView(m, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/index",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    if (!name.equals("")) {
                        user = new User(name);
                        response.redirect("/");
                    } else {
                        response.redirect("/");
                    }
                    return "";
                })
        );
        Spark.post(
                "/messages",
                ((request, response) -> {
                    String text = request.queryParams("userInput");
                    if (!text.equals("")) {
                        user.messages.add(text);
                        response.redirect("/");
                    } else {
                        response.redirect("/");
                    }
                    return "";
                })
        );
        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );
    }
}

//
//
//        Add a password field to the login form. If the user doesn't exist, create a new user
//        and store the password in the User object. If the user does exists, check the password and,
//        if it's wrong, don't let them log in (you can decide the details for yourself).

//        Add multi-user support by storing your users in a HashMap<String, User>
//        and putting your ArrayList<Message> inside the User object.

//        In the /create-user route, save the username into the session.

//        In the / route, get the username out of the session and subsequently get your User object.

//        Show a logout button when the user is logged in. It should invalidate
//        the session and refresh the page so you can log in again with a new user.

//        Add a form in messages.html which lets you delete a message by entering its number.

//        Add a form in messages.html which lets you edit a message
//        by entering its number and the text you want to replace it with.


//        Optional: Make the microblog persist data on the disk by encoding
//        the data as JSON in each POST route, and decoding it when the web app first runs.
