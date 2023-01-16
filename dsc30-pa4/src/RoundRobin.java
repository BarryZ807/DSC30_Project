/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

/**
 * A scheduling policy
 *
 * @author Zehui Zhang
 * @since 2021-02-01
 */
public class RoundRobin {

    /* constants */
    private static final String TASK_HANDLED = "All tasks are already handled.";

    /* instance variables */
    private DoublyLinkedList<Task> waitlist, finished;
    private int quantum, burstTime, waitTime;

    public RoundRobin(Task[] toHandle) {
        this(4, toHandle);
    }

    public RoundRobin(int quantum, Task[] toHandle) {
        if (quantum < 1 || toHandle == null || toHandle.length == 0) {
            throw new IllegalArgumentException();
        }
        
        this.waitlist = new DoublyLinkedList<Task>();
        for (int i = 0; i < toHandle.length; i++) {
            waitlist.add(toHandle[i]);
        }        

        this.finished = new DoublyLinkedList<Task>();
        this.quantum = quantum;
        this.burstTime = 0;
        this.waitTime = 0;
    }

    public String handleAllTasks() {
        if (this.waitlist.isEmpty()) {
            return TASK_HANDLED;
        }
        String order = "";
        while (!waitlist.isEmpty()) {
    
            Task curr = waitlist.remove(0);
            for (int i = 0; i < quantum; i++) {
                boolean handled = curr.handleTask();
                if (handled) {
                    this.waitTime += waitlist.size();
                    this.burstTime++;
                }
                boolean finish = curr.isFinished();
                if (finish) {
                    finished.add(curr);
                    break;
                }
                
            }

            if (curr.isFinished()) {
                finished.add(curr);
                order += curr.toString();
                if (waitlist.size() != 0) {
                    order += " -> ";
                }
            } else {
                waitlist.add(curr);
            }      
        }

        String s = "All tasks are handled within ";
        s += String.valueOf(burstTime);
        s += " units of burst time and ";
        s += String.valueOf(waitTime);
        s += " units of wait time. The tasks are finished in this order: \n";
        s += order;
        return s;
    }

    /**
     * Main method for testing.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A", 3), new Task("B", 4),
                        new Task("C", 4), new Task("D", 12),
                        new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(3, test1);     // Quantum: 3, ToHandle: test1
        System.out.println(rr1.handleAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8),
                        new Task("C", 6), new Task("D", 4),
                        new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(test2);  // Quantum: 4, ToHandle: test2
        System.out.println(rr2.handleAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5),
                        new Task("C", 3), new Task("D", 1),
                        new Task("E", 2), new Task("F", 4),
                        new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(3, test3);     // Quantum: 3, ToHandle: test3
        System.out.println(rr3.handleAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.handleAllTasks());   // TASK_HANDLED
        System.out.println();
    }
}