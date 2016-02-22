package com.theironyard;

import spark.ModelAndView;
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
                    if(user == null) {
                        return new ModelAndView(m, "index.html");
                    }else {
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
                    } else{
                        response.redirect("/");
                    }
                    return "";
                })
    );
        Spark.post(
                "/messages",
                ((request, response) -> {
                    String text = request.queryParams("userInput");
                    if(!text.equals("")) {
                        Message message = new Message(text);
                        user.messages.add(message);
                        response.redirect("/");
                    }else {
                        response.redirect("/");
                    }
                    return"";
                })
        );

    }
}






//        Create resources/templates/index.html which looks like in the first screenshot below.

//        Create a GET route for "/messages" and a POST route for "/create-user" and "/create-text".

//        When the user hits submit in "index.html", it should post the name to "/create-user"
//        which saves it in a user object and redirects to "/messages".

//        Create resources/templates/messages.html which looks like the second screenshot below.
//        It must display the name given by the user, and use Mustache templating
//        to list the messages written by the user.

//        When the user hits submit in "messages.html", it should post the text to "/create-text"
//        which saves it in an ArrayList<com.theironyard.Message> and redirects to "/messages" (i.e. refreshes the page).
//        like usual; if it does exist, then check the password and return an error if it is wrong.

//        Optional: In "index.html", add a password field. If the user doesn't exist, have it behave
//        Optional: Add styling with CSS.