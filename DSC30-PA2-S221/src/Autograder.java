import java.util.ArrayList;
import java.util.List;

public class Autograder implements MessageExchange {

    /* constants */
    private static final String TASK_HANDLED = "All tasks are already handled.";

    // time allowed
    private static final int DEFAULT_ALLOTTED_TIME = 5;

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    // time exchange index
    private static final int TIME_EXCHANGE_IDX = 10;

    /* instance variables */
    private DoublyLinkedList waitlist, finished;
    private int burstTime, waitTime;


    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;
    private Tutor tutor;

    /**
     * The constructor initialize class cariables
     * @param tutor
     */
    public Autograder(Tutor tutor) {
        // Initialize variables
        this.tutor = tutor;
        this.users = new ArrayList<User>();
        this.log = new ArrayList<Message>();
        this.waitlist = new DoublyLinkedList();
        this.finished = new DoublyLinkedList();
    }

    /**
     * Method that returns the log of the char room
     * @param requester The user that requests this operation.
     * @return the log of chat room
     */
    public ArrayList<Message> getLog(User requester) {
        ArrayList<Message> res = null;
        if (requester instanceof Tutor) {
            res =  log;
        } else if(requester instanceof Student){
            res =  log;
            if (res.size() > 0 && res.size()>=MAX_MSG_SIZE){
               res = (ArrayList<Message>) res.subList(0,MAX_MSG_SIZE);
            }
        }
        return log;
    }

    /**
     * Add user to given autograder room
     * @param u User to add.
     * @return Boolean
     */
    public boolean addUser(User u) {
        return users.add(u);
    }

    /**
     * Remove the given user from the room
     * @param requester The user that requests this operation.
     * @param u User to remove.
     * @return boolean
     */
    public boolean removeUser(User requester, User u) {
        requester.quitRoom(this);
        return  users.remove(u);
    }

    /**
     * Return the users of given auto grader room
     * @return users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Add new message
     * @param m Message to add.
     * @return
     */
    public boolean recordMessage(Message m) {
        return log.add(m);
    }

    /**
     * This method here captures the actual process of the tutor handling all the code questions.
     * @param requester user
     * @return string
     * @throws OperationDeniedException
     */
    public String resolveAllProblems(User requester) throws OperationDeniedException {
        // Initialize waitlist
        if (requester instanceof Tutor) {
            for (int i = 0; i < log.size(); i++) {
                Message message = log.get(i);
                if (message instanceof CodeMessage) {
                    // extension is java
                    if (((CodeMessage) message).getExtension().equals("java")) {
                        // the time that tutor needs to resolve the issue for one specific student is equal
                        // to the number of lines of each CodeMessage divided by 10 round up to the next int
                        int requireTime = (int) Math.ceil(((double)(((CodeMessage) message).getLines())/10));
                        this.waitlist.add(requireTime);
                    }
                }
            }
        }
        // return Task handled when waitlist is empty
        if (this.waitlist.isEmpty()) {
            return TASK_HANDLED;
        }
        while (!waitlist.isEmpty()) {

            int time = waitlist.remove(0);
            boolean handled = time >DEFAULT_ALLOTTED_TIME ?false:true;
            if (handled) {
                this.waitTime +=time*waitlist.size();
                this.burstTime+=time;
                finished.add(time);
            }else {
                this.burstTime+=DEFAULT_ALLOTTED_TIME;
                this.waitTime +=DEFAULT_ALLOTTED_TIME*waitlist.size();
                waitlist.add(time-DEFAULT_ALLOTTED_TIME);
            }
        }

        // Append strings to s
        String s = "All tasks are handled within ";
        s += String.valueOf(burstTime);
        s += " units of burst time and ";
        s += String.valueOf(waitTime);
        s += " units of wait time.";
        return s;
    }

}