/* 
 * NAME: Zehui Zhang
 * PID: A16151490
 */
/**
 * Photo Message
 * @author Zehui Zhang
 * @since  2021/01/22
 */
public class PhotoMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";

    // instance variable
    private String extension;
    private String[] extensions = {"jpg","jpeg", "gif", "png", "tif", "tiff","raw"};

    /**
     * Constructor that initializes a photo message
     * @param sender the user
     * @param photoSource a string of photo source
     */
    public PhotoMessage(User sender, String photoSource)
                        throws OperationDeniedException {
        super(sender);
        if (sender == null || photoSource == null) {
            throw new IllegalArgumentException();
        }
        if ( getSender() instanceof StandardUser){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        // extracting extension from photo source
        int index_last_dot = 0;
        String extension_string = "";
        for (int i = photoSource.length()-1; i >= 0; i--) {
            if (photoSource.charAt(i) == '.'){
                index_last_dot = i;
            }
        }
        for (int j = index_last_dot+1; j < photoSource.length(); j++) {
            extension_string += photoSource.charAt(j);
        }
        this.extension = extension_string.toLowerCase();

        boolean valid_extension = false;
        for (int k = 0; k < extensions.length; k++){
            if (extensions[k].equals(extension)){
                valid_extension = true;
                break;
            }
        }
        
        if (!valid_extension){
            throw new OperationDeniedException(INVALID_INPUT);
        }
        this.contents = "";
        for (int i = 0; i < index_last_dot; i++ ) {
            this.contents += photoSource.charAt(i);
        }
        this.contents += '.';
        this.contents += extension;
    }

    /**
     * get string expression of contents 
     * @return a string representing the sender name, date and photo source
     */
    public String getContents() {
        return getSender().displayName()+ " ["+getDate().toString()+"]"+": Picture at "+contents;
    }

    /**
     * get extension of photo source  
     * @return a string representing the extension
     */
    public String getExtension() {
        return extension;
    }

}
