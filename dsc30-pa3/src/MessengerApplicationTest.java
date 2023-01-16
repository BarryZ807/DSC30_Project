/* 
 * NAME: Zehui Zhang
 * PID: A16151490
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Messenger Application Test
 * @author Zehui Zhang
 * @since  2021/01/22
 */
public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";
    private static final String JOIN_ROOM_FAILED = 
            "Failed to join the chat room.";

    /*
      Global test variables. Initialize them in @Before method.
     */
    PremiumUser marina;
    MessageExchange room;
    PremiumUser jack;
    PremiumUser maria;
    StandardUser cole;
    PremiumUser frank;
    PremiumUser bob;

    TextMessage text_message;
    PhotoMessage photo_message;
    StickerMessage sticker_message;
    TextMessage text_message1;
    PhotoMessage photo_message1;
    StickerMessage sticker_message1;
    TextMessage text_message2;
    PhotoMessage photo_message2;
    StickerMessage sticker_message2;
    
    ChatRoom cr;
    PhotoRoom pr;

    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() throws OperationDeniedException{
        marina = new PremiumUser("Marina", "Instructor");
        room = new ChatRoom();

        jack = new PremiumUser("Jack","Professor");
        maria = new PremiumUser("Maria", "QAQ");
        cole = new StandardUser("Cole", "!");
        bob = new PremiumUser("Bob", "bobobbob");
        frank = new PremiumUser("Frank", "fff777");

        text_message = new TextMessage(jack, "Hi guys, I am the professor of CompSci 532.");
        photo_message = new PhotoMessage(jack, "Hi.jpg");
        sticker_message = new StickerMessage(maria, "RoundFace/Welcome");
        text_message1 = new TextMessage(cole, "!!!");
        photo_message1 = new PhotoMessage(bob, "JumpJump.PNg");
        sticker_message1 = new StickerMessage(frank, "CuteDog/Clap");
        text_message2 = new TextMessage(jack, "Here is a introduction of our first class.");
        photo_message2 = new PhotoMessage(jack, "Intro.tiF");
        sticker_message2 = new StickerMessage(jack, "LOLFace/Ready");
        cr = new ChatRoom();
        pr = new PhotoRoom();
    }

    @Test
    public void testTextMsgConstructor() throws OperationDeniedException{
        TextMessage a = new TextMessage(jack, ">");
        TextMessage b = new TextMessage(cole, "!!");
        TextMessage c = new TextMessage(frank, ">>");
        assertEquals(jack, a.getSender());
        assertEquals(cole, b.getSender());
        assertEquals("<Premium> Frank ["+date+"]: >>", c.getContents());
    }

    @Test
    public void testPhotoMsgConstructor() throws OperationDeniedException{
        PhotoMessage a = new PhotoMessage(jack, ">.TIFF");
        PhotoMessage b = new PhotoMessage(jack, "!!.Jpg");
        PhotoMessage c = new PhotoMessage(frank, ">>.Png");
        assertEquals(jack, a.getSender());
        assertEquals("jpg", b.getExtension());
        assertEquals("<Premium> Frank ["+date+"]: Picture at >>.png", c.getContents());
    }

    @Test
    public void testStickerMsgConstructor() throws OperationDeniedException{
        StickerMessage a = new StickerMessage(jack, "C/AAA");
        StickerMessage b = new StickerMessage(jack, "A/AAA");
        StickerMessage c = new StickerMessage(frank, "5678/aaa");
        assertEquals(jack, a.getSender());
        assertEquals("A", b.getPackName());
        assertEquals("<Premium> Frank ["+date+"]: Sticker [aaa] from Pack [5678]", c.getContents());
    }

    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, "A sample text message.");

            // concatenating the current date when running the test
            String expected = "<Premium> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void testPhotoMsgGetcontents() {
        assertEquals("<Premium> Jack ["+date+"]: Picture at Hi.jpg", photo_message.getContents());
    }

    @Test
    public void testStickerGetcontents(){
        assertEquals("<Premium> Maria ["+date+"]: Sticker [Welcome] from Pack [RoundFace]", sticker_message.getContents());
    }

    @Test
    public void testPhotoGetextension(){
        assertEquals("jpg", photo_message.getExtension());
    }

    @Test
    public void testStickerGetPackName(){
        assertEquals("RoundFace", sticker_message.getPackName());
    }

    /*
      Assert exception with message
     */
    @Test
    public void testPhotoMsgThrowsODE() {
        try {
            new PhotoMessage(marina, "PA02.zip");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }

        try {
            new PhotoMessage(cole, "pa.tif");
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_USER_GROUP, ode.getMessage());
        }
    }

    @Test
    public void testStickerMsgThrowsODE() {
        try {
            new StickerMessage(cole, "collection/clap");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_USER_GROUP, ode.getMessage());
        }
    }

    @Test
    public void testTextMsgThrowsODE() {
        try {
            new TextMessage(cole, "I had a similar problem of Eclipse compiling my code just fine but Maven failed when compiling the tests every time despite the fact JUnit was in my list of dependencies and the tests were in /src/test/java/.In my case, I had the wrong version of JUnit in my list of dependencies. I wrote JUnit4 tests (with annotations) but had JUnit 3.8.x as my dependency. Between version 3.8.x and 4 of JUnit they changed the package name from junit.framework to org.junit which is why Maven still breaks compiling using a JUnit jar.I'm still not entirely sure why Eclipse successfully compiled. It must have its own copy of JUnit4 somewhere in the classpath. Hope this alternative solution is useful to people. I reached this solution after following Arthur's link above.");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(EXCEED_MAX_LENGTH, ode.getMessage());
        }
    }


    @Test (expected = IllegalArgumentException.class)
    public void testTextPhotoStickerMsgThrowsIAE() throws OperationDeniedException{
        String n = null;
        new TextMessage(jack, n);
        new TextMessage(null, "!!");
        new PhotoMessage(jack, n);
        new PhotoMessage(null, "!!");
        new StickerMessage(jack, n);
        new StickerMessage(null, "!!");
    }

    @Test
    public void testStdUserConstructorAndDisplayName(){
        StandardUser a = new StandardUser("a", "!!!");
        StandardUser b = new StandardUser("b", ">>>");
        StandardUser c = new StandardUser("c", "QAQ");
        assertEquals("a", a.displayName());
        assertEquals("b", b.displayName());
        assertEquals("QAQ", c.displayBio());
    }

    @Test
    public void testPrmUserConstructorAndDisplayName() {
        PremiumUser a = new PremiumUser("a", "!!!");
        PremiumUser b = new PremiumUser("b", ">>>");
        PremiumUser c = new PremiumUser("c", "QAQ");
        assertEquals("<Premium> a", a.displayName());
        assertEquals("<Premium> b", b.displayName());
        assertEquals("QAQ", c.displayBio());
    }


    @Test (expected = IllegalArgumentException.class)
    public void testStdPrmUserThrowsIAE(){
        String n = null;
        new StandardUser("a", n);
        new StandardUser(n, "a");
        new PremiumUser("a", n);
        new PremiumUser(n, "a");
    }

    @Test 
    public void testSetDisplayBio(){
        assertEquals("Professor", jack.displayBio());
        jack.setBio("Instructor");
        assertEquals("Instructor",jack.displayBio());
    }

    @Test
    public void testSetTitle(){
        jack.setCustomTitle("Instructor");
        assertEquals("<Instructor> Jack", jack.displayName());
    }

    @Test
    public void testChatRoomConstructor(){
        ChatRoom a = new ChatRoom();
        ChatRoom b = new ChatRoom();
        ChatRoom c = new ChatRoom();

        assertEquals(0, a.getLog().size());
        assertEquals(0, b.getLog().size());
        assertEquals(0, c.getUsers().size());
    }

    @Test
    public void testPhotoRoomConstructor(){
        PhotoRoom a = new PhotoRoom();
        PhotoRoom b = new PhotoRoom();
        PhotoRoom c = new PhotoRoom();

        assertEquals(0, a.getLog().size());
        assertEquals(0, b.getLog().size());
        assertEquals(0, c.getUsers().size());
    }

    @Test
    public void testChatPhotoRoomAddRemoveUser(){
        ChatRoom a = new ChatRoom();
        ChatRoom b = new ChatRoom();
        ChatRoom c = new ChatRoom();
        PhotoRoom d = new PhotoRoom();
        PhotoRoom e = new PhotoRoom();

        a.addUser(jack);
        b.addUser(jack);
        c.addUser(cole);
        c.addUser(jack);
        d.addUser(jack);
        d.addUser(maria);
        e.addUser(maria);
        e.addUser(jack);
        e.addUser(frank);
        e.addUser(bob);

        assertEquals(1, a.getUsers().size());
        assertEquals(2, c.getUsers().size());
        assertEquals(2, d.getUsers().size());
        assertEquals(4, e.getUsers().size());
        assertTrue(b.getUsers().contains(jack));
        assertTrue(e.getUsers().contains(bob));

        a.removeUser(jack);
        c.removeUser(cole);
        e.removeUser(frank);
        e.removeUser(cole);

        assertEquals(0,a.getUsers().size());
        assertEquals(1,c.getUsers().size());
        assertFalse(e.getUsers().contains(frank));
        assertFalse(e.getUsers().contains(cole));

        assertFalse(e.addUser(cole));
    }

    @Test
    public void testCharPhotoRoomGetUsers(){
        ChatRoom a = new ChatRoom();
        PhotoRoom e = new PhotoRoom();

        a.addUser(jack);
        a.addUser(cole);
        e.addUser(maria);
        e.addUser(jack);
        e.addUser(frank);
        e.addUser(bob);

        ArrayList<User> au = new ArrayList<User>();
        ArrayList<User> eu = new ArrayList<User>();
        au.add(jack);
        au.add(cole);
        eu.add(maria);
        eu.add(jack);
        eu.add(frank);
        eu.add(bob);

        assertEquals(au, a.getUsers());
        assertEquals(eu, e.getUsers());
    }

    @Test
    public void testChatRoomGetLogAndRecordMsg() {
        cr.recordMessage(text_message);
        cr.recordMessage(text_message1);
        cr.recordMessage(text_message2);
        ArrayList<Message> a = new ArrayList<Message>();
        a.add(text_message);
        a.add(text_message1);
        a.add(text_message2);
        assertEquals(a, cr.getLog());
    }

    @Test
    public void testPhotoRoomGetLogAndRecordMsg() {
        pr.recordMessage(photo_message);
        pr.recordMessage(photo_message1);
        pr.recordMessage(photo_message2);
        ArrayList<Message> a = new ArrayList<Message>();
        a.add(photo_message);
        a.add(photo_message1);
        a.add(photo_message2);
        assertEquals(a, pr.getLog());
    }


    @Test
    public void testStdUserFetchMsg() throws OperationDeniedException {
        jack.joinRoom(cr);
        cole.joinRoom(cr);
        cr.recordMessage(text_message);
        cr.recordMessage(text_message1);
        cr.recordMessage(text_message2);
        
        String s = "<Premium> Jack ["+date+"]: Hi guys, I am the professor of CompSci 532.";
        s += '\n';
        s += "Cole ["+date+"]: !!!";
        s += '\n';
        s += "<Premium> Jack ["+date+"]: Here is a introduction of our first class.";
        s += '\n'; 
        
        assertEquals(s,jack.fetchMessage(cr));
        assertEquals(s,cole.fetchMessage(cr));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFetchThrowsIAE() {
        cole.fetchMessage(cr);
        jack.fetchMessage(null);
    }

    @Test
    public void testCreatePhotoRoom(){
        ArrayList<User> u = new ArrayList<User>();
        u.add(frank);
        jack.createPhotoRoom(u);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateRoomThrowsIAE(){
        jack.createPhotoRoom(null);
        jack.createChatRoom(null);
    }


    @Test
    public void testChatRoom() {
        ArrayList<User> u = new ArrayList<User>();
        u.add(cole);
        MessageExchange r = jack.createChatRoom(u);
        assertEquals(2, r.getUsers().size());
        assertEquals(0, r.getLog().size());

        r.removeUser(frank);
        r.removeUser(cole);
        assertEquals(1, r.getUsers().size());
        r.addUser(cole);
        r.addUser(maria);
        assertEquals(3, r.getUsers().size());

        assertTrue(r.recordMessage(text_message));
        assertTrue(r.recordMessage(photo_message));
        assertTrue(r.recordMessage(sticker_message));
        assertEquals(3, r.getLog().size());

        jack.sendMessage(r, MessageType.TEXT, "!");
        jack.sendMessage(r, MessageType.PHOTO, "!.jpg");
        cole.sendMessage(r, MessageType.PHOTO, "?.jpg");
        assertEquals(5, r.getLog().size());
        System.out.print(jack.fetchMessage(r));
        System.out.println(cole.fetchMessage(r));
    }

    @Test
    public void testPhotoRoom() {
        ArrayList<User> u = new ArrayList<User>();
        u.add(cole);
        MessageExchange r = jack.createPhotoRoom(u);
        assertEquals(1, r.getUsers().size());
        assertEquals(0, r.getLog().size());

        r.removeUser(frank);
        assertEquals(1, r.getUsers().size());
        assertFalse(r.addUser(cole));
        assertTrue(r.addUser(maria));
        assertEquals(2, r.getUsers().size());

        assertFalse(r.recordMessage(text_message));
        assertTrue(r.recordMessage(photo_message));
        assertFalse(r.recordMessage(sticker_message));
        assertEquals(1, r.getLog().size());

        jack.sendMessage(r, MessageType.TEXT, "!");
        jack.sendMessage(r, MessageType.PHOTO, "!.jpg");
        assertEquals(2, r.getLog().size());
        System.out.print(jack.fetchMessage(r));
    }

}
