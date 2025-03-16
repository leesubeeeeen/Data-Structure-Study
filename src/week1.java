import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class week1 {
    private int[] a;
    private int size;

    public week1(){
        a = new int[1];
        size = 0;
    }
    public void resize(int newSize){
        if(size<=newSize){
            int[] newArr = new int[newSize];
            for(int i = 0 ; i<size; i++){
                newArr[i] = a[i];
            }
            a = newArr;
        }
    }
    public void reset(){
        a = new int[size];
        size = 0;
    }
    public void insert(int x){
        if(size == a.length){
            resize(2*a.length);
        }
        a[size] = x;
        size++;
    }
    public void print(){
        for(int i = 0 ; i<size ; i++){
            System.out.print(a[i]+" ");
        }System.out.println();
    }
    public void modify(int x){
        int lastStopJ = size-1;
        for(int i = 0 ; i<size ; i++){
            if(i == lastStopJ) break;
            else if(a[i]<x){
                for(int j = lastStopJ ; j>i ; j--){
                    if(a[i]>=x) break;
                    else {
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                        lastStopJ = j-1;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        week1 main = new week1();
        try{
            String filepath = "C:\\test.txt";
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                if(line.length() != 1){
                    String[] strNum = line.split(" ");
                    for(int i = 0 ;i<strNum.length ; i++){
                        main.insert(Integer.parseInt(strNum[i]));

                    }main.print();
                }else{
                    main.modify(Integer.parseInt(line));
                    main.print();
                    main.reset();
                }
            }
        }catch(IOException e){
            System.out.print("파일 읽어오기가 실패했습니다.");
        }
    }
}
