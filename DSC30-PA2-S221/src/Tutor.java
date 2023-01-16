import java.util.ArrayList;

public class Tutor extends User {

    // instance variable
    private String customTitle;

    /**
     * Constructor that initializes a tutor
     * @param username the user name
     * @param bio a string of short introduction
     */
    public Tutor(String username, String bio) {
        super(username,bio);
        this.customTitle = "Tutor";
    }

    /**
     * fetch messages of a chatroom
     * @param me a given MessageExchange
     * @return finalOutput a string of messages
     */
    public String fetchMessage(MessageExchange me) {
        if (me == null){
            throw new IllegalArgumentException();
        }
        if (!(me.getUsers().contains(this))){
            throw new IllegalArgumentException();
        }
        ArrayList<Message> log = me.getLog(this);
        StringBuilder output = new StringBuilder("");
        /*
        Creates the message using a for loop.
         */
        for (int i = 0; i < log.size(); i++){
            output.append(log.get(i).getContents() + "\n");
        }
        String finalOutput = output.toString();
        return finalOutput;
    }

    /**
     * display the tutor's name
     */
    public String displayName() {
        return "<" + customTitle + "> " + username;
    }

    /**
     * set the tutor's new title
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    /**
     * create a new tutor
     * @param users an arraylist of users
     * @return cr a newly created tutor
     */
    public MessageExchange createAutograder(ArrayList<User> users) {
        if (users == null){
            throw new IllegalArgumentException();
        }
        Autograder  autograder = new Autograder (this);
        // to throw exception by try-catch
        try {
            this.joinRoom(autograder);
        } catch (Exception e) {
            e.getMessage();
        }

        for (int i = 0; i < users.size(); i++) {
            try {
                users.get(i).joinRoom(autograder);
            } catch (Exception e) {
                e.getMessage();
                continue;
            }
        }

        return autograder;
    }

}
