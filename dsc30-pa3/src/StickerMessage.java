/* 
 * NAME: Zehui Zhang
 * PID: A16151490
 */
/**
 * Sticker Message
 * @author Zehui Zhang
 * @since  2021/01/22
 */
public class StickerMessage extends Message {

    // instance variable
    private String packName;

    /**
     * Constructor that initializes a sticker message
     * @param sender the user
     * @param stickerSource a string of sticker source
     */
    public StickerMessage(User sender, String stickerSource)
            throws OperationDeniedException {
        super(sender);
        if (sender == null || stickerSource == null) {
            throw new IllegalArgumentException();
        }
        if ( getSender() instanceof StandardUser){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        // find the '/' to extract pack name 
        int slash_index = 0;
        for (int i = 0; i < stickerSource.length(); i++) {
            if (stickerSource.charAt(i) == '/'){
                slash_index = i;
                break;
            }
        }
        String pack_name = "";
        String sticker_name = "";
        for (int j = 0; j < slash_index; j++) {
            pack_name += stickerSource.charAt(j);
        }
        for (int k = slash_index + 1; k < stickerSource.length(); k++) {
            sticker_name += stickerSource.charAt(k);
        }
        this.packName = pack_name;
        this.contents = sticker_name;
    }

    /**
     * get string expression of contents 
     * @return a string representing the sender name, date and sticker source
     */
    public String getContents() {
        return getSender().displayName()+ " ["+
            getDate().toString()+"]"+": Sticker ["+contents+"]" +
                " from Pack [" + packName+ "]";
    }

    /**
     * get packname  
     * @return a string representing the packname
     */
    public String getPackName() {
        return packName;
    }
}
