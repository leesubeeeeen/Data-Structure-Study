import java.io.IOException;
import java.util.Random;
import java.io.FileReader;
import java.io.BufferedReader;

public class RandomO {
    public static void main(String[] args) {
        ArrList a = new ArrList();
        Random rd = new Random();
        for(int i = 0 ; i<20 ; i++){
            a.insert_dec(rd.nextInt());
            if(a.getSize() != 1) {
                a.descend();
            }
        }
        a.print();
        int avgComParisons = a.getCount()/a.getSize();
        System.out.print("Average comparisons: "+avgComParisons);
    }
}