import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class DNode{ //head다음값이 null인지 확인해야됨
    private int item;
    private DNode previous;
    private DNode next;
    public DNode(int newItem, DNode p, DNode q){
        item = newItem;
        previous = p;
        next = q;
    }
    public int getItem(){return item;}
    public DNode getPrevious(){return previous;}
    public DNode getNext(){return next;}
    public void setItem(int newItem){item = newItem;}
    public void setPrevious(DNode p){previous = p;}
    public void setNext(DNode newNext){next = newNext;}
}
class DList{
    protected DNode head,tail;
    public DList(){
        head = new DNode(0,null,null);
        tail = new DNode(0,head,null);
        head.setNext(tail);
    }

    public void insertBefore(DNode p, int newItem){
        DNode t = p.getPrevious(); //t가 p의 앞 노드 받음
        DNode newNode = new DNode(newItem,t,p);
        p.setPrevious(newNode);
        t.setNext(newNode);
    }
    public void print(){ //연결리스트 원소 출력
        for(DNode p = head.getNext() ; p!=tail ; p=p.getNext()){
            System.out.print(p.getItem()+" ");
        }System.out.println();
    }
    public void modify(int x){
        DNode i = head.getNext(); //head다음노드
        DNode j = tail.getPrevious(); //tail이전노드
        while(i!=j){
            if(head.getNext() == null){
                System.out.println("NULL");
                break;
            }
            if(i.getItem()<x){//x보다 작으면 swap
                int temp = i.getItem();
                i.setItem(j.getItem());
                j.setItem(temp);
                j = j.getPrevious(); //j를 왼쪽으로 이동
            }else{ //x보다 크거나 같으면
                i = i.getNext(); //i를 오른쪽으로 이동
            }
        }
    }
    public void clear(){
        head.setNext(tail);
        tail.setPrevious(head);
    }
}
public class week5 {
    public static void main(String[] args) {
        try{
            String file = "C:\\test.txt";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            DList d = new DList();
            String line;
            int row = 1;
            int[] arr;
            int x;
            boolean isEmpty = false;
            while((line = br.readLine()) != null){
                if(line.isEmpty()){
                    System.out.println();
                    System.out.println("NULL");
                    System.out.println("-------------------------------");
                    isEmpty = true;
                    row++;
                    continue;
                };

                if(row%2 == 1){ //홀수행일때 원소삽입, print
                    String[] str = line.split(" "); //공백을 기준으로 잘라 String타입 배열에 삽입
                    int length = str.length;
                    arr = new int[length]; //length만큼의 int 배열 생성

                    for(int i = 0 ; i<length ; i++){
                        arr[i] = Integer.parseInt(str[i]);
                    }
                    DNode temp = d.tail;
                    for(int i = length-1 ; i>=0 ; i--){
                        d.insertBefore(temp,arr[i]);
                        temp = temp.getPrevious(); //하나씩 앞으로 가기
                    }
                    d.print();
                }else{ //짝수행일때 modify 원소 읽고 호출, print
                    if(isEmpty){
                        row++;
                        continue;
                    }
                    x=Integer.parseInt(line);
                    d.modify(x);
                    d.print();
                    System.out.println("-------------------------------");
                    d.clear();
                }
                row++; //한줄씩 증가하며 읽기
            }
        }catch(IOException e){
            System.out.println("파일 읽기에 실패했습니다.");
        }
    }
}
