public class Main
{
    public static void main(String[] args) {
        Bully election = new Bully();
        election.addProcess(1,1);
        election.addProcess(2,2);
        election.addProcess(3,3);
        election.addProcess(4,4);
        election.addProcess(5,5);
        election.stopProcess(5);
       
        election.startElections(3);
        
       
    }
}