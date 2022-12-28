import java.util.*;

class Process {
    int id;
    int priority;
    Process next;

    Process(int id, int priority) {
        this.id = id;
        this.priority = priority;
        this.next = null;
    }
}

class CircularLinkedList {
    Process head = null;
    Process tail = null;

    void addNode(int id, int priority) {
        Process node = new Process(id, priority);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        tail.next = head;
    }

}

class PriorityComparator implements Comparator<Process> {

    public int compare(Process p1, Process p2) {
        if (p1.id < p2.id)
            return 1;
        else if (p1.id > p2.id)
            return -1;
        return 0;
    }
}

public class ring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int numberOfProcess = sc.nextInt();

        CircularLinkedList cll = new CircularLinkedList();
        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < numberOfProcess; i++) {
            System.out.println("Enter the ID:");
            int id = sc.nextInt();
            System.out.println("Enter the Priority:");
            int priority = sc.nextInt();
            cll.addNode(id, priority);
            map.put(id, true);
        }

        System.out.println("Enter the ID of process which will start the election:");
        int startElectionID = sc.nextInt();

        // Process which are failed.
        map.put(6, false);
        map.put(5, false);

        Process startElection = cll.head;

        while (startElection != null && startElection.id != startElectionID) {
            startElection = startElection.next;
        }
        PriorityQueue<Process> pq = new PriorityQueue<Process>(numberOfProcess, new PriorityComparator());
        pq.add(startElection);
        Iterator<Process> g_iterator = pq.iterator();
        while (g_iterator.hasNext()) {
            Process curr = g_iterator.next();
            System.out.println("ID : " + curr.id +", " +"Priority : " + curr.priority);
        }
        System.out.println("Process : " + startElection.id + " ------->" + "Process : "+ startElection.next.id);
        System.out.println("\n---------------------");

        Process temp = startElection.next;

        while (temp != null && temp.id != startElectionID) {

            if(map.get(temp.id) == true){
                pq.add(temp);
            }

            
            Iterator<Process> iterator = pq.iterator();
            while (iterator.hasNext()) {
                Process curr = iterator.next();
                System.out.println("ID : " + curr.id + ", "+ "Priority : " + curr.priority);
            }
            System.out.println("\nProcess : " + temp.id + " -------> " + " Process : "+ temp.next.id);

            System.out.println("\n---------------------");
            temp = temp.next;
        }

        System.out.println("New coordinator:");
        System.out.println(pq.peek().id);

        // for(int i = 0;i<numberOfProcess;i++){
        //     if(map.get(i+1) == true){
        //         System.out.println("Process : "+ pq.peek().id + "sends victory message to : "+"Process");
        //     }
        // }
        Process itr = cll.head;

        while(itr.next != cll.head){
            if(map.get(itr.id) == true && itr.id != pq.peek().id){
              System.out.println("Process : "+ pq.peek().id + " sends victory message to : "+"Process : " + itr.id);
            }
           
            itr = itr.next;
        }

    }

}