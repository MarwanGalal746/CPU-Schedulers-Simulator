import java.util.ArrayList;

public class Scheduler {

    public static void main(String[] args) {
        ArrayList<process>p=new ArrayList<>();
        process temp1 = new process("p1",0,1);
        p.add(temp1);
        process temp2 = new process("p2",1,7);
        p.add(temp2);
        process temp3 = new process("p3",2,3);
        p.add(temp3);
        process temp4 = new process("p4",3,6);
        p.add(temp4);
        process temp5 = new process("p5",4,5);
        p.add(temp5);
        process temp6 = new process("p6",5,15);
        p.add(temp6);
        process temp7 = new process("p7",15,8);
        p.add(temp7);

        SRTF s = new SRTF(p,0);
        s.execute();
        /*for(int i=0 ; i<p.size();i++)
            System.out.println(p.get(i));*/
    }
}

