public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    /**
     * Constructor that initializes a text message
     * @param sender the user
     * @param text a string of text source
     * @throws OperationDeniedException
     */
    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super(sender);
        contents = text;

        // check if the text length is too long
        if(text.length() > MAX_TEXT_LENGTH){
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        // check if the sender or text is null
        if (sender == null || text == null){
            throw new IllegalArgumentException();
        }
    }

    // Yuxuan [16:38:36.868882500]: A sample text message.

    /**
     * Get string expression of contents
     * @return a string representing the sender name, date and text
     */
    public String getContents() {
        String name = super.getSender().displayName();
        String date = super.getDate().toString();
        String res = name + " [" +date + "]: "+this.contents;
        return res;
    }

}
