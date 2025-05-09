import java.util.EmptyStackException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Node{
    private int item;
    private Node next;

    //생성자
    public Node(int newItem, Node nextNode){
        item = newItem;
        next = nextNode;
    }

    //getter, setter
    public void setItem(int item) {
        this.item = item;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public int getItem() {
        return item;
    }
}

class ListStack{
    private Node top; //stack top항목가진 Node를 가리킴
    private int size; //스택 항목수
    public ListStack(){
        top = new Node(0,null); //dummy node
        size = 0;
    }
    public boolean isEmpty(){return size == 0;}
    public void push(int newItem){
        Node newNode = new Node(newItem, top);
        top = newNode;
        size++;
    }
    public int pop(){
        if(isEmpty()) throw new EmptyStackException();
        int item = top.getItem();
        top = top.getNext();
        size--;
        return item;
    }
    public void calc_postfix(String[] a){
        for(int i = 0 ; i<a.length ; i++){ //연산 수행
            if(isOperator(a[i])){ //연산자면
                int B = pop();
                int A = pop();

                int result = 0;

                switch(a[i]){ //연산진행
                    case "+":
                        result = A+B;
                        break;
                    case "-":
                        result = A-B;
                        break;
                    case "*":
                        result = A*B;
                        break;
                    case "/":
                        if(B == 0){
                            System.out.println("나눗셈은 0으로 나눌 수 없습니다.");
                            return;
                        }
                        result = A/B;
                        break;
                }
                push(result); //push result
            }else{ //피연산자이면
                push(Integer.parseInt(a[i]));
            }
        }
        int finalResult = pop();
        if(isEmpty()){
            System.out.println(finalResult);
        }else{
            System.out.println("후위 표기식이 잘못되었습니다.");
        }

    }
    public static boolean isOperator(String str){
        switch(str){
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }
}

public class week6 {

    public static void main(String[] args) {
        try{
            String file = "C:\\test.txt";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            ListStack stack = new ListStack();

            String line;
            String[] arr;

            while((line = br.readLine()) != null){
                arr = line.split(" ");
                stack.calc_postfix(arr);
            }
        }catch(IOException e){
            System.out.println("파일 읽어오기에 실패했습니다.");
        }
    }
}
