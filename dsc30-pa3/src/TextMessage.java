/* 
 * NAME: Zehui Zhang
 * PID: A16151490
 */
/**
 * Text Message
 * @author Zehui Zhang
 * @since  2021/01/22
 */
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";
    private int MAX_TEXT_LENGTH = 500;

    /**
     * Constructor that initializes a text message
     * @param sender the user
     * @param text a string of text source
     */
    public TextMessage(User sender, String text) 
                        throws OperationDeniedException {
        super(sender);
        if (sender == null || text == null) {
            throw new IllegalArgumentException();
        }
        if (text.length() > MAX_TEXT_LENGTH){
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        
        this.contents = text;
    }

    /**
     * get string expression of contents 
     * @return a string representing the sender name, date and text
     */
    public String getContents() {
        return getSender().displayName()+ " ["+getDate().toString()+"]"+": "+contents;
    }

}
