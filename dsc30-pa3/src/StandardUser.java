/* 
 * NAME: Zehui Zhang
 * PID: A16151490
 */
import java.util.ArrayList;
import java.util.List;
/**
 * Photo Message
 * @author Zehui Zhang
 * @since  2021/01/22
 */


public class StandardUser extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";
    private static final int MAXIMUM_MESSAGE_NUMBER_CAN_BE_FETCHED = 100;

    /**
     * Constructor that initializes a user
     * @param username the user name
     * @param bio a string of short introduction
     */
    public StandardUser(String username, String bio) {
        super(username, bio);
    }

    /**
     * fetch messages of a chatroom
     * @param me a given chatroom or photoroom
     * @return s a string of messages
     */
    public String fetchMessage(MessageExchange me) {
        if (!this.rooms.contains(me) || me == null) {
            throw new IllegalArgumentException();
        }
        String s = "";
        ArrayList<Message> log = me.getLog();
        int min = Math.min(MAXIMUM_MESSAGE_NUMBER_CAN_BE_FETCHED, log.size());

        if (min == MAXIMUM_MESSAGE_NUMBER_CAN_BE_FETCHED) {
            for (int i = log.size() - MAXIMUM_MESSAGE_NUMBER_CAN_BE_FETCHED; i < log.size(); i++ ) {
                Message m = log.get(i);
                if (m instanceof TextMessage) {
                    s += m.getContents();
                    s += '\n';
                } else if (m instanceof PhotoMessage || m instanceof StickerMessage) {
                    s += FETCH_DENIED_MSG;
                    s += '\n';
                }
            }
        } else if (min == log.size()) {
            for (int i = 0; i < log.size(); i++ ) {
                Message m = log.get(i);
                if (m instanceof TextMessage) {
                    s += m.getContents();
                    s += '\n';
                } else if (m instanceof PhotoMessage || m instanceof StickerMessage) {
                    s += FETCH_DENIED_MSG;
                    s += '\n';
                }
            }
        }
        return s;
    }

    /**
     * Return a string of user name
     * @return a string of user name
     */
    public String displayName() {
        return this.username;  // placeholder for checkpoint test.
                               // replace it with your own after checkpoint submission.
    }
}
