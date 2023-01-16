 /*
  Name: Zehui ZHang
  PID:  A16151490
 */

import java.time.LocalDate;
import org.junit.*;
import org.w3c.dom.Text;

import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author TODO
 * @since  TODO
 */
public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path is not valid.";

    /*
      Global test variables. Initialize them in @Before method.
     */
    Tutor marina;
    MessageExchange room;
    Autograder autograder;
    User user;
    Student student;


    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() {
        marina = new Tutor("Marina", "Instructor");
        room = new Autograder(marina);
        autograder = new Autograder(marina);
        student = new Student("Student","I am a student");

    }

    /*
      Recap: Assert exception without message
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPremiumUserThrowsIAE() {
        marina = new Tutor("Marina", null);
    }

    /*
      Assert exception with message
     */
    @Test
    public void testPhotoMessageThrowsODE() {
        try {
            CodeMessage pm = new CodeMessage(marina, "PA02.zip", 10);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }



    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, "A sample text message.");

            // concatenating the current date when running the test
            String expected = "<Tutor> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }


    /*
     * test student  fetch one message
     */
    @Test
    public void testStudentFetchMessage1() {
        Autograder autoStudent = new Autograder(marina);
        autoStudent.addUser(student); // auto grader add a User named Student

        student.sendMessage(autoStudent,"Hello",-1); // Student send a message in autoStudent
        String res = student.fetchMessage(autoStudent); // student fetch the message
        String expected = student.displayName()+" [" + date + "]: "+"Hello\n";
        assertEquals(expected,res);

    }


    /*
     * test student  fetch 101 messages
     */
    @Test
    public void testStudentFetchMessage2() {
        Autograder autoStudent = new Autograder(marina);
        autoStudent.addUser(student); // auto grader add a User named Student

        for (int i = 0; i < 101; i++) {
            student.sendMessage(autoStudent,"Hello",-1); // Student send a message in autoStudent
        }
        String res = student.fetchMessage(autoStudent);
        String expected = "";

        for (int i = 0; i < 100; i++) {
            expected +=student.displayName()+" [" + date + "]: "+"Hello\n";; // Student send a message in autoStudent
        }// student fetch the message
        assertEquals(expected,res);

    }

    /*
     * test ResolveAllProblems with first test case
     */
    @Test
    public void testResolveAllProblems1() throws OperationDeniedException {

        Tutor Oliver = new Tutor("Oliver", "Oliver 1");
        Tutor Huaning  = new Tutor("Huaning", "Huaning  1");
        Tutor Colin  = new Tutor("Colin", "Colin  1");
        Tutor Yuri  = new Tutor("Yuri", "Yuri  1");


        CodeMessage pmOliver = new CodeMessage(Oliver, "PA02.java", 20);
        CodeMessage pmHuaning = new CodeMessage(Huaning, "PA02.java", 20);
        CodeMessage pmColin = new CodeMessage(Oliver, "PA02.java", 20);
        CodeMessage pmYuri = new CodeMessage(Yuri, "PA02.java", 78);




        autograder.addUser(Oliver);
        autograder.addUser(Huaning);
        autograder.addUser(Colin);
        autograder.addUser(Yuri);
        autograder.recordMessage(pmOliver);
        autograder.recordMessage(pmHuaning);
        autograder.recordMessage(pmColin);
        autograder.recordMessage(pmYuri);


        String expected = "All tasks are handled within 14 units of burst time and 12 units of wait time.";
        String res = autograder.resolveAllProblems(Oliver);
        assertEquals(expected,res);

    }


    /*
     * test ResolveAllProblems with second test case
     */
    @Test
    public void testResolveAllProblems2() throws OperationDeniedException {

        Tutor Oliver = new Tutor("Oliver", "Oliver 1");
        Tutor Huaning  = new Tutor("Huaning", "Huaning  1");
        Tutor Colin  = new Tutor("Colin", "Colin  1");


        CodeMessage pmOliver = new CodeMessage(Oliver, "PA02.java", 55);
        CodeMessage pmHuaning = new CodeMessage(Huaning, "PA02.java", 30);
        CodeMessage pmColin = new CodeMessage(Oliver, "PA02.java", 70);




        autograder.addUser(Oliver);
        autograder.addUser(Huaning);
        autograder.addUser(Colin);
        autograder.recordMessage(pmOliver);
        autograder.recordMessage(pmHuaning);
        autograder.recordMessage(pmColin);


        String expected = "All tasks are handled within 16 units of burst time and 22 units of wait time.";
        String res = autograder.resolveAllProblems(Oliver);
        assertEquals(expected,res);

    }
}
