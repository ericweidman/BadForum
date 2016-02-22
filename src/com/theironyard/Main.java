package com.theironyard;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}






//        Create resources/templates/index.html which looks like in the first screenshot below.

//        Create a GET route for "/messages" and a POST route for "/create-user" and "/create-message".

//        When the user hits submit in "index.html", it should post the name to "/create-user"
//        which saves it in a user object and redirects to "/messages".

//        Create resources/templates/messages.html which looks like the second screenshot below.
//        It must display the name given by the user, and use Mustache templating
//        to list the messages written by the user.

//        When the user hits submit in "messages.html", it should post the text to "/create-message"
//        which saves it in an ArrayList<Message> and redirects to "/messages" (i.e. refreshes the page).
//        like usual; if it does exist, then check the password and return an error if it is wrong.

//        Optional: In "index.html", add a password field. If the user doesn't exist, have it behave
//        Optional: Add styling with CSS.