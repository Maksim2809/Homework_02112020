
import gui.MainWindow;

import java.util.HashMap;
import java.util.Scanner;
import Polynoms.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /*Scanner in = new Scanner(System.in);
        HashMap<Double,Double> v = new HashMap<Double, Double>();
        v.put(0.0, 0.0);
        v.put(1.0, 1.0);
        v.put(2.0, 4.0);
        Newton nw = new Newton(v);

        nw.AddUzel(10.0,125.0);
        System.out.println(nw.polin);
        v.put(10.0,125.0);
        nw = new Newton(v);
        System.out.println(nw.polin);

         */



        var vnd = new MainWindow();
        vnd.setVisible(true);
    }
}
