package com.theironyard;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.HashMap;

public class Main {

    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();


        Spark.get(
                "/",
                ((request, response) -> {
                    User user = getUserFromSession(request.session());
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
                "/createUser",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    String pass = request.queryParams("userPass");
                    User user = users.get(name);
                    if (user == null) {
                        user = new User(name, pass);
                        users.put(name, user);
                    }
                    Session session = request.session();
                    session.attribute("loginName", name);
                    if(pass.equals(user.password)) {
                        response.redirect("/");
                    }else{
                        Spark.halt(403);
                    }
                        return "";

                })
        );
        Spark.post(
                "/messages",
                ((request, response) -> {
                    User user = getUserFromSession(request.session());
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
        Spark.post(
                "/delete",
                ((request, response) -> {
                    User user = getUserFromSession(request.session());
                    int number = Integer.valueOf(request.queryParams("userDelete"));
                    user.messages.remove(number - 1);
                    response.redirect("/");
                    return "";
                })

        );
        Spark.post(
                "/edit",
                ((request, response) -> {
                    User user = getUserFromSession(request.session());
                    int number = Integer.valueOf(request.queryParams("userSelectEdit"));
                    user.messages.remove(number - 1);
                    String editPut = request.queryParams("userEdit");
                    user.messages.add(number - 1, editPut);
                    response.redirect("/");
                    return "";
                })

        );
    }
    static User getUserFromSession(Session session){
        String name = session.attribute("loginName");
        return users.get(name);
    }
}
