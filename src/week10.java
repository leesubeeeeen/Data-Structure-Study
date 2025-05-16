import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class week10 {
    public static void search(int[] a, int node1, int node2){

        int parent1 = node1/2;
        int parent2 = node2/2;
        while(parent1!=0 && parent2!=0){
            if(parent1 != parent2){ //조상이 다르면
                if(parent1>parent2){
                    parent1 /= 2;
                }else{
                    parent2 /= 2;
                }
            }
            else{ //조상이 같으면
                int parentIndex = parent1;
                //key 출력
                System.out.println("key: "+a[parentIndex]);
                //depth 출력
                int x = 1; int cnt = 1;
                while(x<=parentIndex){
                    x *= 2;
                    cnt++;
                }
                int depth = cnt-1;
                System.out.println("depth: "+depth);
                System.out.println("----------------------------------");
                break;
            }
        }
    }
    public static void main(String[] args) {
        try{
            String file = "C:\\test.txt";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int row = 1;
            String line;
            String[] strArr; int[] intArr;
            int[] arr = new int[100];
            while((line = br.readLine())!=null){
                strArr = line.split(" ");
                if(row%2!=0){ //홀수행
                    intArr = new int[strArr.length];
                    for(int i = 0 ; i<strArr.length ; i++){
                        intArr[i] = Integer.parseInt(strArr[i]);
                    }
                    int index = 0;
                    for(int i = 0 ; i<intArr.length ; i++){
                        if(i%2==0){ //짝수번째
                            index = intArr[i];
                        }else{ //홀수번째
                            arr[index] = intArr[i];
                        }
                    }
                }else{ //짝수행
                    intArr = new int[strArr.length];
                    for(int i = 0 ; i<strArr.length ; i++){
                        intArr[i] = Integer.parseInt(strArr[i]);
                    }
                    int node1 = intArr[0];
                    int node2 = intArr[1];
                    search(arr,node1, node2);
                }

                row++;
            }
        }catch(IOException e){
            System.out.println("파일 읽어오기에 실패했습니다!");
        }
    }
}
