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


public class PremiumUser extends User {

    // instance variable
    private String customTitle;

    /**
     * Constructor that initializes a user
     * @param username the user name
     * @param bio a string of short introduction
     */
    public PremiumUser(String username, String bio) {
        super(username, bio);
        this.customTitle = "Premium";
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
        for (int i = 0; i < log.size(); i++ ) {
            Message m = log.get(i);
            s += m.getContents();
            s += '\n';
            }
        return s;
    }

    /**
     * Create a new photo room
     * @param users an arraylist of users
     * @return cr the created photo room
     */
    public MessageExchange createPhotoRoom(ArrayList<User> users) {
        if (users == null) {
            throw new IllegalArgumentException();
        }
        PhotoRoom cr = new PhotoRoom();
        try {
            this.joinRoom(cr);
        } catch (OperationDeniedException ode) {
            System.out.println(ode.getMessage());
        }

        for (int i = 0; i < users.size(); i++){
            try {
                users.get(i).joinRoom(cr);
            } catch (OperationDeniedException ode) {
                System.out.println(ode.getMessage());
            }
        }
        
        return cr;
    }

    /**
     * Return a string of user name
     * @return a string of user name and title
     */
    public String displayName() {
        return "<"+ customTitle +"> "+username;  // placeholder for checkpoint test.
                                    // replace it with your own after checkpoint submission.
    }

    /**
     * set new title to title
     * @param newTitle a string of new title
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

}
