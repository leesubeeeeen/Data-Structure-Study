import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class week3{
    public static void main(String[] args) {
        try{
            String file = "C:\\test.txt";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int s = 0;
            int[] Array = new int[0]; // 정수 배열
            int row = 1; //행 번호
            while((line = br.readLine())!=null){
                if(row % 4 == 1){ //iteration
                    String[] strArr = line.split(" "); //공백으로 구분된 문자를 String배열 strArr에서 받음
                    int num = strArr.length;
                    Array = new int[num];
                    for(int i = 0 ; i<Array.length ; i++){ //Array에 값 채우기
                        Array[i] = Integer.parseInt(strArr[i]);
                    }
                    Arrays.sort(Array); //오름차순으로 Array정렬하기
                    Iteration.print(Array); //Iteration - 배열 요소 출력
                } else if(row % 4 == 2){
                    s = Integer.parseInt(line); //읽어온 String의 문자가 1개면 s로 받기
                    Iteration.iterate(Array,s);
                } else if(row % 4 == 3){ //recursion
                    String[] strArr = line.split(" "); //공백으로 구분된 문자를 String배열 strArr에서 받음
                    int num = strArr.length;
                    Array = new int[num];
                    for(int i = 0 ; i<Array.length ; i++) { //Array에 값 채우기
                        Array[i] = Integer.parseInt(strArr[i]);
                    }
                    Arrays.sort(Array);
                    System.out.println();
                    Recursion.print(Array);
                } else{ //row%4 == 0
                    s = Integer.parseInt(line); //읽어온 String의 문자가 1개면 s로 받기
                    Recursion.recurse(Array,s,0,Array.length-1);
                }
                row++;
            }
        }catch(IOException e){
            System.out.println("파일 읽어오기에 실패했습니다.");
        }
    }
}
class Iteration{
    public static void iterate(int[] arr, int s){
        int sum = 0;
        for(int i = 0 ; i<arr.length ; i++){ //O(n) 시간복잡도
            int a = arr[i];
            int b = s-a;
            if(Arrays.binarySearch(arr,i+1,arr.length,b)>=0){
                System.out.println("Pair found: ["+a+", "+b+"]");
            }
        }
    }
    public static void print(int[] arr){
        System.out.println("Iteration");
        for(int i = 0 ; i<arr.length ; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
class Recursion{
    public static int recurse(int[] arr, int s, int start,int end){
        int a = arr[start];
        int b = s-a;
        if(end<2 || start>=end){
            return -1;
        }
        int found = Arrays.binarySearch(arr,start+1, end+1,b);
        if(found>=0){
            System.out.println("Pair found: [" + a + ", " + b + "]");
        }
        return recurse(arr,s,start+1,end);
    }
    public static void print(int[] arr){
        System.out.println("Recursion");
        for(int i = 0 ; i<arr.length ; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}