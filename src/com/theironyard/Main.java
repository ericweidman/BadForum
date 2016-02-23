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
                        Message message = new Message(text);
                        user.messages.add(message);
                        response.redirect("/");
                    } else {
                        response.redirect("/");
                    }
                    return "";
                })
        );

    }
}