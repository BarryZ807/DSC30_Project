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

public class ChatRoom implements MessageExchange {

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;

    /**
     * Constructor of chat room
     */
    public ChatRoom() {
        this.users = new ArrayList<User>();
        this.log = new ArrayList<Message>();
    }

    /**
     * get log of current chat room
     * @return the log of current chatroom
     */
    public ArrayList<Message> getLog() {
        return log;
    }

    /**
     * add a user to chat room
     * 
     * @param u a user
     * @return true
     * @throws OperationDeniedException
     */
    public boolean addUser(User u){
        users.add(u);
        return true;
    }

    /**
     * remove a user of chat room
     * @param u a user
     */
    public void removeUser(User u) {
        users.remove(u);
    }

    /**
     * get users of current chatroom
     * @return the arraylist of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * record the given message to current chat room
     * @param m a message
     * @return true
     */
    public boolean recordMessage(Message m) {
        log.add(m);
        return true;
    }


}