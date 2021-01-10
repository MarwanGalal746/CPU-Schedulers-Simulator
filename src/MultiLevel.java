
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    RR
FCFS
*/
public class MultiLevel {
    ArrayList<process> processes;
    ArrayList<process> notDone, done; // for FCFS

    int currentTime, quantum;
    RoundRobin roundRobin;

    MultiLevel(ArrayList<process> processes, int quantum) {
        this.processes = processes;
        Collections.sort(this.processes, Comparator.comparing(process::getArrivalTime));
        this.currentTime = 0;
        this.quantum = quantum;
        roundRobin = new RoundRobin(quantum);
        notDone = new ArrayList<>();
        done = new ArrayList<>();
        ArrayList<process> q1 = new ArrayList<>();
        for (process p : processes) {
            if (p.getqType() == 1) {
                q1.add(p);
            } else {
                notDone.add(p);
            }
        }
        roundRobin.setProcesses(q1);
        roundRobin.setNotArrivedYet(q1);
        roundRobin.curTime = 0;

    }

    void execute() {
        roundRobin.curTime = currentTime;
        while (roundRobin.isRunning() || roundRobin.isComeNow()) {
            int prevTime = roundRobin.curTime;
            roundRobin.update();
            while(roundRobin.isRunning())
            {
                roundRobin.executeCycle();
            }
            roundRobin.update();
            int curTime = roundRobin.curTime;
            this.currentTime += curTime - prevTime;

            // call function in round robin to execute current processes in cycle
        }
        roundRobin.curTime = this.currentTime;
        if (!roundRobin.isRunning() && !notDone.isEmpty()) {
            if (notDone.get(0).getResponseTime() == -1)
                notDone.get(0).setResponseTime(currentTime);
            notDone.get(0).setRemaining(notDone.get(0).getRemaining() - 1);
            currentTime++;
            if (notDone.get(0).getRemaining() == 0) {
                notDone.get(0).setCompletionTime(currentTime);
                notDone.get(0).setWaitingTime(notDone.get(0).getCompletionTime() - notDone.get(0).getBurstTime() - notDone.get(0).getArrivalTime());
                notDone.get(0).setTurnaroundTime(notDone.get(0).getBurstTime() + notDone.get(0).getWaitingTime());
                done.add(notDone.get(0));
                notDone.remove(0);
            }
        } else
            currentTime++;
        roundRobin.curTime = currentTime;
        return;
    }
    void run() {
        while (!(roundRobin.doneExecution() && notDone.isEmpty())) {
            execute();
        }
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
        for (process p : roundRobin.done) {
            System.out.printf("%s %20d %20d %20d %20d %20d %20d\n", p.getName(), p.getBurstTime(), p.getArrivalTime(), p.getCompletionTime(),
                    p.getTurnaroundTime(), p.getWaitingTime(), p.getResponseTime());
            avgWaiting += p.getWaitingTime();
            avgTurn += p.getTurnaroundTime();
        }
        System.out.println("Average waiting time:    " + (double) avgWaiting / (done.size()+roundRobin.done.size()));
        System.out.println("Average turnaround time: " + (double) avgTurn / (done.size()+roundRobin.done.size()));

    }


}
