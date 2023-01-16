import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    /**
     * Method to call super class with inputs
     * @param username string of user's name
     * @param bio string of user's bio
     */
    public Student(String username, String bio) {
        super(username,bio);
    }

    /**
     * fetch messages of a student
     * @param me a given Message room
     * @return s a string of messages
     */
    public String fetchMessage(MessageExchange me) {
        // Check for exceptions
        if (me == null){
            throw new IllegalArgumentException();
        }
        if (!(me.getUsers().contains(this))){
            throw new IllegalArgumentException();
        }
        String s = ""; // create an empty string
        ArrayList<Message> log = me.getLog(this);
        int min = Math.min(MAX_MSG_SIZE, log.size());

        //condition of max message size
        if (min == MAX_MSG_SIZE) {
            for (int i = log.size() - MAX_MSG_SIZE; i < log.size(); i++ ) {
                Message m = log.get(i);
                if (m instanceof TextMessage) {
                    s += m.getContents();
                    s += '\n';
                } else {
                    s += FETCH_DENIED_MSG;
                    s += '\n';
                }
            }
        } else if (min == log.size()) { // condition of log size
            for (int i = 0; i < log.size(); i++ ) {
                Message m = log.get(i);
                if (m instanceof TextMessage) {
                    s += m.getContents();
                    s += '\n';
                } else  {
                    s += FETCH_DENIED_MSG;
                    s += '\n';
                }
            }
        }
        return s;
    }

    /**
     * Return the username
     * @return the username
     */
    public String displayName() {
        return username;
    }
}
