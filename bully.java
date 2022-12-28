import java.util.*;

class Bully{

    HashMap<Integer, elements> map = new HashMap<>();
    HashMap<Integer, Boolean> available = new HashMap<>();
    int arr[] = new int[10];
    int coordinator = 1;
   
    private class elements{
        int id;
        int priority;
        public elements(int id , int priority){
            this.id = id;
            this.priority = priority;
        }
    }
   
   
    public void addProcess(int id, int priority){
        var temp = new elements(id,priority);
        arr[id] = 1;
     
        map.put(id,temp);
        available.put(id,true);
     
    }
   
   
    public void stopProcess(int id){
       if(map.containsKey(id)){
           available.put(id,false);
       }
    }
   
    public void startElections(int id){
        coordinator = startElection(id);
        System.out.println("current coordinator is " + coordinator);
    }
   
    private int startElection(int id){
        System.out.println("election started by " + id);
        for(int j = id + 1;j < 10; j++){
            if(arr[j] == 1){
                System.out.println(id + " sending to " + j);
                if(available.get(j) != true){
                    System.out.println(j + " is not active ");
                }
            }
        }
        for(int i = id + 1;i<10;i++){
            if(arr[i] == 1){
           
            if(available.get(i) == true){
                System.out.println(i + " accepting from " + id);
                id = startElection(i);
                break;
            }
            }else
                continue;
        }
        return id;
    }
   
   
    public void wakeProcess(int id){
        if(map.containsKey(id)){
            available.put(id,true);
        }
    }
   
}
