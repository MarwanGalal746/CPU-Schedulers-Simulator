public class process implements Comparable<process> {
    private String name;
    private int arrivalTime;
    private int BurstTime;
    private int WaitingTime;
    private int turnaroundTime;
    private int completionTime;

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public process(String name, int arrivalTime, int BurstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.BurstTime = BurstTime;
    }

    public void setWaitingTime(int WaitingTime) {
        this.WaitingTime = WaitingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        BurstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return BurstTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    @Override
    public int compareTo(process process) {
        return Integer.compare(this.arrivalTime, process.arrivalTime);
    }

    @Override
    public String toString() {
        return name + " :  " + " WaitingTime=" + WaitingTime + ",  turnaroundTime=" + turnaroundTime;
    }



}
