import java.util.ArrayList;
import java.util.Scanner;


public class Scheduler {

//    public static void main(String[] args) {
//        ArrayList<process>p=new ArrayList<>();
//        process temp1 = new process("p1",0,1);
//        p.add(temp1);
//        process temp2 = new process("p2",1,7);
//        p.add(temp2);
//        process temp3 = new process("p3",2,3);
//        p.add(temp3);
//        process temp4 = new process("p4",3,6);
//        p.add(temp4);
//        process temp5 = new process("p5",4,5);
//        p.add(temp5);
//        process temp6 = new process("p6",5,15);
//        p.add(temp6);
//        process temp7 = new process("p7",15,8);
//        p.add(temp7);
//
//        SRTF s = new SRTF(p,0);
//        s.execute();
//        /*for(int i=0 ; i<p.size();i++)
//            System.out.println(p.get(i));*/
//    }
    static Scanner in = new Scanner(System.in);
    static void Srtf(){
        System.out.println("Enter number of process: ");
        int n = in.nextInt();
            /*
             *  Burst Time  Arrival time  Context switching (for question 1 and 2 only) 
             * Round robin Time Quantum (for question 2 and 4 only)  Priority (for question
             * 3 only)  Queue number that the process will enter (for question 4 only)
             * 
             */
        ArrayList<process> processes = new ArrayList<>();
        System.out.print("\nEnter context switching time: ");
        int contextTime = in.nextInt();
        for (int i = 0; i < n; i++) {
            in.nextLine();
            System.out.print("Enter name of process: ");
            String name = in.nextLine();
            System.out.print("\nEnter burst time: ");
            int burstTime = in.nextInt();
            System.out.print("\nEnter arrival time: ");
            int arrivalTime = in.nextInt();
            processes.add(new process(name,arrivalTime,burstTime));
        }
        System.out.println();
        SRTF srtf = new SRTF(processes,contextTime);
        srtf.execute();
        System.out.println();
    }
    static void roundRobin(){
        System.out.println("Enter number of process: ");
        int n = in.nextInt();
        /*
         *  Burst Time  Arrival time  Context switching (for question 1 and 2 only) 
         * Round robin Time Quantum (for question 2 and 4 only)  Priority (for question
         * 3 only)  Queue number that the process will enter (for question 4 only)
         * 
         */
        ArrayList<process> processes = new ArrayList<>();
        System.out.print("\nEnter quantum: ");
        int quantum = in.nextInt();
        System.out.print("\nEnter context switching time: ");
        int contextTime = in.nextInt();
        for (int i = 0; i < n; i++) {
            in.nextLine();
            System.out.print("Enter name of process: ");
            String name = in.nextLine();
            System.out.print("\nEnter burst time: ");
            int burstTime = in.nextInt();
            System.out.print("\nEnter arrival time: ");
            int arrivalTime = in.nextInt();
            processes.add(new process(name, arrivalTime, burstTime));
        }
        System.out.println();
        RoundRobin RR = new RoundRobin(processes, contextTime, quantum);
        while (!RR.doneExecution()) {
            RR.execute();
        }
        System.out.println();
    }
    static void pr(){
        System.out.println("Enter number of process: ");
        int n = in.nextInt();
        ArrayList<process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            in.nextLine();
            System.out.print("Enter name of process: ");
            String name = in.nextLine();
            System.out.print("\nEnter burst time: ");
            int burstTime = in.nextInt();
            System.out.print("\nEnter arrival time: ");
            int arrivalTime = in.nextInt();
            System.out.print("\nEnter priority: ");
            int pp = in.nextInt();
            processes.add(new process(name, arrivalTime, burstTime,pp));
        }
        System.out.println();
        priority prr = new priority(processes);
        prr.excute();
        System.out.println();
    }

    static void multiLevel(){
        System.out.println("Enter number of process: ");
        int n = in.nextInt();
        ArrayList<process> processes = new ArrayList<>();
        System.out.print("\nEnter quantum: ");
        int quantum = in.nextInt();
        for (int i = 0; i < n; i++) {
            in.nextLine();
            System.out.print("Enter name of process: ");
            String name = in.nextLine();
            System.out.print("\nEnter burst time: ");
            int burstTime = in.nextInt();
            System.out.print("\nEnter arrival time: ");
            int arrivalTime = in.nextInt();
            System.out.print("Enter queue number: ");
            int qType =  in.nextInt();
            processes.add(new process(name, arrivalTime, burstTime,qType,true));
        }
        MultiLevel multi_level =  new MultiLevel(processes, quantum);
        multi_level.run();
    }
    public static void main(String[] args) {

        while(true){
            System.out.println("1- SRTF");
            System.out.println("2- Round robin");
            System.out.println("3- Priority");
            System.out.println("4- Multilevel");
            int option = in.nextInt();
            if(option == 1){
                Srtf();
            }else if(option == 2){
                roundRobin();
            }else if(option == 3){
                pr();
            }else{
                multiLevel();
            }

        }

    }
}

