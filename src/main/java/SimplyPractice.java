import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimplyPractice {


    SimplyPractice()
    {
        //System.out.println("yoyo");
    }

    String x="hello";


    public static void main(String[] args) {
        List<Object> l=new ArrayList<Object>();
        l.add("fed");
        l.add(1, new SimplyPractice().x);
        System.out.println(l);
        List<Object> m=new LinkedList<>();
        m.addAll(l);
        System.out.println(m);
        System.out.println(m.retainAll(l));
    }
}
