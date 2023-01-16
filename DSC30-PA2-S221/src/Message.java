import java.time.LocalDate;

public abstract class Message {

    // Error message to use in OperationDeniedException
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    // instance variables
    private LocalDate date;
    private User sender;
    protected String contents;

    /**
     * Constructor will set sender and date fields
     * @param sender The user of the message class
     */
    public Message(User sender) {
        this.date = LocalDate.now();
        // check if the sender is null, if not set sender
        if (sender == null){
            // if sender is null throw the exception
            throw new IllegalArgumentException();
        }else{
            this.sender = sender;
        }
    }

    /**
     * Method to return the date of this message
     * @return the date of the message
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Method to return the sender of this message
     * @return the sender of the message
     */
    public User getSender() {
        return sender;
    }

    public abstract String getContents();

}