import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.EmptyStackException;

class ArrayStack{
    private String s[];
    private int top;
    private int size;
    public ArrayStack(){
        s = new String[1];
        top = -1;
        size = 0;
    }
    public void resize(int newSize){
        String[] newArr = new String[newSize];
        for(int i = 0 ; i<size ; i++){
            newArr[i] = s[i];
        }
        s = newArr;
    }
    public void push(String item){
        if(size == s.length) resize(2*s.length);
        s[++top] = item;
        size++;
    }
    public String pop(){
        if(isEmpty()) throw new EmptyStackException();
        String item = s[top];
        s[top--] = null;
        if(size()>0 && size()==s.length/4){
            resize(s.length/2);
        }
        size--;
        return item;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
}
class ArrayQueue{
    private ArrayStack A; //처음 넣기
    private ArrayStack B; //큐형태로 넣기
    private String item;
    public ArrayQueue(){
        A = new ArrayStack();
        B = new ArrayStack();
    }
    public void add(String item){
        A.push(item);
    }
    public void BtoA(int size){ //B에서 A로 옮기기
        for(int i = 0 ; i<size ; i++) { //다시 A에 쏟기
            item = B.pop();
            A.push(item);
        }
    }
    public void AtoB(int size){ //A에서 B로 옮기기
        for(int i = 0 ; i<size ; i++){
            item = A.pop();
            B.push(item);
        }
    }
    public String remove(){ //B에서 pop만..
        if(B.isEmpty()){
            return "더 이상 제거할 대상이 없습니다.";
        }
        else{
            return B.pop(); //pop한거 print
        }
    }
    public boolean isEmpty(){
        return B.isEmpty();
    }
}
public class week7 {
    public static void main(String[] args) {
        try{
            String file = "C:\\test.txt";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            ArrayQueue q = new ArrayQueue();

            String line;
            String[] strArr;
            int length = 0;
            int row = 1;
            boolean error = false; //2번의 remove() 수행 중 empty상태가 발생할 경우 true
            while((line = br.readLine())!=null){
                if(row%2!=0){ //홀수행
                    strArr = line.split(" ");
                    length += strArr.length;
                    for(int i = 0 ; i<strArr.length ; i++){
                        q.add(strArr[i]); //stack A에 push
                    }
                    q.AtoB(length); //remove전 B로 옮겨닮기
                    for(int i = 0 ; i<2 ; i++){
                        System.out.print(q.remove()+" "); //B에서 2번 remove
                        if(q.isEmpty()) {
                            error = true;
                        }
                    }
                    if(error){
                        System.out.println();
                        length -= 1;
                        q.BtoA(length);
                    }else{
                        length -= 2; //2개 빠졌으니까
                        System.out.println();
                        q.BtoA(length); //B에서 A로 옮겨닮기
                    }
                }
                else{ //짝수행
                    strArr = line.split(" ");
                    length += strArr.length; //더 들어온 원소 개수까지 더하기
                    for(int i = 0 ; i<strArr.length ; i++){
                        q.add(strArr[i]); //stack A에 push
                    }
                    q.AtoB(length);
                    for(int i = 0 ; i<length ; i++){
                        System.out.print(q.remove()+" ");
                    }
                    System.out.println();

                    length = 0;
                }
                row++;
            }
        }catch(IOException e){
            System.out.println("파일 읽기에 실패했습니다.");
        }
    }
}
