import java.util.ArrayList;
import java.util.Arrays;

public class CodeMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path is not valid.";

    // Error message to use in OperationDeniedException for the invalid line number
    private static final String INVALID_CODE =
            "The files are not long enough.";

    // input validation criteria
    private static final String[] ACCEPTABLE_EXTENSIONS =
            new String[] {"html", "java", "py", "mjs", "ipynb", "md", "yml"};

    // instance variable
    private String extension;
    private int lines;

    /**
     * The constructor must call "super" and pass in the parameters required
     * by the Message constructor as the FIRST LINE
     * @param sender the user
     * @param codeSource string with code source
     * @param lines number of lines
     * @throws OperationDeniedException exceptions
     */
    public CodeMessage(User sender, String codeSource, int lines)
                        throws OperationDeniedException {
        super(sender);
        // Check conditions of exceptions
        if (codeSource == null|| sender == null){
            throw  new IllegalArgumentException();
        }
        if (lines <10){
            throw  new OperationDeniedException(INVALID_CODE);
        }
        contents = codeSource;
        this.lines = lines;
        String delimeter = "\\.";  // separate characters
        String[] splitCodeSource = codeSource.split(delimeter);

        extension = splitCodeSource[splitCodeSource.length-1].toLowerCase();
        if (!Arrays.asList(ACCEPTABLE_EXTENSIONS).contains(extension)){
            throw  new OperationDeniedException(INVALID_INPUT);
        }
    }

    /**
     * Method return the content
     * @return a string in certain form
     */
    public String getContents() {
        String name = super.getSender().username;
        String date = super.getDate().toString();
        String res = name + " [" +date + "]: "+this.contents;
        return res;
    }

    /**
     * Method will return the extension of the message
     * @return the extension of the message
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Method will return the lines of the message
     * @return lines of message
     */
    public int getLines() {
        return lines;
    }

}
