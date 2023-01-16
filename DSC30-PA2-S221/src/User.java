import java.util.ArrayList;

public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    /**
     * The constructor initialize class variables
     * @param username a string of user's name
     * @param bio a string of short introduction
     */
    public User(String username, String bio) {
        // check for exceptions
        if (username == null || bio == null){
            throw  new IllegalArgumentException();
        }
        // initialize class variables
        this.username = username;
        this.bio = bio;
        this.rooms = new ArrayList<MessageExchange>();
    }

    /**
     * set new bio
     * @param newBio a string of new bio
     */
    public void setBio(String newBio) {
        // check for exceptions
        if (newBio == null){
            throw  new IllegalArgumentException();
        }
        // set new bio
        this.bio = newBio;
    }

    /**
     * Display the current user's bio
     * @return current bio
     */
    public String displayBio() {
        return  this.bio;
    }

    /**
     * Join the given room
     * @param me a Message Exchange
     * @throws OperationDeniedException exception
     */
    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null){
            try {
                throw new IllegalAccessException();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else if(rooms.contains(me) || !(me.addUser(this))){
            throw  new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        // Join the room
        rooms.add(me);
        me.addUser(this);
    }

    /**
     * quit a given room
     * @param me a Message Exchange
     */
    public void quitRoom(MessageExchange me) {
        if (me == null){
            throw new IllegalArgumentException();
        }
        rooms.remove(me);
        me.removeUser(this,this);
    }

    /**
     * send a message in a given room
     * @param me a chatroom or a photoroom
     * @param contents a string of contents
     * @param lines the number of lines of each message
     */
    public void sendMessage(MessageExchange me, String contents, int lines) {
        if (me == null || contents == null){
            throw  new IllegalArgumentException();
        }
        if (!me.getUsers().contains(this)){
            throw  new IllegalArgumentException();
        }
        // Message newMessage;
        if (lines == -1){
            try {
                TextMessage newText = new TextMessage(this,contents);
                if (me.recordMessage(newText) != true){
                    System.out.println(INVALID_MSG_TYPE);
                }
            } catch (Exception e){
                e.getMessage();
            }
        }
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
