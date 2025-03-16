import java.io.IOException;
import java.util.Random;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        ArrList a = new ArrList();

        try{
            String fileName = "C:\\test.txt";
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] strArr = line.split(", "); //각 원소가 String으로 strArr에 저장되어있음
                for(int i = 0 ; i<strArr.length ; i++){
                    a.insert_dec(Integer.parseInt(strArr[i]));
                    if(a.getSize() != 1){
                        a.descend();
                    }
                }

            }
            a.print();
            int avgComParisons = a.getCount()/a.getSize();
            System.out.print("Average comparisons: "+avgComParisons);


        }catch(IOException e){
            System.out.println("파일 읽어오기에 오류가 발생했습니다.");
        }
    }
}
class ArrList{
    private int size;
    private int[] a;
    private int count = 0;
    public ArrList(){
        a = new int[1];
        size = 0;
    }
    public void descend(){
        for(int i = size-1 ; i>=0 ; i--){
            if(a[size-1]<a[size-2]){
                ++count;
                break;
            }else{
                for(int j = i-1 ; j>=0 ; j--){
                    if(a[j]<a[j+1]){
                        int temp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = temp;
                        ++count;
                    }
                }
            }
        }
    }
    public void print(){
        for(int i = 0 ; i<size ; i++){
            System.out.print(a[i]+" ");
        }System.out.println();
    }
    public void insert_dec(int x){
        if(size == a.length){
            resize(a.length*2); //크기같으면 resize
        }
        a[size] = x;
        ++size;

    }

    public void resize(int newSize){
        if(size<=newSize){
            int[] newArr = new int[newSize];
            for(int i = 0 ; i<size ; i++){
                newArr[i] = a[i];
            }
            a = newArr;
        }
    }

    public int getSize(){
        return size;
    }
    public int getCount(){
        return count;
    }
}
