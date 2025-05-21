package StackAndQueue.Learning;

public class QueueLLImp {
    public class Node {
        int data;
        Node next;

        Node(int data){
            this.data = data;
            next = null;
        }
    }

    Node start = null, end = null;
    int size = 0;

    void push(int ele){
        Node temp = new Node(ele);
        if(start == null){
            start = temp;
            end = temp;
        }else{
            end.next = temp;
            end = temp;
        }
        size++;
    }

    int pop(){
        if(start == null){
            return -1;
        }
        size--;
        int val = start.data;
        start = start.next;
        return val;
    }

    int top(){
        if(start == null){
            return -1;
        }
        return start.data;
    }

    int size(){
        return size;
    }

    public static void main(String[] args) {
        QueueLLImp st = new QueueLLImp();

        st.push(19);
        st.push(22);
        st.push(323);
        st.push(42);
        System.out.println(st.size());
        System.out.println(st.top());
        st.pop();
        st.pop();
        st.pop();
        System.out.println(st.top());
    }

}
