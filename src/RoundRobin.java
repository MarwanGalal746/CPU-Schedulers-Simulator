import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RoundRobin {
    ArrayList<process> processes = new ArrayList<>();
    int contextSwitching;
    int quantum;
    int curTime = 0;
    ArrayList<process> notArrivedYet = new ArrayList<>();
    ArrayList<process> cycle = new ArrayList<>();
    ArrayList<process> done = new ArrayList<>();
    RoundRobin(int quantum){
        this.processes = new ArrayList<>();
        this.contextSwitching = 0;
        this.quantum = quantum;
        this.notArrivedYet = new ArrayList<>();
    }
    RoundRobin(ArrayList<process> processes, int contextSwitching, int quantum) {
        this.processes = processes;
        this.contextSwitching = contextSwitching;
        this.quantum = quantum;
        this.notArrivedYet = processes;
    }

    public void setProcesses(ArrayList<process> processes) {
        this.processes = processes;

    }

    public void setNotArrivedYet(ArrayList<process> notArrivedYet) {
        this.notArrivedYet = notArrivedYet;
    }
    boolean doneExecution(){
        if( notArrivedYet.isEmpty() && cycle.isEmpty())
            return true;
        return false;
    }
    boolean isRunning(){
        return !cycle.isEmpty();
    }
    void update() {
        while (!notArrivedYet.isEmpty() && notArrivedYet.get(0).getArrivalTime() <= curTime) {
            cycle.add(notArrivedYet.get(0));
            executeProcess(cycle.size() - 1);
            curTime += contextSwitching;
            notArrivedYet.remove(0);
        }
    }
    boolean isComeNow(){
        if(notArrivedYet.isEmpty())
            return false;
        return notArrivedYet.get(0).getArrivalTime()<=curTime;
    }
    boolean executeProcess(int i) {
        process p = cycle.get(i);
        if (p.getResponseTime() == -1) {
            p.setResponseTime(curTime);
        }
        if (p.getRemaining() <= quantum) {
            System.out.print(curTime + " "+ p.getName()+ " ");
            curTime += p.getRemaining();
            p.setCompletionTime(curTime);
            p.setWaitingTime(p.getCompletionTime() - p.getBurstTime() - p.getArrivalTime());
            p.setTurnaroundTime(p.getBurstTime() + p.getWaitingTime());
            done.add(p);
            cycle.remove(i);
            System.out.println(curTime);
            return true;
        } else {
            System.out.print(curTime + " "+ p.getName()+ " ");
            curTime += quantum;
            p.setRemaining(p.getRemaining() - quantum);
            cycle.set(i, p);
        }
        System.out.println(curTime);

        return false;
    }


    void executeCycle() {
        for (int i = 0; i < cycle.size(); i++) {
            if (executeProcess(i)) {
                i--;
            }
            curTime += contextSwitching;
        }
    }


    void execute() {
        Collections.sort(notArrivedYet, Comparator.comparing(process::getArrivalTime));
        while (!cycle.isEmpty() || !notArrivedYet.isEmpty()) {
            update();
            if(cycle.isEmpty()&&!notArrivedYet.isEmpty()){
                curTime+=1;
            }
            executeCycle();
        }
        Collections.sort(done, Comparator.comparing(process::getName));
        print();
    }
    void print() {
        System.out.printf("%s %20s %20s %20s %20s %20s %20s\n", "name", "burst time", "arrived time", "completion time",
                "turnaround time", "waiting time", "response time");
        int avgWaiting = 0, avgTurn = 0;
        for (process p : done) {
            System.out.printf("%s %20d %20d %20d %20d %20d %20d\n", p.getName(), p.getBurstTime(), p.getArrivalTime(), p.getCompletionTime(),
                    p.getTurnaroundTime(), p.getWaitingTime(), p.getResponseTime());
            avgWaiting += p.getWaitingTime();
            avgTurn += p.getTurnaroundTime();
        }
        System.out.println("Average waiting time:    " + (double) avgWaiting / done.size());
        System.out.println("Average turnaround time: " + (double) avgTurn / done.size());
    }
}
