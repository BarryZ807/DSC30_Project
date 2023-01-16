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

public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED = "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE = "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    /**
     * Constructor that initializes a user
     * @param username the user name
     * @param bio a string of short introduction
     */
    public User(String username, String bio) {
        if (username == null || bio == null) {
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.bio = bio;
        this.rooms = new ArrayList<MessageExchange>();
    }

    /**
     * set new Bio as the bio
     */
    public void setBio(String newBio) {
        if (newBio == null) {
            throw new IllegalArgumentException();
        } else {
            this.bio = newBio;
        }
    }

    /**
     * display the current user's bio
     */
    public String displayBio() {
        return bio;
    }

    /**
     * join a given room
     * @param me a chatroom or a photoroom
     */
    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null) {
            throw new IllegalArgumentException();
        } 
        if (!me.addUser(this) || rooms.contains(me)) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        rooms.add(me);
    }

    /**
     * quit a given room
     * @param me a chatroom or a photoroom
     */
    public void quitRoom(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        rooms.remove(me);
        me.removeUser(this);
    }

    /**
     * create a new room
     * @param users an arraylist of users
     * @return cr a newly created chatroom
     */
    public MessageExchange createChatRoom(ArrayList<User> users) {
        if (users == null) {
            throw new IllegalArgumentException();
        }
        ChatRoom cr = new ChatRoom();
        try{
            this.joinRoom(cr);
        } catch (OperationDeniedException ode) {
            System.out.println(ode.getMessage());
        }      

        for (int i = 0; i < users.size(); i++){
            try{
                users.get(i).joinRoom(cr);
            } catch (OperationDeniedException ode) {
                System.out.println(ode.getMessage());
                continue;
            }      
        }
        return cr;
    }

    /**
     * send a message in a given room
     * @param me a chatroom or a photoroom
     * @param msgType message type of the message
     * @param contents a string of contents
     */
    public void sendMessage(MessageExchange me, MessageType msgType, String contents) {
        if (msgType == null || contents == null) {
            throw new IllegalArgumentException();
        }
        Message m = null;
        if (msgType.equals(MessageType.TEXT)) {
            try{
                m = new TextMessage(this, contents);
                if(!me.recordMessage(m)) {
                    System.out.println(INVALID_MSG_TYPE);
                } 
            } catch (OperationDeniedException ode) {
                System.out.println(ode.getMessage());
            } 
        } 
         else if (msgType.equals(MessageType.PHOTO)) {
            try{
                m = new PhotoMessage(this, contents);
                if(!me.recordMessage(m)) {
                    System.out.println(INVALID_MSG_TYPE);
                } 
            } catch (OperationDeniedException ode) {
                System.out.println(ode.getMessage());
            }   
        } else if (msgType.equals(MessageType.STICKER)) {
            try{
                m = new StickerMessage(this, contents);
                if(!me.recordMessage(m)) {
                    System.out.println(INVALID_MSG_TYPE);
                } 
            } catch (OperationDeniedException ode) {
                System.out.println(ode.getMessage());
            }   
        } else {
            System.out.println(INVALID_MSG_TYPE);
        }
        
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
