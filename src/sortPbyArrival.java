import java.util.Comparator;

public class sortPbyArrival implements Comparator<process> {

    public int compare(process p1, process p2){
        return p1.getArrivalTime()-p2.getArrivalTime();
    }
}
