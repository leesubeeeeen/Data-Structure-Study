import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class Node1{
    private String item; //값
    private Node1 next; //다음 노드 주솟값
    Node1(String newdata,Node1 node){
        item = newdata;
        next = node;
    }
    //getter, setter 설정
    public String getItem(){
        return item;
    }
    public void setItem(String newItem){
        item = newItem;
    }
    public Node1 getNext(){
        return next;
    }
    public void setNext(Node1 newNext){
        next = newNext;
    }

}
class SList{
    protected Node1 head; //head node
    SList(){
        //dummy node
        head = new Node1(null,null);
    }
    public void print(){ //요소 출력
        for(Node1 p = head.getNext() ; p!=null ; p=p.getNext()){
            System.out.print(p.getItem()+" ");
        }
    }
    public void insert_inc(String x) { //오름차순으로 삽입
        Node1 newNode = new Node1(x, null); //새로 삽입할 노드
        Node1 current = head; //현재 노드
        while (current.getNext() != null && current.getNext().getItem().compareTo(x) < 0) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
    }
    public void clear(){
        head.setNext(null);
    }
    public void delete(String x) { //x 삭제
        Node1 prev = head; //이전값
        Node1 curr = head.getNext(); //이후값

        while (curr != null) { //끝이 아닐때까지
            if (x.equals(curr.getItem())) { //x와 같으면
                prev.setNext(curr.getNext()); // 삭제
                curr.setNext(null);
                break;
            }
            else{ //x랑 다르면
                prev = curr; //한칸 앞으로
                curr = curr.getNext(); //한칸 앞으로
            }
        }
    }
}

public class week4 {
    public static void main(String[] args) {
        try{
            String file = "C:\\test.txt";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String delStr;
            int row = 1;
            SList s = new SList();
            while((line = br.readLine())!=null) {
                if (row % 2 == 1) {
                    String[] strArr = line.split(" "); //line을 " "으로 쪼개 배열에 저장

                    for (int i = 0; i < strArr.length; i++) {
                        s.insert_inc(strArr[i]); //사전식 오름차순으로 원소 삽입
                    }
                    s.print();
                    System.out.println();

                }else if (row % 2== 0) {
                    delStr = line;
                    s.delete(delStr);
                    s.print();
                    System.out.println();
                    s.clear();
                }row++;

            }
        }catch(IOException e){
            System.out.println("파일을 읽어오는 데 실패했습니다.");
        }
    }
}
