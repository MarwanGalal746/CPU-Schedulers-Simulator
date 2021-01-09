public class process {
    private String name;
    private int arrivalTime;
    private int BurstTime;
    private int WaitingTime;
    private int turnaroundTime;
    private int completionTime;
    private int responseTime;
    private int remaining;
    private int priority;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

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
        this.responseTime = -1;
        this.remaining = BurstTime;
    }

    public process(String name, int arrivalTime, int BurstTime,int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.BurstTime = BurstTime;
        this.priority=priority;
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

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getRemaining() {
        return this.remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
    

    @Override
    public String toString() {
        return name + " :  " + " WaitingTime=" + WaitingTime + ",  turnaroundTime=" + turnaroundTime;
    }


}
