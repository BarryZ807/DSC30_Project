/* 
 * NAME: Zehui Zhang
 * PID: A16151490
 */
import java.util.ArrayList;
/**
 * Photo Message
 * @author Zehui Zhang
 * @since  2021/01/22
 */

public class PhotoRoom implements MessageExchange {

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;

    /**
     * Constructor of photo room
     */
    public PhotoRoom() {
        this.users = new ArrayList<User>();
        this.log = new ArrayList<Message>();
    }

    /**
     * get log of current room
     * @return the log of current room
     */
    public ArrayList<Message> getLog() {
        return log;
    }

    /**
     * add a user to room
     * @param u a user
     * @return boolean indicating whether the user has been added to photo room 
     */
    public boolean addUser(User u) {
        if (u instanceof PremiumUser) {
                users.add(u);
                return true;
        }
        return false;
    }

    /**
     * remove a user of chat room
     * @param u a user
     */
    public void removeUser(User u) {
        users.remove(u);
    }

    /**
     * get users of current room
     * @return the arraylist of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * record the given message to current room
     * @param m a message
     * @return a boolean indicating whether the message is added 
     */
    public boolean recordMessage(Message m) {
        if (m instanceof PhotoMessage) {
            log.add(m);
            return true;
        }
        return false;
    }

}
